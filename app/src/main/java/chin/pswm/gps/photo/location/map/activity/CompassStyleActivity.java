package chin.pswm.gps.photo.location.map.activity;

import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;
import static chin.pswm.gps.photo.location.map.AllKeyHub.showUserInterDataBack;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import chin.pswm.gps.photo.location.map.AllKeyHub;
import chin.pswm.gps.photo.location.map.adapter.CompassStyleAdapter;
import chin.pswm.gps.photo.location.map.languegess.LanguageManager;
import chin.pswm.gps.photo.location.map.languegess.SharedHelper;
import chin.pswm.gps.photo.location.map.model.CompassStyle;
import chin.pswm.gps.photo.location.map_debug.R;

public class CompassStyleActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CompassStyleAdapter adapter;
    private List<CompassStyle> compassStyles;
    private TextView tvDone;
    private SharedPreferences sharedPreferences;
    ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LanguageManager.setLocale(CompassStyleActivity.this, SharedHelper.getString(CompassStyleActivity.this, "lang_key", ""));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass_style);

        recyclerView = findViewById(R.id.rv_list);
        tvDone = findViewById(R.id.tv_done);
        iv_back = findViewById(R.id.iv_back);
        sharedPreferences = getSharedPreferences("CompassPrefs", MODE_PRIVATE);
        initSocketConnection(this, true, true);

        loadCompassStyles();

        adapter = new CompassStyleAdapter(this, compassStyles, selectedStyle -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("selected_style", selectedStyle.getStyleImage());
            editor.putInt("selected_base", selectedStyle.getBaseImage());
            editor.apply();
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        tvDone.setOnClickListener(v -> {
            setResult(RESULT_OK);
            finish();
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUserInterDataBack(CompassStyleActivity.this, new AllKeyHub.onCrashDataClose() {
                    @Override
                    public void onDataClose() {
                        onBackPressed();
                    }
                });
            }
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
    private void loadCompassStyles() {
        compassStyles = new ArrayList<>();
        compassStyles.add(new CompassStyle(R.drawable.ic_compass_style_1, R.drawable.ic_compass_base_1, R.drawable.ic_compass_fan_1, 1));
        compassStyles.add(new CompassStyle(R.drawable.ic_compass_style_2, R.drawable.ic_compass_base_2, R.drawable.ic_compass_fan_2, 2));
        compassStyles.add(new CompassStyle(R.drawable.ic_compass_style_3, R.drawable.ic_compass_base_3, R.drawable.ic_compass_fan_3, 3));
        compassStyles.add(new CompassStyle(R.drawable.ic_compass_style_4, R.drawable.ic_compass_base_4, R.drawable.ic_compass_fan_4, 4));
        compassStyles.add(new CompassStyle(R.drawable.ic_compass_style_5, R.drawable.ic_compass_base_5, R.drawable.ic_compass_fan_5, 5));
        compassStyles.add(new CompassStyle(R.drawable.ic_compass_style_6, R.drawable.ic_compass_base_6, R.drawable.ic_compass_fan_6, 6));
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemNavigationBar();
    }

    private void hideSystemNavigationBar() {
        try {
            Window window = this.getWindow();
            WindowCompat.setDecorFitsSystemWindows(window, true);
            WindowInsetsControllerCompat windowCompat = WindowCompat.getInsetsController(window, window.getDecorView());
            windowCompat.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
            windowCompat.setAppearanceLightNavigationBars(false);
            windowCompat.hide(WindowInsetsCompat.Type.navigationBars());

        } catch (Exception e) {

        }
    }
}