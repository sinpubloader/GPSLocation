package chin.pswm.gps.photo.location.map.notification

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import chin.pswm.gps.photo.location.map.activity.StartActivity
import chin.pswm.gps.photo.location.map.ads.ext.tryWithoutCatch
import chin.pswm.gps.photo.location.map.ui.theme.PermissionManager.Companion.allowNotification
import chin.pswm.gps.photo.location.map.utils.BitmapUtil
import chin.pswm.gps.photo.location.map.utils.ITag
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Calendar
import chin.pswm.gps.photo.location.map_debug.R

class NotificationManager(
    private val app: Application
) : ITag {


    companion object {
        const val CHANNEL_PINNED = "recovery_channel_pinned"
        const val CHANNEL_KILL_APP = "recovery_channel_kill_app"
        const val CHANNEL_DAILY = "recovery_channel_daily"

        const val DAILY_NOTIFICATION = 21051999
        private const val CODE_REQUEST_DAILY = 123213
    }

    private val coroutineScope = CoroutineScope(Dispatchers.IO + CoroutineExceptionHandler { _, e ->
        Timber.tag(TAG).e("error: ${e.message}")
    })

    init {
        createNotificationChannel(CHANNEL_PINNED, NotificationManager.IMPORTANCE_HIGH)
        createNotificationChannel(CHANNEL_KILL_APP, NotificationManager.IMPORTANCE_LOW)
        createNotificationChannel(CHANNEL_DAILY, NotificationManager.IMPORTANCE_HIGH)
    }

    private fun createNotificationChannel(channelId: String, importance: Int) {
        val name = app.getString(R.string.app_name)
        val descriptionText = app.getString(R.string.app_name)
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
            setShowBadge(false)
        }

        val manager = app.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    fun scheduleReminder() {
        coroutineScope.launch {
            val alarmManager = app.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val intent = Intent(app, NotificationReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                app, CODE_REQUEST_DAILY, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            val calendar = Calendar.getInstance().apply {
                when (get(Calendar.HOUR_OF_DAY)) {
                    in 8..18 -> {
                        set(Calendar.HOUR_OF_DAY, 19)
                        set(Calendar.MINUTE, 0)
                        set(Calendar.SECOND, 0)
                    }

                    in 19..23 -> {
                        set(Calendar.HOUR_OF_DAY, 8)
                        set(Calendar.MINUTE, 0)
                        set(Calendar.SECOND, 0)
                        add(Calendar.DAY_OF_MONTH, 1)
                    }

                    in 0..7 -> {
                        set(Calendar.HOUR_OF_DAY, 8)
                        set(Calendar.MINUTE, 0)
                        set(Calendar.SECOND, 0)
                    }
                }
            }

            try {
                alarmManager.setAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent
                )
                Timber.Forest.tag(TAG).d("scheduleReminder: at ${calendar.time}")
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    fun cancelNotification(id: Int) {
        tryWithoutCatch {
            with(NotificationManagerCompat.from(app)) {
                cancel(id)
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun setNotification(
        notificationId: Int,
        type: Int,
        title: String,
        content: String,
        @DrawableRes icon: Int,
    ) {
        if (app.allowNotification) {
            val pendingIntent = PendingIntent.getActivity(
                app, 41999331, Intent(app, StartActivity::class.java).apply {
                    action = Intent.ACTION_VIEW
                    putExtras(bundleOf("KEY_OPEN_FROM" to type))
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
            val notification =
                NotificationCompat.Builder(app, CHANNEL_DAILY)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.ic_map)
                    .setAutoCancel(false)
                    .also {
                        if (icon != 0) {
                            val bitmap = BitmapUtil.bitmapFromDrawable(
                                context = app, drawableResId = icon
                            )
                            if (bitmap != null) {
                                it.setLargeIcon(bitmap)
                            }
                        }
                    }
                    .build()
            with(NotificationManagerCompat.from(app)) {
                notify(notificationId, notification)
            }
        }
    }
}