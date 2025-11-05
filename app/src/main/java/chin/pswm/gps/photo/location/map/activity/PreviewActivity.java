package chin.pswm.gps.photo.location.map.activity;

import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showUserInterDataBack;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import chin.pswm.gps.photo.location.map.AllKeyHub;
import chin.pswm.gps.photo.location.map.ads.AdsManager;
import chin.pswm.gps.photo.location.map.ads.adunit.banner.BannerType;
import chin.pswm.gps.photo.location.map.compose.ComposeBannerKt;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map_debug.BuildConfig;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivityPreviewBinding;
import chin.pswm.gps.photo.location.map_debug.databinding.DeleteDialogLayoutBinding;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

@SuppressWarnings("all")

public class PreviewActivity extends BaseActivity {
    ActivityPreviewBinding binding;
    String path;
    private long mLastClickTime = 0;
    public ArrayList<Uri> uris = new ArrayList<>();

    @Override
    public void onCreate(Bundle bundle) {
        LanguageManager.setLocale(PreviewActivity.this, SharedHelper.getString(PreviewActivity.this, "lang_key", ""));

        super.onCreate(bundle);
        ActivityPreviewBinding inflate = ActivityPreviewBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        this.path = getIntent().getStringExtra("Path");
        Glide.with((FragmentActivity) this).load(this.path).into(this.binding.preview);
        initSocketConnection(this, true, true);

        this.binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                AdsManager.INSTANCE.showInterInApp(
                        PreviewActivity.this,
                        false,
                        new Function0<Unit>() {
                            @Override
                            public Unit invoke() {
                                PreviewActivity.super.onBackPressed();
                                return null;
                            }
                        }
                );
            }
        });
        this.binding.wh.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PreviewActivity.this.m110xf4af0d14(view);
            }
        });
        this.binding.fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PreviewActivity.this.m111x564d9d5(view);
            }
        });
        this.binding.insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PreviewActivity.this.m112x161aa696(view);
            }
        });
        this.binding.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PreviewActivity.this.m113x26d07357(view);
            }
        });
        this.binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PreviewActivity.this.m114x37864018(view);
            }
        });
        ComposeBannerKt.setBannerContent(binding.composeView,
                "PreviewActivity",
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

    public void m109xe3f94053(View view) {
        onBackPressed();
    }


    public void m110xf4af0d14(View view) {
        if (SystemClock.elapsedRealtime() - this.mLastClickTime < 1000) {
            return;
        }
        this.mLastClickTime = SystemClock.elapsedRealtime();
        try {
            Uri uriForFile = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", new File(this.path));
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("*/*");
            intent.addFlags(1);
            intent.addFlags(2);
            intent.putExtra("android.intent.extra.STREAM", uriForFile);
            intent.setPackage("com.whatsapp");
            startActivity(intent);
        } catch (Exception unused) {
            Toast.makeText(this, getResources().getString(R.string.gpl20), 0).show();
        }
    }


    public void m111x564d9d5(View view) {
        if (SystemClock.elapsedRealtime() - this.mLastClickTime < 1000) {
            return;
        }
        this.mLastClickTime = SystemClock.elapsedRealtime();
        try {
            Uri uriForFile = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", new File(this.path));
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("*/*");
            intent.addFlags(1);
            intent.addFlags(2);
            intent.putExtra("android.intent.extra.STREAM", uriForFile);
            intent.setPackage("com.facebook.katana");
            startActivity(intent);
        } catch (Exception unused) {
            Toast.makeText(this, getResources().getString(R.string.gpl18), 0).show();
        }
    }


    public void m112x161aa696(View view) {
        if (SystemClock.elapsedRealtime() - this.mLastClickTime < 1000) {
            return;
        }
        this.mLastClickTime = SystemClock.elapsedRealtime();
        try {
            Uri uriForFile = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", new File(this.path));
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("*/*");
            intent.addFlags(1);
            intent.addFlags(2);
            intent.putExtra("android.intent.extra.STREAM", uriForFile);
            intent.setPackage("com.instagram.android");
            startActivity(intent);
        } catch (Exception unused) {
            Toast.makeText(this, getResources().getString(R.string.gpl19), 0).show();
        }
    }


    public void m113x26d07357(View view) {
        if (SystemClock.elapsedRealtime() - this.mLastClickTime < 1000) {
            return;
        }
        this.mLastClickTime = SystemClock.elapsedRealtime();
        Uri uriForFile = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", new File(this.path));
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("*/*");
        intent.addFlags(1);
        intent.addFlags(2);
        intent.putExtra("android.intent.extra.STREAM", uriForFile);
        startActivity(intent);
    }


    public void m114x37864018(View view) {
        deleteDialog();
    }

    public void deleteDialog() {
        DeleteDialogLayoutBinding inflate = DeleteDialogLayoutBinding.inflate(getLayoutInflater());
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().requestFeature(1);
        dialog.setContentView(inflate.getRoot());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        inflate.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                dialog.cancel();
            }
        });
        inflate.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                PreviewActivity.this.m108xcbe78a32(dialog, view);
            }
        });
        dialog.show();
    }


    public void m108xcbe78a32(Dialog dialog, View view) {
        dialog.cancel();
        deleteImage(this.path);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void deleteImage(String str) {
        File file = new File(str);
        if (file.delete()) {
            MediaScannerConnection.scanFile(this, new String[]{file.getAbsolutePath()}, null, null);
            onBackPressed();
            return;
        }
        this.uris.clear();
        MediaScannerConnection.scanFile(this, new String[]{str}, null, new MediaScannerConnection.OnScanCompletedListener() {
            @Override
            public void onScanCompleted(String str2, Uri uri) {
                PreviewActivity.this.uris.add(uri);
                PreviewActivity previewActivity = PreviewActivity.this;
                previewActivity.requestDeletePermission(previewActivity.uris);
            }
        });
    }

    public void requestDeletePermission(List<Uri> list) {
        if (Build.VERSION.SDK_INT >= 30) {
            try {
                startIntentSenderForResult(MediaStore.createDeleteRequest(getContentResolver(), list).getIntentSender(), 114, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
                Log.d("TAG", "requestDeletePermission: " + e.getMessage());
                Toast.makeText(this, getResources().getString(R.string.gpl21), 0).show();
            }
        }
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 114) {
            onBackPressed();
        } else {
            Toast.makeText(this, getResources().getString(R.string.gpl21), 0).show();
        }
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
