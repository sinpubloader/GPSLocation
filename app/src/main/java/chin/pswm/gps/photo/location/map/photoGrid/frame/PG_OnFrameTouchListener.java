package chin.pswm.gps.photo.location.map.photoGrid.frame;

import android.view.MotionEvent;

import androidx.core.app.NotificationCompat;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/frame/PG_OnFrameTouchListener;", "", "onFrameDoubleClick", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "onFrameTouch", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public interface PG_OnFrameTouchListener {
    void onFrameDoubleClick(MotionEvent motionEvent);

    void onFrameTouch(MotionEvent motionEvent);
}
