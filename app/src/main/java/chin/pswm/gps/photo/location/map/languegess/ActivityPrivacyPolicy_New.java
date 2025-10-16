package chin.pswm.gps.photo.location.map.languegess;


import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import chin.pswm.gps.photo.location.map.MyApplication;
import chin.pswm.gps.photo.location.map.activity.PermissionActivity;
import chin.pswm.gps.photo.location.map.activity.StartActivity;
import chin.pswm.gps.photo.location.map.utils.PermissionUtils;
import chin.pswm.gps.photo.location.map_debug.R;


public class ActivityPrivacyPolicy_New extends AppCompatActivity {

    CheckBox check_privacy;
    ComposeView composeView;
    TextView start, tv_privacy_policy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LanguageManager.setLocale(ActivityPrivacyPolicy_New.this, SharedHelper.getString(ActivityPrivacyPolicy_New.this, "lang_key", ""));
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_privacy);
        initSocketConnection(this, true, true);
        check_privacy = findViewById(R.id.check_box);
        start = findViewById(R.id.tv_getstarted);
        tv_privacy_policy = findViewById(R.id.tv_privacy_policy);
        composeView = findViewById(R.id.composeView);

        String privacyPolicyText = "By press the Agree button below, you agree to our Privacy Policy and Terms of Conditions.";
        SpannableString spannableString = new SpannableString(privacyPolicyText);

        ClickableSpan privacyPolicySpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://wandaapps.blogspot.com/2025/09/privacy-policy.html")));
            }
        };
        ClickableSpan termsConditionsSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://wandaapps.blogspot.com/2025/09/privacy-policy.html")));
            }
        };
        int privacyPolicyStart = privacyPolicyText.indexOf("Privacy Policy");
        int privacyPolicyEnd = privacyPolicyStart + "Privacy Policy".length();
        int termsConditionsStart = privacyPolicyText.indexOf("Terms of Conditions");
        int termsConditionsEnd = termsConditionsStart + "Terms of Conditions".length();
        spannableString.setSpan(privacyPolicySpan, privacyPolicyStart, privacyPolicyEnd, 0);
        spannableString.setSpan(termsConditionsSpan, termsConditionsStart, termsConditionsEnd, 0);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.main)), privacyPolicyStart, privacyPolicyEnd, 0);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.main)), termsConditionsStart, termsConditionsEnd, 0);
        spannableString.setSpan(new UnderlineSpan(), privacyPolicyStart, privacyPolicyEnd, 0);
        spannableString.setSpan(new UnderlineSpan(), termsConditionsStart, termsConditionsEnd, 0);

        tv_privacy_policy.setText(spannableString);
        tv_privacy_policy.setMovementMethod(LinkMovementMethod.getInstance());

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedHelper.putBoolean(getApplicationContext(), "privacy_screen_shown", true);
                PermissionUtils permissionUtils = new PermissionUtils(ActivityPrivacyPolicy_New.this);
                if (permissionUtils.checkPermission(permissionUtils.allPermissions)) {
                    Intent intent = new Intent(ActivityPrivacyPolicy_New.this, StartActivity.class);
                    intent.setFlags(268435456);
                    startActivity(intent);
                    finish();
                    return;
                }
                Intent intent2 = new Intent(ActivityPrivacyPolicy_New.this, PermissionActivity.class);
                intent2.setFlags(268435456);
                startActivity(intent2);
                finish();
            }
        });
        MyApplication.sendEvent("Privacy_Screen", "");

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
            }
        });
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
