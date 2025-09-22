package chin.pswm.gps.photo.location.map.photoGrid.multitouch;

import android.util.Log;
import android.view.MotionEvent;

import androidx.core.app.NotificationCompat;

import java.lang.reflect.Method;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@SuppressWarnings("all")
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 I*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0004IJKLB\u001f\b\u0007\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u00101\u001a\u000202H\u0002JH\u00103\u001a\u0002022\u0006\u00104\u001a\u00020\u001d2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002062\u0006\u00108\u001a\u0002062\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\u001d2\u0006\u0010<\u001a\u00020\u00062\u0006\u0010=\u001a\u00020\u001aH\u0002J\u0006\u0010>\u001a\u00020\u0006J\b\u0010?\u001a\u000202H\u0002J\b\u0010@\u001a\u000202H\u0002J\u000e\u0010A\u001a\u00020\u00062\u0006\u0010B\u001a\u00020CJ \u0010D\u001a\u00020\u00062\u0006\u0010E\u001a\u00020\u000f2\u0006\u0010F\u001a\u00020\u000f2\u0006\u0010G\u001a\u00020\u000fH\u0002J\b\u0010H\u001a\u000202H\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001e\u0010%\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010*\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u000e\u0010+\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006M"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController;", "T", "", "objectCanvas", "Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$MultiTouchObjectCanvas;", "handleSingleTouchEvents", "", "(Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$MultiTouchObjectCanvas;Z)V", "getHandleSingleTouchEvents", "()Z", "setHandleSingleTouchEvents", "(Z)V", "mCurrPt", "Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$PointInfo;", "mCurrPtAng", "", "mCurrPtDiam", "mCurrPtHeight", "mCurrPtWidth", "mCurrPtX", "mCurrPtY", "mCurrXform", "Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$PositionAndScale;", "mDragOccurred", "mPrevPt", "mSettleEndTime", "", "mSettleStartTime", "<set-?>", "", "mode", "getMode", "()I", "getObjectCanvas$app_release", "()Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$MultiTouchObjectCanvas;", "setObjectCanvas$app_release", "(Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$MultiTouchObjectCanvas;)V", "selectedObject", "getSelectedObject", "()Ljava/lang/Object;", "setSelectedObject", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "startAngleMinusPinchAngle", "startPosX", "startPosY", "startScaleOverPinchDiam", "startScaleXOverPinchWidth", "startScaleYOverPinchHeight", "anchorAtThisPositionAndScale", "", "decodeTouchEvent", "pointerCount", "x", "", "y", "pressure", "pointerIds", "", "action", "down", "eventTime", "dragOccurred", "extractCurrPtInfo", "multiTouchController", "onTouchEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "pastThreshold", "deltaX", "deltaY", "newScale", "performDragOrPinch", "Companion", "MultiTouchObjectCanvas", "PointInfo", "PositionAndScale", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_MultiTouchController<T> {
    private static int ACTION_POINTER_INDEX_SHIFT;
    private static int ACTION_POINTER_UP;
    private static final boolean DEBUG = false;
    private static final int MODE_NOTHING = 0;
    private static Method m_getHistoricalPressure;
    private static Method m_getHistoricalX;
    private static Method m_getHistoricalY;
    private static Method m_getPointerCount;
    private static Method m_getPointerId;
    private static Method m_getPressure;
    private static Method m_getX;
    private static Method m_getY;
    private static final boolean multiTouchSupported;
    private static final int[] pointerIds;
    private static final float[] pressureVals;
    private static final float[] xVals;
    private static final float[] yVals;
    private boolean handleSingleTouchEvents;
    private PointInfo mCurrPt;
    private float mCurrPtAng;
    private float mCurrPtDiam;
    private float mCurrPtHeight;
    private float mCurrPtWidth;
    private float mCurrPtX;
    private float mCurrPtY;
    private final PositionAndScale mCurrXform;
    private boolean mDragOccurred;
    private PointInfo mPrevPt;
    private long mSettleEndTime;
    private long mSettleStartTime;
    private int mode;
    private MultiTouchObjectCanvas<T> objectCanvas;
    private T selectedObject;
    private float startAngleMinusPinchAngle;
    private float startPosX;
    private float startPosY;
    private float startScaleOverPinchDiam;
    private float startScaleXOverPinchWidth;
    private float startScaleYOverPinchHeight;
    public static final Companion Companion = new Companion(null);
    private static final long EVENT_SETTLE_TIME_INTERVAL = 20;
    private static final float MAX_MULTITOUCH_POS_JUMP_SIZE = 30.0f;
    private static final float MAX_MULTITOUCH_DIM_JUMP_SIZE = 40.0f;
    private static final float MIN_MULTITOUCH_SEPARATION = 30.0f;
    private static final float THRESHOLD = 3.0f;
    private static final int MAX_TOUCH_POINTS = 20;
    private static final int MODE_DRAG = 1;
    private static final int MODE_PINCH = 2;
    private static final int MODE_ST_GRAB = 3;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\bf\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002J\u0017\u0010\u0003\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0006J\u001d\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u00012\u0006\u0010\n\u001a\u00020\u000bH&¢\u0006\u0002\u0010\fJ\u001d\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u000fJ\u001d\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u00012\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0011J%\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00028\u00012\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$MultiTouchObjectCanvas;", "T", "", "getDraggableObjectAtPoint", "touchPoint", "Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$PointInfo;", "(Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$PointInfo;)Ljava/lang/Object;", "getPositionAndScale", "", "obj", "objPosAndScaleOut", "Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$PositionAndScale;", "(Ljava/lang/Object;Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$PositionAndScale;)V", "pointInObjectGrabArea", "", "(Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$PointInfo;Ljava/lang/Object;)Z", "selectObject", "(Ljava/lang/Object;Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$PointInfo;)V", "setPositionAndScale", "newObjPosAndScale", "(Ljava/lang/Object;Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$PositionAndScale;Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$PointInfo;)Z", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public interface MultiTouchObjectCanvas<T> {
        T getDraggableObjectAtPoint(PointInfo pointInfo);

        void getPositionAndScale(T t, PositionAndScale positionAndScale);

        boolean pointInObjectGrabArea(PointInfo pointInfo, T t);

        void selectObject(T t, PointInfo pointInfo);

        boolean setPositionAndScale(T t, PositionAndScale positionAndScale, PointInfo pointInfo);
    }


    public PG_MultiTouchController(MultiTouchObjectCanvas<T> objectCanvas) {
        this(objectCanvas, false, 2, null);
        Intrinsics.checkNotNullParameter(objectCanvas, "objectCanvas");
    }

    public PG_MultiTouchController(MultiTouchObjectCanvas<T> objectCanvas, boolean z) {
        Intrinsics.checkNotNullParameter(objectCanvas, "objectCanvas");
        this.objectCanvas = objectCanvas;
        this.handleSingleTouchEvents = z;
        this.mCurrXform = new PositionAndScale();
        this.mode = MODE_NOTHING;
        this.mCurrPt = new PointInfo();
        this.mPrevPt = new PointInfo();
    }

    public  PG_MultiTouchController(MultiTouchObjectCanvas multiTouchObjectCanvas, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(multiTouchObjectCanvas, (i & 2) != 0 ? true : z);
    }

    public final MultiTouchObjectCanvas<T> getObjectCanvas$app_release() {
        return this.objectCanvas;
    }

    public final void setObjectCanvas$app_release(MultiTouchObjectCanvas<T> multiTouchObjectCanvas) {
        Intrinsics.checkNotNullParameter(multiTouchObjectCanvas, "<set-?>");
        this.objectCanvas = multiTouchObjectCanvas;
    }

    protected final boolean getHandleSingleTouchEvents() {
        return this.handleSingleTouchEvents;
    }

    protected final void setHandleSingleTouchEvents(boolean z) {
        this.handleSingleTouchEvents = z;
    }

    public final T getSelectedObject() {
        return this.selectedObject;
    }

    public final void setSelectedObject(T t) {
        this.selectedObject = t;
    }

    public final int getMode() {
        return this.mode;
    }

    private final void extractCurrPtInfo() {
        float multiTouchDiameter;
        float multiTouchWidth;
        float multiTouchHeight;
        PointInfo pointInfo = this.mCurrPt;
        Intrinsics.checkNotNull(pointInfo);
        this.mCurrPtX = pointInfo.getX();
        PointInfo pointInfo2 = this.mCurrPt;
        Intrinsics.checkNotNull(pointInfo2);
        this.mCurrPtY = pointInfo2.getY();
        float f = MIN_MULTITOUCH_SEPARATION;
        float f2 = 0.71f * f;
        float f3 = 0.0f;
        if (this.mCurrXform.getUpdateScale()) {
            PointInfo pointInfo3 = this.mCurrPt;
            Intrinsics.checkNotNull(pointInfo3);
            multiTouchDiameter = pointInfo3.getMultiTouchDiameter();
        } else {
            multiTouchDiameter = 0.0f;
        }
        this.mCurrPtDiam = Math.max(f2, multiTouchDiameter);
        if (this.mCurrXform.getUpdateScaleXY()) {
            PointInfo pointInfo4 = this.mCurrPt;
            Intrinsics.checkNotNull(pointInfo4);
            multiTouchWidth = pointInfo4.getMultiTouchWidth();
        } else {
            multiTouchWidth = 0.0f;
        }
        this.mCurrPtWidth = Math.max(f, multiTouchWidth);
        if (this.mCurrXform.getUpdateScaleXY()) {
            PointInfo pointInfo5 = this.mCurrPt;
            Intrinsics.checkNotNull(pointInfo5);
            multiTouchHeight = pointInfo5.getMultiTouchHeight();
        } else {
            multiTouchHeight = 0.0f;
        }
        this.mCurrPtHeight = Math.max(f, multiTouchHeight);
        if (this.mCurrXform.getUpdateAngle()) {
            PointInfo pointInfo6 = this.mCurrPt;
            Intrinsics.checkNotNull(pointInfo6);
            f3 = pointInfo6.getMultiTouchAngle();
        }
        this.mCurrPtAng = f3;
    }

    public final boolean dragOccurred() {
        return this.mDragOccurred;
    }

    public final boolean onTouchEvent(MotionEvent arg23) {
        Object v4_8;
        Object v4_5;
        Object v4_2;
        int v9;
        PG_MultiTouchController v11 = this;
        MotionEvent v0 = arg23;
        Intrinsics.checkNotNullParameter(v0, "event");
        try {
            boolean v2 = PG_MultiTouchController.multiTouchSupported;
            if(v2) {
                Method v3 = PG_MultiTouchController.m_getPointerCount;
                Intrinsics.checkNotNull(v3);
                Object v3_1 = v3.invoke(v0);
                Intrinsics.checkNotNull(v3_1, "null cannot be cast to non-null type kotlin.Int");
                v9 = (int)(((Integer)v3_1));
            }
            else {
                v9 = 1;
            }

            boolean v3_2 = PG_MultiTouchController.DEBUG;
            String v10 = "MultiTouch";
            if(v3_2) {
                Log.i("MultiTouch", "Got here 1 - " + ((boolean)((v2))) + ' ' + v11.mode + ' ' + v11.handleSingleTouchEvents + ' ' + v9);
            }

            if(v11.mode == PG_MultiTouchController.MODE_NOTHING && !v11.handleSingleTouchEvents && v9 == 1) {
                return false;
            }

            if(v3_2) {
                Log.i("MultiTouch", "Got here 2");
            }

            int v8 = arg23.getAction();
            int v7 = arg23.getHistorySize() / v9;
            if(v7 >= 0) {
                int v6 = 0;
                while(true) {
                    int v1 = v6 >= v7 ? 0 : 1;
                    if((PG_MultiTouchController.multiTouchSupported) && v9 != 1) {
                        boolean v2_1 = PG_MultiTouchController.DEBUG;
                        if(v2_1) {
                            Log.i(v10, "Got here 4");
                        }

                        int v5 = Math.min(v9, PG_MultiTouchController.MAX_TOUCH_POINTS);
                        if((v2_1) && v9 > PG_MultiTouchController.MAX_TOUCH_POINTS) {
                            Log.i(v10, "Got more pointers than MAX_TOUCH_POINTS");
                        }

                        int v2_2;
                        for(v2_2 = 0; v2_2 < v5; ++v2_2) {
                            Method v4 = PG_MultiTouchController.m_getPointerId;
                            Intrinsics.checkNotNull(v4);
                            Object v3_3 = v4.invoke(v0, ((int)v2_2));
                            Intrinsics.checkNotNull(v3_3, "null cannot be cast to non-null type kotlin.Int");
                            PG_MultiTouchController.pointerIds[v2_2] = (int)(((Integer)v3_3));
                            float[] v3_4 = PG_MultiTouchController.xVals;
                            if(v1 == 0) {
                                Method v4_3 = PG_MultiTouchController.m_getX;
                                Intrinsics.checkNotNull(v4_3);
                                v4_2 = v4_3.invoke(v0, ((int)v2_2));
                            }
                            else {
                                Method v4_1 = PG_MultiTouchController.m_getHistoricalX;
                                Intrinsics.checkNotNull(v4_1);
                                v4_2 = v4_1.invoke(v0, ((int)v2_2), ((int)v6));
                            }

                            Intrinsics.checkNotNull(v4_2, "null cannot be cast to non-null type kotlin.Float");
                            v3_4[v2_2] = (float)(((Float)v4_2));
                            float[] v3_5 = PG_MultiTouchController.yVals;
                            if(v1 == 0) {
                                Method v4_6 = PG_MultiTouchController.m_getY;
                                Intrinsics.checkNotNull(v4_6);
                                v4_5 = v4_6.invoke(v0, ((int)v2_2));
                            }
                            else {
                                Method v4_4 = PG_MultiTouchController.m_getHistoricalY;
                                Intrinsics.checkNotNull(v4_4);
                                v4_5 = v4_4.invoke(v0, ((int)v2_2), ((int)v6));
                            }

                            Intrinsics.checkNotNull(v4_5, "null cannot be cast to non-null type kotlin.Float");
                            v3_5[v2_2] = (float)(((Float)v4_5));
                            float[] v3_6 = PG_MultiTouchController.pressureVals;
                            if(v1 == 0) {
                                Method v4_9 = PG_MultiTouchController.m_getPressure;
                                Intrinsics.checkNotNull(v4_9);
                                v4_8 = v4_9.invoke(v0, ((int)v2_2));
                            }
                            else {
                                Method v4_7 = PG_MultiTouchController.m_getHistoricalPressure;
                                Intrinsics.checkNotNull(v4_7);
                                v4_8 = v4_7.invoke(v0, ((int)v2_2), ((int)v6));
                            }

                            Intrinsics.checkNotNull(v4_8, "null cannot be cast to non-null type kotlin.Float");
                            v3_6[v2_2] = (float)(((Float)v4_8));
                        }
                    }
                    else {
                        if(PG_MultiTouchController.DEBUG) {
                            Log.i(v10, "Got here 3");
                        }

                        float[] v2_3 = PG_MultiTouchController.xVals;
                        float v3_7 = v1 == 0 ? arg23.getX() : v0.getHistoricalX(v6);
                        v2_3[0] = v3_7;
                        float[] v2_4 = PG_MultiTouchController.yVals;
                        float v3_8 = v1 == 0 ? arg23.getY() : v0.getHistoricalY(v6);
                        v2_4[0] = v3_8;
                        float[] v2_5 = PG_MultiTouchController.pressureVals;
                        float v3_9 = v1 == 0 ? arg23.getPressure() : v0.getHistoricalPressure(v6);
                        v2_5[0] = v3_9;
                    }

                    float[] v3_10 = PG_MultiTouchController.xVals;
                    float[] v4_10 = PG_MultiTouchController.yVals;
                    float[] v5_1 = PG_MultiTouchController.pressureVals;
                    int[] v13 = PG_MultiTouchController.pointerIds;
                    int v16 = v1 == 0 ? v8 : 2;
                    boolean v15 = v1 != 0 || (v8 != 1 && ((1 << PG_MultiTouchController.ACTION_POINTER_INDEX_SHIFT) - 1 & v8) != PG_MultiTouchController.ACTION_POINTER_UP && v8 != 3);

                    long v1_1 = v1 == 0 ? arg23.getEventTime() : v0.getHistoricalEventTime(v6);
                    int v0_2 = v6;
                    int v13_1 = v7;
                    int v16_1 = v8;
                    int v15_1 = v9;
                    String v21 = v10;
                    this.decodeTouchEvent(v9, v3_10, v4_10, v5_1, v13, v16,  v15, v1_1);
                    if(v0_2 == v13_1) {
                        break;
                    }

                    v6 = v0_2 + 1;
                    v0 = arg23;
                    v7 = v13_1;
                    v9 = v15_1;
                    v8 = v16_1;
                    v10 = v21;
                }
            }

            return v11.selectedObject != null;
        }
        catch(Exception v0_1) {
            Log.e("PG_MultiTouchController", "onTouchEvent() failed", ((Throwable)v0_1));
            return false;
        }
    }
    private final void decodeTouchEvent(int arg14, float[] arg15, float[] arg16, float[] arg17, int[] arg18, int arg19, boolean arg20, long arg21) {
        if(PG_MultiTouchController.DEBUG) {
            Log.i("MultiTouch", "Got here 5 - " + arg14 + ' ' + arg19 + ' ' + arg20);
        }

        PointInfo v3 = this.mPrevPt;
        this.mPrevPt = this.mCurrPt;
        this.mCurrPt = v3;
        Intrinsics.checkNotNull(v3);
        v3.set(arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21);
        this.multiTouchController();
    }

    private final void anchorAtThisPositionAndScale() {
        float scle;
        if (DEBUG) {
            Log.i("MulitTouch", "anchorAtThisPositionAndScale()");
        }
        T t = this.selectedObject;
        if (t == null) {
            return;
        }
        MultiTouchObjectCanvas<T> multiTouchObjectCanvas = this.objectCanvas;
        Intrinsics.checkNotNull(t);
        multiTouchObjectCanvas.getPositionAndScale(t, this.mCurrXform);
        if (this.mCurrXform.getUpdateScale()) {
            if (!(this.mCurrXform.getScle() == 0.0f)) {
                scle = this.mCurrXform.getScle();
                float f = 1.0f / scle;
                extractCurrPtInfo();
                this.startPosX = (this.mCurrPtX - this.mCurrXform.getXOff()) * f;
                this.startPosY = (this.mCurrPtY - this.mCurrXform.getYOff()) * f;
                this.startScaleOverPinchDiam = this.mCurrXform.getScle() / this.mCurrPtDiam;
                this.startScaleXOverPinchWidth = this.mCurrXform.getScleX() / this.mCurrPtWidth;
                this.startScaleYOverPinchHeight = this.mCurrXform.getScleY() / this.mCurrPtHeight;
                this.startAngleMinusPinchAngle = this.mCurrXform.getAngl() - this.mCurrPtAng;
            }
        }
        scle = 1.0f;
        float f2 = 1.0f / scle;
        extractCurrPtInfo();
        this.startPosX = (this.mCurrPtX - this.mCurrXform.getXOff()) * f2;
        this.startPosY = (this.mCurrPtY - this.mCurrXform.getYOff()) * f2;
        this.startScaleOverPinchDiam = this.mCurrXform.getScle() / this.mCurrPtDiam;
        this.startScaleXOverPinchWidth = this.mCurrXform.getScleX() / this.mCurrPtWidth;
        this.startScaleYOverPinchHeight = this.mCurrXform.getScleY() / this.mCurrPtHeight;
        this.startAngleMinusPinchAngle = this.mCurrXform.getAngl() - this.mCurrPtAng;
    }

    private final void performDragOrPinch() {
        float f;
        if (this.selectedObject == null) {
            return;
        }
        float f2 = 1.0f;
        if (this.mCurrXform.getUpdateScale()) {
            if (!(this.mCurrXform.getScle() == 0.0f)) {
                f2 = this.mCurrXform.getScle();
            }
        }
        extractCurrPtInfo();
        float f3 = this.mCurrPtX - (this.startPosX * f2);
        float f4 = this.mCurrPtY - (this.startPosY * f2);
        PointInfo pointInfo = this.mCurrPt;
        Intrinsics.checkNotNull(pointInfo);
        float x = pointInfo.getX();
        PointInfo pointInfo2 = this.mPrevPt;
        Intrinsics.checkNotNull(pointInfo2);
        float x2 = x - pointInfo2.getX();
        PointInfo pointInfo3 = this.mCurrPt;
        Intrinsics.checkNotNull(pointInfo3);
        float y = pointInfo3.getY();
        PointInfo pointInfo4 = this.mPrevPt;
        Intrinsics.checkNotNull(pointInfo4);
        float y2 = y - pointInfo4.getY();
        this.mCurrXform.getScle();
        if (this.mode == MODE_ST_GRAB) {
            if (x2 < 0.0f || y2 < 0.0f) {
                f = this.mCurrXform.getScle() - 0.04f;
            } else {
                f = this.mCurrXform.getScle() + 0.04f;
            }
            if (f < 0.35f) {
                return;
            }
        } else {
            f = this.startScaleOverPinchDiam * this.mCurrPtDiam;
        }
        float f5 = f;
        if (!this.mDragOccurred && !pastThreshold(Math.abs(x2), Math.abs(y2), f5)) {
            if (DEBUG) {
                Log.i("MultiTouch", "Change received by performDragOrPinch was below the threshold");
                return;
            }
            return;
        }
        this.mCurrXform.set(f3, f4, f5, this.startScaleXOverPinchWidth * this.mCurrPtWidth, this.startScaleYOverPinchHeight * this.mCurrPtHeight, this.startAngleMinusPinchAngle + this.mCurrPtAng);
        MultiTouchObjectCanvas<T> multiTouchObjectCanvas = this.objectCanvas;
        T t = this.selectedObject;
        Intrinsics.checkNotNull(t);
        PositionAndScale positionAndScale = this.mCurrXform;
        PointInfo pointInfo5 = this.mCurrPt;
        Intrinsics.checkNotNull(pointInfo5);
        multiTouchObjectCanvas.setPositionAndScale(t, positionAndScale, pointInfo5);
        this.mDragOccurred = true;
    }

    private final boolean pastThreshold(float f, float f2, float f3) {
        float f4 = THRESHOLD;
        if (f < f4 && f2 < f4) {
            if (f3 == this.mCurrXform.getScle()) {
                this.mDragOccurred = false;
                return false;
            }
        }
        this.mDragOccurred = true;
        return true;
    }

    private final void multiTouchController() {
        boolean z = DEBUG;
        if (z) {
            StringBuilder append = new StringBuilder("Got here 6 - ").append(this.mode).append(' ');
            PointInfo pointInfo = this.mCurrPt;
            Intrinsics.checkNotNull(pointInfo);
            StringBuilder append2 = append.append(pointInfo.getNumTouchPoints()).append(' ');
            PointInfo pointInfo2 = this.mCurrPt;
            Intrinsics.checkNotNull(pointInfo2);
            StringBuilder append3 = append2.append(pointInfo2.isDown());
            PointInfo pointInfo3 = this.mCurrPt;
            Intrinsics.checkNotNull(pointInfo3);
            Log.i("MultiTouch", append3.append(pointInfo3.isMultiTouch()).toString());
        }
        int i = this.mode;
        int i2 = MODE_NOTHING;
        if (i == i2) {
            if (z) {
                Log.i("MultiTouch", "MODE_NOTHING");
            }
            PointInfo pointInfo4 = this.mCurrPt;
            Intrinsics.checkNotNull(pointInfo4);
            if (pointInfo4.isDown()) {
                MultiTouchObjectCanvas<T> multiTouchObjectCanvas = this.objectCanvas;
                PointInfo pointInfo5 = this.mCurrPt;
                Intrinsics.checkNotNull(pointInfo5);
                T draggableObjectAtPoint = multiTouchObjectCanvas.getDraggableObjectAtPoint(pointInfo5);
                this.selectedObject = draggableObjectAtPoint;
                if (draggableObjectAtPoint != null) {
                    MultiTouchObjectCanvas<T> multiTouchObjectCanvas2 = this.objectCanvas;
                    PointInfo pointInfo6 = this.mCurrPt;
                    Intrinsics.checkNotNull(pointInfo6);
                    T t = this.selectedObject;
                    Intrinsics.checkNotNull(t);
                    if (multiTouchObjectCanvas2.pointInObjectGrabArea(pointInfo6, t)) {
                        this.mode = MODE_ST_GRAB;
                        MultiTouchObjectCanvas<T> multiTouchObjectCanvas3 = this.objectCanvas;
                        T t2 = this.selectedObject;
                        Intrinsics.checkNotNull(t2);
                        PointInfo pointInfo7 = this.mCurrPt;
                        Intrinsics.checkNotNull(pointInfo7);
                        multiTouchObjectCanvas3.selectObject(t2, pointInfo7);
                        anchorAtThisPositionAndScale();
                        PointInfo pointInfo8 = this.mCurrPt;
                        Intrinsics.checkNotNull(pointInfo8);
                        long eventTime = pointInfo8.getEventTime();
                        this.mSettleEndTime = eventTime;
                        this.mSettleStartTime = eventTime;
                    } else {
                        this.mode = MODE_DRAG;
                        MultiTouchObjectCanvas<T> multiTouchObjectCanvas4 = this.objectCanvas;
                        T t3 = this.selectedObject;
                        Intrinsics.checkNotNull(t3);
                        PointInfo pointInfo9 = this.mCurrPt;
                        Intrinsics.checkNotNull(pointInfo9);
                        multiTouchObjectCanvas4.selectObject(t3, pointInfo9);
                        anchorAtThisPositionAndScale();
                        PointInfo pointInfo10 = this.mCurrPt;
                        Intrinsics.checkNotNull(pointInfo10);
                        long eventTime2 = pointInfo10.getEventTime();
                        this.mSettleEndTime = eventTime2;
                        this.mSettleStartTime = eventTime2;
                    }
                }
            }
        } else if (i == MODE_ST_GRAB) {
            if (z) {
                Log.i("MultiTouch", "MODE_ST_GRAB");
            }
            PointInfo pointInfo11 = this.mCurrPt;
            Intrinsics.checkNotNull(pointInfo11);
            if (!pointInfo11.isDown()) {
                this.mode = i2;
                MultiTouchObjectCanvas<T> multiTouchObjectCanvas5 = this.objectCanvas;
                T t4 = this.selectedObject;
                Intrinsics.checkNotNull(t4);
                PointInfo pointInfo12 = this.mCurrPt;
                Intrinsics.checkNotNull(pointInfo12);
                multiTouchObjectCanvas5.selectObject(t4, pointInfo12);
                this.mDragOccurred = false;
            } else {
                performDragOrPinch();
            }
        } else {
            int i3 = MODE_DRAG;
            if (i == i3) {
                if (z) {
                    Log.i("MultiTouch", "MODE_DRAG");
                }
                PointInfo pointInfo13 = this.mCurrPt;
                Intrinsics.checkNotNull(pointInfo13);
                if (!pointInfo13.isDown()) {
                    this.mode = i2;
                    MultiTouchObjectCanvas<T> multiTouchObjectCanvas6 = this.objectCanvas;
                    T t5 = this.selectedObject;
                    Intrinsics.checkNotNull(t5);
                    PointInfo pointInfo14 = this.mCurrPt;
                    Intrinsics.checkNotNull(pointInfo14);
                    multiTouchObjectCanvas6.selectObject(t5, pointInfo14);
                    this.mDragOccurred = false;
                } else {
                    PointInfo pointInfo15 = this.mCurrPt;
                    Intrinsics.checkNotNull(pointInfo15);
                    if (pointInfo15.isMultiTouch()) {
                        this.mode = MODE_PINCH;
                        anchorAtThisPositionAndScale();
                        PointInfo pointInfo16 = this.mCurrPt;
                        Intrinsics.checkNotNull(pointInfo16);
                        long eventTime3 = pointInfo16.getEventTime();
                        this.mSettleStartTime = eventTime3;
                        this.mSettleEndTime = eventTime3 + EVENT_SETTLE_TIME_INTERVAL;
                    } else {
                        PointInfo pointInfo17 = this.mCurrPt;
                        Intrinsics.checkNotNull(pointInfo17);
                        if (pointInfo17.getEventTime() < this.mSettleEndTime) {
                            anchorAtThisPositionAndScale();
                        } else {
                            performDragOrPinch();
                        }
                    }
                }
            } else if (i == MODE_PINCH) {
                if (z) {
                    Log.i("MultiTouch", "MODE_PINCH");
                }
                PointInfo pointInfo18 = this.mCurrPt;
                Intrinsics.checkNotNull(pointInfo18);
                if (pointInfo18.isMultiTouch()) {
                    PointInfo pointInfo19 = this.mCurrPt;
                    Intrinsics.checkNotNull(pointInfo19);
                    if (pointInfo19.isDown()) {
                        PointInfo pointInfo20 = this.mCurrPt;
                        Intrinsics.checkNotNull(pointInfo20);
                        float x = pointInfo20.getX();
                        PointInfo pointInfo21 = this.mPrevPt;
                        Intrinsics.checkNotNull(pointInfo21);
                        float abs = Math.abs(x - pointInfo21.getX());
                        float f = MAX_MULTITOUCH_POS_JUMP_SIZE;
                        if (abs <= f) {
                            PointInfo pointInfo22 = this.mCurrPt;
                            Intrinsics.checkNotNull(pointInfo22);
                            float y = pointInfo22.getY();
                            PointInfo pointInfo23 = this.mPrevPt;
                            Intrinsics.checkNotNull(pointInfo23);
                            if (Math.abs(y - pointInfo23.getY()) <= f) {
                                PointInfo pointInfo24 = this.mCurrPt;
                                Intrinsics.checkNotNull(pointInfo24);
                                float multiTouchWidth = pointInfo24.getMultiTouchWidth();
                                PointInfo pointInfo25 = this.mPrevPt;
                                Intrinsics.checkNotNull(pointInfo25);
                                float f2 = MAX_MULTITOUCH_DIM_JUMP_SIZE;
                                if (Math.abs(multiTouchWidth - pointInfo25.getMultiTouchWidth()) * 0.5f <= f2) {
                                    PointInfo pointInfo26 = this.mCurrPt;
                                    Intrinsics.checkNotNull(pointInfo26);
                                    float multiTouchHeight = pointInfo26.getMultiTouchHeight();
                                    PointInfo pointInfo27 = this.mPrevPt;
                                    Intrinsics.checkNotNull(pointInfo27);
                                    if (Math.abs(multiTouchHeight - pointInfo27.getMultiTouchHeight()) * 0.5f <= f2) {
                                        PointInfo pointInfo28 = this.mCurrPt;
                                        Intrinsics.checkNotNull(pointInfo28);
                                        if (pointInfo28.getEventTime() < this.mSettleEndTime) {
                                            anchorAtThisPositionAndScale();
                                        } else {
                                            performDragOrPinch();
                                        }
                                    }
                                }
                            }
                        }
                        anchorAtThisPositionAndScale();
                        PointInfo pointInfo29 = this.mCurrPt;
                        Intrinsics.checkNotNull(pointInfo29);
                        long eventTime4 = pointInfo29.getEventTime();
                        this.mSettleStartTime = eventTime4;
                        this.mSettleEndTime = eventTime4 + EVENT_SETTLE_TIME_INTERVAL;
                    }
                }
                PointInfo pointInfo30 = this.mCurrPt;
                Intrinsics.checkNotNull(pointInfo30);
                if (!pointInfo30.isDown()) {
                    this.mode = i2;
                    MultiTouchObjectCanvas<T> multiTouchObjectCanvas7 = this.objectCanvas;
                    T t6 = this.selectedObject;
                    Intrinsics.checkNotNull(t6);
                    PointInfo pointInfo31 = this.mCurrPt;
                    Intrinsics.checkNotNull(pointInfo31);
                    multiTouchObjectCanvas7.selectObject(t6, pointInfo31);
                } else {
                    this.mode = i3;
                    anchorAtThisPositionAndScale();
                    PointInfo pointInfo32 = this.mCurrPt;
                    Intrinsics.checkNotNull(pointInfo32);
                    long eventTime5 = pointInfo32.getEventTime();
                    this.mSettleStartTime = eventTime5;
                    this.mSettleEndTime = eventTime5 + EVENT_SETTLE_TIME_INTERVAL;
                }
            }
        }
        if (z) {
            StringBuilder append4 = new StringBuilder("Got here 7 - ").append(this.mode).append(' ');
            PointInfo pointInfo33 = this.mCurrPt;
            Intrinsics.checkNotNull(pointInfo33);
            StringBuilder append5 = append4.append(pointInfo33.getNumTouchPoints()).append(' ');
            PointInfo pointInfo34 = this.mCurrPt;
            Intrinsics.checkNotNull(pointInfo34);
            StringBuilder append6 = append5.append(pointInfo34.isDown());
            PointInfo pointInfo35 = this.mCurrPt;
            Intrinsics.checkNotNull(pointInfo35);
            Log.i("MultiTouch", append6.append(pointInfo35.isMultiTouch()).toString());
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u00108\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u0004H\u0002J\u000e\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\u0000JI\u0010:\u001a\u00020;2\u0006\u0010=\u001a\u00020\u00042\u0006\u00100\u001a\u00020-2\u0006\u00104\u001a\u00020-2\u0006\u0010*\u001a\u00020-2\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0012H\u0086\u0002R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u0012@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0019\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001bR\u0011\u0010\u001e\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001bR\u0011\u0010 \u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b!\u0010\u001bR\u0011\u0010\"\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b#\u0010\u001bR\u001e\u0010$\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0007R\u0011\u0010&\u001a\u00020'¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u001e\u0010*\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001bR\u0011\u0010,\u001a\u00020-¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u001e\u00100\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001bR\u0011\u00102\u001a\u00020-¢\u0006\b\n\u0000\u001a\u0004\b3\u0010/R\u001e\u00104\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u001bR\u0011\u00106\u001a\u00020-¢\u0006\b\n\u0000\u001a\u0004\b7\u0010/¨\u0006>"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$PointInfo;", "", "()V", "<set-?>", "", "action", "getAction", "()I", "angle", "", "angleIsCalculated", "", "diameter", "diameterIsCalculated", "diameterSq", "diameterSqIsCalculated", "dx", "dy", "", "eventTime", "getEventTime", "()J", "isDown", "()Z", "isMultiTouch", "multiTouchAngle", "getMultiTouchAngle", "()F", "multiTouchDiameter", "getMultiTouchDiameter", "multiTouchDiameterSq", "getMultiTouchDiameterSq", "multiTouchHeight", "getMultiTouchHeight", "multiTouchWidth", "getMultiTouchWidth", "numTouchPoints", "getNumTouchPoints", "pointerIds", "", "getPointerIds", "()[I", "pressure", "getPressure", "pressures", "", "getPressures", "()[F", "x", "getX", "xs", "getXs", "y", "getY", "ys", "getYs", "julery_isqrt", "val", "set", "", "other", "numPoints", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public static final class PointInfo {
        private int action;
        private float angle;
        private boolean angleIsCalculated;
        private float diameter;
        private boolean diameterIsCalculated;
        private float diameterSq;
        private boolean diameterSqIsCalculated;
        private float dx;
        private float dy;
        private long eventTime;
        private boolean isDown;
        private boolean isMultiTouch;
        private int numTouchPoints;
        private float pressure;
        private float x;
        private float y;
        private final float[] xs = new float[PG_MultiTouchController.Companion.getMAX_TOUCH_POINTS()];
        private final float[] ys = new float[PG_MultiTouchController.Companion.getMAX_TOUCH_POINTS()];
        private final float[] pressures = new float[PG_MultiTouchController.Companion.getMAX_TOUCH_POINTS()];
        private final int[] pointerIds = new int[PG_MultiTouchController.Companion.getMAX_TOUCH_POINTS()];

        private final int julery_isqrt(int i) {
            int i2 = 0;
            int i3 = 32768;
            int i4 = 15;
            while (true) {
                i3 >>= 1;
                int i5 = (i2 << 1) + i3;
                int i6 = i4 - 1;
                if (i >= (i5 << i4)) {
                    i2 += i3;
                    i -= i5;
                }
                if ((i3 >> 1) <= 0) {
                    return i2;
                }
                i4 = i6;
            }
        }

        public final int getNumTouchPoints() {
            return this.numTouchPoints;
        }

        public final float[] getXs() {
            return this.xs;
        }

        public final float[] getYs() {
            return this.ys;
        }

        public final float[] getPressures() {
            return this.pressures;
        }

        public final int[] getPointerIds() {
            return this.pointerIds;
        }

        public final float getX() {
            return this.x;
        }

        public final float getY() {
            return this.y;
        }

        public final float getPressure() {
            return this.pressure;
        }

        public final boolean isDown() {
            return this.isDown;
        }

        public final boolean isMultiTouch() {
            return this.isMultiTouch;
        }

        public final int getAction() {
            return this.action;
        }

        public final long getEventTime() {
            return this.eventTime;
        }

        public final float getMultiTouchWidth() {
            if (this.isMultiTouch) {
                return this.dx;
            }
            return 0.0f;
        }

        public final float getMultiTouchHeight() {
            if (this.isMultiTouch) {
                return this.dy;
            }
            return 0.0f;
        }

        public final float getMultiTouchDiameterSq() {
            float f;
            if (!this.diameterSqIsCalculated) {
                if (this.isMultiTouch) {
                    float f2 = this.dx;
                    float f3 = this.dy;
                    f = (f2 * f2) + (f3 * f3);
                } else {
                    f = 0.0f;
                }
                this.diameterSq = f;
                this.diameterSqIsCalculated = true;
            }
            return this.diameterSq;
        }

        public final float getMultiTouchDiameter() {
            if (!this.diameterIsCalculated) {
                if (!this.isMultiTouch) {
                    this.diameter = 0.0f;
                } else {
                    float multiTouchDiameterSq = getMultiTouchDiameterSq();
                    float julery_isqrt = (multiTouchDiameterSq > 0.0f ? 1 : (multiTouchDiameterSq == 0.0f ? 0 : -1)) == 0 ? 0.0f : julery_isqrt((int) (256 * multiTouchDiameterSq)) / 16.0f;
                    this.diameter = julery_isqrt;
                    float f = this.dx;
                    if (julery_isqrt < f) {
                        this.diameter = f;
                    }
                    float f2 = this.diameter;
                    float f3 = this.dy;
                    if (f2 < f3) {
                        this.diameter = f3;
                    }
                }
                this.diameterIsCalculated = true;
            }
            return this.diameter;
        }

        public final float getMultiTouchAngle() {
            if (!this.angleIsCalculated) {
                if (!this.isMultiTouch) {
                    this.angle = 0.0f;
                } else {
                    float[] fArr = this.ys;
                    float[] fArr2 = this.xs;
                    this.angle = (float) Math.atan2(fArr[1] - fArr[0], fArr2[1] - fArr2[0]);
                }
                this.angleIsCalculated = true;
            }
            return this.angle;
        }

        public final void set(int i, float[] x, float[] y, float[] pressure, int[] pointerIds, int i2, boolean z, long j) {
            Intrinsics.checkNotNullParameter(x, "x");
            Intrinsics.checkNotNullParameter(y, "y");
            Intrinsics.checkNotNullParameter(pressure, "pressure");
            Intrinsics.checkNotNullParameter(pointerIds, "pointerIds");
            if (PG_MultiTouchController.Companion.getDEBUG()) {
                Log.i("MultiTouch", "Got here 8 - " + i + ' ' + x[0] + ' ' + y[0] + ' ' + (i > 1 ? x[1] : x[0]) + ' ' + (i > 1 ? y[1] : y[0]) + ' ' + i2 + ' ' + z);
            }
            this.eventTime = j;
            this.action = i2;
            this.numTouchPoints = i;
            for (int i3 = 0; i3 < i; i3++) {
                this.xs[i3] = x[i3];
                this.ys[i3] = y[i3];
                this.pressures[i3] = pressure[i3];
                this.pointerIds[i3] = pointerIds[i3];
            }
            this.isDown = z;
            boolean z2 = i >= 2;
            this.isMultiTouch = z2;
            if (z2) {
                float f = x[0];
                float f2 = x[1];
                this.x = (f + f2) * 0.5f;
                this.y = (y[0] + y[1]) * 0.5f;
                this.pressure = (pressure[0] + pressure[1]) * 0.5f;
                this.dx = Math.abs(f2 - f);
                this.dy = Math.abs(y[1] - y[0]);
            } else {
                this.x = x[0];
                this.y = y[0];
                this.pressure = pressure[0];
                this.dy = 0.0f;
                this.dx = 0.0f;
            }
            this.angleIsCalculated = false;
            this.diameterIsCalculated = false;
            this.diameterSqIsCalculated = false;
        }

        public final void set(PointInfo other) {
            Intrinsics.checkNotNullParameter(other, "other");
            int i = other.numTouchPoints;
            this.numTouchPoints = i;
            for (int i2 = 0; i2 < i; i2++) {
                this.xs[i2] = other.xs[i2];
                this.ys[i2] = other.ys[i2];
                this.pressures[i2] = other.pressures[i2];
                this.pointerIds[i2] = other.pointerIds[i2];
            }
            this.x = other.x;
            this.y = other.y;
            this.pressure = other.pressure;
            this.dx = other.dx;
            this.dy = other.dy;
            this.diameter = other.diameter;
            this.diameterSq = other.diameterSq;
            this.angle = other.angle;
            this.isDown = other.isDown;
            this.action = other.action;
            this.isMultiTouch = other.isMultiTouch;
            this.diameterIsCalculated = other.diameterIsCalculated;
            this.diameterSqIsCalculated = other.diameterSqIsCalculated;
            this.angleIsCalculated = other.angleIsCalculated;
            this.eventTime = other.eventTime;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010#\u001a\u00020\u0004J\u0006\u0010$\u001a\u00020\u0004J\u0006\u0010%\u001a\u00020\u0004J\u0006\u0010&\u001a\u00020\u0004JQ\u0010'\u001a\u00020(2\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u0004H\u0086\u0002J9\u0010'\u001a\u00020(2\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u0004H\u0086\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\u001a\u0010\u001b\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\u001e\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0006R\u001e\u0010!\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0006¨\u0006-"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$PositionAndScale;", "", "()V", "angl", "", "getAngl", "()F", "setAngl", "(F)V", "scle", "getScle", "setScle", "scleX", "getScleX", "setScleX", "scleY", "getScleY", "setScleY", "updateAngle", "", "getUpdateAngle", "()Z", "setUpdateAngle", "(Z)V", "updateScale", "getUpdateScale", "setUpdateScale", "updateScaleXY", "getUpdateScaleXY", "setUpdateScaleXY", "<set-?>", "xOff", "getXOff", "yOff", "getYOff", "getAngle", "getScale", "getScaleX", "getScaleY", "set", "", "scale", "scaleX", "scaleY", "angle", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public static final class PositionAndScale {
        private float angl;
        private float scle;
        private float scleX;
        private float scleY;
        private boolean updateAngle;
        private boolean updateScale;
        private boolean updateScaleXY;
        private float xOff;
        private float yOff;

        public final float getXOff() {
            return this.xOff;
        }

        public final float getYOff() {
            return this.yOff;
        }

        public final float getScle() {
            return this.scle;
        }

        public final void setScle(float f) {
            this.scle = f;
        }

        public final float getScleX() {
            return this.scleX;
        }

        public final void setScleX(float f) {
            this.scleX = f;
        }

        public final float getScleY() {
            return this.scleY;
        }

        public final void setScleY(float f) {
            this.scleY = f;
        }

        public final float getAngl() {
            return this.angl;
        }

        public final void setAngl(float f) {
            this.angl = f;
        }

        public final boolean getUpdateScale() {
            return this.updateScale;
        }

        public final void setUpdateScale(boolean z) {
            this.updateScale = z;
        }

        public final boolean getUpdateScaleXY() {
            return this.updateScaleXY;
        }

        public final void setUpdateScaleXY(boolean z) {
            this.updateScaleXY = z;
        }

        public final boolean getUpdateAngle() {
            return this.updateAngle;
        }

        public final void setUpdateAngle(boolean z) {
            this.updateAngle = z;
        }

        public final void set(float f, float f2, boolean z, float f3, boolean z2, float f4, float f5, boolean z3, float f6) {
            this.xOff = f;
            this.yOff = f2;
            this.updateScale = z;
            if (f3 == 0.0f) {
                f3 = 1.0f;
            }
            this.scle = f3;
            this.updateScaleXY = z2;
            if (f4 == 0.0f) {
                f4 = 1.0f;
            }
            this.scleX = f4;
            if (f5 == 0.0f) {
                f5 = 1.0f;
            }
            this.scleY = f5;
            this.updateAngle = z3;
            this.angl = f6;
        }

        public final void set(float f, float f2, float f3, float f4, float f5, float f6) {
            this.xOff = f;
            this.yOff = f2;
            if (f3 == 0.0f) {
                f3 = 1.0f;
            }
            this.scle = f3;
            if (f4 == 0.0f) {
                f4 = 1.0f;
            }
            this.scleX = f4;
            if (f5 == 0.0f) {
                f5 = 1.0f;
            }
            this.scleY = f5;
            this.angl = f6;
        }

        public final float getScale() {
            if (this.updateScale) {
                return this.scle;
            }
            return 1.0f;
        }

        public final float getScaleX() {
            if (this.updateScaleXY) {
                return this.scleX;
            }
            return 1.0f;
        }

        public final float getScaleY() {
            if (this.updateScaleXY) {
                return this.scleY;
            }
            return 1.0f;
        }

        public final float getAngle() {
            if (this.updateAngle) {
                return this.angl;
            }
            return 0.0f;
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0014\u0010\u0015\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0014\u0010\u0017\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0014\u0010\u0019\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u000e\u0010\u001b\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010%\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\tR\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchController$Companion;", "", "()V", "ACTION_POINTER_INDEX_SHIFT", "", "ACTION_POINTER_UP", "DEBUG", "", "getDEBUG", "()Z", "EVENT_SETTLE_TIME_INTERVAL", "", "MAX_MULTITOUCH_DIM_JUMP_SIZE", "", "MAX_MULTITOUCH_POS_JUMP_SIZE", "MAX_TOUCH_POINTS", "getMAX_TOUCH_POINTS", "()I", "MIN_MULTITOUCH_SEPARATION", "MODE_DRAG", "getMODE_DRAG", "MODE_NOTHING", "getMODE_NOTHING", "MODE_PINCH", "getMODE_PINCH", "MODE_ST_GRAB", "getMODE_ST_GRAB", "THRESHOLD", "m_getHistoricalPressure", "Ljava/lang/reflect/Method;", "m_getHistoricalX", "m_getHistoricalY", "m_getPointerCount", "m_getPointerId", "m_getPressure", "m_getX", "m_getY", "multiTouchSupported", "getMultiTouchSupported", "pointerIds", "", "pressureVals", "", "xVals", "yVals", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getMAX_TOUCH_POINTS() {
            return PG_MultiTouchController.MAX_TOUCH_POINTS;
        }

        public final boolean getDEBUG() {
            return PG_MultiTouchController.DEBUG;
        }

        public final int getMODE_NOTHING() {
            return PG_MultiTouchController.MODE_NOTHING;
        }

        public final int getMODE_DRAG() {
            return PG_MultiTouchController.MODE_DRAG;
        }

        public final int getMODE_PINCH() {
            return PG_MultiTouchController.MODE_PINCH;
        }

        public final int getMODE_ST_GRAB() {
            return PG_MultiTouchController.MODE_ST_GRAB;
        }

        public final boolean getMultiTouchSupported() {
            return PG_MultiTouchController.multiTouchSupported;
        }
    }

    static {
        boolean z = true;
        ACTION_POINTER_UP = 6;
        ACTION_POINTER_INDEX_SHIFT = 8;
        try {
            m_getPointerCount = MotionEvent.class.getMethod("getPointerCount", new Class[0]);
            m_getPointerId = MotionEvent.class.getMethod("getPointerId", Integer.TYPE);
            m_getPressure = MotionEvent.class.getMethod("getPressure", Integer.TYPE);
            m_getHistoricalX = MotionEvent.class.getMethod("getHistoricalX", Integer.TYPE, Integer.TYPE);
            m_getHistoricalY = MotionEvent.class.getMethod("getHistoricalY", Integer.TYPE, Integer.TYPE);
            m_getHistoricalPressure = MotionEvent.class.getMethod("getHistoricalPressure", Integer.TYPE, Integer.TYPE);
            m_getX = MotionEvent.class.getMethod("getX", Integer.TYPE);
            m_getY = MotionEvent.class.getMethod("getY", Integer.TYPE);
        } catch (Exception e) {
            Log.e("PG_MultiTouchController", "static initializer failed", e);
            z = false;
        }
        multiTouchSupported = z;
        if (z) {
            try {
                ACTION_POINTER_UP = MotionEvent.class.getField("ACTION_POINTER_UP").getInt(null);
                ACTION_POINTER_INDEX_SHIFT = MotionEvent.class.getField("ACTION_POINTER_INDEX_SHIFT").getInt(null);
            } catch (Exception unused) {
            }
        }
        int i = MAX_TOUCH_POINTS;
        xVals = new float[i];
        yVals = new float[i];
        pressureVals = new float[i];
        pointerIds = new int[i];
    }
}
