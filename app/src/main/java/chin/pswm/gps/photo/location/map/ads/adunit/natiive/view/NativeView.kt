package chin.pswm.gps.photo.location.map.ads.adunit.natiive.view

import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LifecycleResumeEffect
import chin.pswm.gps.photo.location.map.ads.adunit.common.AdsStatus
import chin.pswm.gps.photo.location.map.ads.adunit.natiive.NativeAdUnit
import chin.pswm.gps.photo.location.map.ads.adunit.natiive.populate.NativeAdUtil
import chin.pswm.gps.photo.location.map.ads.ext.requestLayoutWithDelay
import chin.pswm.gps.photo.location.map.ads.ext.tryWithoutCatch
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs
import chin.pswm.gps.photo.location.map_debug.R
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.compose.koinInject

@Composable
fun NativeView(
    modifier: Modifier,
    nativeAdUnit: NativeAdUnit,
    layoutConfig: Pair<String, Int>,
    layoutFaceBookConfig: Pair<String, Int>,
    delayShow: Long = 0L,
    requestInLayoutID: Int = 0,
    keepAdWaitLoading: Boolean = true,
    reloadAd: () -> Boolean = { true },
) {
    @Stable
    @Composable
    @LayoutRes
    fun layoutNative(name: String, @LayoutRes default: Int): Int {
        val preview = LocalInspectionMode.current
        if (preview) return default

        val prefs: Prefs = koinInject()
        val configLayout = remember {
            prefs.getString(name, "")
        }

        if (configLayout.isEmpty()) return default
        return when (configLayout) {
            // ctr big at bottom have media big
            "native_media_ctr_bot_big_filled" -> R.layout.native_media_ctr_bot_big_filled
            "native_media_ctr_bot_big_stroke" -> R.layout.native_media_ctr_bot_big_stroke

            // ctr big at top have media big
            "native_media_ctr_top_big_filled" -> R.layout.native_media_ctr_top_big_filled
            "native_media_ctr_top_big_stroke" -> R.layout.native_media_ctr_top_big_stroke

            // ctr big at bottom have media small 140dp
            "native_media_ctr_bot_small_filled" -> R.layout.native_media_ctr_bot_small_filled
            "native_media_ctr_bot_small_stroke" -> R.layout.native_media_ctr_bot_small_stroke

            // ctr big at top have media small 140dp
            "native_media_ctr_top_small_filled" -> R.layout.native_media_ctr_top_small_filled
            "native_media_ctr_top_small_stroke" -> R.layout.native_media_ctr_top_small_stroke

            // header + ctr same line at top, media lon
            "native_media_action_top_big_filled" -> R.layout.native_media_action_top_big_filled
            "native_media_action_top_big_stroke" -> R.layout.native_media_action_top_big_stroke

            // header + ctr same line at, media lon
            "native_media_action_bot_big_filled" -> R.layout.native_media_action_bot_big_filled
            "native_media_action_bot_big_stroke" -> R.layout.native_media_action_bot_big_stroke

            // header + ctr same line at top, media small 140dp
            "native_media_action_top_small_filled" -> R.layout.native_media_action_top_small_filled
            "native_media_action_top_small_stroke" -> R.layout.native_media_action_top_small_stroke

            // header + ctr same line at, media small 140dp
            "native_media_action_bot_small_filled" -> R.layout.native_media_action_bot_small_filled
            "native_media_action_bot_small_stroke" -> R.layout.native_media_action_bot_small_stroke

            // meta small left
            "native_media_left_filled" -> R.layout.native_media_left_filled
            "native_media_left_stroke" -> R.layout.native_media_left_stroke

            // not have media, ctr big at bottom
            "native_none_media_action_big_filled" -> R.layout.native_none_media_action_big_filled
            "native_none_media_action_big_stroke" -> R.layout.native_none_media_action_big_stroke

            // not have media, ctr small at right
            "native_none_media_action_small_right_filled" -> R.layout.native_none_media_action_small_right_filled
            "native_none_media_action_small_right_stroke" -> R.layout.native_none_media_action_small_right_stroke

            // not have media, ctr small at bottom
            "native_none_media_action_small_bottom_filled" -> R.layout.native_none_media_action_small_bottom_filled
            "native_none_media_action_small_bottom_stroke" -> R.layout.native_none_media_action_small_bottom_stroke

            // fsn
            "native_fsn_filled" -> R.layout.native_full_screen
            "native_fsn_stroke" -> R.layout.native_full_screen_meta
            else -> default
        }
    }

    val inspectionMode = LocalInspectionMode.current
    if (inspectionMode) return

    val layout = layoutNative(layoutConfig.first, layoutConfig.second)
    val layoutFaceBook = layoutNative(layoutFaceBookConfig.first, layoutFaceBookConfig.second)


    var nativeWrapper: NativeAd? by remember {
        mutableStateOf(nativeAdUnit.adData?.ad)
    }

    val statusAds by nativeAdUnit.statusFlow.collectAsState()
    LaunchedEffect(statusAds) {
        when (statusAds) {
            AdsStatus.NONE, AdsStatus.FAIL -> nativeWrapper = null

            AdsStatus.LOADING -> nativeWrapper = if (keepAdWaitLoading) {
                if (nativeWrapper != null) {
                    nativeWrapper
                } else {
                    nativeAdUnit.adData?.ad
                }
            } else {
                null
            }

            AdsStatus.SUCCESS, AdsStatus.IMPRESSED -> {
                if (delayShow > 0) {
                    delay(delayShow)
                }
                nativeWrapper = nativeAdUnit.adData?.ad
            }
        }
    }

    var fromOnStop = remember {
        reloadAd()
    }
    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    LifecycleResumeEffect(Unit) {
        scope.launch {
            if (fromOnStop && (reloadAd() || (reloadAd() && nativeAdUnit.isClicked))) {
                nativeAdUnit.loadAd(context)
                fromOnStop = false
            }
        }
        onPauseOrDispose {
            scope.cancel()
            if (reloadAd() || (reloadAd() && nativeAdUnit.isClicked)) {
                fromOnStop = true
            }
        }
    }

    if ((statusAds == AdsStatus.NONE || statusAds == AdsStatus.FAIL) && nativeWrapper == null) return

    var adView: View? = remember { null }

    AnimatedContent(
        targetState = nativeWrapper,
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
            AndroidView(
                factory = {
                    val isFB =
                        ad.responseInfo?.mediationAdapterClassName?.contains("facebook") == true

                    adView =
                        LayoutInflater.from(context)
                            .inflate(if (isFB) layoutFaceBook else layout, null, true)
                    NativeAdUtil.populateUnifiedNativeAdView(context, ad, adView as NativeAdView)

                    adView?.post { adView?.rootView?.requestLayout() }
                    adView?.requestLayoutWithDelay(400)

                    adView!!
                }, update = {
                    scope.launch(Dispatchers.Main) {
                        if (nativeAdUnit.status == AdsStatus.SUCCESS) {
                            delay(500)
                            tryWithoutCatch {
                                it.requestLayout()
                                it.rootView.requestLayout()
                            }
                        }
                    }
                    tryWithoutCatch {
                        if (nativeAdUnit.status == AdsStatus.SUCCESS) {
                            it.rootView.requestLayoutWithDelay(500)
                        }
                    }
                }, modifier = modifier
            )
        } else {
            AndroidView(
                factory = {
                    LayoutInflater.from(it).inflate(layout, null, false)
                },
                modifier = modifier
                    .shimmer()
                    .background(Color.Black.copy(0.04f))
                    .graphicsLayer {
                        alpha = 0.5f
                    }
            )
        }
    }

    LaunchedEffect(requestInLayoutID) {
        withContext(Dispatchers.Main) {
            if (nativeAdUnit.status == AdsStatus.SUCCESS) {
                tryWithoutCatch {
                    adView?.rootView?.requestLayoutWithDelay(500)
                }
                delay(500)
                tryWithoutCatch {
                    adView?.rootView?.requestLayout()
                }
            }
        }
    }

    LaunchedEffect(statusAds) {
        while (statusAds == AdsStatus.SUCCESS) {
            adView?.rootView?.requestLayoutWithDelay(400)
            delay(400)
        }
    }
}