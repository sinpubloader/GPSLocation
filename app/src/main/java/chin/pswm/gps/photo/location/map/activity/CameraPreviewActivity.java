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
import androidx.fragment.app.FragmentActivity;

import chin.pswm.gps.photo.location.map.AllKeyHub;
import chin.pswm.gps.photo.location.map.ads.AdsVariable;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivityCameraPreviewBinding;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map.utils.Common;
import com.bumptech.glide.Glide;


public class CameraPreviewActivity extends BaseActivity {
    ActivityCameraPreviewBinding binding;
    Bitmap bitmap;
    private long mLastClickTime = 0;
    int type;

    @Override 
    public void onCreate(Bundle bundle) {
        LanguageManager.setLocale(CameraPreviewActivity.this, SharedHelper.getString(CameraPreviewActivity.this, "lang_key", ""));

        super.onCreate(bundle);
        ActivityCameraPreviewBinding inflate = ActivityCameraPreviewBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        this.bitmap = Common.locationDataModel.getCameraImage();
        this.type = getIntent().getIntExtra("TYPE", 2);
        Glide.with((FragmentActivity) this).load(this.bitmap).into(this.binding.preview);
        initSocketConnection(this, true, true);

        this.binding.back.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                showUserInterDataBack(CameraPreviewActivity.this, new AllKeyHub.onCrashDataClose() {
                    @Override
                    public void onDataClose() {
                        CameraPreviewActivity.this.m75x8d18716e(view);
                    }
                });

            }
        });

        this.binding.rotate.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                CameraPreviewActivity.this.m76xdad7e96f(view);
            }
        });
        this.binding.next.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                CameraPreviewActivity.this.m77x28976170(view);
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

    public  void m75x8d18716e(View view) {
        onBackPressed();
    }


    public  void m76xdad7e96f(View view) {
        if (SystemClock.elapsedRealtime() - this.mLastClickTime < 1000) {
            return;
        }
        this.mLastClickTime = SystemClock.elapsedRealtime();
        this.bitmap = rotateBitmap(this.bitmap, 90.0f);
        Glide.with((FragmentActivity) this).load(this.bitmap).into(this.binding.preview);
    }


    public  void m77x28976170(View view) {
        if (SystemClock.elapsedRealtime() - this.mLastClickTime < 1000) {
            return;
        }
        this.mLastClickTime = SystemClock.elapsedRealtime();
        Common.pictureBitmap = this.bitmap;
        startActivity(new Intent(this, AdvanceCreateActivity.class).setFlags(536870912));
        finish();
    }

    public Bitmap rotateBitmap(Bitmap bitmap, float f) {
        Matrix matrix = new Matrix();
        matrix.postRotate(f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
