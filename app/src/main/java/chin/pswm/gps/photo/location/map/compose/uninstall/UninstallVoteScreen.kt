package chin.pswm.gps.photo.location.map.compose.uninstall

import android.R.attr.data
import android.content.Intent
import android.net.Uri
import android.provider.Settings
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
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", context.packageName, null)
            }
            if (context is android.app.Activity) {
                context.startActivity(intent)
                context.finish()
            }

        }
    )
}
