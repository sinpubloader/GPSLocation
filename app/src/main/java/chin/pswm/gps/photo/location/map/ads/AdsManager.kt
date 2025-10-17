package chin.pswm.gps.photo.location.map.ads

import android.app.Activity
import android.app.Application
import androidx.annotation.LayoutRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalInspectionMode
import chin.pswm.gps.photo.location.map.ads.adunit.BaseAds
import chin.pswm.gps.photo.location.map.ads.adunit.interstitial.InterstitialAdUnit
import chin.pswm.gps.photo.location.map.ads.adunit.natiive.NativeAdUnit
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs
import chin.pswm.gps.photo.location.map_debug.BuildConfig
import chin.pswm.gps.photo.location.map_debug.R
import org.koin.compose.koinInject
import timber.log.Timber

class AdsManager(
    private val application: Application,
    private val prefs: Prefs,
) : BaseAds(application, prefs) {

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

    val nativeOnboard1 by lazy {
        NativeAdUnit(
            "native_ob1_high" to BuildConfig.native_ob1_high,
            "native_ob1" to BuildConfig.native_ob1,
        )
    }

    val nativeOnboard3 by lazy {
        NativeAdUnit(
            "native_ob3_high" to BuildConfig.native_ob3_high,
            "native_ob3" to BuildConfig.native_ob3,
        )
    }

    val nativeFSN by lazy {
        NativeAdUnit(
            "native_fsob_high" to BuildConfig.native_fsob_high,
            "native_fsob" to BuildConfig.native_fsob,
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

    companion object {

        lateinit var INSTANCE: AdsManager

        @Stable
        @Composable
        @LayoutRes
        fun layoutNative(name: String, @LayoutRes default: Int): Int {
            val preview = LocalInspectionMode.current
            if (preview) return default
            val prefs: Prefs = koinInject()
            val configLayout = remember {
                prefs.getString(name, "")
            }

            if (configLayout?.isEmpty() == true) return default
            return when (configLayout) {
                // ctr big at bottom have media big
                "native_media_ctr_bot_big_filled" -> R.layout.native_media_ctr_bot_big_filled
                "native_media_ctr_bot_big_stroke" -> R.layout.native_media_ctr_bot_big_stroke

                // ctr big at top have media big
                "native_media_ctr_top_big_filled" -> R.layout.native_media_ctr_top_big_filled
                "native_media_ctr_top_big_stroke" -> R.layout.native_media_ctr_top_big_stroke

                // ctr big at bottom have media small 140dp
                "native_media_ctr_bot_small_filled" -> R.layout.native_media_ctr_bot_small_filled
                "native_media_ctr_bot_small_stroke" -> R.layout.native_media_ctr_bot_small_stroke

                // ctr big at top have media small 140dp
                "native_media_ctr_top_small_filled" -> R.layout.native_media_ctr_top_small_filled
                "native_media_ctr_top_small_stroke" -> R.layout.native_media_ctr_top_small_stroke

                // header + ctr same line at top, media lon
                "native_media_action_top_big_filled" -> R.layout.native_media_action_top_big_filled
                "native_media_action_top_big_stroke" -> R.layout.native_media_action_top_big_stroke

                // header + ctr same line at, media lon
                "native_media_action_bot_big_filled" -> R.layout.native_media_action_bot_big_filled
                "native_media_action_bot_big_stroke" -> R.layout.native_media_action_bot_big_stroke

                // header + ctr same line at top, media small 140dp
                "native_media_action_top_small_filled" -> R.layout.native_media_action_top_small_filled
                "native_media_action_top_small_stroke" -> R.layout.native_media_action_top_small_stroke

                // header + ctr same line at, media small 140dp
                "native_media_action_bot_small_filled" -> R.layout.native_media_action_bot_small_filled
                "native_media_action_bot_small_stroke" -> R.layout.native_media_action_bot_small_stroke

                // meta small left
                "native_media_left_filled" -> R.layout.native_media_left_filled
                "native_media_left_stroke" -> R.layout.native_media_left_stroke

                // not have media, ctr big at bottom
                "native_none_media_action_big_filled" -> R.layout.native_none_media_action_big_filled
                "native_none_media_action_big_stroke" -> R.layout.native_none_media_action_big_stroke

                // not have media, ctr small at right
                "native_none_media_action_small_right_filled" -> R.layout.native_none_media_action_small_right_filled
                "native_none_media_action_small_right_stroke" -> R.layout.native_none_media_action_small_right_stroke

                // not have media, ctr small at bottom
                "native_none_media_action_small_bottom_filled" -> R.layout.native_none_media_action_small_bottom_filled
                "native_none_media_action_small_bottom_stroke" -> R.layout.native_none_media_action_small_bottom_stroke

                // fsn
                "native_fsn_filled" -> R.layout.native_full_screen
                else -> default
            }
        }
    }
}