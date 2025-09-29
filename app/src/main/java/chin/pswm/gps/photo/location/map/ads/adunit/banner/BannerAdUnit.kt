package chin.pswm.gps.photo.location.map.ads.adunit.banner

import android.app.Activity
import android.os.Bundle
import chin.pswm.gps.photo.location.map.ads.adunit.AdDataConfig
import chin.pswm.gps.photo.location.map.ads.adunit.AdUnit
import chin.pswm.gps.photo.location.map.ads.adunit.common.AdsStatus
import chin.pswm.gps.photo.location.map.ads.adunit.decodeAdUnit
import chin.pswm.gps.photo.location.map.ads.ext.AppUtils
import chin.pswm.gps.photo.location.map.ads.ext.Tracking
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdValue
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnPaidEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import timber.log.Timber
import kotlin.coroutines.resume

class BannerAdUnit(
    vararg adIDs: Pair<String, String>, // name - ad unit
    val name: String,
    var bannerType: BannerType = BannerType.BANNER_ADAPTIVE,
    private val timeout: Long = 20000,
    private val defaultEnable: Boolean = true,
    var onImpression: () -> Unit = {},
    var onClicked: () -> Unit = {},
) : AdUnit<AdViewCustom>(listAdData = adIDs.filter { it.first.isNotEmpty() }
    .map { AdDataConfig(defaultId = it.second.decodeAdUnit, name = it.first.decodeAdUnit, defaultEnable = defaultEnable) }) {

    var adWidth: Int = 0 // default full screen

    companion object {
        private val banners = arrayListOf<BannerAdUnit>()
        fun getBanner(
            adUnitName: String,
            adUnit: String,
            bannerType: BannerType = BannerType.BANNER_ADAPTIVE
        ): BannerAdUnit {
            var banner = banners.find { ban -> ban.name == adUnitName }
            return if (banner == null) {
                banner = BannerAdUnit(
                    adUnitName to adUnit,
                    name = adUnitName,
                    bannerType = bannerType
                )
                banners.add(banner)
                banner
            } else {
                banner.apply {
                    this.bannerType = bannerType
                }
            }
        }
    }

    fun loadAd(activity: Activity) {
        if (!enabled || !adsManager.canShowAds || !AppUtils.isNetworkConnected(activity)) {
            Timber.tag(TAG)
                .d("loadAd: fail ${listAdData.map { it.name }} disable/internet/consent/billing")
            _statusFlow.value = AdsStatus.FAIL
            return
        }

        if (shouldLoadAd()) {
            _statusFlow.value = AdsStatus.LOADING
            Timber.tag(TAG).d("loadAd: load ${name.decodeAdUnit} - type $bannerType")
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    val result = withTimeoutOrNull(timeout) {
                        var adViewCustom: AdViewCustom? = null
                        for (adConfig in listAdData) {
                            adViewCustom = fetchAd(
                                activity = activity,
                                adDataConfig = adConfig,
                                onLoadImpression = {
                                    adImpressionTime = System.currentTimeMillis()
                                    _statusFlow.value = AdsStatus.IMPRESSED
                                    onImpression()
                                },
                                onLoadClicked = {
                                    onClicked()
                                }
                            )
                            if (adViewCustom != null) break
                        }
                        adViewCustom
                    }
                    if (result != null) {
                        if (bannerType == BannerType.BANNER_COLLAPSIBLE) {
                            adData?.ad?.destroy()
                        }
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
        activity: Activity,
        adDataConfig: AdDataConfig,
        onLoadImpression: () -> Unit,
        onLoadClicked: () -> Unit
    ): AdViewCustom? {
        return suspendCancellableCoroutine { continuation ->
            try {
                if (!adDataConfig.enable) {
                    continuation.resume(null)
                } else {
                    val adView = AdView(activity)
                    adView.adUnitId = adDataConfig.id
                    adView.setAdSize(
                        if (adWidth == 0) {
                            BannerSize.adSize(activity, bannerType)
                        } else {
                            BannerSize.adSize(activity, adWidth, bannerType)
                        }
                    )
                    adView.adListener = object : AdListener() {
                        override fun onAdLoaded() {
                            super.onAdLoaded()
                            val mediation = adView.responseInfo?.mediationAdapterClassName
                            val isFB = mediation?.contains("facebook") == true
                            Timber.tag(TAG).d("onAdLoaded: ${displayText(adDataConfig)} - $mediation")

                            if (adDataConfig.name.isNotEmpty()) {
                                Tracking.logEvent("loaded_${adDataConfig.name}")
                                if (isFB) {
                                    Tracking.logEvent("loaded_meta_${adDataConfig.name}")
                                }
                            }

                            adView.onPaidEventListener = OnPaidEventListener { adValue: AdValue ->
                                if (adDataConfig.name.isNotEmpty()) {
                                    Tracking.logEvent("impression_${adDataConfig.name}")
                                    if (isFB) {
                                        Tracking.logEvent("impression_meta_${adDataConfig.name}")
                                    }
                                }
                                if (adValue.valueMicros != 0L) {
                                    Timber.tag(TAG)
                                        .d("OnPaidEvent banner:${adValue.valueMicros / 1000000} ${adValue.currencyCode}")
                                }

                                adjustManager.logAdRevenue(
                                    context = activity,
                                    adValue = adValue,
                                    adUnit = adDataConfig.id,
                                    type = "banner",
                                    mediation = adView.responseInfo?.mediationAdapterClassName
                                )
                            }

                            continuation.resume(
                                AdViewCustom(
                                    ad = adView,
                                    name = adDataConfig.name,
                                    isMeta = isFB
                                )
                            )
                        }

                        override fun onAdFailedToLoad(error: LoadAdError) {
                            super.onAdFailedToLoad(error)
                            Timber.tag(TAG)
                                .e("onAdFailedToLoad: ${displayText(adDataConfig)} ${error.message}")
                            continuation.resume(null)
                        }

                        override fun onAdImpression() {
                            super.onAdImpression()
                            Timber.tag(TAG).d("onAdImpression: ${displayText(adDataConfig)}")
                            onLoadImpression()
                        }

                        override fun onAdClicked() {
                            adsManager.clickedAnyAds = true
                            adsManager.disableAdResumeOneTime = true
                            super.onAdClicked()
                            Timber.tag(TAG).d("onAdClicked: ${displayText(adDataConfig)}")
                            onLoadClicked()

                            if (adDataConfig.name.isNotEmpty()) {
                                Tracking.logEvent("clicked_${adDataConfig.name}")
                                if (adData?.isMeta == true) {
                                    Tracking.logEvent("clicked_meta_${adDataConfig.name}")
                                }
                            }
                        }
                    }

                    // start loadAd
                    Timber.tag(TAG).d("loading:  ${displayText(adDataConfig)}")
                    adView.loadAd(
                        AdRequest.Builder()
                            .apply {
                                if (bannerType == BannerType.BANNER_COLLAPSIBLE) {
                                    val extras = Bundle()
                                    extras.putString("collapsible", "bottom")
                                    addNetworkExtrasBundle(AdMobAdapter::class.java, extras)
                                }
                            }
                            .build()
                    )
                }

                continuation.invokeOnCancellation {
                    Timber.tag(TAG).e("loadAd:  ${adDataConfig.id} canceled: ${it?.message}")
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                continuation.resume(null)
            }
        }
    }

    override fun releaseAd() {
        adData?.ad?.destroy()
        adData = null
    }
}