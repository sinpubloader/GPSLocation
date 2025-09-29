package chin.pswm.gps.photo.location.map.adapter;

import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fom.rapid.assistant.HeyMoon;

import java.util.ArrayList;

import chin.pswm.gps.photo.location.map.model.LanguageModel;
import chin.pswm.gps.photo.location.map.utils.SpManager;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map_debug.databinding.LanguageLayoutBinding;


public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.MyViewHolder> {
    private static long mLastClickTime;
    public ClickLanguage clickLanguage;
    public Context context;
    public ArrayList<LanguageModel> language_list;

    
    public interface ClickLanguage {
        void clickItem();
    }

    public LanguageAdapter(Context context, ArrayList<LanguageModel> arrayList, ClickLanguage clickLanguage) {
        this.context = context;
        this.language_list = arrayList;
        this.clickLanguage = clickLanguage;
    }

    @Override 
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LanguageLayoutBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
        Glide.with(this.context).load(Integer.valueOf(this.language_list.get(myViewHolder.getAdapterPosition()).getImage())).into(myViewHolder.binding.languageImage);
        myViewHolder.binding.language.setText(this.language_list.get(i).getLanguage_name());
        myViewHolder.binding.lanLayout.setBackground(this.context.getResources().getDrawable(this.language_list.get(i).getId().equals(SpManager.getLanguageCodeSnip()) ? R.drawable.language_bg_select : R.drawable.language_bg_unselected));
        myViewHolder.binding.select.setImageDrawable(this.context.getResources().getDrawable(this.language_list.get(i).getId().equals(SpManager.getLanguageCodeSnip()) ? R.drawable.radio_select : R.drawable.radio_unselect));
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - LanguageAdapter.mLastClickTime < 1000) {
                    return;
                }
                long unused = LanguageAdapter.mLastClickTime = SystemClock.elapsedRealtime();
                SpManager.setLanguageCodeSnip(LanguageAdapter.this.language_list.get(i).getId());
                SpManager.setLanguageSelected(true);
                LanguageAdapter.this.clickLanguage.clickItem();
            }
        });
    }

    @Override 
    public int getItemCount() {
        return this.language_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        
        public final LanguageLayoutBinding binding;

        public MyViewHolder(LanguageLayoutBinding languageLayoutBinding) {
            super(languageLayoutBinding.getRoot());
            this.binding = languageLayoutBinding;
            HeyMoon.resize().view(languageLayoutBinding.lanLayout).measureWith(-1).now(LanguageAdapter.this.context);
            HeyMoon.resize().view(languageLayoutBinding.languageImage).measureWith(-1).now(LanguageAdapter.this.context);
            HeyMoon.resize().view(languageLayoutBinding.select).measureWith(-1).now(LanguageAdapter.this.context);
        }
    }

    public void updateAdapter() {
        notifyDataSetChanged();
    }
}
