package chin.pswm.gps.photo.location.map.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

import chin.pswm.gps.photo.location.map.New_intro.New_IntroActivity;
import chin.pswm.gps.photo.location.map.activity.SplashActivity;
import chin.pswm.gps.photo.location.map.ads.AdsSplashUtils;
import chin.pswm.gps.photo.location.map.ads.AdsVariable;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivitySplashBinding;
import chin.pswm.gps.photo.location.map.languegess.ActivityPrivacyPolicy_New;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.New_first_languagesselect;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map.utils.PermissionUtils;
import chin.pswm.gps.photo.location.map.utils.SpManager;


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
        this.permissionUtils = new PermissionUtils(this);
        boolean privacyScreenShown = SharedHelper.getBoolean(getApplicationContext(), "privacy_screen_shown", false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public final void run() {
                if (!privacyScreenShown) {
                    showPrivacyScreen();
                } else {
                    checkMain();
                }
            }
        }, 2000L);
    }


    private void showPrivacyScreen() {
        startActivity(new Intent(SplashActivity.this, ActivityPrivacyPolicy_New.class));
        SharedHelper.putBoolean(getApplicationContext(), "privacy_screen_shown", true);
        finish();
    }

    public final void checkMain() {
        String selectedLanguage = SharedHelper.getString(getApplicationContext(), "lang_key", "");
        boolean displayIntroEveryTime = SharedHelper.getBoolean(getApplicationContext(), "display_intro_everytime", false);
        if (!selectedLanguage.isEmpty() && displayIntroEveryTime) {
            setLanguage(selectedLanguage);
            gotoLoginActivity();
        } else {
            if (selectedLanguage.isEmpty()) {
                startActivity(new Intent(SplashActivity.this, New_first_languagesselect.class));
            } else {
                startActivity(new Intent(SplashActivity.this, New_IntroActivity.class));
            }
        }
        finish();
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
