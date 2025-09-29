package chin.pswm.gps.photo.location.map.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

import java.util.Locale;

import chin.pswm.gps.photo.location.map.ads.AdsManager;
import chin.pswm.gps.photo.location.map.compose.splash.ComposeSplashKt;
import chin.pswm.gps.photo.location.map.compose.splash.ComposeSplashState;
import chin.pswm.gps.photo.location.map.languegess.ActivityPrivacyPolicy_New;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map.utils.PermissionUtils;
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
        ComposeSplashKt.setMyContent(binding.composeView);
        this.permissionUtils = new PermissionUtils(this);
        boolean privacyScreenShown = SharedHelper.getBoolean(getApplicationContext(), "privacy_screen_shown", false);

        AdsManager.INSTANCE.requestUMP(SplashActivity.this, true, true);
        AdsManager.INSTANCE.getConsentFinished().observe(this, finished -> {
                    if (!privacyScreenShown) {
                        showPrivacyScreen();
                    } else {
                        checkMain();
                    }
                }
        );
    }

    private void showPrivacyScreen() {
        startActivity(new Intent(SplashActivity.this, ActivityPrivacyPolicy_New.class));
        finish();
    }

    public final void checkMain() {
        String selectedLanguage = SharedHelper.getString(getApplicationContext(), "lang_key", "");
        boolean displayIntroEveryTime = SharedHelper.getBoolean(getApplicationContext(), "display_intro_everytime", false);
        if (!selectedLanguage.isEmpty() && displayIntroEveryTime) {
            setLanguage(selectedLanguage);
            gotoLoginActivity();
            finish();
        } else {
            Boolean finishFO = SharedHelper.getBoolean(getApplicationContext(), "finis_fo", false);
            if (selectedLanguage.isEmpty()) {
                AdsManager.INSTANCE.getNativeLanguage().loadAd(SplashActivity.this);
                ComposeSplashState.INSTANCE.getClickedAgree().setValue(true);
            } else if (!finishFO) {
                AdsManager.INSTANCE.getNativeOnboard1().loadAd(SplashActivity.this);
                AdsManager.INSTANCE.getNativeOnboard3().loadAd(SplashActivity.this);
                ComposeSplashState.INSTANCE.getClickedOnboard().setValue(true);
            } else {
                ComposeSplashState.INSTANCE.getClickedMain().setValue(true);
            }
        }
    }

    private void gotoLoginActivity() {
        if (SplashActivity.this.permissionUtils.checkPermission(SplashActivity.this.permissionUtils.allPermissions)) {
            startActivity(new Intent(this, PermissionActivity.class));
        } else {
            startActivity(new Intent(this, StartActivity.class));
        }
        finish();
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
}
