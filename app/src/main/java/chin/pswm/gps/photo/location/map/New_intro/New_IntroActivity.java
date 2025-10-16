package chin.pswm.gps.photo.location.map.New_intro;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import chin.pswm.gps.photo.location.map.compose.onboard.ComposeOnboardKt;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.utils.PermissionUtils;
import chin.pswm.gps.photo.location.map_debug.R;


public class New_IntroActivity extends AppCompatActivity {
    ComposeView composeView;
    private SharedPreferences sharedPreferences;

    PermissionUtils permissionUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.setLocale(New_IntroActivity.this, SharedHelper.getString(New_IntroActivity.this, "lang_key", ""));
        setContentView(R.layout.aa_intro_activity);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        sharedPreferences = getSharedPreferences("intro_prefs", MODE_PRIVATE);
        this.permissionUtils = new PermissionUtils(this);
        composeView = findViewById(R.id.composeView);

        ComposeOnboardKt.setMyContent(composeView, () -> {
            redirectToMain();
            return null;
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
            }
        });
    }

    private void redirectToMain() {
        completeIntro();
        startMainActivity();
    }

    public final void startMainActivity() {
        Intent intent2 = new Intent(this, ActivitySelectFeature.class);
        intent2.setFlags(268435456);
        startActivity(intent2);
        finish();
    }

    private void completeIntro() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("display_intro_everytime", true);
        editor.apply();
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



