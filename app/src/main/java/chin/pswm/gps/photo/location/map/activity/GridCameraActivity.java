package chin.pswm.gps.photo.location.map.activity;

import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showUserInterDataBack;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import androidx.core.app.ActivityCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.otaliastudios.cameraview.BitmapCallback;
import com.otaliastudios.cameraview.CameraException;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraOptions;
import com.otaliastudios.cameraview.PictureResult;
import com.otaliastudios.cameraview.VideoResult;
import com.otaliastudios.cameraview.controls.Facing;
import com.otaliastudios.cameraview.controls.Flash;
import com.otaliastudios.cameraview.controls.Preview;

import java.util.ArrayList;

import chin.pswm.gps.photo.location.map.AllKeyHub;
import chin.pswm.gps.photo.location.map.ads.AdsManager;
import chin.pswm.gps.photo.location.map.ads.adunit.banner.BannerType;
import chin.pswm.gps.photo.location.map.compose.ComposeBannerKt;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.photoGrid.activity.PG_CollageActivity;
import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map.utils.Common;
import chin.pswm.gps.photo.location.map.utils.SpManager;
import chin.pswm.gps.photo.location.map.utils.StorageUtils;
import chin.pswm.gps.photo.location.map_debug.BuildConfig;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivityGridCameraBinding;
import chin.pswm.gps.photo.location.map_debug.databinding.ProcessDialogLayoutBinding;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

@SuppressWarnings("all")


