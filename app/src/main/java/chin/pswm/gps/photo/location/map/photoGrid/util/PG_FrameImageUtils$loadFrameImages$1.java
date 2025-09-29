    package chin.pswm.gps.photo.location.map.photoGrid.util;

    import androidx.appcompat.app.AppCompatActivity;

    import chin.pswm.gps.photo.location.map.photoGrid.model.PG_TemplatePGItem;
    import kotlin.Metadata;
    import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "lhs", "Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_TemplatePGItem;", "kotlin.jvm.PlatformType", "rhs", "invoke", "(Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_TemplatePGItem;Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_TemplatePGItem;)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
class PG_FrameImageUtils$loadFrameImages$1 extends AppCompatActivity implements Function2<PG_TemplatePGItem, PG_TemplatePGItem, Integer> {
    public static final PG_FrameImageUtils$loadFrameImages$1 INSTANCE = new PG_FrameImageUtils$loadFrameImages$1();

    PG_FrameImageUtils$loadFrameImages$1() {
        super(2);
    }

    public Integer invoke(PG_TemplatePGItem pG_TemplatePGItem, PG_TemplatePGItem pG_TemplatePGItem2) {
        return Integer.valueOf(pG_TemplatePGItem.getPGPhotoItemList().size() - pG_TemplatePGItem2.getPGPhotoItemList().size());
    }
}