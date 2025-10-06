package chin.pswm.gps.photo.location.map.activity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showUserInterDataBack;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import chin.pswm.gps.photo.location.map.AllKeyHub;
import chin.pswm.gps.photo.location.map.adapter.StartAdapter;
import chin.pswm.gps.photo.location.map.ads.AdsManager;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.New_first_languagesselect;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.utils.ImageLocationExtractor;
import chin.pswm.gps.photo.location.map.utils.StorageUtils;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivitySettingBinding;
import chin.pswm.gps.photo.location.map_debug.databinding.ProcessDialogLayoutBinding;

public class settingActivity extends AppCompatActivity {
    ActivitySettingBinding binding;

    public boolean isImgShow = false, isVideoShow = false;
    public List<Uri> uriList = new ArrayList();
    public List<Uri> uriListVideo = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LanguageManager.setLocale(settingActivity.this, SharedHelper.getString(settingActivity.this, "lang_key", ""));

        super.onCreate(savedInstanceState);
        ActivitySettingBinding inflate = ActivitySettingBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        initSocketConnection(this, true, true);
        binding.cl1.setVisibility(GONE);
        binding.view1.setVisibility(GONE);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUserInterDataBack(settingActivity.this, new AllKeyHub.onCrashDataClose() {
                    @Override
                    public void onDataClose() {
                        onBackPressed();
                    }
                });
            }
        });

        binding.lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settingActivity.this, New_first_languagesselect.class);
                intent.putExtra("fromNavigationBar", true);
                startActivity(intent);
            }
        });
        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.addFlags(524288);
                intent.putExtra("android.intent.extra.SUBJECT", settingActivity.this.getResources().getString(R.string.app_name));
                intent.putExtra("android.intent.extra.TEXT", "Click on below link to download " + settingActivity.this.getResources().getString(R.string.app_name) + "https://play.google.com/store/apps/details?id=" + settingActivity.this.getPackageName() + "");
                settingActivity.this.startActivity(Intent.createChooser(intent, "Share Using!"));
            }
        });
        binding.rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    settingActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + settingActivity.this.getPackageName())));
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(settingActivity.this, " unable to find app", 1).show();
                }
            }
        });
        binding.privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://wandaapps.blogspot.com/2025/09/privacy-policy.html")));
            }
        });

        binding.tvPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdsManager.INSTANCE.showInterInApp(settingActivity.this, true, () -> {
                    startActivity(new Intent(settingActivity.this, MyCreationActivity.class).putExtra("TYPE", 0).setFlags(536870912));
                    return null;
                });
            }
        });

        binding.tvVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdsManager.INSTANCE.showInterInApp(settingActivity.this, true, () -> {
                    startActivity(new Intent(settingActivity.this, MyCreationActivity.class).putExtra("TYPE", 1).setFlags(536870912));
                    return null;
                });
            }
        });
    }

    class ProcessAsyncTask extends AsyncTask<String, Void, String> {

        ProcessAsyncTask() {
        }


        public void onPreExecute() {
            super.onPreExecute();
        }


        public String doInBackground(String[] strArr) {
            settingActivity startActivity = settingActivity.this;
            startActivity.uriList = StorageUtils.getFolderData(StorageUtils.create_folder(startActivity.getResources().getString(R.string.app_name)));
            settingActivity startActivity2 = settingActivity.this;
            startActivity2.uriListVideo = StorageUtils.getFolderDataVideo(StorageUtils.create_folder(startActivity2.getResources().getString(R.string.app_name)));
            return null;
        }


        public void onPostExecute(String str) {
            if (settingActivity.this.uriList == null || settingActivity.this.uriList.size() <= 0) {
                hideShowImageView(true);
            } else {
                hideShowImageView(false);
            }
            if (settingActivity.this.uriListVideo == null || settingActivity.this.uriListVideo.size() <= 0) {
                hideShowVideoView(true);
            } else {
                hideShowVideoView(false);
            }
            setupFinallView();
        }
    }
    
    public void hideShowImageView(boolean isHide) {
        isImgShow = !isHide;
        binding.ivPhoto.setVisibility(isHide ? GONE : VISIBLE);
        binding.tvPhoto.setVisibility(isHide ? GONE : VISIBLE);
        binding.ivImgArrow.setVisibility(isHide ? GONE : VISIBLE);
    }
    
    public void hideShowVideoView(boolean isHide) {
        isVideoShow = !isHide;
        binding.ivVideo.setVisibility(isHide ? GONE : VISIBLE);
        binding.tvVideo.setVisibility(isHide ? GONE : VISIBLE);
        binding.ivVidArrow.setVisibility(isHide ? GONE : VISIBLE);
    }

    public void setupFinallView() {
        if (isImgShow || isVideoShow)
            binding.cl1.setVisibility(VISIBLE);
        binding.view1.setVisibility(isImgShow && isVideoShow ? VISIBLE : GONE);
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

    @Override
    protected void onResume() {
        super.onResume();
        new ProcessAsyncTask().execute(new String[0]);
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