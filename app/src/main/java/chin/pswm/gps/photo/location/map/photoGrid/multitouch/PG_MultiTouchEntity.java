package chin.pswm.gps.photo.location.map.photoGrid.multitouch;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import chin.pswm.gps.photo.location.map.photoGrid.multitouch.PG_MultiTouchController;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
@SuppressWarnings("all")
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b%\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0014\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000 \u0083\u00012\u00020\u0001:\u0002\u0083\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010g\u001a\u00020Y2\u0006\u0010^\u001a\u00020\u00072\u0006\u0010a\u001a\u00020\u0007H\u0004J\u0016\u0010h\u001a\u00020\u001a2\u0006\u0010i\u001a\u00020\u00072\u0006\u0010j\u001a\u00020\u0007J\u0018\u0010k\u001a\u00020\u001a2\u0006\u0010i\u001a\u00020\u00072\u0006\u0010j\u001a\u00020\u0007H\u0007J\b\u0010l\u001a\u00020\u0013H\u0016J\u0010\u0010m\u001a\u00020n2\u0006\u0010o\u001a\u00020pH&J\u0010\u0010q\u001a\u00020n2\u0006\u0010\u0003\u001a\u00020\u0004H\u0004J\u0016\u0010r\u001a\u00020\u001a2\u0006\u0010i\u001a\u00020\u00072\u0006\u0010j\u001a\u00020\u0007J\u0010\u0010s\u001a\u00020n2\u0006\u0010t\u001a\u00020uH&J \u0010s\u001a\u00020n2\u0006\u0010t\u001a\u00020u2\u0006\u0010v\u001a\u00020\u00072\u0006\u0010w\u001a\u00020\u0007H&J\u0010\u0010x\u001a\u00020n2\u0006\u0010y\u001a\u00020zH\u0016J\u000e\u0010{\u001a\u00020n2\u0006\u0010t\u001a\u00020uJ\u000e\u0010|\u001a\u00020\u001a2\u0006\u0010}\u001a\u00020~J0\u0010|\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010^\u001a\u00020\u00072\u0006\u0010a\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0004J\b\u0010\u007f\u001a\u00020nH&J\u001b\u0010\u0080\u0001\u001a\u00020n2\u0007\u0010\u0081\u0001\u001a\u00020z2\u0007\u0010\u0082\u0001\u001a\u00020\u0013H\u0016R$\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR$\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR$\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u0013@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0013X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0016\"\u0004\b \u0010\u0018R\u001a\u0010!\u001a\u00020\u0013X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0016\"\u0004\b#\u0010\u0018R\u001a\u0010$\u001a\u00020\u001aX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001b\"\u0004\b&\u0010\u001dR\u001a\u0010'\u001a\u00020\u0007X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\n\"\u0004\b)\u0010\fR\u001a\u0010*\u001a\u00020\u0007X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\n\"\u0004\b,\u0010\fR\u001a\u0010-\u001a\u00020\u0007X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\n\"\u0004\b/\u0010\fR\u001a\u00100\u001a\u00020\u0007X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\n\"\u0004\b2\u0010\fR\u001a\u00103\u001a\u00020\u001aX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u001b\"\u0004\b5\u0010\u001dR\u001a\u00106\u001a\u00020\u0007X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\n\"\u0004\b8\u0010\fR\u001a\u00109\u001a\u00020\u0007X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\n\"\u0004\b;\u0010\fR\u001a\u0010<\u001a\u00020\u0013X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0016\"\u0004\b>\u0010\u0018R \u0010?\u001a\b\u0012\u0004\u0012\u00020A0@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u001a\u0010F\u001a\u00020GX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR$\u0010L\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\n\"\u0004\bN\u0010\fR$\u0010O\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010\n\"\u0004\bQ\u0010\fR$\u0010R\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010\n\"\u0004\bT\u0010\fR$\u0010U\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010\n\"\u0004\bW\u0010\fR\u001a\u0010X\u001a\u00020YX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R$\u0010^\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010\n\"\u0004\b`\u0010\fR$\u0010a\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010\n\"\u0004\bc\u0010\fR$\u0010d\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u0013@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010\u0016\"\u0004\bf\u0010\u0018¨\u0006\u0084\u0001"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchEntity;", "Landroid/os/Parcelable;", "()V", "res", "Landroid/content/res/Resources;", "(Landroid/content/res/Resources;)V", "<set-?>", "", "angle", "getAngle", "()F", "setAngle", "(F)V", "centerX", "getCenterX", "setCenterX", "centerY", "getCenterY", "setCenterY", "", "height", "getHeight", "()I", "setHeight", "(I)V", "isGrabAreaSelected", "", "()Z", "setGrabAreaSelected", "(Z)V", "mDisplayHeight", "getMDisplayHeight", "setMDisplayHeight", "mDisplayWidth", "getMDisplayWidth", "setMDisplayWidth", "mFirstLoad", "getMFirstLoad", "setMFirstLoad", "mGrabAreaX1", "getMGrabAreaX1", "setMGrabAreaX1", "mGrabAreaX2", "getMGrabAreaX2", "setMGrabAreaX2", "mGrabAreaY1", "getMGrabAreaY1", "setMGrabAreaY1", "mGrabAreaY2", "getMGrabAreaY2", "setMGrabAreaY2", "mIsLatestSelected", "getMIsLatestSelected", "setMIsLatestSelected", "mStartMidX", "getMStartMidX", "setMStartMidX", "mStartMidY", "getMStartMidY", "setMStartMidY", "mUIMode", "getMUIMode", "setMUIMode", "mappedPoints", "", "Landroid/graphics/PointF;", "getMappedPoints$app_release", "()Ljava/util/List;", "setMappedPoints$app_release", "(Ljava/util/List;)V", "matrix", "Landroid/graphics/Matrix;", "getMatrix$app_release", "()Landroid/graphics/Matrix;", "setMatrix$app_release", "(Landroid/graphics/Matrix;)V", "maxX", "getMaxX", "setMaxX", "maxY", "getMaxY", "setMaxY", "minX", "getMinX", "setMinX", "minY", "getMinY", "setMinY", "point", "", "getPoint$app_release", "()[F", "setPoint$app_release", "([F)V", "scaleX", "getScaleX", "setScaleX", "scaleY", "getScaleY", "setScaleY", "width", "getWidth", "setWidth", "calculateHalfDrawableSize", "contain", "touchX", "touchY", "containsPoint", "describeContents", "draw", "", "canvas", "Landroid/graphics/Canvas;", "getMetrics", "grabAreaContainsPoint", "load", "context", "Landroid/content/Context;", "startMidX", "startMidY", "readFromParcel", "in", "Landroid/os/Parcel;", "reload", "setPos", "newImgPosAndScale", "Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$PositionAndScale;", "unload", "writeToParcel", "dest", "flags", "Companion", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public abstract class PG_MultiTouchEntity implements Parcelable {
    private float angle;
    private float centerX;
    private float centerY;
    private int height;
    private boolean isGrabAreaSelected;
    private int mDisplayHeight;
    private int mDisplayWidth;
    private boolean mFirstLoad;
    private float mGrabAreaX1;
    private float mGrabAreaX2;
    private float mGrabAreaY1;
    private float mGrabAreaY2;
    private boolean mIsLatestSelected;
    private float mStartMidX;
    private float mStartMidY;
    private int mUIMode;
    private List<PointF> mappedPoints;
    private Matrix matrix;
    private float maxX;
    private float maxY;
    private float minX;
    private float minY;
    private float[] point;
    private float scaleX;
    private float scaleY;
    private int width;
    public static final Companion Companion = new Companion(null);
    private static final int GRAB_AREA_SIZE = 40;
    private static final int UI_MODE_ROTATE = 1;
    private static final int UI_MODE_ANISOTROPIC_SCALE = 2;

    @Override 
    public int describeContents() {
        return 0;
    }

    public abstract void draw(Canvas canvas);

    public abstract void load(Context context);

    public abstract void load(Context context, float f, float f2);

    public abstract void unload();

    public final boolean getMFirstLoad() {
        return this.mFirstLoad;
    }

    public final void setMFirstLoad(boolean z) {
        this.mFirstLoad = z;
    }

    public final int getWidth() {
        return this.width;
    }

    public final void setWidth(int i) {
        this.width = i;
    }

    public final int getHeight() {
        return this.height;
    }

    public final void setHeight(int i) {
        this.height = i;
    }

    public final int getMDisplayWidth() {
        return this.mDisplayWidth;
    }

    protected final void setMDisplayWidth(int i) {
        this.mDisplayWidth = i;
    }

    public final int getMDisplayHeight() {
        return this.mDisplayHeight;
    }

    protected final void setMDisplayHeight(int i) {
        this.mDisplayHeight = i;
    }

    public final float getCenterX() {
        return this.centerX;
    }

    public final void setCenterX(float f) {
        this.centerX = f;
    }

    public final float getCenterY() {
        return this.centerY;
    }

    public final void setCenterY(float f) {
        this.centerY = f;
    }

    public final float getScaleX() {
        return this.scaleX;
    }

    public final void setScaleX(float f) {
        this.scaleX = f;
    }

    public final float getScaleY() {
        return this.scaleY;
    }

    public final void setScaleY(float f) {
        this.scaleY = f;
    }

    public final float getAngle() {
        return this.angle;
    }

    public final void setAngle(float f) {
        this.angle = f;
    }

    public final float getMinX() {
        return this.minX;
    }

    protected final void setMinX(float f) {
        this.minX = f;
    }

    public final float getMaxX() {
        return this.maxX;
    }

    protected final void setMaxX(float f) {
        this.maxX = f;
    }

    public final float getMinY() {
        return this.minY;
    }

    protected final void setMinY(float f) {
        this.minY = f;
    }

    public final float getMaxY() {
        return this.maxY;
    }

    protected final void setMaxY(float f) {
        this.maxY = f;
    }

    public final boolean isGrabAreaSelected() {
        return this.isGrabAreaSelected;
    }

    public final void setGrabAreaSelected(boolean z) {
        this.isGrabAreaSelected = z;
    }

    protected final boolean getMIsLatestSelected() {
        return this.mIsLatestSelected;
    }

    protected final void setMIsLatestSelected(boolean z) {
        this.mIsLatestSelected = z;
    }

    protected final float getMGrabAreaX1() {
        return this.mGrabAreaX1;
    }

    protected final void setMGrabAreaX1(float f) {
        this.mGrabAreaX1 = f;
    }

    protected final float getMGrabAreaY1() {
        return this.mGrabAreaY1;
    }

    protected final void setMGrabAreaY1(float f) {
        this.mGrabAreaY1 = f;
    }

    protected final float getMGrabAreaX2() {
        return this.mGrabAreaX2;
    }

    protected final void setMGrabAreaX2(float f) {
        this.mGrabAreaX2 = f;
    }

    protected final float getMGrabAreaY2() {
        return this.mGrabAreaY2;
    }

    protected final void setMGrabAreaY2(float f) {
        this.mGrabAreaY2 = f;
    }

    protected final float getMStartMidX() {
        return this.mStartMidX;
    }

    public final void setMStartMidX(float f) {
        this.mStartMidX = f;
    }

    protected final float getMStartMidY() {
        return this.mStartMidY;
    }

    public final void setMStartMidY(float f) {
        this.mStartMidY = f;
    }

    protected final int getMUIMode() {
        return this.mUIMode;
    }

    protected final void setMUIMode(int i) {
        this.mUIMode = i;
    }

    public final Matrix getMatrix$app_release() {
        return this.matrix;
    }

    public final void setMatrix$app_release(Matrix matrix) {
        Intrinsics.checkNotNullParameter(matrix, "<set-?>");
        this.matrix = matrix;
    }

    public final float[] getPoint$app_release() {
        return this.point;
    }

    public final void setPoint$app_release(float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<set-?>");
        this.point = fArr;
    }

    public final List<PointF> getMappedPoints$app_release() {
        return this.mappedPoints;
    }

    public final void setMappedPoints$app_release(List<PointF> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mappedPoints = list;
    }

    public PG_MultiTouchEntity() {
        this.mFirstLoad = true;
        this.mUIMode = UI_MODE_ROTATE;
        this.matrix = new Matrix();
        this.point = new float[2];
        this.mappedPoints = new ArrayList();
    }

    public PG_MultiTouchEntity(Resources res) {
        Intrinsics.checkNotNullParameter(res, "res");
        this.mFirstLoad = true;
        this.mUIMode = UI_MODE_ROTATE;
        this.matrix = new Matrix();
        this.point = new float[2];
        this.mappedPoints = new ArrayList();
        getMetrics(res);
    }

    public final void getMetrics(Resources res) {
        int min;
        int max;
        Intrinsics.checkNotNullParameter(res, "res");
        DisplayMetrics displayMetrics = res.getDisplayMetrics();
        if (res.getConfiguration().orientation == 2) {
            min = Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
        } else {
            min = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        this.mDisplayWidth = min;
        if (res.getConfiguration().orientation == 2) {
            max = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
        } else {
            max = Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        this.mDisplayHeight = max;
    }

    public final boolean setPos(PG_MultiTouchController.PositionAndScale newImgPosAndScale) {
        float scale;
        float scale2;
        Intrinsics.checkNotNullParameter(newImgPosAndScale, "newImgPosAndScale");
        int i = this.mUIMode;
        int i2 = UI_MODE_ANISOTROPIC_SCALE;
        if ((i & i2) != 0) {
            scale = newImgPosAndScale.getScaleX();
        } else {
            scale = newImgPosAndScale.getScale();
        }
        float f = scale;
        if ((this.mUIMode & i2) != 0) {
            scale2 = newImgPosAndScale.getScaleY();
        } else {
            scale2 = newImgPosAndScale.getScale();
        }
        return setPos(newImgPosAndScale.getXOff(), newImgPosAndScale.getYOff(), f, scale2, newImgPosAndScale.getAngle());
    }

    public final boolean setPos(float f, float f2, float f3, float f4, float f5) {
        float[] calculateHalfDrawableSize = calculateHalfDrawableSize(f3, f4);
        float f6 = calculateHalfDrawableSize[0];
        float f7 = calculateHalfDrawableSize[1];
        this.minX = f - f6;
        this.minY = f2 - f7;
        float f8 = f6 + f;
        this.maxX = f8;
        float f9 = f7 + f2;
        this.maxY = f9;
        int i = GRAB_AREA_SIZE;
        this.mGrabAreaX1 = f8 - i;
        this.mGrabAreaY1 = f9 - i;
        this.mGrabAreaX2 = f8;
        this.mGrabAreaY2 = f9;
        this.centerX = f;
        this.centerY = f2;
        this.scaleX = f3;
        this.scaleY = f4;
        this.angle = f5;
        return true;
    }

    protected final float[] calculateHalfDrawableSize(float f, float f2) {
        return new float[]{(this.width / 2) * f, (this.height / 2) * f2};
    }

    @Deprecated(message = " Return whether or not the given screen coords are inside this image")
    public final boolean containsPoint(float f, float f2) {
        return f >= this.minX && f <= this.maxX && f2 >= this.minY && f2 <= this.maxY;
    }

    public final boolean contain(float f, float f2) {
        float f3 = 2;
        float f4 = (this.maxY + this.minY) / f3;
        this.matrix.reset();
        this.matrix.setRotate((this.angle * 180.0f) / 3.1415927f, (this.maxX + this.minX) / f3, f4);
        this.mappedPoints.clear();
        float[] fArr = this.point;
        fArr[0] = this.minX;
        fArr[1] = this.minY;
        this.matrix.mapPoints(fArr);
        List<PointF> list = this.mappedPoints;
        float[] fArr2 = this.point;
        list.add(new PointF(fArr2[0], fArr2[1]));
        float[] fArr3 = this.point;
        fArr3[0] = this.maxX;
        fArr3[1] = this.minY;
        this.matrix.mapPoints(fArr3);
        List<PointF> list2 = this.mappedPoints;
        float[] fArr4 = this.point;
        list2.add(new PointF(fArr4[0], fArr4[1]));
        float[] fArr5 = this.point;
        fArr5[0] = this.maxX;
        fArr5[1] = this.maxY;
        this.matrix.mapPoints(fArr5);
        List<PointF> list3 = this.mappedPoints;
        float[] fArr6 = this.point;
        list3.add(new PointF(fArr6[0], fArr6[1]));
        float[] fArr7 = this.point;
        fArr7[0] = this.minX;
        fArr7[1] = this.maxY;
        this.matrix.mapPoints(fArr7);
        List<PointF> list4 = this.mappedPoints;
        float[] fArr8 = this.point;
        list4.add(new PointF(fArr8[0], fArr8[1]));
        return Companion.contains(this.mappedPoints, new PointF(f, f2));
    }

    public final boolean grabAreaContainsPoint(float f, float f2) {
        return f >= this.mGrabAreaX1 && f <= this.mGrabAreaX2 && f2 >= this.mGrabAreaY1 && f2 <= this.mGrabAreaY2;
    }

    public final void reload(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.mFirstLoad = false;
        load(context, this.centerX, this.centerY);
    }

    @Override 
    public void writeToParcel(Parcel dest, int i) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeBooleanArray(new boolean[]{this.mFirstLoad, this.isGrabAreaSelected, this.mIsLatestSelected});
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeInt(this.mDisplayWidth);
        dest.writeInt(this.mDisplayHeight);
        dest.writeFloat(this.centerX);
        dest.writeFloat(this.centerY);
        dest.writeFloat(this.scaleX);
        dest.writeFloat(this.scaleY);
        dest.writeFloat(this.angle);
        dest.writeFloat(this.minX);
        dest.writeFloat(this.maxX);
        dest.writeFloat(this.minY);
        dest.writeFloat(this.maxY);
        dest.writeFloat(this.mGrabAreaX1);
        dest.writeFloat(this.mGrabAreaY1);
        dest.writeFloat(this.mGrabAreaX2);
        dest.writeFloat(this.mGrabAreaY2);
        dest.writeFloat(this.mStartMidX);
        dest.writeFloat(this.mStartMidY);
        dest.writeInt(this.mUIMode);
    }

    public void readFromParcel(Parcel in) {
        Intrinsics.checkNotNullParameter(in, "in");
        boolean[] zArr = new boolean[3];
        in.readBooleanArray(zArr);
        this.mFirstLoad = zArr[0];
        this.isGrabAreaSelected = zArr[1];
        this.mIsLatestSelected = zArr[2];
        this.width = in.readInt();
        this.height = in.readInt();
        this.mDisplayWidth = in.readInt();
        this.mDisplayHeight = in.readInt();
        this.centerX = in.readFloat();
        this.centerY = in.readFloat();
        this.scaleX = in.readFloat();
        this.scaleY = in.readFloat();
        this.angle = in.readFloat();
        this.minX = in.readFloat();
        this.maxX = in.readFloat();
        this.minY = in.readFloat();
        this.maxY = in.readFloat();
        this.mGrabAreaX1 = in.readFloat();
        this.mGrabAreaY1 = in.readFloat();
        this.mGrabAreaX2 = in.readFloat();
        this.mGrabAreaY2 = in.readFloat();
        this.mStartMidX = in.readFloat();
        this.mStartMidY = in.readFloat();
        this.mUIMode = in.readInt();
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\rR\u0014\u0010\u0003\u001a\u00020\u0004X\u0084D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchEntity$Companion;", "", "()V", "GRAB_AREA_SIZE", "", "getGRAB_AREA_SIZE", "()I", "UI_MODE_ANISOTROPIC_SCALE", "UI_MODE_ROTATE", "contains", "", "points", "", "Landroid/graphics/PointF;", "test", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        protected final int getGRAB_AREA_SIZE() {
            return PG_MultiTouchEntity.GRAB_AREA_SIZE;
        }

        public final boolean contains(List<? extends PointF> points, PointF test) {
            Intrinsics.checkNotNullParameter(points, "points");
            Intrinsics.checkNotNullParameter(test, "test");
            int size = points.size() - 1;
            boolean z = false;
            for (int i = 0; i < points.size(); i++) {
                if ((points.get(i).y > test.y) != (points.get(size).y > test.y) && test.x < (((points.get(size).x - points.get(i).x) * (test.y - points.get(i).y)) / (points.get(size).y - points.get(i).y)) + points.get(i).x) {
                    z = !z;
                }
                size = i;
            }
            return z;
        }
    }
}
