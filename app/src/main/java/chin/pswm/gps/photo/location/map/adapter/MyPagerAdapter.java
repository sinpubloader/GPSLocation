package chin.pswm.gps.photo.location.map.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import chin.pswm.gps.photo.location.map.utils.Common;
import chin.pswm.gps.photo.location.map.utils.SpManager;
import chin.pswm.gps.photo.location.map_debug.R;


public class MyPagerAdapter extends PagerAdapter {
    public ArrayList<Integer> arrayList;
    private Context context;

    @Override 
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public MyPagerAdapter(Context context, ArrayList<Integer> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override 
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(this.context).inflate(this.arrayList.get(i).intValue(), viewGroup, false);
        viewGroup.addView(viewGroup2);
        try {
            ((TextView) viewGroup2.findViewById(R.id.date)).setText(Common.dateTemplate);
        } catch (Exception e) {
            Log.d("TAG", "instantiateItem: 1 " + e.getMessage());
        }
        try {
            ((TextView) viewGroup2.findViewById(R.id.time)).setText(Common.timeTemplate);
        } catch (Exception e2) {
            Log.d("TAG", "instantiateItem: 2 " + e2.getMessage());
        }
        try {
            ((TextView) viewGroup2.findViewById(R.id.address)).setText(Common.addressTemplate);
        } catch (Exception e3) {
            Log.d("TAG", "instantiateItem: 3 " + e3.getMessage());
        }
        try {
            ((TextView) viewGroup2.findViewById(R.id.latitude)).setText(Common.latTemplate.trim());
        } catch (Exception e4) {
            Log.d("TAG", "instantiateItem: 4 " + e4.getMessage());
        }
        try {
            ((TextView) viewGroup2.findViewById(R.id.longitude)).setText(Common.lonTemplate.trim());
        } catch (Exception e5) {
            Log.d("TAG", "instantiateItem: 5 " + e5.getMessage());
        }
        try {
            ((TextView) viewGroup2.findViewById(R.id.temp)).setText(String.format("%s", Common.tempTemplate));
        } catch (Exception e6) {
            Log.d("TAG", "instantiateItem: 6 " + e6.getMessage());
        }
        try {
            ((TextView) viewGroup2.findViewById(R.id.mainWeather)).setText(String.format("%s", Common.disTemplate));
        } catch (Exception e7) {
            Log.d("TAG", "instantiateItem: 7 " + e7.getMessage());
        }
        try {
            ImageView imageView = (ImageView) viewGroup2.findViewById(R.id.map);
            if (SpManager.getSelectTemplate() == 1) {
                Glide.with(this.context).load(getRoundedCornerBitmap(Common.mapTemplate, 245, 260, 40.0f)).into(imageView);
            } else if (SpManager.getSelectTemplate() == 2) {
                Glide.with(this.context).load(getRoundedCornerBitmap(Common.mapTemplate, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 200.0f)).into(imageView);
            } else if (SpManager.getSelectTemplate() == 3) {
                Glide.with(this.context).load(getRoundedCornerBitmap(Common.mapTemplate, 245, 260, 40.0f)).into(imageView);
            } else if (SpManager.getSelectTemplate() == 5) {
                Glide.with(this.context).load(getRoundedCornerBitmap(Common.mapTemplate, 218, 260, 40.0f)).into(imageView);
            } else if (SpManager.getSelectTemplate() == 6) {
                Glide.with(this.context).load(getRoundedCornerBitmap(Common.mapTemplate, 490, 145, 20.0f)).into(imageView);
            } else if (SpManager.getSelectTemplate() == 7) {
                Glide.with(this.context).load(getRoundedCornerBitmap(Common.mapTemplate, 218, 185, 40.0f)).into(imageView);
            }
        } catch (Exception e8) {
            Log.d("TAG", "instantiateItem: 8 " + Common.mapTemplate + "  " + e8.getMessage());
        }
        return viewGroup2;
    }

    @Override 
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override 
    public int getCount() {
        return this.arrayList.size();
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int i, int i2, float f) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(1);
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight()), f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }
}
