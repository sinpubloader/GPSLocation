package chin.pswm.gps.photo.location.map.compose.uninstall

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import chin.pswm.gps.photo.location.map.activity.first_open.common.CommonUtils
import chin.pswm.gps.photo.location.map.activity.first_open.common.Constants
import chin.pswm.gps.photo.location.map.activity.first_open.common.NavigationUtil.safeNavigate
import chin.pswm.gps.photo.location.map.activity.first_open.nav.Dest
import chin.pswm.gps.photo.location.map.compose.uninstall.view.UninstallExploreFeatureContent
import chin.pswm.gps.photo.location.map.ads.ext.Tracking

@Composable
fun UninstallExploreFeatureScreen(navController: NavHostController) {
    val context = LocalContext.current

    UninstallExploreFeatureContent(
        onHome = {
            Tracking.logEvent("uninstall_explore_go_home")
            CommonUtils.openToMainScreen(context)
        },
        onGpsCamera = {
            Tracking.logEvent("uninstall_explore_go_gpscamera")
            CommonUtils.openToMainScreenFromUninstall(context, Constants.OPEN_FROM_UNINSTALL_GPS_SHORTCUT)
        },
        onVote = {
            Tracking.logEvent("uninstall_explore_vote")
            navController.safeNavigate(
                currentRound = Dest.UninstallExploreFeature,
                destRoute = Dest.UninstallVote
            )
        },
        onMapView = {
            Tracking.logEvent("uninstall_explore_go_mapview")
            CommonUtils.openToMainScreenFromUninstall(context, Constants.OPEN_FROM_UNINSTALL_MAP_SHORTCUT)
//            CommonUtils.openToMapView(context)
        },
        onPhotoGrid = {
            Tracking.logEvent("uninstall_explore_go_photogrid")
            CommonUtils.openToMainScreenFromUninstall(context, Constants.OPEN_FROM_UNINSTALL_GRID_SHORTCUT)
//            CommonUtils.openToGridCamera(context)
        }
    )
}