package chin.pswm.gps.photo.location.map.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import chin.pswm.gps.photo.location.map.ads.adunit.natiive.NativeAdUnit
import chin.pswm.gps.photo.location.map.ads.adunit.natiive.view.NativeView

fun setNativeContent(
    composeView: ComposeView,
    nativeAdUnit: NativeAdUnit,
    layoutConfig: Pair<String, Int>,
    layoutFaceBookConfig: Pair<String, Int>,
) {
    composeView.setContent {
        ComposeNative(nativeAdUnit, layoutConfig, layoutFaceBookConfig)
    }
}

@Composable
private fun ComposeNative(
    nativeAdUnit: NativeAdUnit,
    layoutConfig: Pair<String, Int>,
    layoutFaceBookConfig: Pair<String, Int>,
) {
    NativeView(
        modifier = Modifier
            .fillMaxWidth(),
        nativeAdUnit = nativeAdUnit,
        layoutConfig = layoutConfig,
        layoutFaceBookConfig = layoutFaceBookConfig,
    )
}