package chin.pswm.gps.photo.location.map.activity;

import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showUserInterDataBack;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import chin.pswm.gps.photo.location.map.AllKeyHub;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map.adapter.ViewPagerAdapter;
import chin.pswm.gps.photo.location.map.fragment.CameraFragment;
import chin.pswm.gps.photo.location.map.fragment.CompassFragment;
import chin.pswm.gps.photo.location.map.fragment.MapFragment;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.model.CompassStyle;

public class CompassActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private ImageView compassImage;
    private TextView tvDirection, tvLat, tvLong;
    private float currentDegree = 0f;
    private ViewPager viewPager;
    private TextView tvCamera, tvCompass, tvMap,tv_change_style_;
    private FusedLocationProviderClient fusedLocationClient;
    private ImageView ivBaseImg, ivCompass,iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LanguageManager.setLocale(CompassActivity.this, SharedHelper.getString(CompassActivity.this, "lang_key", ""));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass); // Your XML layout
        viewPager = findViewById(R.id.viewPager);
        tvCamera = findViewById(R.id.tv_camera);
        tvCompass = findViewById(R.id.tv_compass);
        iv_back = findViewById(R.id.iv_back);
        tvMap = findViewById(R.id.tv_map);
        compassImage = findViewById(R.id.iv_compass);
        tvDirection = findViewById(R.id.tv_direct);
        tvLat = findViewById(R.id.tv_lat);
        tvLong = findViewById(R.id.tv_long);
        ivBaseImg = findViewById(R.id.iv_base_img);
        ivCompass = findViewById(R.id.iv_compass);
        tv_change_style_=findViewById(R.id.tv_change_style_);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        initSocketConnection(this, true, true);

        setupViewPager();
        getCurrentLocation();
        viewPager.setCurrentItem(1);
        updateTabBackground(tvCompass);
        tv_change_style_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(CompassActivity.this, CompassStyleActivity.class);
                startActivityForResult(intent, 100); // Start activity with request code
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUserInterDataBack(CompassActivity.this, new AllKeyHub.onCrashDataClose() {
                    @Override
                    public void onDataClose() {
                        onBackPressed();
                    }
                });
            }
        });
        tvCamera.setOnClickListener(v -> {
            viewPager.setCurrentItem(0);
            updateTabBackground(tvCamera);
        });

        tvCompass.setOnClickListener(v -> {
            viewPager.setCurrentItem(1);
            updateTabBackground(tvCompass);
        });

        tvMap.setOnClickListener(v -> {
            viewPager.setCurrentItem(2);
            updateTabBackground(tvMap);
        });
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == 0) updateTabBackground(tvCamera);
                else if (position == 1) updateTabBackground(tvCompass);
                else if (position == 2) updateTabBackground(tvMap);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {}

            @Override
            public void onPageScrollStateChanged(int arg0) {}
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showUserInterDataBack(this, new AllKeyHub.onCrashDataClose() {
                @Override
                public void onDataClose() {
                    onBackPressed();
                }
            });
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            return super.onKeyDown(keyCode, event);
        }
        return true;
    }
    private void updateTabBackground(TextView selectedTab) {
        tvCamera.setBackgroundResource(R.drawable.compass_tool_bar_rounded);
        tvCompass.setBackgroundResource(R.drawable.compass_tool_bar_rounded);
        tvMap.setBackgroundResource(R.drawable.compass_tool_bar_rounded);
        tvCamera.setTextColor(getColor(R.color.black));
        tvCompass.setTextColor(getColor(R.color.black));
        tvMap.setTextColor(getColor(R.color.black));


        selectedTab.setBackgroundResource(R.drawable.theme_rounded);
        selectedTab.setTextColor(getColor(R.color.white));
    }
    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CameraFragment(), "Camera");
        adapter.addFragment(new CompassFragment(), "Compass");
        adapter.addFragment(new MapFragment(), "Map");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        applySavedCompassStyle();

        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_UI);
    }

    private void getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(location -> {
                        if (location != null) {
                            tvLat.setText("Lat: " + location.getLatitude());
                            tvLong.setText("Long: " + location.getLongitude());
                        }
                    });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    private float[] gravity;
    private float[] geomagnetic;

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            gravity = event.values;
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            geomagnetic = event.values;
        }

        if (gravity != null && geomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];
            if (SensorManager.getRotationMatrix(R, I, gravity, geomagnetic)) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                float azimuth = (float) Math.toDegrees(orientation[0]);
                azimuth = (azimuth + 360) % 360;

                RotateAnimation ra = new RotateAnimation(
                        currentDegree, -azimuth,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);

                ra.setDuration(500);
                ra.setFillAfter(true);
                compassImage.startAnimation(ra);
                currentDegree = -azimuth;

                tvDirection.setText((int) azimuth + "° " + getDirection(azimuth));
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private String getDirection(float azimuth) {
        String[] directions = {
                "N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE",
                "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW", "N"
        };

        int index = Math.round(azimuth / 22.5f);
        return directions[index];
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            applySavedCompassStyle(); // Load and apply the saved compass style
        }
    }
    private void applySavedCompassStyle() {
        SharedPreferences sharedPreferences = getSharedPreferences("CompassPrefs", MODE_PRIVATE);
        int selectedStyle = sharedPreferences.getInt("selected_style", R.drawable.ic_compass_style_1);
        int selectedBase = sharedPreferences.getInt("selected_base", R.drawable.ic_compass_base_1);

        // Ensure previous images are not causing overlap
        ivCompass.setImageDrawable(null);
        ivBaseImg.setImageDrawable(null);

        // Set new images
        ivCompass.setImageResource(selectedStyle);
        ivBaseImg.setImageResource(selectedBase);

        // Make sure only one is visible
        ivCompass.setVisibility(View.VISIBLE);
        ivBaseImg.setVisibility(View.GONE);
    }
}