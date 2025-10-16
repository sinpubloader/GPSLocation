package chin.pswm.gps.photo.location.map.languegess;


import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import chin.pswm.gps.photo.location.map.New_intro.New_IntroActivity;
import chin.pswm.gps.photo.location.map.activity.StartActivity;
import chin.pswm.gps.photo.location.map.compose.language.ComposeLanguageKt;
import chin.pswm.gps.photo.location.map.compose.language.ComposeLanguageState;
import chin.pswm.gps.photo.location.map.utils.LocaleUtils;
import chin.pswm.gps.photo.location.map_debug.R;


public class New_first_languagesselect extends AppCompatActivity {
    public static final ArrayList<String> languesslist = new ArrayList<>(List.of(
            "en", "fr", "in", "pt", "es", "hi", "it", "ms", "ja", "ko",
            "de", "ar", "fa", "ru", "zh", "tr", "cs", "nl", "vi", "hu",
            "ro", "pl", "bg", "el", "sk", "da", "iw", "hr", "sl", "sv",
            "ca", "uk", "th", "no", "fi", "ms-BN", "ms-MY", "ms-SG", "ms-ID",
            "ur-IN", "ur-PK", "bn", "km", "my", "az", "uz", "gu", "ta", "te",
            "mr", "kn", "or", "ml", "sq"
    ));
    String localeCode;
    int selectedPosition = -1;
    TextView btn_save;
    ComposeView composeView;
    boolean languageSelected = false;
    boolean fromSplash = false;
    boolean fromNavigationBar;
    LinearLayout languges_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        fromNavigationBar = getIntent().getBooleanExtra("fromNavigationBar", false);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        initSocketConnection(this, true, true);

        languesslist.sort((code1, code2) -> {
            String name1 = LocaleUtils.name(code1);
            String name2 = LocaleUtils.name(code2);

            return name1.compareToIgnoreCase(name2);
        });

        selectedPosition = SharedHelper.getInt(getApplicationContext(), "Lastlang_poss", -1);
        if (selectedPosition >= 0 && selectedPosition < languesslist.size()) {
            languageSelected = true;
            localeCode = languesslist.get(selectedPosition);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        allbank_MyLangAdapter adapter = new allbank_MyLangAdapter(languesslist);
        recyclerView.setAdapter(adapter);
        composeView = findViewById(R.id.composeView);
        btn_save = findViewById(R.id.done);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ComposeLanguageState.INSTANCE.getClickedLanguage()) {
                    nextScreen();
                } else {
                    Toast toast = Toast.makeText(New_first_languagesselect.this, R.string.select_language_please, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (fromNavigationBar)
                    finish();
            }
        });
        ComposeLanguageKt.setMyContent(composeView);
    }


    Intent intent;

    private void nextScreen() {
        if (localeCode == null) {
            Toast.makeText(this, "Please Select A Language ", Toast.LENGTH_SHORT).show();
        } else {
            LanguageManager.setLocale(this, localeCode);
            if (fromNavigationBar) {
                intent = new Intent(getApplicationContext(), StartActivity.class);
            } else {
                intent = new Intent(getApplicationContext(), New_IntroActivity.class);
            }
            intent.putExtra("selectedLocale", localeCode);
            startActivity(intent);
            finish();
        }
    }

    public class allbank_MyLangAdapter extends RecyclerView.Adapter<allbank_MyLangAdapter.ViewHolder> {
        private final ArrayList<String> listdata;

        public allbank_MyLangAdapter(ArrayList<String> l0) {
            this.listdata = l0;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem = layoutInflater.inflate(R.layout.language_item, parent, false);
            return new ViewHolder(listItem);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            String langItem = listdata.get(position);

            if (selectedPosition == position) {
//                holder.language_layout.setBackgroundResource(R.drawable.btn_global_square1);
                holder.language_layout.setBackgroundResource(R.drawable.permission_detail_allow);
//                holder.tv_language_item.setTextColor(getColor(R.color.white));
            } else {
//                holder.language_layout.setBackgroundResource(R.drawable.btn_global_card);
                holder.language_layout.setBackgroundResource(R.drawable.permission_detail);
//                holder.tv_language_item.setTextColor(getColor(R.color.black));
            }

            holder.tv_language_item.setText(LocaleUtils.name(langItem));
            holder.language_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedPosition = position;
                    notifyDataSetChanged();
                    btn_save.setVisibility(View.VISIBLE);
                    localeCode = langItem;
                    ComposeLanguageState.INSTANCE.setClickedLanguage(true);
                    SharedHelper.putInt(getApplicationContext(), "Lastlang_poss", selectedPosition);
                    SharedHelper.putString(getApplicationContext(), "lang_key", localeCode);
                }
            });
        }

        @Override
        public int getItemCount() {
            return listdata.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tv_language_item;
            public ImageView selected_language;
            LinearLayout language_layout;
            public ImageView flag_image;

            public ViewHolder(View itemView) {
                super(itemView);
                tv_language_item = itemView.findViewById(R.id.tvLanguageName);
                selected_language = itemView.findViewById(R.id.ivLanguageImage);
                language_layout = itemView.findViewById(R.id.rl_language);
                flag_image = itemView.findViewById(R.id.ivLanguageImage);
            }
        }
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