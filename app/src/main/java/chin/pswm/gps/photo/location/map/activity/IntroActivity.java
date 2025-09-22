package chin.pswm.gps.photo.location.map.activity;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map.adapter.IntroAdapter;
import chin.pswm.gps.photo.location.map.ads.AdsVariable;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivityIntroBinding;
import chin.pswm.gps.photo.location.map.model.IntroModel;
import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map.utils.PermissionUtils;
import chin.pswm.gps.photo.location.map.utils.Resizer;
import chin.pswm.gps.photo.location.map.utils.SpManager;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("all")

public class IntroActivity extends BaseActivity {
    ActivityIntroBinding binding;
    private final ViewPager2.OnPageChangeCallback onPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageScrolled(int i, float f, int i2) {
            super.onPageScrolled(i, f, i2);
        }

        @Override
        public void onPageSelected(int i) {
            super.onPageSelected(i);
            Glide.with((FragmentActivity) IntroActivity.this).load(Integer.valueOf(i == 0 ? R.drawable.intro_page_1 : i == 1 ? R.drawable.intro_page_2 : R.drawable.intro_page_3)).into(IntroActivity.this.binding.indicator);
            IntroActivity.this.binding.nextDoneTxt.setText(i != 2 ? R.string.gpl9 : R.string.gpl10);
        }

        @Override
        public void onPageScrollStateChanged(int i) {
            super.onPageScrollStateChanged(i);
        }
    };
    PermissionUtils permissionUtils;

    @Override 
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityIntroBinding inflate = ActivityIntroBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        this.permissionUtils = new PermissionUtils(this);
        final ArrayList arrayList = new ArrayList();
        arrayList.add(new IntroModel(R.drawable.intro_logo_1, R.string.gpl5, R.string.gpl6));
        arrayList.add(new IntroModel(R.drawable.intro_logo_2, R.string.gpl7, R.string.gpl8));
        arrayList.add(new IntroModel(R.drawable.intro_logo_3, R.string.gpl7_1, R.string.gpl8_1));
        Resizer.getheightandwidth(this);
        Resizer.setSize(this.binding.indicator, 141, 35, true);
        Resizer.setSize(this.binding.nextDoneTxt, 242, 144, true);
        this.binding.infoPager.setAdapter(new IntroAdapter(arrayList));
        this.binding.nextDoneTxt.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                IntroActivity.this.m94x3cc85f7(arrayList, view);
            }
        });
    }


    public  void m94x3cc85f7(List list, View view) {
        if (this.binding.infoPager.getCurrentItem() == 2) {
            SpManager.setIsFirstTime(false);
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
            return;
        }
        this.binding.infoPager.setCurrentItem(Math.min(this.binding.infoPager.getCurrentItem() + 1, list.size() - 1), true);
    }

    @Override
    public void onBackPressed() {
        List<ActivityManager.AppTask> appTasks;
        ActivityManager activityManager = (ActivityManager) getSystemService("activity");
        if (activityManager == null || (appTasks = activityManager.getAppTasks()) == null || appTasks.size() <= 0) {
            return;
        }
        for (ActivityManager.AppTask appTask : appTasks) {
            appTask.finishAndRemoveTask();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.binding.infoPager.registerOnPageChangeCallback(this.onPageChangeCallback);
    }

    @Override
    public void onPause() {
        super.onPause();
        this.binding.infoPager.unregisterOnPageChangeCallback(this.onPageChangeCallback);
    }
}
