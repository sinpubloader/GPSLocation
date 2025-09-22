package chin.pswm.gps.photo.location.map.model;

import android.location.Location;

import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;


public class PlaceData implements ClusterItem {
    public String city;
    public String country;
    public String date;
    public LatLng latLng;
    public double latitude;
    public Location location;
    public double longitude;
    public String path;
    public String state;

    public PlaceData(String str, LatLng latLng2, String str2, String str3, String str4, String str5) {
        this.path = str;
        this.latLng = latLng2;
        this.date = str2;
        this.country = str3;
        this.state = str4;
        this.city = str5;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location2) {
        this.location = location2;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public LatLng getPosition() {
        return this.latLng;
    }

    @Nullable
    @Override
    public String getTitle() {
        return "";
    }


    @Nullable
    @Override
    public String getSnippet() {
        return "";
    }

    @Nullable
    @Override
    public Float getZIndex() {
        return 0f;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }
}