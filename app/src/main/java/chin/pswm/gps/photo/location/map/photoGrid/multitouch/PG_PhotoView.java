package chin.pswm.gps.photo.location.map.photoGrid.multitouch;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.core.view.InputDeviceCompat;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map.photoGrid.frame.PG_FrameTouch;
import chin.pswm.gps.photo.location.map.photoGrid.frame.PG_OnFrameTouchListener;
import chin.pswm.gps.photo.location.map.photoGrid.multitouch.PG_MultiTouchController;
import chin.pswm.gps.photo.location.map.photoGrid.util.PG_ImageDecoder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
@SuppressWarnings("all")

@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0019\u0018\u0000 a2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001aB\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0019\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u000e\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u0003J\b\u00105\u001a\u000203H\u0002J\u0006\u00106\u001a\u000203J\u0006\u00107\u001a\u000203J\u0010\u00108\u001a\u0002032\u0006\u00109\u001a\u00020:H\u0002J\u0012\u0010;\u001a\u0004\u0018\u00010\u00032\u0006\u0010<\u001a\u00020\u0010H\u0016J\u0010\u0010=\u001a\u0004\u0018\u00010>2\u0006\u0010?\u001a\u00020\"J\u0018\u0010@\u001a\u0002032\u0006\u0010A\u001a\u00020\u00032\u0006\u0010B\u001a\u00020CH\u0016J\u0010\u0010D\u001a\u0002032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u000e\u0010E\u001a\u0002032\u0006\u0010\u0004\u001a\u00020\u0005J\u0010\u0010F\u001a\u0002032\u0006\u00109\u001a\u00020:H\u0014J\u0010\u0010G\u001a\u00020(2\u0006\u0010H\u001a\u00020IH\u0016J\u0018\u0010J\u001a\u00020(2\u0006\u0010<\u001a\u00020\u00102\u0006\u0010A\u001a\u00020\u0003H\u0016J\u000e\u0010K\u001a\u0002032\u0006\u00104\u001a\u00020\u0003J\u0018\u0010L\u001a\u0002032\u0006\u0010A\u001a\u00020\u00032\u0006\u0010M\u001a\u00020\u0010H\u0016J\u000e\u0010N\u001a\u0002032\u0006\u0010O\u001a\u00020\u000bJ\u000e\u0010P\u001a\u0002032\u0006\u0010Q\u001a\u00020\"J\u000e\u0010R\u001a\u0002032\u0006\u0010S\u001a\u00020(J\u000e\u0010T\u001a\u0002032\u0006\u0010U\u001a\u00020(J\u000e\u0010V\u001a\u0002032\u0006\u0010W\u001a\u00020\u001eJ\u000e\u0010X\u001a\u0002032\u0006\u0010Y\u001a\u00020\u001bJ\u000e\u0010Z\u001a\u0002032\u0006\u0010.\u001a\u00020-J \u0010[\u001a\u00020(2\u0006\u0010A\u001a\u00020\u00032\u0006\u0010\\\u001a\u00020C2\u0006\u0010M\u001a\u00020\u0010H\u0016J\u000e\u0010]\u001a\u0002032\u0006\u0010^\u001a\u00020\u000bJ\u0006\u0010_\u001a\u000203J\u0006\u0010`\u001a\u000203R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\"\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010.\u001a\u0004\u0018\u00010-2\b\u0010,\u001a\u0004\u0018\u00010-@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u000e\u00101\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000¨\u0006b"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_PhotoView;", "Landroid/view/View;", "Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$MultiTouchObjectCanvas;", "Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchEntity;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "PGMultiTouchController", "Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController;", "currTouchPoint", "Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$PointInfo;", "displayHeight", "displayWidth", "hight", "imageEntities", "Ljava/util/ArrayList;", "getImageEntities", "()Ljava/util/ArrayList;", "setImageEntities", "(Ljava/util/ArrayList;)V", "mClickListener", "Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_OnDoubleClickListener;", "mCurrentSelectedObject", "mFrameTouchListener", "Lchin/pswm/gps/photo/location/map/photoGrid/frame/PG_OnFrameTouchListener;", "mLinePaintTouchPointCircle", "Landroid/graphics/Paint;", "mOldX", "", "mOldY", "mSelectedCount", "mSelectedTime", "", "mShowDebugInfo", "", "mTouchAreaInterval", "mTouchedObject", "mUIMode", "<set-?>", "Landroid/net/Uri;", "photoBackgroundUri", "getPhotoBackgroundUri", "()Landroid/net/Uri;", "wdth", "addImageEntity", "", "entity", "cleanImages", "clearAllImageEntities", "destroyBackground", "drawMultitouchDebugMarks", "canvas", "Landroid/graphics/Canvas;", "getDraggableObjectAtPoint", "pt", "getImage", "Landroid/graphics/Bitmap;", "outputScale", "getPositionAndScale", "img", "objPosAndScaleOut", "Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$PositionAndScale;", "init", "loadImages", "onDraw", "onTouchEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "pointInObjectGrabArea", "removeImageEntity", "selectObject", "touchPoint", "setBorderColor", "color", "setBorderSize", "borderSize", "setDrawImageBound", "drawImageBorder", "setDrawShadow", "drawShadow", "setFrameTouchListener", "frameTouchListener", "setOnDoubleClickListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setPhotoBackground", "setPositionAndScale", "newImgPosAndScale", "setShadowSize", "shadowSize", "trackballClicked", "unloadImages", "Companion", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_PhotoView extends View implements PG_MultiTouchController.MultiTouchObjectCanvas<PG_MultiTouchEntity> {
    private final PG_MultiTouchController<PG_MultiTouchEntity> PGMultiTouchController;
    private final PG_MultiTouchController.PointInfo currTouchPoint;
    private int displayHeight;
    private int displayWidth;
    private int hight;
    private ArrayList<PG_MultiTouchEntity> imageEntities;
    private PG_OnDoubleClickListener mClickListener;
    private PG_MultiTouchEntity mCurrentSelectedObject;
    private PG_OnFrameTouchListener mFrameTouchListener;
    private final Paint mLinePaintTouchPointCircle;
    private float mOldX;
    private float mOldY;
    private int mSelectedCount;
    private long mSelectedTime;
    private  boolean mShowDebugInfo;
    private float mTouchAreaInterval;
    private PG_MultiTouchEntity mTouchedObject;
    private int mUIMode;
    private Uri photoBackgroundUri;
    private  int wdth;
    public static final Companion Companion = new Companion(null);
    private static final long DOUBLE_CLICK_TIME_INTERVAL = 700;
    private static final int UI_MODE_ROTATE = 1;
    private static final int UI_MODE_ANISOTROPIC_SCALE = 2;
    private static final float SCREEN_MARGIN = 100.0f;

    @Override
    public boolean pointInObjectGrabArea(PG_MultiTouchController.PointInfo pt, PG_MultiTouchEntity img) {
        Intrinsics.checkNotNullParameter(pt, "pt");
        Intrinsics.checkNotNullParameter(img, "img");
        return false;
    }

    
    public PG_PhotoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.imageEntities = new ArrayList<>();
        this.PGMultiTouchController = new PG_MultiTouchController<>(this, false, 2, null);
        this.currTouchPoint = new PG_MultiTouchController.PointInfo();
        this.mUIMode = UI_MODE_ROTATE;
        this.mLinePaintTouchPointCircle = new Paint();
        this.mSelectedTime = System.currentTimeMillis();
        this.mTouchAreaInterval = 10.0f;
        init(context);
    }

    public final ArrayList<PG_MultiTouchEntity> getImageEntities() {
        return this.imageEntities;
    }

    public final void setImageEntities(ArrayList<PG_MultiTouchEntity> arrayList) {
        this.imageEntities = arrayList;
    }

    public final Uri getPhotoBackgroundUri() {
        return this.photoBackgroundUri;
    }


    public PG_PhotoView(Context context) {
        this(context, null);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }


    public PG_PhotoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    private final void init(Context context) {
        int min;
        int max;
        Resources resources = context.getResources();
        this.mLinePaintTouchPointCircle.setColor(InputDeviceCompat.SOURCE_ANY);
        this.mLinePaintTouchPointCircle.setStrokeWidth(5.0f);
        this.mLinePaintTouchPointCircle.setStyle(Paint.Style.STROKE);
        this.mLinePaintTouchPointCircle.setAntiAlias(true);
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if (resources.getConfiguration().orientation == 2) {
            min = Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
        } else {
            min = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        this.displayWidth = min;
        if (resources.getConfiguration().orientation == 2) {
            max = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
        } else {
            max = Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        this.displayHeight = max;
        this.mTouchAreaInterval = resources.getDimension(R.dimen.touch_area_interval);
    }

    public final void loadImages(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ArrayList<PG_MultiTouchEntity> arrayList = this.imageEntities;
        if (arrayList == null) {
            return;
        }
        Intrinsics.checkNotNull(arrayList);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ArrayList<PG_MultiTouchEntity> arrayList2 = this.imageEntities;
            Intrinsics.checkNotNull(arrayList2);
            arrayList2.get(i).load(context);
        }
        cleanImages();
    }

    private final void cleanImages() {
        if (this.imageEntities == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList<PG_MultiTouchEntity> arrayList2 = this.imageEntities;
        Intrinsics.checkNotNull(arrayList2);
        Iterator<PG_MultiTouchEntity> it = arrayList2.iterator();
        while (it.hasNext()) {
            PG_MultiTouchEntity next = it.next();
            Intrinsics.checkNotNull(next, "null cannot be cast to non-null type chin.pswm.gps.photo.location.map.photoGrid.multitouch.PG_ImageEntity");
            if (!((PG_ImageEntity) next).isNull()) {
                arrayList.add(next);
            }
        }
        ArrayList<PG_MultiTouchEntity> arrayList3 = this.imageEntities;
        Intrinsics.checkNotNull(arrayList3);
        arrayList3.clear();
        ArrayList<PG_MultiTouchEntity> arrayList4 = this.imageEntities;
        Intrinsics.checkNotNull(arrayList4);
        arrayList4.addAll(arrayList);
    }

    public final void addImageEntity(PG_MultiTouchEntity entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        ArrayList<PG_MultiTouchEntity> arrayList = this.imageEntities;
        if (arrayList == null) {
            return;
        }
        Intrinsics.checkNotNull(arrayList);
        if (arrayList.size() > 0) {
            ArrayList<PG_MultiTouchEntity> arrayList2 = this.imageEntities;
            Intrinsics.checkNotNull(arrayList2);
            if ((arrayList2.get(0) instanceof PG_ImageEntity) && (entity instanceof PG_ImageEntity)) {
                ArrayList<PG_MultiTouchEntity> arrayList3 = this.imageEntities;
                Intrinsics.checkNotNull(arrayList3);
                PG_MultiTouchEntity pG_MultiTouchEntity = arrayList3.get(0);
                Intrinsics.checkNotNull(pG_MultiTouchEntity, "null cannot be cast to non-null type chin.pswm.gps.photo.location.map.photoGrid.multitouch.PG_ImageEntity");
                PG_ImageEntity pG_ImageEntity = (PG_ImageEntity) pG_MultiTouchEntity;
                PG_ImageEntity pG_ImageEntity2 = (PG_ImageEntity) entity;
                pG_ImageEntity2.setBorderColor(pG_ImageEntity.getBorderColor());
                pG_ImageEntity2.setDrawImageBorder(pG_ImageEntity.isDrawImageBorder());
            }
        }
        ArrayList<PG_MultiTouchEntity> arrayList4 = this.imageEntities;
        Intrinsics.checkNotNull(arrayList4);
        arrayList4.add(entity);
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        entity.load(context, (getWidth() - entity.getWidth()) / 2, (getHeight() - entity.getHeight()) / 2);
        invalidate();
    }

    public final void clearAllImageEntities() {
        if (this.imageEntities == null) {
            return;
        }
        unloadImages();
        ArrayList<PG_MultiTouchEntity> arrayList = this.imageEntities;
        Intrinsics.checkNotNull(arrayList);
        arrayList.clear();
        invalidate();
    }

    public final void removeImageEntity(PG_MultiTouchEntity entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        ArrayList<PG_MultiTouchEntity> arrayList = this.imageEntities;
        if (arrayList == null) {
            return;
        }
        Intrinsics.checkNotNull(arrayList);
        arrayList.remove(entity);
        invalidate();
    }

    public final void setOnDoubleClickListener(PG_OnDoubleClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mClickListener = listener;
    }

    public final void setFrameTouchListener(PG_OnFrameTouchListener frameTouchListener) {
        Intrinsics.checkNotNullParameter(frameTouchListener, "frameTouchListener");
        this.mFrameTouchListener = frameTouchListener;
    }

    public final void setBorderColor(int i) {
        ArrayList<PG_MultiTouchEntity> arrayList = this.imageEntities;
        if (arrayList == null) {
            return;
        }
        Intrinsics.checkNotNull(arrayList);
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList<PG_MultiTouchEntity> arrayList2 = this.imageEntities;
            Intrinsics.checkNotNull(arrayList2);
            if (arrayList2.get(i2) instanceof PG_ImageEntity) {
                ArrayList<PG_MultiTouchEntity> arrayList3 = this.imageEntities;
                Intrinsics.checkNotNull(arrayList3);
                PG_MultiTouchEntity pG_MultiTouchEntity = arrayList3.get(i2);
                Intrinsics.checkNotNull(pG_MultiTouchEntity, "null cannot be cast to non-null type chin.pswm.gps.photo.location.map.photoGrid.multitouch.PG_ImageEntity");
                ((PG_ImageEntity) pG_MultiTouchEntity).setBorderColor(i);
            }
        }
        invalidate();
    }

    public final void setBorderSize(float f) {
        ArrayList<PG_MultiTouchEntity> arrayList = this.imageEntities;
        if (arrayList == null) {
            return;
        }
        Intrinsics.checkNotNull(arrayList);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ArrayList<PG_MultiTouchEntity> arrayList2 = this.imageEntities;
            Intrinsics.checkNotNull(arrayList2);
            if (arrayList2.get(i) instanceof PG_ImageEntity) {
                ArrayList<PG_MultiTouchEntity> arrayList3 = this.imageEntities;
                Intrinsics.checkNotNull(arrayList3);
                PG_MultiTouchEntity pG_MultiTouchEntity = arrayList3.get(i);
                Intrinsics.checkNotNull(pG_MultiTouchEntity, "null cannot be cast to non-null type chin.pswm.gps.photo.location.map.photoGrid.multitouch.PG_ImageEntity");
                ((PG_ImageEntity) pG_MultiTouchEntity).setBorderSize(f);
            }
        }
        invalidate();
    }

    public final void setDrawImageBound(boolean z) {
        ArrayList<PG_MultiTouchEntity> arrayList = this.imageEntities;
        if (arrayList == null) {
            return;
        }
        Intrinsics.checkNotNull(arrayList);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ArrayList<PG_MultiTouchEntity> arrayList2 = this.imageEntities;
            Intrinsics.checkNotNull(arrayList2);
            if (arrayList2.get(i) instanceof PG_ImageEntity) {
                ArrayList<PG_MultiTouchEntity> arrayList3 = this.imageEntities;
                Intrinsics.checkNotNull(arrayList3);
                PG_MultiTouchEntity pG_MultiTouchEntity = arrayList3.get(i);
                Intrinsics.checkNotNull(pG_MultiTouchEntity, "null cannot be cast to non-null type chin.pswm.gps.photo.location.map.photoGrid.multitouch.PG_ImageEntity");
                ((PG_ImageEntity) pG_MultiTouchEntity).setDrawImageBorder(z);
            }
        }
        invalidate();
    }

    public final void setDrawShadow(boolean z) {
        ArrayList<PG_MultiTouchEntity> arrayList = this.imageEntities;
        if (arrayList == null) {
            return;
        }
        Intrinsics.checkNotNull(arrayList);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ArrayList<PG_MultiTouchEntity> arrayList2 = this.imageEntities;
            Intrinsics.checkNotNull(arrayList2);
            if (arrayList2.get(i) instanceof PG_ImageEntity) {
                ArrayList<PG_MultiTouchEntity> arrayList3 = this.imageEntities;
                Intrinsics.checkNotNull(arrayList3);
                PG_MultiTouchEntity pG_MultiTouchEntity = arrayList3.get(i);
                Intrinsics.checkNotNull(pG_MultiTouchEntity, "null cannot be cast to non-null type chin.pswm.gps.photo.location.map.photoGrid.multitouch.PG_ImageEntity");
                ((PG_ImageEntity) pG_MultiTouchEntity).setDrawShadow(z);
            }
        }
        invalidate();
    }

    public final void setShadowSize(int i) {
        ArrayList<PG_MultiTouchEntity> arrayList = this.imageEntities;
        if (arrayList == null) {
            return;
        }
        Intrinsics.checkNotNull(arrayList);
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList<PG_MultiTouchEntity> arrayList2 = this.imageEntities;
            Intrinsics.checkNotNull(arrayList2);
            if (arrayList2.get(i2) instanceof PG_ImageEntity) {
                ArrayList<PG_MultiTouchEntity> arrayList3 = this.imageEntities;
                Intrinsics.checkNotNull(arrayList3);
                PG_MultiTouchEntity pG_MultiTouchEntity = arrayList3.get(i2);
                Intrinsics.checkNotNull(pG_MultiTouchEntity, "null cannot be cast to non-null type chin.pswm.gps.photo.location.map.photoGrid.multitouch.PG_ImageEntity");
                ((PG_ImageEntity) pG_MultiTouchEntity).setShadowSize(i);
            }
        }
        invalidate();
    }

    public final void setPhotoBackground(Uri photoBackgroundUri) {
        Intrinsics.checkNotNullParameter(photoBackgroundUri, "photoBackgroundUri");
        destroyBackground();
        this.photoBackgroundUri = photoBackgroundUri;
        if (photoBackgroundUri != null) {
            PG_ImageDecoder pG_ImageDecoder = PG_ImageDecoder.INSTANCE;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            setBackground(pG_ImageDecoder.decodeUriToDrawable(context, photoBackgroundUri));
            return;
        }
        setBackground(null);
    }

    public final void destroyBackground() {
        Bitmap bitmap;
        Drawable background = getBackground();
        if (background != null && (background instanceof BitmapDrawable) && (bitmap = ((BitmapDrawable) background).getBitmap()) != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        setBackground(null);
        this.photoBackgroundUri = null;
    }

    public final void unloadImages() {
        ArrayList<PG_MultiTouchEntity> arrayList = this.imageEntities;
        Intrinsics.checkNotNull(arrayList);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ArrayList<PG_MultiTouchEntity> arrayList2 = this.imageEntities;
            Intrinsics.checkNotNull(arrayList2);
            arrayList2.get(i).unload();
        }
    }

    @Override 
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        ArrayList<PG_MultiTouchEntity> arrayList = this.imageEntities;
        if (arrayList == null) {
            return;
        }
        Intrinsics.checkNotNull(arrayList);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ArrayList<PG_MultiTouchEntity> arrayList2 = this.imageEntities;
            Intrinsics.checkNotNull(arrayList2);
            arrayList2.get(i).draw(canvas);
        }
        if (this.mShowDebugInfo) {
            drawMultitouchDebugMarks(canvas);
        }
    }

    public final Bitmap getImage(float f) {
        Bitmap bitmap;
        if (this.imageEntities == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap((int) (getWidth() * f), (int) (getHeight() * f), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Drawable background = getBackground();
        if (background != null && (background instanceof BitmapDrawable) && (bitmap = ((BitmapDrawable) background).getBitmap()) != null) {
            canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, createBitmap.getWidth(), createBitmap.getHeight()), new Paint(1));
        }
        ArrayList<PG_MultiTouchEntity> arrayList = this.imageEntities;
        Intrinsics.checkNotNull(arrayList);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ArrayList<PG_MultiTouchEntity> arrayList2 = this.imageEntities;
            Intrinsics.checkNotNull(arrayList2);
            PG_MultiTouchEntity pG_MultiTouchEntity = arrayList2.get(i);
            Intrinsics.checkNotNullExpressionValue(pG_MultiTouchEntity, "imageEntities!![i]");
            PG_MultiTouchEntity pG_MultiTouchEntity2 = pG_MultiTouchEntity;
            if (pG_MultiTouchEntity2 instanceof PG_ImageEntity) {
                ((PG_ImageEntity) pG_MultiTouchEntity2).draw(canvas, f);
            } else {
                pG_MultiTouchEntity2.draw(canvas);
            }
        }
        return createBitmap;
    }

    public final void trackballClicked() {
        this.mUIMode = (this.mUIMode + 1) % 3;
        invalidate();
    }

    private final void drawMultitouchDebugMarks(Canvas canvas) {
        if (this.currTouchPoint.isDown()) {
            float[] xs = this.currTouchPoint.getXs();
            float[] ys = this.currTouchPoint.getYs();
            float[] pressures = this.currTouchPoint.getPressures();
            int min = Math.min(this.currTouchPoint.getNumTouchPoints(), 2);
            for (int i = 0; i < min; i++) {
                canvas.drawCircle(xs[i], ys[i], 50 + (pressures[i] * 80), this.mLinePaintTouchPointCircle);
            }
            if (min == 2) {
                canvas.drawLine(xs[0], ys[0], xs[1], ys[1], this.mLinePaintTouchPointCircle);
            }
        }
    }

    @Override 
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        PG_OnFrameTouchListener pG_OnFrameTouchListener = this.mFrameTouchListener;
        if (pG_OnFrameTouchListener != null && (pG_OnFrameTouchListener instanceof PG_FrameTouch)) {
            Intrinsics.checkNotNull(pG_OnFrameTouchListener, "null cannot be cast to non-null type chin.pswm.gps.photo.location.map.photoGrid.frame.PG_FrameTouch");
            if (((PG_FrameTouch) pG_OnFrameTouchListener).isImageFrameMoving()) {
                PG_OnFrameTouchListener pG_OnFrameTouchListener2 = this.mFrameTouchListener;
                if (pG_OnFrameTouchListener2 != null && this.mTouchedObject == null) {
                    Intrinsics.checkNotNull(pG_OnFrameTouchListener2);
                    pG_OnFrameTouchListener2.onFrameTouch(event);
                    return false;
                }
                return this.PGMultiTouchController.onTouchEvent(event);
            }
        }
        boolean onTouchEvent = this.PGMultiTouchController.onTouchEvent(event);
        PG_OnFrameTouchListener pG_OnFrameTouchListener3 = this.mFrameTouchListener;
        if (pG_OnFrameTouchListener3 != null && this.mTouchedObject == null) {
            Intrinsics.checkNotNull(pG_OnFrameTouchListener3);
            pG_OnFrameTouchListener3.onFrameTouch(event);
        }
        return onTouchEvent;
    }

    @Override
    public PG_MultiTouchEntity getDraggableObjectAtPoint(PG_MultiTouchController.PointInfo pt) {
        Intrinsics.checkNotNullParameter(pt, "pt");
        float x = pt.getX();
        float y = pt.getY();
        ArrayList<PG_MultiTouchEntity> arrayList = this.imageEntities;
        Intrinsics.checkNotNull(arrayList);
        int size = arrayList.size();
        while (true) {
            size--;
            if (-1 >= size) {
                return null;
            }
            ArrayList<PG_MultiTouchEntity> arrayList2 = this.imageEntities;
            Intrinsics.checkNotNull(arrayList2);
            PG_MultiTouchEntity pG_MultiTouchEntity = arrayList2.get(size);
            Intrinsics.checkNotNull(pG_MultiTouchEntity, "null cannot be cast to non-null type chin.pswm.gps.photo.location.map.photoGrid.multitouch.PG_ImageEntity");
            PG_ImageEntity pG_ImageEntity = (PG_ImageEntity) pG_MultiTouchEntity;
            if (pG_ImageEntity.contain(x, y)) {
                return pG_ImageEntity;
            }
        }
    }

    @Override
    public void selectObject(PG_MultiTouchEntity img, PG_MultiTouchController.PointInfo touchPoint) {
        Intrinsics.checkNotNullParameter(img, "img");
        Intrinsics.checkNotNullParameter(touchPoint, "touchPoint");
        this.currTouchPoint.set(touchPoint);
        this.mTouchedObject = img;
        ArrayList<PG_MultiTouchEntity> arrayList = this.imageEntities;
        Intrinsics.checkNotNull(arrayList);
        arrayList.remove(img);
        ArrayList<PG_MultiTouchEntity> arrayList2 = this.imageEntities;
        Intrinsics.checkNotNull(arrayList2);
        arrayList2.add(img);
        if (!touchPoint.isMultiTouch() && touchPoint.isDown()) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.mCurrentSelectedObject != img) {
                this.mCurrentSelectedObject = img;
                this.mSelectedCount = 1;
                this.mOldX = touchPoint.getX();
                this.mOldY = touchPoint.getY();
            } else {
                if (currentTimeMillis - this.mSelectedTime < DOUBLE_CLICK_TIME_INTERVAL) {
                    float x = touchPoint.getX();
                    float y = touchPoint.getY();
                    float f = this.mOldX;
                    float f2 = this.mTouchAreaInterval;
                    if (f + f2 > x && f - f2 < x) {
                        float f3 = this.mOldY;
                        if (f3 + f2 > y && f3 - f2 < y) {
                            this.mSelectedCount++;
                        }
                    }
                    this.mOldX = x;
                    this.mOldY = y;
                } else {
                    this.mOldX = touchPoint.getX();
                    this.mOldY = touchPoint.getY();
                }
                if (this.mSelectedCount == 2) {
                    PG_OnDoubleClickListener pG_OnDoubleClickListener = this.mClickListener;
                    if (pG_OnDoubleClickListener != null) {
                        Intrinsics.checkNotNull(pG_OnDoubleClickListener);
                        pG_OnDoubleClickListener.onPhotoViewDoubleClick(this, img);
                    }
                    this.mCurrentSelectedObject = null;
                    this.mSelectedCount = 0;
                    this.mOldX = 0.0f;
                    this.mOldY = 0.0f;
                }
            }
            this.mSelectedTime = currentTimeMillis;
        }
        invalidate();
    }

    @Override
    public void getPositionAndScale(PG_MultiTouchEntity img, PG_MultiTouchController.PositionAndScale objPosAndScaleOut) {
        Intrinsics.checkNotNullParameter(img, "img");
        Intrinsics.checkNotNullParameter(objPosAndScaleOut, "objPosAndScaleOut");
        float centerX = img.getCenterX();
        float centerY = img.getCenterY();
        int i = this.mUIMode;
        int i2 = UI_MODE_ANISOTROPIC_SCALE;
        objPosAndScaleOut.set(centerX, centerY, (i & i2) == 0, (img.getScaleX() + img.getScaleY()) / 2, (i2 & this.mUIMode) != 0, img.getScaleX(), img.getScaleY(), (this.mUIMode & UI_MODE_ROTATE) != 0, img.getAngle());
    }

    @Override
    public boolean setPositionAndScale(PG_MultiTouchEntity img, PG_MultiTouchController.PositionAndScale newImgPosAndScale, PG_MultiTouchController.PointInfo touchPoint) {
        Intrinsics.checkNotNullParameter(img, "img");
        Intrinsics.checkNotNullParameter(newImgPosAndScale, "newImgPosAndScale");
        Intrinsics.checkNotNullParameter(touchPoint, "touchPoint");
        this.currTouchPoint.set(touchPoint);
        boolean pos = ((PG_ImageEntity) img).setPos(newImgPosAndScale);
        if (pos) {
            invalidate();
        }
        return pos;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_PhotoView$Companion;", "", "()V", "DOUBLE_CLICK_TIME_INTERVAL", "", "SCREEN_MARGIN", "", "UI_MODE_ANISOTROPIC_SCALE", "", "UI_MODE_ROTATE", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
