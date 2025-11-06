package chin.pswm.gps.photo.location.map.activity.first_open.ui.language

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.navigation.NavHostController
import chin.pswm.gps.photo.location.map.activity.first_open.common.CommonUtils
import chin.pswm.gps.photo.location.map.activity.first_open.common.NavigationUtil.safeNavigate
import chin.pswm.gps.photo.location.map.activity.first_open.cusom.HelpSelectLanguageDialog
import chin.pswm.gps.photo.location.map.activity.first_open.data.LanguageType
import chin.pswm.gps.photo.location.map.activity.first_open.nav.Dest
import chin.pswm.gps.photo.location.map.activity.first_open.ui.language.view.LanguageContent
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.ext.Tracking
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs
import chin.pswm.gps.photo.location.map.languegess.LanguageManager
import chin.pswm.gps.photo.location.map.languegess.LanguageState
import chin.pswm.gps.photo.location.map.languegess.SharedHelper
import chin.pswm.gps.photo.location.map.utils.LocalScreenTAG
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LanguageScreen(
    navController: NavHostController,
    isFromSplash: Boolean
) {
    val TAG = LocalScreenTAG.current
    val context = LocalContext.current
    val langType = if (isFromSplash) LanguageType.Normal else LanguageType.Setting
    val adsManager: AdsManager = remember {
        AdsManager.INSTANCE
    }
    val prefs: Prefs = remember {
        Prefs.INSTANCE
    }

    var languageCode = SharedHelper.getString(context, "lang_key", "")

    var showLanguageHelp by remember { mutableStateOf(false) }
    var language by remember { mutableStateOf("") }

    fun onConfirm(code: String) {
        prefs.language = language
        SharedHelper.putString(context, "lang_key", language)
        LanguageManager.setLocale(context, language)
//        navController.safeNavigate(Dest.Language, Dest.LanguageAlt(code), Dest.Language)
    }

    LanguageContent(
        languageType = langType,
        showPoint = adsManager.clickedLanguage,
        language = languageCode,
        state = LanguageState.state,
        onBack = {
            (context as? Activity)?.finish()
        },
        onConfirm = {
            if (language.isEmpty()) {
                if (!showLanguageHelp) {
                    Tracking.logEvent(TAG + "_confirm_pressed")
                    showLanguageHelp = true
                }
            } else {

                if (!isFromSplash) {
                    CommonUtils.openToMainScreen(context)
                } else {
                    prefs.language = language
                    SharedHelper.putString(context, "lang_key", language)
                    LanguageManager.setLocale(context, language)

                    if (adsManager.nextLanguage == Dest.Main) {
                        prefs.firstOpen = false
                        CommonUtils.openToMainScreen(context)
                    } else {
                        navController.safeNavigate(
                            currentRound = Dest.Language,
                            destRoute = adsManager.nextLanguage,
                            popUpTo = Dest.Language
                        )
                    }
                }
            }
        },
        onLanguageChange = { code ->
            language = code
            onConfirm(code)
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
                adsManager.nativeHome.loadAd(context)
            }

            else -> Unit
        }
    }

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

//        scope.launch {
//            delay(250)
//            adsManager.nativeLanguageAlt.loadAd(context)
//        }

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