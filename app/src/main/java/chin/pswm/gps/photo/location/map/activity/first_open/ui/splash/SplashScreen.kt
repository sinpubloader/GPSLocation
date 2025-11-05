package chin.pswm.gps.photo.location.map.activity.first_open.ui.splash

import android.Manifest.permission.POST_NOTIFICATIONS
import android.app.Activity
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.navigation.NavHostController
import chin.pswm.gps.photo.location.map.activity.first_open.FirstOpenActivity
import chin.pswm.gps.photo.location.map.activity.first_open.common.CommonUtils
import chin.pswm.gps.photo.location.map.activity.first_open.common.NavigationUtil.safeNavigate
import chin.pswm.gps.photo.location.map.activity.first_open.cusom.shotcuts.ShortcutManager
import chin.pswm.gps.photo.location.map.activity.first_open.nav.Dest
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.AppScreenState
import chin.pswm.gps.photo.location.map.ads.adunit.common.AdErrorCode
import chin.pswm.gps.photo.location.map.ads.adunit.common.AdsStatus
import chin.pswm.gps.photo.location.map.ads.ext.Tracking
import chin.pswm.gps.photo.location.map.ads.ext.tryWithoutCatch
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs
import chin.pswm.gps.photo.location.map.ads.remoteconfig.RemoteConfigManager
import chin.pswm.gps.photo.location.map.languegess.LanguageState
import chin.pswm.gps.photo.location.map.notification.NotificationManager
import chin.pswm.gps.photo.location.map.ui.theme.PermissionManager.Companion.allowNotification
import chin.pswm.gps.photo.location.map.utils.LocalScreenTAG
import chin.pswm.gps.photo.location.map.activity.first_open.ui.splash.view.SplashContent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.Locale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SplashScreen(
    navController: NavHostController,
) {

    val context = LocalContext.current
    val TAG = LocalScreenTAG.current

    val prefs: Prefs = remember {
        Prefs.INSTANCE
    }
    val adsManager: AdsManager = remember {
        AdsManager.INSTANCE
    }
    val notificationManager: NotificationManager = remember {
        NotificationManager.INSTANCE
    }
    val remoteConfigManager: RemoteConfigManager = remember {
        RemoteConfigManager.INSTANCE
    }

    fun goNextScreen() {
        when {
            prefs.firstOpen -> {
                navController.safeNavigate(Dest.Splash, adsManager.nextSplash, Dest.Splash)
            }

            else -> {
                CommonUtils.openToMainScreen(context)
            }
        }
    }

    val notificationPermissionState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        rememberPermissionState(
            permission = POST_NOTIFICATIONS, onPermissionResult = { allow ->
                if (allow) {
                    Tracking.logEvent("SplashScreen_allow_notification")
                    notificationManager.setReminderPinned(context)
                    (context as? FirstOpenActivity)?.bindService()
                } else {
                    Tracking.logEvent("SplashScreen_deny_notification")
                }
                AppScreenState.requestingNotificationPermission.value = true
            })
    } else remember { null }

    LaunchedEffect(Unit) {
        combine(
            flow = adsManager.initFinished,
            flow2 = adsManager.isConnectedFlow,
            flow3 = remoteConfigManager.fetchedStateFlow,
        ) { consentFinished, isConnected, config ->
            Timber.tag(TAG).d("init: $consentFinished, $isConnected, $config")
            consentFinished && isConnected && config
        }.collectLatest { allowCallNext ->
            if (!allowCallNext) return@collectLatest

            if (context.allowNotification) {
                AppScreenState.requestingNotificationPermission.value = true
                notificationManager.setReminderPinned(context)
                (context as? FirstOpenActivity)?.bindService()
            } else if (notificationPermissionState != null && !AppScreenState.requestingNotificationPermission.value) {
                Tracking.logEvent(TAG + "_request_notification")
                notificationPermissionState.launchPermissionRequest()
            } else {
                AppScreenState.requestingNotificationPermission.value = true
            }

            if (adsManager.interSplash.status == AdsStatus.NONE) {
                adsManager.interSplash.loadAd(context)
                if (prefs.firstOpen) {
                    when (adsManager.nextSplash) {
                        Dest.Language -> {
                            adsManager.nativeLanguage.loadAd(context)
                        }

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
                } else {
                    adsManager.nativeHome.loadAd(context)
                }
            }
        }
    }

    fun showSplash() {
        adsManager.interSplash.apply {
            onImpression = {
                AppScreenState.screenCreated = 0
                adsManager.lastShowInterstitial = System.currentTimeMillis()
                if (adsManager.nextSplash != Dest.Main) {
                    goNextScreen()
                }
            }
        }.show(
            context as Activity,
            onAdClosed = {
                goNextScreen()
            },
            onNextAction = {
                if (adsManager.nextSplash != Dest.Main) {
                    goNextScreen()
                }
            },
            onAdFailedToShow = {
                if (it?.code != AdErrorCode.ERROR_CODE_SHOW_IN_BACKGROUND.code) {
                    goNextScreen()
                }
            },
        )
    }

    val flowAds = remember {
        combine(
            adsManager.interSplash.statusFlow,
            AppScreenState.requestingNotificationPermission.asStateFlow()
        ) { splash, noti ->
            Timber.tag(TAG).d("ads: $splash, $noti")
            splash to noti
        }.distinctUntilChanged()
    }.collectAsState(AdsStatus.NONE to false).value

    LifecycleResumeEffect(flowAds) {
        val splash = flowAds.first
        val noti = flowAds.second
        if (noti) {
            if (splash == AdsStatus.IMPRESSED || splash == AdsStatus.FAIL) {
                goNextScreen()
            } else if (splash == AdsStatus.SUCCESS) {
                showSplash()
            }
        }

        onPauseOrDispose {

        }
    }

    LaunchedEffect(Unit) {
        ShortcutManager(context).updateShortcuts()
        notificationManager.scheduleReminder()
        adsManager.requestUMP(activity = context as FirstOpenActivity, enableDebug = true, resetData = false)
    }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            if (prefs.firstOpen) {
                tryWithoutCatch {
                    LanguageState.codes.sortBy { LanguageState.name(it) }
                    val code = Locale.getDefault().language
                    val languagesAtCurrent = LanguageState.codes.filter {
                        Locale.forLanguageTag(it).language == code
                    }
                    if (languagesAtCurrent.isNotEmpty()) {
                        LanguageState.codes.removeIf {
                            LanguageState.name(it) in languagesAtCurrent.map { old ->
                                LanguageState.name(
                                    old
                                )
                            }
                        }
                        LanguageState.codes.sortBy { LanguageState.name(it) }
                        languagesAtCurrent.forEach {
                            LanguageState.codes.add(0, it)
                        }
                    } else {
                        val languagesAtCurrent =
                            LanguageState.codes.filter {
                                Locale.forLanguageTag(it).language == "en"
                            }

                        LanguageState.codes.removeIf {
                            LanguageState.name(it) in languagesAtCurrent.map { old ->
                                LanguageState.name(
                                    old
                                )
                            }
                        }
                        LanguageState.codes.sortBy { LanguageState.name(it) }
                        languagesAtCurrent.forEach {
                            LanguageState.codes.add(0, it)
                        }
                    }
                }
            }
        }
    }


    SplashContent()
}