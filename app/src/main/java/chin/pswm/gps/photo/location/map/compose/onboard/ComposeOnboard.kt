package chin.pswm.gps.photo.location.map.compose.onboard

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.adunit.natiive.view.NativeView
import chin.pswm.gps.photo.location.map_debug.R

fun setMyContent(composeView: ComposeView, index: Int) {
    composeView.setContent {
        ComposeOnboard(index)
    }
}

@Composable
fun ComposeOnboard(index: Int) {
    val nativeAdUnit = remember {
        if (index == 0) {
            AdsManager.INSTANCE.nativeOnboard1
        } else {
            AdsManager.INSTANCE.nativeOnboard3
        }
    }

    NativeView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .padding(bottom = 12.dp),
        nativeAdUnit = nativeAdUnit,
        layoutConfig = "layout_onboard" to R.layout.native_media_ctr_bot_big_filled,
        layoutFaceBookConfig = "layout_onboard_meta" to R.layout.native_media_ctr_bot_big_filled
    )
}