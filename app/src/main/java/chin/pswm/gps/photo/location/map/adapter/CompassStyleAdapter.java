package chin.pswm.gps.photo.location.map.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import chin.pswm.gps.photo.location.map.model.CompassStyle;
import chin.pswm.gps.photo.location.map_debug.R;

public class CompassStyleAdapter extends RecyclerView.Adapter<CompassStyleAdapter.ViewHolder> {

    private final List<CompassStyle> compassStyles;
    private int selectedPosition = -1;
    private final Context context;
    private final OnStyleSelectListener listener;

    public interface OnStyleSelectListener {
        void onStyleSelected(CompassStyle style);
    }

    public CompassStyleAdapter(Context context, List<CompassStyle> compassStyles, OnStyleSelectListener listener) {
        this.context = context;
        this.compassStyles = compassStyles;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_compass_style, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CompassStyle style = compassStyles.get(position);

        holder.compassImage.setImageResource(style.getStyleImage());
        holder.checkedImage.setVisibility(selectedPosition == position ? View.VISIBLE : View.GONE);

        holder.rootLayout.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();
            listener.onStyleSelected(style);
        });
    }

    @Override
    public int getItemCount() {
        return compassStyles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView compassImage, checkedImage;
        RelativeLayout rootLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            compassImage = itemView.findViewById(R.id.iv_compass);
            checkedImage = itemView.findViewById(R.id.iv_checked);
            rootLayout = itemView.findViewById(R.id.ll_root);
        }
    }
}
