package chin.pswm.gps.photo.location.map.activity;

import static chin.pswm.gps.photo.location.map.AllKeyHub.showDynamicNativeData;

import android.Manifest;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import chin.pswm.gps.photo.location.map.MyApplication;
import chin.pswm.gps.photo.location.map.adapter.StartAdapter;
import chin.pswm.gps.photo.location.map.ads.AdsManager;
import chin.pswm.gps.photo.location.map.ads.AdsVariable;
import chin.pswm.gps.photo.location.map.ads.adunit.banner.BannerType;
import chin.pswm.gps.photo.location.map.compose.ComposeBannerKt;
import chin.pswm.gps.photo.location.map.earthview.EarthViewActivity;
import chin.pswm.gps.photo.location.map.interfaces.OnClickGallery;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.model.PlaceData;
import chin.pswm.gps.photo.location.map.notification.DailyNotificationType;
import chin.pswm.gps.photo.location.map.notification.NotificationManager;
import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map.utils.GPSUtils;
import chin.pswm.gps.photo.location.map.utils.ImageLocationExtractor;
import chin.pswm.gps.photo.location.map.utils.PermissionUtils;
import chin.pswm.gps.photo.location.map.utils.SpManager;
import chin.pswm.gps.photo.location.map.utils.StorageUtils;
import chin.pswm.gps.photo.location.map_debug.BuildConfig;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivityStartNewBinding;
import chin.pswm.gps.photo.location.map_debug.databinding.ProcessDialogLayoutBinding;
import chin.pswm.gps.photo.location.map_debug.databinding.RateDialogBinding;

@SuppressWarnings("all")

public class StartActivity extends BaseActivity implements OnClickGallery {
    public static ArrayList<PlaceData> placesArrayList = new ArrayList<>();
    public static int startFlag = 0;
    ActivityStartNewBinding binding;
    StartAdapter myCreationAdapter;
    public List<Uri> uriList = new ArrayList();
    public List<Uri> uriListVideo = new ArrayList();
    List<ImageLocationExtractor.LocationListener> locationListeners = new ArrayList();
    public int click = -1;
    NotificationManager notificationManager;
    ActivityResultLauncher<Intent> galleryResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult activityResult) {
            Intent data;
            if (activityResult.getResultCode() != -1 || (data = activityResult.getData()) == null) {
                return;
            }
            StartActivity.this.startActivity(new Intent(StartActivity.this, GalleryPreviewActivity.class).setFlags(536870912).putExtra("Path", data.getStringExtra("Choice")).putExtra("Type", 2));
        }
    });
    int i = 0;

    @Override
    public void onClickFolder(int i) {
    }

    PermissionUtils permissionUtils;

    @Override
    public void onCreate(Bundle bundle) {
        LanguageManager.setLocale(StartActivity.this, SharedHelper.getString(StartActivity.this, "lang_key", ""));
        super.onCreate(bundle);
        ActivityStartNewBinding inflate = ActivityStartNewBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        this.permissionUtils = new PermissionUtils(this);
        notificationManager = new NotificationManager(MyApplication.instance);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {  // Android 13+
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_MEDIA_IMAGES}, 100);
            }
        } else {  // Android 12 and below
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
            }
        }
