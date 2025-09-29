package chin.pswm.gps.photo.location.map.compose.splash

import android.content.Intent
import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LifecycleResumeEffect
import chin.pswm.gps.photo.location.map.activity.SplashActivity
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.adunit.common.AdsStatus
import chin.pswm.gps.photo.location.map.languegess.New_first_languagesselect
import kotlinx.coroutines.flow.MutableStateFlow

object ComposeSplashState {
    val clickedAgree = MutableStateFlow(false)
}

fun setMyContent(composeView: ComposeView) {
    composeView.setContent {
        ComposeSplash(composeView)
    }
}

@Composable
fun ComposeSplash(composeView: ComposeView) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        AdsManager.INSTANCE.interSplash.loadAd(context)
    }

    val adsStatus = AdsManager.INSTANCE.interSplash.statusFlow.collectAsState().value
    val clickedAgree = ComposeSplashState.clickedAgree.collectAsState().value
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
}