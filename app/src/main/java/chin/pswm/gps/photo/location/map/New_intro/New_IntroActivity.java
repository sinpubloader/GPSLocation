package chin.pswm.gps.photo.location.map.New_intro;



import static chin.pswm.gps.photo.location.map.AllKeyHub.showDynamicNativeData;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showIntroAddData;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map.activity.PermissionActivity;
import chin.pswm.gps.photo.location.map.activity.StartActivity;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.utils.PermissionUtils;


public class New_IntroActivity extends AppCompatActivity {
    ViewPager viewPager;
    DotsIndicator dot1;
    TextView next;
    TextView skip;
    ViewAdapter viewAdapter;
    private SharedPreferences sharedPreferences;
    private static final String PREF_INTRO_COMPLETED = "display_intro_everytime";
//    FrameLayout frameLayout;
    private Handler handler = new Handler();
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
        boolean displayIntroEveryTime = sharedPreferences.getBoolean(PREF_INTRO_COMPLETED, false);
        if (displayIntroEveryTime) {
            redirectToMain();
            return;
        }
        viewPager = findViewById(R.id.view_pager);
//        frameLayout = findViewById(R.id.Ad_Native);
        dot1 = findViewById(R.id.dots_indicator);
        next = findViewById(R.id.next);
        skip = findViewById(R.id.skip_button);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentItem = viewPager.getCurrentItem();
                if (currentItem < 2) {
                    viewPager.setCurrentItem(2);
                } else {
                    redirectToMain();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentItem = viewPager.getCurrentItem();
                if (currentItem < viewAdapter.getCount() - 1) {
                    viewPager.setCurrentItem(currentItem + 1);
                } else {
                    redirectToMain();
                }
            }
        });
        viewAdapter = new ViewAdapter(this);
        viewPager.setAdapter(viewAdapter);
        dot1.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position < viewAdapter.getCount() - 1) {
                    next.setText(getResources().getString(R.string.next));
                } else {
                    next.setText(getResources().getString(R.string.get_start));
                }
//                showIntroAddData(New_IntroActivity.this, () -> {});
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
//        updateFrameLayout(viewPager.getCurrentItem());
    }

    private void redirectToMain() {
        completeIntro();
        startMainActivity();
    }

    public final void startMainActivity() {

        PermissionUtils permissionUtils = this.permissionUtils;
        if (permissionUtils.checkPermission(permissionUtils.allPermissions)) {
            Intent intent = new Intent(this, StartActivity.class);
            intent.setFlags(268435456);
            startActivity(intent);
            finish();
            return;
        }
        Intent intent2 = new Intent(this, PermissionActivity.class);
        intent2.setFlags(268435456);
        startActivity(intent2);
        finish();
    }

    private void completeIntro() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PREF_INTRO_COMPLETED, true);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() > 0) {
            viewPager.setCurrentItem(0);
        } else {
            super.onBackPressed();
        }
    }


//    private void updateFrameLayout(int position) {
//        FrameLayout frameLayout = findViewById(R.id.Ad_Native);
//        frameLayout.removeAllViews();
//        LayoutInflater inflater = LayoutInflater.from(this);
//        View fragmentView = inflater.inflate(getFragmentLayout(position), frameLayout, false);
//        frameLayout.addView(fragmentView);
//        showDynamicNativeData(this, frameLayout, null, true);
//    }

//    private int getFragmentLayout(int position) {
//
//
//        switch (position) {
//            case 0:
//                Log.d("ad_check", "getFragmentLayout:  0 ");
//                return R.layout.item_native_ad;
//            case 1:
//                Log.d("ad_check", "getFragmentLayout:  1 ");
//                return R.layout.item_native_ad_1;
//            case 2:
//                Log.d("ad_check", "getFragmentLayout:  2 ");
//                return R.layout.item_native_ad_2;
//            default:
//                Log.d("ad_check", "getFragmentLayout:  default ");
//                return R.layout.item_native_ad_default;
//        }
//    }
}



