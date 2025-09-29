package chin.pswm.gps.photo.location.map.compose.privacy

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LifecycleResumeEffect
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.adunit.common.AdsStatus
import chin.pswm.gps.photo.location.map.compose.dialog.LoadingDialog
import chin.pswm.gps.photo.location.map.compose.utils.black
import chin.pswm.gps.photo.location.map.compose.utils.visible
import chin.pswm.gps.photo.location.map.languegess.ActivityPrivacyPolicy_New
import chin.pswm.gps.photo.location.map.languegess.New_first_languagesselect
import kotlinx.coroutines.flow.MutableStateFlow

object ComposePrivacyState {
    val clickedAgree = MutableStateFlow(false)
}

fun setMyContent(composeView: ComposeView) {
    composeView.setContent {
        ComposePrivacy(composeView)
    }
}

@Composable
fun ComposePrivacy(composeView: ComposeView) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        AdsManager.INSTANCE.interSplash.loadAd(context)
    }

    val adsStatus = AdsManager.INSTANCE.interSplash.statusFlow.collectAsState().value
    val clickedAgree = ComposePrivacyState.clickedAgree.collectAsState().value
    LifecycleResumeEffect(clickedAgree, adsStatus) {
        if (clickedAgree) {
            when (adsStatus) {
                AdsStatus.SUCCESS -> {
                    composeView.black()
                    AdsManager.INSTANCE.interSplash.show(
                        activity = context as ActivityPrivacyPolicy_New,
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
                    (context as? ActivityPrivacyPolicy_New)?.finish()
                }

                else -> Unit
            }
        }
        onPauseOrDispose {

        }
    }

    val isLoading by remember(adsStatus, clickedAgree) {
        derivedStateOf {
            adsStatus == AdsStatus.LOADING && clickedAgree
        }
    }
    LoadingDialog(isLoading)
}