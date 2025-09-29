package chin.pswm.gps.photo.location.map.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fom.rapid.assistant.HeyMoon;

import java.util.ArrayList;

import chin.pswm.gps.photo.location.map.interfaces.OnClickGallery;
import chin.pswm.gps.photo.location.map.model.GalleryModel;
import chin.pswm.gps.photo.location.map_debug.databinding.FolderLayoutBinding;


public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.MyViewHolder> {
    public ArrayList<GalleryModel> arrayList;
    public Context context;
    public OnClickGallery onClickGallery;

    public FolderAdapter(Context context, ArrayList<GalleryModel> arrayList, OnClickGallery onClickGallery) {
        this.context = context;
        this.arrayList = arrayList;
        this.onClickGallery = onClickGallery;
    }

    @Override 
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(FolderLayoutBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(MyViewHolder myViewHolder, final int i) {
        Glide.with(this.context).load(this.arrayList.get(myViewHolder.getAdapterPosition()).getItemList().get(0)).into(myViewHolder.binding.image);
        myViewHolder.binding.name.setText(this.arrayList.get(i).getFolderName());
        myViewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() { 
            @Override 
            public final void onClick(View view) {
                FolderAdapter.this.m152xa2008757(i, view);
            }
        });
    }


    public  void m152xa2008757(int i, View view) {
        this.onClickGallery.onClickFolder(i);
    }

    @Override 
    public int getItemCount() {
        return this.arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        
        public final FolderLayoutBinding binding;

        public MyViewHolder(FolderLayoutBinding folderLayoutBinding) {
            super(folderLayoutBinding.getRoot());
            this.binding = folderLayoutBinding;
            HeyMoon.resize().view(folderLayoutBinding.mainRl).measureWith(-1).now(FolderAdapter.this.context);
            HeyMoon.resize().view(folderLayoutBinding.cv).measureWith(-1).now(FolderAdapter.this.context);
        }
    }

    public void updateAdapter(ArrayList<GalleryModel> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }
}
