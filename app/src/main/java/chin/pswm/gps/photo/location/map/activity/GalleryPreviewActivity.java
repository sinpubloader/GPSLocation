package chin.pswm.gps.photo.location.map.activity;

import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showUserInterDataBack;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;

import chin.pswm.gps.photo.location.map.AllKeyHub;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map.utils.Common;
import chin.pswm.gps.photo.location.map.utils.StorageUtils;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivityCameraPreviewBinding;
@SuppressWarnings("all")

public class GalleryPreviewActivity extends BaseActivity {
    public static Bitmap bitmap;
    ActivityCameraPreviewBinding binding;
    private long mLastClickTime = 0;
    String path;
    int type;

    @Override 
    public void onCreate(Bundle bundle) {
        LanguageManager.setLocale(GalleryPreviewActivity.this, SharedHelper.getString(GalleryPreviewActivity.this, "lang_key", ""));

        super.onCreate(bundle);
        ActivityCameraPreviewBinding inflate = ActivityCameraPreviewBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        this.path = getIntent().getStringExtra("Path");
        this.type = getIntent().getIntExtra("Type", 1);
        bitmap = StorageUtils.getBitmapFromFile(this.path);
        Glide.with((FragmentActivity) this).load(bitmap).into(this.binding.preview);
        initSocketConnection(this, true, true);

        this.binding.back.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                showUserInterDataBack(GalleryPreviewActivity.this, new AllKeyHub.onCrashDataClose() {
                    @Override
                    public void onDataClose() {
                        onBackPressed();
                    }
                });
            }
        });
        this.binding.rotate.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                GalleryPreviewActivity.this.m81x18aa2088(view);
            }
        });
        this.binding.next.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                GalleryPreviewActivity.this.m82x82d9a8a7(view);
            }
        });
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

    public  void m80xae7a9869(View view) {
        onBackPressed();
    }


    public  void m81x18aa2088(View view) {
        if (SystemClock.elapsedRealtime() - this.mLastClickTime < 1000) {
            return;
        }
        this.mLastClickTime = SystemClock.elapsedRealtime();
        bitmap = rotateBitmap(bitmap, 90.0f);
        Glide.with((FragmentActivity) this).load(bitmap).into(this.binding.preview);
    }


    public  void m82x82d9a8a7(View view) {
        if (SystemClock.elapsedRealtime() - this.mLastClickTime < 1000) {
            return;
        }
        this.mLastClickTime = SystemClock.elapsedRealtime();
        if (this.type == 1) {
            Common.pictureBitmap = bitmap;
            startActivity(new Intent(this, AdvanceCreateActivity.class).setFlags(536870912));
            finish();
            return;
        }
        Common.locationDataModel.setCameraImage(bitmap);
        startActivity(new Intent(this, GalleryPreviewMapActivity.class).setFlags(536870912).putExtra("Type", this.type));
        finish();
    }

    public Bitmap rotateBitmap(Bitmap bitmap2, float f) {
        Matrix matrix = new Matrix();
        matrix.postRotate(f);
        return Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), bitmap2.getHeight(), matrix, true);
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
