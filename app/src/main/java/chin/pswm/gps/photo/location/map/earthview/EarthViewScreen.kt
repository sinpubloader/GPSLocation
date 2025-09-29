package chin.pswm.gps.photo.location.map.earthview

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.earthview.view.EarthViewContent
import com.google.accompanist.permissions.ExperimentalPermissionsApi


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun EarthViewScreen(
    viewModel: EarthViewViewModel
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        AdsManager.INSTANCE.loadInterInApp()
    }

    EarthViewContent(
        state = viewModel.screenState,
        searchState = viewModel.searchState,
        onBack = {
            AdsManager.INSTANCE.showInterInApp(
                activity = context as Activity,
                onNextAction = {
                    (context as? EarthViewActivity)?.finish()
                }
            )
        }
    )
}