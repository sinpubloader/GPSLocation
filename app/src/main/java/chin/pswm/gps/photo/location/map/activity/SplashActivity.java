package chin.pswm.gps.photo.location.map.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;

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
        ComposeSplashKt.setMyContent(binding.composeView, binding.composeViewBanner);
        this.permissionUtils = new PermissionUtils(this);

        AdsManager.INSTANCE.requestUMP(SplashActivity.this, true, true);
        AdsManager.INSTANCE.getConsentFinished().observe(this, finished -> {
            checkMain();
        });
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
