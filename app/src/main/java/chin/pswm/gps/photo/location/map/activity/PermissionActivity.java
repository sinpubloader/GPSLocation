package chin.pswm.gps.photo.location.map.activity;

import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showUserInterDataBack;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.core.app.ActivityCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import chin.pswm.gps.photo.location.map.AllKeyHub;
import chin.pswm.gps.photo.location.map.MyApplication;
import chin.pswm.gps.photo.location.map.ads.AdsManager;
import chin.pswm.gps.photo.location.map.ads.adunit.common.AdLoadError;
import chin.pswm.gps.photo.location.map.ads.adunit.common.AdsStatus;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.New_first_languagesselect;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map.utils.PermissionUtils;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivityPermissionBinding;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

@SuppressWarnings("all")

public class PermissionActivity extends BaseActivity {
    ActivityPermissionBinding binding;
    PermissionUtils permissionUtils;

    @Override
    public void onCreate(Bundle bundle) {
        LanguageManager.setLocale(PermissionActivity.this, SharedHelper.getString(PermissionActivity.this, "lang_key", ""));

        super.onCreate(bundle);
        ActivityPermissionBinding inflate = ActivityPermissionBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        AdsManager.INSTANCE.getInterPermission().loadAd(PermissionActivity.this);
        this.permissionUtils = new PermissionUtils(this);
        initSocketConnection(this, true, true);
        setData();
        MyApplication.sendEvent("Permision_Screen", "");
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
            }
        });
    }


    private void setData() {
        setImgBg();
        this.binding.rlNotiView.setVisibility(this.permissionUtils.isNotificationRequired() && !permissionUtils.checkPermission(permissionUtils.permissionsNotification) ? 0 : 8);
        this.binding.cameraRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PermissionActivity.this.m103x202738eb(view);
            }
        });
        this.binding.locationRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PermissionActivity.this.m104xb465a88a(view);
            }
        });
        this.binding.photoVideoRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PermissionActivity.this.m105x48a41829(view);
            }
        });
        this.binding.rlNotiView.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PermissionActivity.this.m106xdce287c8(view);
            }
        });
        this.binding.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PermissionActivity.this.m107x7120f767(view);
            }
        });
    }


    public void m103x202738eb(View view) {
        PermissionUtils permissionUtils = this.permissionUtils;
        if (permissionUtils.checkPermission(permissionUtils.permissionsCamera)) {
            return;
        }
        PermissionUtils permissionUtils2 = this.permissionUtils;
        permissionUtils2.callPermission(permissionUtils2.permissionsCamera, this.permissionUtils.CAMERA_PERMISSION);
    }


    public void m104xb465a88a(View view) {
        PermissionUtils permissionUtils = this.permissionUtils;
        if (permissionUtils.checkPermission(permissionUtils.permissionsLocation)) {
            return;
        }
        PermissionUtils permissionUtils2 = this.permissionUtils;
        permissionUtils2.callPermission(permissionUtils2.permissionsLocation, this.permissionUtils.LOCATION_PERMISSION);
    }


    public void m105x48a41829(View view) {
        PermissionUtils permissionUtils = this.permissionUtils;
        if (permissionUtils.checkPermission(permissionUtils.permissionsStorage)) {
            return;
        }
        PermissionUtils permissionUtils2 = this.permissionUtils;
        permissionUtils2.callPermission(permissionUtils2.permissionsStorage, this.permissionUtils.PHOTO_VIDEO_PERMISSION);
    }


    public void m106xdce287c8(View view) {
        /*PermissionUtils permissionUtils = this.permissionUtils;
        if (permissionUtils.checkPermission(permissionUtils.permissionsNotification)) {
            return;
        }
        PermissionUtils permissionUtils2 = this.permissionUtils;
        permissionUtils2.callPermission(permissionUtils2.permissionsNotification, this.permissionUtils.NOTIFICATION_PERMISSION);*/
        if (permissionUtils.checkPermission(permissionUtils.permissionsNotification)) {
            return;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(PermissionActivity.this, Manifest.permission.POST_NOTIFICATIONS)) {
            new AlertDialog.Builder(PermissionActivity.this)
                    .setTitle(getString(R.string.notification_permission_require))
                    .setMessage(getString(R.string.noti_perm_detail))
                    .setPositiveButton(getString(R.string.allow), (dialog, which) -> ActivityCompat.requestPermissions(
                            PermissionActivity.this,
                            new String[]{Manifest.permission.POST_NOTIFICATIONS},
                            permissionUtils.NOTIFICATION_PERMISSION
                    ))
                    .setNegativeButton(getString(R.string.cancel), null)
                    .show();
        } else {
            new AlertDialog.Builder(PermissionActivity.this)
                    .setTitle(getString(R.string.enable_notifications))
                    .setMessage(getString(R.string.noti_perm_detail1))
                    .setPositiveButton(getString(R.string.open_settings), (dialog, which) -> openAppSettings(PermissionActivity.this))
                    .setNegativeButton(getString(R.string.cancel), null)
                    .show();
        }
    }

    private void openAppSettings(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivity(intent);
    }

    public void m107x7120f767(View view) {
        PermissionUtils permissionUtils = this.permissionUtils;
        if (permissionUtils.checkPermission(permissionUtils.allPermissions)) {
            checkAdStatus();
            return;
        }
        permissionUtils.callPermission(permissionUtils.allPermissions, permissionUtils.ALL_PERMISSIONS_REQUEST_CODE);
//        Toast.makeText(this, "Please Allow Permission", 0).show();
    }

    public void checkAdStatus() {
        AdsStatus adsStatus = AdsManager.INSTANCE.getInterPermission().getStatusFlow().getValue();
        if (adsStatus == AdsStatus.SUCCESS) {
            AdsManager.INSTANCE.getInterPermission().show(PermissionActivity.this, true, false, new Function0<Unit>() {
                @Override
                public Unit invoke() {
                    return null;
                }
            }, new Function1<AdLoadError, Unit>() {
                @Override
                public Unit invoke(AdLoadError adLoadError) {
                    startMainActivity();
                    return null;
                }
            }, new Function0<Unit>() {
                @Override
                public Unit invoke() {
                    return null;
                }
            }, new Function0<Unit>() {
                @Override
                public Unit invoke() {
                    startMainActivity();
                    return null;
                }
            });
        } else if (adsStatus == AdsStatus.FAIL) {
            startMainActivity();
        }

    }

    public void startMainActivity() {
        startActivity(new Intent(this, StartActivity.class));
        finish();
    }

    private void setImgBg() {
        PermissionUtils permissionUtils = this.permissionUtils;
        if (permissionUtils.checkPermission(permissionUtils.permissionsCamera)) {
            this.binding.cameraSelect.setVisibility(0);
//            this.binding.tt1.setTextColor(Color.parseColor("#000000"));
            this.binding.cameraRl.setBackgroundResource(R.drawable.permission_detail_allow);
        } else {
            this.binding.cameraSelect.setVisibility(4);
//            this.binding.tt1.setTextColor(Color.parseColor("#000000"));
            this.binding.cameraRl.setBackgroundResource(R.drawable.permission_detail);
        }
        PermissionUtils permissionUtils2 = this.permissionUtils;
        if (permissionUtils2.checkPermission(permissionUtils2.permissionsLocation)) {
            this.binding.locationSelect.setVisibility(0);
//            this.binding.tt2.setTextColor(Color.parseColor("#000000"));
            this.binding.locationRl.setBackgroundResource(R.drawable.permission_detail_allow);
        } else {
            this.binding.locationSelect.setVisibility(4);
//            this.binding.tt2.setTextColor(Color.parseColor("#000000"));
            this.binding.locationRl.setBackgroundResource(R.drawable.permission_detail);
        }
        PermissionUtils permissionUtils3 = this.permissionUtils;
        if (permissionUtils3.checkPermission(permissionUtils3.permissionsStorage)) {
            this.binding.photoVideoSelect.setVisibility(0);
//            this.binding.tt3.setTextColor(Color.parseColor("#000000"));
            this.binding.photoVideoRl.setBackgroundResource(R.drawable.permission_detail_allow);
        } else {
            this.binding.photoVideoSelect.setVisibility(4);
//            this.binding.tt3.setTextColor(Color.parseColor("#000000"));
            this.binding.photoVideoRl.setBackgroundResource(R.drawable.permission_detail);
        }
        PermissionUtils permissionUtils4 = this.permissionUtils;
        if (permissionUtils4.checkPermission(permissionUtils4.permissionsNotification)) {
            this.binding.notificationSelect.setVisibility(0);
//            this.binding.tt4.setTextColor(Color.parseColor("#000000"));
            this.binding.rlNotiView.setBackgroundResource(R.drawable.permission_detail_allow);
            return;
        }
        this.binding.notificationSelect.setVisibility(4);
//        this.binding.tt4.setTextColor(Color.parseColor("#000000"));
        this.binding.rlNotiView.setBackgroundResource(R.drawable.permission_detail);
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        setImgBg();
        if (i == permissionUtils.NOTIFICATION_PERMISSION) {
            if (iArr.length > 0 && iArr[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(this, getString(R.string.noti_perm_required), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemNavigationBar();
        setImgBg();
    }

    private void hideSystemNavigationBar() {
        try {
            Window window = this.getWindow();
            WindowCompat.setDecorFitsSystemWindows(window, true);
            WindowInsetsControllerCompat windowCompat = WindowCompat.getInsetsController(window, window.getDecorView());
            windowCompat.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
            windowCompat.setAppearanceLightNavigationBars(false);
            windowCompat.hide(WindowInsetsCompat.Type.navigationBars());

        } catch (Exception e) {

        }
    }
}
