package chin.pswm.gps.photo.location.map.activity;

import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showUserInterDataBack;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import chin.pswm.gps.photo.location.map.AllKeyHub;
import chin.pswm.gps.photo.location.map.ads.adunit.banner.BannerType;
import chin.pswm.gps.photo.location.map.compose.ComposeBannerKt;
import chin.pswm.gps.photo.location.map.fragment.CurrentLocationFragment;
import chin.pswm.gps.photo.location.map.fragment.ManualLocationFragment;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map.utils.SpManager;
import chin.pswm.gps.photo.location.map_debug.BuildConfig;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivityLocationBinding;

@SuppressWarnings("all")

public class LocationActivity extends BaseActivity {
    ActivityLocationBinding binding;
    int currentFragment = 0;

    @Override
    public void onCreate(Bundle bundle) {
        LanguageManager.setLocale(LocationActivity.this, SharedHelper.getString(LocationActivity.this, "lang_key", ""));

        super.onCreate(bundle);
        ActivityLocationBinding inflate = ActivityLocationBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        initSocketConnection(this, true, true);

        this.binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                showUserInterDataBack(LocationActivity.this, new AllKeyHub.onCrashDataClose() {
                    @Override
                    public void onDataClose() {
                        onBackPressed();
                    }
                });
            }
        });
        setFragment();
        this.binding.current.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                LocationActivity.this.m98x91b09b67(view);
            }
        });
        this.binding.manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                LocationActivity.this.m99x97b466c6(view);
            }
        });
        this.binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                LocationActivity.this.m100x9db83225(view);
            }
        });

        ComposeBannerKt.setBannerContent(binding.composeView,
                BuildConfig.banner_inapp,
                "banner_inapp",
                BannerType.BANNER_ADAPTIVE
        );
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showUserInterDataBack(this, new AllKeyHub.onCrashDataClose() {
                @Override
                public void onDataClose() {
                    onBackPressed();
                }
            });
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            return super.onKeyDown(keyCode, event);
        }
        return true;
    }

    public void m97x8bacd008(View view) {
        onBackPressed();
    }

    public void m98x91b09b67(View view) {
        if (this.currentFragment != 0) {
            this.currentFragment = 0;
            setFragment();
        }
    }


    public void m99x97b466c6(View view) {
        if (this.currentFragment != 1) {
            this.currentFragment = 1;
            setFragment();
        }
    }


    public void m100x9db83225(View view) {
        if (this.currentFragment == 0) {
            SpManager.setSelectLocationType(0);
            onBackPressed();
            return;
        }
        SpManager.setSelectLocationType(1);
        SpManager.setLATITUDE(String.valueOf(ManualLocationFragment.lat));
        SpManager.setLONGITUDE(String.valueOf(ManualLocationFragment.lon));
        onBackPressed();
    }

  /*  public void setFragment() {
        Fragment currentLocationFragment = this.currentFragment == 0 ? new CurrentLocationFragment(this) : new ManualLocationFragment(this);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.frameLayoutOne, currentLocationFragment);
        beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        beginTransaction.commit();
        boolean z = currentLocationFragment instanceof CurrentLocationFragment;
        this.binding.current.setTextColor(Color.parseColor(z ? "#000000" : "#FFFFFF"));
        boolean z2 = currentLocationFragment instanceof ManualLocationFragment;
        this.binding.manual.setTextColor(Color.parseColor(z2 ? "#000000" : "#FFFFFF"));
        TextView textView = this.binding.current;
        Resources resources = getResources();
        int i = R.drawable.effect_white;
        textView.setBackground(resources.getDrawable(z ? R.drawable.effect_white : R.drawable.effect_black, getTheme()));
        TextView textView2 = this.binding.manual;
        Resources resources2 = getResources();
        if (!z2) {
            i = R.drawable.effect_black;
        }
        textView2.setBackground(resources2.getDrawable(i, getTheme()));
    }*/


    public void setFragment() {
        Fragment fragment = (this.currentFragment == 0)
                ? new CurrentLocationFragment(this)
                : new ManualLocationFragment(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayoutOne, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();

        boolean isCurrent = fragment instanceof CurrentLocationFragment;
        boolean isManual = fragment instanceof ManualLocationFragment;

        // Text color
        binding.current.setTextColor(Color.parseColor(isCurrent ? "#ffffff" : "#000000"));
        binding.manual.setTextColor(Color.parseColor(isManual ? "#ffffff" : "#000000"));

        // Background drawable
        binding.current.setBackground(ContextCompat.getDrawable(this,
                isCurrent ? R.drawable.bb : R.drawable.aa));

        binding.manual.setBackground(ContextCompat.getDrawable(this,
                isManual ? R.drawable.bb : R.drawable.aa));
    }
}
