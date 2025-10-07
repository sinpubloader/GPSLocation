package chin.pswm.gps.photo.location.map.compose.select

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.adunit.natiive.view.NativeView
import chin.pswm.gps.photo.location.map.compose.language.ComposeLanguageState
import chin.pswm.gps.photo.location.map_debug.R

object ComposeSelectState {
    var isSelected by mutableStateOf(false)
}

fun setMyContent(composeView: ComposeView) {
    composeView.setContent {
        ComposeSelect()
    }
}

@Composable
fun ComposeSelect() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        if (ComposeSelectState.isSelected) {
            NativeView(
                modifier = Modifier.fillMaxWidth(),
                nativeAdUnit = AdsManager.INSTANCE.nativeSelectAlt,
                layoutConfig = "layout_select_alt" to R.layout.native_media_ctr_bot_big_filled,
                layoutFaceBookConfig = "layout_select_alt_meta" to R.layout.native_media_ctr_bot_big_filled,
            )
        } else {
            NativeView(
                modifier = Modifier.fillMaxWidth(),
                nativeAdUnit = AdsManager.INSTANCE.nativeSelect,
                layoutConfig = "layout_select" to R.layout.native_media_ctr_bot_big_filled,
                layoutFaceBookConfig = "layout_select_meta" to R.layout.native_media_ctr_bot_big_filled,
            )
        }

    }
}