package chin.pswm.gps.photo.location.map.languegess;


import static chin.pswm.gps.photo.location.map.AllKeyHub.initSocketConnection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.platform.ComposeView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import chin.pswm.gps.photo.location.map.New_intro.New_IntroActivity;
import chin.pswm.gps.photo.location.map.activity.StartActivity;
import chin.pswm.gps.photo.location.map.compose.language.ComposeLanguageKt;
import chin.pswm.gps.photo.location.map.compose.language.ComposeLanguageState;
import chin.pswm.gps.photo.location.map_debug.R;


public class New_first_languagesselect extends AppCompatActivity {
    static ArrayList<Lang_item> languesslist = new ArrayList<>();
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
        languesslist.clear();
        languesslist.add(new Lang_item("English", "en", "english", R.drawable.eng));
        languesslist.add(new Lang_item("Deutsch", "de", "german", R.drawable.ger));
        languesslist.add(new Lang_item("Français", "fr", "French", R.drawable.french));
        languesslist.add(new Lang_item("हिंदी", "hi", "hindi", R.drawable.indi));
        languesslist.add(new Lang_item("Italiano", "it", "Italian", R.drawable.italian));
        languesslist.add(new Lang_item("Português", "pt", "Portuguese", R.drawable.portuguese));
        languesslist.add(new Lang_item("Türkçe", "tr", "Turkish", R.drawable.turki));
        languesslist.add(new Lang_item("Tiếng Việt", "vi", "Vietnamese", R.drawable.vietnam));
        languesslist.add(new Lang_item("Arabic", "ar", "Arabic", R.drawable.arab));

        selectedPosition = SharedHelper.getInt(getApplicationContext(), "Lastlang_poss", -1);
        if (selectedPosition >= 0 && selectedPosition < languesslist.size()) {
            languageSelected = true;
            localeCode = languesslist.get(selectedPosition).getLag_code();
        }
/*        LinearLayout languageContainer = findViewById(R.id.language_container);
        for (int i = 0; i < languesslist.size(); i++) {
            Lang_item langItem = languesslist.get(i);

            View languageView = LayoutInflater.from(this).inflate(R.layout.language_item, languageContainer, false);

            ImageView flagImage = languageView.findViewById(R.id.ivLanguageImage);
            TextView languageName = languageView.findViewById(R.id.tvLanguageName);
            ImageView radioButton = languageView.findViewById(R.id.radioButton);
            languges_s = languageView.findViewById(R.id.languges_s);
            Glide.with(this).load(langItem.getFlag_image()).into(flagImage);
            languageName.setText(langItem.getLag_name());

            radioButton.setVisibility(selectedPosition == i ? View.VISIBLE : View.GONE);
            languges_s.setBackground(selectedPosition == i
                    ? ContextCompat.getDrawable(this, R.drawable.bg_selected_gradient)
                    : ContextCompat.getDrawable(this, R.drawable.bg_gray_gradient));
            int finalI = i;
            languageView.setOnClickListener(v -> {
                selectedPosition = finalI;
                localeCode = langItem.getLag_code();
                SharedHelper.putInt(getApplicationContext(), "Lastlang_poss", selectedPosition);
                SharedHelper.putString(getApplicationContext(), "lang_key", localeCode);
                updateLanguageSelection(languageContainer);
            });

            languageContainer.addView(languageView);
        }*/

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));
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
        private final ArrayList<Lang_item> listdata;

        public allbank_MyLangAdapter(ArrayList<Lang_item> l0) {
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
            Lang_item langItem = listdata.get(position);

            if (selectedPosition == position) {
                holder.language_layout.setBackgroundResource(R.drawable.bg_lang_select);
                holder.tv_language_item.setTextColor(getColor(R.color.white));
            } else {
                holder.language_layout.setBackgroundResource(R.drawable.bg_lang);
                holder.tv_language_item.setTextColor(getColor(R.color.black));
            }
            Glide.with(getApplicationContext()).load(langItem.getFlag_image()).into(holder.flag_image);

            holder.tv_language_item.setText(langItem.getLag_name());
            holder.language_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedPosition = position;
                    notifyDataSetChanged();
                    btn_save.setVisibility(View.VISIBLE);
                    localeCode = langItem.getLag_code();
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
}