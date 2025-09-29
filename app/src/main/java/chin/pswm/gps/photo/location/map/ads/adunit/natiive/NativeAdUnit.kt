package chin.pswm.gps.photo.location.map.ads.adunit.natiive

import android.content.Context
import chin.pswm.gps.photo.location.map.ads.adunit.AdDataConfig
import chin.pswm.gps.photo.location.map.ads.adunit.AdUnit
import chin.pswm.gps.photo.location.map.ads.adunit.common.AdsStatus
import chin.pswm.gps.photo.location.map.ads.adunit.decodeAdUnit
import chin.pswm.gps.photo.location.map.ads.ext.AppUtils
import chin.pswm.gps.photo.location.map.ads.ext.Tracking
import chin.pswm.gps.photo.location.map.ads.ext.tryWithoutCatch
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdValue
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.VideoOptions
import com.google.android.gms.ads.formats.AdManagerAdViewOptions
import com.google.android.gms.ads.nativead.NativeAdOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import timber.log.Timber
import kotlin.coroutines.resume

class NativeAdUnit(
    vararg adIDs: Pair<String, String>, // name - ad unit
    var volume: Float = 0f,
    private val timeout: Long = 20000,
    private val defaultEnable: Boolean = true,
    private val delayFloor: Long = 0,
    val onImpression: (String) -> Unit = {},
    val onClicked: (String) -> Unit = {},
) : AdUnit<NativeAdCustom>(listAdData = adIDs.filter { it.first.isNotEmpty() }
    .map { AdDataConfig(defaultId = it.second.decodeAdUnit, name = it.first.decodeAdUnit, defaultEnable = defaultEnable) }) {

    init {
        Timber.tag(TAG).d("createAds ${listAdData.map { it.name }}: ")
    }

    var isClicked: Boolean = false

    fun addOnGoing() = enabled && status != AdsStatus.NONE && status != AdsStatus.FAIL

    fun loadAd(context: Context) {
        if (!enabled || !adsManager.canShowAds || !AppUtils.isNetworkConnected(context)) {
            Timber.tag(TAG)
                .d("loadAd: fail ${listAdData.firstOrNull()?.name} disable/internet/consent/billing")
            _statusFlow.value = AdsStatus.FAIL
            return
        }

        if (shouldLoadAd()) {
            val minTimeReloadIfNotClick = prefs.getLong("native_min_time_reload", 0)
            if (status == AdsStatus.IMPRESSED && !isClicked && minTimeReloadIfNotClick > 0 && System.currentTimeMillis() - adLoadedTime < minTimeReloadIfNotClick) {
                Timber.tag(TAG).d("ad just show < ${minTimeReloadIfNotClick / 1000}s")
                return
            }

            _statusFlow.value = AdsStatus.LOADING
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    isClicked = false
                    val result = withTimeoutOrNull(timeout) {
                        var nativeAdCustom: NativeAdCustom? = null
                        for (adConfig in listAdData) {
                            nativeAdCustom = fetchAd(
                                context = context,
                                adDataConfig = adConfig,
                                onLoadImpression = {
                                    adImpressionTime = System.currentTimeMillis()
                                    onImpression(it)
                                    _statusFlow.value = AdsStatus.IMPRESSED
                                },
                                onLoadClicked = {
                                    isClicked = true
                                    onClicked(it)
                                }
                            )
                            if (nativeAdCustom != null) break
                            delay(delayFloor)
                        }
                        nativeAdCustom
                    }
                    if (result != null) {
                        adData = result
                        adLoadedTime = System.currentTimeMillis()
                        _statusFlow.value = AdsStatus.SUCCESS
                    } else {
                        adData = null
                        _statusFlow.value = AdsStatus.FAIL
                    }
                }
            }
        }
    }

    private suspend fun fetchAd(
        context: Context,
        adDataConfig: AdDataConfig,
        onLoadImpression: (String) -> Unit,
        onLoadClicked: (String) -> Unit
    ): NativeAdCustom? {
        return suspendCancellableCoroutine { continuation ->
            try {
                if (!adDataConfig.enable) {
                    continuation.resume(null)
                } else {
                    val videoOptions = VideoOptions.Builder()
                        .setStartMuted(volume == 0f)
                        .build()

                    val adOptions = NativeAdOptions.Builder()
                        .setVideoOptions(videoOptions)
                        .build()

                    Timber.tag(TAG).d("loading : ${displayText(adDataConfig)}")
                    val adLoader = AdLoader.Builder(context, adDataConfig.id)
                        .forNativeAd { nativeAd ->
                            val mediation = nativeAd.responseInfo?.mediationAdapterClassName
                            val isFB = mediation?.contains("facebook") == true
                            Timber.tag(TAG).d("onAdLoaded: ${displayText(adDataConfig)} - $mediation")

                            if (adDataConfig.name.isNotEmpty()) {
                                Tracking.logEvent("loaded_${adDataConfig.name}")
                                if (isFB) {
                                    Tracking.logEvent("loaded_meta_${adDataConfig.name}")
                                }
                            }
                            nativeAd.setOnPaidEventListener { adValue: AdValue ->
                                if (adDataConfig.name.isNotEmpty()) {
                                    Tracking.logEvent("impression_${adDataConfig.name}")
                                    if (isFB) {
                                        Tracking.logEvent("impression_meta_${adDataConfig.name}")
                                    }
                                }

                                adjustManager.logAdRevenue(
                                    context = context,
                                    adValue = adValue,
                                    adUnit = adDataConfig.id,
                                    type = "native",
                                    mediation = mediation
                                )
                            }

                            continuation.resume(
                                NativeAdCustom(
                                    ad = nativeAd,
                                    name = adDataConfig.name,
                                    isMeta = isFB
                                )
                            )
                        }.withAdManagerAdViewOptions(AdManagerAdViewOptions.Builder().apply {
                            setManualImpressionsEnabled(false)
                        }.build())
                        .withAdListener(object : AdListener() {
                            override fun onAdFailedToLoad(error: LoadAdError) {
                                Timber.tag(TAG)
                                    .e("onAdFailedToLoad: ${displayText(adDataConfig)} ${error.message}")
                                continuation.resume(null)
                            }

                            override fun onAdImpression() {
                                super.onAdImpression()
                                Timber.tag(TAG).w("onAdImpression: ${displayText(adDataConfig)}")
                                onLoadImpression(adDataConfig.id)
                            }

                            override fun onAdClicked() {
                                adsManager.clickedAnyAds = true
                                adsManager.disableAdResumeOneTime = true
                                super.onAdClicked()
                                Timber.tag(TAG).d("onAdClicked: ${displayText(adDataConfig)}")
                                onLoadClicked(adDataConfig.id)
                                if (adDataConfig.name.isNotEmpty()) {
                                    Tracking.logEvent("clicked_${adDataConfig.name}")
                                    if (adData?.isMeta == true) {
                                        Tracking.logEvent("clicked_meta_${adDataConfig.name}")

                                    }
                                }
                            }
                        })
                        .withNativeAdOptions(adOptions)
                        .build()
                    adLoader.loadAd(AdRequest.Builder().build())
                }

                continuation.invokeOnCancellation {
                    Timber.tag(TAG)
                        .e("loadAd: ${displayText(adDataConfig)} canceled: ${it?.message}")
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                continuation.resume(null)
            }
        }
    }

    override fun releaseAd() {
        tryWithoutCatch {
            isClicked = false
            adData?.ad?.destroy()
        }
    }
}