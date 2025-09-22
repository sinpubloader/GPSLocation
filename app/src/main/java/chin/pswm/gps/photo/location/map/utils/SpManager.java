package chin.pswm.gps.photo.location.map.utils;

import android.content.Context;
import android.content.SharedPreferences;
@SuppressWarnings("all")

public class SpManager {
    public static String CAMERA_ACTIVITY_MAP_TYPE = "cameraActivityMapType";
    public static String CONNECT_TYPE = "connect_type";
    public static String GALLERY_ACTIVITY_MAP_TYPE = "galleryActivityMapType";
    public static String IS_FIRST_TIME = "is_first_time";
    public static String IS_INDIAN = "is_indian";
    public static String LANGUAGE_CODE = "language_code";
    public static String LANGUAGE_CODE_SNIP = "language_code_snip";
    public static String LANGUAGE_SELECTED = "language_selected";
    public static String LATITUDE = "selectLatitude";
    public static String LONGITUDE = "selectLongitude";
    public static String Rate_Which = "Rate_Which";
    public static String SELECT_LOCATION_TYPE = "selectLocationType";
    public static String SELECT_TEMPLATE = "selectTemplate";
    public static String isSRatePopupMC = "isSRatePopupMC";
    static SharedPreferences.Editor myEdit;
    static SharedPreferences sharedPreferences;

    public static void initializingSharedPreference(Context context) {
        SharedPreferences sharedPreferences2 = context.getSharedPreferences("MySharedPref123", 0);
        sharedPreferences = sharedPreferences2;
        myEdit = sharedPreferences2.edit();
    }

    public static boolean isIndian() {
        return sharedPreferences.getBoolean(IS_INDIAN, true);
    }

    public static void setIndian(boolean z) {
        sharedPreferences.edit().putBoolean(IS_INDIAN, z).apply();
    }

    public static boolean getLanguageSelected() {
        return sharedPreferences.getBoolean(LANGUAGE_SELECTED, false);
    }

    public static void setLanguageSelected(boolean z) {
        sharedPreferences.edit().putBoolean(LANGUAGE_SELECTED, z).apply();
    }

    public static String getLanguageCode() {
        return sharedPreferences.getString(LANGUAGE_CODE, "en");
    }

    public static void setLanguageCode(String str) {
        sharedPreferences.edit().putString(LANGUAGE_CODE, str).apply();
    }

    public static String getLanguageCodeSnip() {
        return sharedPreferences.getString(LANGUAGE_CODE_SNIP, "en");
    }

    public static void setLanguageCodeSnip(String str) {
        sharedPreferences.edit().putString(LANGUAGE_CODE_SNIP, str).apply();
    }

    public static boolean getIsFirstTime() {
        return sharedPreferences.getBoolean(IS_FIRST_TIME, true);
    }

    public static void setIsFirstTime(boolean z) {
        sharedPreferences.edit().putBoolean(IS_FIRST_TIME, z).apply();
    }

    public static String getConnectType() {
        return sharedPreferences.getString(CONNECT_TYPE, "A2DP");
    }

    public static void setConnectType(String str) {
        sharedPreferences.edit().putString(CONNECT_TYPE, str).apply();
    }

    public static void setIsRatePopupMC(int i) {
        sharedPreferences.edit().putInt(isSRatePopupMC, i).apply();
    }

    public static int getIsRatePopupMC() {
        return sharedPreferences.getInt(isSRatePopupMC, 0);
    }

    public static void setRate_Which(int i) {
        sharedPreferences.edit().putInt(Rate_Which, i).apply();
    }

    public static int getRate_Which() {
        return sharedPreferences.getInt(Rate_Which, 0);
    }

    public static void setCameraActivityMapType(int i) {
        sharedPreferences.edit().putInt(CAMERA_ACTIVITY_MAP_TYPE, i).apply();
    }

    public static int getCameraActivityMapType() {
        return sharedPreferences.getInt(CAMERA_ACTIVITY_MAP_TYPE, 1);
    }

    public static void setGalleryActivityMapType(int i) {
        sharedPreferences.edit().putInt(GALLERY_ACTIVITY_MAP_TYPE, i).apply();
    }

    public static int getGalleryActivityMapType() {
        return sharedPreferences.getInt(GALLERY_ACTIVITY_MAP_TYPE, 1);
    }

    public static void setSelectTemplate(int i) {
        sharedPreferences.edit().putInt(SELECT_TEMPLATE, i).apply();
    }

    public static int getSelectTemplate() {
        return sharedPreferences.getInt(SELECT_TEMPLATE, 0);
    }

    public static void setSelectLocationType(int i) {
        sharedPreferences.edit().putInt(SELECT_LOCATION_TYPE, i).apply();
    }

    public static int getSelectLocationType() {
        return sharedPreferences.getInt(SELECT_LOCATION_TYPE, 0);
    }

    public static void setLATITUDE(String str) {
        sharedPreferences.edit().putString(LATITUDE, str).apply();
    }

    public static String getLATITUDE() {
        return sharedPreferences.getString(LATITUDE, "22.6708");
    }

    public static void setLONGITUDE(String str) {
        sharedPreferences.edit().putString(LONGITUDE, str).apply();
    }

    public static String getLONGITUDE() {
        return sharedPreferences.getString(LONGITUDE, "71.5724");
    }
}
