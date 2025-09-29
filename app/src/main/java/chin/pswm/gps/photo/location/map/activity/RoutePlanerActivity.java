package chin.pswm.gps.photo.location.map.activity;

import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showDynamicNativeData;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showUserInterDataBack;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import chin.pswm.gps.photo.location.map.AllKeyHub;
import chin.pswm.gps.photo.location.map.ads.AdsManager;
import chin.pswm.gps.photo.location.map.ads.adunit.banner.BannerType;
import chin.pswm.gps.photo.location.map.compose.ComposeBannerKt;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map_debug.BuildConfig;
import chin.pswm.gps.photo.location.map_debug.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class RoutePlanerActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;

    private static final int SPEECH_REQUEST_CODE_CURRENT = 1;
    private static final int SPEECH_REQUEST_CODE_DESTINATION = 2;
    private static final int LOCATION_PERMISSION_REQUEST = 100;
    private TextView tvOpenMap;
    private ImageView ivCurrentLocation, ivDestination, ivSwitch;
    EditText txtDestinationLocation, txtCurrentLocation;
    private LinearLayout llMapOption, liNormal, liSatellite, liTerrain, liHybrid;
    private String currentLocation = "";
    private String destinationLocation = "";
    private static final int SEARCH_REQUEST_CODE = 101;
    private Dialog mapTypeDialog;
    private ComposeView composeView;
    private ImageView ivNormal, ivSatellite, ivTerrain, ivHybrid, iv_back;
    private int selectedMapType = GoogleMap.MAP_TYPE_NORMAL;
    private boolean isUpdatingLocation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LanguageManager.setLocale(RoutePlanerActivity.this, SharedHelper.getString(RoutePlanerActivity.this, "lang_key", ""));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_planer);

        composeView = findViewById(R.id.composeView);
        txtCurrentLocation = findViewById(R.id.txtCurrentLocation);
        txtDestinationLocation = findViewById(R.id.txtDestinationLocation);
        ivCurrentLocation = findViewById(R.id.iv_current_location);
        ivDestination = findViewById(R.id.iv_destination);
        tvOpenMap = findViewById(R.id.tv_OpenMap);
        ivSwitch = findViewById(R.id.iv_switch);
        llMapOption = findViewById(R.id.ll_map_option);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUserInterDataBack(RoutePlanerActivity.this, new AllKeyHub.onCrashDataClose() {
                    @Override
                    public void onDataClose() {
                        AdsManager.INSTANCE.showInterInApp(
                                RoutePlanerActivity.this,
                                new Function0<Unit>() {
                                    @Override
                                    public Unit invoke() {
                                        RoutePlanerActivity.super.onBackPressed();
                                        return null;
                                    }
                                }
                        );
                    }
                });
            }
        });
        initSocketConnection(this, true, true);

        ivSwitch.setOnClickListener(view -> switchLocations());

        txtDestinationLocation.getText().toString();

        ivCurrentLocation.setOnClickListener(view -> startVoiceRecognition(SPEECH_REQUEST_CODE_CURRENT));

        // Voice input for destination location
        ivDestination.setOnClickListener(view -> startVoiceRecognition(SPEECH_REQUEST_CODE_DESTINATION));

        // Open Google Maps with directions
        tvOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGoogleMaps();
            }
        });
        llMapOption.setOnClickListener(view -> showMapTypeDialog());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.flMap);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        txtCurrentLocation.setOnClickListener(v -> getCurrentLocation());
        txtCurrentLocation.addTextChangedListener(textWatcher);
        txtDestinationLocation.addTextChangedListener(textWatcher);

        getCurrentLocation();

        ComposeBannerKt.setBannerContent(composeView,
                "RoutePlanerActivity",
                BuildConfig.banner_inapp,
                "banner_inapp",
                BannerType.BANNER_ADAPTIVE
        );
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

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            txtCurrentLocation.removeTextChangedListener(this);

            String currentText = txtCurrentLocation.getText().toString().trim();
            String destinationText = txtDestinationLocation.getText().toString().trim();

            if (currentText.isEmpty() && !isUpdatingLocation) {
                isUpdatingLocation = true;
                getCurrentLocation(); // Will auto-fill current location
            }

            // Toggle open map button
            tvOpenMap.setVisibility(!currentText.isEmpty() && !destinationText.isEmpty()
                    ? View.VISIBLE : View.GONE);

            txtCurrentLocation.addTextChangedListener(this);
        }
    };

    private void startVoiceRecognition(int requestCode) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now...");
        try {
            startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            Toast.makeText(this, "Your device does not support speech input", Toast.LENGTH_SHORT).show();
        }
    }

    private void switchLocations() {
        String temp = txtCurrentLocation.getText().toString();
        txtCurrentLocation.setText(txtDestinationLocation.getText().toString());
        txtDestinationLocation.setText(temp);
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            String currentText = txtCurrentLocation.getText().toString().trim();

            // ❗ Only update if user hasn't typed anything manually
            if (!currentText.isEmpty()) {
                isUpdatingLocation = false;
                return;
            }

            fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                if (location != null) {
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    getAddressFromLocation(latLng, txtCurrentLocation);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
                }
                isUpdatingLocation = false;
            }).addOnFailureListener(e -> {
                isUpdatingLocation = false;
            });

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            isUpdatingLocation = false;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Request Location Permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST);
            return;
        }

        // Enable My Location Layer
        mMap.setMyLocationEnabled(true);

        // Get Current Location
        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

                // Add Marker & Move Camera
                mMap.addMarker(new MarkerOptions().position(currentLatLng).title("You are here"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));
            }
        });

        // **Map Click Listener - Set Clicked Location as Destination**
        mMap.setOnMapClickListener(latLng -> {
            getAddressFromLocation(latLng, txtDestinationLocation); // Get address from clicked LatLng
            mMap.clear(); // Clear previous markers
            mMap.addMarker(new MarkerOptions().position(latLng).title("Selected Destination"));
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEARCH_REQUEST_CODE && resultCode == RESULT_OK) {
            String destination = data.getStringExtra("destination");
            if (destination != null) {
                txtDestinationLocation.setText(destination);
            }
        }
        if (resultCode == RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (result != null && !result.isEmpty()) {
                if (requestCode == SPEECH_REQUEST_CODE_CURRENT) {
                    currentLocation = result.get(0);
                    txtCurrentLocation.setText(currentLocation);
                } else if (requestCode == SPEECH_REQUEST_CODE_DESTINATION) {
                    destinationLocation = result.get(0);
                    txtDestinationLocation.setText(destinationLocation);
                }
            }
        }
    }

    private void openGoogleMaps() {
        // Get the latest entered values from EditText and TextView
        currentLocation = txtCurrentLocation.getText().toString().trim();
        destinationLocation = txtDestinationLocation.getText().toString().trim();

        // Validate input
        if (currentLocation.isEmpty() || destinationLocation.isEmpty()) {
            Toast.makeText(this, "Please enter both locations", Toast.LENGTH_SHORT).show();
            return;
        }

        // Open Google Maps for route
        String uri = "https://www.google.com/maps/dir/?api=1&origin=" + Uri.encode(currentLocation) + "&destination=" + Uri.encode(destinationLocation);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps");

        // Check if Google Maps is installed
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Google Maps is not installed", Toast.LENGTH_SHORT).show();
        }
    }


    private void getAddressFromLocation(LatLng latLng, TextView textView) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addresses != null && !addresses.isEmpty()) {
                String address = addresses.get(0).getAddressLine(0);
                textView.setText(address);
            } else {
                textView.setText("Unknown location");
            }
        } catch (IOException e) {
            e.printStackTrace();
            textView.setText("Error getting address");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                recreate();
            }
        }
    }

    private void showMapTypeDialog() {
        mapTypeDialog = new Dialog(this);
        mapTypeDialog.setContentView(R.layout.dialog_map_type);
        showDynamicNativeData(RoutePlanerActivity.this, null, mapTypeDialog.findViewById(R.id.Ad_Banner), true);

        mapTypeDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mapTypeDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        liNormal = mapTypeDialog.findViewById(R.id.li_normal);
        liSatellite = mapTypeDialog.findViewById(R.id.li_setellite);
        liTerrain = mapTypeDialog.findViewById(R.id.li_terrain);
        liHybrid = mapTypeDialog.findViewById(R.id.li_hybird);

        ivNormal = mapTypeDialog.findViewById(R.id.iv_normal);
        ivSatellite = mapTypeDialog.findViewById(R.id.iv_stalelite);
        ivTerrain = mapTypeDialog.findViewById(R.id.iv_terrain);
        ivHybrid = mapTypeDialog.findViewById(R.id.iv_hybrid);

        updateSelection();

        liNormal.setOnClickListener(v -> changeMapType(GoogleMap.MAP_TYPE_NORMAL));
        liSatellite.setOnClickListener(v -> changeMapType(GoogleMap.MAP_TYPE_SATELLITE));
        liTerrain.setOnClickListener(v -> changeMapType(GoogleMap.MAP_TYPE_TERRAIN));
        liHybrid.setOnClickListener(v -> changeMapType(GoogleMap.MAP_TYPE_HYBRID));

        mapTypeDialog.show();
    }

    private void changeMapType(int mapType) {
        if (mMap != null) {
            mMap.setMapType(mapType);
        }
        selectedMapType = mapType;
        updateSelection();
        mapTypeDialog.dismiss();
    }

    private void updateSelection() {
        ivNormal.setVisibility(selectedMapType == GoogleMap.MAP_TYPE_NORMAL ? View.VISIBLE : View.GONE);
        ivSatellite.setVisibility(selectedMapType == GoogleMap.MAP_TYPE_SATELLITE ? View.VISIBLE : View.GONE);
        ivTerrain.setVisibility(selectedMapType == GoogleMap.MAP_TYPE_TERRAIN ? View.VISIBLE : View.GONE);
        ivHybrid.setVisibility(selectedMapType == GoogleMap.MAP_TYPE_HYBRID ? View.VISIBLE : View.GONE);
    }
}