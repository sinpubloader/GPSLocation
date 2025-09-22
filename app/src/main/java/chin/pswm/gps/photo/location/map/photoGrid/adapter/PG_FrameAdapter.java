package chin.pswm.gps.photo.location.map.photoGrid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map.photoGrid.adapter.PG_FrameAdapter;
import chin.pswm.gps.photo.location.map.photoGrid.model.PG_TemplatePGItem;
import chin.pswm.gps.photo.location.map.photoGrid.util.PG_PhotoUtils;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
@SuppressWarnings("all")
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002)*BE\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b\u0012\u0016\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010\u001f\u001a\u00020 H\u0016J\u0018\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020 H\u0016J\u0018\u0010%\u001a\u00020\u00022\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020 H\u0016R\u001a\u0010\r\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R*\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR*\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001b¨\u0006+"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_FrameAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_FrameAdapter$FrameHolder;", "context", "Landroid/content/Context;", "imageList", "Ljava/util/ArrayList;", "Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_TemplatePGItem;", "Lkotlin/collections/ArrayList;", "imageListSelect", "frameClickListener", "Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_FrameAdapter$OnFrameClickListener;", "(Landroid/content/Context;Ljava/util/ArrayList;Ljava/util/ArrayList;Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_FrameAdapter$OnFrameClickListener;)V", "frameListener", "getFrameListener", "()Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_FrameAdapter$OnFrameClickListener;", "setFrameListener", "(Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_FrameAdapter$OnFrameClickListener;)V", "mContext", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "mImages", "getMImages", "()Ljava/util/ArrayList;", "setMImages", "(Ljava/util/ArrayList;)V", "mImagesSelect", "getMImagesSelect", "setMImagesSelect", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "FrameHolder", "OnFrameClickListener", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_FrameAdapter extends RecyclerView.Adapter<PG_FrameAdapter.FrameHolder> {
    private OnFrameClickListener frameListener;
    private Context mContext;
    private ArrayList<PG_TemplatePGItem> mImages;
    private ArrayList<PG_TemplatePGItem> mImagesSelect;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_FrameAdapter$OnFrameClickListener;", "", "onFrameClick", "", "templateItem", "Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_TemplatePGItem;", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public interface OnFrameClickListener {
        void onFrameClick(PG_TemplatePGItem pG_TemplatePGItem);
    }

    public PG_FrameAdapter(Context context, ArrayList<PG_TemplatePGItem> imageList, ArrayList<PG_TemplatePGItem> imageListSelect, OnFrameClickListener frameClickListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imageList, "imageList");
        Intrinsics.checkNotNullParameter(imageListSelect, "imageListSelect");
        Intrinsics.checkNotNullParameter(frameClickListener, "frameClickListener");
        this.mContext = context;
        this.mImages = imageList;
        this.mImagesSelect = imageListSelect;
        this.frameListener = frameClickListener;
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final void setMContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.mContext = context;
    }

    public final ArrayList<PG_TemplatePGItem> getMImages() {
        return this.mImages;
    }

    public final void setMImages(ArrayList<PG_TemplatePGItem> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.mImages = arrayList;
    }

    public final ArrayList<PG_TemplatePGItem> getMImagesSelect() {
        return this.mImagesSelect;
    }

    public final void setMImagesSelect(ArrayList<PG_TemplatePGItem> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.mImagesSelect = arrayList;
    }

    public final OnFrameClickListener getFrameListener() {
        return this.frameListener;
    }

    public final void setFrameListener(OnFrameClickListener onFrameClickListener) {
        Intrinsics.checkNotNullParameter(onFrameClickListener, "<set-?>");
        this.frameListener = onFrameClickListener;
    }

    @Override 
    public FrameHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_frame, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new FrameHolder(view);
    }

    @Override 
    public int getItemCount() {
        return this.mImages.size();
    }

    @Override 
    public void onBindViewHolder(FrameHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (this.mImages.get(i).isSelected()) {
            PG_PhotoUtils pG_PhotoUtils = PG_PhotoUtils.INSTANCE;
            Context context = this.mContext;
            ImageView img_frame = holder.getImg_frame();
            String preview = this.mImagesSelect.get(i).getPreview();
            pG_PhotoUtils.loadImageWithGlide(context, img_frame, preview != null ? StringsKt.replace(preview, "//frame/", "//frame_select/", false) : null);
        } else {
            PG_PhotoUtils.INSTANCE.loadImageWithGlide(this.mContext, holder.getImg_frame(), this.mImages.get(i).getPreview());
        }
        holder.getImg_frame().setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                PG_FrameAdapter.OnFrameClickListener frameListener = PG_FrameAdapter.this.getFrameListener();
                PG_TemplatePGItem pG_TemplatePGItem = PG_FrameAdapter.this.getMImages().get(i);
                Intrinsics.checkNotNullExpressionValue(pG_TemplatePGItem, "mImages[position]");
                frameListener.onFrameClick(pG_TemplatePGItem);
            }
        });
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_FrameAdapter$FrameHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "img_frame", "Landroid/widget/ImageView;", "getImg_frame", "()Landroid/widget/ImageView;", "setImg_frame", "(Landroid/widget/ImageView;)V", "ll_itemframe", "Landroid/widget/LinearLayout;", "getLl_itemframe", "()Landroid/widget/LinearLayout;", "setLl_itemframe", "(Landroid/widget/LinearLayout;)V", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public static final class FrameHolder extends RecyclerView.ViewHolder {
        private ImageView img_frame;
        private LinearLayout ll_itemframe;


        public FrameHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.img_frame);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.img_frame)");
            this.img_frame = (ImageView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.ll_itemframe);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.ll_itemframe)");
            this.ll_itemframe = (LinearLayout) findViewById2;
        }

        public final ImageView getImg_frame() {
            return this.img_frame;
        }

        public final void setImg_frame(ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.img_frame = imageView;
        }

        public final LinearLayout getLl_itemframe() {
            return this.ll_itemframe;
        }

        public final void setLl_itemframe(LinearLayout linearLayout) {
            Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
            this.ll_itemframe = linearLayout;
        }
    }
}
