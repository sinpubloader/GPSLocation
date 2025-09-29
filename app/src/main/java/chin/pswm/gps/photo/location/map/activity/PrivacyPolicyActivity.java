package chin.pswm.gps.photo.location.map.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivityPrivacyPolicyBinding;


public class PrivacyPolicyActivity extends BaseActivity {
    ActivityPrivacyPolicyBinding binding;
    private long mLastClickTime = 0;

    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityPrivacyPolicyBinding inflate = ActivityPrivacyPolicyBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        this.binding.back.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - PrivacyPolicyActivity.this.mLastClickTime < 1000) {
                    return;
                }
                PrivacyPolicyActivity.this.mLastClickTime = SystemClock.elapsedRealtime();
                PrivacyPolicyActivity.this.onBackPressed();
            }
        });
        this.binding.webview.loadUrl(getString(R.string.privacy_link));
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
