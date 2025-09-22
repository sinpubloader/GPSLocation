package chin.pswm.gps.photo.location.map.activity;

import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showUserInterDataBack;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.core.content.FileProvider;

import chin.pswm.gps.photo.location.map.AllKeyHub;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map.ads.AdsVariable;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivityVideoPreviewBinding;
import chin.pswm.gps.photo.location.map_debug.databinding.DeleteDialogLayoutBinding;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map.utils.Common;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("all")

public class VideoPreviewActivity extends BaseActivity {
    ActivityVideoPreviewBinding binding;
    String path;
    String totaltime;
    private long mLastClickTime = 0;
    public long current_pos = 0;
    public long total_duration = 0;
    public ArrayList<Uri> uris = new ArrayList<>();

    @Override 
    public void onCreate(Bundle bundle) {
        LanguageManager.setLocale(VideoPreviewActivity.this, SharedHelper.getString(VideoPreviewActivity.this, "lang_key", ""));

        super.onCreate(bundle);
        ActivityVideoPreviewBinding inflate = ActivityVideoPreviewBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        this.path = getIntent().getStringExtra("Path");
        initSocketConnection(this, true, true);

        setData();
        this.binding.back.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                showUserInterDataBack(VideoPreviewActivity.this, new AllKeyHub.onCrashDataClose() {
                    @Override
                    public void onDataClose() {
                        onBackPressed();
                    }
                });
            }
        });
        this.binding.wh.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                VideoPreviewActivity.this.m147x3ec160ff(view);
            }
        });
        this.binding.fb.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                VideoPreviewActivity.this.m148xbd2264de(view);
            }
        });
        this.binding.insta.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                VideoPreviewActivity.this.m149x3b8368bd(view);
            }
        });
        this.binding.more.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                VideoPreviewActivity.this.m150xb9e46c9c(view);
            }
        });
        this.binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                VideoPreviewActivity.this.m151x3845707b(view);
            }
        });
    }


    public  void m146xc0605d20(View view) {
        onBackPressed();
    }


    public  void m147x3ec160ff(View view) {
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


    public  void m148xbd2264de(View view) {
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

    public  void m149x3b8368bd(View view) {
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


    public  void m150xb9e46c9c(View view) {
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

    public  void m151x3845707b(View view) {
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
                VideoPreviewActivity.this.m145xab8066a1(dialog, view);
            }
        });
        dialog.show();
    }


    public  void m145xab8066a1(Dialog dialog, View view) {
        dialog.cancel();
        deleteImage(this.path);
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
                VideoPreviewActivity.this.uris.add(uri);
                VideoPreviewActivity videoPreviewActivity = VideoPreviewActivity.this;
                videoPreviewActivity.requestDeletePermission(videoPreviewActivity.uris);
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

    private void setData() {
        playVideo(this.path);
        this.binding.pause.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                if (VideoPreviewActivity.this.binding.videoView.isPlaying()) {
                    VideoPreviewActivity.this.binding.pause.setImageResource(R.drawable.play);
                    VideoPreviewActivity.this.binding.videoView.pause();
                    return;
                }
                VideoPreviewActivity.this.binding.videoView.seekTo((int) VideoPreviewActivity.this.current_pos);
                VideoPreviewActivity.this.binding.pause.setImageResource(R.drawable.plause);
                VideoPreviewActivity.this.binding.videoView.start();
            }
        });
    }

    public void playVideo(String str) {
        try {
            this.binding.videoView.setVideoURI(Uri.parse(str));
            this.binding.videoView.requestFocus();
            final ViewGroup.LayoutParams layoutParams = this.binding.videoView.getLayoutParams();
            layoutParams.width = 846;
            layoutParams.height = 1300;
            this.binding.videoView.setLayoutParams(layoutParams);
            this.binding.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    VideoPreviewActivity.this.setVideoProgress();
                    VideoPreviewActivity.this.binding.videoView.setLayoutParams(layoutParams);
                    VideoPreviewActivity.this.binding.videoView.start();
                    VideoPreviewActivity.this.binding.pause.setImageResource(R.drawable.plause);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        if (this.current_pos != 0 && this.total_duration != 0) {
            this.binding.currentDuration.setText(Common.timeConversion(this.current_pos));
            this.binding.totalDuration.setText(Common.timeConversion(this.total_duration));
            this.binding.seekbar.setProgress((int) this.current_pos);
            this.binding.videoView.seekTo((int) this.current_pos);
        }
        super.onResume();
    }

    public void setVideoProgress() {
        if (this.current_pos == 0 && this.total_duration == 0) {
            long duration = this.binding.videoView.getDuration();
            this.total_duration = duration;
            this.totaltime = Common.timeConversion(duration);
            this.current_pos = this.binding.videoView.getCurrentPosition();
            this.binding.currentDuration.setText(Common.timeConversion(this.current_pos));
            this.binding.totalDuration.setText(Common.timeConversion(this.total_duration));
            this.binding.seekbar.setMax((int) this.total_duration);
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override 
            public void run() {
                try {
                    VideoPreviewActivity videoPreviewActivity = VideoPreviewActivity.this;
                    videoPreviewActivity.current_pos = videoPreviewActivity.binding.videoView.getCurrentPosition();
                    String timeConversion = Common.timeConversion(VideoPreviewActivity.this.current_pos);
                    VideoPreviewActivity.this.binding.currentDuration.setText(timeConversion);
                    if (timeConversion.equalsIgnoreCase(VideoPreviewActivity.this.totaltime)) {
                        VideoPreviewActivity.this.current_pos = 0L;
                        VideoPreviewActivity.this.binding.currentDuration.setText(Common.timeConversion(VideoPreviewActivity.this.current_pos));
                        VideoPreviewActivity.this.binding.pause.setImageResource(R.drawable.play);
                    }
                    VideoPreviewActivity.this.binding.seekbar.setProgress((int) VideoPreviewActivity.this.current_pos);
                    handler.postDelayed(this, 10L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 10L);
        this.binding.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                VideoPreviewActivity.this.current_pos = seekBar.getProgress();
                VideoPreviewActivity.this.binding.videoView.seekTo((int) VideoPreviewActivity.this.current_pos);
            }
        });
    }

    @Override
    public void onBackPressed() {
        try {
            if (this.binding.videoView.isPlaying()) {
                this.binding.pause.setImageResource(R.drawable.play);
                this.binding.videoView.pause();
            }
        } catch (Exception unused) {
        }
        finish();
    }
}
