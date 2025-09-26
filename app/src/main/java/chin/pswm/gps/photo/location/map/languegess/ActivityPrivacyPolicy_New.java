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
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import chin.pswm.gps.photo.location.map_debug.R;


public class ActivityPrivacyPolicy_New extends AppCompatActivity {

    CheckBox check_privacy;
    TextView start, tv_privacy_policy,tv_getstarted;
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LanguageManager.setLocale(ActivityPrivacyPolicy_New.this, SharedHelper.getString(ActivityPrivacyPolicy_New.this, "lang_key",""));
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
        tv_getstarted = findViewById(R.id.tv_getstarted);

        String privacyPolicyText = "By checking the box you agree to our Privacy Policy and Terms of Conditions.";
        SpannableString spannableString = new SpannableString(privacyPolicyText);

        ClickableSpan privacyPolicySpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.google.co.in/")));
            }
        };
        ClickableSpan termsConditionsSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.google.co.in/")));
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

        check_privacy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (check_privacy.isChecked()) {
                    start.setBackgroundResource(R.drawable.rect_round_main_blue);
                    tv_getstarted.setTextColor(getResources().getColor(R.color.white));
                }else {
                    start.setBackgroundResource(R.drawable.rect_round_main_sky);
                    tv_getstarted.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
        if (check_privacy.isChecked()) {
            start.setBackgroundResource(R.drawable.rect_round_main_blue);
            tv_getstarted.setTextColor(getResources().getColor(R.color.white));
        }else {
            start.setBackgroundResource(R.drawable.rect_round_main_sky);
            tv_getstarted.setTextColor(getResources().getColor(R.color.black));
        }
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check_privacy.isChecked()) {
                    start.setBackgroundResource(R.drawable.rect_round_main_blue);
                    startActivity(new Intent(ActivityPrivacyPolicy_New.this, New_first_languagesselect.class));
                    finish();
                } else {
                    Toast.makeText(ActivityPrivacyPolicy_New.this, "Check Policy First", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
