package chin.pswm.gps.photo.location.map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.browser.customtabs.CustomTabsIntent;

import com.bumptech.glide.Glide;

import chin.pswm.gps.photo.location.map_debug.R;

public class AllKeyHub {
    static Boolean AD_ACTIVE = false;

    public static int clickcount = 0;

    //OLD NAME --->  FirebaseInit

    /**
     * TODO  for Full setup ads
     **/
    public static void showIntroAddData(Context context, onCrashDataClose dataClose) {
        dataClose.onDataClose();
    }


    public static void initSocketConnection(Activity activity, boolean isreportshow, boolean isshownative) {
//        Toast.makeText(activity, "ALL", Toast.LENGTH_SHORT).show();

//        if (bigNative != null) {
//
//        }
        if (AD_ACTIVE) {
            BIGNATIVE(activity, activity.findViewById(R.id.Ad_Native));
            SMallNATIVE(activity, activity.findViewById(R.id.Ad_Banner));
            FULLSCREEN(activity);
        }


    }

    //OLD NAME --->  SHowAllFirebase_N

    /**
     * TODO only for Native ads
     **/

    public static void showDynamicNativeData(Activity activity, FrameLayout bigNative, FrameLayout smallNative, boolean isshownative) {
        if (AD_ACTIVE) {

            if (bigNative != null) {
                BIGNATIVE(activity,bigNative);
            }

            if (smallNative != null) {
                SMallNATIVE(activity,smallNative);
            }

        }

    }

    //  OLD NAME ---> Showfirebasedata

    /**
     * TODO  only for interstitial  ads
     **/
    public static void getConnectionData(Context context) {
        if (AD_ACTIVE) {
            FULLSCREEN(context);
        }
    }

    // TODO  for  back ads and  intent click ads

    /**
      showUserInterDataBack(this, new onCrashDataClose() {

     * @Override public void onDataClose() {
     * //to action put here
     * }
     * });
     **/
    public interface onCrashDataClose {
        void onDataClose();
    }

    public static void showUserInterDataBack(Context context, onCrashDataClose interstitialAdsListener1) {
        interstitialAdsListener1.onDataClose();
        if (AD_ACTIVE) {
            FULLSCREEN(context);
        }
    }

    public static void getConnectionData_CLICK(Context context) {
        if (clickcount == 5) {
            clickcount = 0;
            if (AD_ACTIVE) {
                FULLSCREEN(context);
            }
        } else {
            clickcount++;
        }


    }

    public static void BIGNATIVE(final Context context, ViewGroup viewGroup) {
        if (viewGroup == null)
            return;
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.big_test, (ViewGroup) null);
        ImageView imageView = (ImageView) viewGroup2.findViewById(R.id.icon);
        TextView textView = (TextView) viewGroup2.findViewById(R.id.title);
        TextView textView3 = (TextView) viewGroup2.findViewById(R.id.description);
        Glide.with(context).load(R.mipmap.ic_launcher).into(imageView);
        Glide.with(context).load(R.mipmap.ic_launcher).into((ImageView) viewGroup2.findViewById(R.id.ImgAppBanner));
        textView.setText(" BIG NATIVE ADS DEMO");
        textView3.setText("Lorem Ipsum is simply dummy text...");
        viewGroup.removeAllViews();
        viewGroup.addView(viewGroup2);
    }


    public static void SMallNATIVE(final Context context, ViewGroup viewGroup) {
        if (viewGroup == null)
            return;
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.small_test, (ViewGroup) null);
        TextView textView = (TextView) viewGroup2.findViewById(R.id.install);
        TextView textView2 = (TextView) viewGroup2.findViewById(R.id.headline);
        TextView textView3 = (TextView) viewGroup2.findViewById(R.id.body);
        Glide.with(context).load(R.mipmap.ic_launcher).into((ImageView) viewGroup2.findViewById(R.id.imageView_icon));
        textView2.setText("SMALL NATIVE ADS DEMO");
        textView3.setText("Lorem Ipsum is simply dummy text...");
        viewGroup.removeAllViews();
        viewGroup.addView(viewGroup2);
    }

    public static void FULLSCREEN(final Context context) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent build = builder.build();
        build.intent.setPackage("com.android.chrome");
        build.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        build.launchUrl(context, Uri.parse("https://www.google.co.in/"));
    }

}
