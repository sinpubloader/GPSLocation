package chin.pswm.gps.photo.location.map.activity;

import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showUserInterDataBack;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import chin.pswm.gps.photo.location.map.AllKeyHub;
import chin.pswm.gps.photo.location.map.ads.AdsManager;
import chin.pswm.gps.photo.location.map.ads.adunit.banner.BannerType;
import chin.pswm.gps.photo.location.map.compose.ComposeBannerKt;
import chin.pswm.gps.photo.location.map.fragment.StartFragment;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map.utils.ImageLocationExtractor;
import chin.pswm.gps.photo.location.map.utils.StorageUtils;
import chin.pswm.gps.photo.location.map_debug.BuildConfig;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivityMapViewBinding;
import chin.pswm.gps.photo.location.map_debug.databinding.ProcessDialogLayoutBinding;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

@SuppressWarnings("all")

public class MapViewActivity extends BaseActivity {
    ActivityMapViewBinding binding;
    public List<Uri> uriList = new ArrayList();
    List<ImageLocationExtractor.LocationListener> locationListeners = new ArrayList();


    @Override
    public void onCreate(Bundle bundle) {
        LanguageManager.setLocale(MapViewActivity.this, SharedHelper.getString(MapViewActivity.this, "lang_key", ""));

        super.onCreate(bundle);
        ActivityMapViewBinding inflate = ActivityMapViewBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        initSocketConnection(this, true, true);

        this.binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                showUserInterDataBack(MapViewActivity.this, new AllKeyHub.onCrashDataClose() {
                    @Override
                    public void onDataClose() {
                        AdsManager.INSTANCE.showInterInApp(
                                MapViewActivity.this,
                                false,
                                new Function0<Unit>() {
                                    @Override
                                    public Unit invoke() {
                                        MapViewActivity.super.onBackPressed();
                                        return null;
                                    }
                                }
                        );
                    }
                });
            }
        });

        ComposeBannerKt.setBannerContent(binding.composeView,
                "MapViewActivity",
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

    public void m101x201939ac(View view) {
        onBackPressed();
    }


    @Override
    public void onResume() {
        super.onResume();
        new ProcessAsyncTask().execute(new String[0]);
        hideSystemNavigationBar();
    }


    class ProcessAsyncTask extends AsyncTask<String, Void, String> {
        ProcessDialogLayoutBinding binding1;
        Dialog dialog;

        ProcessAsyncTask() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.binding1 = ProcessDialogLayoutBinding.inflate(MapViewActivity.this.getLayoutInflater());
            Dialog dialog = new Dialog(MapViewActivity.this);
            this.dialog = dialog;
            dialog.getWindow().requestFeature(1);
            this.dialog.setContentView(this.binding1.getRoot());
            this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    return true;
                }
            });
            Glide.with((FragmentActivity) MapViewActivity.this).load(Integer.valueOf((int) R.drawable.loading)).into(this.binding1.gif);
            this.dialog.show();
        }


        @Override
        public String doInBackground(String[] strArr) {
            MapViewActivity mapViewActivity = MapViewActivity.this;
            mapViewActivity.uriList = StorageUtils.getFolderData(StorageUtils.create_folder(mapViewActivity.getResources().getString(R.string.app_name)));
            return null;
        }


        @Override
        public void onPostExecute(String str) {
            if (MapViewActivity.this.uriList != null && MapViewActivity.this.uriList.size() > 0) {
                MapViewActivity mapViewActivity = MapViewActivity.this;
                new ImageLocationExtractor(mapViewActivity, mapViewActivity.uriList, MapViewActivity.this.locationListeners, new ImageLocationExtractor.ProcessComplete() {
                    @Override
                    public void locationListGet() {
                        ProcessAsyncTask.this.dialog.dismiss();
                        MapViewActivity.this.setFragment();
                    }
                }).start();
                return;
            }
            this.dialog.dismiss();
            MapViewActivity.this.setFragment();
        }
    }

    public void setFragment() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.frameLayoutOne, new StartFragment(this));
        beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        beginTransaction.commitAllowingStateLoss();
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
