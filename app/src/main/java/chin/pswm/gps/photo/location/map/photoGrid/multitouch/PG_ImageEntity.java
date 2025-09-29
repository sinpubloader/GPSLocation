package chin.pswm.gps.photo.location.map.photoGrid.multitouch;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import chin.pswm.gps.photo.location.map.photoGrid.util.PG_ImageDecoder;
import chin.pswm.gps.photo.location.map_debug.R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
@SuppressWarnings("all")
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\u0018\u0000 N2\u00020\u0001:\u0001NB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tB\u0017\b\u0016\u0012\u0006\u0010\n\u001a\u00020\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u000bB\u000f\b\u0014\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0012\u00101\u001a\u0004\u0018\u00010\u00152\u0006\u00102\u001a\u000203H\u0004J\b\u00104\u001a\u00020\u0003H\u0016J\u0010\u00105\u001a\u0002062\u0006\u00107\u001a\u000208H\u0016J\u0016\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020%J\u0018\u0010:\u001a\u0002062\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020%H\u0002J\u0010\u0010;\u001a\u0002062\u0006\u00102\u001a\u000203H\u0016J \u0010;\u001a\u0002062\u0006\u00102\u001a\u0002032\u0006\u0010<\u001a\u00020%2\u0006\u0010=\u001a\u00020%H\u0016J&\u0010;\u001a\u0002062\u0006\u00102\u001a\u0002032\u0006\u0010<\u001a\u00020%2\u0006\u0010=\u001a\u00020%2\u0006\u0010>\u001a\u00020%J\u0010\u0010?\u001a\u0002062\u0006\u0010\u0004\u001a\u00020\u0005H\u0004J\u0010\u0010@\u001a\u0002062\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010A\u001a\u000206H\u0004J\u000e\u0010B\u001a\u0002062\u0006\u0010C\u001a\u00020%J\u000e\u0010D\u001a\u0002062\u0006\u0010:\u001a\u00020\u001dJ\u0016\u0010E\u001a\u0002062\u0006\u00102\u001a\u0002032\u0006\u0010\u0019\u001a\u00020\bJ\u000e\u0010F\u001a\u0002062\u0006\u0010G\u001a\u00020,J\u000e\u0010H\u001a\u0002062\u0006\u0010I\u001a\u00020\u0003J\b\u0010J\u001a\u000206H\u0016J\u0018\u0010K\u001a\u0002062\u0006\u0010L\u001a\u00020\r2\u0006\u0010M\u001a\u00020\u0003H\u0016R\u001a\u0010\u000f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\"\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\"\u0010\u0019\u001a\u0004\u0018\u00010\b2\b\u0010\u0014\u001a\u0004\u0018\u00010\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001e\"\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\u001d8F¢\u0006\u0006\u001a\u0004\b!\u0010\u001eR\u001a\u0010\"\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 R\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0011¨\u0006O"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_ImageEntity;", "Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchEntity;", "resourceId", "", "res", "Landroid/content/res/Resources;", "(ILandroid/content/res/Resources;)V", "image", "Landroid/net/Uri;", "(Landroid/net/Uri;Landroid/content/res/Resources;)V", "e", "(Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_ImageEntity;Landroid/content/res/Resources;)V", "in", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "borderColor", "getBorderColor", "()I", "setBorderColor", "(I)V", "<set-?>", "Landroid/graphics/drawable/Drawable;", "drawable", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "imageUri", "getImageUri", "()Landroid/net/Uri;", "isDrawImageBorder", "", "()Z", "setDrawImageBorder", "(Z)V", "isNull", "isSticker", "setSticker", "mBorderSize", "", "mBoundRect", "Landroid/graphics/RectF;", "mDrawShadow", "mGradientDrawable", "Landroid/graphics/drawable/GradientDrawable;", "mInitScaleFactor", "", "mPaint", "Landroid/graphics/Paint;", "mShadowSize", "getResourceId", "createDrawableFromPrimaryInfo", "context", "Landroid/content/Context;", "describeContents", "draw", "", "canvas", "Landroid/graphics/Canvas;", "scale", "drawShadow", "load", "startMidX", "startMidY", "startAngle", "loadConfigs", "readFromParcel", "resetPrimaryInfo", "setBorderSize", "borderSize", "setDrawShadow", "setImageUri", "setInitScaleFactor", "initScaleFactor", "setShadowSize", "shadowSize", "unload", "writeToParcel", "dest", "flags", "CREATOR", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_ImageEntity extends PG_MultiTouchEntity {
    public static final CREATOR CREATOR = new CREATOR(null);
    private int borderColor;
    private transient Drawable drawable;
    private Uri imageUri;
    private boolean isDrawImageBorder;
    private boolean isSticker;
    private float mBorderSize;
    private RectF mBoundRect;
    private boolean mDrawShadow;
    private final GradientDrawable mGradientDrawable;
    private double mInitScaleFactor;
    private final Paint mPaint;
    private int mShadowSize;
    private int resourceId;

    @Override
    public int describeContents() {
        return 0;
    }

    public final Drawable getDrawable() {
        return this.drawable;
    }

    public final Uri getImageUri() {
        return this.imageUri;
    }

    public final int getResourceId() {
        return this.resourceId;
    }

    public final boolean isDrawImageBorder() {
        return this.isDrawImageBorder;
    }

    public final void setDrawImageBorder(boolean z) {
        this.isDrawImageBorder = z;
    }

    public final int getBorderColor() {
        return this.borderColor;
    }

    public final void setBorderColor(int i) {
        this.borderColor = i;
    }

    public final boolean isSticker() {
        return this.isSticker;
    }

    public final void setSticker(boolean z) {
        this.isSticker = z;
    }

    public final boolean isNull() {
        return this.imageUri == null && this.resourceId <= 0;
    }

    
    public PG_ImageEntity(int i, Resources res) {
        super(res);
        Intrinsics.checkNotNullParameter(res, "res");
        this.mInitScaleFactor = 0.25d;
        this.resourceId = -1;
        this.borderColor = -16711936;
        this.mBorderSize = 3.0f;
        this.mBoundRect = new RectF();
        this.mPaint = new Paint(1);
        this.isSticker = true;
        this.mGradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{0, -7829368});
        this.resourceId = i;
        this.imageUri = null;
        loadConfigs(res);
    }

    
    public PG_ImageEntity(Uri image, Resources res) {
        super(res);
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(res, "res");
        this.mInitScaleFactor = 0.25d;
        this.resourceId = -1;
        this.borderColor = -16711936;
        this.mBorderSize = 3.0f;
        this.mBoundRect = new RectF();
        this.mPaint = new Paint(1);
        this.isSticker = true;
        this.mGradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{0, -7829368});
        this.imageUri = image;
        this.resourceId = -1;
        loadConfigs(res);
    }

    
    public PG_ImageEntity(PG_ImageEntity e, Resources res) {
        super(res);
        Intrinsics.checkNotNullParameter(e, "e");
        Intrinsics.checkNotNullParameter(res, "res");
        this.mInitScaleFactor = 0.25d;
        this.resourceId = -1;
        this.borderColor = -16711936;
        this.mBorderSize = 3.0f;
        this.mBoundRect = new RectF();
        this.mPaint = new Paint(1);
        this.isSticker = true;
        this.mGradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{0, -7829368});
        this.drawable = e.drawable;
        this.resourceId = e.resourceId;
        setScaleX(e.getScaleX());
        setScaleY(e.getScaleY());
        setCenterX(e.getCenterX());
        setCenterY(e.getCenterY());
        setAngle(e.getAngle());
        this.imageUri = e.imageUri;
        loadConfigs(res);
    }

    protected final void loadConfigs(Resources res) {
        Intrinsics.checkNotNullParameter(res, "res");
        this.mBorderSize = res.getDimension(R.dimen.image_border_size);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mBorderSize);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
    }

    public final void setShadowSize(int i) {
        this.mShadowSize = i;
    }

    public final void setDrawShadow(boolean z) {
        this.mDrawShadow = z;
    }

    public final void setInitScaleFactor(double d) {
        this.mInitScaleFactor = d;
    }

    public final void setBorderSize(float f) {
        this.mBorderSize = f;
        this.mPaint.setStrokeWidth(f);
    }

    public final void setImageUri(Context context, Uri imageUri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imageUri, "imageUri");
        unload();
        this.imageUri = imageUri;
        load(context);
    }

    @Override
    public void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        draw(canvas, 1.0f);
    }

    public final void draw(Canvas canvas, float f) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.save();
        Drawable drawable = this.drawable;
        if (drawable == null) {
            return;
        }
        if (drawable instanceof BitmapDrawable) {
            Intrinsics.checkNotNull(drawable, "null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap == null || bitmap.isRecycled()) {
                return;
            }
        }
        float f2 = 2;
        float maxX = ((getMaxX() + getMinX()) * f) / f2;
        float maxY = ((getMaxY() + getMinY()) * f) / f2;
        Drawable drawable2 = this.drawable;
        Intrinsics.checkNotNull(drawable2);
        drawable2.setBounds((int) (getMinX() * f), (int) (getMinY() * f), (int) (getMaxX() * f), (int) (getMaxY() * f));
        canvas.translate(maxX, maxY);
        canvas.rotate((getAngle() * 180.0f) / 3.1415927f);
        canvas.translate(-maxX, -maxY);
        if (this.mDrawShadow && !this.isSticker && this.mShadowSize > 1) {
            drawShadow(canvas, f);
        }
        Drawable drawable3 = this.drawable;
        Intrinsics.checkNotNull(drawable3);
        drawable3.draw(canvas);
        canvas.restore();
    }

    private final void drawShadow(Canvas canvas, float f) {
        this.mGradientDrawable.setBounds((int) ((getMinX() + this.mShadowSize) * f), (int) ((getMinY() + this.mShadowSize) * f), (int) ((getMaxX() + this.mShadowSize) * f), (int) (f * (getMaxY() + this.mShadowSize)));
        this.mGradientDrawable.setCornerRadius(5.0f);
        this.mGradientDrawable.draw(canvas);
    }

    @Override
    public void unload() {
        Drawable drawable = this.drawable;
        if ((drawable instanceof BitmapDrawable) && drawable != null) {
            Intrinsics.checkNotNull(drawable, "null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
        this.drawable = null;
    }

    @Override
    public void load(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Resources res = context.getResources();
        Intrinsics.checkNotNullExpressionValue(res, "res");
        getMetrics(res);
        if (this.drawable == null) {
            this.drawable = createDrawableFromPrimaryInfo(context);
        }
        Drawable drawable = this.drawable;
        if (drawable == null) {
            if (this.imageUri != null) {
                resetPrimaryInfo();
                return;
            }
            return;
        }
        Intrinsics.checkNotNull(drawable);
        setWidth(drawable.getIntrinsicWidth());
        Drawable drawable2 = this.drawable;
        Intrinsics.checkNotNull(drawable2);
        setHeight(drawable2.getIntrinsicHeight());
        setPos(getCenterX(), getCenterY(), getScaleX(), getScaleY(), getAngle());
    }

    public final void load(Context context, float f, float f2, float f3) {
        float scaleX;
        float f4;
        float f5;
        float scaleY;
        Intrinsics.checkNotNullParameter(context, "context");
        Resources res = context.getResources();
        Intrinsics.checkNotNullExpressionValue(res, "res");
        getMetrics(res);
        setMStartMidX(f);
        setMStartMidY(f2);
        if (this.drawable == null) {
            this.drawable = createDrawableFromPrimaryInfo(context);
        }
        Drawable drawable = this.drawable;
        if (drawable == null) {
            if (this.imageUri != null) {
                resetPrimaryInfo();
                return;
            }
            return;
        }
        Intrinsics.checkNotNull(drawable);
        setWidth(drawable.getIntrinsicWidth());
        Drawable drawable2 = this.drawable;
        Intrinsics.checkNotNull(drawable2);
        setHeight(drawable2.getIntrinsicHeight());
        if (getMFirstLoad()) {
            setAngle(f3);
            setMFirstLoad(false);
            scaleX = (float) ((Math.min(getMDisplayWidth(), getMDisplayHeight()) / Math.max(getWidth(), getHeight())) * this.mInitScaleFactor);
            scaleY = scaleX;
            f4 = f;
            f5 = f2;
        } else {
            float centerX = getCenterX();
            float centerY = getCenterY();
            scaleX = getScaleX();
            f4 = centerX;
            f5 = centerY;
            scaleY = getScaleY();
        }
        setPos(f4, f5, scaleX, scaleY, getAngle());
    }

    @Override
    public void load(Context context, float f, float f2) {
        Intrinsics.checkNotNullParameter(context, "context");
        load(context, f, f2, 0.0f);
    }

    protected final Drawable createDrawableFromPrimaryInfo(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Resources resources = context.getResources();
        if (this.imageUri != null) {
            PG_ImageDecoder pG_ImageDecoder = PG_ImageDecoder.INSTANCE;
            Uri uri = this.imageUri;
            Intrinsics.checkNotNull(uri);
            return pG_ImageDecoder.decodeUriToDrawable(context, uri);
        }
        int i = this.resourceId;
        if (i > 0) {
            return resources.getDrawable(i);
        }
        return null;
    }

    protected final void resetPrimaryInfo() {
        this.imageUri = null;
        this.resourceId = -1;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        super.writeToParcel(dest, i);
        dest.writeDouble(this.mInitScaleFactor);
        dest.writeParcelable(this.imageUri, i);
        dest.writeInt(this.resourceId);
        dest.writeBooleanArray(new boolean[]{this.isDrawImageBorder, this.isSticker});
        dest.writeInt(this.borderColor);
        dest.writeFloat(this.mBorderSize);
        dest.writeParcelable(this.mBoundRect, i);
    }

    @Override
    public void readFromParcel(Parcel in) {
        Intrinsics.checkNotNullParameter(in, "in");
        super.readFromParcel(in);
        this.mInitScaleFactor = in.readDouble();
        this.imageUri = (Uri) in.readParcelable(Uri.class.getClassLoader());
        this.resourceId = in.readInt();
        boolean[] zArr = new boolean[2];
        in.readBooleanArray(zArr);
        this.isDrawImageBorder = zArr[0];
        this.isSticker = zArr[1];
        this.borderColor = in.readInt();
        this.mBorderSize = in.readFloat();
        this.mBoundRect = (RectF) in.readParcelable(RectF.class.getClassLoader());
    }

    protected PG_ImageEntity(Parcel in) {
        Intrinsics.checkNotNullParameter(in, "in");
        this.mInitScaleFactor = 0.25d;
        this.resourceId = -1;
        this.borderColor = -16711936;
        this.mBorderSize = 3.0f;
        this.mBoundRect = new RectF();
        this.mPaint = new Paint(1);
        this.isSticker = true;
        this.mGradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{0, -7829368});
        readFromParcel(in);
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_ImageEntity$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_ImageEntity;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_ImageEntity;", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public static final class CREATOR implements Parcelable.Creator<PG_ImageEntity> {
        public  CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }

        @Override 
        public PG_ImageEntity createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new PG_ImageEntity(parcel);
        }

        @Override 
        public PG_ImageEntity[] newArray(int i) {
            return new PG_ImageEntity[i];
        }
    }
}
