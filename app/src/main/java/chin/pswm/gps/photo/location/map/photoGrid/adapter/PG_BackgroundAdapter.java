package chin.pswm.gps.photo.location.map.photoGrid.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import chin.pswm.gps.photo.location.map_debug.R;

import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
@SuppressWarnings("all")
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002)*B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010 \u001a\u00020\u001bH\u0016J\u0018\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u001bH\u0016J\u0018\u0010%\u001a\u00020\u00022\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u001bH\u0016R\u001a\u0010\b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006+"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_BackgroundAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_BackgroundAdapter$BackgroundHolder;", "context", "Landroid/content/Context;", "bgClickListener", "Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_BackgroundAdapter$OnBGClickListener;", "(Landroid/content/Context;Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_BackgroundAdapter$OnBGClickListener;)V", "bgListener", "getBgListener", "()Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_BackgroundAdapter$OnBGClickListener;", "setBgListener", "(Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_BackgroundAdapter$OnBGClickListener;)V", "mContext", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "mImages", "", "", "getMImages", "()[Ljava/lang/String;", "setMImages", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "selectedindex", "", "getSelectedindex", "()I", "setSelectedindex", "(I)V", "getItemCount", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "BackgroundHolder", "OnBGClickListener", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_BackgroundAdapter extends RecyclerView.Adapter<PG_BackgroundAdapter.BackgroundHolder> {
    private OnBGClickListener bgListener;
    private Context mContext;
    private String[] mImages;
    private int selectedindex;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_BackgroundAdapter$OnBGClickListener;", "", "onBGClick", "", "drawable", "Landroid/graphics/drawable/Drawable;", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public interface OnBGClickListener {
        void onBGClick(Drawable drawable);
    }

    public PG_BackgroundAdapter(Context context, OnBGClickListener bgClickListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bgClickListener, "bgClickListener");
        this.mContext = context;
        this.bgListener = bgClickListener;
        try {
            this.mImages = context.getAssets().list("background");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final String[] getMImages() {
        return this.mImages;
    }

    public final void setMImages(String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "<set-?>");
        this.mImages = strArr;
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final void setMContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.mContext = context;
    }

    public final OnBGClickListener getBgListener() {
        return this.bgListener;
    }

    public final void setBgListener(OnBGClickListener onBGClickListener) {
        Intrinsics.checkNotNullParameter(onBGClickListener, "<set-?>");
        this.bgListener = onBGClickListener;
    }

    public final int getSelectedindex() {
        return this.selectedindex;
    }

    public final void setSelectedindex(int i) {
        this.selectedindex = i;
    }

    @Override 
    public BackgroundHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_frame, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new BackgroundHolder(view);
    }

    @Override 
    public int getItemCount() {
        return this.mImages.length;
    }

    @Override
    public void onBindViewHolder(BackgroundHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        InputStream open = null;
        try {
            open = this.mContext.getAssets().open("background/" + this.mImages[i]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Intrinsics.checkNotNullExpressionValue(open, "mContext.assets.open(\"ba…nd/\" + mImages[position])");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = Drawable.createFromStream(open, null);
        holder.getImg_frame().setImageDrawable((Drawable) objectRef.element);
        if (this.selectedindex == i) {
            holder.getLl_itemframe().setBackgroundColor(this.mContext.getResources().getColor(R.color.colorAccent));
        } else {
            holder.getLl_itemframe().setBackgroundColor(this.mContext.getResources().getColor(R.color.transparent));
        }
        holder.getImg_frame().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PG_BackgroundAdapter.this.setSelectedindex(i);
                if (objectRef.element != null) {
                    PG_BackgroundAdapter.this.getBgListener().onBGClick((Drawable) objectRef.element);
                }
                PG_BackgroundAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_BackgroundAdapter$BackgroundHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "img_frame", "Landroid/widget/ImageView;", "getImg_frame", "()Landroid/widget/ImageView;", "setImg_frame", "(Landroid/widget/ImageView;)V", "ll_itemframe", "Landroid/widget/LinearLayout;", "getLl_itemframe", "()Landroid/widget/LinearLayout;", "setLl_itemframe", "(Landroid/widget/LinearLayout;)V", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public static final class BackgroundHolder extends RecyclerView.ViewHolder {
        private ImageView img_frame;
        private LinearLayout ll_itemframe;


        public BackgroundHolder(View itemView) {
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
