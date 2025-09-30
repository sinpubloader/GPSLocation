package chin.pswm.gps.photo.location.map.New_intro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.compose.ui.platform.ComposeView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import chin.pswm.gps.photo.location.map.compose.onboard.ComposeOnboardKt;
import chin.pswm.gps.photo.location.map_debug.R;


public class ViewAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images = {R.drawable.intro_1, R.drawable.intro_2, R.drawable.intro_3, R.drawable.intro_4};
    private Integer[] text1 = {R.string.intro_1, R.string.intro_2, R.string.intro_3, R.string.intro_4};
//    private Integer[] text2 = {R.string.intro_11, R.string.intro_22, R.string.intro_33};

    public ViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.intro_item, null);

        ImageView imageView = view.findViewById(R.id.image_view);
        TextView text01 = view.findViewById(R.id.view1);
        ComposeView composeView = view.findViewById(R.id.composeView);
//        TextView text02 = view.findViewById(R.id.view2);


        imageView.setImageResource(images[position]);
        text01.setText(text1[position]);

//        text02.setText(text2[position]);


        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);


        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
