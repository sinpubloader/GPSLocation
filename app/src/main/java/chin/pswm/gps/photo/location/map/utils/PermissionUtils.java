package chin.pswm.gps.photo.location.map.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import chin.pswm.gps.photo.location.map_debug.R;

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

    public String[] permStorage;
    public String[] permOnlyStorage;
    public String[] permCamera = {"android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};

    public PermissionUtils(Activity activity) {
        this.activity = activity;
        if (Build.VERSION.SDK_INT >= 33) {
            this.permissionsStorage = new String[]{"android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO"};
            this.allPermissions = new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.POST_NOTIFICATIONS", "android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO"};

            this.permStorage = new String[]{"android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
            this.permOnlyStorage = new String[]{"android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO"};

            return;
        }
        this.permissionsStorage = new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
        this.allPermissions = new String[]{"android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};

        this.permStorage = new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
        this.permOnlyStorage = new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
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

    public boolean checkPermissionn(Activity context, String[] strArr, boolean isShowDialog) {
        boolean isAnyPermNotALlow = false;
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(this.activity, str) != 0) {
//                if (ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.POST_NOTIFICATIONS)) {
//                    new AlertDialog.Builder(context)
//                            .setTitle(context.getString(R.string.permission_require))
//                            .setMessage(context.getString(R.string.perm_detail))
//                            .setPositiveButton(context.getString(R.string.allow), (dialog, which) -> ActivityCompat.requestPermissions(
//                                    context,
//                                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
//                                    NOTIFICATION_PERMISSION
//                            ))
//                            .setNegativeButton(context.getString(R.string.cancel), null)
//                            .show();
//                } else {
                if (isShowDialog) {
                    new AlertDialog.Builder(context)
                            .setCancelable(false)
                            .setTitle(context.getString(R.string.permission_require))
                            .setMessage(context.getString(R.string.perm_detail1))
                            .setPositiveButton(context.getString(R.string.open_settings), (dialog, which) -> openAppSettings(context))
                            .setNegativeButton(context.getString(R.string.cancel), (dialogInterface, i) -> {context.finish();})
                            .show();
                }
//                }
                Log.d("Permission_Issue"," show dialog ");
                isAnyPermNotALlow = true;
                return false;
            }
            if (isAnyPermNotALlow)
                break;
        }
        return true;
    }

    private void openAppSettings(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivity(intent);
    }

    public void callPermission(String[] strArr, int i) {
        ActivityCompat.requestPermissions(this.activity, strArr, i);
    }
}
