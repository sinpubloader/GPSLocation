package chin.pswm.gps.photo.location.map.photoGrid.frame;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.List;

import chin.pswm.gps.photo.location.map.photoGrid.template.PG_PhotoItem;
import chin.pswm.gps.photo.location.map.photoGrid.util.PG_ImageDecoder;
import chin.pswm.gps.photo.location.map.photoGrid.util.PG_ImageUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
@SuppressWarnings("all")
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 82\u00020\u00012\u00020\u0002:\u000289B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ(\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\u0016H\u0002J4\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001a2\u0006\u0010$\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u00162\b\b\u0002\u0010\u001f\u001a\u00020\u00162\b\b\u0002\u0010 \u001a\u00020\u0016H\u0007J\u0006\u0010%\u001a\u00020&J\u001a\u0010'\u001a\u0004\u0018\u00010\u000e2\u0006\u0010(\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020\"2\u0006\u0010,\u001a\u00020\u000eH\u0016J\u0010\u0010-\u001a\u00020\"2\u0006\u0010.\u001a\u00020\u000eH\u0016J\u0006\u0010/\u001a\u00020\"J\u000e\u00100\u001a\u00020\"2\u0006\u00101\u001a\u000202J\u000e\u00103\u001a\u00020\"2\u0006\u00104\u001a\u000202J\u000e\u00105\u001a\u00020\"2\u0006\u00106\u001a\u00020\u0018J\u0016\u00107\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\u0016R\u0014\u0010\t\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/frame/PG_FramePhotoLayout;", "Landroid/widget/RelativeLayout;", "Lchin/pswm/gps/photo/location/map/photoGrid/frame/PG_FrameImageView$OnImageClickListener;", "context", "Landroid/content/Context;", "mPGPhotoItems", "", "Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_PhotoItem;", "(Landroid/content/Context;Ljava/util/List;)V", "isNotLargeThan1Gb", "", "()Z", "mItemImageViews", "", "Lchin/pswm/gps/photo/location/map/photoGrid/frame/PG_FrameImageView;", "mOnDragListener", "Landroid/view/View$OnDragListener;", "getMOnDragListener$app_release", "()Landroid/view/View$OnDragListener;", "setMOnDragListener$app_release", "(Landroid/view/View$OnDragListener;)V", "mOutputScaleRatio", "", "mQuickActionClickListener", "Lchin/pswm/gps/photo/location/map/photoGrid/frame/PG_FramePhotoLayout$OnQuickActionClickListener;", "mViewHeight", "", "mViewWidth", "addPhotoItemView", "item", "outputScaleRatio", "space", "corner", "build", "", "viewWidth", "viewHeight", "createImage", "Landroid/graphics/Bitmap;", "getSelectedFrameImageView", "target", NotificationCompat.CATEGORY_EVENT, "Landroid/view/DragEvent;", "onDoubleClickImage", "view", "onLongClickImage", "v", "recycleImages", "restoreInstanceState", "savedInstanceState", "Landroid/os/Bundle;", "saveInstanceState", "outState", "setQuickActionClickListener", "quickActionClickListener", "setSpace", "Companion", "OnQuickActionClickListener", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_FramePhotoLayout extends RelativeLayout implements PG_FrameImageView.OnImageClickListener {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "PG_FramePhotoLayout";
    private final List<PG_FrameImageView> mItemImageViews;
    private View.OnDragListener mOnDragListener;
    private float mOutputScaleRatio;
    private final List<PG_PhotoItem> mPGPhotoItems;
    private OnQuickActionClickListener mQuickActionClickListener;
    private int mViewHeight;
    private int mViewWidth;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/frame/PG_FramePhotoLayout$OnQuickActionClickListener;", "", "onChangeActionClick", "", "v", "Lchin/pswm/gps/photo/location/map/photoGrid/frame/PG_FrameImageView;", "onEditActionClick", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public interface OnQuickActionClickListener {
        void onChangeActionClick(PG_FrameImageView pG_FrameImageView);

        void onEditActionClick(PG_FrameImageView pG_FrameImageView);
    }

    public final void build(int i, int i2, float f) {
        build$default(this, i, i2, f, 0.0f, 0.0f, 24, null);
    }

    public final void build(int i, int i2, float f, float f2) {
        build$default(this, i, i2, f, f2, 0.0f, 16, null);
    }

    @Override
    public void onDoubleClickImage(PG_FrameImageView view) {
        Intrinsics.checkNotNullParameter(view, "view");
    }


    public PG_FramePhotoLayout(Context context, List<PG_PhotoItem> mPGPhotoItems) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mPGPhotoItems, "mPGPhotoItems");
        this.mPGPhotoItems = mPGPhotoItems;
        this.mOnDragListener = new View.OnDragListener() {
            @Override
            public final boolean onDrag(View view, DragEvent dragEvent) {
                boolean mOnDragListener$lambda$0;
                mOnDragListener$lambda$0 = PG_FramePhotoLayout.mOnDragListener$lambda$0(PG_FramePhotoLayout.this, view, dragEvent);
                return mOnDragListener$lambda$0;
            }
        };
        this.mOutputScaleRatio = 1.0f;
        this.mItemImageViews = new ArrayList();
        setLayerType(2, null);
    }

    public final View.OnDragListener getMOnDragListener$app_release() {
        return this.mOnDragListener;
    }

    public final void setMOnDragListener$app_release(View.OnDragListener onDragListener) {
        Intrinsics.checkNotNullParameter(onDragListener, "<set-?>");
        this.mOnDragListener = onDragListener;
    }

    public static final boolean mOnDragListener$lambda$0(PG_FramePhotoLayout this$0, View view, DragEvent event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (event.getAction() != 3) {
            return true;
        }
        Intrinsics.checkNotNull(view, "null cannot be cast to non-null type chin.pswm.gps.photo.location.map.photoGrid.frame.PG_FrameImageView");
        Intrinsics.checkNotNullExpressionValue(event, "event");
        PG_FrameImageView selectedFrameImageView = this$0.getSelectedFrameImageView((PG_FrameImageView) view, event);
        if (selectedFrameImageView != null) {
            Object localState = event.getLocalState();
            Intrinsics.checkNotNull(localState, "null cannot be cast to non-null type chin.pswm.gps.photo.location.map.photoGrid.frame.PG_FrameImageView");
            PG_FrameImageView pG_FrameImageView = (PG_FrameImageView) localState;
            if (selectedFrameImageView.getPGPhotoItem() == null || pG_FrameImageView.getPGPhotoItem() == null) {
                return true;
            }
            String imagePath = selectedFrameImageView.getPGPhotoItem().getImagePath();
            String imagePath2 = pG_FrameImageView.getPGPhotoItem().getImagePath();
            if (imagePath == null) {
                imagePath = "";
            }
            if (imagePath2 == null) {
                imagePath2 = "";
            }
            if (Intrinsics.areEqual(imagePath, imagePath2)) {
                return true;
            }
            selectedFrameImageView.swapImage(pG_FrameImageView);
            return true;
        }
        return true;
    }

    private final boolean isNotLargeThan1Gb() {
        PG_ImageUtils pG_ImageUtils = PG_ImageUtils.INSTANCE;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        PG_ImageUtils.MemoryInfo memoryInfo = pG_ImageUtils.getMemoryInfo(context);
        return memoryInfo.getTotalMem() > 0 && ((double) memoryInfo.getTotalMem()) / 1048576.0d <= 1024.0d;
    }

    private final PG_FrameImageView getSelectedFrameImageView(PG_FrameImageView pG_FrameImageView, DragEvent dragEvent) {
        Object localState = dragEvent.getLocalState();
        Intrinsics.checkNotNull(localState, "null cannot be cast to non-null type chin.pswm.gps.photo.location.map.photoGrid.frame.PG_FrameImageView");
        PG_FrameImageView pG_FrameImageView2 = (PG_FrameImageView) localState;
        int i = (int) (this.mViewHeight * pG_FrameImageView.getPGPhotoItem().getBound().top);
        float x = ((int) (this.mViewWidth * pG_FrameImageView.getPGPhotoItem().getBound().left)) + dragEvent.getX();
        float y = i + dragEvent.getY();
        List<PG_FrameImageView> list = this.mItemImageViews;
        Intrinsics.checkNotNull(list);
        int size = list.size() - 1;
        if (size >= 0) {
            while (true) {
                int i2 = size - 1;
                PG_FrameImageView pG_FrameImageView3 = this.mItemImageViews.get(size);
                if (pG_FrameImageView3.isSelected(x - (this.mViewWidth * pG_FrameImageView3.getPGPhotoItem().getBound().left), y - (this.mViewHeight * pG_FrameImageView3.getPGPhotoItem().getBound().top))) {
                    if (pG_FrameImageView3 == pG_FrameImageView2) {
                        return null;
                    }
                    return pG_FrameImageView3;
                } else if (i2 < 0) {
                    break;
                } else {
                    size = i2;
                }
            }
        }
        return null;
    }

    public final void saveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        List<PG_FrameImageView> list = this.mItemImageViews;
        if (list != null) {
            for (PG_FrameImageView pG_FrameImageView : list) {
                pG_FrameImageView.saveInstanceState(outState);
            }
        }
    }

    public final void restoreInstanceState(Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(savedInstanceState, "savedInstanceState");
        List<PG_FrameImageView> list = this.mItemImageViews;
        if (list != null) {
            for (PG_FrameImageView pG_FrameImageView : list) {
                pG_FrameImageView.restoreInstanceState(savedInstanceState);
            }
        }
    }

    public final void setQuickActionClickListener(OnQuickActionClickListener quickActionClickListener) {
        Intrinsics.checkNotNullParameter(quickActionClickListener, "quickActionClickListener");
        this.mQuickActionClickListener = quickActionClickListener;
    }

    public static  void build$default(PG_FramePhotoLayout pG_FramePhotoLayout, int i, int i2, float f, float f2, float f3, int i3, Object obj) {
        pG_FramePhotoLayout.build(i, i2, f, (i3 & 8) != 0 ? 0.0f : f2, (i3 & 16) != 0 ? 0.0f : f3);
    }

    public final void build(int i, int i2, float f, float f2, float f3) {
        if (i < 1 || i2 < 1) {
            return;
        }
        this.mViewWidth = i;
        this.mViewHeight = i2;
        this.mOutputScaleRatio = f;
        List<PG_FrameImageView> list = this.mItemImageViews;
        Intrinsics.checkNotNull(list);
        list.clear();
        if (this.mPGPhotoItems.size() > 4 || isNotLargeThan1Gb()) {
            PG_ImageDecoder.INSTANCE.setSAMPLER_SIZE(256);
        } else {
            PG_ImageDecoder.INSTANCE.setSAMPLER_SIZE(512);
        }
        for (PG_PhotoItem pG_PhotoItem : this.mPGPhotoItems) {
            this.mItemImageViews.add(addPhotoItemView(pG_PhotoItem, this.mOutputScaleRatio, f2, f3));
        }
    }

    public final void setSpace(float f, float f2) {
        List<PG_FrameImageView> list = this.mItemImageViews;
        Intrinsics.checkNotNull(list);
        for (PG_FrameImageView pG_FrameImageView : list) {
            pG_FrameImageView.setSpace(f, f2);
        }
    }

    private final PG_FrameImageView addPhotoItemView(PG_PhotoItem pG_PhotoItem, float f, float f2, float f3) {
        int width;
        int height;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        PG_FrameImageView pG_FrameImageView = new PG_FrameImageView(context, pG_PhotoItem);
        int i = (int) (this.mViewWidth * pG_PhotoItem.getBound().left);
        int i2 = (int) (this.mViewHeight * pG_PhotoItem.getBound().top);
        if (pG_PhotoItem.getBound().right == 1.0f) {
            width = this.mViewWidth - i;
        } else {
            width = (int) ((this.mViewWidth * pG_PhotoItem.getBound().width()) + 0.5f);
        }
        int i3 = width;
        if (pG_PhotoItem.getBound().bottom == 1.0f) {
            height = this.mViewHeight - i2;
        } else {
            height = (int) ((this.mViewHeight * pG_PhotoItem.getBound().height()) + 0.5f);
        }
        pG_FrameImageView.init(i3, height, f, f2, f3);
        pG_FrameImageView.setOnImageClickListener(this);
        if (this.mPGPhotoItems.size() > 1) {
            pG_FrameImageView.setOnDragListener(this.mOnDragListener);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i3, height);
        layoutParams.leftMargin = i;
        layoutParams.topMargin = i2;
        pG_FrameImageView.setOriginalLayoutParams(layoutParams);
        addView(pG_FrameImageView, layoutParams);
        return pG_FrameImageView;
    }

    public final Bitmap createImage() throws OutOfMemoryError {
        try {
            float f = this.mOutputScaleRatio;
            Bitmap template = Bitmap.createBitmap((int) (this.mViewWidth * f), (int) (f * this.mViewHeight), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(template);
            List<PG_FrameImageView> list = this.mItemImageViews;
            Intrinsics.checkNotNull(list);
            for (PG_FrameImageView pG_FrameImageView : list) {
                if (pG_FrameImageView.getImage() != null) {
                    Bitmap image = pG_FrameImageView.getImage();
                    Intrinsics.checkNotNull(image);
                    if (!image.isRecycled()) {
                        int left = (int) (pG_FrameImageView.getLeft() * this.mOutputScaleRatio);
                        int top = (int) (pG_FrameImageView.getTop() * this.mOutputScaleRatio);
                        int width = (int) (pG_FrameImageView.getWidth() * this.mOutputScaleRatio);
                        int height = (int) (pG_FrameImageView.getHeight() * this.mOutputScaleRatio);
                        float f2 = left;
                        float f3 = top;
                        canvas.saveLayer(f2, f3, left + width, top + height, new Paint(), 31);
                        canvas.translate(f2, f3);
                        canvas.clipRect(0, 0, width, height);
                        pG_FrameImageView.drawOutputImage(canvas);
                        canvas.restore();
                    }
                }
            }
            Intrinsics.checkNotNullExpressionValue(template, "template");
            return template;
        } catch (OutOfMemoryError e) {
            throw e;
        }
    }

    public final void recycleImages() {
        List<PG_FrameImageView> list = this.mItemImageViews;
        Intrinsics.checkNotNull(list);
        for (PG_FrameImageView pG_FrameImageView : list) {
            pG_FrameImageView.recycleImage();
        }
        System.gc();
    }

    @Override
    public void onLongClickImage(PG_FrameImageView v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (this.mPGPhotoItems.size() > 1) {
            v.setTag("x=" + v.getPGPhotoItem().getX() + ",y=" + v.getPGPhotoItem().getY() + ",path=" + v.getPGPhotoItem().getImagePath());
            Object tag = v.getTag();
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type kotlin.CharSequence");
            ClipData.Item item = new ClipData.Item((CharSequence) tag);
            v.startDrag(new ClipData(v.getTag().toString(), new String[]{"text/plain"}, item), new View.DragShadowBuilder(v), v, 0);
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/frame/PG_FramePhotoLayout$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
