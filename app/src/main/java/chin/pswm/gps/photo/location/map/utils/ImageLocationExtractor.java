package chin.pswm.gps.photo.location.map.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import chin.pswm.gps.photo.location.map.activity.StartActivity;
import chin.pswm.gps.photo.location.map.model.PlaceData;
import com.google.android.gms.maps.model.LatLng;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;


public class ImageLocationExtractor {
    private static final int MESSAGE_EXTRACT_LOCATION = 1;
    private static final String TAG = "ImageLocationExtractor";
    public static boolean isPlaceInProgress = false;
    Context context;
    private final Handler handler;
    private final HandlerThread handlerThread;
    private final List<Uri> imageUris;
    private final List<LocationListener> locationListeners;
    ProcessComplete processComplete;

    
    public interface LocationListener {
        void onLocationExtracted(Uri uri, float f, float f2);
    }

    
    public interface ProcessComplete {
        void locationListGet();
    }

    public ImageLocationExtractor(Context context, List<Uri> list, List<LocationListener> list2, ProcessComplete processComplete) {
        this.context = context;
        this.imageUris = list;
        this.locationListeners = list2;
        this.processComplete = processComplete;
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.handlerThread = handlerThread;
        handlerThread.start();
        this.handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    return;
                }
                ImageLocationExtractor.this.extractLocation((Uri) message.obj);
            }
        };
    }

    public void start() {
        StartActivity.placesArrayList.clear();
        isPlaceInProgress = true;
        for (int i = 0; i < this.imageUris.size(); i++) {
            Handler handler = this.handler;
            handler.sendMessage(handler.obtainMessage(1, this.imageUris.get(i)));
        }
    }

    public void stop() {
        Collections.sort(StartActivity.placesArrayList, new Comparator<PlaceData>() {
            DateFormat f = new SimpleDateFormat("dd MMMM yyyy h:mm a");

            @Override
            public int compare(PlaceData placeData, PlaceData placeData2) {
                try {
                    return this.f.parse(placeData.getDate()).compareTo(this.f.parse(placeData2.getDate()));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
        isPlaceInProgress = false;
        this.handlerThread.quit();
        this.processComplete.locationListGet();
    }
    public void extractLocation(Uri uri) {
        try {
            float[] fArr = new float[2];
            boolean hasLocation = new ExifInterface(uri.getPath()).getLatLong(fArr);
            ImageLocationExtractor imageLocationExtractor2 = this;

            if (hasLocation) {
                LatLng latLng = new LatLng(fArr[0], fArr[1]);
                List<Address> fromLocation = new Geocoder(this.context, Locale.getDefault())
                        .getFromLocation(latLng.latitude, latLng.longitude, 1);

                if (!fromLocation.isEmpty()) {
                    String locality = fromLocation.get(0).getLocality();
                    String adminArea = fromLocation.get(0).getAdminArea();
                    String countryName = fromLocation.get(0).getCountryName();

                    StartActivity.placesArrayList.add(new PlaceData(
                            uri.getPath(), latLng,
                            StorageUtils.fileDate(new File(uri.getPath()).lastModified(), "dd MMMM yyyy h:mm a"),
                            countryName, adminArea, locality
                    ));
                }
                imageLocationExtractor2.notifyLocationListeners(uri, fArr[0], fArr[1]);
            } else {
                imageLocationExtractor2.notifyLocationListeners(uri, 0.0f, 0.0f);
            }
        } catch (Exception e) {
            Log.d(TAG, "extractLocation: " + e);
        }


        if (imageUris.get(imageUris.size() - 1).getPath().equals(uri.getPath())) {
            stop();
        }
    }

    private void notifyLocationListeners(Uri uri, float f, float f2) {
        for (LocationListener locationListener : this.locationListeners) {
            locationListener.onLocationExtracted(uri, f, f2);
        }
    }
}