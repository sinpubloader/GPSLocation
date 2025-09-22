//package chin.pswm.gps.photo.location.map.activity;
//
//import android.app.ActivityManager;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.SystemClock;
//import android.view.View;
//import androidx.fragment.app.FragmentActivity;
//import chin.pswm.gps.photo.location.map_debug.R;
//import chin.pswm.gps.photo.location.map.adapter.LanguageAdapter;
//import chin.pswm.gps.photo.location.map.ads.AdsVariable;
//import chin.pswm.gps.photo.location.map_debug.databinding.ActivityLanguageBinding;
//import chin.pswm.gps.photo.location.map.model.LanguageModel;
//import chin.pswm.gps.photo.location.map.utils.BaseActivity;
//import chin.pswm.gps.photo.location.map.utils.SpManager;
//import com.bumptech.glide.Glide;
//import java.util.ArrayList;
//import java.util.List;
//@SuppressWarnings("all")a
//
//public class LanguageActivity extends BaseActivity implements LanguageAdapter.ClickLanguage {
//    ActivityLanguageBinding binding;
//    public LanguageModel defaultModel;
//    LanguageAdapter languageAdapter;
//    ArrayList<LanguageModel> language_list = new ArrayList<>();
//    private long mLastClickTime = 0;
//
//    @Override
//    public void onCreate(Bundle bundle) {
//        super.onCreate(bundle);
//        ActivityLanguageBinding inflate = ActivityLanguageBinding.inflate(getLayoutInflater());
//        this.binding = inflate;
//        setContentView(inflate.getRoot());
//        if (getIntent().getIntExtra("Type", 1) == 1) {
//            this.binding.back.setVisibility(8);
//        } else {
//            this.binding.back.setVisibility(0);
//        }
//        this.binding.back.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public final void onClick(View view) {
//                LanguageActivity.this.m95x751f70ab(view);
//            }
//        });
//        this.binding.save.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View view) {
//                if (SystemClock.elapsedRealtime() - LanguageActivity.this.mLastClickTime < 1000) {
//                    return;
//                }
//                LanguageActivity.this.mLastClickTime = SystemClock.elapsedRealtime();
//                SpManager.setLanguageCode(SpManager.getLanguageCodeSnip());
//                if (LanguageActivity.this.getIntent().getIntExtra("Type", 1) == 1) {
//                    LanguageActivity.this.startActivity(new Intent(LanguageActivity.this, IntroActivity.class).putExtra("Type", 1));
//                    LanguageActivity.this.finish();
//                    return;
//                }
//                Intent intent = new Intent(LanguageActivity.this, StartActivity.class);
//                intent.setFlags(32768);
//                intent.setFlags(67108864);
//                intent.setFlags(268435456);
//                LanguageActivity.this.startActivity(intent);
//                LanguageActivity.this.finish();
//            }
//        });
//        if (SpManager.isIndian()) {
//            this.language_list.add(new LanguageModel(R.drawable.english, "English (Default)", "en"));
//            this.language_list.add(new LanguageModel(R.drawable.hindi, "Hindi (हिंदी)", "hi"));
//            this.language_list.add(new LanguageModel(R.drawable.punjabi, "Punjabi (ਪੰਜਾਬੀ)", "pa"));
//            this.language_list.add(new LanguageModel(R.drawable.arabic, "Arabic (عربي)", "ar"));
//            this.language_list.add(new LanguageModel(R.drawable.chinese, "Chinese (中文)", "zh"));
//            this.language_list.add(new LanguageModel(R.drawable.dutch, "Dutch (Nederlands)", "nl"));
//            this.language_list.add(new LanguageModel(R.drawable.french, "French (Français)", "fr"));
//            this.language_list.add(new LanguageModel(R.drawable.german, "German (Deutsch)", "de"));
//            this.language_list.add(new LanguageModel(R.drawable.indonesian, "Indonesian (Indonesia)", "in"));
//            this.language_list.add(new LanguageModel(R.drawable.italian, "Italian (Italiana)", "it"));
//            this.language_list.add(new LanguageModel(R.drawable.japanese, "Japanese (日本語)", "ja"));
//            this.language_list.add(new LanguageModel(R.drawable.korean, "Korean (한국인)", "ko"));
//            this.language_list.add(new LanguageModel(R.drawable.malaysian, "Malaysian (Malaysia)", "ms"));
//            this.language_list.add(new LanguageModel(R.drawable.portuguese, "Portuguese (Português)", "pt"));
//            this.language_list.add(new LanguageModel(R.drawable.russian, "Russian (Русский)", "ru"));
//            this.language_list.add(new LanguageModel(R.drawable.spanish, "Spanish (Española)", "es"));
//            this.language_list.add(new LanguageModel(R.drawable.thai, "Thai (แบบไทย)", "th"));
//            this.language_list.add(new LanguageModel(R.drawable.turkish, "Turkish (Türkçe)", "tr"));
//            this.language_list.add(new LanguageModel(R.drawable.vietnamese, "Vietnamese (Tiếng Việt)", "vi"));
//        } else {
//            this.language_list.add(new LanguageModel(R.drawable.english, "English (Default)", "en"));
//            this.language_list.add(new LanguageModel(R.drawable.arabic, "Arabic (عربي)", "ar"));
//            this.language_list.add(new LanguageModel(R.drawable.chinese, "Chinese (中文)", "zh"));
//            this.language_list.add(new LanguageModel(R.drawable.dutch, "Dutch (Nederlands)", "nl"));
//            this.language_list.add(new LanguageModel(R.drawable.french, "French (Français)", "fr"));
//            this.language_list.add(new LanguageModel(R.drawable.german, "German (Deutsch)", "de"));
//            this.language_list.add(new LanguageModel(R.drawable.hindi, "Hindi (हिंदी)", "hi"));
//            this.language_list.add(new LanguageModel(R.drawable.indonesian, "Indonesian (Indonesia)", "in"));
//            this.language_list.add(new LanguageModel(R.drawable.italian, "Italian (Italiana)", "it"));
//            this.language_list.add(new LanguageModel(R.drawable.japanese, "Japanese (日本語)", "ja"));
//            this.language_list.add(new LanguageModel(R.drawable.korean, "Korean (한국인)", "ko"));
//            this.language_list.add(new LanguageModel(R.drawable.malaysian, "Malaysian (Malaysia)", "ms"));
//            this.language_list.add(new LanguageModel(R.drawable.portuguese, "Portuguese (Português)", "pt"));
//            this.language_list.add(new LanguageModel(R.drawable.punjabi, "Punjabi (ਪੰਜਾਬੀ)", "pa"));
//            this.language_list.add(new LanguageModel(R.drawable.russian, "Russian (Русский)", "ru"));
//            this.language_list.add(new LanguageModel(R.drawable.spanish, "Spanish (Española)", "es"));
//            this.language_list.add(new LanguageModel(R.drawable.thai, "Thai (แบบไทย)", "th"));
//            this.language_list.add(new LanguageModel(R.drawable.turkish, "Turkish (Türkçe)", "tr"));
//            this.language_list.add(new LanguageModel(R.drawable.vietnamese, "Vietnamese (Tiếng Việt)", "vi"));
//        }
//        for (int i = 0; i < this.language_list.size(); i++) {
//            if (SpManager.getLanguageCode().equals(this.language_list.get(i).getId())) {
//                this.defaultModel = this.language_list.get(i);
//                this.binding.language.setText(this.defaultModel.getLanguage_name());
//                Glide.with((FragmentActivity) this).load(Integer.valueOf(this.defaultModel.getImage())).into(this.binding.languageImage);
//                Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.radio_select)).into(this.binding.select);
//                this.language_list.remove(i);
//            }
//        }
//        this.languageAdapter = new LanguageAdapter(this, this.language_list, this);
//        this.binding.languageLayout.setAdapter(this.languageAdapter);
//        this.binding.lanLayout.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public final void onClick(View view) {
//                LanguageActivity.this.m96x7b233c0a(view);
//            }
//        });
//    }
//
//
//    public  void m95x751f70ab(View view) {
//        onBackPressed();
//    }
//
//
//    public  void m96x7b233c0a(View view) {
//        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.radio_select)).into(this.binding.select);
//        this.binding.lanLayout.setBackground(getResources().getDrawable(R.drawable.language_bg_select));
//        SpManager.setLanguageCodeSnip(this.defaultModel.getId());
//        SpManager.setLanguageSelected(true);
//        this.languageAdapter.updateAdapter();
//    }
//
//    @Override
//    public void onBackPressed() {
//        List<ActivityManager.AppTask> appTasks;
//        SpManager.setLanguageCodeSnip(SpManager.getLanguageCode());
//        if (getIntent().getIntExtra("Type", 1) == 1) {
//            ActivityManager activityManager = (ActivityManager) getSystemService("activity");
//            if (activityManager == null || (appTasks = activityManager.getAppTasks()) == null || appTasks.size() <= 0) {
//                return;
//            }
//            for (ActivityManager.AppTask appTask : appTasks) {
//                appTask.finishAndRemoveTask();
//            }
//            return;
//        }
//        finish();
//    }
//
//    @Override
//    public void clickItem() {
//        Glide.with((FragmentActivity) this).load(Integer.valueOf((int) R.drawable.radio_unselect)).into(this.binding.select);
//        this.binding.lanLayout.setBackground(getResources().getDrawable(R.drawable.language_bg_unselected));
//        this.languageAdapter.updateAdapter();
//    }
//}
