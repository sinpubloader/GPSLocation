package chin.pswm.gps.photo.location.map.photoGrid.util;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import chin.pswm.gps.photo.location.map.photoGrid.frame.PG_FrameEntity;
import chin.pswm.gps.photo.location.map.photoGrid.multitouch.PG_MultiTouchEntity;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 02\u00020\u0001:\u00010B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u001aJ\u0006\u0010\u001c\u001a\u00020\u001aJ\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00140\nJ\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u0010\u0010 \u001a\u0004\u0018\u00010\u00122\u0006\u0010!\u001a\u00020\u0011J\u000e\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\u0014J\u000e\u0010$\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\u000bJ\u0014\u0010%\u001a\u00020\u001a2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u0016\u0010'\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\u0012J\u0014\u0010)\u001a\u00020\u001a2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u000e\u0010*\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\u000bJ\u000e\u0010+\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\u000bJ\u000e\u0010,\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020.J\u000e\u0010/\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020.R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR.\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\b¨\u00061"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/util/PG_ResultContainer;", "", "()V", "frameBackgroundImage", "Landroid/net/Uri;", "getFrameBackgroundImage", "()Landroid/net/Uri;", "setFrameBackgroundImage", "(Landroid/net/Uri;)V", "<set-?>", "Ljava/util/ArrayList;", "Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_MultiTouchEntity;", "imageEntities", "getImageEntities", "()Ljava/util/ArrayList;", "mDecodedImageMap", "Ljava/util/HashMap;", "", "Landroid/graphics/Bitmap;", "mFrameImages", "Lchin/pswm/gps/photo/location/map/photoGrid/frame/PG_FrameEntity;", "mFrameStickerImages", "photoBackgroundImage", "getPhotoBackgroundImage", "setPhotoBackgroundImage", "clearAll", "", "clearAllImageInFrameCreator", "clearFrameImages", "copyFrameImages", "copyFrameStickerImages", "copyImageEntities", "getImage", "key", "putFrameImage", "entity", "putFrameSticker", "putFrameStickerImages", "images", "putImage", "bitmap", "putImageEntities", "removeFrameSticker", "removeImageEntity", "restoreFromBundle", "bundle", "Landroid/os/Bundle;", "saveToBundle", "Companion", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_ResultContainer {
    private static PG_ResultContainer instance;
    private Uri frameBackgroundImage;
    private ArrayList<PG_MultiTouchEntity> imageEntities;
    private final HashMap<String, Bitmap> mDecodedImageMap;
    private ArrayList<PG_FrameEntity> mFrameImages;
    private ArrayList<PG_MultiTouchEntity> mFrameStickerImages;
    private Uri photoBackgroundImage;
    public static final Companion Companion = new Companion(null);
    private static final String IMAGES_KEY = "imagesKey";
    private static final String PHOTO_BACKGROUND_IMAGE_KEY = "photoBgKey";
    private static final String FRAME_STICKER_IMAGES_KEY = "frameStickerKey";
    private static final String FRAME_BACKGROUND_IMAGE_KEY = "frameBackgroundKey";
    private static final String FRAME_IMAGES_KEY = "frameImageKey";

    public  PG_ResultContainer(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private PG_ResultContainer() {
        this.imageEntities = new ArrayList<>();
        this.mFrameStickerImages = new ArrayList<>();
        this.mFrameImages = new ArrayList<>();
        this.mDecodedImageMap = new HashMap<>();
    }

    public final ArrayList<PG_MultiTouchEntity> getImageEntities() {
        return this.imageEntities;
    }

    public final Uri getPhotoBackgroundImage() {
        return this.photoBackgroundImage;
    }

    public final void setPhotoBackgroundImage(Uri uri) {
        this.photoBackgroundImage = uri;
    }

    public final Uri getFrameBackgroundImage() {
        return this.frameBackgroundImage;
    }

    public final void setFrameBackgroundImage(Uri uri) {
        this.frameBackgroundImage = uri;
    }

    public final void removeImageEntity(PG_MultiTouchEntity entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        ArrayList<PG_MultiTouchEntity> arrayList = this.imageEntities;
        Intrinsics.checkNotNull(arrayList);
        arrayList.remove(entity);
    }

    public final void putImageEntities(ArrayList<PG_MultiTouchEntity> images) {
        Intrinsics.checkNotNullParameter(images, "images");
        ArrayList<PG_MultiTouchEntity> arrayList = this.imageEntities;
        Intrinsics.checkNotNull(arrayList);
        arrayList.clear();
        Iterator<PG_MultiTouchEntity> it = images.iterator();
        while (it.hasNext()) {
            ArrayList<PG_MultiTouchEntity> arrayList2 = this.imageEntities;
            Intrinsics.checkNotNull(arrayList2);
            arrayList2.add(it.next());
        }
    }

    public final void putImage(String key, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        this.mDecodedImageMap.put(key, bitmap);
    }

    public final Bitmap getImage(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.mDecodedImageMap.get(key);
    }

    public final ArrayList<PG_MultiTouchEntity> copyImageEntities() {
        ArrayList<PG_MultiTouchEntity> arrayList = new ArrayList<>();
        ArrayList<PG_MultiTouchEntity> arrayList2 = this.imageEntities;
        Intrinsics.checkNotNull(arrayList2);
        Iterator<PG_MultiTouchEntity> it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public final void putFrameImage(PG_FrameEntity entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        ArrayList<PG_FrameEntity> arrayList = this.mFrameImages;
        Intrinsics.checkNotNull(arrayList);
        arrayList.add(entity);
    }

    public final ArrayList<PG_FrameEntity> copyFrameImages() {
        ArrayList<PG_FrameEntity> arrayList = new ArrayList<>();
        ArrayList<PG_FrameEntity> arrayList2 = this.mFrameImages;
        Intrinsics.checkNotNull(arrayList2);
        Iterator<PG_FrameEntity> it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public final void putFrameSticker(PG_MultiTouchEntity entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        ArrayList<PG_MultiTouchEntity> arrayList = this.mFrameStickerImages;
        Intrinsics.checkNotNull(arrayList);
        arrayList.add(entity);
    }

    public final void putFrameStickerImages(ArrayList<PG_MultiTouchEntity> images) {
        Intrinsics.checkNotNullParameter(images, "images");
        ArrayList<PG_MultiTouchEntity> arrayList = this.mFrameStickerImages;
        Intrinsics.checkNotNull(arrayList);
        arrayList.clear();
        Iterator<PG_MultiTouchEntity> it = images.iterator();
        while (it.hasNext()) {
            ArrayList<PG_MultiTouchEntity> arrayList2 = this.mFrameStickerImages;
            Intrinsics.checkNotNull(arrayList2);
            arrayList2.add(it.next());
        }
    }

    public final ArrayList<PG_MultiTouchEntity> copyFrameStickerImages() {
        ArrayList<PG_MultiTouchEntity> arrayList = new ArrayList<>();
        ArrayList<PG_MultiTouchEntity> arrayList2 = this.mFrameStickerImages;
        Intrinsics.checkNotNull(arrayList2);
        Iterator<PG_MultiTouchEntity> it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public final void removeFrameSticker(PG_MultiTouchEntity entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        ArrayList<PG_MultiTouchEntity> arrayList = this.mFrameStickerImages;
        Intrinsics.checkNotNull(arrayList);
        arrayList.remove(entity);
    }

    public final void clearFrameImages() {
        ArrayList<PG_FrameEntity> arrayList = this.mFrameImages;
        Intrinsics.checkNotNull(arrayList);
        arrayList.clear();
    }

    public final void clearAll() {
        ArrayList<PG_MultiTouchEntity> arrayList = this.imageEntities;
        Intrinsics.checkNotNull(arrayList);
        arrayList.clear();
        this.photoBackgroundImage = null;
        ArrayList<PG_MultiTouchEntity> arrayList2 = this.mFrameStickerImages;
        Intrinsics.checkNotNull(arrayList2);
        arrayList2.clear();
        ArrayList<PG_FrameEntity> arrayList3 = this.mFrameImages;
        Intrinsics.checkNotNull(arrayList3);
        arrayList3.clear();
        this.frameBackgroundImage = null;
        this.mDecodedImageMap.clear();
    }

    public final void clearAllImageInFrameCreator() {
        ArrayList<PG_MultiTouchEntity> arrayList = this.mFrameStickerImages;
        Intrinsics.checkNotNull(arrayList);
        arrayList.clear();
        ArrayList<PG_FrameEntity> arrayList2 = this.mFrameImages;
        Intrinsics.checkNotNull(arrayList2);
        arrayList2.clear();
        this.frameBackgroundImage = null;
    }

    public final void saveToBundle(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        bundle.putParcelableArrayList(IMAGES_KEY, this.imageEntities);
        bundle.putParcelable(PHOTO_BACKGROUND_IMAGE_KEY, this.photoBackgroundImage);
        bundle.putParcelableArrayList(FRAME_STICKER_IMAGES_KEY, this.mFrameStickerImages);
        bundle.putParcelable(FRAME_BACKGROUND_IMAGE_KEY, this.frameBackgroundImage);
        bundle.putParcelableArrayList(FRAME_IMAGES_KEY, this.mFrameImages);
    }

    public final void restoreFromBundle(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        ArrayList<PG_MultiTouchEntity> parcelableArrayList = bundle.getParcelableArrayList(IMAGES_KEY);
        this.imageEntities = parcelableArrayList;
        if (parcelableArrayList == null) {
            this.imageEntities = new ArrayList<>();
        }
        this.photoBackgroundImage = (Uri) bundle.getParcelable(PHOTO_BACKGROUND_IMAGE_KEY);
        ArrayList<PG_MultiTouchEntity> parcelableArrayList2 = bundle.getParcelableArrayList(FRAME_STICKER_IMAGES_KEY);
        this.mFrameStickerImages = parcelableArrayList2;
        if (parcelableArrayList2 == null) {
            this.mFrameStickerImages = new ArrayList<>();
        }
        this.frameBackgroundImage = (Uri) bundle.getParcelable(FRAME_BACKGROUND_IMAGE_KEY);
        ArrayList<PG_FrameEntity> parcelableArrayList3 = bundle.getParcelableArrayList(FRAME_IMAGES_KEY);
        this.mFrameImages = parcelableArrayList3;
        if (parcelableArrayList3 == null) {
            this.mFrameImages = new ArrayList<>();
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0011\u001a\u00020\u0010R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/util/PG_ResultContainer$Companion;", "", "()V", "FRAME_BACKGROUND_IMAGE_KEY", "", "getFRAME_BACKGROUND_IMAGE_KEY", "()Ljava/lang/String;", "FRAME_IMAGES_KEY", "getFRAME_IMAGES_KEY", "FRAME_STICKER_IMAGES_KEY", "getFRAME_STICKER_IMAGES_KEY", "IMAGES_KEY", "getIMAGES_KEY", "PHOTO_BACKGROUND_IMAGE_KEY", "getPHOTO_BACKGROUND_IMAGE_KEY", "instance", "Lchin/pswm/gps/photo/location/map/photoGrid/util/PG_ResultContainer;", "getInstance", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getIMAGES_KEY() {
            return PG_ResultContainer.IMAGES_KEY;
        }

        public final String getPHOTO_BACKGROUND_IMAGE_KEY() {
            return PG_ResultContainer.PHOTO_BACKGROUND_IMAGE_KEY;
        }

        public final String getFRAME_STICKER_IMAGES_KEY() {
            return PG_ResultContainer.FRAME_STICKER_IMAGES_KEY;
        }

        public final String getFRAME_BACKGROUND_IMAGE_KEY() {
            return PG_ResultContainer.FRAME_BACKGROUND_IMAGE_KEY;
        }

        public final String getFRAME_IMAGES_KEY() {
            return PG_ResultContainer.FRAME_IMAGES_KEY;
        }

        public final PG_ResultContainer getInstance() {
            if (PG_ResultContainer.instance == null) {
                PG_ResultContainer.instance = new PG_ResultContainer(null);
            }
            PG_ResultContainer pG_ResultContainer = PG_ResultContainer.instance;
            Intrinsics.checkNotNull(pG_ResultContainer, "null cannot be cast to non-null type chin.pswm.gps.photo.location.map.photoGrid.util.PG_ResultContainer");
            return pG_ResultContainer;
        }
    }
}
