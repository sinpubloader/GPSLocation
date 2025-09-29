package chin.pswm.gps.photo.location.map.utils;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.util.Log;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Objects;


public class GPSUtils {
    public static boolean isGPSEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        if (locationManager != null) {
            return locationManager.isProviderEnabled("gps");
        }
        return false;
    }

    public static void showGPSDialog(final Activity activity) {
        LocationSettingsRequest.Builder addLocationRequest = new LocationSettingsRequest.Builder().addLocationRequest(new LocationRequest.Builder(100, 100L).setWaitForAccurateLocation(false).setMinUpdateIntervalMillis(2000L).setMaxUpdateDelayMillis(100L).build());
        addLocationRequest.setAlwaysShow(true);
        LocationServices.getSettingsClient(activity).checkLocationSettings(addLocationRequest.build()).addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(Task<LocationSettingsResponse> task) {
                try {
                    task.getResult(ApiException.class);
                } catch (ApiException e) {
                    if (e.getStatusCode() == 6) {
                        try {
                            ((ResolvableApiException) e).startResolutionForResult(activity, 100);
                        } catch (Exception e2) {
                            Log.d("TAG", (String) Objects.requireNonNull(e2.getMessage()));
                        }
                    }
                }
            }
        });
    }
}
