    package chin.pswm.gps.photo.location.map.photoGrid.activity;

    import android.view.ViewTreeObserver;

    import chin.pswm.gps.photo.location.map.photoGrid.model.PG_TemplatePGItem;
    import chin.pswm.gps.photo.location.map.photoGrid.util.PG_ImageUtils;
    import kotlin.Metadata;
    import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"chin/pswm/gps/photo/location/map/photoGrid/activity/PG_CollageActivity$onCreate$2", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "onGlobalLayout", "", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PG_CollageActivity$onCreate$2 implements ViewTreeObserver.OnGlobalLayoutListener {
    final  PG_CollageActivity this$0;

    PG_CollageActivity$onCreate$2(PG_CollageActivity pG_CollageActivity) {
        this.this$0 = pG_CollageActivity;
    }

    public void onGlobalLayout() {
        this.this$0.setMOutputScale(PG_ImageUtils.INSTANCE.calculateOutputScaleFactor(this.this$0.getBinding().rlContainer.getWidth(), this.this$0.getBinding().rlContainer.getHeight()));
        PG_CollageActivity pG_CollageActivity = this.this$0;
        PG_TemplatePGItem mSelectedTemplateItem = pG_CollageActivity.getMSelectedTemplateItem();
        Intrinsics.checkNotNull(mSelectedTemplateItem);
        pG_CollageActivity.buildLayout(mSelectedTemplateItem);
        this.this$0.getBinding().rlContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }
}