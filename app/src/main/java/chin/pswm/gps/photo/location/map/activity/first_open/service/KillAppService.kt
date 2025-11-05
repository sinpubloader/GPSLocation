package chin.pswm.gps.photo.location.map.activity.first_open.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat
import chin.pswm.gps.photo.location.map.ads.ext.ITag
import chin.pswm.gps.photo.location.map.ads.ext.tryWithoutCatch
import chin.pswm.gps.photo.location.map.notification.NotificationManager
import chin.pswm.gps.photo.location.map.ui.theme.PermissionManager.Companion.allowNotification
import chin.pswm.gps.photo.location.map_debug.R
import timber.log.Timber

class KillAppService : Service(), ITag {

    private val binder = LocalBinder()

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        Timber.Forest.tag(TAG).e("onTaskRemoved: ")
        stopSelf()
    }

    override fun onCreate() {
        super.onCreate()
        Timber.Forest.tag(TAG).d("onCreate: ")
        tryWithoutCatch {
            if (allowNotification) {
                val notification = NotificationCompat.Builder(this, NotificationManager.Companion.CHANNEL_KILL_APP)
                    .setContentTitle(getString(R.string.kill_app_notification_title))
                    .setContentTitle(this@KillAppService.getString(R.string.kill_app_notification_content))
                    .build()

                startForeground(5345341, notification)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.Forest.tag(TAG).d("onDestroy: ")
        stopForeground(STOP_FOREGROUND_REMOVE)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        tryWithoutCatch {
            if (allowNotification) {
                val notification = NotificationCompat.Builder(this, NotificationManager.Companion.CHANNEL_KILL_APP)
                    .setContentTitle(getString(R.string.kill_app_notification_title))
                    .setContentTitle(this@KillAppService.getString(R.string.kill_app_notification_content))
                    .build()

                startForeground(5345341, notification)
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    inner class LocalBinder : Binder() {
        fun getService(): KillAppService = this@KillAppService
    }
}