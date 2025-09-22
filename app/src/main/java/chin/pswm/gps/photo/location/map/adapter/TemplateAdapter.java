package chin.pswm.gps.photo.location.map.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import chin.pswm.gps.photo.location.map_debug.databinding.TemplateAdapterBinding;
import chin.pswm.gps.photo.location.map.utils.SpManager;
import com.bumptech.glide.Glide;
import com.fom.rapid.assistant.HeyMoon;
import java.util.ArrayList;


public class TemplateAdapter extends RecyclerView.Adapter<TemplateAdapter.MyViewHolder> {
    public ArrayList<Integer> arrayList;
    public Context context;
    public OnClickTemplate onClickTemplate;
    public int selectPos = SpManager.getSelectTemplate();

    
    public interface OnClickTemplate {
        void clickTemplate(int i);
    }

    public TemplateAdapter(Context context, ArrayList<Integer> arrayList, OnClickTemplate onClickTemplate) {
        this.context = context;
        this.arrayList = arrayList;
        this.onClickTemplate = onClickTemplate;
    }

    @Override 
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(TemplateAdapterBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
        Glide.with(this.context).load(this.arrayList.get(myViewHolder.getAdapterPosition())).into(myViewHolder.binding.template);
        myViewHolder.binding.select.setVisibility(this.selectPos == i ? 0 : 8);
        myViewHolder.binding.mainRl.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                TemplateAdapter.this.m156x9ec4344b(i, view);
            }
        });
    }


    public  void m156x9ec4344b(int i, View view) {
        this.onClickTemplate.clickTemplate(i);
    }

    @Override 
    public int getItemCount() {
        return this.arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        
        public final TemplateAdapterBinding binding;

        public MyViewHolder(TemplateAdapterBinding templateAdapterBinding) {
            super(templateAdapterBinding.getRoot());
            this.binding = templateAdapterBinding;
            HeyMoon.resize().view(templateAdapterBinding.mainRl).measureWith(-1).now(TemplateAdapter.this.context);
            HeyMoon.resize().view(templateAdapterBinding.select).measureWith(-1).now(TemplateAdapter.this.context);
        }
    }

    public void updateAdapter(int i) {
        this.selectPos = i;
        notifyDataSetChanged();
    }
}
