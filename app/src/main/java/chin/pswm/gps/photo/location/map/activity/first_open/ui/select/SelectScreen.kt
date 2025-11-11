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
import chin.pswm.gps.photo.location.map.activity.first_open.common.NavigationUtil.safeNavigate
import chin.pswm.gps.photo.location.map.activity.first_open.cusom.SelectFeaturePopup
import chin.pswm.gps.photo.location.map.activity.first_open.data.SelectType
import chin.pswm.gps.photo.location.map.activity.first_open.nav.Dest
import chin.pswm.gps.photo.location.map.activity.first_open.ui.select.view.SelectContent
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.ext.Tracking
import chin.pswm.gps.photo.location.map.ads.ext.tryWithoutCatch
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs
import chin.pswm.gps.photo.location.map.utils.LocalScreenTAG

@Composable
fun SelectScreen(
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

    var showSelect by remember { mutableStateOf(false) }

    fun nextScreen() {
        navController.safeNavigate(Dest.Select, Dest.SelectAlt, Dest.Select)
    }

    SelectContent(
        isAlt = false,
        onContinue = {
            if (!showSelect) {
                showSelect = true
            }
        },
        onSelectAnyItem = {
            nextScreen()
        }
    )

    SelectFeaturePopup(
        isShow = showSelect,
        isSelectNormal = true,
        onGoIt = {
            showSelect = false
        },
        onSelectSome = {
            showSelect = false
            SelectType.selectedItems.addAll(SelectType.entries)
            nextScreen()
        }
    )

    LifecycleResumeEffect(Unit) {
        tryWithoutCatch {
            if (adsManager.nativeSelect.isClicked) {
                adsManager.nativeSelect.isClicked = false
                val type = prefs.getString("click_ad_select_type", "none")
                when (type) {
                    "next_screen" -> {
                        nextScreen()
                    }

                    "clear_ads" -> {
                        adsManager.nativeSelect.reset()
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

    LaunchedEffect(Unit) {
        adsManager.nativeSelectAlt.loadAd(context)
    }

    BackHandler {
        if (!showSelect) {
            Tracking.logEvent(TAG + "_back_pressed")
        }
        showSelect = true
    }
}