public class GridCameraActivity extends BaseActivity implements OnMapReadyCallback, LocationListener {
    boolean GpsStatus;
    ActivityGridCameraBinding binding;
    Bitmap cameraBitmap;
    double latitude;
    public Location location;
    LocationManager locationManager;
    double longitude;
    private GoogleMap mMap;
    boolean flash = false;
    public long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    public long MIN_TIME_BW_UPDATES = 60000;
    private long mLastClickTime = 0;
    CameraListener cameraListener = new CameraListener() {
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


        public void onPictureTaken(PictureResult pictureResult) {
            super.onPictureTaken(pictureResult);
            pictureResult.toBitmap(new BitmapCallback() {
                public void onBitmapReady(Bitmap bitmap) {
                    GridCameraActivity.this.binding.capture.setEnabled(true);
                    GridCameraActivity.this.binding.camera.setPreview(Preview.GL_SURFACE);
                    GridCameraActivity.this.cameraBitmap = bitmap;
                    GridCameraActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new ProcessAsyncTask().execute(new String[0]);
                        }
                    });
                }
            });
        }

        @Override
        public void onVideoTaken(VideoResult videoResult) {
            super.onVideoTaken(videoResult);
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
    };

    @Override
    public void onCreate(Bundle bundle) {
        LanguageManager.setLocale(GridCameraActivity.this, SharedHelper.getString(GridCameraActivity.this, "lang_key", ""));

        super.onCreate(bundle);
        ActivityGridCameraBinding inflate = ActivityGridCameraBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        this.locationManager = (LocationManager) getSystemService("location");
        this.binding.mapView.onCreate(bundle);
        this.binding.mapView.getMapAsync(this);
        this.binding.mapView.onResume();
        initSocketConnection(this, true, true);

        setData();

        ComposeBannerKt.setBannerContent(binding.composeView,
                "GridCameraActivity",
                BuildConfig.banner_inapp,
                "banner_inapp",
                BannerType.BANNER_ADAPTIVE
        );
    }

    private void setData() {
        this.binding.camera.setLifecycleOwner(this);
        this.binding.camera.open();
        this.binding.camera.addCameraListener(this.cameraListener);
        if (this.binding.camera.getFacing() == Facing.BACK) {
            this.binding.flash.setEnabled(true);
        } else {
            this.binding.flash.setEnabled(false);
        }
        this.binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                AdsManager.INSTANCE.showInterInApp(
                        GridCameraActivity.this,
                        false,
                        new Function0<Unit>() {
                            @Override
                            public Unit invoke() {
                                onBackPressed();
                                return null;
                            }
                        }
                );
            }
        });
        this.binding.flip.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                GridCameraActivity.this.m89x549114c6(view);
            }
        });
        this.binding.flash.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                GridCameraActivity.this.m90xe8cf8465(view);
            }
        });
        this.binding.capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                GridCameraActivity.this.m91x7d0df404(view);
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

    public void m88xc052a527(View view) {
        onBackPressed();
    }


    public void m89x549114c6(View view) {
        if (this.binding.camera.getFacing() == Facing.BACK) {
            this.binding.camera.setFacing(Facing.FRONT);
            this.binding.flash.setEnabled(false);
            return;
        }
        this.binding.camera.setFacing(Facing.BACK);
        this.binding.flash.setEnabled(true);
        this.binding.flash.setImageDrawable(getResources().getDrawable(R.drawable.effect_flash_off));
    }

    public void m90xe8cf8465(View view) {
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


    public void m91x7d0df404(View view) {
        if (SystemClock.elapsedRealtime() - this.mLastClickTime < 1000) {
            return;
        }
        this.mLastClickTime = SystemClock.elapsedRealtime();
        this.binding.camera.setPreview(Preview.SURFACE);
        this.binding.camera.takePicture();
        this.binding.capture.setEnabled(false);
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
            try {
                if (SpManager.getSelectLocationType() == 0) {
                    this.latitude = location.getLatitude();
                    this.longitude = location.getLongitude();
                } else {
                    this.latitude = Double.parseDouble(SpManager.getLATITUDE());
                    this.longitude = Double.parseDouble(SpManager.getLONGITUDE());
                }
                LatLng latLng = new LatLng(this.latitude, this.longitude);
                this.mMap.clear();
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Current Position");
                markerOptions.icon(Common.BitmapFromVector(getApplicationContext(), R.drawable.map_location));
                this.mMap.addMarker(markerOptions);
                this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));
                Common.latTemplate = Common.formatNumber(this.latitude);
                Common.lonTemplate = Common.formatNumber(this.longitude);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        GridCameraActivity.this.m92x2eff351();
                    }
                }, 3000L);
            } catch (Exception unused) {
            }
        }
    }


    public void m92x2eff351() {
        this.mMap.snapshot(new GoogleMap.SnapshotReadyCallback() {
            @Override
            public void onSnapshotReady(Bitmap bitmap) {
                Common.mapTemplate = bitmap;
                GridCameraActivity.this.binding.mapView.setVisibility(4);
            }
        });
    }


    public class ProcessAsyncTask extends AsyncTask<String, Void, String> {
        ProcessDialogLayoutBinding binding1;
        Dialog dialog;
        ArrayList<String> arrayList = new ArrayList<>();
        String mapPath = null;

        ProcessAsyncTask() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.binding1 = ProcessDialogLayoutBinding.inflate(GridCameraActivity.this.getLayoutInflater());
            Dialog dialog = new Dialog(GridCameraActivity.this);
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
            Glide.with((FragmentActivity) GridCameraActivity.this).load(Integer.valueOf((int) R.drawable.loading)).into(this.binding1.gif);
            this.dialog.show();
            this.arrayList.clear();
        }

        @Override
        public String doInBackground(String[] strArr) {
            GridCameraActivity gridCameraActivity = GridCameraActivity.this;
            final String saveImage = StorageUtils.saveImage(gridCameraActivity, gridCameraActivity.cameraBitmap, GridCameraActivity.this.getFilesDir().getAbsolutePath(), "camera_" + String.valueOf(System.currentTimeMillis()) + "_image.jpg");
            if (Common.mapTemplate == null) {
                GridCameraActivity.this.mMap.snapshot(new GoogleMap.SnapshotReadyCallback() {
                    @Override
                    public void onSnapshotReady(Bitmap bitmap) {
                        GridCameraActivity.this.binding.mapView.setVisibility(4);
                        ProcessAsyncTask processAsyncTask = ProcessAsyncTask.this;
                        processAsyncTask.mapPath = StorageUtils.saveImage(GridCameraActivity.this, Common.mapTemplate, GridCameraActivity.this.getFilesDir().getAbsolutePath(), "map_image.jpg");
                        ProcessAsyncTask.this.arrayList.add(saveImage);
                        ProcessAsyncTask.this.arrayList.add(ProcessAsyncTask.this.mapPath);
                    }
                });
                return null;
            }
            this.mapPath = StorageUtils.saveImage(GridCameraActivity.this, Common.mapTemplate, GridCameraActivity.this.getFilesDir().getAbsolutePath(), "map_" + String.valueOf(System.currentTimeMillis()) + "_image.jpg");
            this.arrayList.add(saveImage);
            this.arrayList.add(this.mapPath);
            return null;
        }

        @Override
        public void onPostExecute(String str) {
            this.dialog.dismiss();
            Intent intent = new Intent(GridCameraActivity.this, PG_CollageActivity.class);
            intent.putStringArrayListExtra("selectedImages", this.arrayList);
            intent.putExtra("imagesinTemplate", this.arrayList.size());
            intent.putExtra("latitude", GridCameraActivity.this.latitude);
            intent.putExtra("longitude", GridCameraActivity.this.longitude);
            GridCameraActivity.this.startActivity(intent);
            finish();
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
