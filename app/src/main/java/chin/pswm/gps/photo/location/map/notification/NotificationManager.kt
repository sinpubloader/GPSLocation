package chin.pswm.gps.photo.location.map.notification

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import chin.pswm.gps.photo.location.map.activity.StartActivity
import chin.pswm.gps.photo.location.map.activity.first_open.FirstOpenActivity
import chin.pswm.gps.photo.location.map.activity.first_open.common.Constants
import chin.pswm.gps.photo.location.map.ads.ext.tryWithoutCatch
import chin.pswm.gps.photo.location.map.ui.theme.PermissionManager.Companion.allowNotification
import chin.pswm.gps.photo.location.map.utils.BitmapUtil
import chin.pswm.gps.photo.location.map.utils.ITag
import chin.pswm.gps.photo.location.map_debug.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Calendar

class NotificationManager(
    private val app: Application
) : ITag {


    companion object {
        const val CHANNEL_KILL_APP = "recovery_channel_kill_app"
        const val CHANNEL_DAILY = "recovery_channel_daily"

        const val DAILY_NOTIFICATION = 234213423
        const val PINNED_NOTIFICATION_ID = 445345
        private const val CODE_REQUEST_DAILY = 12123213

        lateinit var INSTANCE: chin.pswm.gps.photo.location.map.notification.NotificationManager
    }

    private val coroutineScope = CoroutineScope(Dispatchers.IO + CoroutineExceptionHandler { _, e ->
        Timber.tag(TAG).e("error: ${e.message}")
    })

    init {
        INSTANCE = this@NotificationManager
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

    @SuppressLint("MissingPermission")
    fun setOnBoardNotification(
        notificationId: Int,
    ) {
        if (app.allowNotification) {

            cancelNotification(notificationId)

            val pendingIntent = PendingIntent.getActivity(
                app,
                notificationId,
                Intent(app, FirstOpenActivity::class.java).apply {
                    putExtra(Constants.KEY_OPEN_FROM, Constants.OPEN_FROM_ONBOARD_NOTI)
                    action = Intent.ACTION_VIEW
                },
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )

            val notification = NotificationCompat.Builder(app, CHANNEL_KILL_APP)
                .setSmallIcon(R.drawable.ic_map)
                .setContentTitle("Continue onboarding")
                .setContentText("Tap to continue where you left off")
                .setPriority(NotificationCompat.PRIORITY_LOW)
//                .setCategory(NotificationCompat.CATEGORY_CALL)
                .setAutoCancel(true)
//                .setFullScreenIntent(pendingIntent, true)
                .setContentIntent(pendingIntent)
                .build()

            NotificationManagerCompat.from(app).notify(notificationId, notification)
        }
    }

    @SuppressLint("MissingPermission")
    fun setReminderPinned(context: Context) {
        if (!context.allowNotification) return
        Timber.tag(TAG).d("setReminderPinned: ")
        val pendingIntentNone = PendingIntent.getActivity(
            context,
            123123123,
            Intent(context, FirstOpenActivity::class.java).apply {
                action = Intent.ACTION_VIEW
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtras(
                    bundleOf(
                        Constants.KEY_OPEN_FROM to Constants.OPEN_FROM_NOTIFY_PINNED
                    )
                )
            },
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val pendingIntentEarthView = PendingIntent.getActivity(
            context,
            1231234,
            Intent(context, FirstOpenActivity::class.java).apply {
                action = Intent.ACTION_VIEW
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtras(
                    bundleOf(
                        Constants.KEY_OPEN_FROM to Constants.OPEN_EARTH_VIEW_FROM_NOTIFY_PINNED
                    )
                )
            },
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val pendingIntentCamera = PendingIntent.getActivity(
            context,
            1231253,
            Intent(context, FirstOpenActivity::class.java).apply {
                action = Intent.ACTION_VIEW
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtras(
                    bundleOf(
                        Constants.KEY_OPEN_FROM to Constants.OPEN_CAMERA_FROM_NOTIFY_PINNED
                    )
                )
            },
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val pendingIntentGrid = PendingIntent.getActivity(
            context,
            1231236,
            Intent(context, FirstOpenActivity::class.java).apply {
                action = Intent.ACTION_VIEW
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtras(
                    bundleOf(
                        Constants.KEY_OPEN_FROM to Constants.OPEN_GRID_FROM_NOTIFY_PINNED
                    )
                )
            },
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val pendingIntentRoute = PendingIntent.getActivity(
            context,
            1231237,
            Intent(context, FirstOpenActivity::class.java).apply {
                action = Intent.ACTION_VIEW
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtras(
                    bundleOf(
                        Constants.KEY_OPEN_FROM to Constants.OPEN_ROUTE_FROM_NOTIFY_PINNED
                    )
                )
            },
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val customView = RemoteViews(context.packageName, R.layout.layout_notification_pinned).apply {
            setOnClickPendingIntent(R.id.llEarthView, pendingIntentEarthView)
            setOnClickPendingIntent(R.id.llCamera, pendingIntentCamera)
            setOnClickPendingIntent(R.id.llGrid, pendingIntentGrid)
            setOnClickPendingIntent(R.id.llRoute, pendingIntentRoute)
        }

        val customBigView = RemoteViews(context.packageName, R.layout.layout_notification_pinned_expand).apply {
            setOnClickPendingIntent(R.id.llEarthView, pendingIntentEarthView)
            setOnClickPendingIntent(R.id.llCamera, pendingIntentCamera)
            setOnClickPendingIntent(R.id.llGrid, pendingIntentGrid)
            setOnClickPendingIntent(R.id.llRoute, pendingIntentRoute)
        }


        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_DAILY)
            .setSmallIcon(R.drawable.img_camera)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(customView)
            .setCustomBigContentView(customBigView)
            .setContentIntent(pendingIntentNone)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSilent(true)
            .setGroup(context.packageName)

        with(NotificationManagerCompat.from(context)) {
            notify(PINNED_NOTIFICATION_ID, notificationBuilder.build())
        }
    }

        @SuppressLint("MissingPermission")
    fun showKillAppNotification() {
        if (!app.allowNotification) {
            return
        }
        val pendingIntent = PendingIntent.getActivity(
            app,
            111222333,
            Intent(app, FirstOpenActivity::class.java).apply {
                action = Intent.ACTION_VIEW
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            },
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notification = NotificationCompat.Builder(app, CHANNEL_KILL_APP)
            .setContentTitle(app.getString(R.string.kill_app_notification_title))
            .setContentText(app.getString(R.string.kill_app_notification_content))
            .setSmallIcon(R.drawable.notification_ic_p)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setAutoCancel(false)
            .build()
        NotificationManagerCompat.from(app).notify(111222333, notification)
    }
}