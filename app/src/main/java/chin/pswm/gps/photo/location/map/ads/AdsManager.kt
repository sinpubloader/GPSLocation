package chin.pswm.gps.photo.location.map.ads

import android.app.Activity
import android.app.Application
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import chin.pswm.gps.photo.location.map.activity.first_open.nav.Dest
import chin.pswm.gps.photo.location.map.ads.adunit.BaseAds
import chin.pswm.gps.photo.location.map.ads.adunit.interstitial.InterstitialAdUnit
import chin.pswm.gps.photo.location.map.ads.adunit.natiive.NativeAdUnit
import chin.pswm.gps.photo.location.map.ads.ext.tryWithoutCatch
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs
import chin.pswm.gps.photo.location.map_debug.BuildConfig
import timber.log.Timber

class AdsManager(
    private val application: Application,
    private val prefs: Prefs,
) : BaseAds(application, prefs) {

    init {
        INSTANCE = this
    }

    var lastShowInterstitial by mutableLongStateOf(0L)

    val intervalInterInApp: Long
        get() = prefs.intervalInterInapp
    val canShowInterInApp: Boolean
        get() {
            return AppScreenState.screenCreated >= prefs.screenSkipInter && (System.currentTimeMillis() - lastShowInterstitial) >= intervalInterInApp
        }

    val interSplash by lazy {
        InterstitialAdUnit(
            "inter_splash_high" to BuildConfig.inter_splash_high,
            "inter_splash" to BuildConfig.inter_splash,
        )
    }

    val interPermission by lazy {
        InterstitialAdUnit(
            "inter_permission" to BuildConfig.inter_permission,
        )
    }

    val interInApp by lazy {
        InterstitialAdUnit(
            "inter_inapp" to BuildConfig.inter_inapp,
        )
    }

    fun loadInterInApp() {
        Timber.tag(TAG).d("loadInterInApp: ${AppScreenState.screenCreated}")
        if (canShowInterInApp) {
            Timber.tag(TAG).d("loadInterInApp: start load inter in app")
            interInApp.loadAd(application)
        }
    }


    fun showInterInApp(activity: Activity, showLoading: Boolean, onNextAction: () -> Unit) {
        Timber.tag(TAG).d("showInterInApp: ")
        var reset = false
        if (canShowInterInApp && interInApp.canShowAd()) {
            reset = true
        }
        if (!showLoading) {
            interInApp.forceShow(
                activity = activity,
                condition = canShowInterInApp,
                onNextAction = {
                    onNextAction()
                }
            )
        } else {
            interInApp.show(
                activity = activity,
                showLoading = true,
                onAdClosed = {
                    onNextAction()
                    LoadingAdsDialog.hideLoading()
                },
                onAdFailedToShow = {
                    onNextAction()
                    LoadingAdsDialog.hideLoading()
                },
                onNextAction = {
                    onNextAction()
                    LoadingAdsDialog.hideLoading()
                }
            )
        }
        if (reset) {
            Timber.tag(TAG).d("showInterInApp: reset screen = 0")
            AppScreenState.screenCreated = 0
        }
    }

    fun resetState() {
        tryWithoutCatch {
            AppScreenState.screenCreated = 0

            isShowingAds = false
            interSplash.resetIfFailOrImpressed()
            nativeLanguage.resetIfFailOrImpressed()

            clickedLanguageTooltip = false
            clickedLanguage = false
            clickedNativeLangAlt = false
            clickedNativeFsn = false
            clickedNativeOb1 = false
            clickedNativeOb4 = false

            nativeLanguage.unDisable()
            nativeLanguageAlt.unDisable()
            nativeOnboard1.unDisable()
            nativeOnboard4.unDisable()
            nativeFSN.unDisable()
        }
    }

    fun clearAdsFsn() {
        nativeFSN.reset()
        nativeFSN.disable()
    }

    fun clearAdsOb1() {
        nativeOnboard1.reset()
        nativeOnboard1.disable()
    }

    fun clearAdsOb4() {
        nativeOnboard4.reset()
        nativeOnboard4.disable()
    }

    fun clearAdsLanguage() {
        nativeLanguage.reset()
        nativeLanguage.disable()
    }

    fun clearAdsLanguageAlt() {
        nativeLanguageAlt.reset()
        nativeLanguageAlt.disable()
    }

    var clickedLanguage by mutableStateOf(false)
    var clickedLanguageTooltip by mutableStateOf(false)
    var countShowLanguage by mutableIntStateOf(0)
    val nativeLanguage by lazy {
        NativeAdUnit(
            "native_lang_high" to BuildConfig.native_lang_high,
            "native_lang" to BuildConfig.native_lang,
            onClicked = {
                clickedLanguage = true
                clickedLanguageTooltip = true
            }
        )
    }

    var clickedNativeLangAlt by mutableStateOf(false)
    val nativeLanguageAlt by lazy {
        NativeAdUnit(
            "native_lang_alt_high" to BuildConfig.native_lang_alt_high,
            "native_lang_alt" to BuildConfig.native_lang_alt,
            onClicked = {
                clickedNativeLangAlt = true
            }
        )
    }

    var clickedNativeOb1 by mutableStateOf(false)
    val nativeOnboard1 by lazy {
        NativeAdUnit(
            "native_ob1_high" to BuildConfig.native_ob1_high,
            "native_ob1" to BuildConfig.native_ob1,
            onClicked = {
                clickedNativeOb1 = true
            }
        )
    }

    var clickedNativeOb4 by mutableStateOf(false)
    val nativeOnboard4 by lazy {
        NativeAdUnit(
            "native_ob3_high" to BuildConfig.native_ob3_high,
            "native_ob3" to BuildConfig.native_ob3,
            onClicked = {
                clickedNativeOb4 = true
            }
        )
    }

    var clickedNativeFsn by mutableStateOf(false)
    val nativeFSN by lazy {
        NativeAdUnit(
            "native_fsob_high" to BuildConfig.native_fsob_high,
            "native_fsob" to BuildConfig.native_fsob,
            onClicked = {
                clickedNativeFsn = true
            }
        )
    }

    val nativeSelect by lazy {
        NativeAdUnit(
            "native_select_high" to BuildConfig.native_select_high,
            "native_select" to BuildConfig.native_select,
        )
    }

    val nativeSelectAlt by lazy {
        NativeAdUnit(
            "native_select_alt_high" to BuildConfig.native_select_alt_high,
            "native_select_alt" to BuildConfig.native_select_alt,
        )
    }

    val nativeUninstall by lazy {
        NativeAdUnit(
            "native_uninstall" to BuildConfig.native_uninstall,
        )
    }

    val nativeHome by lazy {
        NativeAdUnit(
            "native_home" to BuildConfig.native_home,
        )
    }

    // for config
    @Stable
    val reloadAdsLanguage: Boolean
        get() = prefs.getBoolean("reload_language", true)

    @Stable
    val reloadAdsLanguageAlt: Boolean
        get() = prefs.getBoolean("reload_language_alt", false)

    @Stable
    val reloadAdsOnboard: Boolean
        get() = prefs.getBoolean("reload_onboard", true)

    @Stable
    val reloadAdsHome: Boolean
        get() = prefs.getBoolean("reload_home", true)

    @Stable
    val reloadAdsSelect: Boolean
        get() = prefs.getBoolean("reload_select", true)

    @Stable
    val reloadAdsFSN: Boolean
        get() = prefs.getBoolean("reload_fsn", true)

    @Stable
    val showCloseFsn: Boolean
        get() = prefs.getBoolean("show_close_fsn", true)

    val flowOffSplashAllPriceAndUninstall: Boolean
        get() = prefs.getBoolean("flow_off_splash_all_price_and_uninstall", true)

    // Screen Next
    @Stable
    val nextSplash: Dest
        get() {
            return if (prefs.firstOpen) {
                val splashNextConfig = prefs.getString("splash_next", "language")
                when (splashNextConfig) {
                    "select" -> Dest.Select
                    "onboard" -> Dest.OnBoard
                    "home" -> Dest.Main
                    else -> Dest.Language

                }
            } else Dest.Main
        }

    @Stable
    val nextLanguage: Dest
        get() {
            val languageNextConfig = prefs.getString("language_next", "select")
            return when (languageNextConfig) {
                "onboard" -> Dest.OnBoard
                "home" -> Dest.Main
                else -> Dest.Select

            }
        }

    @Stable
    val nextSelect: Dest
        get() {
            val languageNextConfig = prefs.getString("select_next", "onboard")
            return when (languageNextConfig) {
                "language" -> Dest.Language
                "home" -> Dest.Main
                else -> Dest.OnBoard
            }
        }

    @Stable
    val nextOnBoard: Dest
        get() {
            val languageNextConfig = prefs.getString("onboard_next", "home")
            return when (languageNextConfig) {
                "select" -> Dest.Select
                "language" -> Dest.Language
                else -> Dest.Main
            }
        }

    companion object {
        lateinit var INSTANCE: AdsManager
    }
}