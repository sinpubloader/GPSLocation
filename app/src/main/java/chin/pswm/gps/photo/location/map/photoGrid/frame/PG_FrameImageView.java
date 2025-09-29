package chin.pswm.gps.photo.location.map.photoGrid.frame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import chin.pswm.gps.photo.location.map.photoGrid.multitouch.PG_MultiTouchHandler;
import chin.pswm.gps.photo.location.map.photoGrid.template.PG_PhotoItem;
import chin.pswm.gps.photo.location.map.photoGrid.util.PG_GeometryUtils;
import chin.pswm.gps.photo.location.map.photoGrid.util.PG_ImageDecoder;
import chin.pswm.gps.photo.location.map.photoGrid.util.PG_ImageUtils;
import chin.pswm.gps.photo.location.map.photoGrid.util.PG_ResultContainer;
import chin.pswm.gps.photo.location.map_debug.R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
@SuppressWarnings("all")
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u0000 c2\u00020\u0001:\u0002cdB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010@\u001a\u00020AJ\u0010\u0010B\u001a\u00020A2\u0006\u0010C\u001a\u00020DH\u0002J\u000e\u0010E\u001a\u00020A2\u0006\u0010C\u001a\u00020DJ\b\u0010F\u001a\u00020%H\u0016J4\u0010G\u001a\u00020A2\u0006\u0010>\u001a\u00020\u000e2\u0006\u0010<\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020\u000e2\b\b\u0002\u0010:\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u0007J\u0016\u0010I\u001a\u00020!2\u0006\u0010J\u001a\u00020\u000e2\u0006\u0010K\u001a\u00020\u000eJ\u0010\u0010L\u001a\u00020A2\u0006\u0010C\u001a\u00020DH\u0014J\u0010\u0010M\u001a\u00020!2\u0006\u0010N\u001a\u00020OH\u0016J\u0006\u0010P\u001a\u00020AJ\u0006\u0010Q\u001a\u00020AJ\u000e\u0010R\u001a\u00020A2\u0006\u0010S\u001a\u00020TJ\u000e\u0010U\u001a\u00020A2\u0006\u0010V\u001a\u00020TJ\u0010\u0010W\u001a\u00020A2\u0006\u0010X\u001a\u00020\u0019H\u0016J\u000e\u0010Y\u001a\u00020A2\u0006\u0010Z\u001a\u00020!J\u000e\u0010[\u001a\u00020A2\u0006\u0010\\\u001a\u00020]J\u000e\u0010^\u001a\u00020A2\u0006\u0010_\u001a\u00020'J\u0016\u0010`\u001a\u00020A2\u0006\u0010:\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eJ\u000e\u0010a\u001a\u00020A2\u0006\u0010b\u001a\u00020\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\t\u001a\u0004\u0018\u00010\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\n0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00100\u001a\b\u0012\u0004\u0012\u00020\n0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e¢\u0006\u0002\n\u0000R$\u00105\u001a\u00020)2\u0006\u00105\u001a\u00020)8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001e\u0010:\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u0011R\u001e\u0010<\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u0011R\u001e\u0010>\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\u0011¨\u0006e"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/frame/PG_FrameImageView;", "Landroid/widget/ImageView;", "context", "Landroid/content/Context;", "PGPhotoItem", "Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_PhotoItem;", "(Landroid/content/Context;Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_PhotoItem;)V", "getPGPhotoItem", "()Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_PhotoItem;", "centerPolygon", "Landroid/graphics/PointF;", "getCenterPolygon", "()Landroid/graphics/PointF;", "<set-?>", "", "corner", "getCorner", "()F", "image", "Landroid/graphics/Bitmap;", "getImage", "()Landroid/graphics/Bitmap;", "setImage", "(Landroid/graphics/Bitmap;)V", "mBackgroundColor", "", "mBackgroundPath", "Landroid/graphics/Path;", "mClearPath", "mConvertedClearPoints", "Ljava/util/ArrayList;", "mConvertedPoints", "mEnableTouch", "", "mGestureDetector", "Landroid/view/GestureDetector;", "mImageMatrix", "Landroid/graphics/Matrix;", "mOnImageClickListener", "Lchin/pswm/gps/photo/location/map/photoGrid/frame/PG_FrameImageView$OnImageClickListener;", "mOriginalLayoutParams", "Landroid/widget/RelativeLayout$LayoutParams;", "mOutputScale", "mPaint", "Landroid/graphics/Paint;", "mPath", "mPathRect", "Landroid/graphics/Rect;", "mPolygon", "mScaleMatrix", "mSelected", "mTouchHandler", "Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchHandler;", "originalLayoutParams", "getOriginalLayoutParams", "()Landroid/widget/RelativeLayout$LayoutParams;", "setOriginalLayoutParams", "(Landroid/widget/RelativeLayout$LayoutParams;)V", "space", "getSpace", "viewHeight", "getViewHeight", "viewWidth", "getViewWidth", "clearMainImage", "", "drawCenterLine", "canvas", "Landroid/graphics/Canvas;", "drawOutputImage", "getImageMatrix", "init", "scale", "isSelected", "x", "y", "onDraw", "onTouchEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "recycleImage", "resetImageMatrix", "restoreInstanceState", "savedInstanceState", "Landroid/os/Bundle;", "saveInstanceState", "outState", "setBackgroundColor", "backgroundColor", "setEnableTouch", "enableTouch", "setImagePath", "imagePath", "", "setOnImageClickListener", "onImageClickListener", "setSpace", "swapImage", "view", "Companion", "OnImageClickListener", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_FrameImageView extends ImageView {
    public   Companion Companion = new Companion(null);
    private static final String TAG = "PG_FrameImageView";
    private final PG_PhotoItem PGPhotoItem;
    private float corner;
    private Bitmap image;
    private int mBackgroundColor;
    private final Path mBackgroundPath;
    private final Path mClearPath;
    private final ArrayList<PointF> mConvertedClearPoints;
    private final ArrayList<PointF> mConvertedPoints;
    private boolean mEnableTouch;
    private  GestureDetector mGestureDetector;
    private  Matrix mImageMatrix;
    private OnImageClickListener mOnImageClickListener;
    private RelativeLayout.LayoutParams mOriginalLayoutParams;
    private float mOutputScale;
    private  Paint mPaint;
    private final Path mPath;
    private final Rect mPathRect;
    private final ArrayList<PointF> mPolygon;
    private  Matrix mScaleMatrix;
    private boolean mSelected;
    private PG_MultiTouchHandler mTouchHandler;
    private float space;
    private float viewHeight;
    private float viewWidth;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/frame/PG_FrameImageView$OnImageClickListener;", "", "onDoubleClickImage", "", "view", "Lchin/pswm/gps/photo/location/map/photoGrid/frame/PG_FrameImageView;", "onLongClickImage", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public interface OnImageClickListener {
        void onDoubleClickImage(PG_FrameImageView pG_FrameImageView);

        void onLongClickImage(PG_FrameImageView pG_FrameImageView);
    }

    public final void init(float f, float f2, float f3) {
        init$default(this, f, f2, f3, 0.0f, 0.0f, 24, null);
    }

    public final void init(float f, float f2, float f3, float f4) {
        init$default(this, f, f2, f3, f4, 0.0f, 16, null);
    }

    public PG_FrameImageView(Context arg4, PG_PhotoItem arg5) {
        super(arg4);
        Intrinsics.checkNotNullParameter(arg4, "context");
        Intrinsics.checkNotNullParameter(arg5, "PGPhotoItem");

        this.PGPhotoItem = arg5;
        this.mOutputScale = 1.0f;
        this.mEnableTouch = true;
        this.mPath = new Path();
        this.mBackgroundPath = new Path();
        this.mPolygon = new ArrayList();
        this.mPathRect = new Rect(0, 0, 0, 0);
        this.mSelected = true;
        this.mConvertedPoints = new ArrayList();
        this.mBackgroundColor = -1;
        this.mClearPath = new Path();
        this.mConvertedClearPoints = new ArrayList();

        if (arg5.getImagePath() != null) {
            String imagePath = arg5.getImagePath();
            Intrinsics.checkNotNull(imagePath);

            if (!imagePath.isEmpty()) {
                PG_ResultContainer resultContainer = PG_ResultContainer.Companion.getInstance();
                Bitmap cachedImage = resultContainer.getImage(imagePath);

                if (cachedImage != null) {
                    this.image = cachedImage;
                } else {
                    try {
                        
                        this.image = PG_ImageDecoder.INSTANCE.decodeFileToBitmap(imagePath);

                        
                        if (this.image != null) {
                            resultContainer.putImage(imagePath, this.image);
                        } else {
                            Log.e("PG_FrameImageView", "Failed to decode image from path: " + imagePath);
                        }
                    } catch (OutOfMemoryError e) {
                        Log.e("PG_FrameImageView", "Out of Memory Error while decoding image", e);

                        if (arg4 instanceof Activity) {
                            ((Activity) arg4).runOnUiThread(() -> PG_FrameImageView._init_$lambda$0(this, arg4));
                        }
                    }
                }

                
                if (this.image != null && !this.image.isRecycled()) {
                    Paint paint = new Paint();
                    this.mPaint = paint;
                    paint.setFilterBitmap(true);
                    paint.setAntiAlias(true);

                    this.setScaleType(ImageView.ScaleType.MATRIX);
                    this.setLayerType(LAYER_TYPE_SOFTWARE, paint);

                    this.mImageMatrix = new Matrix();
                    this.mScaleMatrix = new Matrix();
                    this.mGestureDetector = new GestureDetector(this.getContext(),
                            new GestureDetector.SimpleOnGestureListener() {
                                @Override
                                public boolean onDoubleTap(MotionEvent e) {
                                    Intrinsics.checkNotNullParameter(e, "e");
                                    if (PG_FrameImageView.this.mOnImageClickListener != null) {
                                        PG_FrameImageView.this.mOnImageClickListener.onDoubleClickImage(PG_FrameImageView.this);
                                    }
                                    return true;
                                }

                                @Override
                                public void onLongPress(MotionEvent e) {
                                    Intrinsics.checkNotNullParameter(e, "e");
                                    if (PG_FrameImageView.this.mOnImageClickListener != null) {
                                        PG_FrameImageView.this.mOnImageClickListener.onLongClickImage(PG_FrameImageView.this);
                                    }
                                }
                            });
                } else {
                    Log.e("PG_FrameImageView", "Image is null or recycled after decoding");
                }
            } else {
                Log.e("PG_FrameImageView", "Image path is empty");
            }
        } else {
            Log.e("PG_FrameImageView", "PG_PhotoItem.getImagePath() returned null");
        }
    }


    public final PG_PhotoItem getPGPhotoItem() {
        return this.PGPhotoItem;
    }

    public final Bitmap getImage() {
        return this.image;
    }

    public final void setImage(Bitmap bitmap) {
        this.image = bitmap;
    }

    public final float getViewWidth() {
        return this.viewWidth;
    }

    public final float getViewHeight() {
        return this.viewHeight;
    }

    public final float getCorner() {
        return this.corner;
    }

    public final float getSpace() {
        return this.space;
    }

    public final RelativeLayout.LayoutParams getOriginalLayoutParams() {
        if (this.mOriginalLayoutParams != null) {
            RelativeLayout.LayoutParams layoutParams = this.mOriginalLayoutParams;
            Intrinsics.checkNotNull(layoutParams);
            int i = layoutParams.width;
            RelativeLayout.LayoutParams layoutParams2 = this.mOriginalLayoutParams;
            Intrinsics.checkNotNull(layoutParams2);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(i, layoutParams2.height);
            RelativeLayout.LayoutParams layoutParams4 = this.mOriginalLayoutParams;
            Intrinsics.checkNotNull(layoutParams4);
            layoutParams3.leftMargin = layoutParams4.leftMargin;
            RelativeLayout.LayoutParams layoutParams5 = this.mOriginalLayoutParams;
            Intrinsics.checkNotNull(layoutParams5);
            layoutParams3.topMargin = layoutParams5.topMargin;
            return layoutParams3;
        }
        ViewGroup.LayoutParams layoutParams6 = getLayoutParams();
        Intrinsics.checkNotNull(layoutParams6, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        return (RelativeLayout.LayoutParams) layoutParams6;
    }

    public final void setOriginalLayoutParams(RelativeLayout.LayoutParams originalLayoutParams) {
        Intrinsics.checkNotNullParameter(originalLayoutParams, "originalLayoutParams");
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(originalLayoutParams.width, originalLayoutParams.height);
        this.mOriginalLayoutParams = layoutParams;
        Intrinsics.checkNotNull(layoutParams);
        layoutParams.leftMargin = originalLayoutParams.leftMargin;
        RelativeLayout.LayoutParams layoutParams2 = this.mOriginalLayoutParams;
        Intrinsics.checkNotNull(layoutParams2);
        layoutParams2.topMargin = originalLayoutParams.topMargin;
    }

    public final PointF getCenterPolygon() {
        ArrayList<PointF> arrayList = this.mPolygon;
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        PointF pointF = new PointF();
        Iterator<PointF> it = this.mPolygon.iterator();
        while (it.hasNext()) {
            PointF next = it.next();
            pointF.x += next.x;
            pointF.y += next.y;
        }
        pointF.x /= this.mPolygon.size();
        pointF.y /= this.mPolygon.size();
        return pointF;
    }

    public static final void _init_$lambda$0(PG_FrameImageView this$0, Context context) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        try {
            Toast.makeText(this$0.getContext().getApplicationContext(), context.getString(R.string.waring_out_of_memory), 0).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void saveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        int index = this.PGPhotoItem.getIndex();
        float[] fArr = new float[9];
        this.mImageMatrix.getValues(fArr);
        outState.putFloatArray("mImageMatrix_" + index, fArr);
        float[] fArr2 = new float[9];
        this.mScaleMatrix.getValues(fArr2);
        outState.putFloatArray("mScaleMatrix_" + index, fArr2);
        outState.putFloat("mViewWidth_" + index, this.viewWidth);
        outState.putFloat("mViewHeight_" + index, this.viewHeight);
        outState.putFloat("mOutputScale_" + index, this.mOutputScale);
        outState.putFloat("mCorner_" + index, this.corner);
        outState.putFloat("mSpace_" + index, this.space);
        outState.putInt("mBackgroundColor_" + index, this.mBackgroundColor);
    }

    public final void restoreInstanceState(Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(savedInstanceState, "savedInstanceState");
        int index = this.PGPhotoItem.getIndex();
        float[] floatArray = savedInstanceState.getFloatArray("mImageMatrix_" + index);
        if (floatArray != null) {
            this.mImageMatrix.setValues(floatArray);
        }
        float[] floatArray2 = savedInstanceState.getFloatArray("mScaleMatrix_" + index);
        if (floatArray2 != null) {
            this.mScaleMatrix.setValues(floatArray2);
        }
        this.viewWidth = savedInstanceState.getFloat("mViewWidth_" + index, 1.0f);
        this.viewHeight = savedInstanceState.getFloat("mViewHeight_" + index, 1.0f);
        this.mOutputScale = savedInstanceState.getFloat("mOutputScale_" + index, 1.0f);
        this.corner = savedInstanceState.getFloat("mCorner_" + index, 0.0f);
        this.space = savedInstanceState.getFloat("mSpace_" + index, 0.0f);
        this.mBackgroundColor = savedInstanceState.getInt("mBackgroundColor_" + index, -1);
        PG_MultiTouchHandler pG_MultiTouchHandler = this.mTouchHandler;
        Intrinsics.checkNotNull(pG_MultiTouchHandler);
        pG_MultiTouchHandler.setMatrices(this.mImageMatrix, this.mScaleMatrix);
        PG_MultiTouchHandler pG_MultiTouchHandler2 = this.mTouchHandler;
        Intrinsics.checkNotNull(pG_MultiTouchHandler2);
        pG_MultiTouchHandler2.setScale(this.mOutputScale);
        setSpace(this.space, this.corner);
    }

    public final void swapImage(PG_FrameImageView view) {
        Bitmap bitmap;
        Intrinsics.checkNotNullParameter(view, "view");
        Bitmap bitmap2 = this.image;
        if (bitmap2 == null || (bitmap = view.image) == null) {
            return;
        }
        view.image = bitmap2;
        this.image = bitmap;
        String imagePath = view.PGPhotoItem.getImagePath();
        view.PGPhotoItem.setImagePath(this.PGPhotoItem.getImagePath());
        this.PGPhotoItem.setImagePath(imagePath);
        resetImageMatrix();
        view.resetImageMatrix();
    }

    public final void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        Intrinsics.checkNotNullParameter(onImageClickListener, "onImageClickListener");
        this.mOnImageClickListener = onImageClickListener;
    }

    @Override 
    public void setBackgroundColor(int i) {
        this.mBackgroundColor = i;
        invalidate();
    }

    @Override // android.widget.ImageView
    public Matrix getImageMatrix() {
        return this.mImageMatrix;
    }

    public static  void init$default(PG_FrameImageView pG_FrameImageView, float f, float f2, float f3, float f4, float f5, int i, Object obj) {
        pG_FrameImageView.init(f, f2, f3, (i & 8) != 0 ? 0.0f : f4, (i & 16) != 0 ? 0.0f : f5);
    }

    public final void init(float f, float f2, float f3, float f4, float f5) {
        this.viewWidth = f;
        this.viewHeight = f2;
        this.mOutputScale = f3;
        this.space = f4;
        this.corner = f5;
        if (this.image != null) {
            Matrix matrix = this.mImageMatrix;
            PG_ImageUtils pG_ImageUtils = PG_ImageUtils.INSTANCE;
            Bitmap bitmap = this.image;
            Intrinsics.checkNotNull(bitmap);
            Bitmap bitmap2 = this.image;
            Intrinsics.checkNotNull(bitmap2);
            matrix.set(pG_ImageUtils.createMatrixToDrawImageInCenterView(f, f2, bitmap.getWidth(), bitmap2.getHeight()));
            Matrix matrix2 = this.mScaleMatrix;
            Bitmap bitmap3 = this.image;
            Intrinsics.checkNotNull(bitmap3);
            Bitmap bitmap4 = this.image;
            Intrinsics.checkNotNull(bitmap4);
            matrix2.set(PG_ImageUtils.INSTANCE.createMatrixToDrawImageInCenterView(f * f3, f2 * f3, bitmap3.getWidth(), bitmap4.getHeight()));
        }
        PG_MultiTouchHandler pG_MultiTouchHandler = new PG_MultiTouchHandler();
        this.mTouchHandler = pG_MultiTouchHandler;
        Intrinsics.checkNotNull(pG_MultiTouchHandler);
        pG_MultiTouchHandler.setMatrices(this.mImageMatrix, this.mScaleMatrix);
        PG_MultiTouchHandler pG_MultiTouchHandler2 = this.mTouchHandler;
        Intrinsics.checkNotNull(pG_MultiTouchHandler2);
        pG_MultiTouchHandler2.setScale(f3);
        PG_MultiTouchHandler pG_MultiTouchHandler3 = this.mTouchHandler;
        Intrinsics.checkNotNull(pG_MultiTouchHandler3);
        pG_MultiTouchHandler3.setEnableRotation(true);
        setSpace(this.space, this.corner);
    }

    public final void setSpace(float f, float f2) {
        this.space = f;
        this.corner = f2;
        Companion.setSpace(this.viewWidth, this.viewHeight, this.PGPhotoItem, this.mConvertedPoints, this.mConvertedClearPoints, this.mPath, this.mClearPath, this.mBackgroundPath, this.mPolygon, this.mPathRect, f, f2);
        invalidate();
    }

    public final void setImagePath(String imagePath) {
        Intrinsics.checkNotNullParameter(imagePath, "imagePath");
        this.PGPhotoItem.setImagePath(imagePath);
        recycleImage();
        try {
            this.image = PG_ImageDecoder.INSTANCE.decodeFileToBitmap(imagePath);
            Matrix matrix = this.mImageMatrix;
            PG_ImageUtils pG_ImageUtils = PG_ImageUtils.INSTANCE;
            float f = this.viewWidth;
            float f2 = this.viewHeight;
            Bitmap bitmap = this.image;
            Intrinsics.checkNotNull(bitmap);
            Bitmap bitmap2 = this.image;
            Intrinsics.checkNotNull(bitmap2);
            matrix.set(pG_ImageUtils.createMatrixToDrawImageInCenterView(f, f2, bitmap.getWidth(), bitmap2.getHeight()));
            Matrix matrix2 = this.mScaleMatrix;
            PG_ImageUtils pG_ImageUtils2 = PG_ImageUtils.INSTANCE;
            float f3 = this.mOutputScale;
            float f4 = this.viewWidth * f3;
            float f5 = f3 * this.viewHeight;
            Bitmap bitmap3 = this.image;
            Intrinsics.checkNotNull(bitmap3);
            Bitmap bitmap4 = this.image;
            Intrinsics.checkNotNull(bitmap4);
            matrix2.set(pG_ImageUtils2.createMatrixToDrawImageInCenterView(f4, f5, bitmap3.getWidth(), bitmap4.getHeight()));
            PG_MultiTouchHandler pG_MultiTouchHandler = this.mTouchHandler;
            Intrinsics.checkNotNull(pG_MultiTouchHandler);
            pG_MultiTouchHandler.setMatrices(this.mImageMatrix, this.mScaleMatrix);
            invalidate();
            PG_ResultContainer companion = PG_ResultContainer.Companion.getInstance();
            String imagePath2 = this.PGPhotoItem.getImagePath();
            Intrinsics.checkNotNull(imagePath2);
            Bitmap bitmap5 = this.image;
            Intrinsics.checkNotNull(bitmap5);
            companion.putImage(imagePath2, bitmap5);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
        }
    }

    public final void resetImageMatrix() {
        Matrix matrix = this.mImageMatrix;
        PG_ImageUtils pG_ImageUtils = PG_ImageUtils.INSTANCE;
        float f = this.viewWidth;
        float f2 = this.viewHeight;
        Bitmap bitmap = this.image;
        Intrinsics.checkNotNull(bitmap);
        Bitmap bitmap2 = this.image;
        Intrinsics.checkNotNull(bitmap2);
        matrix.set(pG_ImageUtils.createMatrixToDrawImageInCenterView(f, f2, bitmap.getWidth(), bitmap2.getHeight()));
        Matrix matrix2 = this.mScaleMatrix;
        PG_ImageUtils pG_ImageUtils2 = PG_ImageUtils.INSTANCE;
        float f3 = this.mOutputScale;
        float f4 = this.viewWidth * f3;
        float f5 = f3 * this.viewHeight;
        Bitmap bitmap3 = this.image;
        Intrinsics.checkNotNull(bitmap3);
        Bitmap bitmap4 = this.image;
        Intrinsics.checkNotNull(bitmap4);
        matrix2.set(pG_ImageUtils2.createMatrixToDrawImageInCenterView(f4, f5, bitmap3.getWidth(), bitmap4.getHeight()));
        PG_MultiTouchHandler pG_MultiTouchHandler = this.mTouchHandler;
        Intrinsics.checkNotNull(pG_MultiTouchHandler);
        pG_MultiTouchHandler.setMatrices(this.mImageMatrix, this.mScaleMatrix);
        invalidate();
    }

    public final void clearMainImage() {
        this.PGPhotoItem.setImagePath(null);
        recycleImage();
        invalidate();
    }

    public final void recycleImage() {
        Bitmap bitmap = this.image;
        if (bitmap != null) {
            Intrinsics.checkNotNull(bitmap);
            bitmap.recycle();
            this.image = null;
            System.gc();
        }
    }

    private final void drawCenterLine(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(5.0f);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setStyle(Paint.Style.STROKE);
        float f = this.viewHeight;
        float f2 = 2;
        canvas.drawLine(0.0f, f / f2, this.viewWidth, f / f2, paint);
        canvas.drawRect(0.0f, 0.0f, this.viewWidth, this.viewHeight, paint);
    }

    public final boolean isSelected(float f, float f2) {
        return PG_GeometryUtils.contains(this.mPolygon, new PointF(f, f2));
    }

    @Override 
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        Companion.drawImage(canvas, this.mPath, this.mPaint, this.mPathRect, this.image, this.mImageMatrix, getWidth(), getHeight(), this.mBackgroundColor, this.mBackgroundPath, this.mClearPath, this.mPolygon);
    }

    public final void drawOutputImage(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        float f = this.viewWidth;
        float f2 = this.mOutputScale;
        float f3 = f * f2;
        float f4 = this.viewHeight * f2;
        Path path = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Rect rect = new Rect();
        ArrayList arrayList = new ArrayList();
        Companion companion = Companion;
        PG_PhotoItem pG_PhotoItem = this.PGPhotoItem;
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = arrayList;
        float f5 = this.space;
        float f6 = this.mOutputScale;
        companion.setSpace(f3, f4, pG_PhotoItem, arrayList2, arrayList3, path, path2, path3, arrayList4, rect, f5 * f6, this.corner * f6);
       Companion.drawImage(canvas, path, this.mPaint, rect, this.image, this.mScaleMatrix, f3, f4, this.mBackgroundColor, path3, path2, arrayList4);
    }

    @Override 
    public boolean onTouchEvent(MotionEvent event) {
        Bitmap bitmap;
        Intrinsics.checkNotNullParameter(event, "event");
        if (!this.mEnableTouch) {
            return super.onTouchEvent(event);
        }
        if (event.getAction() == 0) {
            if (PG_GeometryUtils.contains(this.mPolygon, new PointF(event.getX(), event.getY()))) {
                this.mSelected = true;
            } else {
                this.mSelected = false;
            }
        }
        if (this.mSelected) {
            if (event.getAction() == 1) {
                this.mSelected = false;
            }
            this.mGestureDetector.onTouchEvent(event);
            if (this.mTouchHandler != null && (bitmap = this.image) != null) {
                Intrinsics.checkNotNull(bitmap);
                if (!bitmap.isRecycled()) {
                    PG_MultiTouchHandler pG_MultiTouchHandler = this.mTouchHandler;
                    Intrinsics.checkNotNull(pG_MultiTouchHandler);
                    pG_MultiTouchHandler.touch(event);
                    Matrix matrix = this.mImageMatrix;
                    PG_MultiTouchHandler pG_MultiTouchHandler2 = this.mTouchHandler;
                    Intrinsics.checkNotNull(pG_MultiTouchHandler2);
                    matrix.set(pG_MultiTouchHandler2.getMatrix());
                    Matrix matrix2 = this.mScaleMatrix;
                    PG_MultiTouchHandler pG_MultiTouchHandler3 = this.mTouchHandler;
                    Intrinsics.checkNotNull(pG_MultiTouchHandler3);
                    matrix2.set(pG_MultiTouchHandler3.getScaleMatrix());
                    invalidate();
                }
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    public final void setEnableTouch(boolean z) {
        this.mEnableTouch = z;
    }

    @Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J2\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\tH\u0002J8\u0010\u000f\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\tH\u0002Jv\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u00072\u000e\u0010\"\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010#H\u0002J\u0010\u0010%\u001a\u00020 2\u0006\u0010\u000b\u001a\u00020\fH\u0002Jz\u0010&\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020$0#2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u00072\f\u0010)\u001a\b\u0012\u0004\u0012\u00020$0#2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\tH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/frame/PG_FrameImageView$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "buildRealClearPath", "Landroid/graphics/Path;", "viewWidth", "", "viewHeight", "PGPhotoItem", "Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_PhotoItem;", "clearPath", "corner", "buildRealPath", "", "outPath", "space", "drawImage", "canvas", "Landroid/graphics/Canvas;", "path", "paint", "Landroid/graphics/Paint;", "pathRect", "Landroid/graphics/Rect;", "image", "Landroid/graphics/Bitmap;", "imageMatrix", "Landroid/graphics/Matrix;", "color", "", "backgroundPath", "touchPolygon", "", "Landroid/graphics/PointF;", "findCenterPointIndex", "setSpace", "convertedPoints", "convertedClearPoints", "polygon", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }


        private final void setSpace(float arg10, float arg11, PG_PhotoItem arg12, List arg13, List arg14, Path arg15, Path arg16, Path arg17, List arg18, Rect arg19, float arg20, float arg21) {
            List v2_5;
            float v7 = arg20;
            float v8 = arg21;
            if(arg12.getPointList() != null && (arg13.isEmpty())) {
                for(Object v2: arg12.getPointList()) {
                    PointF v2_1 = (PointF)v2;
                    PointF v3 = new PointF(v2_1.x * arg10, v2_1.y * arg11);
                    arg13.add(v3);
                    if(arg12.getShrinkMap() == null) {
                        continue;
                    }

                    HashMap v4 = arg12.getShrinkMap();
                    Intrinsics.checkNotNull(v4);
                    HashMap v5 = arg12.getShrinkMap();
                    Intrinsics.checkNotNull(v5);
                    Object v2_2 = v5.get(v2_1);
                    Intrinsics.checkNotNull(v2_2);
                    ((Map)v4).put(v3, v2_2);
                }
            }

            if(arg12.getClearAreaPoints() == null) {


            }
            else {
                ArrayList v1_1 = arg12.getClearAreaPoints();
                Intrinsics.checkNotNull(v1_1);
                if(v1_1.size() <= 0) {
                    if(arg12.getClearPath() != null) {
                        arg16.reset();
                        buildRealClearPath(arg10, arg11, arg12, arg16, arg21);
                    }
                }

                arg16.reset();
                if(arg14.isEmpty()) {
                    ArrayList v1_2 = arg12.getClearAreaPoints();
                    Intrinsics.checkNotNull(v1_2);
                    for(Object v2_3: v1_2) {
                        PointF v2_4 = (PointF)v2_3;
                        arg14.add(new PointF(v2_4.x * arg10, v2_4.y * arg11));
                    }
                }

                PG_GeometryUtils.createPathWithCircleCorner(arg16, arg14, v8);
            }

            if(arg12.getPath() == null) {
                if(arg12.getShrinkMethod() == PG_PhotoItem.Companion.getSHRINK_METHOD_3_3()) {
                    v2_5 = PG_GeometryUtils.shrinkPathCollage_3_3(arg13, findCenterPointIndex(arg12), v7, arg12.getBound());
                    Intrinsics.checkNotNullExpressionValue(v2_5, "shrinkPathCollage_3_3(\n …und\n                    )");
                }
                else if(arg12.getShrinkMethod() == PG_PhotoItem.Companion.getSHRINK_METHOD_USING_MAP() && arg12.getShrinkMap() != null) {
                    HashMap v2_6 = arg12.getShrinkMap();
                    Intrinsics.checkNotNull(v2_6);
                    v2_5 = PG_GeometryUtils.shrinkPathCollageUsingMap(arg13, v7, v2_6);
                    Intrinsics.checkNotNullExpressionValue(v2_5, "shrinkPathCollageUsingMa…p!!\n                    )");
                }
                else if(arg12.getShrinkMethod() == PG_PhotoItem.Companion.getSHRINK_METHOD_COMMON() && arg12.getShrinkMap() != null) {
                    HashMap v2_7 = arg12.getShrinkMap();
                    Intrinsics.checkNotNull(v2_7);
                    v2_5 = PG_GeometryUtils.commonShrinkPath(arg13, v7, ((Map)v2_7));
                    Intrinsics.checkNotNullExpressionValue(v2_5, "commonShrinkPath(convert… PGPhotoItem.shrinkMap!!)");
                }
                else if(arg12.getDisableShrink()) {
                    v2_5 = PG_GeometryUtils.shrinkPath(arg13, 0.0f, arg12.getBound());
                    Intrinsics.checkNotNullExpressionValue(v2_5, "shrinkPath(convertedPoints, 0f, PGPhotoItem.bound)");
                }
                else {
                    v2_5 = PG_GeometryUtils.shrinkPath(arg13, v7, arg12.getBound());
                    Intrinsics.checkNotNullExpressionValue(v2_5, "shrinkPath(convertedPoin…space, PGPhotoItem.bound)");
                }

                arg18.clear();
                arg18.addAll(((Collection)v2_5));
                PG_GeometryUtils.createPathWithCircleCorner(arg15, v2_5, v8);
                if(arg12.getHasBackground()) {
                    arg17.reset();
                    PG_GeometryUtils.createPathWithCircleCorner(arg17, arg13, v8);
                }
            }
            else {
                buildRealPath(arg10, arg11, arg12, arg15, arg20, arg21);
                arg18.clear();
            }

            arg19.set(0, 0, 0, 0);
        }


        private final int findCenterPointIndex(PG_PhotoItem pG_PhotoItem) {
            int i;
            int i2 = 0;
            if (pG_PhotoItem.getBound().left == 0.0f) {
                if (pG_PhotoItem.getBound().top == 0.0f) {
                    int size = pG_PhotoItem.getPointList().size();
                    i = 0;
                    float f = 1.0f;
                    while (i2 < size) {
                        PointF pointF = pG_PhotoItem.getPointList().get(i2);
                        Intrinsics.checkNotNullExpressionValue(pointF, "PGPhotoItem.pointList[idx]");
                        PointF pointF2 = pointF;
                        if (pointF2.x > 0.0f && pointF2.x < 1.0f && pointF2.y > 0.0f && pointF2.y < 1.0f && pointF2.x < f) {
                            f = pointF2.x;
                            i = i2;
                        }
                        i2++;
                    }
                    return i;
                }
            }
            int size2 = pG_PhotoItem.getPointList().size();
            float f2 = 0.0f;
            i = 0;
            while (i2 < size2) {
                PointF pointF3 = pG_PhotoItem.getPointList().get(i2);
                Intrinsics.checkNotNullExpressionValue(pointF3, "PGPhotoItem.pointList[idx]");
                PointF pointF4 = pointF3;
                if (pointF4.x > 0.0f && pointF4.x < 1.0f && pointF4.y > 0.0f && pointF4.y < 1.0f && pointF4.x > f2) {
                    f2 = pointF4.x;
                    i = i2;
                }
                i2++;
            }
            return i;
        }

        public final void buildRealPath(float f, float f2, PG_PhotoItem pG_PhotoItem, Path path, float f3, float f4) {
            float min;
            float f5;
            float f6;
            float f7;
            if (pG_PhotoItem.getPath() != null) {
                RectF rectF = new RectF();
                Path path2 = pG_PhotoItem.getPath();
                Intrinsics.checkNotNull(path2);
                path2.computeBounds(rectF, true);
                float width = rectF.width();
                float height = rectF.height();
                float f8 = 2;
                float f9 = f3 * f8;
                Path path3 = pG_PhotoItem.getPath();
                Intrinsics.checkNotNull(path3);
                path.set(path3);
                Matrix matrix = new Matrix();
                if (pG_PhotoItem.getFitBound()) {
                    float pathScaleRatio = pG_PhotoItem.getPathScaleRatio();
                    RectF pathRatioBound = pG_PhotoItem.getPathRatioBound();
                    Intrinsics.checkNotNull(pathRatioBound);
                    float f10 = f8 * f9;
                    min = (pathScaleRatio * ((pathRatioBound.width() * f) - f10)) / width;
                    float pathScaleRatio2 = pG_PhotoItem.getPathScaleRatio();
                    RectF pathRatioBound2 = pG_PhotoItem.getPathRatioBound();
                    Intrinsics.checkNotNull(pathRatioBound2);
                    f5 = (pathScaleRatio2 * ((pathRatioBound2.height() * f2) - f10)) / height;
                } else {
                    float f11 = f8 * f9;
                    min = Math.min((pG_PhotoItem.getPathScaleRatio() * (f2 - f11)) / height, (pG_PhotoItem.getPathScaleRatio() * (f - f11)) / width);
                    f5 = min;
                }
                matrix.postScale(min, f5);
                path.transform(matrix);
                RectF rectF2 = new RectF();
                if (pG_PhotoItem.getCornerMethod() == PG_PhotoItem.Companion.getCORNER_METHOD_3_6()) {
                    path.computeBounds(rectF2, true);
                    PG_GeometryUtils.createRegularPolygonPath(path, Math.min(rectF2.width(), rectF2.height()), 6, f4);
                    path.computeBounds(rectF2, true);
                } else if (pG_PhotoItem.getCornerMethod() == PG_PhotoItem.Companion.getCORNER_METHOD_3_13()) {
                    path.computeBounds(rectF2, true);
                    PG_GeometryUtils.createRectanglePath(path, rectF2.width(), rectF2.height(), f4);
                    path.computeBounds(rectF2, true);
                } else {
                    path.computeBounds(rectF2, true);
                }
                if (pG_PhotoItem.getShrinkMethod() == PG_PhotoItem.Companion.getSHRINK_METHOD_3_6() || pG_PhotoItem.getShrinkMethod() == PG_PhotoItem.Companion.getSHRINK_METHOD_3_8()) {
                    matrix.reset();
                    matrix.postTranslate((f / f8) - (rectF2.width() / f8), (f2 / f8) - (rectF2.height() / f8));
                    path.transform(matrix);
                    return;
                }
                if (pG_PhotoItem.getPathAlignParentRight()) {
                    RectF pathRatioBound3 = pG_PhotoItem.getPathRatioBound();
                    Intrinsics.checkNotNull(pathRatioBound3);
                    f6 = ((pathRatioBound3.right * f) - rectF2.width()) - (f9 / min);
                    RectF pathRatioBound4 = pG_PhotoItem.getPathRatioBound();
                    Intrinsics.checkNotNull(pathRatioBound4);
                    f7 = pathRatioBound4.top;
                } else {
                    RectF pathRatioBound5 = pG_PhotoItem.getPathRatioBound();
                    Intrinsics.checkNotNull(pathRatioBound5);
                    f6 = (pathRatioBound5.left * f) + (f9 / min);
                    RectF pathRatioBound6 = pG_PhotoItem.getPathRatioBound();
                    Intrinsics.checkNotNull(pathRatioBound6);
                    f7 = pathRatioBound6.top;
                }
                float f12 = (f7 * f2) + (f9 / f5);
                if (pG_PhotoItem.getPathInCenterHorizontal()) {
                    f6 = (f / 2.0f) - (rectF2.width() / 2.0f);
                }
                if (pG_PhotoItem.getPathInCenterVertical()) {
                    f12 = (f2 / 2.0f) - (rectF2.height() / 2.0f);
                }
                matrix.reset();
                matrix.postTranslate(f6, f12);
                path.transform(matrix);
            }
        }

        private final Path buildRealClearPath(float f, float f2, PG_PhotoItem pG_PhotoItem, Path path, float f3) {
            float min;
            float f4;
            float width;
            float height;
            if (pG_PhotoItem.getClearPath() != null) {
                RectF rectF = new RectF();
                Path clearPath = pG_PhotoItem.getClearPath();
                Intrinsics.checkNotNull(clearPath);
                clearPath.computeBounds(rectF, true);
                float width2 = rectF.width();
                float height2 = rectF.height();
                Path clearPath2 = pG_PhotoItem.getClearPath();
                Intrinsics.checkNotNull(clearPath2);
                path.set(clearPath2);
                Matrix matrix = new Matrix();
                if (pG_PhotoItem.getFitBound()) {
                    RectF clearPathRatioBound = pG_PhotoItem.getClearPathRatioBound();
                    Intrinsics.checkNotNull(clearPathRatioBound);
                    min = ((pG_PhotoItem.getClearPathScaleRatio() * f) * clearPathRatioBound.width()) / width2;
                    RectF clearPathRatioBound2 = pG_PhotoItem.getClearPathRatioBound();
                    Intrinsics.checkNotNull(clearPathRatioBound2);
                    f4 = ((pG_PhotoItem.getClearPathScaleRatio() * f2) * clearPathRatioBound2.height()) / height2;
                } else {
                    min = Math.min((pG_PhotoItem.getClearPathScaleRatio() * f2) / height2, (pG_PhotoItem.getClearPathScaleRatio() * f) / width2);
                    f4 = min;
                }
                matrix.postScale(min, f4);
                path.transform(matrix);
                RectF rectF2 = new RectF();
                if (pG_PhotoItem.getCornerMethod() == PG_PhotoItem.Companion.getCORNER_METHOD_3_6()) {
                    path.computeBounds(rectF2, true);
                    PG_GeometryUtils.createRegularPolygonPath(path, Math.min(rectF2.width(), rectF2.height()), 6, f3);
                    path.computeBounds(rectF2, true);
                } else if (pG_PhotoItem.getCornerMethod() == PG_PhotoItem.Companion.getCORNER_METHOD_3_13()) {
                    path.computeBounds(rectF2, true);
                    PG_GeometryUtils.createRectanglePath(path, rectF2.width(), rectF2.height(), f3);
                    path.computeBounds(rectF2, true);
                } else {
                    path.computeBounds(rectF2, true);
                }
                if (pG_PhotoItem.getShrinkMethod() == PG_PhotoItem.Companion.getSHRINK_METHOD_3_6()) {
                    RectF clearPathRatioBound3 = pG_PhotoItem.getClearPathRatioBound();
                    Intrinsics.checkNotNull(clearPathRatioBound3);
                    if (clearPathRatioBound3.left > 0.0f) {
                        width = f - (rectF2.width() / 2);
                    } else {
                        width = (-rectF2.width()) / 2;
                    }
                    float f5 = 2;
                    height = (f2 / f5) - (rectF2.height() / f5);
                } else if (pG_PhotoItem.getCenterInClearBound()) {
                    RectF clearPathRatioBound4 = pG_PhotoItem.getClearPathRatioBound();
                    Intrinsics.checkNotNull(clearPathRatioBound4);
                    float f6 = 2;
                    width = ((f / f6) - (rectF2.width() / f6)) + (clearPathRatioBound4.left * f);
                    RectF clearPathRatioBound5 = pG_PhotoItem.getClearPathRatioBound();
                    Intrinsics.checkNotNull(clearPathRatioBound5);
                    height = ((f2 / f6) - (rectF2.height() / f6)) + (clearPathRatioBound5.top * f2);
                } else {
                    RectF clearPathRatioBound6 = pG_PhotoItem.getClearPathRatioBound();
                    Intrinsics.checkNotNull(clearPathRatioBound6);
                    float f7 = clearPathRatioBound6.left * f;
                    RectF clearPathRatioBound7 = pG_PhotoItem.getClearPathRatioBound();
                    Intrinsics.checkNotNull(clearPathRatioBound7);
                    float f8 = clearPathRatioBound7.top * f2;
                    width = pG_PhotoItem.getClearPathInCenterHorizontal() ? (f / 2.0f) - (rectF2.width() / 2.0f) : f7;
                    height = pG_PhotoItem.getClearPathInCenterVertical() ? (f2 / 2.0f) - (rectF2.height() / 2.0f) : f8;
                }
                matrix.reset();
                matrix.postTranslate(width, height);
                path.transform(matrix);
                return path;
            }
            return null;
        }

        public final void drawImage(Canvas canvas, Path path, Paint paint, Rect rect, Bitmap bitmap, Matrix matrix, float f, float f2, int i, Path path2, Path path3, List<PointF> list) {
            if (bitmap != null && !bitmap.isRecycled()) {
                canvas.drawBitmap(bitmap, matrix, paint);
            }
            if (rect.left == rect.right) {
                canvas.save();
                canvas.clipPath(path);
                rect.set(canvas.getClipBounds());
                canvas.restore();
            }
            canvas.save();
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(0.0f, 0.0f, f, rect.top, paint);
            canvas.drawRect(0.0f, 0.0f, rect.left, f2, paint);
            canvas.drawRect(rect.right, 0.0f, f, f2, paint);
            canvas.drawRect(0.0f, rect.bottom, f, f2, paint);
            paint.setXfermode(null);
            canvas.restore();
            canvas.save();
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            paint.setStyle(Paint.Style.FILL);
            Path.FillType fillType = path.getFillType();
            Intrinsics.checkNotNullExpressionValue(fillType, "path.fillType");
            path.setFillType(Path.FillType.INVERSE_WINDING);
            canvas.drawPath(path, paint);
            paint.setXfermode(null);
            canvas.restore();
            path.setFillType(fillType);
            if (path3 != null) {
                canvas.save();
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                canvas.drawARGB(0, 0, 0, 0);
                paint.setColor(ViewCompat.MEASURED_STATE_MASK);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawPath(path3, paint);
                paint.setXfermode(null);
                canvas.restore();
            }
            if (path2 != null) {
                canvas.save();
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
                canvas.drawARGB(0, 0, 0, 0);
                paint.setColor(i);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawPath(path2, paint);
                paint.setXfermode(null);
                canvas.restore();
            }
            if (list == null || !list.isEmpty()) {
                return;
            }
            list.add(new PointF(rect.left, rect.top));
            list.add(new PointF(rect.right, rect.top));
            list.add(new PointF(rect.right, rect.bottom));
            list.add(new PointF(rect.left, rect.bottom));
        }
    }
}
