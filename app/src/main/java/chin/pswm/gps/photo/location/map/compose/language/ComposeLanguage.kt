package chin.pswm.gps.photo.location.map.compose.language

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import chin.pswm.gps.photo.location.map.MyApplication
import chin.pswm.gps.photo.location.map.activity.first_open.ui.language.LanguageScreen
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.adunit.natiive.view.NativeView
import chin.pswm.gps.photo.location.map_debug.R

object ComposeLanguageState {
    var clickedLanguage by mutableStateOf(false)
}

fun setMyContent(composeView: ComposeView) {
    composeView.setContent {
        LanguageScreen(rememberNavController(), false)
//        ComposeLanguage(composeView)
    }
}

@Composable
fun ComposeLanguage(composeView: ComposeView) {
    val context = LocalContext.current
//    LaunchedEffect(Unit) {
//        AdsManager.INSTANCE.nativeLanguageAlt.loadAd(context)
//    }

    LaunchedEffect(ComposeLanguageState.clickedLanguage) {
        AdsManager.INSTANCE.run {
            nativeSelect.loadAd(context)
//            nativeOnboard1.loadAd(context)
//            nativeFSN.loadAd(context)
//            nativeOnboard3.loadAd(context)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
//        if (ComposeLanguageState.clickedLanguage) {
//            NativeView(
//                modifier = Modifier.fillMaxWidth(),
//                nativeAdUnit = AdsManager.INSTANCE.nativeLanguageAlt,
//                layoutConfig = "layout_language_alt" to R.layout.native_media_ctr_bot_big_filled,
//                layoutFaceBookConfig = "layout_language_meta_alt" to R.layout.native_media_ctr_bot_big_filled,
//            )
//            MyApplication.sendEvent("Language_Screen", "language_alt")
//        } else {
            NativeView(
                modifier = Modifier.fillMaxWidth(),
                nativeAdUnit = AdsManager.INSTANCE.nativeLanguage,
                layoutConfig = "layout_language" to R.layout.native_media_left_filled,
                layoutFaceBookConfig = "layout_language_meta" to R.layout.native_media_left_filled,
            )
            MyApplication.sendEvent("Language_Screen", "language")
//        }

    }
}