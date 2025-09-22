package chin.pswm.gps.photo.location.map.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map.model.LocationDataModel;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class Common {
    public static String addressTemplate;
    public static String cityTemplate;
    public static String dateTemplate;
    public static String disTemplate;
    public static String latTemplate;
    public static String lonTemplate;
    public static Bitmap mapTemplate;
    public static String tempTemplate;
    public static String timeTemplate;
    public static LocationDataModel locationDataModel = new LocationDataModel();
    public static Bitmap mapBitmap = null;
    public static Bitmap pictureBitmap = null;

    public static ArrayList<Integer> getFontList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(Integer.valueOf((int) R.font.aileron));
        arrayList.add(Integer.valueOf((int) R.font.aleo_regular));
        arrayList.add(Integer.valueOf((int) R.font.alexbrush_regular));
        arrayList.add(Integer.valueOf((int) R.font.amatic_sc_regular));
        arrayList.add(Integer.valueOf((int) R.font.avenir_heavy));
        arrayList.add(Integer.valueOf((int) R.font.avenir_ltstd_black));
        arrayList.add(Integer.valueOf((int) R.font.bebas));
        arrayList.add(Integer.valueOf((int) R.font.blackout_2_am));
        arrayList.add(Integer.valueOf((int) R.font.blackout_midnight));
        arrayList.add(Integer.valueOf((int) R.font.caviar_dreams));
        arrayList.add(Integer.valueOf((int) R.font.danielbd));
        arrayList.add(Integer.valueOf((int) R.font.lobster_13));
        arrayList.add(Integer.valueOf((int) R.font.permanent_marker));
        arrayList.add(Integer.valueOf((int) R.font.pusab));
        arrayList.add(Integer.valueOf((int) R.font.robote_medium));
        arrayList.add(Integer.valueOf((int) R.font.roboto_condensed_regular));
        arrayList.add(Integer.valueOf((int) R.font.roboto_regular));
        arrayList.add(Integer.valueOf((int) R.font.roboto_thin));
        arrayList.add(Integer.valueOf((int) R.font.seasrn));
        return arrayList;
    }

    public static ArrayList<Integer> getLayoutList() {
        int selectTemplate = SpManager.getSelectTemplate();
        ArrayList<Integer> arrayList = new ArrayList<>();
        switch (selectTemplate) {
            case 0:
                arrayList.add(Integer.valueOf((int) R.layout.details_layout_1));
                break;
            case 1:
                arrayList.add(Integer.valueOf((int) R.layout.details_layout_2));
                break;
            case 2:
                arrayList.add(Integer.valueOf((int) R.layout.details_layout_3));
                break;
            case 3:
                arrayList.add(Integer.valueOf((int) R.layout.details_layout_4));
                break;
            case 4:
                arrayList.add(Integer.valueOf((int) R.layout.details_layout_5));
                break;
            case 5:
                arrayList.add(Integer.valueOf((int) R.layout.details_layout_6));
                break;
            case 6:
                arrayList.add(Integer.valueOf((int) R.layout.details_layout_7));
                break;
            case 7:
                arrayList.add(Integer.valueOf((int) R.layout.details_layout_8));
                break;
        }
        return arrayList;
    }

    public static BitmapDescriptor BitmapFromVector(Context context, int i) {
        Drawable drawable = ContextCompat.getDrawable(context, i);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        drawable.draw(new Canvas(createBitmap));
        return BitmapDescriptorFactory.fromBitmap(createBitmap);
    }

    public static String timeConversion(long j) {
        return String.format("%02d:%02d:%02d", Long.valueOf(TimeUnit.MILLISECONDS.toHours(j) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(j))), Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(j) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(j))), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(j))));
    }

    public static String formatNumber(double d) {
        return new DecimalFormat("##.####", new DecimalFormatSymbols(new Locale("en"))).format(d);
    }
}
