package chin.pswm.gps.photo.location.map.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import chin.pswm.gps.photo.location.map_debug.databinding.StartAdapterLayoutBinding;
import chin.pswm.gps.photo.location.map.interfaces.OnClickGallery;
import chin.pswm.gps.photo.location.map.utils.Resizer;
import com.bumptech.glide.Glide;
import java.util.List;


public class StartAdapter extends RecyclerView.Adapter<StartAdapter.MyViewHolder> {
    public List<Uri> arrayList;
    public Context context;
    public OnClickGallery onClickGallery;

    public StartAdapter(Context context, List<Uri> list, OnClickGallery onClickGallery) {
        this.context = context;
        this.arrayList = list;
        this.onClickGallery = onClickGallery;
    }

    @Override 
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(StartAdapterLayoutBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
        Glide.with(this.context).load(this.arrayList.get(i).getPath()).into(myViewHolder.binding.image);
        myViewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                StartAdapter.this.m155x7ada2177(i, view);
            }
        });
    }

    
    public  void m155x7ada2177(int i, View view) {
        this.onClickGallery.onClickItem(this.arrayList.get(i).getPath());
    }

    @Override 
    public int getItemCount() {
        if (this.arrayList.size() > 3) {
            return 3;
        }
        return this.arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        
        public final StartAdapterLayoutBinding binding;

        public MyViewHolder(StartAdapterLayoutBinding startAdapterLayoutBinding) {
            super(startAdapterLayoutBinding.getRoot());
            this.binding = startAdapterLayoutBinding;
            Resizer.getheightandwidth(StartAdapter.this.context);
            Resizer.setSize(startAdapterLayoutBinding.mainRl, 285, 292, true);
            Resizer.setMargins(startAdapterLayoutBinding.mainRl, 10, 10, 10, 10);
        }
    }
}
