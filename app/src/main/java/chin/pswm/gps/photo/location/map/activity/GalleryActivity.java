package chin.pswm.gps.photo.location.map.activity;

import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showUserInterDataBack;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import chin.pswm.gps.photo.location.map.AllKeyHub;
import chin.pswm.gps.photo.location.map.adapter.FolderAdapter;
import chin.pswm.gps.photo.location.map.adapter.ItemAdapter;
import chin.pswm.gps.photo.location.map.interfaces.OnClickGallery;
import chin.pswm.gps.photo.location.map.interfaces.OnProgressUpdate;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.model.GalleryModel;
import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map.utils.Common;
import chin.pswm.gps.photo.location.map.utils.GalleryAsyncTask;
import chin.pswm.gps.photo.location.map.utils.Resizer;
import chin.pswm.gps.photo.location.map.utils.SpManager;
import chin.pswm.gps.photo.location.map.utils.StorageUtils;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivityGalleryBinding;
import chin.pswm.gps.photo.location.map_debug.databinding.ProcessDialogLayoutBinding;
@SuppressWarnings("all")

public class GalleryActivity extends BaseActivity implements OnClickGallery, OnMapReadyCallback, LocationListener {
    ActivityGalleryBinding binding;
    ProcessDialogLayoutBinding binding1;
    Dialog dialog;
    FolderAdapter folderAdapter;
    ItemAdapter itemAdapter;
    double latitude;
    public Location location;
    LocationManager locationManager;
    double longitude;
    private GoogleMap mMap;
    ArrayList<GalleryModel> arrayList = new ArrayList<>();
    public long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    public long MIN_TIME_BW_UPDATES = 60000;

    
    @Override 
    public void onCreate(Bundle bundle) {
        LanguageManager.setLocale(GalleryActivity.this, SharedHelper.getString(GalleryActivity.this, "lang_key", ""));

        super.onCreate(bundle);
        ActivityGalleryBinding inflate = ActivityGalleryBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate.getRoot());
        this.locationManager = (LocationManager) getSystemService("location");
        this.binding.mapView.onCreate(bundle);
        this.binding.mapView.getMapAsync(this);
        this.binding.mapView.onResume();
        this.binding1 = ProcessDialogLayoutBinding.inflate(getLayoutInflater());
        Dialog dialog = new Dialog(this);
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
        initSocketConnection(this, true, true);

        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.loading)).into(this.binding1.gif);
        setSize();
        setData();
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
        this.folderAdapter = new FolderAdapter(this, this.arrayList, this);
        this.binding.folder.setAdapter(this.folderAdapter);
        new GalleryAsyncTask(this, new OnProgressUpdate() {
            @Override
            public void onTaskStart() {
                GalleryActivity.this.dialog.show();
            }

            @Override
            public void onComplete(ArrayList<GalleryModel> arrayList) {
                GalleryActivity.this.dialog.dismiss();
                GalleryActivity.this.arrayList = arrayList;
                if (arrayList != null && arrayList.size() > 0) {
                    GalleryActivity.this.binding.noData.setVisibility(8);
                    GalleryActivity.this.binding.item.setVisibility(8);
                    GalleryActivity.this.binding.folder.setVisibility(0);
                    GalleryActivity.this.folderAdapter.updateAdapter(arrayList);
                    return;
                }
                GalleryActivity.this.binding.item.setVisibility(8);
                GalleryActivity.this.binding.folder.setVisibility(8);
                GalleryActivity.this.binding.noData.setVisibility(0);
            }
        }).execute(new Void[0]);
        this.binding.back.setOnClickListener(new View.OnClickListener() {
            @Override 
            public final void onClick(View view) {
                showUserInterDataBack(GalleryActivity.this, new AllKeyHub.onCrashDataClose() {
                    @Override
                    public void onDataClose() {
                        GalleryActivity.this.m78x27a3b0b4(view);

                    }
                });
            }
        });
    }

    public  void m78x27a3b0b4(View view) {
        onBackPressed();
    }

    private void setSize() {
        Resizer.getheightandwidth(this);
        Resizer.setSize(this.binding.noData, 500, 339, true);
        Resizer.setSize(this.binding.mapRl, 300, 400, true);
    }

    @Override
    public void onClickFolder(int i) {
        this.binding.folder.setVisibility(8);
        this.binding.item.setVisibility(0);
        this.itemAdapter = new ItemAdapter(this, this.arrayList.get(i).getItemList(), this);
        this.binding.item.setAdapter(this.itemAdapter);
    }

    @Override
    public void onClickItem(String str) {
        setResult(-1, new Intent().putExtra("Choice", str));
        finish();
    }

    @Override
    public void onBackPressed() {
        if (this.binding.item.getVisibility() == 0) {
            this.binding.item.setVisibility(8);
            this.binding.folder.setVisibility(0);
        } else if (this.binding.folder.getVisibility() == 0) {
            setResult(0, new Intent());
            finish();
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
            this.binding.mapRl.setVisibility(0);
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
                new WeatherDataFetcher(String.valueOf(this.latitude), String.valueOf(this.longitude)).execute(new Void[0]);
                LatLng latLng = new LatLng(this.latitude, this.longitude);
                List<Address> fromLocation = new Geocoder(this, Locale.getDefault()).getFromLocation(latLng.latitude, latLng.longitude, 1);
                String addressLine = fromLocation.get(0).getAddressLine(0);
                String locality = fromLocation.get(0).getLocality();
                String countryName = fromLocation.get(0).getCountryName();
                this.mMap.clear();
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Current Position");
                markerOptions.icon(Common.BitmapFromVector(getApplicationContext(), R.drawable.map_location));
                this.mMap.addMarker(markerOptions);
                this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));
                Common.cityTemplate = locality + ", " + countryName;
                Common.dateTemplate = StorageUtils.getCurrentDate("dd MMM yyyy");
                Common.timeTemplate = StorageUtils.getCurrentDate("h:mm a");
                Common.latTemplate = Common.formatNumber(this.latitude);
                Common.lonTemplate = Common.formatNumber(this.longitude);
                Common.addressTemplate = addressLine;
                new Handler().postDelayed(new Runnable() {
                    @Override 
                    public final void run() {
                        GalleryActivity.this.m79x4661bc87();
                    }
                }, 3000L);
            } catch (Exception unused2) {
            }
        }
    }



    public  void m79x4661bc87() {
        this.mMap.snapshot(new GoogleMap.SnapshotReadyCallback() {
            @Override 
            public void onSnapshotReady(Bitmap bitmap) {
                Common.mapTemplate = bitmap;
                GalleryActivity.this.binding.mapRl.setVisibility(8);
            }
        });
    }

    
    public class WeatherDataFetcher extends AsyncTask<Void, Void, String> {
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
            this.API_URL = "https://api.openweathermap.org/data/2.5/weather?units=metric&lat=" + this.latitude + "&lon=" + this.longitude + "&appid=5b919eba028aef3bf10707088ddee3fd";
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
                } catch (Exception unused) {
                }
            }
        }
    }
}
