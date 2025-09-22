package chin.pswm.gps.photo.location.map.photoGrid.template;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import androidx.core.app.NotificationCompat;
import chin.pswm.gps.photo.location.map.photoGrid.multitouch.PG_MultiTouchHandler;
import chin.pswm.gps.photo.location.map.photoGrid.util.PG_ImageDecoder;
import chin.pswm.gps.photo.location.map.photoGrid.util.PG_ImageUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
@SuppressWarnings("all")
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u00010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u001c\u001a\u00020\rH\u0016J&\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u0013J\u0010\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020$H\u0014J\u0010\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0016J\u0006\u0010)\u001a\u00020\u001eJ\u0006\u0010*\u001a\u00020\u001eJ\u000e\u0010+\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020-J\u000e\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020\u000fR\"\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0019\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u00061"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_TransitionImageView;", "Landroid/widget/ImageView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "<set-?>", "Landroid/graphics/Bitmap;", "image", "getImage", "()Landroid/graphics/Bitmap;", "mGestureDetector", "Landroid/view/GestureDetector;", "mImageMatrix", "Landroid/graphics/Matrix;", "mOnImageClickListener", "Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_TransitionImageView$OnImageClickListener;", "mPaint", "Landroid/graphics/Paint;", "mScale", "", "mTouchHandler", "Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchHandler;", "mViewHeight", "", "mViewWidth", "scaleMatrix", "getScaleMatrix", "()Landroid/graphics/Matrix;", "getImageMatrix", "init", "", "viewWidth", "viewHeight", "scale", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onTouchEvent", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "recycleImages", "reset", "setImagePath", "path", "", "setOnImageClickListener", "onImageClickListener", "OnImageClickListener", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_TransitionImageView extends ImageView {
    private Bitmap image;
    private final GestureDetector mGestureDetector;
    private final Matrix mImageMatrix;
    private OnImageClickListener mOnImageClickListener;
    private final Paint mPaint;
    private float mScale;
    private PG_MultiTouchHandler mTouchHandler;
    private int mViewHeight;
    private int mViewWidth;
    private final Matrix scaleMatrix;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_TransitionImageView$OnImageClickListener;", "", "onDoubleClickImage", "", "view", "Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_TransitionImageView;", "onLongClickImage", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public interface OnImageClickListener {
        void onDoubleClickImage(PG_TransitionImageView pG_TransitionImageView);

        void onLongClickImage(PG_TransitionImageView pG_TransitionImageView);
    }

    
    public PG_TransitionImageView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mScale = 1.0f;
        setScaleType(ImageView.ScaleType.MATRIX);
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setFilterBitmap(true);
        paint.setAntiAlias(true);
        this.mImageMatrix = new Matrix();
        this.scaleMatrix = new Matrix();
        this.mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() { 


            @Override 
            public void onLongPress(MotionEvent e) {
                Intrinsics.checkNotNullParameter(e, "e");
                if (PG_TransitionImageView.this.mOnImageClickListener != null) {
                    OnImageClickListener onImageClickListener = PG_TransitionImageView.this.mOnImageClickListener;
                    Intrinsics.checkNotNull(onImageClickListener);
                    onImageClickListener.onLongClickImage(PG_TransitionImageView.this);
                }
            }

            @Override 
            public boolean onDoubleTap(MotionEvent e) {
                Intrinsics.checkNotNullParameter(e, "e");
                if (PG_TransitionImageView.this.mOnImageClickListener != null) {
                    OnImageClickListener onImageClickListener = PG_TransitionImageView.this.mOnImageClickListener;
                    Intrinsics.checkNotNull(onImageClickListener);
                    onImageClickListener.onDoubleClickImage(PG_TransitionImageView.this);
                    return true;
                }
                return true;
            }
        });
    }

    public final Matrix getScaleMatrix() {
        return this.scaleMatrix;
    }

    public final Bitmap getImage() {
        return this.image;
    }

    public final void reset() {
        this.mTouchHandler = null;
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    public final void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        Intrinsics.checkNotNullParameter(onImageClickListener, "onImageClickListener");
        this.mOnImageClickListener = onImageClickListener;
    }

    public final void recycleImages() {
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
            invalidate();
        }
    }

    public final void setImagePath(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        recycleImages();
        Bitmap decodeFileToBitmap = PG_ImageDecoder.INSTANCE.decodeFileToBitmap(path);
        if (decodeFileToBitmap != null) {
            init(decodeFileToBitmap, this.mViewWidth, this.mViewHeight, this.mScale);
        }
    }

    public final void init(Bitmap bitmap, int i, int i2, float f) {
        Intrinsics.checkNotNullParameter(bitmap, "image");
        this.image = bitmap;
        this.mViewWidth = i;
        this.mViewHeight = i2;
        this.mScale = f;
        if (bitmap != null) {
            Matrix matrix = this.mImageMatrix;
            PG_ImageUtils pG_ImageUtils = PG_ImageUtils.INSTANCE;
            float f2 = (float) i;
            float f3 = (float) i2;
            Bitmap bitmap2 = this.image;
            Intrinsics.checkNotNull(bitmap2);
            Bitmap bitmap3 = this.image;
            Intrinsics.checkNotNull(bitmap3);
            matrix.set(pG_ImageUtils.createMatrixToDrawImageInCenterView(f2, f3, (float) bitmap2.getWidth(), (float) bitmap3.getHeight()));
            Matrix matrix2 = this.scaleMatrix;
            Bitmap bitmap4 = this.image;
            Intrinsics.checkNotNull(bitmap4);
            Bitmap bitmap5 = this.image;
            Intrinsics.checkNotNull(bitmap5);
            matrix2.set(PG_ImageUtils.INSTANCE.createMatrixToDrawImageInCenterView(f2 * f, f3 * f, (float) bitmap4.getWidth(), (float) bitmap5.getHeight()));
        }
        PG_MultiTouchHandler pG_MultiTouchHandler = new PG_MultiTouchHandler();
        this.mTouchHandler = pG_MultiTouchHandler;
        Intrinsics.checkNotNull(pG_MultiTouchHandler);
        pG_MultiTouchHandler.setMatrices(this.mImageMatrix, this.scaleMatrix);
        PG_MultiTouchHandler pG_MultiTouchHandler2 = this.mTouchHandler;
        Intrinsics.checkNotNull(pG_MultiTouchHandler2);
        pG_MultiTouchHandler2.setScale(f);
        PG_MultiTouchHandler pG_MultiTouchHandler3 = this.mTouchHandler;
        Intrinsics.checkNotNull(pG_MultiTouchHandler3);
        pG_MultiTouchHandler3.setEnableRotation(false);
        PG_MultiTouchHandler pG_MultiTouchHandler4 = this.mTouchHandler;
        Intrinsics.checkNotNull(pG_MultiTouchHandler4);
        pG_MultiTouchHandler4.setEnableZoom(false);
        float f4 = (float) i;
        Bitmap bitmap6 = this.image;
        Intrinsics.checkNotNull(bitmap6);
        float width = f4 / ((float) bitmap6.getWidth());
        float f5 = (float) i2;
        Bitmap bitmap7 = this.image;
        Intrinsics.checkNotNull(bitmap7);
        float height = f5 / ((float) bitmap7.getHeight());
        if (width > height) {
            PG_MultiTouchHandler pG_MultiTouchHandler5 = this.mTouchHandler;
            Intrinsics.checkNotNull(pG_MultiTouchHandler5);
            pG_MultiTouchHandler5.setEnableTranslateX(false);
            Bitmap bitmap8 = this.image;
            Intrinsics.checkNotNull(bitmap8);
            PG_MultiTouchHandler pG_MultiTouchHandler6 = this.mTouchHandler;
            Intrinsics.checkNotNull(pG_MultiTouchHandler6);
            pG_MultiTouchHandler6.setMaxPositionOffset(((width * ((float) bitmap8.getHeight())) - f5) / 2.0f);
        } else {
            PG_MultiTouchHandler pG_MultiTouchHandler7 = this.mTouchHandler;
            Intrinsics.checkNotNull(pG_MultiTouchHandler7);
            pG_MultiTouchHandler7.setEnableTranslateY(false);
            Bitmap bitmap9 = this.image;
            Intrinsics.checkNotNull(bitmap9);
            PG_MultiTouchHandler pG_MultiTouchHandler8 = this.mTouchHandler;
            Intrinsics.checkNotNull(pG_MultiTouchHandler8);
            pG_MultiTouchHandler8.setMaxPositionOffset(((height * ((float) bitmap9.getWidth())) - f4) / 2.0f);
        }
        invalidate();
    }

    @Override 
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        Bitmap bitmap = this.image;
        if (bitmap != null) {
            Intrinsics.checkNotNull(bitmap);
            if (bitmap.isRecycled()) {
                return;
            }
            Bitmap bitmap2 = this.image;
            Intrinsics.checkNotNull(bitmap2);
            canvas.drawBitmap(bitmap2, this.mImageMatrix, this.mPaint);
        }
    }

    @Override 
    public boolean onTouchEvent(MotionEvent event) {
        Bitmap bitmap;
        Intrinsics.checkNotNullParameter(event, "event");
        this.mGestureDetector.onTouchEvent(event);
        if (this.mTouchHandler == null || (bitmap = this.image) == null) {
            return true;
        }
        Intrinsics.checkNotNull(bitmap);
        if (bitmap.isRecycled()) {
            return true;
        }
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
        return true;
    }

    @Override 
    public Matrix getImageMatrix() {
        return this.mImageMatrix;
    }
}
