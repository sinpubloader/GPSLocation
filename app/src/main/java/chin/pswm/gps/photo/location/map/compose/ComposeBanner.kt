package chin.pswm.gps.photo.location.map.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import chin.pswm.gps.photo.location.map.ads.adunit.banner.BannerType
import chin.pswm.gps.photo.location.map.ads.adunit.banner.view.BannerView

fun setBannerContent(
    composeView: ComposeView,
    adUnit: String,
    adUnitName: String,
    bannerType: BannerType,
) {
    composeView.setContent {
        ComposeBanner(adUnit, adUnitName, bannerType)
    }
}

@Composable
private fun ComposeBanner(adUnit: String, adUnitName: String, bannerType: BannerType) {

    BannerView(
        adUnit = adUnit,
        adUnitName = adUnitName,
        bannerType = bannerType
    )
}