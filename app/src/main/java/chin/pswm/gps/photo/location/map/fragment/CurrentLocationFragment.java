package chin.pswm.gps.photo.location.map.fragment;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map_debug.databinding.FragmentCurrentLocationBinding;
import chin.pswm.gps.photo.location.map.utils.Common;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
@SuppressWarnings("all")

public class CurrentLocationFragment extends Fragment implements OnMapReadyCallback, LocationListener {
    public long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    public long MIN_TIME_BW_UPDATES = 60000;
    Activity activity;
    FragmentCurrentLocationBinding binding;
    double latitude;
    public Location location;
    LocationManager locationManager;
    double longitude;
    private GoogleMap mMap;

    public CurrentLocationFragment() {
    }

    public CurrentLocationFragment(Activity activity) {
        this.activity = activity;
    }

    @Override 
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentCurrentLocationBinding inflate = FragmentCurrentLocationBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        return inflate.getRoot();
    }

    @Override 
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.locationManager = (LocationManager) this.activity.getSystemService("location");
        this.binding.mapView.onCreate(bundle);
        this.binding.mapView.getMapAsync(this);
        this.binding.mapView.onResume();
    }

    @Override 
    public void onLocationChanged(Location location) {
        setMapData(location);
    }

    @Override 
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this.activity, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this.activity, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            this.mMap.setMyLocationEnabled(false);
            getLocation();
        }
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this.activity, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this.activity, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
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
                    this.locationManager.requestLocationUpdates("gps", 10L, 0.0f, this);
                    LocationManager locationManager2 = this.locationManager;
                    if (locationManager2 != null) {
                        Location lastKnownLocation2 = locationManager2.getLastKnownLocation("gps");
                        this.location = lastKnownLocation2;
                        setMapData(lastKnownLocation2);
                    }
                }
            } catch (Exception unused2) {
            }
        }
    }

    public void setMapData(Location location) {
        if (location != null) {
            try {
                this.latitude = location.getLatitude();
                this.longitude = location.getLongitude();
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                this.mMap.clear();
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Current Position");
                markerOptions.icon(Common.BitmapFromVector(this.activity, R.drawable.map_location));
                this.mMap.addMarker(markerOptions);
                this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));
            } catch (Exception unused) {
            }
        }
    }
}
