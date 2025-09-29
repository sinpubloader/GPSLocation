package chin.pswm.gps.photo.location.map.ads;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.util.Log;

import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.FormError;
import com.google.android.ump.UserMessagingPlatform;

import java.io.IOException;

import chin.pswm.gps.photo.location.map.MyApplication;
import chin.pswm.gps.photo.location.map_debug.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class AdsSplashUtils {
    Activity activity;
    private ConsentInformation consentInformation;
    SharedPreferences.Editor myEdit;
    private long secondsRemaining;
    String serverLink;
    SharedPreferences sharedPreferences;
    SplashInterface splashInterface;

    
    public interface SplashInterface {
        void callNativePreload();

        void nextActivity();
    }

    public AdsSplashUtils(Activity activity, String str, SplashInterface splashInterface) {
        this.activity = activity;
        this.serverLink = str;
        this.splashInterface = splashInterface;
        getOnlineIds();
    }

    public void getOnlineIds() {
        Activity activity = this.activity;
        SharedPreferences sharedPreferences = activity.getSharedPreferences(activity.getString(R.string.app_name), 0);
        this.sharedPreferences = sharedPreferences;
        this.myEdit = sharedPreferences.edit();
        AdsVariable.ads_loading_flg = this.sharedPreferences.getString("ads_loading_flg", "11");
        AdsVariable.ads_loading_flg_all = this.sharedPreferences.getString("ads_loading_flg_all", "11");
        AdsVariable.appOpen = this.sharedPreferences.getString("appOpen", "11");
        AdsVariable.appOpen_on_splash = this.sharedPreferences.getString("appOpen_on_splash", "11");
        AdsVariable.pubId = this.sharedPreferences.getString("pubId", "11");
        AdsVariable.appOpenSplashTime = this.sharedPreferences.getLong("appOpenSplashTime", 30L);
        AdsVariable.native_bg_color = this.sharedPreferences.getString("native_bg_color", "#e6fefb");
        AdsVariable.native_text_color_title = this.sharedPreferences.getString("native_text_color_title", "#168DFB");
        AdsVariable.native_text_color_body = this.sharedPreferences.getString("native_text_color_body", "#787676");
        AdsVariable.native_text_color_app_name = this.sharedPreferences.getString("native_text_color_app_name", "#ACACAC");
        AdsVariable.native_button_color = this.sharedPreferences.getString("native_button_color", "#1199E5");
        AdsVariable.native_button_text_color = this.sharedPreferences.getString("native_button_text_color", "#ffffff");
        AdsVariable.native_ad_tag_bg = this.sharedPreferences.getString("native_ad_tag_bg", "#7E88FB");
        AdsVariable.native_ad_tag_text = this.sharedPreferences.getString("native_ad_tag_text", "#E6FEFB");
        AdsVariable.full_SplashScreen = this.sharedPreferences.getString("full_SplashScreen", "11");
        AdsVariable.full_PreloadScreen = this.sharedPreferences.getString("full_PreloadScreen", "11");
        AdsVariable.full_StartScreen = this.sharedPreferences.getString("full_StartScreen", "11");
        AdsVariable.full_GPSCameraScreen_Save = this.sharedPreferences.getString("full_GPSCameraScreen_Save", "11");
        AdsVariable.full_PhotoGridScreen_Save = this.sharedPreferences.getString("full_PhotoGridScreen_Save", "11");
        AdsVariable.full_GalleryScreen_Save = this.sharedPreferences.getString("full_GalleryScreen_Save", "11");
        AdsVariable.native_LanguageScreen = this.sharedPreferences.getString("native_LanguageScreen", "11");
        AdsVariable.native_StartScreen = this.sharedPreferences.getString("native_StartScreen", "11");
        AdsVariable.native_TemplateScreen = this.sharedPreferences.getString("native_TemplateScreen", "11");
        AdsVariable.banner_GalleryCameraPreview_Screen = this.sharedPreferences.getString("banner_GalleryCameraPreview_Screen", "11");
        AdsVariable.banner_ImagePreview_Screen = this.sharedPreferences.getString("banner_ImagePreview_Screen", "11");
        AdsVariable.banner_PhotoGrid_Screen = this.sharedPreferences.getString("banner_PhotoGrid_Screen", "11");
        AdsVariable.banner_VideoPreview_Screen = this.sharedPreferences.getString("banner_VideoPreview_Screen", "11");
        if (isNetworkAvailable()) {
            try {
                new OkHttpClient().newCall(new Request.Builder().url(this.serverLink).build()).enqueue(new Callback() { 

                    @Override 
                    public void onFailure(Call call, IOException iOException) {
                        AdsSplashUtils.this.activity.runOnUiThread(new Runnable() { 


                            @Override 
                            public void run() {
                                Log.d("TAG", "run: 123 onFailure");
                                AdsSplashUtils.this.next();
                            }
                        });
                    }

                    @Override 
                    public void onResponse(Call call, Response response) throws IOException {
                        String str;
                        if (!response.isSuccessful()) {
                            Log.d("TAG", "run: 123 !response.isSuccessful()");
                            AdsSplashUtils.this.splashInterface.nextActivity();
                        }
                        Headers headers = response.headers();
                        for (int i = 0; i < headers.size(); i++) {
                            System.out.println(headers.name(i) + ": " + headers.value(i));
                        }
                        String str2 = "#";
                        String[] split = response.body().string().trim().trim().split("#");
                        int length = split.length;
                        int i2 = 0;
                        while (true) {
                            String str3 = str2;
                            if (i2 < length) {
                                int i3 = length;
                                String str4 = split[i2];
                                String[] strArr = split;
                                int i4 = i2;
                                String trim = str4.split("\\$")[0].trim();
                                trim.hashCode();
                                char c = 65535;
                                switch (trim.hashCode()) {
                                    case -2056687903:
                                        if (trim.equals("startFlagOnline")) {
                                            c = 0;
                                            break;
                                        }
                                        break;
                                    case -1968920189:
                                        if (trim.equals("full_SplashScreen")) {
                                            c = 1;
                                            break;
                                        }
                                        break;
                                    case -1610328685:
                                        if (trim.equals("appOpen_on_splash")) {
                                            c = 2;
                                            break;
                                        }
                                        break;
                                    case -1598486126:
                                        if (trim.equals("native_text_color_title")) {
                                            c = 3;
                                            break;
                                        }
                                        break;
                                    case -1559767257:
                                        if (trim.equals("banner_GalleryCameraPreview_Screen")) {
                                            c = 4;
                                            break;
                                        }
                                        break;
                                    case -1469519458:
                                        if (trim.equals("full_StartScreen")) {
                                            c = 5;
                                            break;
                                        }
                                        break;
                                    case -1299021016:
                                        if (trim.equals("native_text_color_body")) {
                                            c = 6;
                                            break;
                                        }
                                        break;
                                    case -1180258811:
                                        if (trim.equals("full_PreloadScreen")) {
                                            c = 7;
                                            break;
                                        }
                                        break;
                                    case -824393304:
                                        if (trim.equals("full_PhotoGridScreen_Save")) {
                                            c = '\b';
                                            break;
                                        }
                                        break;
                                    case -794092533:
                                        if (trim.equals("appOpen")) {
                                            c = '\t';
                                            break;
                                        }
                                        break;
                                    case -667791578:
                                        if (trim.equals("native_ad_tag_text")) {
                                            c = '\n';
                                            break;
                                        }
                                        break;
                                    case -479151442:
                                        if (trim.equals("full_GalleryScreen_Save")) {
                                            c = 11;
                                            break;
                                        }
                                        break;
                                    case -464871722:
                                        if (trim.equals("native_button_text_color")) {
                                            c = '\f';
                                            break;
                                        }
                                        break;
                                    case -426340658:
                                        if (trim.equals("native_TemplateScreen")) {
                                            c = '\r';
                                            break;
                                        }
                                        break;
                                    case -267118799:
                                        if (trim.equals("ads_loading_flg_all")) {
                                            c = 14;
                                            break;
                                        }
                                        break;
                                    case -246358298:
                                        if (trim.equals("banner_PhotoGrid_Screen")) {
                                            c = 15;
                                            break;
                                        }
                                        break;
                                    case 107016440:
                                        if (trim.equals("pubId")) {
                                            c = 16;
                                            break;
                                        }
                                        break;
                                    case 473631526:
                                        if (trim.equals("native_StartScreen")) {
                                            c = 17;
                                            break;
                                        }
                                        break;
                                    case 686727697:
                                        if (trim.equals("native_bg_color")) {
                                            c = 18;
                                            break;
                                        }
                                        break;
                                    case 839973999:
                                        if (trim.equals("native_text_color_app_name")) {
                                            c = 19;
                                            break;
                                        }
                                        break;
                                    case 952167887:
                                        if (trim.equals("ads_loading_flg")) {
                                            c = 20;
                                            break;
                                        }
                                        break;
                                    case 963196716:
                                        if (trim.equals("native_LanguageScreen")) {
                                            c = 21;
                                            break;
                                        }
                                        break;
                                    case 1118847806:
                                        if (trim.equals("native_button_color")) {
                                            c = 22;
                                            break;
                                        }
                                        break;
                                    case 1161314430:
                                        if (trim.equals("native_ad_tag_bg")) {
                                            c = 23;
                                            break;
                                        }
                                        break;
                                    case 1167426929:
                                        if (trim.equals("full_GPSCameraScreen_Save")) {
                                            c = 24;
                                            break;
                                        }
                                        break;
                                    case 1516637247:
                                        if (trim.equals("appOpenSplashTime")) {
                                            c = 25;
                                            break;
                                        }
                                        break;
                                    case 1550349675:
                                        if (trim.equals("banner_ImagePreview_Screen")) {
                                            c = 26;
                                            break;
                                        }
                                        break;
                                    case 1832118411:
                                        if (trim.equals("banner_VideoPreview_Screen")) {
                                            c = 27;
                                            break;
                                        }
                                        break;
                                }
                                switch (c) {
                                    case 0:
                                        str = str3;
                                        AdsVariable.startFlagOnline = str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case 1:
                                        str = str3;
                                        AdsVariable.full_SplashScreen = str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case 2:
                                        str = str3;
                                        AdsVariable.appOpen_on_splash = str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case 3:
                                        str = str3;
                                        AdsVariable.native_text_color_title = str + str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case 4:
                                        str = str3;
                                        AdsVariable.banner_GalleryCameraPreview_Screen = str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case 5:
                                        str = str3;
                                        AdsVariable.full_StartScreen = str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case 6:
                                        str = str3;
                                        AdsVariable.native_text_color_body = str + str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case 7:
                                        str = str3;
                                        AdsVariable.full_PreloadScreen = str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case '\b':
                                        str = str3;
                                        AdsVariable.full_PhotoGridScreen_Save = str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case '\t':
                                        str = str3;
                                        AdsVariable.appOpen = str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case '\n':
                                        str = str3;
                                        AdsVariable.native_ad_tag_text = str + str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case 11:
                                        str = str3;
                                        AdsVariable.full_GalleryScreen_Save = str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case '\f':
                                        str = str3;
                                        AdsVariable.native_button_text_color = str + str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case '\r':
                                        str = str3;
                                        AdsVariable.native_TemplateScreen = str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case 14:
                                        str = str3;
                                        AdsVariable.ads_loading_flg_all = str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case 15:
                                        str = str3;
                                        AdsVariable.banner_PhotoGrid_Screen = str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case 16:
                                        str = str3;
                                        AdsVariable.pubId = str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case 17:
                                        str = str3;
                                        AdsVariable.native_StartScreen = str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case 18:
                                        str = str3;
                                        AdsVariable.native_bg_color = str + str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case 19:
                                        str = str3;
                                        AdsVariable.native_text_color_app_name = str + str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case 20:
                                        str = str3;
                                        AdsVariable.ads_loading_flg = str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case 21:
                                        str = str3;
                                        AdsVariable.native_LanguageScreen = str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case 22:
                                        str = str3;
                                        AdsVariable.native_button_color = str + str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case 23:
                                        str = str3;
                                        AdsVariable.native_ad_tag_bg = str + str4.split("\\$")[1].trim();

                                        str2 = str;
                                        length = i3;
                                        split = strArr;
                                        i2 = i4 + 1;
                                        continue;
                                    case 24:
                                        AdsVariable.full_GPSCameraScreen_Save = str4.split("\\$")[1].trim();
                                        break;
                                    case 25:
                                        AdsVariable.appOpenSplashTime = Long.parseLong(str4.split("\\$")[1].trim());
                                        break;
                                    case 26:
                                        AdsVariable.banner_ImagePreview_Screen = str4.split("\\$")[1].trim();
                                        break;
                                    case 27:
                                        AdsVariable.banner_VideoPreview_Screen = str4.split("\\$")[1].trim();
                                        break;
                                }
                                str = str3;
                                str2 = str;
                                length = i3;
                                split = strArr;
                                i2 = i4 + 1;
                            } else {
                                AdsSplashUtils.this.myEdit.putString("ads_loading_flg", AdsVariable.ads_loading_flg);
                                AdsSplashUtils.this.myEdit.putString("ads_loading_flg_all", AdsVariable.ads_loading_flg_all);
                                AdsSplashUtils.this.myEdit.putString("appOpen", AdsVariable.appOpen);
                                AdsSplashUtils.this.myEdit.putString("appOpen_on_splash", AdsVariable.appOpen_on_splash);
                                AdsSplashUtils.this.myEdit.putLong("appOpenSplashTime", AdsVariable.appOpenSplashTime);
                                AdsSplashUtils.this.myEdit.putString("pubId", AdsVariable.pubId);
                                AdsSplashUtils.this.myEdit.putString("native_bg_color", AdsVariable.native_bg_color);
                                AdsSplashUtils.this.myEdit.putString("native_text_color_title", AdsVariable.native_text_color_title);
                                AdsSplashUtils.this.myEdit.putString("native_text_color_body", AdsVariable.native_text_color_body);
                                AdsSplashUtils.this.myEdit.putString("native_text_color_app_name", AdsVariable.native_text_color_app_name);
                                AdsSplashUtils.this.myEdit.putString("native_button_color", AdsVariable.native_button_color);
                                AdsSplashUtils.this.myEdit.putString("native_button_text_color", AdsVariable.native_button_text_color);
                                AdsSplashUtils.this.myEdit.putString("native_ad_tag_bg", AdsVariable.native_ad_tag_bg);
                                AdsSplashUtils.this.myEdit.putString("native_ad_tag_text", AdsVariable.native_ad_tag_text);
                                AdsSplashUtils.this.myEdit.putString("full_SplashScreen", AdsVariable.full_SplashScreen);
                                AdsSplashUtils.this.myEdit.putString("full_PreloadScreen", AdsVariable.full_PreloadScreen);
                                AdsSplashUtils.this.myEdit.putString("full_StartScreen", AdsVariable.full_StartScreen);
                                AdsSplashUtils.this.myEdit.putString("full_GPSCameraScreen_Save", AdsVariable.full_GPSCameraScreen_Save);
                                AdsSplashUtils.this.myEdit.putString("full_PhotoGridScreen_Save", AdsVariable.full_PhotoGridScreen_Save);
                                AdsSplashUtils.this.myEdit.putString("full_GalleryScreen_Save", AdsVariable.full_GalleryScreen_Save);
                                AdsSplashUtils.this.myEdit.putString("native_LanguageScreen", AdsVariable.native_LanguageScreen);
                                AdsSplashUtils.this.myEdit.putString("native_StartScreen", AdsVariable.native_StartScreen);
                                AdsSplashUtils.this.myEdit.putString("native_TemplateScreen", AdsVariable.native_TemplateScreen);
                                AdsSplashUtils.this.myEdit.putString("banner_GalleryCameraPreview_Screen", AdsVariable.banner_GalleryCameraPreview_Screen);
                                AdsSplashUtils.this.myEdit.putString("banner_ImagePreview_Screen", AdsVariable.banner_ImagePreview_Screen);
                                AdsSplashUtils.this.myEdit.putString("banner_PhotoGrid_Screen", AdsVariable.banner_PhotoGrid_Screen);
                                AdsSplashUtils.this.myEdit.putString("banner_VideoPreview_Screen", AdsVariable.banner_VideoPreview_Screen);
                                AdsSplashUtils.this.myEdit.commit();
                                AdsSplashUtils.this.activity.runOnUiThread(new Runnable() {


                                    @Override 
                                    public void run() {
                                        AdsSplashUtils.this.next();
                                    }
                                });
                                return;
                            }
                        }
                    }
                });
                return;
            } catch (Exception unused) {
                next();
                return;
            }
        }
        next();
    }

    void next() {
        ConsentRequestParameters build = new ConsentRequestParameters.Builder().setTagForUnderAgeOfConsent(false).build();
        ConsentInformation consentInformation = UserMessagingPlatform.getConsentInformation(this.activity);
        this.consentInformation = consentInformation;
        consentInformation.requestConsentInfoUpdate(this.activity, build, new AnonymousClass2(), new ConsentInformation.OnConsentInfoUpdateFailureListener() {


            @Override
            public void onConsentInfoUpdateFailure(FormError formError) {
                AdsSplashUtils.this.initializeMobileAdsSdk();
            }
        });
    }


    
    public class AnonymousClass2 implements ConsentInformation.OnConsentInfoUpdateSuccessListener {
        AnonymousClass2() {
        }

        @Override
        public void onConsentInfoUpdateSuccess() {
            UserMessagingPlatform.loadAndShowConsentFormIfRequired(AdsSplashUtils.this.activity, new ConsentForm.OnConsentFormDismissedListener() {
                @Override
                public final void onConsentFormDismissed(FormError formError) {
                    AdsSplashUtils.AnonymousClass2.this.m159x734cb895(formError);
                }
            });
        }


        public  void m159x734cb895(FormError formError) {
            if (formError == null) {
                if (AdsSplashUtils.this.consentInformation.canRequestAds()) {
                    AdsSplashUtils.this.initializeMobileAdsSdk();
                    return;
                }
                return;
            }
            AdsSplashUtils.this.initializeMobileAdsSdk();
        }
    }

    public void initializeMobileAdsSdk() {

            goToMain();

    }

    public void goToMain() {
        if (isNetworkAvailable()) {
            this.splashInterface.callNativePreload();
            this.secondsRemaining = 0L;
            if (!AdsVariable.appOpen_on_splash.equalsIgnoreCase("11")) {
                createTimer(AdsVariable.appOpenSplashTime);
                return;
            }
            this.activity.runOnUiThread(new Runnable() {


                @Override 
                public void run() {
                    AdsSplashUtils.this.nextactivity();
                }
            });
            return;
        }
        nextactivity();
    }

    private void createTimer(final long j) {
        new CountDownTimer(j * 1000, 1000L) {


            @Override
            public void onTick(long j2) {
                AdsSplashUtils.this.secondsRemaining = (j2 / 1000) + 1;
                if (AdsSplashUtils.this.secondsRemaining >= j || AdsSplashUtils.this.isNetworkAvailable()) {
                    if (AdsSplashUtils.this.secondsRemaining < j) {
                        MyApplication myApplication = (MyApplication) AdsSplashUtils.this.activity.getApplication();
                        AdsSplashUtils.this.nextactivity();
                    }
                    if (AdsSplashUtils.this.secondsRemaining < j) {
                        MyApplication myApplication3 = (MyApplication) AdsSplashUtils.this.activity.getApplication();
                        AdsSplashUtils.this.nextactivity();
                        return;
                    }
                    return;
                }
                MyApplication.needToShow = false;
                AdsSplashUtils.this.nextactivity();
                cancel();
            }

            @Override
            public void onFinish() {
                AdsSplashUtils.this.secondsRemaining = 0L;
                Application application = AdsSplashUtils.this.activity.getApplication();
                if (!(application instanceof MyApplication)) {
                    MyApplication.needToShow = false;
                    AdsSplashUtils.this.nextactivity();
                    return;
                }
                AdsSplashUtils.this.nextactivity();
            }
        }.start();
    }

    public void nextactivity() {
        this.splashInterface.nextActivity();
    }

    public boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.activity.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