//        initSocketConnection(this, true, true);
//        showDynamicNativeData(StartActivity.this, binding.AdNative1, null, true);

        int count = SharedHelper.getInt(StartActivity.this, "rate_dialog_counter", 0) + 1;
        SharedHelper.putInt(StartActivity.this, "rate_dialog_counter", count);
        if (count > 1) {
            showRateDialog(false);
        }

        setData();

        ComposeBannerKt.setBannerContent(binding.composeView,
                "StartActivity",
                BuildConfig.banner_home,
                "banner_home",
                BannerType.BANNER_COLLAPSIBLE
        );

        notificationManager.cancelNotification(NotificationManager.DAILY_NOTIFICATION);
        notificationManager.cancelNotification(23647623);
        requestExactAlarmPermission();
    }

    private static final int REQUEST_EXACT_ALARM = 1001;

    private void requestExactAlarmPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            if (!alarmManager.canScheduleExactAlarms()) {
                Intent intent = new Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
                startActivityForResult(intent, REQUEST_EXACT_ALARM);
            } else {
                notificationManager.scheduleReminder();
            }
        } else {
            notificationManager.scheduleReminder();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_EXACT_ALARM) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                if (alarmManager.canScheduleExactAlarms()) {
                    notificationManager.scheduleReminder();
                } else {
                    Toast.makeText(this, getString(R.string.alarm_perm_required), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }



    private void setData() {
        this.binding.compass.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                if (!checkPermissionStatus())
                    return;
                AdsManager.INSTANCE.showInterInApp(StartActivity.this, true, () -> {
                    StartActivity.this.m116xd0e95e84(view);
                    return null;
                });
            }
        });
        binding.rlEathView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdsManager.INSTANCE.showInterInApp(StartActivity.this, true, () -> {
                    Intent intent = new Intent(getApplicationContext(), EarthViewActivity.class);
                    startActivity(intent);
                    MyApplication.sendEvent("Home_Screen", "select_earth_view_feature");
                    return null;
                });
            }
        });
        binding.setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), settingActivity.class);
                startActivity(intent);
                MyApplication.sendEvent("Home_Screen", "select_setting");
            }
        });
        this.binding.routePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                if (!checkPermissionStatus())
                    return;
                AdsManager.INSTANCE.showInterInApp(StartActivity.this, true, () -> {
                    StartActivity.this.m117xf67d6785(view);
                    return null;
                });
            }
        });
        this.binding.advanceCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                if (!checkPermissionStatus())
                    return;
                AdsManager.INSTANCE.showInterInApp(StartActivity.this, true, () -> {
                    StartActivity.this.m120x1c117086(view);
                    return null;
                });
            }
        });
        this.binding.mapViewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                AdsManager.INSTANCE.showInterInApp(StartActivity.this, true, () -> {
                    StartActivity.this.m121x41a57987(view);
                    return null;
                });
            }
        });
        this.binding.photoGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                if (!checkPermissionStatus())
                    return;
                AdsManager.INSTANCE.showInterInApp(StartActivity.this, true, () -> {
                    StartActivity.this.m122x67398288(view);
                    return null;
                });
            }
        });
        this.binding.camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                if (!checkPermissionStatus())
                    return;
                AdsManager.INSTANCE.showInterInApp(StartActivity.this, true, () -> {
                    StartActivity.this.m123x8ccd8b89(view);
                    return null;
                });
            }
        });
        this.binding.gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                if (!checkPermissionStatus())
                    return;
                AdsManager.INSTANCE.showInterInApp(StartActivity.this, true, () -> {
                    StartActivity.this.m124xb261948a(view);
                    return null;
                });
            }
        });
        this.binding.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                AdsManager.INSTANCE.showInterInApp(StartActivity.this, true, () -> {
                    StartActivity.this.m125xd7f59d8b(view);
                    return null;
                });
            }
        });
        this.binding.template.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                AdsManager.INSTANCE.showInterInApp(StartActivity.this, true, () -> {
                    StartActivity.this.m126xfd89a68c(view);
                    return null;
                });
            }
        });
        this.binding.albumVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                AdsManager.INSTANCE.showInterInApp(StartActivity.this, true, () -> {
                    StartActivity.this.m127x231daf8d(view);
                    return null;
                });
            }
        });
        this.binding.album.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                AdsManager.INSTANCE.showInterInApp(StartActivity.this, true, () -> {
                    StartActivity.this.m118xb296b2fd(view);
                    return null;
                });
            }
        });
        this.binding.videoCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                if (!checkPermissionStatus())
                    return;
                AdsManager.INSTANCE.showInterInApp(StartActivity.this, true, () -> {
                    StartActivity.this.m119xd82abbfe(view);
                    return null;
                });
            }
        });
    }


    public void m116xd0e95e84(View view) {
        startActivity(new Intent(this, CompassActivity.class));
        MyApplication.sendEvent("Home_Screen", "select_compass_feature");
    }

    public void m117xf67d6785(View view) {
        startActivity(new Intent(this, RoutePlanerActivity.class).setFlags(536870912));
        MyApplication.sendEvent("Home_Screen", "select_route_plan_feature");
    }


    public void m120x1c117086(View view) {
        this.click = 1;
        if (!GPSUtils.isGPSEnabled(this)) {
            GPSUtils.showGPSDialog(this);
        } else {
            callAds();
        }
    }


    public void m121x41a57987(View view) {
        this.click = 2;
        if (!GPSUtils.isGPSEnabled(this)) {
            GPSUtils.showGPSDialog(this);
        } else {
            callAds();
        }
    }


    public void m122x67398288(View view) {
        this.click = 3;
        if (!GPSUtils.isGPSEnabled(this)) {
            GPSUtils.showGPSDialog(this);
        } else {
            callAds();
        }
    }


    public void m123x8ccd8b89(View view) {
        this.click = 4;
        if (!GPSUtils.isGPSEnabled(this)) {
            GPSUtils.showGPSDialog(this);
        } else {
            callAds();
        }
    }


    public void m124xb261948a(View view) {
        this.click = 5;
        if (!GPSUtils.isGPSEnabled(this)) {
            GPSUtils.showGPSDialog(this);
        } else {
            callAds();
        }
    }


    public void m125xd7f59d8b(View view) {
        this.click = 6;
        if (!GPSUtils.isGPSEnabled(this)) {
            GPSUtils.showGPSDialog(this);
        } else {
            callAds();
        }
    }

    public void m126xfd89a68c(View view) {
        this.click = 7;
        if (!GPSUtils.isGPSEnabled(this)) {
            GPSUtils.showGPSDialog(this);
        } else {
            callAds();
        }
    }


    public void m127x231daf8d(View view) {
        this.click = 8;
        MyCreationActivity.cluster = null;
        callAds();
    }


    public void m118xb296b2fd(View view) {
        this.click = 9;
        MyCreationActivity.cluster = null;
        callAds();
    }


    public void m119xd82abbfe(View view) {
        this.click = 10;
        if (!GPSUtils.isGPSEnabled(this)) {
            GPSUtils.showGPSDialog(this);
        } else {
            callAds();
        }
    }

    public void callAds() {
        if (AdsVariable.startFlagOnline.equalsIgnoreCase("0")) {
            startFlag = 0;
        }
        if (startFlag % 2 == 0) {
            StartActivity.this.nextCall();
        } else {
            nextCall();
        }
        startFlag++;
    }

    public void nextCall() {
        switch (this.click) {
            case 1:
                startActivity(new Intent(this, AdvanceCameraActivity.class).setFlags(536870912));
                MyApplication.sendEvent("Home_Screen", "select_gps_camera_feature");
                return;
            case 2:
                startActivity(new Intent(this, MapViewActivity.class).setFlags(536870912));
                MyApplication.sendEvent("Home_Screen", "select_map_view_feature");
                return;
            case 3:
                startActivity(new Intent(this, GridCameraActivity.class).setFlags(536870912).putExtra("Type", 0));
                MyApplication.sendEvent("Home_Screen", "select_photoGrid_feature");
                return;
            case 4:
                startActivity(new Intent(this, CameraActivity.class).setFlags(536870912));
                MyApplication.sendEvent("Home_Screen", "select_camera_feature");
                return;
            case 5:
                this.galleryResult.launch(new Intent(this, GalleryActivity.class).setFlags(536870912));
                MyApplication.sendEvent("Home_Screen", "select_gallery_feature");
                return;
            case 6:
                startActivity(new Intent(this, LocationActivity.class).setFlags(536870912));
                MyApplication.sendEvent("Home_Screen", "select_location_feature");
                return;
            case 7:
                startActivity(new Intent(this, TemplateActivity.class).setFlags(536870912));
                MyApplication.sendEvent("Home_Screen", "select_template_feature");
                return;
            case 8:
                startActivity(new Intent(this, MyCreationActivity.class).putExtra("TYPE", 1).setFlags(536870912));
                return;
            case 9:
                startActivity(new Intent(this, MyCreationActivity.class).putExtra("TYPE", 0).setFlags(536870912));
                return;
            case 10:
                startActivity(new Intent(this, VideoActivity.class).setFlags(536870912));
                MyApplication.sendEvent("Home_Screen", "select_video_feature");
                return;
            default:
                return;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        new ProcessAsyncTask().execute(new String[0]);
        hideSystemNavigationBar();
    }

    public void setFragment() {
//        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
//        beginTransaction.replace(R.id.frameLayoutOne, new StartFragment(this));
//        beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        beginTransaction.commitAllowingStateLoss(); // Safe even after onSaveInstanceState
    }


    class ProcessAsyncTask extends AsyncTask<String, Void, String> {
        ProcessDialogLayoutBinding binding1;
        Dialog dialog;

        ProcessAsyncTask() {
        }


        public void onPreExecute() {
            super.onPreExecute();
            this.binding1 = ProcessDialogLayoutBinding.inflate(StartActivity.this.getLayoutInflater());
            Dialog dialog2 = new Dialog(StartActivity.this);
            this.dialog = dialog2;
            dialog2.getWindow().requestFeature(1);
            this.dialog.setContentView(this.binding1.getRoot());
            this.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    return true;
                }
            });
            Glide.with((FragmentActivity) StartActivity.this).load(Integer.valueOf(R.drawable.loading)).into(this.binding1.gif);
