package chin.pswm.gps.photo.location.map.languegess;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;


public class SharedHelper {
    static Context context;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPref;
    private static final String PREF_NAME = "MyPreferences";
    private static volatile SharedHelper instance;

    public static synchronized SharedHelper getInstance() {
        SharedHelper myConstants;
        synchronized (SharedHelper.class) {
            if (instance == null) {
                instance = new SharedHelper(context);
            }
            myConstants = instance;
        }
        return myConstants;
    }

    public SharedHelper(Context context) {
        this.context = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, 0);
        this.mPref = sharedPreferences;
        this.mEditor = sharedPreferences.edit();
    }

    public boolean isOnline(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= 29) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (networkCapabilities != null && (networkCapabilities.hasTransport(0) || networkCapabilities.hasTransport(1) || networkCapabilities.hasTransport(3))) {
                    return true;
                }
            } else {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                    Log.i("update_statut", "Network is available : true");
                    return true;
                }
            }
        }
        Log.i("update_status", "Network is available : FALSE ");
        return false;
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getString(key, defaultValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, defaultValue);
    }

    public static void putInt(Context context, String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(key, defaultValue);
    }
}