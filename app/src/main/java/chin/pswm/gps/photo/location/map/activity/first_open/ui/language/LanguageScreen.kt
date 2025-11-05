package chin.pswm.gps.photo.location.map.activity.first_open.ui.language

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.navigation.NavHostController
import chin.pswm.gps.photo.location.map.activity.first_open.common.NavigationUtil.safeNavigate
import chin.pswm.gps.photo.location.map.activity.first_open.cusom.HelpSelectLanguageDialog
import chin.pswm.gps.photo.location.map.activity.first_open.data.LanguageType
import chin.pswm.gps.photo.location.map.activity.first_open.nav.Dest
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.ext.Tracking
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs
import chin.pswm.gps.photo.location.map.languegess.LanguageState
import chin.pswm.gps.photo.location.map.utils.LocalScreenTAG
import chin.pswm.gps.photo.location.map.activity.first_open.ui.language.view.LanguageContent
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LanguageScreen(
    navController: NavHostController
) {
    val TAG = LocalScreenTAG.current
    val context = LocalContext.current
    val adsManager: AdsManager = remember {
        AdsManager.INSTANCE
    }
    val prefs: Prefs = remember {
        Prefs.INSTANCE
    }

    var showLanguageHelp by remember { mutableStateOf(false) }

    fun onConfirm(code: String) {
        navController.safeNavigate(Dest.Language, Dest.LanguageAlt(code), Dest.Language)
    }

    LanguageContent(
        languageType = LanguageType.Normal,
        showPoint = adsManager.clickedLanguage,
        language = "",
        state = LanguageState.state,
        onBack = {
            // no need
        },
        onConfirm = {
            if (!showLanguageHelp) {
                Tracking.logEvent(TAG + "_confirm_pressed")
                showLanguageHelp = true
            }
        },
        onLanguageChange = { code ->
            onConfirm(code)
        }
    )

    val scope = rememberCoroutineScope()
    LifecycleResumeEffect(Unit) {
        val timeShowClick = prefs.getLong("time_show_tooltips_language", 4)
        scope.launch {
            while (true) {
                delay(1000)
                adsManager.countShowLanguage++

                if (adsManager.countShowLanguage >= timeShowClick) {
                    adsManager.clickedLanguage = true
                }
            }
        }
        onPauseOrDispose {
            scope.cancel()
        }
    }

    BackHandler {
        if (!showLanguageHelp) {
            Tracking.logEvent(TAG + "_back_pressed")
        }
        showLanguageHelp = true
    }

    LifecycleResumeEffect(Unit) {

        if (adsManager.clickedLanguageTooltip) {
            val action = prefs.getString("action_clicked_native_lang", "tooltip")
            when (action) {
                "tooltip" -> if (!showLanguageHelp) {
                    Tracking.logEvent(TAG + "_clicked_ad")
                    showLanguageHelp = true
                }

                "clear_ads" -> adsManager.clearAdsLanguage()
                "next_screen" -> {
                    onConfirm(LanguageState.codes.first())
                }

            }
            adsManager.clickedLanguageTooltip = false
        }

        scope.launch {
            delay(250)
            adsManager.nativeLanguageAlt.loadAd(context)
        }

        onPauseOrDispose {

        }
    }

    HelpSelectLanguageDialog(
        isShow = showLanguageHelp,
        onDismiss = {
            showLanguageHelp = false
        },
        onContinue = {
            showLanguageHelp = false
        }
    )
}