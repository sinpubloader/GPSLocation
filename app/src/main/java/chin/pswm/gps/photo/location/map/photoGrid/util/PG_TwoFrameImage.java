package chin.pswm.gps.photo.location.map.photoGrid.util;

import android.graphics.PointF;

import java.util.ArrayList;

import chin.pswm.gps.photo.location.map.photoGrid.model.PG_TemplatePGItem;
import chin.pswm.gps.photo.location.map.photoGrid.template.PG_PhotoItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0004J\u0006\u0010\u0007\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0004J\u0006\u0010\t\u001a\u00020\u0004J\u0006\u0010\n\u001a\u00020\u0004J\u0006\u0010\u000b\u001a\u00020\u0004J\u0006\u0010\f\u001a\u00020\u0004J\u0006\u0010\r\u001a\u00020\u0004J\u0006\u0010\u000e\u001a\u00020\u0004J\u0006\u0010\u000f\u001a\u00020\u0004¨\u0006\u0010"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/util/PG_TwoFrameImage;", "", "()V", "collage_2_0", "Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_TemplatePGItem;", "collage_2_1", "collage_2_10", "collage_2_11", "collage_2_2", "collage_2_3", "collage_2_4", "collage_2_5", "collage_2_6", "collage_2_7", "collage_2_8", "collage_2_9", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_TwoFrameImage {
    public static final PG_TwoFrameImage INSTANCE = new PG_TwoFrameImage();

    private PG_TwoFrameImage() {
    }

    public final PG_TemplatePGItem collage_2_0() {
        PG_TemplatePGItem collage$app_release = PG_FrameImageUtils.INSTANCE.collage$app_release("collage_2_0.png");
        PG_PhotoItem pG_PhotoItem = new PG_PhotoItem();
        pG_PhotoItem.setIndex(0);
        pG_PhotoItem.getBound().set(0.0f, 0.0f, 0.5f, 1.0f);
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem);
        PG_PhotoItem pG_PhotoItem2 = new PG_PhotoItem();
        pG_PhotoItem2.setIndex(1);
        pG_PhotoItem2.getBound().set(0.5f, 0.0f, 1.0f, 1.0f);
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem2);
        return collage$app_release;
    }

    public final PG_TemplatePGItem collage_2_1() {
        PG_TemplatePGItem collage$app_release = PG_FrameImageUtils.INSTANCE.collage$app_release("collage_2_1.png");
        PG_PhotoItem pG_PhotoItem = new PG_PhotoItem();
        pG_PhotoItem.setIndex(0);
        pG_PhotoItem.getBound().set(0.0f, 0.0f, 1.0f, 0.5f);
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem);
        PG_PhotoItem pG_PhotoItem2 = new PG_PhotoItem();
        pG_PhotoItem2.setIndex(1);
        pG_PhotoItem2.getBound().set(0.0f, 0.5f, 1.0f, 1.0f);
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem2);
        return collage$app_release;
    }

    public final PG_TemplatePGItem collage_2_2() {
        PG_TemplatePGItem collage$app_release = PG_FrameImageUtils.INSTANCE.collage$app_release("collage_2_2.png");
        PG_PhotoItem pG_PhotoItem = new PG_PhotoItem();
        pG_PhotoItem.setIndex(0);
        pG_PhotoItem.getBound().set(0.0f, 0.0f, 1.0f, 0.333f);
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem);
        PG_PhotoItem pG_PhotoItem2 = new PG_PhotoItem();
        pG_PhotoItem2.setIndex(1);
        pG_PhotoItem2.getBound().set(0.0f, 0.333f, 1.0f, 1.0f);
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem2);
        return collage$app_release;
    }

    public final PG_TemplatePGItem collage_2_3() {
        PG_TemplatePGItem collage$app_release = PG_FrameImageUtils.INSTANCE.collage$app_release("collage_2_3.png");
        PG_PhotoItem pG_PhotoItem = new PG_PhotoItem();
        pG_PhotoItem.setIndex(0);
        pG_PhotoItem.getBound().set(0.0f, 0.0f, 1.0f, 0.667f);
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 0.5f));
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem);
        PG_PhotoItem pG_PhotoItem2 = new PG_PhotoItem();
        pG_PhotoItem2.setIndex(1);
        pG_PhotoItem2.getBound().set(0.0f, 0.333f, 1.0f, 1.0f);
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 0.5f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem2);
        return collage$app_release;
    }

    public final PG_TemplatePGItem collage_2_4() {
        PG_TemplatePGItem collage$app_release = PG_FrameImageUtils.INSTANCE.collage$app_release("collage_2_4.png");
        PG_PhotoItem pG_PhotoItem = new PG_PhotoItem();
        pG_PhotoItem.setIndex(0);
        pG_PhotoItem.getBound().set(0.0f, 0.0f, 1.0f, 0.5714f);
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem.getPointList().add(new PointF(0.8333f, 0.75f));
        pG_PhotoItem.getPointList().add(new PointF(0.6666f, 1.0f));
        pG_PhotoItem.getPointList().add(new PointF(0.5f, 0.75f));
        pG_PhotoItem.getPointList().add(new PointF(0.3333f, 1.0f));
        pG_PhotoItem.getPointList().add(new PointF(0.1666f, 0.75f));
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem);
        PG_PhotoItem pG_PhotoItem2 = new PG_PhotoItem();
        pG_PhotoItem2.setIndex(1);
        pG_PhotoItem2.getBound().set(0.0f, 0.4286f, 1.0f, 1.0f);
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 0.25f));
        pG_PhotoItem2.getPointList().add(new PointF(0.1666f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(0.3333f, 0.25f));
        pG_PhotoItem2.getPointList().add(new PointF(0.5f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(0.6666f, 0.25f));
        pG_PhotoItem2.getPointList().add(new PointF(0.8333f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 0.25f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem2);
        return collage$app_release;
    }

    public final PG_TemplatePGItem collage_2_5() {
        PG_TemplatePGItem collage$app_release = PG_FrameImageUtils.INSTANCE.collage$app_release("collage_2_5.png");
        PG_PhotoItem pG_PhotoItem = new PG_PhotoItem();
        pG_PhotoItem.setIndex(0);
        pG_PhotoItem.getBound().set(0.0f, 0.0f, 1.0f, 0.6667f);
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem);
        PG_PhotoItem pG_PhotoItem2 = new PG_PhotoItem();
        pG_PhotoItem2.setIndex(1);
        pG_PhotoItem2.getBound().set(0.0f, 0.6667f, 1.0f, 1.0f);
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem2);
        return collage$app_release;
    }

    public final PG_TemplatePGItem collage_2_6() {
        PG_TemplatePGItem collage$app_release = PG_FrameImageUtils.INSTANCE.collage$app_release("collage_2_6.png");
        PG_PhotoItem pG_PhotoItem = new PG_PhotoItem();
        pG_PhotoItem.setIndex(0);
        pG_PhotoItem.getBound().set(0.0f, 0.0f, 1.0f, 0.667f);
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 0.5f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem);
        PG_PhotoItem pG_PhotoItem2 = new PG_PhotoItem();
        pG_PhotoItem2.setIndex(1);
        pG_PhotoItem2.getBound().set(0.0f, 0.333f, 1.0f, 1.0f);
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 0.5f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem2);
        return collage$app_release;
    }

    public final PG_TemplatePGItem collage_2_7() {
        PG_TemplatePGItem collage$app_release = PG_FrameImageUtils.INSTANCE.collage$app_release("collage_2_7.png");
        PG_PhotoItem pG_PhotoItem = new PG_PhotoItem();
        pG_PhotoItem.setIndex(0);
        pG_PhotoItem.getBound().set(0.0f, 0.0f, 1.0f, 1.0f);
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 1.0f));
        pG_PhotoItem.setClearAreaPoints(new ArrayList<>());
        ArrayList<PointF> clearAreaPoints = pG_PhotoItem.getClearAreaPoints();
        Intrinsics.checkNotNull(clearAreaPoints);
        clearAreaPoints.add(new PointF(0.6f, 0.6f));
        ArrayList<PointF> clearAreaPoints2 = pG_PhotoItem.getClearAreaPoints();
        Intrinsics.checkNotNull(clearAreaPoints2);
        clearAreaPoints2.add(new PointF(0.9f, 0.6f));
        ArrayList<PointF> clearAreaPoints3 = pG_PhotoItem.getClearAreaPoints();
        Intrinsics.checkNotNull(clearAreaPoints3);
        clearAreaPoints3.add(new PointF(0.9f, 0.9f));
        ArrayList<PointF> clearAreaPoints4 = pG_PhotoItem.getClearAreaPoints();
        Intrinsics.checkNotNull(clearAreaPoints4);
        clearAreaPoints4.add(new PointF(0.6f, 0.9f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem);
        PG_PhotoItem pG_PhotoItem2 = new PG_PhotoItem();
        pG_PhotoItem2.setIndex(1);
        pG_PhotoItem2.getBound().set(0.6f, 0.6f, 0.9f, 0.9f);
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem2);
        return collage$app_release;
    }

    public final PG_TemplatePGItem collage_2_8() {
        PG_TemplatePGItem collage$app_release = PG_FrameImageUtils.INSTANCE.collage$app_release("collage_2_8.png");
        PG_PhotoItem pG_PhotoItem = new PG_PhotoItem();
        pG_PhotoItem.setIndex(0);
        pG_PhotoItem.getBound().set(0.0f, 0.0f, 0.3333f, 1.0f);
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem);
        PG_PhotoItem pG_PhotoItem2 = new PG_PhotoItem();
        pG_PhotoItem2.setIndex(1);
        pG_PhotoItem2.getBound().set(0.3333f, 0.0f, 1.0f, 1.0f);
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem2);
        return collage$app_release;
    }

    public final PG_TemplatePGItem collage_2_9() {
        PG_TemplatePGItem collage$app_release = PG_FrameImageUtils.INSTANCE.collage$app_release("collage_2_9.png");
        PG_PhotoItem pG_PhotoItem = new PG_PhotoItem();
        pG_PhotoItem.setIndex(0);
        pG_PhotoItem.getBound().set(0.0f, 0.0f, 0.6667f, 1.0f);
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(0.5f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem);
        PG_PhotoItem pG_PhotoItem2 = new PG_PhotoItem();
        pG_PhotoItem2.setIndex(1);
        pG_PhotoItem2.getBound().set(0.3333f, 0.0f, 1.0f, 1.0f);
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem2.getPointList().add(new PointF(0.5f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem2);
        return collage$app_release;
    }

    public final PG_TemplatePGItem collage_2_10() {
        PG_TemplatePGItem collage$app_release = PG_FrameImageUtils.INSTANCE.collage$app_release("collage_2_10.png");
        PG_PhotoItem pG_PhotoItem = new PG_PhotoItem();
        pG_PhotoItem.setIndex(0);
        pG_PhotoItem.getBound().set(0.0f, 0.0f, 0.667f, 1.0f);
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem);
        PG_PhotoItem pG_PhotoItem2 = new PG_PhotoItem();
        pG_PhotoItem2.setIndex(1);
        pG_PhotoItem2.getBound().set(0.667f, 0.0f, 1.0f, 1.0f);
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem2);
        return collage$app_release;
    }

    public final PG_TemplatePGItem collage_2_11() {
        PG_TemplatePGItem collage$app_release = PG_FrameImageUtils.INSTANCE.collage$app_release("collage_2_11.png");
        PG_PhotoItem pG_PhotoItem = new PG_PhotoItem();
        pG_PhotoItem.setIndex(0);
        pG_PhotoItem.getBound().set(0.0f, 0.0f, 0.667f, 1.0f);
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem.getPointList().add(new PointF(0.5f, 1.0f));
        pG_PhotoItem.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem);
        PG_PhotoItem pG_PhotoItem2 = new PG_PhotoItem();
        pG_PhotoItem.setIndex(1);
        pG_PhotoItem2.getBound().set(0.333f, 0.0f, 1.0f, 1.0f);
        pG_PhotoItem2.getPointList().add(new PointF(0.5f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 0.0f));
        pG_PhotoItem2.getPointList().add(new PointF(1.0f, 1.0f));
        pG_PhotoItem2.getPointList().add(new PointF(0.0f, 1.0f));
        collage$app_release.getPGPhotoItemList().add(pG_PhotoItem2);
        return collage$app_release;
    }
}
