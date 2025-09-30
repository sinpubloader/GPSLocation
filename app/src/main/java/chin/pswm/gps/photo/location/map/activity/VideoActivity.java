package chin.pswm.gps.photo.location.map.activity;

import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showUserInterDataBack;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import androidx.core.app.ActivityCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.otaliastudios.cameraview.CameraException;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraOptions;
import com.otaliastudios.cameraview.PictureResult;
import com.otaliastudios.cameraview.VideoResult;
import com.otaliastudios.cameraview.controls.Facing;
import com.otaliastudios.cameraview.controls.Flash;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

import chin.pswm.gps.photo.location.map.AllKeyHub;
import chin.pswm.gps.photo.location.map.adapter.MyPagerAdapter;
import chin.pswm.gps.photo.location.map.ads.AdsManager;
import chin.pswm.gps.photo.location.map.ads.adunit.banner.BannerType;
import chin.pswm.gps.photo.location.map.compose.ComposeBannerKt;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map.utils.Common;
import chin.pswm.gps.photo.location.map.utils.Resizer;
import chin.pswm.gps.photo.location.map.utils.SpManager;
import chin.pswm.gps.photo.location.map.utils.StorageUtils;
import chin.pswm.gps.photo.location.map_debug.BuildConfig;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivityVideoBinding;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

@SuppressWarnings("all")

