package chin.pswm.gps.photo.location.map.photoGrid.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import chin.pswm.gps.photo.location.map.activity.PreviewActivity;
import chin.pswm.gps.photo.location.map.photoGrid.adapter.PG_BackgroundAdapter;
import chin.pswm.gps.photo.location.map.photoGrid.adapter.PG_FrameAdapter;
import chin.pswm.gps.photo.location.map.photoGrid.frame.PG_FramePhotoLayout;
import chin.pswm.gps.photo.location.map.photoGrid.model.PG_TemplatePGItem;
import chin.pswm.gps.photo.location.map.photoGrid.multitouch.PG_PhotoView;
import chin.pswm.gps.photo.location.map.photoGrid.template.PG_PhotoItem;
import chin.pswm.gps.photo.location.map.photoGrid.util.PG_FrameImageUtils;
import chin.pswm.gps.photo.location.map.photoGrid.util.PG_ImageUtils;
import chin.pswm.gps.photo.location.map.utils.BaseActivity;
import chin.pswm.gps.photo.location.map.utils.Resizer;
import chin.pswm.gps.photo.location.map.utils.StorageUtils;
import chin.pswm.gps.photo.location.map_debug.R;
import chin.pswm.gps.photo.location.map_debug.databinding.ActivityCollageBinding;
import chin.pswm.gps.photo.location.map_debug.databinding.ProcessDialogLayoutBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@SuppressWarnings("all")
@Metadata(d1 = {"\u0000´\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001B\u0005¢\u0006\u0002\u0010\u0005J\u000e\u0010m\u001a\u00020n2\u0006\u0010o\u001a\u00020]J\u0006\u0010p\u001a\u00020nJ\u0006\u0010q\u001a\u000209J\b\u0010r\u001a\u00020nH\u0002J\u0010\u0010s\u001a\u00020n2\u0006\u0010t\u001a\u00020uH\u0016J\u0012\u0010v\u001a\u00020n2\b\u0010w\u001a\u0004\u0018\u00010xH\u0016J\u0012\u0010y\u001a\u00020n2\b\u0010z\u001a\u0004\u0018\u00010TH\u0014J\u0010\u0010{\u001a\u00020n2\u0006\u0010|\u001a\u00020]H\u0016J\u0010\u0010}\u001a\u00020n2\u0006\u0010~\u001a\u00020TH\u0014R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\u0014\u0010\u000f\u001a\u00020\u0007XD¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\tR\u001a\u0010\u0011\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\t\"\u0004\b\u0013\u0010\u000bR\u0014\u0010\u0014\u001a\u00020\u0007XD¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\tR\u001a\u0010\u0016\u001a\u00020\u0017X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u001dXD¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020\u001dXD¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u001c\u0010\"\u001a\u0004\u0018\u00010#X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010(\u001a\u00020)X.¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020/X.¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u000e\u00104\u001a\u000205X\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000205X\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u001dX\u000e¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u0004\u0018\u000109X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010:\u001a\u0004\u0018\u00010;X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u001dX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020?X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010@\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u001f\"\u0004\bB\u0010CR\u001a\u0010D\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010\t\"\u0004\bF\u0010\u000bR\u001c\u0010G\u001a\u0004\u0018\u00010HX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\u001a\u0010M\u001a\u00020NX.¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\u0010\u0010S\u001a\u0004\u0018\u00010TX\u000e¢\u0006\u0002\n\u0000R \u0010U\u001a\b\u0012\u0004\u0012\u00020W0VX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010Y\"\u0004\bZ\u0010[R\u001c\u0010\\\u001a\u0004\u0018\u00010]X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010_\"\u0004\b`\u0010aR\u000e\u0010b\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R.\u0010c\u001a\u0016\u0012\u0004\u0012\u00020]\u0018\u00010dj\n\u0012\u0004\u0012\u00020]\u0018\u0001`eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010g\"\u0004\bh\u0010iR.\u0010j\u001a\u0016\u0012\u0004\u0012\u00020]\u0018\u00010dj\n\u0012\u0004\u0012\u00020]\u0018\u0001`eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bk\u0010g\"\u0004\bl\u0010i¨\u0006\u0001"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/activity/PG_CollageActivity;", "Lchin/pswm/gps/photo/location/map/utils/BaseActivity;", "Landroid/view/View$OnClickListener;", "Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_FrameAdapter$OnFrameClickListener;", "Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_BackgroundAdapter$OnBGClickListener;", "()V", "DEFAULT_SPACE", "", "getDEFAULT_SPACE", "()F", "setDEFAULT_SPACE", "(F)V", "MAX_CORNER", "getMAX_CORNER", "setMAX_CORNER", "MAX_CORNER_PROGRESS", "getMAX_CORNER_PROGRESS", "MAX_SPACE", "getMAX_SPACE", "setMAX_SPACE", "MAX_SPACE_PROGRESS", "getMAX_SPACE_PROGRESS", "PGFrameAdapter", "Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_FrameAdapter;", "getPGFrameAdapter", "()Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_FrameAdapter;", "setPGFrameAdapter", "(Lchin/pswm/gps/photo/location/map/photoGrid/adapter/PG_FrameAdapter;)V", "RATIO_GOLDEN", "", "getRATIO_GOLDEN", "()I", "RATIO_SQUARE", "getRATIO_SQUARE", "adsLoadUtil", "Lchin/pswm/gps/photo/location/map/ads/AdsLoadUtil;", "getAdsLoadUtil", "()Lchin/pswm/gps/photo/location/map/ads/AdsLoadUtil;", "setAdsLoadUtil", "(Lchin/pswm/gps/photo/location/map/ads/AdsLoadUtil;)V", "binding", "Lchin/pswm/gps/photo/location/map/databinding/ActivityCollageBinding;", "getBinding", "()Lchin/pswm/gps/photo/location/map/databinding/ActivityCollageBinding;", "setBinding", "(Lchin/pswm/gps/photo/location/map/databinding/ActivityCollageBinding;)V", "img_background", "Landroid/widget/ImageView;", "getImg_background", "()Landroid/widget/ImageView;", "setImg_background", "(Landroid/widget/ImageView;)V", "latitude", "", "longitude", "mBackgroundColor", "mBackgroundImage", "Landroid/graphics/Bitmap;", "mBackgroundUri", "Landroid/net/Uri;", "mCorner", "mImageInTemplateCount", "mLastClickTime", "", "mLayoutRatio", "getMLayoutRatio", "setMLayoutRatio", "(I)V", "mOutputScale", "getMOutputScale", "setMOutputScale", "mPGFramePhotoLayout", "Lchin/pswm/gps/photo/location/map/photoGrid/frame/PG_FramePhotoLayout;", "getMPGFramePhotoLayout", "()Lchin/pswm/gps/photo/location/map/photoGrid/frame/PG_FramePhotoLayout;", "setMPGFramePhotoLayout", "(Lchin/pswm/gps/photo/location/map/photoGrid/frame/PG_FramePhotoLayout;)V", "mPGPhotoView", "Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_PhotoView;", "getMPGPhotoView", "()Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_PhotoView;", "setMPGPhotoView", "(Lchin/pswm/gps/photo/location/map/photoGrid/multitouch/PG_PhotoView;)V", "mSavedInstanceState", "Landroid/os/Bundle;", "mSelectedPhotoPaths", "", "", "getMSelectedPhotoPaths", "()Ljava/util/List;", "setMSelectedPhotoPaths", "(Ljava/util/List;)V", "mSelectedTemplateItem", "Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_TemplatePGItem;", "getMSelectedTemplateItem", "()Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_TemplatePGItem;", "setMSelectedTemplateItem", "(Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_TemplatePGItem;)V", "mSpace", "mTemplateItemList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getMTemplateItemList", "()Ljava/util/ArrayList;", "setMTemplateItemList", "(Ljava/util/ArrayList;)V", "mTemplateItemListSelect", "getMTemplateItemListSelect", "setMTemplateItemListSelect", "buildLayout", "", "item", "checkClick", "createOutputImage", "loadFrameImages", "onBGClick", "drawable", "Landroid/graphics/drawable/Drawable;", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "onFrameClick", "templateItem", "onSaveInstanceState", "outState", "ProcessAsyncTask", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PG_CollageActivity extends BaseActivity implements View.OnClickListener, PG_FrameAdapter.OnFrameClickListener, PG_BackgroundAdapter.OnBGClickListener {
    private float DEFAULT_SPACE = 10.0f;
    private float MAX_CORNER;
    private final float MAX_CORNER_PROGRESS = 200.0f;
    private float MAX_SPACE;
    private final float MAX_SPACE_PROGRESS = 300.0f;
    public PG_FrameAdapter PGFrameAdapter;
    private final int RATIO_GOLDEN = 2;
    private final int RATIO_SQUARE = 0;
    public ActivityCollageBinding binding;
    public ImageView img_background;
    private double latitude;
    private double longitude;
    private int mBackgroundColor = -1;
    private Bitmap mBackgroundImage;
    private Uri mBackgroundUri;
    private float mCorner = 20.0f;
    private int mImageInTemplateCount;
    private long mLastClickTime;
    private int mLayoutRatio = this.RATIO_SQUARE;
    private float mOutputScale = 1.0f;
    private PG_FramePhotoLayout mPGFramePhotoLayout;
    protected PG_PhotoView mPGPhotoView;
    private Bundle mSavedInstanceState;
    private List<String> mSelectedPhotoPaths = new ArrayList();
    private PG_TemplatePGItem mSelectedTemplateItem;
    private float mSpace = 10.0f;
    private ArrayList<PG_TemplatePGItem> mTemplateItemList = new ArrayList<>();
    private ArrayList<PG_TemplatePGItem> mTemplateItemListSelect = new ArrayList<>();

    public final PG_FramePhotoLayout getMPGFramePhotoLayout() {
        return this.mPGFramePhotoLayout;
    }

    public final void setMPGFramePhotoLayout(PG_FramePhotoLayout pG_FramePhotoLayout) {
        this.mPGFramePhotoLayout = pG_FramePhotoLayout;
    }

    public final float getDEFAULT_SPACE() {
        return this.DEFAULT_SPACE;
    }

    public final void setDEFAULT_SPACE(float f) {
        this.DEFAULT_SPACE = f;
    }

    public final float getMAX_SPACE() {
        return this.MAX_SPACE;
    }

    public final void setMAX_SPACE(float f) {
        this.MAX_SPACE = f;
    }

    public final float getMAX_CORNER() {
        return this.MAX_CORNER;
    }

    public final void setMAX_CORNER(float f) {
        this.MAX_CORNER = f;
    }


    public final int getRATIO_SQUARE() {
        return this.RATIO_SQUARE;
    }


    public final int getRATIO_GOLDEN() {
        return this.RATIO_GOLDEN;
    }

    public final float getMAX_SPACE_PROGRESS() {
        return this.MAX_SPACE_PROGRESS;
    }

    public final float getMAX_CORNER_PROGRESS() {
        return this.MAX_CORNER_PROGRESS;
    }


    public final int getMLayoutRatio() {
        return this.mLayoutRatio;
    }


    public final void setMLayoutRatio(int i) {
        this.mLayoutRatio = i;
    }


    public final PG_PhotoView getMPGPhotoView() {
        PG_PhotoView pG_PhotoView = this.mPGPhotoView;
        if (pG_PhotoView != null) {
            return pG_PhotoView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mPGPhotoView");
        return null;
    }


    public final void setMPGPhotoView(PG_PhotoView pG_PhotoView) {
        Intrinsics.checkNotNullParameter(pG_PhotoView, "<set-?>");
        this.mPGPhotoView = pG_PhotoView;
    }


    public final float getMOutputScale() {
        return this.mOutputScale;
    }


    public final void setMOutputScale(float f) {
        this.mOutputScale = f;
    }


    public final PG_TemplatePGItem getMSelectedTemplateItem() {
        return this.mSelectedTemplateItem;
    }


    public final void setMSelectedTemplateItem(PG_TemplatePGItem pG_TemplatePGItem) {
        this.mSelectedTemplateItem = pG_TemplatePGItem;
    }


    public final ArrayList<PG_TemplatePGItem> getMTemplateItemList() {
        return this.mTemplateItemList;
    }


    public final void setMTemplateItemList(ArrayList<PG_TemplatePGItem> arrayList) {
        this.mTemplateItemList = arrayList;
    }


    public final ArrayList<PG_TemplatePGItem> getMTemplateItemListSelect() {
        return this.mTemplateItemListSelect;
    }


    public final void setMTemplateItemListSelect(ArrayList<PG_TemplatePGItem> arrayList) {
        this.mTemplateItemListSelect = arrayList;
    }


    public final List<String> getMSelectedPhotoPaths() {
        return this.mSelectedPhotoPaths;
    }


    public final void setMSelectedPhotoPaths(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mSelectedPhotoPaths = list;
    }

    public final PG_FrameAdapter getPGFrameAdapter() {
        PG_FrameAdapter pG_FrameAdapter = this.PGFrameAdapter;
        if (pG_FrameAdapter != null) {
            return pG_FrameAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("PGFrameAdapter");
        return null;
    }

    public final void setPGFrameAdapter(PG_FrameAdapter pG_FrameAdapter) {
        Intrinsics.checkNotNullParameter(pG_FrameAdapter, "<set-?>");
        this.PGFrameAdapter = pG_FrameAdapter;
    }

    public final ImageView getImg_background() {
        ImageView imageView = this.img_background;
        if (imageView != null) {
            return imageView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("img_background");
        return null;
    }

    public final void setImg_background(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.img_background = imageView;
    }


    public final void checkClick() {
        if (SystemClock.elapsedRealtime() - this.mLastClickTime >= 1000) {
            this.mLastClickTime = SystemClock.elapsedRealtime();
        }
    }

    public void onBGClick(Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        PG_FramePhotoLayout pG_FramePhotoLayout = this.mPGFramePhotoLayout;
        Intrinsics.checkNotNull(pG_FramePhotoLayout);
        Bitmap createImage = pG_FramePhotoLayout.createImage();
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        PG_AndroidUtils pG_AndroidUtils = PG_AndroidUtils.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
        this.mBackgroundImage = pG_AndroidUtils.resizeImageToNewSize(bitmap, createImage.getWidth(), createImage.getHeight());
        getImg_background().setBackground(new BitmapDrawable(getResources(), this.mBackgroundImage));
        getImg_background().setImageBitmap(this.mBackgroundImage);
    }

    public void onFrameClick(PG_TemplatePGItem pG_TemplatePGItem) {
        Intrinsics.checkNotNullParameter(pG_TemplatePGItem, "templateItem");
        PG_TemplatePGItem pG_TemplatePGItem2 = this.mSelectedTemplateItem;
        Intrinsics.checkNotNull(pG_TemplatePGItem2);
        pG_TemplatePGItem2.setSelected(false);
        PG_TemplatePGItem pG_TemplatePGItem3 = this.mSelectedTemplateItem;
        Intrinsics.checkNotNull(pG_TemplatePGItem3);
        int size = pG_TemplatePGItem3.getPGPhotoItemList().size();
        for (int i = 0; i < size; i++) {
            PG_TemplatePGItem pG_TemplatePGItem4 = this.mSelectedTemplateItem;
            Intrinsics.checkNotNull(pG_TemplatePGItem4);
            PG_PhotoItem pG_PhotoItem = pG_TemplatePGItem4.getPGPhotoItemList().get(i);
            Intrinsics.checkNotNullExpressionValue(pG_PhotoItem, "mSelectedTemplateItem!!.PGPhotoItemList[idx]");
            PG_PhotoItem pG_PhotoItem2 = pG_PhotoItem;
            if (pG_PhotoItem2.getImagePath() != null) {
                String imagePath = pG_PhotoItem2.getImagePath();
                Intrinsics.checkNotNull(imagePath);
                if (imagePath.length() > 0) {
                    if (i < this.mSelectedPhotoPaths.size()) {
                        List<String> list = this.mSelectedPhotoPaths;
                        String imagePath2 = pG_PhotoItem2.getImagePath();
                        Intrinsics.checkNotNull(imagePath2);
                        list.add(i, imagePath2);
                    } else {
                        List<String> list2 = this.mSelectedPhotoPaths;
                        String imagePath3 = pG_PhotoItem2.getImagePath();
                        Intrinsics.checkNotNull(imagePath3);
                        list2.add(imagePath3);
                    }
                }
            }
        }
        int min = Math.min(this.mSelectedPhotoPaths.size(), pG_TemplatePGItem.getPGPhotoItemList().size());
        for (int i2 = 0; i2 < min; i2++) {
            PG_PhotoItem pG_PhotoItem3 = pG_TemplatePGItem.getPGPhotoItemList().get(i2);
            Intrinsics.checkNotNullExpressionValue(pG_PhotoItem3, "templateItem.PGPhotoItemList.get(idx)");
            PG_PhotoItem pG_PhotoItem4 = pG_PhotoItem3;
            if (pG_PhotoItem4.getImagePath() != null) {
                String imagePath4 = pG_PhotoItem4.getImagePath();
                Intrinsics.checkNotNull(imagePath4);
                if (imagePath4.length() >= 1) {
                }
            }
            pG_PhotoItem4.setImagePath(this.mSelectedPhotoPaths.get(i2));
        }
        this.mSelectedTemplateItem = pG_TemplatePGItem;
        Intrinsics.checkNotNull(pG_TemplatePGItem);
        pG_TemplatePGItem.setSelected(true);
        getPGFrameAdapter().notifyDataSetChanged();
        buildLayout(pG_TemplatePGItem);
    }

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ'\u0010\u0018\u001a\u0004\u0018\u00010\u00022\u0016\u0010\u0019\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u001a\"\u0004\u0018\u00010\u0002H\u0014¢\u0006\u0002\u0010\u001bJ\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0002H\u0014J\b\u0010\u001f\u001a\u00020\u001dH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0002X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0002X\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/activity/PG_CollageActivity$ProcessAsyncTask;", "Landroid/os/AsyncTask;", "", "Ljava/lang/Void;", "activity", "Landroidx/appcompat/app/AppCompatActivity;", "latitude", "", "longitude", "adsLoadUtil", "Lchin/pswm/gps/photo/location/map/ads/AdsLoadUtil;", "(Landroidx/appcompat/app/AppCompatActivity;DDLchin/pswm/gps/photo/location/map/ads/AdsLoadUtil;)V", "binding1", "Lchin/pswm/gps/photo/location/map/databinding/ProcessDialogLayoutBinding;", "bitmap", "Landroid/graphics/Bitmap;", "getBitmap", "()Landroid/graphics/Bitmap;", "setBitmap", "(Landroid/graphics/Bitmap;)V", "dialog", "Landroid/app/Dialog;", "name", "path", "doInBackground", "args", "", "([Ljava/lang/String;)Ljava/lang/String;", "onPostExecute", "", "result", "onPreExecute", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class ProcessAsyncTask extends AsyncTask<String, Void, String> {

        public final AppCompatActivity activity;
        private ProcessDialogLayoutBinding binding1;
        private Bitmap bitmap;
        private Dialog dialog;
        private final double latitude;
        private final double longitude;
        private String name;
        private String path;


        public static final boolean onPreExecute$lambda$0(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return true;
        }

        public ProcessAsyncTask(AppCompatActivity appCompatActivity, double d, double d2) {
            Intrinsics.checkNotNullParameter(appCompatActivity, "activity");
            this.activity = appCompatActivity;
            this.latitude = d;
            this.longitude = d2;
        }

        public final Bitmap getBitmap() {
            return this.bitmap;
        }

        public final void setBitmap(Bitmap bitmap2) {
            this.bitmap = bitmap2;
        }


        public void onPreExecute() {
            Window window;
            super.onPreExecute();
            this.binding1 = ProcessDialogLayoutBinding.inflate(this.activity.getLayoutInflater());
            Dialog dialog2 = new Dialog(this.activity);
            this.dialog = dialog2;
            Window window2 = dialog2.getWindow();
            if (window2 != null) {
                window2.requestFeature(1);
            }
            Dialog dialog3 = this.dialog;
            if (dialog3 != null) {
                ProcessDialogLayoutBinding processDialogLayoutBinding = this.binding1;
                Intrinsics.checkNotNull(processDialogLayoutBinding);
                dialog3.setContentView(processDialogLayoutBinding.getRoot());
            }
            Dialog dialog4 = this.dialog;
            if (!(dialog4 == null || (window = dialog4.getWindow()) == null)) {
                window.setBackgroundDrawable(new ColorDrawable(0));
            }
            Dialog dialog5 = this.dialog;
            if (dialog5 != null) {
                dialog5.setCanceledOnTouchOutside(false);
            }
            Dialog dialog6 = this.dialog;
            if (dialog6 != null) {
                dialog6.setOnKeyListener(new PG_CollageActivity$ProcessAsyncTask$$ExternalSyntheticLambda0());
            }
            RequestBuilder<Drawable> load = Glide.with((FragmentActivity) this.activity).load(Integer.valueOf(R.drawable.loading));
            ProcessDialogLayoutBinding processDialogLayoutBinding2 = this.binding1;
            ImageView imageView = processDialogLayoutBinding2 != null ? processDialogLayoutBinding2.gif : null;
            Intrinsics.checkNotNull(imageView);
            load.into(imageView);
            Dialog dialog7 = this.dialog;
            if (dialog7 != null) {
                dialog7.show();
            }
        }


        public String doInBackground(String... strArr) {
            Intrinsics.checkNotNullParameter(strArr, "args");
            this.path = StorageUtils.create_folder(this.activity.getString(R.string.app_name));
            this.name = "Image_" + System.currentTimeMillis() + ".jpg";
            this.bitmap = BitmapFactory.decodeFile(this.activity.getCacheDir().getAbsolutePath() + "/tempBMP");
            return null;
        }


        public void onPostExecute(String str) {
            String saveImageWithLocation = StorageUtils.saveImageWithLocation(this.activity, this.bitmap, this.path, this.name, this.latitude, this.longitude);
            StorageUtils.scanFile(this.activity, saveImageWithLocation);
            new Handler().postDelayed(new PG_CollageActivity$ProcessAsyncTask$$ExternalSyntheticLambda1(this, saveImageWithLocation), 2000);
        }


        public final void onPostExecute$lambda$1(ProcessAsyncTask processAsyncTask, String str) {
            Intrinsics.checkNotNullParameter(processAsyncTask, "this$0");
            Dialog dialog2 = processAsyncTask.dialog;
            if (dialog2 != null) {
                dialog2.dismiss();
            }
            if (str != null) {
                startActivity(new Intent(PG_CollageActivity.this, PreviewActivity.class).setFlags(536870912).putExtra("Path", str));
                finish();
            }
        }
    }

    public void onClick(View view) {
        Intrinsics.checkNotNull(view);
        int id = view.getId();
        if (id == R.id.back) {
            onBackPressed();
            return;
        } else if (id == R.id.black) {
            Drawable drawable = getResources().getDrawable(R.drawable.b_bg);
            Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.b_bg)");
            onBGClick(drawable);
            return;
        } else if (id == R.id.btn_next) {
            checkClick();
            try {
                Bitmap createOutputImage = createOutputImage();
                FileOutputStream fileOutputStream = new FileOutputStream(new File(getCacheDir(), "tempBMP"));
                createOutputImage.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.close();
                double d = this.latitude;
                double d2 = this.longitude;
                new ProcessAsyncTask(this, d, d2).execute(new String[0]);
                return;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            } catch (IOException e2) {
                e2.printStackTrace();
                return;
            }
        } else if (id == R.id.white) {
            Drawable drawable2 = getResources().getDrawable(R.drawable.bg);
            Intrinsics.checkNotNullExpressionValue(drawable2, "resources.getDrawable(R.drawable.bg)");
            onBGClick(drawable2);
            return;
        }
        return;
    }

    public final ActivityCollageBinding getBinding() {
        ActivityCollageBinding activityCollageBinding = this.binding;
        if (activityCollageBinding != null) {
            return activityCollageBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(ActivityCollageBinding activityCollageBinding) {
        Intrinsics.checkNotNullParameter(activityCollageBinding, "<set-?>");
        this.binding = activityCollageBinding;
    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityCollageBinding inflate = ActivityCollageBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        setBinding(inflate);
        setContentView((View) getBinding().getRoot());
        Context context = this;
        Resizer.getheightandwidth(context);
        this.DEFAULT_SPACE = PG_ImageUtils.INSTANCE.pxFromDp(context, 2.0f);
        this.MAX_SPACE = PG_ImageUtils.INSTANCE.pxFromDp(context, 30.0f);
        this.MAX_CORNER = PG_ImageUtils.INSTANCE.pxFromDp(context, 60.0f);
        this.mSpace = this.DEFAULT_SPACE;
        if (bundle != null) {
            this.mSpace = bundle.getFloat("mSpace");
            this.mCorner = bundle.getFloat("mCorner");
            this.mSavedInstanceState = bundle;
        }
        this.latitude = getIntent().getDoubleExtra("latitude", 0.0d);
        this.longitude = getIntent().getDoubleExtra("longitude", 0.0d);
        this.mImageInTemplateCount = getIntent().getIntExtra("imagesinTemplate", 0);
        ArrayList<String> stringArrayListExtra = getIntent().getStringArrayListExtra("selectedImages");
        setMPGPhotoView(new PG_PhotoView(context));
        getBinding().rlContainer.getViewTreeObserver().addOnGlobalLayoutListener(new PG_CollageActivity$onCreate$2(this));
        View findViewById = findViewById(R.id.img_background);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<ImageView>(R.id.img_background)");
        setImg_background((ImageView) findViewById);
        loadFrameImages();
        ArrayList<PG_TemplatePGItem> arrayList = this.mTemplateItemList;
        Intrinsics.checkNotNull(arrayList);
        PG_TemplatePGItem pG_TemplatePGItem = arrayList.get(0);
        this.mSelectedTemplateItem = pG_TemplatePGItem;
        Intrinsics.checkNotNull(pG_TemplatePGItem);
        pG_TemplatePGItem.setSelected(true);
        if (stringArrayListExtra != null) {
            int size = stringArrayListExtra.size();
            PG_TemplatePGItem pG_TemplatePGItem2 = this.mSelectedTemplateItem;
            Intrinsics.checkNotNull(pG_TemplatePGItem2);
            int min = Math.min(size, pG_TemplatePGItem2.getPGPhotoItemList().size());
            for (int i = 0; i < min; i++) {
                PG_TemplatePGItem pG_TemplatePGItem3 = this.mSelectedTemplateItem;
                Intrinsics.checkNotNull(pG_TemplatePGItem3);
                pG_TemplatePGItem3.getPGPhotoItemList().get(i).setImagePath(stringArrayListExtra.get(i));
            }
        }
        ArrayList<PG_TemplatePGItem> arrayList2 = this.mTemplateItemList;
        Intrinsics.checkNotNull(arrayList2);
        ArrayList<PG_TemplatePGItem> arrayList3 = this.mTemplateItemListSelect;
        Intrinsics.checkNotNull(arrayList3);
        setPGFrameAdapter(new PG_FrameAdapter(context, arrayList2, arrayList3, this));
        getBinding().listFrames.setAdapter(getPGFrameAdapter());
        View.OnClickListener onClickListener = this;
        getBinding().btnNext.setOnClickListener(onClickListener);
        getBinding().black.setOnClickListener(onClickListener);
        getBinding().white.setOnClickListener(onClickListener);
        getBinding().back.setOnClickListener(onClickListener);
    }

    private final void loadFrameImages() {
        ArrayList arrayList = new ArrayList();
        Context context = this;
        arrayList.addAll(PG_FrameImageUtils.INSTANCE.loadFrameImages(context));
        ArrayList<PG_TemplatePGItem> arrayList2 = new ArrayList<>();
        this.mTemplateItemList = arrayList2;
        if (this.mImageInTemplateCount > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                PG_TemplatePGItem pG_TemplatePGItem = (PG_TemplatePGItem) it.next();
                if (pG_TemplatePGItem.getPGPhotoItemList().size() == this.mImageInTemplateCount) {
                    ArrayList<PG_TemplatePGItem> arrayList3 = this.mTemplateItemList;
                    Intrinsics.checkNotNull(arrayList3);
                    arrayList3.add(pG_TemplatePGItem);
                }
            }
        } else {
            Intrinsics.checkNotNull(arrayList2);
            arrayList2.addAll(arrayList);
        }
        ArrayList arrayList4 = new ArrayList();
        arrayList4.addAll(PG_FrameImageUtils.INSTANCE.loadFrameImagesSelect(context));
        ArrayList<PG_TemplatePGItem> arrayList5 = new ArrayList<>();
        this.mTemplateItemListSelect = arrayList5;
        if (this.mImageInTemplateCount > 0) {
            Iterator it2 = arrayList4.iterator();
            while (it2.hasNext()) {
                PG_TemplatePGItem pG_TemplatePGItem2 = (PG_TemplatePGItem) it2.next();
                if (pG_TemplatePGItem2.getPGPhotoItemList().size() == this.mImageInTemplateCount) {
                    ArrayList<PG_TemplatePGItem> arrayList6 = this.mTemplateItemListSelect;
                    Intrinsics.checkNotNull(arrayList6);
                    arrayList6.add(pG_TemplatePGItem2);
                }
            }
            return;
        }
        Intrinsics.checkNotNull(arrayList5);
        arrayList5.addAll(arrayList4);
    }


    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        super.onSaveInstanceState(bundle);
        bundle.putFloat("mSpace", this.mSpace);
        bundle.putFloat("mCornerBar", this.mCorner);
        PG_FramePhotoLayout pG_FramePhotoLayout = this.mPGFramePhotoLayout;
        if (pG_FramePhotoLayout != null) {
            Intrinsics.checkNotNull(pG_FramePhotoLayout);
            pG_FramePhotoLayout.saveInstanceState(bundle);
        }
    }

    public final void buildLayout(PG_TemplatePGItem pG_TemplatePGItem) {
        Intrinsics.checkNotNullParameter(pG_TemplatePGItem, "item");
        this.mPGFramePhotoLayout = new PG_FramePhotoLayout(this, pG_TemplatePGItem.getPGPhotoItemList());
        int width = getBinding().rlContainer.getWidth();
        int height = getBinding().rlContainer.getHeight();
        int i = this.mLayoutRatio;
        if (i == this.RATIO_SQUARE) {
            if (width > height) {
                width = height;
            } else {
                height = width;
            }
        } else if (i == this.RATIO_GOLDEN) {
            if (width <= height) {
                double d = ((double) width) * 1.61803398875d;
                double d2 = (double) height;
                if (d >= d2) {
                    width = (int) (d2 / 1.61803398875d);
                } else {
                    height = (int) d;
                }
            } else if (height <= width) {
                double d3 = ((double) height) * 1.61803398875d;
                double d4 = (double) width;
                if (d3 >= d4) {
                    height = (int) (d4 / 1.61803398875d);
                } else {
                    width = (int) d3;
                }
            }
        }
        this.mOutputScale = PG_ImageUtils.INSTANCE.calculateOutputScaleFactor(width, height);
        PG_FramePhotoLayout pG_FramePhotoLayout = this.mPGFramePhotoLayout;
        Intrinsics.checkNotNull(pG_FramePhotoLayout);
        pG_FramePhotoLayout.build(width, height, this.mOutputScale, this.mSpace, this.mCorner);
        if (this.mSavedInstanceState != null) {
            PG_FramePhotoLayout pG_FramePhotoLayout2 = this.mPGFramePhotoLayout;
            Intrinsics.checkNotNull(pG_FramePhotoLayout2);
            Bundle bundle = this.mSavedInstanceState;
            Intrinsics.checkNotNull(bundle);
            pG_FramePhotoLayout2.restoreInstanceState(bundle);
            this.mSavedInstanceState = null;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
        layoutParams.addRule(13);
        getBinding().rlContainer.removeAllViews();
        getBinding().rlContainer.removeView(getImg_background());
        ViewGroup.LayoutParams layoutParams2 = layoutParams;
        getBinding().rlContainer.addView(getImg_background(), layoutParams2);
        getBinding().rlContainer.addView(this.mPGFramePhotoLayout, layoutParams2);
        getBinding().rlContainer.removeView(getMPGPhotoView());
        getBinding().rlContainer.addView(getMPGPhotoView(), layoutParams2);
    }

    public final Bitmap createOutputImage() throws OutOfMemoryError {
        try {
            PG_FramePhotoLayout pG_FramePhotoLayout = this.mPGFramePhotoLayout;
            Intrinsics.checkNotNull(pG_FramePhotoLayout);
            Bitmap createImage = pG_FramePhotoLayout.createImage();
            Intrinsics.checkNotNull(createImage);
            Bitmap createBitmap = Bitmap.createBitmap(createImage.getWidth(), createImage.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint(1);
            Bitmap bitmap = this.mBackgroundImage;
            if (bitmap != null) {
                Intrinsics.checkNotNull(bitmap);
                if (!bitmap.isRecycled()) {
                    Bitmap bitmap2 = this.mBackgroundImage;
                    Intrinsics.checkNotNull(bitmap2);
                    Bitmap bitmap3 = this.mBackgroundImage;
                    Intrinsics.checkNotNull(bitmap3);
                    int width = bitmap3.getWidth();
                    Bitmap bitmap4 = this.mBackgroundImage;
                    Intrinsics.checkNotNull(bitmap4);
                    canvas.drawBitmap(bitmap2, new Rect(0, 0, width, bitmap4.getHeight()), new Rect(0, 0, createBitmap.getWidth(), createBitmap.getHeight()), paint);
                    canvas.drawBitmap(createImage, 0.0f, 0.0f, paint);
                    createImage.recycle();
                    Bitmap image = getMPGPhotoView().getImage(this.mOutputScale);
                    Intrinsics.checkNotNull(image);
                    canvas.drawBitmap(image, 0.0f, 0.0f, paint);
                    image.recycle();
                    System.gc();
                    Intrinsics.checkNotNullExpressionValue(createBitmap, "result");
                    return createBitmap;
                }
            }
            canvas.drawColor(this.mBackgroundColor);
            canvas.drawBitmap(createImage, 0.0f, 0.0f, paint);
            createImage.recycle();
            Bitmap image2 = getMPGPhotoView().getImage(this.mOutputScale);
            Intrinsics.checkNotNull(image2);
            canvas.drawBitmap(image2, 0.0f, 0.0f, paint);
            image2.recycle();
            System.gc();
            Intrinsics.checkNotNullExpressionValue(createBitmap, "result");
            return createBitmap;
        } catch (OutOfMemoryError e) {
            throw e;
        }
    }
}
    