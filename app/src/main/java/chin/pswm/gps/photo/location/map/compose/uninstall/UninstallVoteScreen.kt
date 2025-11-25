package chin.pswm.gps.photo.location.map.compose.uninstall

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import chin.pswm.gps.photo.location.map.compose.uninstall.view.UninstallVoteContent
import chin.pswm.gps.photo.location.map.ads.ext.Tracking

@Composable
fun UninstallVoteScreen(navController: NavHostController) {
    val context = LocalContext.current

    UninstallVoteContent(
        onBack = {
            navController.popBackStack()
        },
        onUninstall = {
            Tracking.logEvent("uninstall_vote_submit")
            if (context is android.app.Activity) {
                context.finish()
            }
        }
    )
}