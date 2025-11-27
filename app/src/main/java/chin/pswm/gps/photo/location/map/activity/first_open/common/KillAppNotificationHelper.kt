package chin.pswm.gps.photo.location.map.activity.first_open.common

import android.app.Activity
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs
import chin.pswm.gps.photo.location.map.notification.NotificationManager

object KillAppNotificationHelper {

    fun postIfNotShowingAds(activity: Activity) {
        val prefs = runCatching { Prefs.INSTANCE }.getOrNull() ?: return
        if (!prefs.firstOpen) return

        val adsManager = runCatching { AdsManager.INSTANCE }.getOrNull() ?: return
        if (adsManager.isShowingAds) return

        val notificationManager = runCatching { NotificationManager.INSTANCE }.getOrNull() ?: return
        notificationManager.showKillAppNotification()
    }
}