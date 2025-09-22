package chin.pswm.gps.photo.location.map.model;

import android.graphics.Bitmap;


public class LocationDataModel {
    public String address;
    public Bitmap cameraImage;
    public String date;
    public String lat1;
    public String lat2;
    public double latitude;
    public String lng1;
    public String lng2;
    public double longitude;
    public Bitmap mapImage;
    public String temp;

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

    public Bitmap getCameraImage() {
        return this.cameraImage;
    }

    public void setCameraImage(Bitmap bitmap) {
        this.cameraImage = bitmap;
    }

    public Bitmap getMapImage() {
        return this.mapImage;
    }

    public void setMapImage(Bitmap bitmap) {
        this.mapImage = bitmap;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getTemp() {
        return this.temp;
    }

    public void setTemp(String str) {
        this.temp = str;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getLat1() {
        return this.lat1;
    }

    public void setLat1(String str) {
        this.lat1 = str;
    }

    public String getLat2() {
        return this.lat2;
    }

    public void setLat2(String str) {
        this.lat2 = str;
    }

    public String getLng1() {
        return this.lng1;
    }

    public void setLng1(String str) {
        this.lng1 = str;
    }

    public String getLng2() {
        return this.lng2;
    }

    public void setLng2(String str) {
        this.lng2 = str;
    }
}
