package chin.pswm.gps.photo.location.map.compose.splash

import android.content.Intent
import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.asFlow
import androidx.lifecycle.compose.LifecycleResumeEffect
import chin.pswm.gps.photo.location.map.New_intro.New_IntroActivity
import chin.pswm.gps.photo.location.map.activity.SplashActivity
import chin.pswm.gps.photo.location.map.activity.StartActivity
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.adunit.banner.view.BannerView
import chin.pswm.gps.photo.location.map.ads.adunit.common.AdsStatus
import chin.pswm.gps.photo.location.map.languegess.ActivityPrivacyPolicy_New
import chin.pswm.gps.photo.location.map.languegess.New_first_languagesselect
import chin.pswm.gps.photo.location.map_debug.BuildConfig
import kotlinx.coroutines.flow.MutableStateFlow

object ComposeSplashState {
    val clickedAgree = MutableStateFlow(false)
    val clickedOnboard = MutableStateFlow(false)
    val clickedMain = MutableStateFlow(false)
    val clickedPrivacy = MutableStateFlow(false)
}

fun setMyContent(composeView: ComposeView, composeViewBanner: ComposeView) {
    composeView.setContent {
        ComposeSplash(composeView)
    }
    composeViewBanner.setContent {
        BannerView(
            adUnit = BuildConfig.banner_splash,
            adUnitName = BuildConfig.banner_splash
        )
    }
}

@Composable
fun ComposeSplash(composeView: ComposeView) {
    val context = LocalContext.current


    val initFinished = AdsManager.INSTANCE.consentFinished.asFlow().collectAsState(false).value
    val adsStatus = AdsManager.INSTANCE.interSplash.statusFlow.collectAsState().value
    val clickedAgree = ComposeSplashState.clickedAgree.collectAsState().value

    LaunchedEffect(initFinished) {
        if (initFinished) AdsManager.INSTANCE.interSplash.loadAd(context)
    }

    LifecycleResumeEffect(clickedAgree, adsStatus) {
        if (clickedAgree) {
            when (adsStatus) {
                AdsStatus.SUCCESS -> {
                    composeView.setBackgroundColor(Color.BLACK)
                    AdsManager.INSTANCE.interSplash.show(
                        activity = context as SplashActivity,
                        onAdClosed = {
                            context.startActivity(Intent(context, New_first_languagesselect::class.java))
                            context.finish()
                        },
                        onAdFailedToShow = {
                            context.startActivity(Intent(context, New_first_languagesselect::class.java))
                            context.finish()
                        }
                    )
                }

                AdsStatus.FAIL -> {
                    context.startActivity(Intent(context, New_first_languagesselect::class.java))
                    (context as? SplashActivity)?.finish()
                }

                else -> Unit
            }
        }
        onPauseOrDispose {

        }
    }

    val clickedOnboard = ComposeSplashState.clickedOnboard.collectAsState().value
    LifecycleResumeEffect(clickedOnboard, adsStatus) {
        if (clickedOnboard) {
            when (adsStatus) {
                AdsStatus.SUCCESS -> {
                    composeView.setBackgroundColor(Color.BLACK)
                    AdsManager.INSTANCE.interSplash.show(
                        activity = context as SplashActivity,
                        onAdClosed = {
                            context.startActivity(Intent(context, New_IntroActivity::class.java))
                            context.finish()
                        },
                        onAdFailedToShow = {
                            context.startActivity(Intent(context, New_IntroActivity::class.java))
                            context.finish()
                        }
                    )
                }

                AdsStatus.FAIL -> {
                    context.startActivity(Intent(context, New_IntroActivity::class.java))
                    (context as? SplashActivity)?.finish()
                }

                else -> Unit
            }
        }
        onPauseOrDispose {

        }
    }

    val clickedSplash = ComposeSplashState.clickedMain.collectAsState().value
    LifecycleResumeEffect(clickedSplash, adsStatus) {
        if (clickedSplash) {
            when (adsStatus) {
                AdsStatus.SUCCESS -> {
                    composeView.setBackgroundColor(Color.BLACK)
                    AdsManager.INSTANCE.interSplash.show(
                        activity = context as SplashActivity,
                        onAdClosed = {
                            context.startActivity(Intent(context, StartActivity::class.java))
                            context.finish()
                        },
                        onAdFailedToShow = {
                            context.startActivity(Intent(context, StartActivity::class.java))
                            context.finish()
                        }
                    )
                }

                AdsStatus.FAIL -> {
                    context.startActivity(Intent(context, StartActivity::class.java))
                    (context as? SplashActivity)?.finish()
                }

                else -> Unit
            }
        }
        onPauseOrDispose {

        }
    }

    val clickedPrivacy = ComposeSplashState.clickedPrivacy.collectAsState().value
    LifecycleResumeEffect(clickedPrivacy, adsStatus) {
        if (clickedPrivacy) {
            when (adsStatus) {
                AdsStatus.SUCCESS -> {
                    composeView.setBackgroundColor(Color.BLACK)
                    AdsManager.INSTANCE.interSplash.show(
                        activity = context as SplashActivity,
                        onAdClosed = {
                            context.startActivity(Intent(context, ActivityPrivacyPolicy_New::class.java))
                            context.finish()
                        },
                        onAdFailedToShow = {
                            context.startActivity(Intent(context, ActivityPrivacyPolicy_New::class.java))
                            context.finish()
                        }
                    )
                }

                AdsStatus.FAIL -> {
                    context.startActivity(Intent(context, ActivityPrivacyPolicy_New::class.java))
                    (context as? SplashActivity)?.finish()
                }

                else -> Unit
            }
        }
        onPauseOrDispose {

        }
    }
}