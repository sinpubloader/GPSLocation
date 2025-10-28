package chin.pswm.gps.photo.location.map.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import java.util.Locale;

import chin.pswm.gps.photo.location.map.ads.AdsManager;
import chin.pswm.gps.photo.location.map.compose.splash.ComposeSplashKt;
import chin.pswm.gps.photo.location.map.compose.splash.ComposeSplashState;
import chin.pswm.gps.photo.location.map.languegess.ActivityPrivacyPolicy_New;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map.utils.PermissionUtils;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivitySplashBinding;


public class SplashActivity extends BaseActivity {
    ActivitySplashBinding binding;
    PermissionUtils permissionUtils;

    @Override
    public void onCreate(Bundle bundle) {
        LanguageManager.setLocale(SplashActivity.this, SharedHelper.getString(SplashActivity.this, "lang_key", ""));

        super.onCreate(bundle);
        ActivitySplashBinding inflate = ActivitySplashBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        if (!isNetworkConnected()) {
            noNetworkDialog();
            return;
        }
        this.permissionUtils = new PermissionUtils(this);
        /*if (permissionUtils.isNotificationRequired()) {
            if (permissionUtils.checkPermission(permissionUtils.permissionsNotification)) {
                setupData();
            } else {
                permissionUtils.callPermission(permissionUtils.permissionsNotification, this.permissionUtils.NOTIFICATION_PERMISSION);
            }
        } else {
            setupData();
        }*/
        setupData();
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
            }
        });
    }

    private void setupData() {
        ComposeSplashKt.setMyContent(binding.composeView, binding.composeViewBanner);

        AdsManager.INSTANCE.requestUMP(SplashActivity.this, true, true);
        AdsManager.INSTANCE.getConsentFinished().observe(this, finished -> {
            checkMain();
        });
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == permissionUtils.NOTIFICATION_PERMISSION) {
            if (iArr.length > 0 && iArr[0] == PackageManager.PERMISSION_GRANTED) {
                setupData();
            } else {
                setupData();
                Toast.makeText(this, getString(R.string.noti_perm_required), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public void noNetworkDialog() {
        final Dialog dialog = new Dialog(SplashActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.newdail);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        ((TextView) dialog.findViewById(R.id.text_dialog)).setText(getResources().getString(R.string.no_internet));
        ((TextView) dialog.findViewById(R.id.iv_yes)).setText(getResources().getString(R.string.try_again));
        ((TextView) dialog.findViewById(R.id.iv_yes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkConnected()) {
                    dialog.dismiss();
                    new Handler().postDelayed(() -> setupData(),2000);
                }
            }
        });
        dialog.findViewById(R.id.iv_no).setVisibility(View.GONE);
        ((TextView) dialog.findViewById(R.id.iv_no)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public final void checkMain() {
        String selectedLanguage = SharedHelper.getString(getApplicationContext(), "lang_key", "");

        Boolean finishFO = SharedHelper.getBoolean(getApplicationContext(), "finis_fo", false);
        boolean privacyScreenShown = SharedHelper.getBoolean(getApplicationContext(), "privacy_screen_shown", false);
        if (selectedLanguage.isEmpty()) {
            AdsManager.INSTANCE.getNativeLanguage().loadAd(SplashActivity.this);
            ComposeSplashState.INSTANCE.getClickedAgree().setValue(true);
        } else if (!finishFO) {
            AdsManager.INSTANCE.getNativeOnboard1().loadAd(SplashActivity.this);
            AdsManager.INSTANCE.getNativeFSN().loadAd(SplashActivity.this);
            AdsManager.INSTANCE.getNativeOnboard3().loadAd(SplashActivity.this);
            ComposeSplashState.INSTANCE.getClickedOnboard().setValue(true);
        } else if (!privacyScreenShown) {
            ComposeSplashState.INSTANCE.getClickedPrivacy().setValue(true);
        } else {
            ComposeSplashState.INSTANCE.getClickedMain().setValue(true);
        }
    }

    private void setLanguage(String localeCode) {
        try {
            if (!localeCode.isEmpty()) {
                Resources resources = getResources();
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                Configuration configuration = resources.getConfiguration();
                configuration.setLocale(new Locale(localeCode.toLowerCase()));
                resources.updateConfiguration(configuration, displayMetrics);
            } else {
                Resources resources = getResources();
                DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                Configuration configuration = resources.getConfiguration();
                configuration.setLocale(Locale.ENGLISH);
                resources.updateConfiguration(configuration, displayMetrics);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
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
