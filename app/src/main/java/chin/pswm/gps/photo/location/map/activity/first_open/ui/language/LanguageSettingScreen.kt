package chin.pswm.gps.photo.location.map.activity.first_open.ui.language

import android.app.Activity
import android.os.Build
import android.view.WindowInsets
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import chin.pswm.gps.photo.location.map.activity.first_open.common.CommonUtils
import chin.pswm.gps.photo.location.map.activity.first_open.data.LanguageType
import chin.pswm.gps.photo.location.map.activity.first_open.ui.language.view.LanguageContent
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.languegess.LanguageManager
import chin.pswm.gps.photo.location.map.languegess.LanguageState
import chin.pswm.gps.photo.location.map.languegess.SharedHelper

@Composable
fun LanguageSettingScreen() {
    val context = LocalContext.current
    val adsManager: AdsManager = remember {
        AdsManager.INSTANCE
    }

    var language by remember { mutableStateOf(SharedHelper.getString(context, "lang_key", "")) }


    LanguageContent(
        languageType = LanguageType.Setting,
        showPoint = adsManager.clickedLanguage,
        language = language,
        state = LanguageState.state,
        onBack = {
            (context as? Activity)?.finish()
        },
        onConfirm = {
            SharedHelper.putString(context, "lang_key", language)
            LanguageManager.setLocale(context, language)
            CommonUtils.openToMainScreen(context)
        },
        onLanguageChange = { code ->
            language = code
        }
    )

    LaunchedEffect(Unit) {
        val activity = context as? Activity
        activity?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                activity.window.insetsController?.show(WindowInsets.Type.statusBars())
            } else {
                activity.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }
    }
}