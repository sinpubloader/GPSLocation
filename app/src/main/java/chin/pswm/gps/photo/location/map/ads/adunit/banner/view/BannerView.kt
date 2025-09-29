package chin.pswm.gps.photo.location.map.ads.adunit.banner.view

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.lifecycle.compose.LifecycleStartEffect
import chin.pswm.gps.photo.location.map.ads.adunit.banner.BannerAdUnit
import chin.pswm.gps.photo.location.map.ads.adunit.banner.BannerType
import chin.pswm.gps.photo.location.map.ads.adunit.common.AdsStatus
import chin.pswm.gps.photo.location.map.ads.ext.AppUtils
import chin.pswm.gps.photo.location.map.ads.ext.requestLayoutWithDelay
import chin.pswm.gps.photo.location.map.ads.ext.tryWithoutCatch
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs
import chin.pswm.gps.photo.location.map_debug.databinding.ShimmerBanner120Binding
import chin.pswm.gps.photo.location.map_debug.databinding.ShimmerBannerBinding
import com.google.android.gms.ads.AdView
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import timber.log.Timber

@Immutable
data class AdViewWrapper(
    val adView: AdView,
)

private const val TAG = "BannerView"

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun BannerView(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xFFF9F9F9)),
    adUnit: String, // name to find
    adUnitName: String,
    position: String = "",
    bannerType: BannerType = BannerType.BANNER_ADAPTIVE,
    allowLoadAd: Boolean = true,
    keepAdWaitLoading: Boolean = true,
) {
    val inspectionMode = LocalInspectionMode.current
    if (inspectionMode) return
    val prefs: Prefs = koinInject()

    val bannerTypeUse = remember {
        val positionConfig = prefs.getString(position, "")
        when (positionConfig) {
            "BANNER_ADAPTIVE" -> BannerType.BANNER_ADAPTIVE
            "BANNER_INLINE_ADAPTIVE" -> BannerType.BANNER_INLINE_ADAPTIVE
            "BANNER_INLINE_ADAPTIVE_50" -> BannerType.BANNER_INLINE_ADAPTIVE_50
            "BANNER_INLINE_ADAPTIVE_90" -> BannerType.BANNER_INLINE_ADAPTIVE_90
            "BANNER_INLINE_ADAPTIVE_120" -> BannerType.BANNER_INLINE_ADAPTIVE_120
            "BANNER_INLINE_LANDSCAPE_ADAPTIVE" -> BannerType.BANNER_INLINE_LANDSCAPE_ADAPTIVE
            "BANNER_COLLAPSIBLE" -> BannerType.BANNER_COLLAPSIBLE
            else -> BannerType.BANNER_ADAPTIVE
        }
        bannerType
    }

    BoxWithConstraints(modifier) {
        val context = LocalContext.current
        val activity = remember { context as Activity }
        val banner = remember {
            BannerAdUnit.getBanner(
                adUnitName = adUnitName,
                adUnit = adUnit,
                bannerType = bannerTypeUse
            ).also {
                it.adWidth = maxWidth.value.toInt()
            }
        }
        var adViewData: AdViewWrapper? by remember {
            mutableStateOf(null)
        }
        val statusAds by banner.statusFlow.collectAsState()

        LaunchedEffect(statusAds) {
            when (statusAds) {
                AdsStatus.NONE -> adViewData = null
                AdsStatus.LOADING -> if (!keepAdWaitLoading) {
                    adViewData = null
                }

                AdsStatus.FAIL -> {
                    if (!AppUtils.isNetworkConnected(context)) {
                        adViewData = null
                    }
                }

                AdsStatus.SUCCESS, AdsStatus.IMPRESSED -> {
                    if (adViewData != null) {
                        val oldResponseId = adViewData?.adView?.responseInfo?.responseId
                        val newResponseId = banner.adData?.ad?.responseInfo?.responseId
                        if (oldResponseId != newResponseId && banner.adData != null) {
                            adViewData = AdViewWrapper(banner.adData!!.ad)
                        }
                    } else if (banner.adData != null) {
                        adViewData = AdViewWrapper(banner.adData!!.ad)
                    }
                }
            }
        }

        var fromOnStop by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(statusAds) {
            if (statusAds == AdsStatus.FAIL || statusAds == AdsStatus.IMPRESSED) {
                val reloadTime = prefs.getLong("banner_reload_time", 0)
                if (reloadTime > 0L) {
                    delay(reloadTime)
                    Timber.tag(TAG).d("BannerView: fast reload banner with ${reloadTime}s")
                    banner.loadAd(activity)
                }
            }
        }

        val scopeAds = rememberCoroutineScope()
        LifecycleStartEffect(Unit) {
            scopeAds.launch {
                if (fromOnStop) {
                    fromOnStop = false
                    delay(150)
                }

                Timber.tag(TAG).d("BannerView: ON_RESUME")
                if (allowLoadAd) {
                    val minReloadTime = prefs.getLong("banner_min_time_reload", 1)
                    val googleOptimize = prefs.getBoolean("banner_google_optimize", false)

                    if (banner.adImpressionTime == 0L) {
                        Timber.tag(TAG).d("BannerView: load by init")
                        banner.loadAd(activity)
                    } else if (!googleOptimize && minReloadTime > 0) {
                        if (System.currentTimeMillis() - banner.adImpressionTime >= minReloadTime) {
                            Timber.tag(TAG).d("BannerView: reload by min time")
                            banner.loadAd(activity)
                        }
                    }
                }
            }
            onStopOrDispose {
                scopeAds.cancel()
                fromOnStop = true
            }
        }

        if (statusAds == AdsStatus.NONE || statusAds == AdsStatus.FAIL && adViewData == null) return@BoxWithConstraints

        AnimatedContent(
            targetState = adViewData,
            transitionSpec = {
                fadeIn(animationSpec = tween(0)) togetherWith fadeOut(
                    animationSpec = tween(
                        0
                    )
                )
            },
            label = "",
        ) { ad ->
            if (ad != null) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.6.dp)
                            .background(MaterialTheme.colorScheme.primary.copy(0.4f))
                    )

                    AndroidView(
                        factory = { context ->
                            try {
                                tryWithoutCatch { (ad.adView.parent as? ViewGroup)?.removeView(ad.adView) }
                                tryWithoutCatch { (ad.adView.parent as? ViewGroup)?.removeAllViews() }
                                tryWithoutCatch { ad.adView.requestLayoutWithDelay(400) }
                                ad.adView

                            } catch (ex: Exception) {
                                View(context)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )

                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.6.dp)
                            .background(MaterialTheme.colorScheme.primary.copy(0.4f))
                    )
                }
            } else {
                if (bannerTypeUse == BannerType.BANNER_INLINE_ADAPTIVE_120) {
                    AndroidViewBinding(
                        factory = ShimmerBanner120Binding::inflate,
                        modifier = modifier
                    )
                } else {
                    AndroidViewBinding(
                        factory = ShimmerBannerBinding::inflate,
                        modifier = modifier
                    )
                }
            }
        }
    }
}