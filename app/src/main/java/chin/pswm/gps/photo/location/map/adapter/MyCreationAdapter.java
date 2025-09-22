package chin.pswm.gps.photo.location.map.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import chin.pswm.gps.photo.location.map_debug.databinding.MyCreationLayoutBinding;
import chin.pswm.gps.photo.location.map.interfaces.OnClickGallery;
import com.bumptech.glide.Glide;
import com.fom.rapid.assistant.HeyMoon;
import java.util.List;


public class MyCreationAdapter extends RecyclerView.Adapter<MyCreationAdapter.MyViewHolder> {
    public List<Uri> arrayList;
    public Context context;
    public OnClickGallery onClickGallery;
    public int type;

    public MyCreationAdapter(Context context, List<Uri> list, OnClickGallery onClickGallery, int i) {
        this.context = context;
        this.arrayList = list;
        this.onClickGallery = onClickGallery;
        this.type = i;
    }

    @Override 
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(MyCreationLayoutBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
        Glide.with(this.context).load(this.arrayList.get(i).getPath()).into(myViewHolder.binding.image);
        myViewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                MyCreationAdapter.this.m154xbb64c57a(i, view);
            }
        });
    }

    
    public  void m154xbb64c57a(int i, View view) {
        this.onClickGallery.onClickItem(this.arrayList.get(i).getPath());
    }

    @Override 
    public int getItemCount() {
        if (this.type == 1) {
            if (this.arrayList.size() > 3) {
                return 3;
            }
            return this.arrayList.size();
        }
        return this.arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        
        public final MyCreationLayoutBinding binding;

        public MyViewHolder(MyCreationLayoutBinding myCreationLayoutBinding) {
            super(myCreationLayoutBinding.getRoot());
            this.binding = myCreationLayoutBinding;
            HeyMoon.resize().view(myCreationLayoutBinding.cv).measureWith(-1).now(MyCreationAdapter.this.context);
        }
    }


    public void updateAdapter(List<Uri> list) {
        this.arrayList = list;
        notifyDataSetChanged();
    }
}
