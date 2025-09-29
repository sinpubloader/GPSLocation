package chin.pswm.gps.photo.location.map.earthview

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import chin.pswm.gps.photo.location.map.earthview.view.EarthViewContent
import com.google.accompanist.permissions.ExperimentalPermissionsApi


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun EarthViewScreen(
    viewModel: EarthViewViewModel
) {
    val context = LocalContext.current


    EarthViewContent(
        state = viewModel.screenState,
        searchState = viewModel.searchState,
    )
}