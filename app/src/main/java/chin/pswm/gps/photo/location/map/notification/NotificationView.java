package chin.pswm.gps.photo.location.map.notification;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import chin.pswm.gps.photo.location.map_debug.R;
import com.onesignal.OSMutableNotification;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationReceivedEvent;
import com.onesignal.OneSignal;
import java.net.URL;
import org.json.JSONObject;


public class NotificationView implements OneSignal.OSNotificationWillShowInForegroundHandler {
    Context context;
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("VIEW_VISIBLE")) {
                NotificationView.this.remoteViewsSmall.setViewVisibility(R.id.body, 0);
                NotificationView.this.remoteViewsSmall.setViewVisibility(R.id.view_image, 8);
                NotificationView.this.remoteViewsSmall.setViewVisibility(R.id.view_image_two, 0);
                Toast.makeText(context, "Done", 0).show();
            } else if (action.equals("VIEW_HIDE")) {
                NotificationView.this.remoteViewsSmall.setViewVisibility(R.id.body, 8);
                NotificationView.this.remoteViewsSmall.setViewVisibility(R.id.view_image_two, 8);
                NotificationView.this.remoteViewsSmall.setViewVisibility(R.id.view_image, 0);
                Toast.makeText(context, "Done Demo", 0).show();
            }
        }
    };
    RemoteViews remoteViewsLarge;
    RemoteViews remoteViewsSingle;
    RemoteViews remoteViewsSmall;

    public NotificationView(Context context) {
        this.context = context;
    }

    @Override
    public void notificationWillShowInForeground(OSNotificationReceivedEvent oSNotificationReceivedEvent) {
        OSNotification notification = oSNotificationReceivedEvent.getNotification();
        OSMutableNotification mutableCopy = notification.mutableCopy();
        JSONObject additionalData = notification.getAdditionalData();
        if (additionalData != null) {
            String optString = additionalData.optString("small_layout", null);
            String optString2 = additionalData.optString("single_layout", null);
            String optString3 = additionalData.optString("large_layout", null);
            if (optString != null) {
                RemoteViews remoteViews = new RemoteViews(this.context.getPackageName(), (int) R.layout.notification_layout_one);
                this.remoteViewsSmall = remoteViews;
                remoteViews.setImageViewBitmap(R.id.icon, getBitmapFromURL(optString));
                this.remoteViewsSmall.setTextViewText(R.id.title, notification.getTitle());
                this.remoteViewsSmall.setTextViewText(R.id.message, notification.getBody());
                Intent intent = new Intent("VIEW_VISIBLE");
                intent.putExtra("toastMessage", "close_service");
                this.remoteViewsSmall.setOnClickPendingIntent(R.id.view_image, PendingIntent.getBroadcast(this.context, 0, intent, 67108864));
                Intent intent2 = new Intent("VIEW_HIDE");
                intent2.putExtra("toastMessage", "screenshot_service");
                this.remoteViewsSmall.setOnClickPendingIntent(R.id.view_image_two, PendingIntent.getBroadcast(this.context, 1, intent2, 67108864));
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("VIEW_VISIBLE");
                intentFilter.addAction("VIEW_HIDE");
                this.context.registerReceiver(this.receiver, intentFilter);
                mutableCopy.setExtender(new NotificationCompat.Extender() {
                    @Override
                    public final NotificationCompat.Builder extend(NotificationCompat.Builder builder) {
                        return NotificationView.this.m160xd0bc9131(builder);
                    }
                });
                oSNotificationReceivedEvent.complete(mutableCopy);
                return;
            } else if (optString2 != null) {
                RemoteViews remoteViews2 = new RemoteViews(this.context.getPackageName(), (int) R.layout.notification_layout_two);
                this.remoteViewsSingle = remoteViews2;
                remoteViews2.setImageViewBitmap(R.id.icon, getBitmapFromURL(optString2));
                this.remoteViewsSingle.setTextViewText(R.id.title, notification.getTitle());
                this.remoteViewsSingle.setTextViewText(R.id.message, notification.getBody());
                mutableCopy.setExtender(new NotificationCompat.Extender() {
                    @Override
                    public final NotificationCompat.Builder extend(NotificationCompat.Builder builder) {
                        return NotificationView.this.m161x4f1d9510(builder);
                    }
                });
                oSNotificationReceivedEvent.complete(mutableCopy);
                return;
            } else if (optString3 != null) {
                RemoteViews remoteViews3 = new RemoteViews(this.context.getPackageName(), (int) R.layout.notification_layout_three);
                this.remoteViewsLarge = remoteViews3;
                remoteViews3.setImageViewBitmap(R.id.icon, getBitmapFromURL(optString3));
                this.remoteViewsLarge.setImageViewBitmap(R.id.large_icon, getBitmapFromURL(notification.getLargeIcon()));
                this.remoteViewsLarge.setTextViewText(R.id.title, notification.getTitle());
                this.remoteViewsLarge.setTextViewText(R.id.message, notification.getBody());
                mutableCopy.setExtender(new NotificationCompat.Extender() {
                    @Override
                    public final NotificationCompat.Builder extend(NotificationCompat.Builder builder) {
                        return NotificationView.this.m162xcd7e98ef(builder);
                    }
                });
                oSNotificationReceivedEvent.complete(mutableCopy);
                return;
            } else {
                return;
            }
        }
        oSNotificationReceivedEvent.complete(null);
    }


    public  NotificationCompat.Builder m160xd0bc9131(NotificationCompat.Builder builder) {
        return builder.setCustomContentView(this.remoteViewsSmall);
    }


    public  NotificationCompat.Builder m161x4f1d9510(NotificationCompat.Builder builder) {
        return builder.setCustomContentView(this.remoteViewsSingle);
    }


    public  NotificationCompat.Builder m162xcd7e98ef(NotificationCompat.Builder builder) {
        return builder.setCustomContentView(this.remoteViewsLarge);
    }

    private static Bitmap getBitmapFromURL(String str) {
        try {
            return BitmapFactory.decodeStream(new URL(str).openConnection().getInputStream());
        } catch (Throwable unused) {
            Log.i("OneSignalExample", "COULD NOT DOWNLOAD IMAGE");
            return null;
        }
    }
}