public class VideoActivity extends BaseActivity implements OnMapReadyCallback, LocationListener {
    ActivityVideoBinding binding;
    double latitude;
    public Location location;
    LocationManager locationManager;
    double longitude;
    private GoogleMap mMap;
    boolean flash = false;
    public long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    public long MIN_TIME_BW_UPDATES = 60000;
    private long mLastClickTime = 0;
    public ArrayList<Integer> layoutArrayList = Common.getLayoutList();
    private Handler customHandler = new Handler();
    private long startTime = 0;
    long timeInMilliseconds = 0;
    long timeSwapBuff = 0;
    long updatedTime = 0;
    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            VideoActivity.this.timeInMilliseconds = SystemClock.uptimeMillis() - VideoActivity.this.startTime;
            VideoActivity videoActivity = VideoActivity.this;
            videoActivity.updatedTime = videoActivity.timeSwapBuff + VideoActivity.this.timeInMilliseconds;
            int i = (int) (VideoActivity.this.updatedTime / 1000);
            VideoActivity.this.binding.counter.setText("" + (i / 60) + ":" + String.format("%02d", Integer.valueOf(i % 60)));
            VideoActivity.this.customHandler.postDelayed(this, 0L);
        }
    };
    CameraListener cameraListener = new AnonymousClass2();


    @Override
    public void onCreate(Bundle bundle) {
        LanguageManager.setLocale(VideoActivity.this, SharedHelper.getString(VideoActivity.this, "lang_key", ""));

        super.onCreate(bundle);
        ActivityVideoBinding inflate = ActivityVideoBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        this.locationManager = (LocationManager) getSystemService("location");
        this.binding.mapView.onCreate(bundle);
        this.binding.mapView.getMapAsync(this);
        this.binding.mapView.onResume();
        initSocketConnection(this, true, true);

        setData();

        ComposeBannerKt.setBannerContent(binding.composeView,
                "VideoActivity",
                BuildConfig.banner_inapp,
                "banner_inapp",
                BannerType.BANNER_ADAPTIVE
        );
    }

    private void setData() {
        this.binding.counter.setText("00:00");
        this.binding.capture.setImageDrawable(getResources().getDrawable(R.drawable.effect_video_start));
        this.binding.camera.setLifecycleOwner(this);
        this.binding.camera.open();
        this.binding.camera.addCameraListener(this.cameraListener);
        if (this.binding.camera.getFacing() == Facing.BACK) {
            this.binding.flash.setVisibility(0);
        } else {
            this.binding.flash.setVisibility(4);
        }
        this.binding.flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                VideoActivity.this.m139xb96d859d(view);
            }
        });
        this.binding.flash.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                VideoActivity.this.m140xdf018e9e(view);
            }
        });
        this.binding.capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                VideoActivity.this.m141x495979f(view);
            }
        });
        this.binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                showUserInterDataBack(VideoActivity.this, new AllKeyHub.onCrashDataClose() {
                    @Override
                    public void onDataClose() {
                        AdsManager.INSTANCE.showInterInApp(
                                VideoActivity.this,
                                false,
                                new Function0<Unit>() {
                                    @Override
                                    public Unit invoke() {
                                        VideoActivity.super.onBackPressed();
                                        return null;
                                    }
                                }
                        );
                    }
                });
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

    public void m139xb96d859d(View view) {
        if (this.binding.camera.getFacing() == Facing.BACK) {
            this.binding.camera.setFacing(Facing.FRONT);
            this.binding.flash.setVisibility(4);
            return;
        }
        this.binding.camera.setFacing(Facing.BACK);
        this.binding.flash.setVisibility(0);
        this.binding.flash.setImageDrawable(getResources().getDrawable(R.drawable.effect_flash_off));
    }

    public void m140xdf018e9e(View view) {
        if (this.flash) {
            this.flash = false;
            this.binding.camera.setFlash(Flash.OFF);
            this.binding.flash.setImageDrawable(getResources().getDrawable(R.drawable.effect_flash_off));
            return;
        }
        this.flash = true;
        this.binding.camera.setFlash(Flash.TORCH);
        this.binding.flash.setImageDrawable(getResources().getDrawable(R.drawable.effect_flash_on));
    }


    public void m141x495979f(View view) {
        if (SystemClock.elapsedRealtime() - this.mLastClickTime < 1000) {
            return;
        }
        this.mLastClickTime = SystemClock.elapsedRealtime();
        if (this.binding.camera.isTakingVideo()) {
            this.binding.camera.stopVideo();
            this.binding.capture.setImageDrawable(getResources().getDrawable(R.drawable.effect_video_start));
            stopTimer();
            this.binding.counter.setText("00:00");
            return;
        }
        this.binding.camera.takeVideoSnapshot(new File(StorageUtils.create_folder(getResources().getString(R.string.app_name)), "Video_" + String.valueOf(System.currentTimeMillis()) + ".mp4"));
        this.binding.capture.setImageDrawable(getResources().getDrawable(R.drawable.effect_video_stop));
        startTimer();
    }

    public void m142x2a29a0a0(View view) {
        onBackPressed();
    }

    public void startTimer() {
        this.startTime = 0L;
        this.timeInMilliseconds = 0L;
        this.timeSwapBuff = 0L;
        this.updatedTime = 0L;
        this.startTime = SystemClock.uptimeMillis();
        this.customHandler.postDelayed(this.updateTimerThread, 0L);
    }

    public void stopTimer() {
        this.timeSwapBuff += this.timeInMilliseconds;
        this.customHandler.removeCallbacks(this.updateTimerThread);
    }


    public class AnonymousClass2 extends CameraListener {
        AnonymousClass2() {
        }

        @Override
        public void onCameraOpened(CameraOptions cameraOptions) {
            super.onCameraOpened(cameraOptions);
        }

        @Override
        public void onCameraClosed() {
            super.onCameraClosed();
        }

        @Override
        public void onCameraError(CameraException cameraException) {
            super.onCameraError(cameraException);
        }

        @Override
        public void onPictureTaken(PictureResult pictureResult) {
            super.onPictureTaken(pictureResult);
        }

        @Override
        public void onVideoTaken(VideoResult videoResult) {
            super.onVideoTaken(videoResult);
            final File file = videoResult.getFile();
            VideoActivity.this.runOnUiThread(new Runnable() {
                @Override
                public final void run() {
                    VideoActivity.AnonymousClass2.this.m144x11ae0624(file);
                }
            });
        }

        public void m144x11ae0624(File file) {
            VideoActivity.this.startActivity(new Intent(VideoActivity.this, VideoPreviewActivity.class).setFlags(536870912).putExtra("Path", file.getAbsolutePath()));
        }

        @Override
        public void onOrientationChanged(int i) {
            super.onOrientationChanged(i);
        }

        @Override
        public void onAutoFocusStart(PointF pointF) {
            super.onAutoFocusStart(pointF);
        }

        @Override
        public void onAutoFocusEnd(boolean z, PointF pointF) {
            super.onAutoFocusEnd(z, pointF);
        }

        @Override
        public void onZoomChanged(float f, float[] fArr, PointF[] pointFArr) {
            super.onZoomChanged(f, fArr, pointFArr);
        }

        @Override
        public void onExposureCorrectionChanged(float f, float[] fArr, PointF[] pointFArr) {
            super.onExposureCorrectionChanged(f, fArr, pointFArr);
        }

        @Override
        public void onVideoRecordingStart() {
            super.onVideoRecordingStart();
        }

        @Override
        public void onVideoRecordingEnd() {
            super.onVideoRecordingEnd();
        }

        @Override
        public void onPictureShutter() {
            super.onPictureShutter();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        setMapData(location);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            this.mMap.setMyLocationEnabled(false);
            getLocation();
        }
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            try {
                try {
                    this.locationManager.requestLocationUpdates("network", this.MIN_TIME_BW_UPDATES, (float) this.MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    LocationManager locationManager = this.locationManager;
                    if (locationManager != null) {
                        Location lastKnownLocation = locationManager.getLastKnownLocation("network");
                        this.location = lastKnownLocation;
                        setMapData(lastKnownLocation);
                    }
                } catch (Exception unused) {
                }
            } catch (Exception unused2) {
                this.locationManager.requestLocationUpdates("gps", 10L, 0.0f, this);
                LocationManager locationManager2 = this.locationManager;
                if (locationManager2 != null) {
                    Location lastKnownLocation2 = locationManager2.getLastKnownLocation("gps");
                    this.location = lastKnownLocation2;
                    setMapData(lastKnownLocation2);
                }
            }
        }
    }

    public void setMapData(Location location) {
        if (location != null) {
            Log.d("TAG", "getLocation: if 2");
            try {
                Resizer.getheightandwidth(this);
                if (SpManager.getSelectTemplate() == 1) {
                    Resizer.setSize(this.binding.mapRl, 245, 260, true);
                } else if (SpManager.getSelectTemplate() == 2) {
                    Resizer.setSize(this.binding.mapRl, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, true);
                } else if (SpManager.getSelectTemplate() == 3) {
                    Resizer.setSize(this.binding.mapRl, 245, 260, true);
                } else if (SpManager.getSelectTemplate() == 5) {
                    Resizer.setSize(this.binding.mapRl, 218, 260, true);
                } else if (SpManager.getSelectTemplate() == 6) {
                    Resizer.setSize(this.binding.mapRl, 490, 145, true);
                } else if (SpManager.getSelectTemplate() == 7) {
                    Resizer.setSize(this.binding.mapRl, 218, 185, true);
                }
            } catch (Exception unused) {
            }
            try {
                if (SpManager.getSelectLocationType() == 0) {
                    this.latitude = location.getLatitude();
                    this.longitude = location.getLongitude();
                } else {
                    this.latitude = Double.parseDouble(SpManager.getLATITUDE());
                    this.longitude = Double.parseDouble(SpManager.getLONGITUDE());
                }
                this.binding.camera.setLocation(location);
                new WeatherDataFetcher(String.valueOf(this.latitude), String.valueOf(this.longitude)).execute(new Void[0]);
                LatLng latLng = new LatLng(this.latitude, this.longitude);
                String addressLine = new Geocoder(this, Locale.getDefault()).getFromLocation(latLng.latitude, latLng.longitude, 1).get(0).getAddressLine(0);
                this.mMap.clear();
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Current Position");
                markerOptions.icon(Common.BitmapFromVector(getApplicationContext(), R.drawable.map_location));
                this.mMap.addMarker(markerOptions);
                this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));
                Common.dateTemplate = StorageUtils.getCurrentDate("dd MMM yyyy");
                Common.timeTemplate = StorageUtils.getCurrentDate("h:mm a");
                Common.latTemplate = Common.formatNumber(this.latitude);
                Common.lonTemplate = Common.formatNumber(this.longitude);
                Common.addressTemplate = addressLine;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        VideoActivity.this.m143xce8e8533();
                    }
                }, 3000L);
            } catch (Exception unused2) {
            }
        }
    }

    public void m143xce8e8533() {
        this.mMap.snapshot(new GoogleMap.SnapshotReadyCallback() {
            @Override
            public void onSnapshotReady(Bitmap bitmap) {
                Common.locationDataModel.setMapImage(bitmap);
                Common.mapTemplate = bitmap;
                VideoActivity.this.binding.mapRl.setVisibility(8);
                VideoActivity videoActivity = VideoActivity.this;
                VideoActivity.this.binding.viewPager.setAdapter(new MyPagerAdapter(videoActivity, videoActivity.layoutArrayList));
            }
        });
    }


    public class WeatherDataFetcher extends AsyncTask<Void, Void, String> {
        private String API_KEY = "5b919eba028aef3bf10707088ddee3fd";
        private String API_URL;
        String latitude;
        String longitude;

        public WeatherDataFetcher(String str, String str2) {
            this.latitude = str;
            this.longitude = str2;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.API_URL = "https://api.openweathermap.org/data/2.5/weather?units=metric&lat=" + this.latitude + "&lon=" + this.longitude + "&appid=" + this.API_KEY;
        }


        @Override
        public String doInBackground(Void... voidArr) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(((HttpURLConnection) new URL(this.API_URL).openConnection()).getInputStream()));
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                    } else {
                        return sb.toString();
                    }
                }
            } catch (IOException unused) {
                return null;
            }
        }


        @Override
        public void onPostExecute(String str) {
            if (str != null) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    double d = jSONObject.getJSONObject("main").getDouble("temp");
                    String string = jSONObject.getJSONArray("weather").getJSONObject(0).getString("main");
                    Common.tempTemplate = d + "°C";
                    Common.disTemplate = string;
                    VideoActivity videoActivity = VideoActivity.this;
                    VideoActivity.this.binding.viewPager.setAdapter(new MyPagerAdapter(videoActivity, videoActivity.layoutArrayList));
                } catch (Exception unused) {
                    VideoActivity videoActivity2 = VideoActivity.this;
                    VideoActivity.this.binding.viewPager.setAdapter(new MyPagerAdapter(videoActivity2, videoActivity2.layoutArrayList));
                }
            }
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
