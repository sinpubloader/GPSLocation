    package chin.pswm.gps.photo.location.map.photoGrid.util;

    import java.util.Comparator;

    import kotlin.jvm.functions.Function2;

public final  class PG_FrameImageUtils$$ExternalSyntheticLambda1 implements Comparator {
    public final  Function2 f$0;

    public  PG_FrameImageUtils$$ExternalSyntheticLambda1(Function2 function2) {
        this.f$0 = function2;
    }

    public final int compare(Object obj, Object obj2) {
        return PG_FrameImageUtils.loadFrameImagesSelect$lambda$1(this.f$0, obj, obj2);
    }
}