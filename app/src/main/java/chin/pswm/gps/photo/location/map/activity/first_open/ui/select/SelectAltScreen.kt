package chin.pswm.gps.photo.location.map.activity.first_open.ui.select

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.navigation.NavHostController
import chin.pswm.gps.photo.location.map.activity.first_open.common.CommonUtils
import chin.pswm.gps.photo.location.map.activity.first_open.common.NavigationUtil.safeNavigate
import chin.pswm.gps.photo.location.map.activity.first_open.cusom.SelectFeaturePopup
import chin.pswm.gps.photo.location.map.activity.first_open.nav.Dest
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.ext.Tracking
import chin.pswm.gps.photo.location.map.ads.ext.tryWithoutCatch
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs
import chin.pswm.gps.photo.location.map.utils.LocalScreenTAG
import chin.pswm.gps.photo.location.map.activity.first_open.ui.select.view.SelectContent

@Composable
fun SelectAltScreen(
    navController: NavHostController
) {
    val TAG = LocalScreenTAG.current
    val context = LocalContext.current
    val adsManager: AdsManager = remember {
        AdsManager.INSTANCE
    }
    val prefs: Prefs = remember {
        Prefs.INSTANCE
    }

    fun onNext() {
        if (adsManager.nextSelect == Dest.Main) {
            prefs.firstOpen = false
            CommonUtils.openToMainScreen(context)
        } else {
            navController.safeNavigate(
                currentRound = Dest.SelectAlt,
                destRoute = adsManager.nextSelect,
                popUpTo = Dest.SelectAlt
            )
        }
    }

    SelectContent(
        isAlt = true,
        onContinue = {
            onNext()
        }
    )

    LaunchedEffect(Unit) {
        when (adsManager.nextSelect) {
            Dest.Language -> {
                adsManager.nativeLanguage.loadAd(context)
            }

            Dest.OnBoard -> {
                adsManager.nativeOnboard1.loadAd(context)
                adsManager.nativeFSN.loadAd(context)
            }

            Dest.Main -> {
                adsManager.nativeHome.loadAd(context)
            }

            else -> Unit
        }
    }

    var showSelect by remember {
        mutableStateOf(false)
    }

    LifecycleResumeEffect(Unit) {
        tryWithoutCatch {
            if (adsManager.nativeSelectAlt.isClicked) {
                adsManager.nativeSelectAlt.isClicked = false
                val type = prefs.getString("click_ad_select_alt_type", "next_screen")
                when (type) {
                    "next_screen" -> {
                        onNext()
                    }

                    "clear_ads" -> {
                        adsManager.nativeSelectAlt.reset()
                        if (!showSelect) {
                            Tracking.logEvent(TAG + "_clicked_ad")
                        }
                        showSelect = true
                    }

                    else -> {
                        if (!showSelect) {
                            Tracking.logEvent(TAG + "_clicked_ad")
                        }
                        showSelect = true
                    }
                }
            }
        }

        onPauseOrDispose {

        }
    }

    BackHandler {
        if (!showSelect) {
            Tracking.logEvent(TAG + "_back_pressed")
        }
        showSelect = true
    }

    SelectFeaturePopup(
        isShow = showSelect,
        isSelectNormal = false,
        onGoIt = {
            showSelect = false
        }
    )
}