package chin.pswm.gps.photo.location.map.ads.adunit.reward

import android.app.Activity
import android.content.Context
import androidx.core.os.bundleOf
import chin.pswm.gps.photo.location.map.ads.adunit.AdDataConfig
import chin.pswm.gps.photo.location.map.ads.adunit.AdUnit
import chin.pswm.gps.photo.location.map.ads.adunit.common.AdLoadError
import chin.pswm.gps.photo.location.map.ads.adunit.common.AdsStatus
import chin.pswm.gps.photo.location.map.ads.adunit.decodeAdUnit
import chin.pswm.gps.photo.location.map.ads.ext.AppUtils
import chin.pswm.gps.photo.location.map.ads.ext.Tracking
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdValue
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnPaidEventListener
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import timber.log.Timber
import kotlin.coroutines.resume

class RewardAdUnit(
    vararg adIDs: Pair<String, String>, // name - ad unit
    private val timeout: Long = 20000,
    private val defaultEnable: Boolean = true,
    private val rewardType: RewardType = RewardType.Video,
    var onImpression: () -> Unit = {},
    var onClicked: () -> Unit = {},
) : AdUnit<RewardAd>(listAdData = adIDs.filter { it.first.isNotEmpty() && it.second.isNotEmpty() }
    .map { AdDataConfig(defaultId = it.second.decodeAdUnit, name = it.first.decodeAdUnit, defaultEnable = defaultEnable) }) {

    var adsEarned = false

    fun canShowAd() = enabled && adsManager.canShowAds && !adsManager.isShowingAds && status == AdsStatus.SUCCESS

    fun show(
        activity: Activity,
        condition: Boolean = true,
        onUserEarnedReward: (RewardItem) -> Unit = {},
        onNextAction: () -> Unit = {},
        onAdFailedToShow: (adError: AdLoadError?) -> Unit = {},
        onRewardShownShow: () -> Unit = {},
        onAdClosed: () -> Unit = {},
    ) {
        if (!condition || !canShowAd()) {
            Timber.tag(TAG).e(
                "show: error $enabled/${adsManager.canShowAds}/$condition/${adsManager.isShowingAds}"
            )
            onNextAction()
            return
        }

        when (status) {
            AdsStatus.NONE, AdsStatus.LOADING, AdsStatus.FAIL, AdsStatus.IMPRESSED -> {
                Timber.tag(TAG).e("show: error status -> $status")
                onNextAction()
            }

            AdsStatus.SUCCESS -> {
                val fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdClicked() {
                        super.onAdClicked()
                        if (adData?.name?.isNotEmpty() == true) {
                            Tracking.logEvent("clicked_${adData?.name}")
                        }
                        onClicked()
                    }

                    override fun onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent()
                        adsManager.hideLoading()
                        onAdClosed()
                        adsManager.isShowingAds = false
                    }

                    override fun onAdFailedToShowFullScreenContent(error: AdError) {
                        super.onAdFailedToShowFullScreenContent(error)
                        Timber.tag(TAG).e("onAdFailedToShowFullScreenContent: ${error.message}")
                        adsManager.hideLoading()
                        onAdFailedToShow(AdLoadError(error.code, error.message))
                        adsManager.isShowingAds = false
                        _statusFlow.value = AdsStatus.FAIL
                    }

                    override fun onAdImpression() {
                        super.onAdImpression()
                        adImpressionTime = System.currentTimeMillis()
                        onImpression()
                        _statusFlow.value = AdsStatus.IMPRESSED
                    }

                    override fun onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent()
                        adsManager.hideLoading()
                        onRewardShownShow()
                    }
                }

                when (rewardType) {
                    RewardType.Video -> {
                        val admobReward = adData?.rewardedAd
                        if (admobReward == null) onAdFailedToShow(
                            AdLoadError(
                                0,
                                "Reward ad is null"
                            )
                        )
                        else {
                            adsManager.isShowingAds = true
                            admobReward.fullScreenContentCallback = fullScreenContentCallback
                            admobReward.show(activity) {
                                adsEarned = true
                                Timber.tag(TAG).d("onUserEarnedReward: ${admobReward.adUnitId}")
                                onUserEarnedReward(it)
                            }
                        }
                    }

                    RewardType.Interstitial -> {
                        val admobReward = adData?.rewardedInterstitialAd
                        if (admobReward == null) onAdFailedToShow(
                            AdLoadError(
                                0,
                                "Reward ad is null"
                            )
                        )
                        else {
                            adsManager.isShowingAds = true
                            admobReward.fullScreenContentCallback = fullScreenContentCallback
                            admobReward.show(activity) {
                                adsEarned = true
                                Timber.tag(TAG).d("onUserEarnedReward: ${admobReward.adUnitId}")
                                onUserEarnedReward(it)
                            }
                        }
                    }
                }
            }
        }
    }

    fun loadAd(context: Context) {
        if (!enabled || !adsManager.canShowAds || !AppUtils.isNetworkConnected(context)) {
            Timber.tag(TAG).d("loadAd: fail disable/internet/consent/billing")
            _statusFlow.value = AdsStatus.FAIL
            return
        }

        if (shouldLoadAd()) {
            adsEarned = false
            _statusFlow.value = AdsStatus.LOADING
            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    val result = withTimeoutOrNull(timeout) {
                        var rewardAd: RewardAd? = null
                        for (adConfig in listAdData) {
                            rewardAd = fetchAd(context = context, adDataConfig = adConfig)
                            if (rewardAd != null) break
                        }
                        rewardAd
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
        } else {
            Timber.tag(TAG).d("loadAd:ad is available $status")
        }
    }

    private suspend fun fetchAd(
        context: Context,
        adDataConfig: AdDataConfig,
    ): RewardAd? {
        return suspendCancellableCoroutine { continuation ->
            try {
                if (!adDataConfig.enable) {
                    continuation.resume(null)
                } else {
                    Timber.tag(TAG).d("loading: ${displayText(adDataConfig)}")

                    val adRequest = AdRequest.Builder().build()
                    when (rewardType) {
                        RewardType.Video -> {
                            val start = System.currentTimeMillis()
                            RewardedAd.load(
                                context,
                                adDataConfig.id,
                                adRequest,
                                object : RewardedAdLoadCallback() {
                                    override fun onAdFailedToLoad(adError: LoadAdError) {
                                        super.onAdFailedToLoad(adError)
                                        Timber.tag(TAG)
                                            .e("onAdFailedToLoad: ${displayText(adDataConfig)} ${adError.message}")
                                        continuation.resume(null)
                                    }

                                    override fun onAdLoaded(ad: RewardedAd) {
                                        super.onAdLoaded(ad)
                                        val mediation =
                                            ad.responseInfo.mediationAdapterClassName?.split(".")?.lastOrNull()
                                        Timber.tag(TAG).d("onAdLoaded: ${displayText(adDataConfig)} - $mediation")

                                        val rewardAd = RewardAd(
                                            rewardedAd = ad,
                                            name = adDataConfig.name
                                        )
                                        if (adDataConfig.name.isNotEmpty()) {
                                            Tracking.logEvent("loaded_${adDataConfig.name}")
                                        }

                                        ad.onPaidEventListener =
                                            OnPaidEventListener { adValue: AdValue ->
                                                if (adDataConfig.name.isNotEmpty()) {
                                                    Tracking.logEvent("impression_${adDataConfig.name}")
                                                }
                                                if (adValue.valueMicros != 0L) {
                                                    Timber.tag(TAG).d(
                                                        TAG,
                                                        "OnPaidEvent interstitial:${adValue.valueMicros / 1000000} ${adValue.currencyCode}"
                                                    )
                                                }

                                                adjustManager.logAdRevenue(
                                                    context = context,
                                                    adValue = adValue,
                                                    adUnit = adDataConfig.id,
                                                    type = "rewarded",
                                                    mediation = ad.responseInfo.mediationAdapterClassName
                                                )
                                            }
                                        Tracking.logEvent(
                                            "time_${adDataConfig.name}", bundleOf(
                                                "time" to (System.currentTimeMillis() - start)
                                            )
                                        )
                                        continuation.resume(rewardAd)
                                    }
                                })
                        }

                        RewardType.Interstitial -> {
                            val start = System.currentTimeMillis()
                            RewardedInterstitialAd.load(
                                context,
                                adDataConfig.id,
                                adRequest,
                                object : RewardedInterstitialAdLoadCallback() {
                                    override fun onAdFailedToLoad(adError: LoadAdError) {
                                        super.onAdFailedToLoad(adError)
                                        Timber.tag(TAG)
                                            .e("onAdFailedToLoad: ${displayText(adDataConfig)} ${adError.message}")
                                        continuation.resume(null)
                                    }


                                    override fun onAdLoaded(ad: RewardedInterstitialAd) {
                                        super.onAdLoaded(ad)
                                        Timber.tag(TAG)
                                            .d("onAdLoaded: ${displayText(adDataConfig)}")

                                        if (adDataConfig.name.isNotEmpty()) {
                                            Tracking.logEvent("loaded_${adDataConfig.name}")
                                        }

                                        val rewardAd = RewardAd(
                                            rewardedInterstitialAd = ad,
                                            name = adDataConfig.name
                                        )
                                        ad.onPaidEventListener =
                                            OnPaidEventListener { adValue: AdValue ->
                                                if (adDataConfig.name.isNotEmpty()) {
                                                    Tracking.logEvent("impression_${adDataConfig.name}")
                                                }
                                                if (adValue.valueMicros != 0L) {
                                                    Timber.tag(TAG).d(
                                                        TAG,
                                                        "OnPaidEvent interstitial:${adValue.valueMicros / 1000000} ${adValue.currencyCode}"
                                                    )
                                                }
                                                adjustManager.logAdRevenue(
                                                    context = context,
                                                    adValue = adValue,
                                                    adUnit = adDataConfig.id,
                                                    type = "rewarded",
                                                    mediation = ad.responseInfo.mediationAdapterClassName
                                                )
                                            }
                                        Tracking.logEvent(
                                            "time_${adDataConfig.name}", bundleOf(
                                                "time" to (System.currentTimeMillis() - start)
                                            )
                                        )
                                        continuation.resume(rewardAd)
                                    }
                                })
                        }
                    }
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