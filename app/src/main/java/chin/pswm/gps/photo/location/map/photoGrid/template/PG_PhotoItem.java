package chin.pswm.gps.photo.location.map.photoGrid.template;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.HashMap;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
@SuppressWarnings("all")

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 m2\u00020\u0001:\u0001mB\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\f\"\u0004\b\u001e\u0010\u000eR\u001a\u0010\u001f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\f\"\u0004\b!\u0010\u000eR\u001a\u0010\"\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\f\"\u0004\b$\u0010\u000eR\u001c\u0010%\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0006\"\u0004\b'\u0010\bR\u001a\u0010(\u001a\u00020)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020/X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001a\u00104\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\f\"\u0004\b6\u0010\u000eR\u001a\u00107\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\f\"\u0004\b9\u0010\u000eR\u001a\u0010:\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\f\"\u0004\b<\u0010\u000eR\u001c\u0010=\u001a\u0004\u0018\u00010>X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001a\u0010C\u001a\u00020/X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u00101\"\u0004\bE\u00103R\u001c\u0010F\u001a\u0004\u0018\u00010>X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010@\"\u0004\bH\u0010BR\u001c\u0010I\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u0019\"\u0004\bK\u0010\u001bR\u001a\u0010L\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\f\"\u0004\bN\u0010\u000eR\u001a\u0010O\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010\f\"\u0004\bQ\u0010\u000eR\u001a\u0010R\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010\f\"\u0004\bT\u0010\u000eR\u001c\u0010U\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010\u0006\"\u0004\bW\u0010\bR\u001a\u0010X\u001a\u00020)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010+\"\u0004\bZ\u0010-R \u0010[\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010\u0013\"\u0004\b]\u0010\u0015R(\u0010^\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0018\u00010_X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010a\"\u0004\bb\u0010cR\u001a\u0010d\u001a\u00020/X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u00101\"\u0004\bf\u00103R\u001a\u0010g\u001a\u00020)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010+\"\u0004\bi\u0010-R\u001a\u0010j\u001a\u00020)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010+\"\u0004\bl\u0010-¨\u0006n"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_PhotoItem;", "", "()V", "bound", "Landroid/graphics/RectF;", "getBound", "()Landroid/graphics/RectF;", "setBound", "(Landroid/graphics/RectF;)V", "centerInClearBound", "", "getCenterInClearBound", "()Z", "setCenterInClearBound", "(Z)V", "clearAreaPoints", "Ljava/util/ArrayList;", "Landroid/graphics/PointF;", "getClearAreaPoints", "()Ljava/util/ArrayList;", "setClearAreaPoints", "(Ljava/util/ArrayList;)V", "clearPath", "Landroid/graphics/Path;", "getClearPath", "()Landroid/graphics/Path;", "setClearPath", "(Landroid/graphics/Path;)V", "clearPathAlignParentRight", "getClearPathAlignParentRight", "setClearPathAlignParentRight", "clearPathInCenterHorizontal", "getClearPathInCenterHorizontal", "setClearPathInCenterHorizontal", "clearPathInCenterVertical", "getClearPathInCenterVertical", "setClearPathInCenterVertical", "clearPathRatioBound", "getClearPathRatioBound", "setClearPathRatioBound", "clearPathScaleRatio", "", "getClearPathScaleRatio", "()F", "setClearPathScaleRatio", "(F)V", "cornerMethod", "", "getCornerMethod", "()I", "setCornerMethod", "(I)V", "disableShrink", "getDisableShrink", "setDisableShrink", "fitBound", "getFitBound", "setFitBound", "hasBackground", "getHasBackground", "setHasBackground", "imagePath", "", "getImagePath", "()Ljava/lang/String;", "setImagePath", "(Ljava/lang/String;)V", "index", "getIndex", "setIndex", "maskPath", "getMaskPath", "setMaskPath", "path", "getPath", "setPath", "pathAlignParentRight", "getPathAlignParentRight", "setPathAlignParentRight", "pathInCenterHorizontal", "getPathInCenterHorizontal", "setPathInCenterHorizontal", "pathInCenterVertical", "getPathInCenterVertical", "setPathInCenterVertical", "pathRatioBound", "getPathRatioBound", "setPathRatioBound", "pathScaleRatio", "getPathScaleRatio", "setPathScaleRatio", "pointList", "getPointList", "setPointList", "shrinkMap", "Ljava/util/HashMap;", "getShrinkMap", "()Ljava/util/HashMap;", "setShrinkMap", "(Ljava/util/HashMap;)V", "shrinkMethod", "getShrinkMethod", "setShrinkMethod", "x", "getX", "setX", "y", "getY", "setY", "Companion", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_PhotoItem {
    private static final int CORNER_METHOD_DEFAULT = 0;
    private static final int SHRINK_METHOD_DEFAULT = 0;
    private boolean centerInClearBound;
    private ArrayList<PointF> clearAreaPoints;
    private Path clearPath;
    private boolean clearPathAlignParentRight;
    private boolean clearPathInCenterHorizontal;
    private boolean clearPathInCenterVertical;
    private RectF clearPathRatioBound;
    private boolean disableShrink;
    private boolean fitBound;
    private boolean hasBackground;
    private String imagePath;
    private int index;
    private String maskPath;
    private Path path;
    private boolean pathAlignParentRight;
    private boolean pathInCenterHorizontal;
    private boolean pathInCenterVertical;
    private RectF pathRatioBound;
    private HashMap<PointF, PointF> shrinkMap;
    private float x;
    private float y;
    public static final Companion Companion = new Companion(null);
    private static final int SHRINK_METHOD_3_3 = 1;
    private static final int SHRINK_METHOD_USING_MAP = 2;
    private static final int SHRINK_METHOD_3_6 = 3;
    private static final int SHRINK_METHOD_3_8 = 4;
    private static final int SHRINK_METHOD_COMMON = 5;
    private static final int CORNER_METHOD_3_6 = 1;
    private static final int CORNER_METHOD_3_13 = 2;
    private ArrayList<PointF> pointList = new ArrayList<>();
    private RectF bound = new RectF();
    private float pathScaleRatio = 1.0f;
    private int shrinkMethod = SHRINK_METHOD_DEFAULT;
    private int cornerMethod = CORNER_METHOD_DEFAULT;
    private float clearPathScaleRatio = 1.0f;

    public final float getX() {
        return this.x;
    }

    public final void setX(float f) {
        this.x = f;
    }

    public final float getY() {
        return this.y;
    }

    public final void setY(float f) {
        this.y = f;
    }

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int i) {
        this.index = i;
    }

    public final String getImagePath() {
        return this.imagePath;
    }

    public final void setImagePath(String str) {
        this.imagePath = str;
    }

    public final String getMaskPath() {
        return this.maskPath;
    }

    public final void setMaskPath(String str) {
        this.maskPath = str;
    }

    public final ArrayList<PointF> getPointList() {
        return this.pointList;
    }

    public final void setPointList(ArrayList<PointF> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.pointList = arrayList;
    }

    public final RectF getBound() {
        return this.bound;
    }

    public final void setBound(RectF rectF) {
        Intrinsics.checkNotNullParameter(rectF, "<set-?>");
        this.bound = rectF;
    }

    public final Path getPath() {
        return this.path;
    }

    public final void setPath(Path path) {
        this.path = path;
    }

    public final RectF getPathRatioBound() {
        return this.pathRatioBound;
    }

    public final void setPathRatioBound(RectF rectF) {
        this.pathRatioBound = rectF;
    }

    public final boolean getPathInCenterHorizontal() {
        return this.pathInCenterHorizontal;
    }

    public final void setPathInCenterHorizontal(boolean z) {
        this.pathInCenterHorizontal = z;
    }

    public final boolean getPathInCenterVertical() {
        return this.pathInCenterVertical;
    }

    public final void setPathInCenterVertical(boolean z) {
        this.pathInCenterVertical = z;
    }

    public final boolean getPathAlignParentRight() {
        return this.pathAlignParentRight;
    }

    public final void setPathAlignParentRight(boolean z) {
        this.pathAlignParentRight = z;
    }

    public final float getPathScaleRatio() {
        return this.pathScaleRatio;
    }

    public final void setPathScaleRatio(float f) {
        this.pathScaleRatio = f;
    }

    public final boolean getFitBound() {
        return this.fitBound;
    }

    public final void setFitBound(boolean z) {
        this.fitBound = z;
    }

    public final boolean getHasBackground() {
        return this.hasBackground;
    }

    public final void setHasBackground(boolean z) {
        this.hasBackground = z;
    }

    public final int getShrinkMethod() {
        return this.shrinkMethod;
    }

    public final void setShrinkMethod(int i) {
        this.shrinkMethod = i;
    }

    public final int getCornerMethod() {
        return this.cornerMethod;
    }

    public final void setCornerMethod(int i) {
        this.cornerMethod = i;
    }

    public final boolean getDisableShrink() {
        return this.disableShrink;
    }

    public final void setDisableShrink(boolean z) {
        this.disableShrink = z;
    }

    public final HashMap<PointF, PointF> getShrinkMap() {
        return this.shrinkMap;
    }

    public final void setShrinkMap(HashMap<PointF, PointF> hashMap) {
        this.shrinkMap = hashMap;
    }

    public final ArrayList<PointF> getClearAreaPoints() {
        return this.clearAreaPoints;
    }

    public final void setClearAreaPoints(ArrayList<PointF> arrayList) {
        this.clearAreaPoints = arrayList;
    }

    public final Path getClearPath() {
        return this.clearPath;
    }

    public final void setClearPath(Path path) {
        this.clearPath = path;
    }

    public final RectF getClearPathRatioBound() {
        return this.clearPathRatioBound;
    }

    public final void setClearPathRatioBound(RectF rectF) {
        this.clearPathRatioBound = rectF;
    }

    public final boolean getClearPathInCenterHorizontal() {
        return this.clearPathInCenterHorizontal;
    }

    public final void setClearPathInCenterHorizontal(boolean z) {
        this.clearPathInCenterHorizontal = z;
    }

    public final boolean getClearPathInCenterVertical() {
        return this.clearPathInCenterVertical;
    }

    public final void setClearPathInCenterVertical(boolean z) {
        this.clearPathInCenterVertical = z;
    }

    public final boolean getClearPathAlignParentRight() {
        return this.clearPathAlignParentRight;
    }

    public final void setClearPathAlignParentRight(boolean z) {
        this.clearPathAlignParentRight = z;
    }

    public final float getClearPathScaleRatio() {
        return this.clearPathScaleRatio;
    }

    public final void setClearPathScaleRatio(float f) {
        this.clearPathScaleRatio = f;
    }

    public final boolean getCenterInClearBound() {
        return this.centerInClearBound;
    }

    public final void setCenterInClearBound(boolean z) {
        this.centerInClearBound = z;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0013\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006¨\u0006\u0017"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_PhotoItem$Companion;", "", "()V", "CORNER_METHOD_3_13", "", "getCORNER_METHOD_3_13", "()I", "CORNER_METHOD_3_6", "getCORNER_METHOD_3_6", "CORNER_METHOD_DEFAULT", "getCORNER_METHOD_DEFAULT", "SHRINK_METHOD_3_3", "getSHRINK_METHOD_3_3", "SHRINK_METHOD_3_6", "getSHRINK_METHOD_3_6", "SHRINK_METHOD_3_8", "getSHRINK_METHOD_3_8", "SHRINK_METHOD_COMMON", "getSHRINK_METHOD_COMMON", "SHRINK_METHOD_DEFAULT", "getSHRINK_METHOD_DEFAULT", "SHRINK_METHOD_USING_MAP", "getSHRINK_METHOD_USING_MAP", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getSHRINK_METHOD_DEFAULT() {
            return PG_PhotoItem.SHRINK_METHOD_DEFAULT;
        }

        public final int getSHRINK_METHOD_3_3() {
            return PG_PhotoItem.SHRINK_METHOD_3_3;
        }

        public final int getSHRINK_METHOD_USING_MAP() {
            return PG_PhotoItem.SHRINK_METHOD_USING_MAP;
        }

        public final int getSHRINK_METHOD_3_6() {
            return PG_PhotoItem.SHRINK_METHOD_3_6;
        }

        public final int getSHRINK_METHOD_3_8() {
            return PG_PhotoItem.SHRINK_METHOD_3_8;
        }

        public final int getSHRINK_METHOD_COMMON() {
            return PG_PhotoItem.SHRINK_METHOD_COMMON;
        }

        public final int getCORNER_METHOD_DEFAULT() {
            return PG_PhotoItem.CORNER_METHOD_DEFAULT;
        }

        public final int getCORNER_METHOD_3_6() {
            return PG_PhotoItem.CORNER_METHOD_3_6;
        }

        public final int getCORNER_METHOD_3_13() {
            return PG_PhotoItem.CORNER_METHOD_3_13;
        }
    }
}
