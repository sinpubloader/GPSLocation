package chin.pswm.gps.photo.location.map.activity;

import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showUserInterDataBack;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import chin.pswm.gps.photo.location.map.AllKeyHub;
import chin.pswm.gps.photo.location.map.MyApplication;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map.utils.PermissionUtils;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivityPermissionBinding;

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
        this.binding.notificationRl.setOnClickListener(new View.OnClickListener() {
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
        PermissionUtils permissionUtils = this.permissionUtils;
        if (permissionUtils.checkPermission(permissionUtils.permissionsNotification)) {
            return;
        }
        PermissionUtils permissionUtils2 = this.permissionUtils;
        permissionUtils2.callPermission(permissionUtils2.permissionsNotification, this.permissionUtils.NOTIFICATION_PERMISSION);
    }


    public void m107x7120f767(View view) {
        PermissionUtils permissionUtils = this.permissionUtils;
        if (permissionUtils.checkPermission(permissionUtils.allPermissions)) {
            startActivity(new Intent(this, StartActivity.class));
            finish();
            return;
        }
        permissionUtils.callPermission(permissionUtils.allPermissions, permissionUtils.ALL_PERMISSIONS_REQUEST_CODE);

        Toast.makeText(this, "Please Allow Permission", 0).show();
    }

    private void setImgBg() {
        this.binding.notificationRl.setVisibility(this.permissionUtils.isNotificationRequired() ? 0 : 8);
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
            this.binding.notificationRl.setBackgroundResource(R.drawable.permission_detail_allow);
            return;
        }
        this.binding.notificationSelect.setVisibility(4);
//        this.binding.tt4.setTextColor(Color.parseColor("#000000"));
        this.binding.notificationRl.setBackgroundResource(R.drawable.permission_detail);
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        setImgBg();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemNavigationBar();
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
