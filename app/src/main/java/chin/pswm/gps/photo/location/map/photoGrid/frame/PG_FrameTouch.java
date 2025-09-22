package chin.pswm.gps.photo.location.map.photoGrid.frame;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/frame/PG_FrameTouch;", "Lchin/pswm/gps/photo/location/map/photoGrid/frame/PG_OnFrameTouchListener;", "()V", "isImageFrameMoving", "", "()Z", "setImageFrameMoving", "(Z)V", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public abstract class PG_FrameTouch implements PG_OnFrameTouchListener {
    private boolean isImageFrameMoving;

    public final boolean isImageFrameMoving() {
        return this.isImageFrameMoving;
    }

    public final void setImageFrameMoving(boolean z) {
        this.isImageFrameMoving = z;
    }
}
