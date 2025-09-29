package chin.pswm.gps.photo.location.map.ads

import android.app.Activity
import android.app.Application
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import chin.pswm.gps.photo.location.map.ads.adunit.BaseAds
import chin.pswm.gps.photo.location.map.ads.adunit.interstitial.InterstitialAdUnit
import chin.pswm.gps.photo.location.map.ads.adunit.natiive.NativeAdUnit
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs
import chin.pswm.gps.photo.location.map_debug.BuildConfig
import timber.log.Timber

class AdsManager(
    private val application: Application,
    private val prefs: Prefs,
) : BaseAds(application, prefs) {

    companion object {
        lateinit var INSTANCE: AdsManager
    }

    init {
        INSTANCE = this
    }

    val canShowInterInApp: Boolean
        get() = AppScreenState.screenCreated >= prefs.screenSkipInter

    val interSplash by lazy {
        InterstitialAdUnit(
            "inter_splash_high" to BuildConfig.inter_splash_high,
            "inter_splash" to BuildConfig.inter_splash,
        )
    }

    val interInApp by lazy {
        InterstitialAdUnit(
            "inter_inapp" to BuildConfig.inter_inapp,
        )
    }

    fun loadInterInApp() {
        Timber.tag(TAG).d("loadInterInApp: ")
        if (canShowInterInApp) {
            interInApp.loadAd(application)
        }
    }


    fun showInterInApp(activity: Activity, onNextAction: () -> Unit) {
        Timber.tag(TAG).d("showInterInApp: ")
        var reset = false
        if (canShowInterInApp) {
            reset = true
        }
        interInApp.forceShow(
            activity = activity,
            condition = canShowInterInApp,
            onNextAction = {
                onNextAction()
            }
        )
        if (reset) AppScreenState.screenCreated = 0
    }

    var clickedLanguage by mutableStateOf(false)
    var countShowLanguage by mutableIntStateOf(0)
    val nativeLanguage by lazy {
        NativeAdUnit(
            "native_lang_high" to BuildConfig.native_lang_high,
            "native_lang" to BuildConfig.native_lang,
            onClicked = {
                clickedLanguage = true
            }
        )
    }

    val nativeLanguageAlt by lazy {
        NativeAdUnit(
            "native_lang_alt_high" to BuildConfig.native_lang_alt_high,
            "native_lang_alt" to BuildConfig.native_lang_alt,
        )
    }

    val nativeOnboard by lazy {
        NativeAdUnit(
            "native_ob_high" to BuildConfig.native_ob_high,
            "native_ob" to BuildConfig.native_ob,
        )
    }

    val nativeFSN by lazy {
        NativeAdUnit(
            "native_fsob_high" to BuildConfig.native_fsob_high,
            "native_fsob" to BuildConfig.native_fsob,
            defaultEnable = false
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

    val nativeHome by lazy {
        NativeAdUnit(
            "native_home" to BuildConfig.native_home,
        )
    }

    val nativeExit by lazy {
        NativeAdUnit(
            "native_select" to BuildConfig.native_select,
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
}