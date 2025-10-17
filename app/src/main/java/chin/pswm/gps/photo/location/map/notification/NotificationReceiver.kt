package chin.pswm.gps.photo.location.map.notification

//import com.demo.parner.R
//import com.example.demo.common.Constants
//import com.example.demo.domain.enumClas.DailyNotificationType
//import com.example.demo.domain.permission.PermissionManager.Companion.allowNotification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import chin.pswm.gps.photo.location.map.MyApplication
import chin.pswm.gps.photo.location.map.ui.theme.PermissionManager.Companion.allowNotification
import chin.pswm.gps.photo.location.map_debug.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class NotificationReceiver : BroadcastReceiver() {

    //    private val notificationManager: NotificationManager by inject(clazz = NotificationManager::class.java)
    private lateinit var notificationManager: NotificationManager

    override fun onReceive(context: Context?, intent: Intent?) {
        notificationManager = NotificationManager(MyApplication.instance);
        if (context?.allowNotification == true)
            CoroutineScope(Dispatchers.IO + CoroutineExceptionHandler { _, _ ->

            }).launch {
                if (DailyNotificationType.entries.isNotEmpty()) {
                    val itemRandom = DailyNotificationType.entries.random()
                    notificationManager.setNotification(
                        title = context.getString(itemRandom.title),
                        content = context.getString(itemRandom.description),
                        notificationId = NotificationManager.Companion.DAILY_NOTIFICATION,
                        type = 1,
                        icon = R.drawable.img_camera
                    )
                }
            }

        notificationManager.scheduleReminder()
    }
}