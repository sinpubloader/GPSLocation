package chin.pswm.gps.photo.location.map;

import static timber.log.Timber.DebugTree;

import android.app.Activity;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.multidex.MultiDexApplication;

import com.facebook.FacebookSdk;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;

import chin.pswm.gps.photo.location.map.ads.AdsManager;
import chin.pswm.gps.photo.location.map.ads.adjust.AdjustManager;
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs;
import chin.pswm.gps.photo.location.map.ads.remoteconfig.RemoteConfigManager;
import chin.pswm.gps.photo.location.map.notification.NotificationManager;
import chin.pswm.gps.photo.location.map.ads.ext.Tracking;
import timber.log.Timber;


public class MyApplication extends MultiDexApplication {
    public static Activity currentActivity = null;
    public static MyApplication instance = null;
    private static Intent intent = null;
    private static MediaProjectionManager mMediaProjectionManager = null;
    public static boolean needToShow = false;
    private static int result;
    private static FirebaseAnalytics mFirebaseAnalytics;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // init some for ads
        Timber.Forest.plant(new DebugTree());
        AdjustManager adjustManager = new AdjustManager();
        Prefs prefs = new Prefs(this);
        AdsManager adsManager = new AdsManager(this, prefs);
        NotificationManager noti = new NotificationManager(this);
        FirebaseApp.initializeApp(this);
        RemoteConfigManager remoteConfigManager = new RemoteConfigManager(this, prefs);
        remoteConfigManager.fetchRemoteConfig();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        initFcm();
//        AudienceNetworkInitializeHelper.initialize(this);
        FacebookSdk.setApplicationId("1839024150025521");
        FacebookSdk.setClientToken("7e4fe4f80a01570be8f95bcd5da6fa26");
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onMoveToForeground() {
        Log.d("TAG", "onMoveToForeground: ON_START ");
        Activity activity = currentActivity;
//        if ((activity instanceof SplashActivity) || (activity instanceof PrivacyPolicyActivity) || needToShow) {
//            return;
//        }
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

    public static void sendEvent(String screenName, String eventName) {
        Bundle bundle = new Bundle();
        bundle.putString("screenName", screenName);
        bundle.putString("eventName", eventName);
        if (mFirebaseAnalytics == null)
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(MyApplication.instance);
        mFirebaseAnalytics.setDefaultEventParameters(bundle);
    }

    private void initFcm() {
        FirebaseMessaging.getInstance()
                .getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Timber.tag("FirebaseFCM").e(task.getException(), "Fetching FCM token failed");
                        return;
                    }
                    String token = task.getResult();
                    Timber.tag("FirebaseFCM").d("FCM token: %s", token);
                });
    }
}
