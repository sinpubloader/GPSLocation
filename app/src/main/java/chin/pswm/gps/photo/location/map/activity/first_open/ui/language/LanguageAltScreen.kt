package chin.pswm.gps.photo.location.map.activity.first_open.ui.language

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.navigation.NavHostController
import chin.pswm.gps.photo.location.map.activity.first_open.common.CommonUtils
import chin.pswm.gps.photo.location.map.activity.first_open.common.NavigationUtil.safeNavigate
import chin.pswm.gps.photo.location.map.activity.first_open.cusom.HelpSelectLanguageDialog
import chin.pswm.gps.photo.location.map.activity.first_open.data.LanguageType
import chin.pswm.gps.photo.location.map.activity.first_open.nav.Dest
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.ext.Tracking
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs
import chin.pswm.gps.photo.location.map.languegess.LanguageManager
import chin.pswm.gps.photo.location.map.languegess.LanguageState
import chin.pswm.gps.photo.location.map.languegess.SharedHelper
import chin.pswm.gps.photo.location.map.utils.LocalScreenTAG
import chin.pswm.gps.photo.location.map.activity.first_open.ui.language.view.LanguageContent

@Composable
fun LanguageAltScreen(
    navController: NavHostController,
    code: String
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
    var language by remember { mutableStateOf(code) }

    fun nextScreen() {
        prefs.language = language
        SharedHelper.putString(context, "lang_key", language)
        LanguageManager.setLocale(context, language)

        if (adsManager.nextLanguage == Dest.Main) {
            prefs.firstOpen = false
            CommonUtils.openToMainScreen(context)
        } else {
            navController.safeNavigate(
                currentRound = Dest.LanguageAlt(code),
                destRoute = adsManager.nextLanguage,
                popUpTo = Dest.LanguageAlt(code)
            )
        }
    }

    LanguageContent(
        languageType = LanguageType.Alt,
        language = language,
        state = LanguageState.state,
        onBack = {
            // no need
        },
        onConfirm = {
            nextScreen()
        },
        onLanguageChange = {
            language = it
        }
    )

    LaunchedEffect(Unit) {
        when (adsManager.nextLanguage) {
            Dest.Select -> {
                adsManager.nativeSelect.loadAd(context)
            }

            Dest.OnBoard -> {
                adsManager.nativeOnboard1.loadAd(context)
                adsManager.nativeFSN.loadAd(context)
            }

            Dest.Main -> {
                // todo: has native home?
            }

            else -> Unit
        }
    }

    BackHandler {
        if (!showLanguageHelp) {
            Tracking.logEvent(TAG + "_back_pressed")
            showLanguageHelp = true
        }
    }

    LifecycleResumeEffect(Unit) {
        if (adsManager.clickedNativeLangAlt) {
            val action = prefs.getString("action_clicked_native_lang_alt", "next_screen")
            when (action) {
                "tooltip" -> if (!showLanguageHelp) {
                    Tracking.logEvent(TAG + "_clicked_ad")
                    showLanguageHelp = true
                }

                "clear_ads" -> adsManager.clearAdsLanguageAlt()

                "next_screen" -> nextScreen()

            }
            adsManager.clickedNativeLangAlt = false
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