//            this.dialog.show();
        }


        public String doInBackground(String[] strArr) {
            StartActivity startActivity = StartActivity.this;
            startActivity.uriList = StorageUtils.getFolderData(StorageUtils.create_folder(startActivity.getResources().getString(R.string.app_name)));
            StartActivity startActivity2 = StartActivity.this;
            startActivity2.uriListVideo = StorageUtils.getFolderDataVideo(StorageUtils.create_folder(startActivity2.getResources().getString(R.string.app_name)));
            return null;
        }


        public void onPostExecute(String str) {
            if (StartActivity.this.uriList == null || StartActivity.this.uriList.size() <= 0) {
//                this.dialog.dismiss();
                StartActivity.this.setFragment();
            } else {
                StartActivity startActivity = StartActivity.this;
                new ImageLocationExtractor(startActivity, startActivity.uriList, StartActivity.this.locationListeners, new ImageLocationExtractor.ProcessComplete() {
                    public void locationListGet() {
                        ProcessAsyncTask.this.dialog.dismiss();
                        StartActivity.this.setFragment();
                    }
                }).start();
            }
            if (StartActivity.this.uriList == null || StartActivity.this.uriList.size() <= 0) {
//                StartActivity.this.binding.myCreation.setVisibility(8);
            } else {
//                StartActivity.this.binding.myCreation.setVisibility(0);
                StartActivity startActivity2 = StartActivity.this;
                StartActivity startActivity3 = StartActivity.this;
                startActivity2.myCreationAdapter = new StartAdapter(startActivity3, startActivity3.uriList, StartActivity.this);
                StartActivity.this.binding.recyclerview.setAdapter(StartActivity.this.myCreationAdapter);
            }
            if (StartActivity.this.uriListVideo == null || StartActivity.this.uriListVideo.size() <= 0) {
//                StartActivity.this.binding.myCreationVideo.setVisibility(8);
                return;
            }
//            StartActivity.this.binding.myCreationVideo.setVisibility(0);
            StartActivity startActivity4 = StartActivity.this;
            StartActivity startActivity5 = StartActivity.this;
            startActivity4.myCreationAdapter = new StartAdapter(startActivity5, startActivity5.uriListVideo, StartActivity.this);
            StartActivity.this.binding.recyclerviewVideo.setAdapter(StartActivity.this.myCreationAdapter);
        }
    }

    @Override
    public void onBackPressed() {
        int count = SharedHelper.getInt(StartActivity.this, "rate_dialog_counter", 0);
        if (count == 1 || count == 3 || count == 5) {
            showRateDialog(true);
        } else {
            temp();
        }
    }

    public void temp() {
        final Dialog dialog = new Dialog(StartActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.newdail);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        showDynamicNativeData(this, dialog.findViewById(R.id.Ad_Native), null, true);
        ((TextView) dialog.findViewById(R.id.iv_yes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finishAffinity();
            }
        });
        ((TextView) dialog.findViewById(R.id.iv_no)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onClickItem(String str) {
        if (str.contains(".mp4")) {
            startActivity(new Intent(this, VideoPreviewActivity.class).setFlags(536870912).putExtra("Path", str));
        } else {
            startActivity(new Intent(this, PreviewActivity.class).setFlags(536870912).putExtra("Path", str));
        }
    }

    private void showRateDialog(boolean isFromExit) {

        if (SharedHelper.getBoolean(getApplicationContext(), "is_user_already_rated", false)) {
//            StartActivity.this.m133x7f7b49d1(null, null);
            if (isFromExit)
                temp();
            return;
        }

        final RateDialogBinding inflate = RateDialogBinding.inflate(getLayoutInflater());
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().requestFeature(1);
        dialog.setContentView(inflate.getRoot());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return true;
            }
        });
        dialog.show();
        inflate.star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                StartActivity.this.m128xc3971ccc(inflate, view);
            }
        });
        inflate.star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                StartActivity.this.m129xe92b25cd(inflate, view);
            }
        });
        inflate.star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                StartActivity.this.m130xebf2ece(inflate, view);
            }
        });
        inflate.star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                StartActivity.this.m131x345337cf(inflate, view);
            }
        });
        inflate.star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                StartActivity.this.m132x59e740d0(inflate, view);
            }
        });
        inflate.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
