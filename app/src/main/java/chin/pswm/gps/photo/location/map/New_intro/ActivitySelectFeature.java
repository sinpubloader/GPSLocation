package chin.pswm.gps.photo.location.map.New_intro;


import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import chin.pswm.gps.photo.location.map.activity.PermissionActivity;
import chin.pswm.gps.photo.location.map.activity.StartActivity;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.utils.PermissionUtils;
import chin.pswm.gps.photo.location.map_debug.R;


public class ActivitySelectFeature extends AppCompatActivity {

    RelativeLayout llEarthView, llTime;
    ImageView ivEarthview, ivTimeView;
    PermissionUtils permissionUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.setLocale(ActivitySelectFeature.this, SharedHelper.getString(ActivitySelectFeature.this, "lang_key", ""));
        setContentView(R.layout.activity_select_feature);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        this.permissionUtils = new PermissionUtils(this);
        llEarthView = findViewById(R.id.llEarthView);
        llTime = findViewById(R.id.llTime);
        ivEarthview = findViewById(R.id.ivEarthview);
        ivTimeView = findViewById(R.id.ivTimeView);

        setImgBg(0);
        llEarthView.setOnClickListener(view -> setImgBg(0));
        llTime.setOnClickListener(view -> setImgBg(1));
        findViewById(R.id.start).setOnClickListener(view -> startMainActivity());

//        updateFrameLayout(viewPager.getCurrentItem());
    }

    private void setImgBg(int selectPos) {
        llEarthView.setBackgroundResource(R.drawable.permission_detail);
        llTime.setBackgroundResource(R.drawable.permission_detail);
        ivEarthview.setImageResource(R.drawable.ic_unselect_round);
        ivTimeView.setImageResource(R.drawable.ic_unselect_round);

        if (selectPos == 0) {
            llEarthView.setBackgroundResource(R.drawable.permission_detail_allow);
            ivEarthview.setImageResource(R.drawable.ic_select_round);
        } else {
            llTime.setBackgroundResource(R.drawable.permission_detail_allow);
            ivTimeView.setImageResource(R.drawable.ic_select_round);
        }

    }

    public final void startMainActivity() {
        PermissionUtils permissionUtils = this.permissionUtils;
        if (permissionUtils.checkPermission(permissionUtils.allPermissions)) {
            Intent intent = new Intent(this, StartActivity.class);
            intent.setFlags(268435456);
            startActivity(intent);
            finish();
            return;
        }
        Intent intent2 = new Intent(this, PermissionActivity.class);
        intent2.setFlags(268435456);
        startActivity(intent2);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}



