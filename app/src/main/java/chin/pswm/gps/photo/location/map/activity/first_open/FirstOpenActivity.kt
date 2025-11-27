package chin.pswm.gps.photo.location.map.activity.first_open

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import chin.pswm.gps.photo.location.map.activity.first_open.common.Constants
import chin.pswm.gps.photo.location.map.activity.first_open.nav.FirstOpenNavigation
import chin.pswm.gps.photo.location.map.activity.first_open.service.KillAppService
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.ext.ITag
import chin.pswm.gps.photo.location.map.ads.ext.Tracking
import chin.pswm.gps.photo.location.map.ads.ext.tryWithoutCatch
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs
import chin.pswm.gps.photo.location.map.languegess.LanguageManager
import chin.pswm.gps.photo.location.map.languegess.SharedHelper
import chin.pswm.gps.photo.location.map.notification.NotificationManager
import chin.pswm.gps.photo.location.map.ui.theme.PermissionManager.Companion.allowNotification
import chin.pswm.gps.photo.location.map.ui.theme.colorWhite
import chin.pswm.gps.photo.location.map.utils.BaseActivity
import chin.pswm.gps.photo.location.map_debug.R
import timber.log.Timber

class FirstOpenActivity : BaseActivity(), ITag {

    private val prefs: Prefs
        get() = Prefs.INSTANCE

    private val notificationManager: NotificationManager
        get() = NotificationManager.INSTANCE

    private val adsManager: AdsManager
        get() = AdsManager.INSTANCE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LanguageManager.setLocale(this@FirstOpenActivity, SharedHelper.getString(this, "lang_key", ""))
        adsManager.resetState()
        setContent {
            FirstOpenNavigation(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorWhite),
                intent = intent
            )
        }
        prefs.onBoardOpen = false
        notificationManager.cancelNotification(23644444)
    }

    private fun hideSystemNavigationBar() {
        try {
            WindowCompat.setDecorFitsSystemWindows(window, true)
            WindowCompat.getInsetsController(window, window.decorView).apply {
                systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                isAppearanceLightNavigationBars = false
                hide(WindowInsetsCompat.Type.navigationBars())
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        hideSystemNavigationBar()
    }

    /*private fun initArgs() {
        if (intent.data != null) {
            Tracking.logEvent("open_from_gallery")
        }

        val extras = intent.extras ?: return

        val openTo = extras.getInt(Constants.KEY_OPEN_TO, Constants.OPEN_TO_DEFAULT)
        prefs.openTo = openTo

        Timber.Forest.tag(TAG).d("initArgs: openTo $openTo")

        val openFrom = extras.getInt(Constants.KEY_OPEN_FROM, Constants.OPEN_FROM_DEFAULT)
        when (openFrom) {
            Constants.OPEN_FROM_SHORTCUT -> {
                Tracking.logEvent("open_from_shortcut")
                if (openTo == Constants.OPEN_TO_UNINSTALL) {
                    Tracking.logEvent("open_from_shortcut_uninstall")
                }
            }

            Constants.OPEN_FROM_NOTIFY -> Tracking.logEvent("open_from_notification")
            Constants.OPEN_FROM_NOTIFY_PINNED -> Tracking.logEvent("open_from_notification_pinned")
            Constants.OPEN_FROM_NOTIFY_KILL -> Tracking.logEvent("open_from_notification_kill_app")
            Constants.OPEN_FROM_WIDGET -> Tracking.logEvent("open_from_widget")
        }
    }*/

    override fun onDestroy() {
        super.onDestroy()
        Timber.Forest.tag(TAG).w("onDestroy: ")
//        prefs.onBoardScreenCount = 0

        if (prefs.onBoardOpen) {
            notificationManager.setOnBoardNotification(
                notificationId = 23644444,
            )
        } else if (prefs.firstOpen) {
            notificationManager.setNotification(
                notificationId = 23647623,
                type = Constants.OPEN_FROM_NOTIFY_KILL,
                title = this.getString(R.string.onboard_popup_title),
                content = this.getString(R.string.onboard_popup_content),
                icon = R.drawable.img_camera,
            )
        }
        unbindService()
    }

    // For notification
    private var mService: KillAppService? = null
    private var binded = false
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            Timber.Forest.tag(TAG).d("onServiceConnected: ")
            val binder = service as KillAppService.LocalBinder
            mService = binder.getService()
            binded = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            Timber.Forest.tag(TAG).d("onServiceDisconnected: ")
            mService = null
            binded = false
        }
    }

    fun bindService() {
        if (this.allowNotification && !binded && prefs.firstOpen) {
            Timber.tag(TAG).d("bindService: ")
            tryWithoutCatch {
                KillAppService.startService(this)
                val intent = Intent(this, KillAppService::class.java)
                bindService(intent, connection, BIND_AUTO_CREATE)
            }
        }
    }

    fun unbindService() {
        tryWithoutCatch {
            if (binded) {
                unbindService(connection)
                binded = false
            }
        }
    }
}