//                StartActivity.this.m133x7f7b49d1(dialog, view);
                dialog.dismiss();
                if (isFromExit)
                    temp();
            }
        });
        inflate.rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                StartActivity.this.m136x55c21e9(dialog, view);
            }
        });
    }


    public void m128xc3971ccc(RateDialogBinding rateDialogBinding, View view) {
        this.i = 1;
        rateDialogBinding.star1.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
        rateDialogBinding.star2.setImageDrawable(getResources().getDrawable(R.drawable.star_unfill));
        rateDialogBinding.star3.setImageDrawable(getResources().getDrawable(R.drawable.star_unfill));
        rateDialogBinding.star4.setImageDrawable(getResources().getDrawable(R.drawable.star_unfill));
        rateDialogBinding.star5.setImageDrawable(getResources().getDrawable(R.drawable.star_unfill));
    }


    public void m129xe92b25cd(RateDialogBinding rateDialogBinding, View view) {
        this.i = 2;
        rateDialogBinding.star1.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
        rateDialogBinding.star2.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
        rateDialogBinding.star3.setImageDrawable(getResources().getDrawable(R.drawable.star_unfill));
        rateDialogBinding.star4.setImageDrawable(getResources().getDrawable(R.drawable.star_unfill));
        rateDialogBinding.star5.setImageDrawable(getResources().getDrawable(R.drawable.star_unfill));
    }


    public void m130xebf2ece(RateDialogBinding rateDialogBinding, View view) {
        this.i = 3;
        rateDialogBinding.star1.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
        rateDialogBinding.star2.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
        rateDialogBinding.star3.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
        rateDialogBinding.star4.setImageDrawable(getResources().getDrawable(R.drawable.star_unfill));
        rateDialogBinding.star5.setImageDrawable(getResources().getDrawable(R.drawable.star_unfill));
    }


    public void m131x345337cf(RateDialogBinding rateDialogBinding, View view) {
        this.i = 4;
        rateDialogBinding.star1.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
        rateDialogBinding.star2.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
        rateDialogBinding.star3.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
        rateDialogBinding.star4.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
        rateDialogBinding.star5.setImageDrawable(getResources().getDrawable(R.drawable.star_unfill));
    }


    public void m132x59e740d0(RateDialogBinding rateDialogBinding, View view) {
        this.i = 5;
        rateDialogBinding.star1.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
        rateDialogBinding.star2.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
        rateDialogBinding.star3.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
        rateDialogBinding.star4.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
        rateDialogBinding.star5.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
    }


    public void m133x7f7b49d1(Dialog dialog, View view) {
        List<ActivityManager.AppTask> appTasks;
        if (dialog != null)
            dialog.dismiss();

        ActivityManager activityManager = (ActivityManager) getSystemService("activity");
        if (activityManager == null || (appTasks = activityManager.getAppTasks()) == null || appTasks.size() <= 0) {
            return;
        }
        for (ActivityManager.AppTask appTask : appTasks) {
            appTask.finishAndRemoveTask();
        }
    }


    public void m136x55c21e9(Dialog dialog, View view) {
        SpManager.setRate_Which(this.i);
        int i = this.i;
        if (i == 0) {
            Toast.makeText(this, getResources().getString(R.string.gps80), 0).show();
            return;
        } /*else if (i > 1 && i <= 3) {
            SpManager.setRate_Which(i);
            Intent intent = new Intent("android.intent.action.SENDTO");
            intent.setType("text/plain");
            intent.setData(Uri.parse(MailTo.MAILTO_SCHEME));
            intent.putExtra("android.intent.extra.EMAIL", new String[]{"abcd@gmail.com"});
            intent.putExtra("android.intent.extra.SUBJECT", "Response For the Live Football TV - Live Score Application");
            intent.putExtra("android.intent.extra.TEXT", "Thank you For your Response. If you have Any Suggestion then You may type Here. \n");
            intent.setPackage("com.google.android.gm");
            try {
                startActivity(Intent.createChooser(intent, "Send mail..."));
            } catch (ActivityNotFoundException unused) {
                Toast.makeText(this, getResources().getString(R.string.gps81), 0).show();
            }
            dialog.dismiss();
            new Handler().postDelayed(new Runnable() {
                @Override
                public final void run() {
                    StartActivity.this.m134xa50f52d2();
                }
            }, 2000L);
        }*/ else if (i > 3) {
            SharedHelper.putBoolean(getApplicationContext(), "is_user_already_rated", true);
            SpManager.setRate_Which(i);
            try {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getPackageName())));
            } catch (ActivityNotFoundException unused2) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
            }
            /*dialog.dismiss();
            new Handler().postDelayed(new Runnable() {
                @Override
                public final void run() {
                    StartActivity.this.m135xcaa35bd3();
                }
            }, 2000L);*/
        }
        dialog.dismiss();
