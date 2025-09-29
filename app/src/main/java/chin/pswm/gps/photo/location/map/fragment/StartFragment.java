package chin.pswm.gps.photo.location.map.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import java.util.Iterator;

import chin.pswm.gps.photo.location.map.activity.MyCreationActivity;
import chin.pswm.gps.photo.location.map.activity.PreviewActivity;
import chin.pswm.gps.photo.location.map.activity.StartActivity;
import chin.pswm.gps.photo.location.map.model.PlaceData;
import chin.pswm.gps.photo.location.map.utils.StorageUtils;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map_debug.databinding.FragmentStartBinding;
@SuppressWarnings("all")


public class StartFragment extends Fragment implements OnMapReadyCallback, ClusterManager.OnClusterItemClickListener<PlaceData>, ClusterManager.OnClusterClickListener<PlaceData> {
    Activity activity;
    FragmentStartBinding binding;
    private ClusterManager<PlaceData> clusterManager;
    private GoogleMap mMap;

    public StartFragment() {
    }

    public StartFragment(Activity activity2) {
        this.activity = activity2;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentStartBinding inflate = FragmentStartBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        return inflate.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.activity == null) {
            this.activity = getActivity();
        }
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (supportMapFragment != null) {
            supportMapFragment.getMapAsync(this);
        }
    }

    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(20.5937d, 78.9629d), 1.0f));
        this.mMap.setMapType(1);
        ClusterManager<PlaceData> clusterManager2 = new ClusterManager<>(requireActivity(), this.mMap);
        this.clusterManager = clusterManager2;
        clusterManager2.setRenderer(new PlaceRender(getContext(), this.mMap, this.clusterManager));
        this.clusterManager.setOnClusterClickListener(this);
        this.clusterManager.setOnClusterItemClickListener(this);
        this.mMap.setOnCameraIdleListener(this.clusterManager);

        this.mMap.setOnMarkerClickListener(this.clusterManager);
        this.clusterManager.addItems(StartActivity.placesArrayList);
        this.clusterManager.cluster();
    }

    public boolean onClusterClick(Cluster<PlaceData> cluster) {
        Log.d("TAG", "locationListGet: " + cluster.getItems().size());
        MyCreationActivity.cluster = cluster;
        startActivity(new Intent(getContext(), MyCreationActivity.class).setFlags(536870912).putExtra("Call", "PlacesFragment"));
        return true;
    }

    public boolean onClusterItemClick(PlaceData placeData) {
        Log.d("TAG", "locationListGet: " + placeData.getPath());
        startActivity(new Intent(getContext(), PreviewActivity.class).setFlags(536870912).putExtra("Path", placeData.getPath()));
        return true;
    }

    public class PlaceRender extends DefaultClusterRenderer<PlaceData> {
        private final IconGenerator mClusterIconGenerator;
        private final int mDimensionHeight;
        private final int mDimensionWidth;
        private final IconGenerator mIconGenerator;
        public ImageView mImageView;
        public ImageView photo;

        public PlaceRender(Context context, GoogleMap googleMap, ClusterManager<PlaceData> clusterManager) {
            super(context, googleMap, clusterManager);
            IconGenerator iconGenerator = new IconGenerator(StartFragment.this.getContext());
            this.mIconGenerator = iconGenerator;
            IconGenerator iconGenerator2 = new IconGenerator(StartFragment.this.getContext());
            this.mClusterIconGenerator = iconGenerator2;
            View inflate = StartFragment.this.getLayoutInflater().inflate(R.layout.map_layout, (ViewGroup) null);
            this.photo = (ImageView) inflate.findViewById(R.id.photo);
            iconGenerator2.setContentView(inflate);
            this.mImageView = new ImageView(StartFragment.this.getContext());
            int dimension = (int) StartFragment.this.getResources().getDimension(R.dimen.custom_profile_image_width);
            this.mDimensionWidth = dimension;
            int dimension2 = (int) StartFragment.this.getResources().getDimension(R.dimen.custom_profile_image_height);
            this.mDimensionHeight = dimension2;
            this.mImageView.setLayoutParams(new ViewGroup.LayoutParams(dimension, dimension2));
            this.photo.setLayoutParams(new ViewGroup.LayoutParams(dimension, dimension2));
            iconGenerator.setContentView(this.mImageView);
        }

        
        public void onBeforeClusterItemRendered(PlaceData placeData, MarkerOptions markerOptions) {
            super.onBeforeClusterItemRendered(placeData, markerOptions);
            try {
                StartFragment startFragment = StartFragment.this;
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(StartFragment.mergeToPin(startFragment.drawableToBitmap(startFragment.getResources().getDrawable(R.drawable.pin)), StartFragment.getRoundedBitmap(Bitmap.createScaledBitmap(StorageUtils.getBitmapFromFile(placeData.getPath()), 80, 80, false)))));
            } catch (Exception unused) {
            }
        }

        
        public void onBeforeClusterRendered(Cluster<PlaceData> cluster, MarkerOptions markerOptions) {
            super.onBeforeClusterRendered(cluster, markerOptions);
            Log.d("TAG", "onBeforeClusterItemRendered: 456 ");
            try {
                Iterator<PlaceData> it = cluster.getItems().iterator();
                StartFragment startFragment = StartFragment.this;
                Bitmap mergeToPin = StartFragment.mergeToPin(startFragment.drawableToBitmap(startFragment.getResources().getDrawable(R.drawable.pin)), StartFragment.getRoundedBitmap(Bitmap.createScaledBitmap(StorageUtils.getBitmapFromFile(it.next().getPath()), 80, 80, false)));
                StartFragment startFragment2 = StartFragment.this;
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(StartFragment.mergeToPinTwo(mergeToPin, StartFragment.this.writeTextOnDrawable(startFragment2.drawableToBitmapTwo(startFragment2.getResources().getDrawable(R.drawable.digit_bg)), String.valueOf(cluster.getSize())))));
            } catch (Exception unused) {
            }
        }
    }

    public static Bitmap mergeToPin(Bitmap bitmap, Bitmap bitmap2) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        int width = bitmap.getWidth();
        int width2 = bitmap2.getWidth();
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(bitmap2, (float) ((width - width2) / 2), (float) (((bitmap.getHeight() - bitmap2.getHeight()) / 2) - 12), (Paint) null);
        return createBitmap;
    }

    public static Bitmap mergeToPinTwo(Bitmap bitmap, Bitmap bitmap2) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(bitmap2, (float) ((bitmap.getWidth() - bitmap2.getWidth()) - 10), 0.0f, (Paint) null);
        return createBitmap;
    }

    
    public Bitmap writeTextOnDrawable(Bitmap bitmap, String str) {
        Bitmap bitmap2 = bitmap;
        String str2 = str;
        try {
            String str3 = Integer.parseInt(str) >= 100 ? "99+" : str2;
            Typeface create = Typeface.create("Helvetica", 1);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            paint.setTypeface(create);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.getTextBounds(str3, 0, str3.length(), new Rect());
            Bitmap copy = bitmap2.copy(bitmap.getConfig(), true);
            Canvas canvas = new Canvas(copy);
            if (str3.equals("99+")) {
                paint.setTextSize(20.0f);
            } else {
                paint.setTextSize(25.0f);
            }
            canvas.drawText(str3, (float) (canvas.getWidth() / 2), (float) ((int) (((float) (canvas.getHeight() / 2)) - ((paint.descent() + paint.ascent()) / 2.0f))), paint);
            return new BitmapDrawable(getResources(), copy).getBitmap();
        } catch (Exception unused) {
            Typeface create2 = Typeface.create("Helvetica", 1);
            Paint paint2 = new Paint();
            paint2.setStyle(Paint.Style.FILL);
            paint2.setColor(ViewCompat.MEASURED_STATE_MASK);
            paint2.setTextSize(25.0f);
            paint2.setTypeface(create2);
            paint2.setTextAlign(Paint.Align.CENTER);
            Rect rect = new Rect();
            paint2.getTextBounds(str2, 0, str.length(), rect);
            Bitmap copy2 = bitmap2.copy(bitmap.getConfig(), true);
            Canvas canvas2 = new Canvas(copy2);
            if (rect.width() >= canvas2.getWidth()) {
                paint2.setTextSize(25.0f);
            }
            canvas2.drawText(str2, (float) (canvas2.getWidth() / 2), (float) ((int) (((float) (canvas2.getHeight() / 2)) - ((paint2.descent() + paint2.ascent()) / 2.0f))), paint2);
            return new BitmapDrawable(getResources(), copy2).getBitmap();
        }
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public Bitmap drawableToBitmapTwo(Drawable drawable) {
        Bitmap createBitmap = Bitmap.createBitmap(40, 40, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    
    public static Bitmap getRoundedBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new Shader());
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) width, (float) height), (float) (width / 2), (float) (height / 2), paint);
        return createBitmap;
    }
}