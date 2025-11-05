package chin.pswm.gps.photo.location.map.activity;

import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showUserInterDataBack;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import java.util.ArrayList;

import chin.pswm.gps.photo.location.map.AllKeyHub;
import chin.pswm.gps.photo.location.map.adapter.TemplateAdapter;
import chin.pswm.gps.photo.location.map.ads.AdsManager;
import chin.pswm.gps.photo.location.map.ads.adunit.banner.BannerType;
import chin.pswm.gps.photo.location.map.compose.ComposeBannerKt;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map.utils.SpManager;
import chin.pswm.gps.photo.location.map_debug.BuildConfig;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivityTemplateBinding;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

@SuppressWarnings("all")

public class TemplateActivity extends BaseActivity implements TemplateAdapter.OnClickTemplate {
    ActivityTemplateBinding binding;
    TemplateAdapter templateAdapter;
    int selectPos = 0;
    ArrayList<Integer> arrayList = new ArrayList<>();

    @Override
    public void onCreate(Bundle bundle) {
        LanguageManager.setLocale(TemplateActivity.this, SharedHelper.getString(TemplateActivity.this, "lang_key", ""));

        super.onCreate(bundle);
        ActivityTemplateBinding inflate = ActivityTemplateBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        initSocketConnection(this, true, true);

        this.arrayList.add(Integer.valueOf((int) R.drawable.template_1));
        this.arrayList.add(Integer.valueOf((int) R.drawable.template_2));
        this.arrayList.add(Integer.valueOf((int) R.drawable.template_3));
        this.arrayList.add(Integer.valueOf((int) R.drawable.template_4));
        this.arrayList.add(Integer.valueOf((int) R.drawable.template_5));
        this.arrayList.add(Integer.valueOf((int) R.drawable.template_6));
        this.arrayList.add(Integer.valueOf((int) R.drawable.template_7));
        this.arrayList.add(Integer.valueOf((int) R.drawable.template_8));

        this.binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                AdsManager.INSTANCE.showInterInApp(
                        TemplateActivity.this,
                        false,
                        new Function0<Unit>() {
                            @Override
                            public Unit invoke() {
                                TemplateActivity.super.onBackPressed();
                                return null;
                            }
                        }
                );
            }
        });
        this.templateAdapter = new TemplateAdapter(this, this.arrayList, this);
        this.binding.recyclerview.setAdapter(this.templateAdapter);
        this.binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                TemplateActivity.this.m138x9259332c(view);
            }
        });

        ComposeBannerKt.setBannerContent(binding.composeView,
                "TemplateActivity",
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

    public void m137x8c5567cd(View view) {
        onBackPressed();
    }


    public void m138x9259332c(View view) {
        SpManager.setSelectTemplate(this.selectPos);
        finish();
    }

    @Override
    public void clickTemplate(int i) {
        this.selectPos = i;
        this.templateAdapter.updateAdapter(i);
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
