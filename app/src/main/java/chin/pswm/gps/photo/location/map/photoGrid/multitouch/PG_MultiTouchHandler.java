package chin.pswm.gps.photo.location.map.photoGrid.multitouch;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.MotionEvent;

import androidx.core.app.NotificationCompat;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
@SuppressWarnings("all")
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\u0018\u0000 @2\u00020\u0001:\u0001@B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0012\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010&\u001a\u00020\u0016H\u0016J\u0018\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020+H\u0002J\u0006\u0010,\u001a\u00020(J\u0010\u0010-\u001a\u00020\t2\u0006\u0010*\u001a\u00020+H\u0002J\u000e\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u00020\u000bJ\u000e\u00100\u001a\u00020(2\u0006\u00101\u001a\u00020\u000bJ\u000e\u00102\u001a\u00020(2\u0006\u00103\u001a\u00020\u000bJ\u000e\u00104\u001a\u00020(2\u0006\u00105\u001a\u00020\u000bJ\u0016\u00106\u001a\u00020(2\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u0012J\u000e\u00107\u001a\u00020(2\u0006\u00108\u001a\u00020\tJ\u000e\u00109\u001a\u00020(2\u0006\u0010:\u001a\u00020\tJ\u0010\u0010;\u001a\u00020\t2\u0006\u0010*\u001a\u00020+H\u0002J\u000e\u0010<\u001a\u00020(2\u0006\u0010*\u001a\u00020+J\u0018\u0010=\u001a\u00020(2\u0006\u0010>\u001a\u00020\u00042\u0006\u0010?\u001a\u00020\u0016H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010 \"\u0004\b%\u0010\"¨\u0006A"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchHandler;", "Landroid/os/Parcelable;", "()V", "in", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "mCheckingPosition", "Landroid/graphics/PointF;", "mD", "", "mEnableRotation", "", "mEnableTranslateX", "mEnableTranslateY", "mEnableZoom", "mLastEvent", "", "mMatrix", "Landroid/graphics/Matrix;", "mMaxPositionOffset", "mMid", "mMode", "", "mNewRot", "mOldDist", "mOldImagePosition", "mSavedMatrix", "mScale", "mScaleSavedMatrix", "mStart", "matrix", "getMatrix", "()Landroid/graphics/Matrix;", "setMatrix", "(Landroid/graphics/Matrix;)V", "scaleMatrix", "getScaleMatrix", "setScaleMatrix", "describeContents", "midPoint", "", "point", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "reset", "rotation", "setEnableRotation", "enableRotation", "setEnableTranslateX", "enableTranslateX", "setEnableTranslateY", "enableTranslateY", "setEnableZoom", "enableZoom", "setMatrices", "setMaxPositionOffset", "maxPositionOffset", "setScale", "scale", "spacing", "touch", "writeToParcel", "dest", "flags", "CREATOR", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_MultiTouchHandler implements Parcelable {
    private static final int NONE = 0;
    private PointF mCheckingPosition;
    private float mD;
    private boolean mEnableRotation;
    private boolean mEnableTranslateX;
    private boolean mEnableTranslateY;
    private boolean mEnableZoom;
    private float[] mLastEvent;
    private Matrix mMatrix;
    private float mMaxPositionOffset;
    private PointF mMid;
    private int mMode;
    private float mNewRot;
    private float mOldDist;
    private PointF mOldImagePosition;
    private Matrix mSavedMatrix;
    private float mScale;
    private Matrix mScaleSavedMatrix;
    private PointF mStart;
    private Matrix scaleMatrix;
    public static final CREATOR CREATOR = new CREATOR(null);
    private static final int DRAG = 1;
    private static final int ZOOM = 2;

    public  PG_MultiTouchHandler(Parcel parcel, DefaultConstructorMarker defaultConstructorMarker) {
        this(parcel);
    }

    @Override 
    public int describeContents() {
        return 0;
    }

    public final Matrix getScaleMatrix() {
        return this.scaleMatrix;
    }

    public final void setScaleMatrix(Matrix matrix) {
        Intrinsics.checkNotNullParameter(matrix, "<set-?>");
        this.scaleMatrix = matrix;
    }

    public final Matrix getMatrix() {
        return this.mMatrix;
    }

    public final void setMatrix(Matrix matrix) {
        Intrinsics.checkNotNullParameter(matrix, "matrix");
        this.mMatrix.set(matrix);
        this.mSavedMatrix.set(matrix);
        this.scaleMatrix.reset();
        this.mScaleSavedMatrix.reset();
    }

    public PG_MultiTouchHandler() {
        this.mMatrix = new Matrix();
        this.mSavedMatrix = new Matrix();
        this.mMode = NONE;
        this.mStart = new PointF();
        this.mMid = new PointF();
        this.mOldDist = 1.0f;
        this.mEnableZoom = true;
        this.mEnableTranslateX = true;
        this.mEnableTranslateY = true;
        this.mScale = 1.0f;
        this.scaleMatrix = new Matrix();
        this.mScaleSavedMatrix = new Matrix();
        this.mMaxPositionOffset = -1.0f;
        this.mOldImagePosition = new PointF(0.0f, 0.0f);
        this.mCheckingPosition = new PointF(0.0f, 0.0f);
    }

    public final void setMatrices(Matrix matrix, Matrix scaleMatrix) {
//        Intrinsics.checkNotNullParameter(matrix, "matrix");
//        Intrinsics.checkNotNullParameter(scaleMatrix, "scaleMatrix");
        this.mMatrix.set(matrix);
        this.mSavedMatrix.set(matrix);
        this.scaleMatrix.set(scaleMatrix);
        this.mScaleSavedMatrix.set(scaleMatrix);
    }

    public final void reset() {
        this.mMatrix.reset();
        this.mSavedMatrix.reset();
        this.mMode = NONE;
        this.mStart.set(0.0f, 0.0f);
        this.mMid.set(0.0f, 0.0f);
        this.mOldDist = 1.0f;
        this.mD = 0.0f;
        this.mNewRot = 0.0f;
        this.mLastEvent = null;
        this.mEnableRotation = false;
        this.scaleMatrix.reset();
        this.mScaleSavedMatrix.reset();
    }

    public final void setMaxPositionOffset(float f) {
        this.mMaxPositionOffset = f;
    }

    public final void touch(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        int action = event.getAction() & 255;
        if (action == 0) {
            this.mSavedMatrix.set(this.mMatrix);
            this.mScaleSavedMatrix.set(this.scaleMatrix);
            this.mStart.set(event.getX(), event.getY());
            this.mOldImagePosition.set(this.mCheckingPosition.x, this.mCheckingPosition.y);
            this.mMode = DRAG;
            this.mLastEvent = null;
            return;
        }
        if (action != 1) {
            if (action == 2) {
                int i = this.mMode;
                if (i == DRAG) {
                    this.mMatrix.set(this.mSavedMatrix);
                    this.scaleMatrix.set(this.mScaleSavedMatrix);
                    this.mCheckingPosition.set(this.mOldImagePosition.x, this.mOldImagePosition.y);
                    float x = event.getX() - this.mStart.x;
                    float y = event.getY() - this.mStart.y;
                    this.mCheckingPosition.x += x;
                    this.mCheckingPosition.y += y;
                    float f = 0.0f;
                    if (!this.mEnableTranslateX) {
                        if (this.mCheckingPosition.y > this.mMaxPositionOffset) {
                            float f2 = this.mCheckingPosition.y;
                            float f3 = this.mMaxPositionOffset;
                            y -= f2 - f3;
                            this.mCheckingPosition.y = f3;
                        } else if (this.mCheckingPosition.y < (-this.mMaxPositionOffset)) {
                            float f4 = this.mCheckingPosition.y;
                            float f5 = this.mMaxPositionOffset;
                            y -= f4 + f5;
                            this.mCheckingPosition.y = -f5;
                        }
                        x = 0.0f;
                    }
                    if (this.mEnableTranslateY) {
                        f = y;
                    } else if (this.mCheckingPosition.x > this.mMaxPositionOffset) {
                        float f6 = this.mCheckingPosition.x;
                        float f7 = this.mMaxPositionOffset;
                        x -= f6 - f7;
                        this.mCheckingPosition.x = f7;
                    } else if (this.mCheckingPosition.x < (-this.mMaxPositionOffset)) {
                        float f8 = this.mCheckingPosition.x;
                        float f9 = this.mMaxPositionOffset;
                        x -= f8 + f9;
                        this.mCheckingPosition.x = -f9;
                    }
                    this.mMatrix.postTranslate(x, f);
                    Matrix matrix = this.scaleMatrix;
                    float f10 = this.mScale;
                    matrix.postTranslate(x * f10, f * f10);
                    return;
                } else if (i == ZOOM && this.mEnableZoom) {
                    float spacing = spacing(event);
                    if (spacing > 10.0f) {
                        this.mMatrix.set(this.mSavedMatrix);
                        this.scaleMatrix.set(this.mScaleSavedMatrix);
                        float f11 = spacing / this.mOldDist;
                        this.mMatrix.postScale(f11, f11, this.mMid.x, this.mMid.y);
                        this.scaleMatrix.postScale(f11, f11, this.mMid.x * this.mScale, this.mMid.y * this.mScale);
                    }
                    if (this.mEnableRotation && this.mLastEvent != null && event.getPointerCount() == 2) {
                        this.mNewRot = rotation(event);
                        midPoint(this.mMid, event);
                        float f12 = this.mNewRot - this.mD;
                        this.mMatrix.postRotate(f12, this.mMid.x, this.mMid.y);
                        this.scaleMatrix.postRotate(f12, this.mMid.x * this.mScale, this.mMid.y * this.mScale);
                        return;
                    }
                    return;
                } else {
                    return;
                }
            } else if (action == 5) {
                float spacing2 = spacing(event);
                this.mOldDist = spacing2;
                if (spacing2 > 10.0f) {
                    this.mSavedMatrix.set(this.mMatrix);
                    this.mScaleSavedMatrix.set(this.scaleMatrix);
                    midPoint(this.mMid, event);
                    this.mMode = ZOOM;
                }
                float[] fArr = new float[4];
                this.mLastEvent = fArr;
                Intrinsics.checkNotNull(fArr);
                fArr[0] = event.getX(0);
                float[] fArr2 = this.mLastEvent;
                Intrinsics.checkNotNull(fArr2);
                fArr2[1] = event.getX(1);
                float[] fArr3 = this.mLastEvent;
                Intrinsics.checkNotNull(fArr3);
                fArr3[2] = event.getY(0);
                float[] fArr4 = this.mLastEvent;
                Intrinsics.checkNotNull(fArr4);
                fArr4[3] = event.getY(1);
                this.mD = rotation(event);
                return;
            } else if (action != 6) {
                return;
            }
        }
        this.mMode = NONE;
        this.mLastEvent = null;
    }

    public final void setScale(float f) {
        this.mScale = f;
    }

    public final void setEnableRotation(boolean z) {
        this.mEnableRotation = z;
    }

    public final void setEnableZoom(boolean z) {
        this.mEnableZoom = z;
    }

    public final void setEnableTranslateX(boolean z) {
        this.mEnableTranslateX = z;
    }

    public final void setEnableTranslateY(boolean z) {
        this.mEnableTranslateY = z;
    }

    private final float spacing(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }

    private final void midPoint(PointF pointF, MotionEvent motionEvent) {
        float y = motionEvent.getY(0) + motionEvent.getY(1);
        float f = 2;
        pointF.set((motionEvent.getX(0) + motionEvent.getX(1)) / f, y / f);
    }

    private final float rotation(MotionEvent motionEvent) {
        return (float) Math.toDegrees(Math.atan2(motionEvent.getY(0) - motionEvent.getY(1), motionEvent.getX(0) - motionEvent.getX(1)));
    }

    private PG_MultiTouchHandler(Parcel parcel) {
        this.mMatrix = new Matrix();
        this.mSavedMatrix = new Matrix();
        this.mMode = NONE;
        this.mStart = new PointF();
        this.mMid = new PointF();
        this.mOldDist = 1.0f;
        this.mEnableZoom = true;
        this.mEnableTranslateX = true;
        this.mEnableTranslateY = true;
        this.mScale = 1.0f;
        this.scaleMatrix = new Matrix();
        this.mScaleSavedMatrix = new Matrix();
        this.mMaxPositionOffset = -1.0f;
        this.mOldImagePosition = new PointF(0.0f, 0.0f);
        this.mCheckingPosition = new PointF(0.0f, 0.0f);
        float[] fArr = new float[9];
        parcel.readFloatArray(fArr);
        Matrix matrix = new Matrix();
        this.mMatrix = matrix;
        matrix.setValues(fArr);
        float[] fArr2 = new float[9];
        parcel.readFloatArray(fArr2);
        Matrix matrix2 = new Matrix();
        this.mSavedMatrix = matrix2;
        matrix2.setValues(fArr2);
        this.mMode = parcel.readInt();
        Parcelable readParcelable = parcel.readParcelable(PointF.class.getClassLoader());
        Intrinsics.checkNotNull(readParcelable);
        this.mStart = (PointF) readParcelable;
        Parcelable readParcelable2 = parcel.readParcelable(PointF.class.getClassLoader());
        Intrinsics.checkNotNull(readParcelable2);
        this.mMid = (PointF) readParcelable2;
        this.mOldDist = parcel.readFloat();
        this.mD = parcel.readFloat();
        this.mNewRot = parcel.readFloat();
        boolean[] zArr = new boolean[4];
        parcel.readBooleanArray(zArr);
        this.mEnableRotation = zArr[0];
        this.mEnableZoom = zArr[1];
        this.mEnableTranslateX = zArr[2];
        this.mEnableTranslateY = zArr[3];
        this.mScale = parcel.readFloat();
        float[] fArr3 = new float[9];
        parcel.readFloatArray(fArr3);
        Matrix matrix3 = new Matrix();
        this.scaleMatrix = matrix3;
        matrix3.setValues(fArr3);
        float[] fArr4 = new float[9];
        parcel.readFloatArray(fArr4);
        Matrix matrix4 = new Matrix();
        this.mScaleSavedMatrix = matrix4;
        matrix4.setValues(fArr4);
        this.mMaxPositionOffset = parcel.readFloat();
        Parcelable readParcelable3 = parcel.readParcelable(PointF.class.getClassLoader());
        Intrinsics.checkNotNull(readParcelable3);
        this.mOldImagePosition = (PointF) readParcelable3;
        Parcelable readParcelable4 = parcel.readParcelable(PointF.class.getClassLoader());
        Intrinsics.checkNotNull(readParcelable4);
        this.mCheckingPosition = (PointF) readParcelable4;
    }

    @Override 
    public void writeToParcel(Parcel dest, int i) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        float[] fArr = new float[9];
        this.mMatrix.getValues(fArr);
        dest.writeFloatArray(fArr);
        float[] fArr2 = new float[9];
        this.mSavedMatrix.getValues(fArr2);
        dest.writeFloatArray(fArr2);
        dest.writeInt(this.mMode);
        dest.writeParcelable(this.mStart, i);
        dest.writeParcelable(this.mMid, i);
        dest.writeFloat(this.mOldDist);
        dest.writeFloat(this.mD);
        dest.writeFloat(this.mNewRot);
        dest.writeBooleanArray(new boolean[]{this.mEnableRotation, this.mEnableZoom, this.mEnableTranslateX, this.mEnableTranslateY});
        dest.writeFloat(this.mScale);
        float[] fArr3 = new float[9];
        this.scaleMatrix.getValues(fArr3);
        dest.writeFloatArray(fArr3);
        float[] fArr4 = new float[9];
        this.mScaleSavedMatrix.getValues(fArr4);
        dest.writeFloatArray(fArr4);
        dest.writeFloat(this.mMaxPositionOffset);
        dest.writeParcelable(this.mOldImagePosition, i);
        dest.writeParcelable(this.mCheckingPosition, i);
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u001d\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\f2\u0006\u0010\r\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchHandler$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchHandler;", "()V", "DRAG", "", "NONE", "ZOOM", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "(I)[Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchHandler;", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public static final class CREATOR implements Parcelable.Creator<PG_MultiTouchHandler> {
        public  CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }

        @Override 
        public PG_MultiTouchHandler createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new PG_MultiTouchHandler(parcel, null);
        }

        @Override 
        public PG_MultiTouchHandler[] newArray(int i) {
            return new PG_MultiTouchHandler[i];
        }
    }
}
