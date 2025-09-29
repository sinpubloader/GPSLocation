package chin.pswm.gps.photo.location.map.utils;

import android.app.Activity;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
@SuppressWarnings("all")

public class PermissionUtils {
    public Activity activity;
    public String[] allPermissions;
    public String[] permissionsStorage;
    public int CAMERA_PERMISSION = 123;
    public int LOCATION_PERMISSION = 456;
    public int PHOTO_VIDEO_PERMISSION = 789;
    public int NOTIFICATION_PERMISSION = 147;
    public final int ALL_PERMISSIONS_REQUEST_CODE = 500;

    public String[] permissionsCamera = {"android.permission.CAMERA", "android.permission.RECORD_AUDIO"};
    public String[] permissionsLocation = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    public String[] permissionsNotification = {"android.permission.POST_NOTIFICATIONS"};

    public PermissionUtils(Activity activity) {
        this.activity = activity;
        if (Build.VERSION.SDK_INT >= 33) {
            this.permissionsStorage = new String[]{"android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO"};
            this.allPermissions = new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.POST_NOTIFICATIONS", "android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO"};
            return;
        }
        this.permissionsStorage = new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
        this.allPermissions = new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    }

    public boolean isNotificationRequired() {
        return Build.VERSION.SDK_INT >= 33;
    }

    public boolean checkPermission(String[] strArr) {
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(this.activity, str) != 0) {
                Log.e("TAG", "checkPermission: " + str);
                return false;
            }
        }
        return true;
    }

    public void callPermission(String[] strArr, int i) {
        ActivityCompat.requestPermissions(this.activity, strArr, i);
    }
}
