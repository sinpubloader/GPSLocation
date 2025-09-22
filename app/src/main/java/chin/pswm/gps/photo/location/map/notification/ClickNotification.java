package chin.pswm.gps.photo.location.map.notification;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import chin.pswm.gps.photo.location.map.activity.SplashActivity;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationOpenedResult;
import com.onesignal.OneSignal;
import org.json.JSONObject;


public class ClickNotification implements OneSignal.OSNotificationOpenedHandler {
    Context context;
    SharedPreferences.Editor myEdit;
    SharedPreferences sharedPreferences;

    public ClickNotification(Context context) {
        this.context = context;
    }

    @Override 
    public void notificationOpened(OSNotificationOpenedResult oSNotificationOpenedResult) {
        OSNotification notification = oSNotificationOpenedResult.getNotification();
        notification.mutableCopy();
        JSONObject additionalData = notification.getAdditionalData();
        if (additionalData != null) {
            additionalData.optString("small_layout", null);
            additionalData.optString("single_layout", null);
            additionalData.optString("large_layout", null);
            String optString = additionalData.optString("type", null);
            String optString2 = additionalData.optString("link", null);
            Log.e("getmetype", "The type is " + optString + " ");
            Log.e("getmelink", "The link is " + optString2 + " ");
            SharedPreferences sharedPreferences = this.context.getSharedPreferences("bdcPref", 0);
            this.sharedPreferences = sharedPreferences;
            this.myEdit = sharedPreferences.edit();
            this.sharedPreferences.getString("colorfulldayfulls_notiWebactivity", "11");
            if (StringUtils.isNotEmpty(optString)) {
                if (optString.equalsIgnoreCase("1")) {
                    if (StringUtils.isNotEmpty(optString2)) {
                        this.context.startActivity(new Intent(this.context, WebActivity.class).putExtra("type", "1").putExtra("link", optString2).addFlags(536870912).addFlags(67108864).addFlags(268435456));
                    } else {
                        this.context.startActivity(new Intent(this.context, SplashActivity.class).putExtra("type", "1").addFlags(536870912).addFlags(67108864).addFlags(268435456));
                    }
                } else if (optString.equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D)) {
                    if (StringUtils.isNotEmpty(optString2)) {
                        this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(optString2)).setFlags(268435456));
                    } else {
                        this.context.startActivity(new Intent(this.context, SplashActivity.class).putExtra("type", ExifInterface.GPS_MEASUREMENT_2D).addFlags(536870912).addFlags(67108864).addFlags(268435456));
                    }
                } else if (optString.equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_3D)) {
                    if (StringUtils.isNotEmpty(optString2)) {
                        this.context.startActivity(new Intent(this.context, WebActivity.class).putExtra("type", ExifInterface.GPS_MEASUREMENT_3D).putExtra("link", optString2).addFlags(536870912).addFlags(67108864).addFlags(268435456));
                    } else {
                        this.context.startActivity(new Intent(this.context, SplashActivity.class).putExtra("type", ExifInterface.GPS_MEASUREMENT_3D).addFlags(536870912).addFlags(67108864).addFlags(268435456));
                    }
                }
            } else {
                this.context.startActivity(new Intent(this.context, SplashActivity.class).putExtra("type", ExifInterface.GPS_MEASUREMENT_3D).addFlags(536870912).addFlags(67108864).addFlags(268435456));
            }
        } else {
            this.context.startActivity(new Intent(this.context, SplashActivity.class).putExtra("type", ExifInterface.GPS_MEASUREMENT_3D).addFlags(536870912).addFlags(67108864).addFlags(268435456));
        }
        Log.d("TAG", "notificationOpened:2 " + oSNotificationOpenedResult.getNotification().getTitle());
    }
}
