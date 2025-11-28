package chin.pswm.gps.photo.location.map.activity.first_open.ui.onboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import chin.pswm.gps.photo.location.map.activity.first_open.common.CommonUtils
import chin.pswm.gps.photo.location.map.activity.first_open.common.NavigationUtil.safeNavigate
import chin.pswm.gps.photo.location.map.activity.first_open.nav.Dest
import chin.pswm.gps.photo.location.map.activity.first_open.ui.onboard.view.OnboardContent
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs

@Composable
fun OnboardScreen(
    navController: NavHostController
) {
    val context = LocalContext.current

    val prefs: Prefs = remember {
        Prefs.INSTANCE
    }
    val adsManager: AdsManager = remember {
        AdsManager.INSTANCE
    }

    OnboardContent(
        onFinish = {
//            prefs.onBoardOpen = false
            if (adsManager.nextOnBoard == Dest.Main) {
                prefs.firstOpen = false
                CommonUtils.openToMainScreen(context)
            } else {
                navController.safeNavigate(
                    currentRound = Dest.OnBoard,
                    destRoute = adsManager.nextOnBoard,
                    popUpTo = Dest.OnBoard
                )
            }
        }
    )

    LaunchedEffect(Unit) {
//        prefs.onBoardOpen = true
        when (adsManager.nextOnBoard) {
            Dest.Language -> {
                adsManager.nativeLanguage.loadAd(context)
            }

            Dest.Select -> {
                adsManager.nativeSelect.loadAd(context)
            }

            else -> Unit
        }
    }
}