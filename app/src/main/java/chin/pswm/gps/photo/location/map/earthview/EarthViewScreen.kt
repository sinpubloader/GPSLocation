@file:OptIn(ExperimentalPermissionsApi::class)

package chin.pswm.gps.photo.location.map.earthview

import com.ai.panda.domain.models.location.MarkLocation
import com.ai.panda.ui.theme.brushMain
import com.google.maps.android.compose.rememberUpdatedMarkerState
import com.ai.panda.ads.adunit.banner.view.BannerView
import com.ai.panda.common.LocalScreenTAG
import com.ai.panda.common.TrackingScreen
import com.ai.panda.ads.AdsManager
import com.ai.panda.common.constract.AppDetailsSettings
import com.ai.panda.domain.repository.AppRepository
import com.ai.panda.ui.nav.NavigationManager
import com.ai.panda.ui.base.BaseViewModel
import com.ai.panda.ui.nav.AppDestination
import com.ai.panda.domain.models.location.MarkLocation


import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
//import com.ai.panda.ads.AdsManager
//import com.ai.panda.common.LocalScreenTAG
//import com.ai.panda.common.Tracking
//import com.ai.panda.common.TrackingScreen
//import com.ai.panda.common.allowLocation
//import com.ai.panda.common.constract.AppDetailsSettings
//import com.ai.panda.domain.models.enumClass.PermissionType
//import com.ai.panda.ui.base.bottom_sheet.MapTypeBottomSheet
//import com.ai.panda.ui.base.bottom_sheet.PermissionBottomSheet
import chin.pswm.gps.photo.location.map.earthview.view.EarthViewContent
import chin.pswm.gps.photo.location.map.ui.theme.MapTypeBottomSheet
import chin.pswm.gps.photo.location.map.ui.theme.PermissionBottomSheet
import chin.pswm.gps.photo.location.map.ui.theme.PermissionManager.Companion.allowLocation
//import com.google.accompanist.permissions.ExperimentalPermissionsApi
//import com.google.accompanist.permissions.rememberMultiplePermissionsState
//import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.koinInject

@Composable
fun EarthViewScreen(
    viewModel: EarthViewViewModel
) {
    val TAG = LocalScreenTAG.current
    val context = LocalContext.current
    TrackingScreen(screen = TAG)

    val adsManager: AdsManager = koinInject()

    LaunchedEffect(Unit) {
        adsManager.loadInterInApp()
    }

    EarthViewContent(
        state = viewModel.screenState,
        searchState = viewModel.searchState,
    )

    val locationPermissionSettingState = rememberLauncherForActivityResult(AppDetailsSettings()) {
        if (context.allowLocation) {
            Tracking.logEvent(TAG + "_allow_location")
            viewModel.permissionBottomSheetState.removePermission(PermissionType.Location)
            viewModel.appRepository.updateLocationRealtime()
        }
    }

    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
        onPermissionsResult = { allow ->
            if (context.allowLocation) {
                Tracking.logEvent(TAG + "_allow_location")
                viewModel.permissionBottomSheetState.removePermission(PermissionType.Location)
                viewModel.appRepository.updateLocationRealtime()
            }
        }
    )

    LaunchedEffect(viewModel.permissionAction) {
        viewModel.permissionAction.collectLatest { permissionType ->
            when (permissionType) {
                PermissionType.Location -> {
                    Tracking.logEvent(TAG + "_request_location")
                    adsManager.disableAdResumeOneTime = true
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
        viewModel.appRepository.updateLocationRealtime()
    }
}