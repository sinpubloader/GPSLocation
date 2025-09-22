package chin.pswm.gps.photo.location.map.photoGrid.template;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.app.NotificationCompat;
import chin.pswm.gps.photo.location.map.photoGrid.multitouch.PG_MultiTouchHandler;
import chin.pswm.gps.photo.location.map.photoGrid.util.PG_ImageDecoder;
import chin.pswm.gps.photo.location.map.photoGrid.util.PG_ImageUtils;
import chin.pswm.gps.photo.location.map.photoGrid.util.PG_PhotoUtils;
import chin.pswm.gps.photo.location.map.photoGrid.util.PG_ResultContainer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
@SuppressWarnings("all")

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000 K2\u00020\u0001:\u0002KLB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u00103\u001a\u000204J\b\u00105\u001a\u00020\u0014H\u0016J\u001e\u00106\u001a\u0002042\u0006\u00101\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020\u001a2\u0006\u00107\u001a\u00020\u001aJ\u0010\u00108\u001a\u0002042\u0006\u00109\u001a\u00020:H\u0014J\u0010\u0010;\u001a\u00020\u00102\u0006\u0010<\u001a\u00020=H\u0016J\u000e\u0010>\u001a\u0002042\u0006\u0010?\u001a\u00020\u0010J\b\u0010?\u001a\u000204H\u0002J\b\u0010@\u001a\u000204H\u0002J\u0006\u0010A\u001a\u000204J\u000e\u0010B\u001a\u0002042\u0006\u0010C\u001a\u00020\u0010J\u000e\u0010D\u001a\u0002042\u0006\u0010E\u001a\u00020FJ\u000e\u0010G\u001a\u0002042\u0006\u0010H\u001a\u00020\u0016J\u000e\u0010I\u001a\u0002042\u0006\u0010J\u001a\u00020\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010 \u001a\u0004\u0018\u00010\n2\b\u0010\u001f\u001a\u0004\u0018\u00010\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\fR\u0011\u0010\"\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R$\u0010%\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u00188F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0011\u0010*\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b+\u0010$R\u0011\u0010,\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b-\u0010$R\u001e\u0010.\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001a@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u001e\u00101\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001a@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b2\u00100¨\u0006M"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_ItemImageView;", "Landroid/widget/ImageView;", "context", "Landroid/content/Context;", "PGPhotoItem", "Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_PhotoItem;", "(Landroid/content/Context;Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_PhotoItem;)V", "getPGPhotoItem", "()Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_PhotoItem;", "image", "Landroid/graphics/Bitmap;", "getImage", "()Landroid/graphics/Bitmap;", "setImage", "(Landroid/graphics/Bitmap;)V", "mEnableTouch", "", "mGestureDetector", "Landroid/view/GestureDetector;", "mImageMatrix", "Landroid/graphics/Matrix;", "mOnImageClickListener", "Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_ItemImageView$OnImageClickListener;", "mOriginalLayoutParams", "Landroid/widget/RelativeLayout$LayoutParams;", "mOutputScale", "", "mPaint", "Landroid/graphics/Paint;", "mTouchHandler", "Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchHandler;", "<set-?>", "maskImage", "getMaskImage", "maskMatrix", "getMaskMatrix", "()Landroid/graphics/Matrix;", "originalLayoutParams", "getOriginalLayoutParams", "()Landroid/widget/RelativeLayout$LayoutParams;", "setOriginalLayoutParams", "(Landroid/widget/RelativeLayout$LayoutParams;)V", "scaleMaskMatrix", "getScaleMaskMatrix", "scaleMatrix", "getScaleMatrix", "viewHeight", "getViewHeight", "()F", "viewWidth", "getViewWidth", "clearMainImage", "", "getImageMatrix", "init", "scale", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onTouchEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "recycleImages", "recycleMainImage", "recycleMaskImage", "resetImageMatrix", "setEnableTouch", "enableTouch", "setImagePath", "imagePath", "", "setOnImageClickListener", "onImageClickListener", "swapImage", "view", "Companion", "OnImageClickListener", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_ItemImageView extends ImageView {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "PG_ItemImageView";
    private final PG_PhotoItem PGPhotoItem;
    private Bitmap image;
    private boolean mEnableTouch;
    private GestureDetector mGestureDetector;
    private  Matrix mImageMatrix;
    private OnImageClickListener mOnImageClickListener;
    private RelativeLayout.LayoutParams mOriginalLayoutParams;
    private float mOutputScale;
    private  Paint mPaint;
    private PG_MultiTouchHandler mTouchHandler;
    private Bitmap maskImage;
    private  Matrix maskMatrix;
    private  Matrix scaleMaskMatrix;
    private  Matrix scaleMatrix;
    private float viewHeight;
    private float viewWidth;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_ItemImageView$OnImageClickListener;", "", "onDoubleClickImage", "", "view", "Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_ItemImageView;", "onLongClickImage", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public interface OnImageClickListener {
        void onDoubleClickImage(PG_ItemImageView pG_ItemImageView);

        void onLongClickImage(PG_ItemImageView pG_ItemImageView);
    }
    public PG_ItemImageView(Context arg5, PG_PhotoItem arg6) {
        super(arg5);
        Intrinsics.checkNotNullParameter(arg5, "context");
        Intrinsics.checkNotNullParameter(arg6, "PGPhotoItem");
        this.PGPhotoItem = arg6;
        this.mOutputScale = 1.0f;
        this.mEnableTouch = true;
        if(arg6.getImagePath() != null) {
            String v1 = arg6.getImagePath();
            Intrinsics.checkNotNull(v1);
            if(v1.length() > 0) {
                PG_ResultContainer v1_1 = PG_ResultContainer.Companion.getInstance();
                String v2 = arg6.getImagePath();
                Intrinsics.checkNotNull(v2);
                Bitmap v1_2 = v1_1.getImage(v2);
                this.image = v1_2;
                if(v1_2 == null) {

                }
                else {
                    Intrinsics.checkNotNull(v1_2);
                    if(!v1_2.isRecycled()) {
                        if(arg6.getMaskPath() != null) {
                            String v1_4 = arg6.getMaskPath();
                            Intrinsics.checkNotNull(v1_4);
                            if(v1_4.length() > 0) {
                                PG_ResultContainer v1_5 = PG_ResultContainer.Companion.getInstance();
                                String v2_3 = arg6.getMaskPath();
                                Intrinsics.checkNotNull(v2_3);
                                Bitmap v1_6 = v1_5.getImage(v2_3);
                                this.maskImage = v1_6;
                                if(v1_6 == null) {

                                }
                                else {
                                    Intrinsics.checkNotNull(v1_6);
                                    if(!v1_6.isRecycled()) {
                                        Paint v5_1 = new Paint();
                                        this.mPaint = v5_1;
                                        v5_1.setFilterBitmap(true);
                                        v5_1.setAntiAlias(true);
                                        this.setScaleType(ImageView.ScaleType.MATRIX);
                                        this.setLayerType(2, v5_1);
                                        this.mImageMatrix = new Matrix();
                                        this.scaleMatrix = new Matrix();
                                        this.maskMatrix = new Matrix();
                                        this.scaleMaskMatrix = new Matrix();
                                        this.mGestureDetector = new GestureDetector(this.getContext(), ((GestureDetector.OnGestureListener)new GestureDetector.SimpleOnGestureListener() {
                                            @Override  // android.view.GestureDetector$SimpleOnGestureListener
                                            public boolean onDoubleTap(MotionEvent arg2) {
                                                Intrinsics.checkNotNullParameter(arg2, "e");
                                                if(PG_ItemImageView.this.mOnImageClickListener != null) {
                                                    OnImageClickListener v2 = PG_ItemImageView.this.mOnImageClickListener;
                                                    Intrinsics.checkNotNull(v2);
                                                    v2.onDoubleClickImage(PG_ItemImageView.this);
                                                }

                                                return true;
                                            }

                                            @Override  // android.view.GestureDetector$SimpleOnGestureListener
                                            public void onLongPress(MotionEvent arg2) {
                                                Intrinsics.checkNotNullParameter(arg2, "e");
                                                if(PG_ItemImageView.this.mOnImageClickListener != null) {
                                                    OnImageClickListener v2 = PG_ItemImageView.this.mOnImageClickListener;
                                                    Intrinsics.checkNotNull(v2);
                                                    v2.onLongClickImage(PG_ItemImageView.this);
                                                }
                                            }
                                        }));
                                    }

                                    String v2_4 = arg6.getMaskPath();
                                    Intrinsics.checkNotNull(v2_4);
                                    this.maskImage = PG_PhotoUtils.INSTANCE.decodePNGImage(arg5, v2_4);
                                    PG_ResultContainer v5 = PG_ResultContainer.Companion.getInstance();
                                    String v6 = arg6.getMaskPath();
                                    Intrinsics.checkNotNull(v6);
                                    Bitmap v1_7 = this.maskImage;
                                    Intrinsics.checkNotNull(v1_7);
                                    v5.putImage(v6, v1_7);
                                }
                            }
                        }
                    }

                    String v2_1 = arg6.getImagePath();
                    Intrinsics.checkNotNull(v2_1);
                    this.image = PG_ImageDecoder.INSTANCE.decodeFileToBitmap(v2_1);
                    PG_ResultContainer v1_3 = PG_ResultContainer.Companion.getInstance();
                    String v2_2 = arg6.getImagePath();
                    Intrinsics.checkNotNull(v2_2);
                    Bitmap v3 = this.image;
                    Intrinsics.checkNotNull(v3);
                    v1_3.putImage(v2_2, v3);
                }
            }
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

    public final Bitmap getMaskImage() {
        return this.maskImage;
    }

    public final Matrix getScaleMatrix() {
        return this.scaleMatrix;
    }

    public final Matrix getMaskMatrix() {
        return this.maskMatrix;
    }

    public final Matrix getScaleMaskMatrix() {
        return this.scaleMaskMatrix;
    }

    public final float getViewWidth() {
        return this.viewWidth;
    }

    public final float getViewHeight() {
        return this.viewHeight;
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

    public final void swapImage(PG_ItemImageView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        Bitmap bitmap = view.image;
        view.image = this.image;
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

    @Override // android.widget.ImageView
    public Matrix getImageMatrix() {
        return this.mImageMatrix;
    }

    public final void init(float f, float f2, float f3) {
        this.viewWidth = f;
        this.viewHeight = f2;
        this.mOutputScale = f3;
        if (this.image != null) {
            Matrix matrix = this.mImageMatrix;
            PG_ImageUtils pG_ImageUtils = PG_ImageUtils.INSTANCE;
            Bitmap bitmap = this.image;
            Intrinsics.checkNotNull(bitmap);
            Bitmap bitmap2 = this.image;
            Intrinsics.checkNotNull(bitmap2);
            matrix.set(pG_ImageUtils.createMatrixToDrawImageInCenterView(f, f2, bitmap.getWidth(), bitmap2.getHeight()));
            Matrix matrix2 = this.scaleMatrix;
            Bitmap bitmap3 = this.image;
            Intrinsics.checkNotNull(bitmap3);
            Bitmap bitmap4 = this.image;
            Intrinsics.checkNotNull(bitmap4);
            matrix2.set(PG_ImageUtils.INSTANCE.createMatrixToDrawImageInCenterView(f3 * f, f3 * f2, bitmap3.getWidth(), bitmap4.getHeight()));
        }
        if (this.maskImage != null) {
            Matrix matrix3 = this.maskMatrix;
            PG_ImageUtils pG_ImageUtils2 = PG_ImageUtils.INSTANCE;
            Bitmap bitmap5 = this.maskImage;
            Intrinsics.checkNotNull(bitmap5);
            Bitmap bitmap6 = this.maskImage;
            Intrinsics.checkNotNull(bitmap6);
            matrix3.set(pG_ImageUtils2.createMatrixToDrawImageInCenterView(f, f2, bitmap5.getWidth(), bitmap6.getHeight()));
            Matrix matrix4 = this.scaleMaskMatrix;
            Bitmap bitmap7 = this.maskImage;
            Intrinsics.checkNotNull(bitmap7);
            Bitmap bitmap8 = this.maskImage;
            Intrinsics.checkNotNull(bitmap8);
            matrix4.set(PG_ImageUtils.INSTANCE.createMatrixToDrawImageInCenterView(f * f3, f2 * f3, bitmap7.getWidth(), bitmap8.getHeight()));
        }
        PG_MultiTouchHandler pG_MultiTouchHandler = new PG_MultiTouchHandler();
        this.mTouchHandler = pG_MultiTouchHandler;
        Intrinsics.checkNotNull(pG_MultiTouchHandler);
        pG_MultiTouchHandler.setMatrices(this.mImageMatrix, this.scaleMatrix);
        PG_MultiTouchHandler pG_MultiTouchHandler2 = this.mTouchHandler;
        Intrinsics.checkNotNull(pG_MultiTouchHandler2);
        pG_MultiTouchHandler2.setScale(f3);
        PG_MultiTouchHandler pG_MultiTouchHandler3 = this.mTouchHandler;
        Intrinsics.checkNotNull(pG_MultiTouchHandler3);
        pG_MultiTouchHandler3.setEnableRotation(true);
        invalidate();
    }

    public final void setImagePath(String imagePath) {
        Intrinsics.checkNotNullParameter(imagePath, "imagePath");
        this.PGPhotoItem.setImagePath(imagePath);
        recycleMainImage();
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
        Matrix matrix2 = this.scaleMatrix;
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
        pG_MultiTouchHandler.setMatrices(this.mImageMatrix, this.scaleMatrix);
        invalidate();
        PG_ResultContainer companion = PG_ResultContainer.Companion.getInstance();
        String imagePath2 = this.PGPhotoItem.getImagePath();
        Intrinsics.checkNotNull(imagePath2);
        Bitmap bitmap5 = this.image;
        Intrinsics.checkNotNull(bitmap5);
        companion.putImage(imagePath2, bitmap5);
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
        Matrix matrix2 = this.scaleMatrix;
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
        pG_MultiTouchHandler.setMatrices(this.mImageMatrix, this.scaleMatrix);
        invalidate();
    }

    public final void clearMainImage() {
        this.PGPhotoItem.setImagePath(null);
        recycleMainImage();
        invalidate();
    }

    private final void recycleMainImage() {
        Bitmap bitmap = this.image;
        if (bitmap != null) {
            Intrinsics.checkNotNull(bitmap);
            if (bitmap.isRecycled()) {
                return;
            }
            Bitmap bitmap2 = this.image;
            Intrinsics.checkNotNull(bitmap2);
            bitmap2.recycle();
            this.image = null;
            System.gc();
        }
    }

    private final void recycleMaskImage() {
        Bitmap bitmap = this.maskImage;
        if (bitmap != null) {
            Intrinsics.checkNotNull(bitmap);
            if (bitmap.isRecycled()) {
                return;
            }
            Bitmap bitmap2 = this.maskImage;
            Intrinsics.checkNotNull(bitmap2);
            bitmap2.recycle();
            this.maskImage = null;
            System.gc();
        }
    }

    public final void recycleImages(boolean z) {
        if (z) {
            recycleMainImage();
        }
        recycleMaskImage();
    }

    @Override 
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        Bitmap bitmap2 = this.image;
        if (bitmap2 != null) {
            Intrinsics.checkNotNull(bitmap2);
            if (bitmap2.isRecycled() || (bitmap = this.maskImage) == null) {
                return;
            }
            Intrinsics.checkNotNull(bitmap);
            if (bitmap.isRecycled()) {
                return;
            }
            Bitmap bitmap3 = this.image;
            Intrinsics.checkNotNull(bitmap3);
            canvas.drawBitmap(bitmap3, this.mImageMatrix, this.mPaint);
            this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            Bitmap bitmap4 = this.maskImage;
            Intrinsics.checkNotNull(bitmap4);
            canvas.drawBitmap(bitmap4, this.maskMatrix, this.mPaint);
            this.mPaint.setXfermode(null);
        }
    }

    @Override 
    public boolean onTouchEvent(MotionEvent event) {
        Bitmap bitmap;
        Intrinsics.checkNotNullParameter(event, "event");
        if (!this.mEnableTouch) {
            return super.onTouchEvent(event);
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
                Matrix matrix2 = this.scaleMatrix;
                PG_MultiTouchHandler pG_MultiTouchHandler3 = this.mTouchHandler;
                Intrinsics.checkNotNull(pG_MultiTouchHandler3);
                matrix2.set(pG_MultiTouchHandler3.getScaleMatrix());
                invalidate();
            }
        }
        return true;
    }

    public final void setEnableTouch(boolean z) {
        this.mEnableTouch = z;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_ItemImageView$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
