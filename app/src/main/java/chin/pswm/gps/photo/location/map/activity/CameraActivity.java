package chin.pswm.gps.photo.location.map.activity;

import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showUserInterDataBack;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.ItemTouchHelper;

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

import org.json.JSONObject;

import java.io.BufferedReader;
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
import chin.pswm.gps.photo.location.map.utils.Common;
import chin.pswm.gps.photo.location.map.utils.Resizer;
import chin.pswm.gps.photo.location.map.utils.SpManager;
import chin.pswm.gps.photo.location.map.utils.StorageUtils;
import chin.pswm.gps.photo.location.map_debug.BuildConfig;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivityCameraBinding;
import chin.pswm.gps.photo.location.map_debug.databinding.ProcessDialogLayoutBinding;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;


public class CameraActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {
    ActivityCameraBinding binding;
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
                    CameraActivity.this.binding.capture.setEnabled(true);
                    CameraActivity.this.binding.camera.setPreview(Preview.GL_SURFACE);
                    Common.locationDataModel.setCameraImage(bitmap);
                    CameraActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new ProcessAsyncTask(bitmap).execute(new String[0]);
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
        LanguageManager.setLocale(CameraActivity.this, SharedHelper.getString(CameraActivity.this, "lang_key", ""));
        super.onCreate(bundle);
        ActivityCameraBinding inflate = ActivityCameraBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());

        this.locationManager = (LocationManager) getSystemService("location");
        this.binding.mapView.onCreate(bundle);
        this.binding.mapView.getMapAsync(this);
        this.binding.mapView.onResume();
        initSocketConnection(this, true, true);

        setData();

        ComposeBannerKt.setBannerContent(binding.composeView,
                "CameraActivity",
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

    private void setData() {
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
                CameraActivity.this.m68xaa3ffec1(view);
            }
        });
        this.binding.flash.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                CameraActivity.this.m69x372d15e0(view);
            }
        });
        this.binding.capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                CameraActivity.this.m70xc41a2cff(view);
            }
        });
        this.binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                showUserInterDataBack(CameraActivity.this, new AllKeyHub.onCrashDataClose() {
                    @Override
                    public void onDataClose() {
                        AdsManager.INSTANCE.showInterInApp(
                                CameraActivity.this,
                                new Function0<Unit>() {
                                    @Override
                                    public Unit invoke() {
                                        CameraActivity.this.m71x5107441e(view);
                                        return null;
                                    }
                                }
                        );
                    }
                });
            }
        });
    }


    public void m68xaa3ffec1(View view) {
        if (this.binding.camera.getFacing() == Facing.BACK) {
            this.binding.camera.setFacing(Facing.FRONT);
            this.binding.flash.setVisibility(4);
            return;
        }
        this.binding.camera.setFacing(Facing.BACK);
        this.binding.flash.setVisibility(0);
        this.binding.flash.setImageDrawable(getResources().getDrawable(R.drawable.effect_flash_off));
    }

    public void m69x372d15e0(View view) {
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

    public void m70xc41a2cff(View view) {
        if (SystemClock.elapsedRealtime() - this.mLastClickTime < 1000) {
            return;
        }
        this.mLastClickTime = SystemClock.elapsedRealtime();
        this.binding.camera.setLocation(this.location);
        this.binding.camera.setPreview(Preview.SURFACE);
        this.binding.camera.takePictureSnapshot();
        this.binding.capture.setEnabled(false);
    }

    public void m71x5107441e(View view) {
        onBackPressed();
    }

    @Override
    public void onLocationChanged(Location location) {
        setMapData(location);
    }

    public class ProcessAsyncTask extends AsyncTask<String, Void, String> {
        ProcessDialogLayoutBinding binding1;
        Bitmap bitmap;
        Dialog dialog;
        String name;
        String path;

        public ProcessAsyncTask(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.binding1 = ProcessDialogLayoutBinding.inflate(CameraActivity.this.getLayoutInflater());
            Dialog dialog = new Dialog(CameraActivity.this);
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
            Glide.with((FragmentActivity) CameraActivity.this).load(Integer.valueOf((int) R.drawable.loading)).into(this.binding1.gif);
            this.dialog.show();
        }


        @Override
        public String doInBackground(String[] strArr) {
            this.path = StorageUtils.create_folder(CameraActivity.this.getResources().getString(R.string.app_name));
            String str = "Image_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
            this.name = str;
            try {
                String saveImageWithLocation = StorageUtils.saveImageWithLocation(CameraActivity.this, this.bitmap, this.path, str, Double.parseDouble(Common.latTemplate), Double.parseDouble(Common.lonTemplate));
                StorageUtils.scanFile(CameraActivity.this, saveImageWithLocation);
                return saveImageWithLocation;
            } catch (Exception unused) {
                return null;
            }
        }


        @Override
        public void onPostExecute(final String str) {
            try {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        CameraActivity.ProcessAsyncTask.this.m74xbd093aff(str);
                    }
                }, 2000L);
            } catch (Exception unused) {
                this.dialog.dismiss();
            }
        }

        public void m74xbd093aff(String str) {
            this.dialog.dismiss();
            if (str != null) {
                CameraActivity.this.startActivity(new Intent(CameraActivity.this, PreviewActivity.class).setFlags(536870912).putExtra("Path", str));
            }
        }
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
                Common.locationDataModel.setLatitude(this.latitude);
                Common.locationDataModel.setLongitude(this.longitude);
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
                Common.locationDataModel.setAddress(addressLine);
                Common.locationDataModel.setDate("" + StorageUtils.getCurrentDate("MMM , dd , yyyy"));
                Common.locationDataModel.setLat1("" + this.latitude);
                Common.locationDataModel.setLng1("" + this.longitude);
                Common.locationDataModel.setLat2("" + convertDecimalToDMS(this.latitude));
                Common.locationDataModel.setLng2("" + convertDecimalToDMS(this.longitude));
                new Locale("en");
                Common.dateTemplate = StorageUtils.getCurrentDate("dd MMM yyyy");
                Common.timeTemplate = StorageUtils.getCurrentDate("h:mm a");
                Common.latTemplate = Common.formatNumber(this.latitude);
                Common.lonTemplate = Common.formatNumber(this.longitude);
                Common.addressTemplate = addressLine;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        CameraActivity.this.m72x393ef1eb();
                    }
                }, 3000L);
            } catch (Exception unused2) {
            }
        }
    }

    public void m72x393ef1eb() {
        this.mMap.snapshot(new GoogleMap.SnapshotReadyCallback() {
            @Override
            public void onSnapshotReady(Bitmap bitmap) {
                Common.locationDataModel.setMapImage(bitmap);
                Common.mapTemplate = bitmap;
                CameraActivity.this.binding.mapRl.setVisibility(8);
                CameraActivity cameraActivity = CameraActivity.this;
                CameraActivity.this.binding.viewPager.setAdapter(new MyPagerAdapter(cameraActivity, cameraActivity.layoutArrayList));
            }
        });
    }

    public static String convertDecimalToDMS(double d) {
        int i = (int) d;
        double d2 = d - i;
        int i2 = (int) (d2 * 60.0d);
        return String.format("%d° %d' %.2f\"", Integer.valueOf(i), Integer.valueOf(i2), Double.valueOf((d2 - (i2 / 60.0d)) * 3600.0d));
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
            Log.d("TAG", "onPreExecute:  " + this.API_URL);
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
            } catch (IOException e) {
                Log.e("WeatherDataFetcher", "Error fetching weather data", e);
                return null;
            }
        }


        @Override
        public void onPostExecute(String str) {
            if (str != null) {
                try {
                    Log.d("TAG", "onPostExecute: " + str);
                    JSONObject jSONObject = new JSONObject(str);
                    double d = jSONObject.getJSONObject("main").getDouble("temp");
                    Common.locationDataModel.setTemp(d + "°C");
                    String string = jSONObject.getJSONArray("weather").getJSONObject(0).getString("main");
                    Common.tempTemplate = d + "°C";
                    Common.disTemplate = string;
                    CameraActivity cameraActivity = CameraActivity.this;
                    CameraActivity.this.binding.viewPager.setAdapter(new MyPagerAdapter(cameraActivity, cameraActivity.layoutArrayList));
                } catch (Exception unused) {
                    CameraActivity cameraActivity2 = CameraActivity.this;
                    CameraActivity.this.binding.viewPager.setAdapter(new MyPagerAdapter(cameraActivity2, cameraActivity2.layoutArrayList));
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


