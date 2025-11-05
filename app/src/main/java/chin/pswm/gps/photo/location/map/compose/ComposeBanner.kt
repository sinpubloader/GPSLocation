package chin.pswm.gps.photo.location.map.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.compose.LifecycleResumeEffect
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.AppScreenState
import chin.pswm.gps.photo.location.map.ads.adunit.banner.BannerType
import chin.pswm.gps.photo.location.map.ads.adunit.banner.view.BannerView
import timber.log.Timber

fun setBannerContent(
    composeView: ComposeView,
    screen: String,
    adUnit: String,
    adUnitName: String,
    bannerType: BannerType,
) {
    composeView.setContent {
        ComposeBanner(adUnit, adUnitName, bannerType, screen)
    }
}

@Composable
private fun ComposeBanner(adUnit: String, adUnitName: String, bannerType: BannerType, screen: String) {
    LifecycleResumeEffect(screen, AppScreenState.lastScreen) {
        if (screen != AppScreenState.lastScreen) {
            AppScreenState.lastScreen = screen
            AppScreenState.screenCreated++
            Timber.tag(screen).d("TrackingScreen: $screen - screenCreated ${AppScreenState.screenCreated}")
        }

        onPauseOrDispose {

        }
    }
    LifecycleResumeEffect(Unit) {
        AdsManager.INSTANCE.loadInterInApp()
        onPauseOrDispose {

        }
    }
    BannerView(
        adUnit = adUnit,
        adUnitName = adUnitName,
        bannerType = bannerType
    )
}