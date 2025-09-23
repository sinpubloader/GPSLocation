package chin.pswm.gps.photo.location.map.earthview

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import chin.pswm.gps.photo.location.map.earthview.view.EarthViewContent
import chin.pswm.gps.photo.location.map.ui.theme.MapTypeBottomSheet
import chin.pswm.gps.photo.location.map.ui.theme.PermissionBottomSheet
import chin.pswm.gps.photo.location.map.ui.theme.PermissionManager.Companion.allowLocation
import chin.pswm.gps.photo.location.map.ui.theme.PermissionType
import chin.pswm.gps.photo.location.map.utils.contracts.AppDetailsSettings
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.flow.collectLatest


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

    val locationPermissionSettingState = rememberLauncherForActivityResult(AppDetailsSettings()) {
        if (context.allowLocation) {
            viewModel.permissionBottomSheetState.remove(PermissionType.Location)
            // todo: NEED_UPDATE update location
        }
    }


    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
        onPermissionsResult = { allow ->
            if (context.allowLocation) {
                viewModel.permissionBottomSheetState.remove(PermissionType.Location)
                // todo: NEED_UPDATE update location
            }
        }
    )

    LaunchedEffect(viewModel.permissionAction) {
        viewModel.permissionAction.collectLatest { permissionType ->
            when (permissionType) {
                PermissionType.Location -> {
                    if (locationPermissionState.permissions.any { !it.status.shouldShowRationale }) {
                        locationPermissionState.launchMultiplePermissionRequest()
                    } else {
                        locationPermissionSettingState.launch(null)
                    }
                }

                else -> Unit
            }
        }
    }

    PermissionBottomSheet(
        state = viewModel.permissionBottomSheetState
    )

    MapTypeBottomSheet(
        state = viewModel.mapTypeBottomSheetState
    )

    LaunchedEffect(Unit) {
        // todo: NEED_UPDATE update location
    }
}