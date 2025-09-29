package chin.pswm.gps.photo.location.map;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.LogLevel;
import com.onesignal.OneSignal;

import chin.pswm.gps.photo.location.map.activity.PrivacyPolicyActivity;
import chin.pswm.gps.photo.location.map.activity.SplashActivity;
import chin.pswm.gps.photo.location.map_debug.BuildConfig;
import chin.pswm.gps.photo.location.map.ads.adjust.AdjustManager;
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs;
import chin.pswm.gps.photo.location.map.notification.ClickNotification;
import chin.pswm.gps.photo.location.map.notification.NotificationView;


public class MyApplication extends Application {
    public static Activity currentActivity = null;
    public static MyApplication instance = null;
    private static Intent intent = null;
    private static MediaProjectionManager mMediaProjectionManager = null;
    public static boolean needToShow = false;
    private static int result;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.setNotificationWillShowInForegroundHandler(new NotificationView(this));
        OneSignal.setNotificationOpenedHandler(new ClickNotification(this));
        OneSignal.initWithContext(this);
        OneSignal.setAppId("cd0d2840-e92c-4ba7-8dd5-c19efa8d37a9");

        // init some for ads
        AdjustManager adjustManager = new AdjustManager();
        Prefs prefs = new Prefs(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onMoveToForeground() {
        Log.d("TAG", "onMoveToForeground: ON_START ");
        Activity activity = currentActivity;
        if ((activity instanceof SplashActivity) || (activity instanceof PrivacyPolicyActivity) || needToShow) {
            return;
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onMoveToBackground() {
        Log.d("TAG", "onMoveToForeground: ON_PAUSE ");
    }

//    @Override
//    public void onActivityStarted(Activity activity) {
//        Log.d("TAG", "onMoveToForeground: onActivityStarted ");
//
//        currentActivity = activity;
//    }


    public static int getResult() {
        return result;
    }

    public static Intent getIntent() {
        return intent;
    }

    public static MediaProjectionManager getMediaProjectionManager() {
        return mMediaProjectionManager;
    }

    public static void setResult(int i) {
        result = i;
    }

    public static void setIntent(Intent intent2) {
        intent = intent2;
    }

    public static void setMediaProjectionManager(MediaProjectionManager mediaProjectionManager) {
        mMediaProjectionManager = mediaProjectionManager;
    }


}
