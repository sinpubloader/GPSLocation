package chin.pswm.gps.photo.location.map.activity.first_open.service


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import chin.pswm.gps.photo.location.map.ads.ext.tryWithoutCatch
import chin.pswm.gps.photo.location.map.ui.theme.PermissionManager.Companion.allowNotification
import chin.pswm.gps.photo.location.map_debug.R
import timber.log.Timber


class KillAppService : Service() {

    companion object {
        private const val TAG = "KillAppService"
        private const val NOTIFICATION_ID = 5345341
        private const val CHANNEL_ID =
            chin.pswm.gps.photo.location.map.notification.NotificationManager.Companion.CHANNEL_KILL_APP

        fun startService(context: android.content.Context) {
            val intent = Intent(context, KillAppService::class.java)
            ContextCompat.startForegroundService(context, intent)
        }

        fun stopService(context: android.content.Context) {
            val intent = Intent(context, KillAppService::class.java)
            context.stopService(intent)
        }
    }

    private val binder = LocalBinder()

    override fun onCreate() {
        super.onCreate()
        Timber.tag(TAG).d("onCreate() called")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timber.tag(TAG).d("onStartCommand() called")

        tryWithoutCatch {
            if (allowNotification) {
                val notification = createNotification()
                startForeground(NOTIFICATION_ID, notification)
                Timber.tag(TAG).d("startForeground() success")
            }
        }

        return START_STICKY
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        Timber.tag(TAG).e("onTaskRemoved() called")
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.tag(TAG).d("onDestroy() called")
        stopForeground(STOP_FOREGROUND_REMOVE)
    }

    override fun onBind(intent: Intent?): IBinder = binder

    inner class LocalBinder : Binder() {
        fun getService(): KillAppService = this@KillAppService
    }

    private fun createNotification(): android.app.Notification {
        val manager = getSystemService(NotificationManager::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                getString(R.string.kill_app_notification_title),
                NotificationManager.IMPORTANCE_LOW
            )
            manager.createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.kill_app_notification_title))
            .setContentText(getString(R.string.kill_app_notification_content))
            .build()
    }
}
