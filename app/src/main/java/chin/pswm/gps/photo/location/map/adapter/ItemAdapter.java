package chin.pswm.gps.photo.location.map.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import chin.pswm.gps.photo.location.map_debug.databinding.ItemLayoutBinding;
import chin.pswm.gps.photo.location.map.interfaces.OnClickGallery;
import com.bumptech.glide.Glide;
import com.fom.rapid.assistant.HeyMoon;
import java.util.ArrayList;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    public ArrayList<String> arrayList;
    public Context context;
    public OnClickGallery onClickGallery;

    public ItemAdapter(Context context, ArrayList<String> arrayList, OnClickGallery onClickGallery) {
        this.context = context;
        this.arrayList = arrayList;
        this.onClickGallery = onClickGallery;
    }

    @Override 
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
        Glide.with(this.context).load(this.arrayList.get(myViewHolder.getAdapterPosition())).into(myViewHolder.binding.image);
        myViewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                ItemAdapter.this.m153x81007292(i, view);
            }
        });
    }

    
    public  void m153x81007292(int i, View view) {
        this.onClickGallery.onClickItem(this.arrayList.get(i));
    }

    @Override 
    public int getItemCount() {
        return this.arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        
        public final ItemLayoutBinding binding;

        public MyViewHolder(ItemLayoutBinding itemLayoutBinding) {
            super(itemLayoutBinding.getRoot());
            this.binding = itemLayoutBinding;
            HeyMoon.resize().view(itemLayoutBinding.mainRl).measureWith(-1).now(ItemAdapter.this.context);
        }
    }
}
