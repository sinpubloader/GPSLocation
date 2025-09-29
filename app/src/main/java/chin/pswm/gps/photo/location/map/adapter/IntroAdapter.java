package chin.pswm.gps.photo.location.map.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import chin.pswm.gps.photo.location.map.model.IntroModel;
import chin.pswm.gps.photo.location.map.utils.Resizer;
import chin.pswm.gps.photo.location.map_debug.databinding.IntroRowBinding;


public class IntroAdapter extends RecyclerView.Adapter<IntroAdapter.IntroViewHolder> {
    private final List<IntroModel> infoList;

    public IntroAdapter(List<IntroModel> list) {
        this.infoList = list;
    }

    @Override 
    public IntroViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new IntroViewHolder(IntroRowBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false));
    }

    @Override 
    public void onBindViewHolder(IntroViewHolder introViewHolder, int i) {
        introViewHolder.binding.infoImg.setImageResource(this.infoList.get(introViewHolder.getAdapterPosition()).getInfoSrc());
        introViewHolder.binding.infoTxtHeader.setText(this.infoList.get(introViewHolder.getAdapterPosition()).getInfoHeader());
        introViewHolder.binding.infoTxtDesc.setText(this.infoList.get(introViewHolder.getAdapterPosition()).getInfoTxt());
    }

    @Override 
    public int getItemCount() {
        return this.infoList.size();
    }

    
    public static class IntroViewHolder extends RecyclerView.ViewHolder {
        IntroRowBinding binding;

        public IntroViewHolder(IntroRowBinding introRowBinding) {
            super(introRowBinding.getRoot());
            this.binding = introRowBinding;
            Resizer.setSize(introRowBinding.infoImg, 1080, 900, true);
            Resizer.setMargins(introRowBinding.infoImg, 0, 150, 0, 0);
        }
    }
}