//        StartActivity.this.m135xcaa35bd3();
    }


    public void m134xa50f52d2() {
        List<ActivityManager.AppTask> appTasks;
        ActivityManager activityManager = (ActivityManager) getSystemService("activity");
        if (activityManager == null || (appTasks = activityManager.getAppTasks()) == null || appTasks.size() <= 0) {
            return;
        }
        for (ActivityManager.AppTask appTask : appTasks) {
            appTask.finishAndRemoveTask();
        }
    }


    public void m135xcaa35bd3() {
        List<ActivityManager.AppTask> appTasks;
        ActivityManager activityManager = (ActivityManager) getSystemService("activity");
        if (activityManager == null || (appTasks = activityManager.getAppTasks()) == null || appTasks.size() <= 0) {
            return;
        }
        for (ActivityManager.AppTask appTask : appTasks) {
            appTask.finishAndRemoveTask();
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!DailyNotificationType.values().equals(null) && DailyNotificationType.values().length > 0) {
            DailyNotificationType[] values = DailyNotificationType.values();
            DailyNotificationType itemRandom = values[new Random().nextInt(values.length)];
            notificationManager.setNotification(
                    23647623,
                    5,
                    getString(itemRandom.getTitle()),
                    getString(itemRandom.getDescription()),
                    R.drawable.img_camera
            );
        }
    }

    public boolean checkPermissionStatus() {
        PermissionUtils permissionUtils = this.permissionUtils;
        if (permissionUtils.checkPermissionn(StartActivity.this, permissionUtils.allPermissions)) {
            return true;
        }
        permissionUtils.callPermission(permissionUtils.allPermissions, permissionUtils.ALL_PERMISSIONS_REQUEST_CODE);
        return false;
    }
}
