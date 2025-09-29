package chin.pswm.gps.photo.location.map.ads.adunit.interstitial

import android.app.Activity
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import chin.pswm.gps.photo.location.map.ads.adunit.AdDataConfig
import chin.pswm.gps.photo.location.map.ads.adunit.AdUnit
import chin.pswm.gps.photo.location.map.ads.adunit.common.AdErrorCode
import chin.pswm.gps.photo.location.map.ads.adunit.common.AdLoadError
import chin.pswm.gps.photo.location.map.ads.adunit.common.AdsStatus
import chin.pswm.gps.photo.location.map.ads.adunit.decodeAdUnit
import chin.pswm.gps.photo.location.map.ads.ext.AppUtils
import chin.pswm.gps.photo.location.map.ads.ext.Tracking
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdValue
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnPaidEventListener
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import timber.log.Timber
import kotlin.coroutines.resume

class InterstitialAdUnit(
    vararg adIDs: Pair<String, String>, // name - ad unit
    private val timeout: Long = 20000,
    private val defaultEnable: Boolean = true,
    var onImpression: () -> Unit = {},
    var onClicked: () -> Unit = {},
    var onLoaded: (success: Boolean) -> Unit = {},
) : AdUnit<InterstitialAdCustom>(listAdData = adIDs.filter { it.first.isNotEmpty() }
    .map { AdDataConfig(defaultId = it.second.decodeAdUnit, name = it.first.decodeAdUnit, defaultEnable = defaultEnable) }) {

    init {
        Timber.tag(TAG).d("init ${listAdData.map { it.name }}")
    }

    var disableOnTime = arrayListOf<String>()

    fun forceShow(activity: Activity?, condition: Boolean = true, onNextAction: () -> Unit = {}) {

        var shouldNext = true
        fun next() {
            try {
                if (!shouldNext) return
                onNextAction.invoke()

                shouldNext = false
            } catch (_: Exception) {
                next()
            }
        }
        if (condition && activity != null) {
            show(
                activity = activity,
                condition = true,
                onNextAction = {
                    next()
                },
                onAdFailedToShow = {
                    next()
                },
                onAdClosed = {
                    next()
                },
                onInterstitialShow = {
                    next()
                }
            )
        } else {
            next()
        }
    }

    fun canShowAd() =
        !(!enabled || !adsManager.canShowAds || adsManager.isShowingAds) && status == AdsStatus.SUCCESS

    fun show(
        activity: Activity,
        condition: Boolean = true,
        onNextAction: () -> Unit = {},
        onAdFailedToShow: (adError: AdLoadError?) -> Unit = {},
        onInterstitialShow: () -> Unit = {},
        onAdClosed: () -> Unit = {},
    ) {
        Timber.tag(TAG).d("show: ${listAdData.map { it.name }}")
        if (!enabled || !adsManager.canShowAds || !condition || adsManager.isShowingAds) {
            Timber.tag(TAG)
                .e("show: error $enabled/${adsManager.canShowAds}/$condition/${adsManager.isShowingAds}")
            onNextAction()
            return
        }

        when (status) {
            AdsStatus.NONE, AdsStatus.LOADING, AdsStatus.FAIL, AdsStatus.IMPRESSED -> {
                Timber.tag(TAG).e("show: error status -> $status")
                onNextAction()
            }

            AdsStatus.SUCCESS -> {
                adData?.ad?.setImmersiveMode(true)
                adData?.ad?.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdClicked() {
                        adsManager.clickedAnyAds = true
                        adsManager.disableAdResumeOneTime = true
                        super.onAdClicked()
                        onClicked()
                        if (adData?.name?.isNotEmpty() == true) {
                            Tracking.logEvent("clicked_${adData?.name}")
                            if (adData?.isMeta == true) {
                                Tracking.logEvent("clicked_meta_${adData?.name}")
                            }
                        }
                    }

                    override fun onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent()
                        onAdClosed()
                        adsManager.isShowingAds = false
                    }

                    override fun onAdFailedToShowFullScreenContent(error: AdError) {
                        super.onAdFailedToShowFullScreenContent(error)
                        Timber.tag(TAG).e("onAdFailedToShowFullScreenContent: ${error.message}")
                        onAdFailedToShow(AdLoadError(error.code, error.message))
                        adsManager.isShowingAds = false
                        _statusFlow.value = AdsStatus.FAIL
                    }

                    override fun onAdImpression() {
                        super.onAdImpression()
                        Timber.tag(TAG).d("onAdImpression: ${listAdData.map { it.name }}")
                        adImpressionTime = System.currentTimeMillis()
                        onImpression()
                        _statusFlow.value = AdsStatus.IMPRESSED
                    }

                    override fun onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent()
                        onInterstitialShow()
                    }
                }

                adsManager.isShowingAds = true

                if (activity is ComponentActivity && activity.lifecycle.currentState.isAtLeast(
                        Lifecycle.State.RESUMED
                    )
                ) {
                    adData?.ad?.show(activity)
                } else {
                    Timber.tag(TAG).d("showAds: cannot show ad in background")
                    onAdFailedToShow(
                        AdLoadError(
                            AdErrorCode.ERROR_CODE_SHOW_IN_BACKGROUND.code,
                            "cannot show ads in background"
                        )
                    )
                    adsManager.isShowingAds = false
                }
            }
        }
    }

    fun loadAd(context: Context) {
        if (!enabled || !adsManager.canShowAds || !AppUtils.isNetworkConnected(context)) {
            Timber.tag(TAG)
                .d("loadAd: fail ${listAdData.firstOrNull()?.name} $enabled/${adsManager.canShowAds}/consent/billing")
            _statusFlow.value = AdsStatus.FAIL
            return
        }

        if (shouldLoadAd()) {
            _statusFlow.value = AdsStatus.LOADING
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    val result = withTimeoutOrNull(timeout) {
                        var interstitialAdCustom: InterstitialAdCustom? = null
                        for (adConfig in listAdData) {
                            if (adConfig.name in disableOnTime) {
                                disableOnTime.remove(adConfig.name)
                            } else {
                                interstitialAdCustom = fetchAd(context = context, adDataConfig = adConfig)
                                if (interstitialAdCustom != null) break
                            }
                        }
                        interstitialAdCustom
                    }
                    onLoaded(result != null)
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
        } else {
            Timber.tag(TAG).d("loadAd:ad is available $status")
        }
    }

    private suspend fun fetchAd(
        context: Context,
        adDataConfig: AdDataConfig,
    ): InterstitialAdCustom? {
        return suspendCancellableCoroutine { continuation ->
            try {
                if (!adDataConfig.enable) {
                    continuation.resume(null)
                } else {
                    Timber.tag(TAG).d("loading: ${displayText(adDataConfig)}")
                    val adRequest = AdManagerAdRequest.Builder().build()
                    InterstitialAd.load(
                        context,
                        adDataConfig.id,
                        adRequest,
                        object : InterstitialAdLoadCallback() {
                            override fun onAdLoaded(interAd: InterstitialAd) {
                                super.onAdLoaded(interAd)
                                val mediation = interAd.responseInfo.mediationAdapterClassName
                                val isFB = mediation?.contains("facebook") == true
                                Timber.tag(TAG).d("onAdLoaded: ${displayText(adDataConfig)} - $mediation")

                                if (adDataConfig.name.isNotEmpty()) {
                                    Tracking.logEvent("loaded_${adDataConfig.name}")
                                    if (isFB) {
                                        Tracking.logEvent("loaded_meta_${adDataConfig.name}")
                                    }
                                }

                                interAd.onPaidEventListener =
                                    OnPaidEventListener { adValue: AdValue ->
                                        if (adDataConfig.name.isNotEmpty()) {
                                            Tracking.logEvent("impression_${adDataConfig.name}")
                                            if (isFB) {
                                                Tracking.logEvent("impression_meta_${adDataConfig.name}")
                                            }
                                        }
                                        if (adValue.valueMicros != 0L) {
                                            Timber.tag(TAG).d(
                                                "OnPaidEvent interstitial:${adValue.valueMicros / 1000000} ${adValue.currencyCode}"
                                            )
                                        }

                                        adjustManager.logAdRevenue(
                                            context = context,
                                            adValue = adValue,
                                            adUnit = adDataConfig.id,
                                            type = "interstitial",
                                            mediation = interAd.responseInfo.mediationAdapterClassName
                                        )
                                    }

                                continuation.resume(
                                    InterstitialAdCustom(
                                        ad = interAd,
                                        name = adDataConfig.name,
                                        isMeta = isFB
                                    )
                                )
                            }

                            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                                super.onAdFailedToLoad(loadAdError)
                                Timber.tag(TAG)
                                    .e("onAdFailedToLoad: ${displayText(adDataConfig)} ${loadAdError.message}")
                                continuation.resume(null)
                            }
                        })
                }
                continuation.invokeOnCancellation {
                    Timber.tag(TAG).e("loadAd: ${adDataConfig.id} canceled: ${it?.message}")
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                continuation.resume(null)
            }
        }
    }
}