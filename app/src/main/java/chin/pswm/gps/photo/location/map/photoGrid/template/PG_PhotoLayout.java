package chin.pswm.gps.photo.location.map.photoGrid.template;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import chin.pswm.gps.photo.location.map.photoGrid.model.PG_ImageTemplate;
import chin.pswm.gps.photo.location.map.photoGrid.template.PG_ItemImageView;
import chin.pswm.gps.photo.location.map.photoGrid.template.PG_PhotoLayout;
import chin.pswm.gps.photo.location.map.photoGrid.template.PG_TransitionImageView;
import chin.pswm.gps.photo.location.map.photoGrid.util.PG_ImageDecoder;
import chin.pswm.gps.photo.location.map.photoGrid.util.PG_ImageUtils;
import chin.pswm.gps.photo.location.map.photoGrid.util.PG_PhotoUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
@SuppressWarnings("all")
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 B2\u00020\u00012\u00020\u0002:\u0001BB\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B%\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ$\u0010/\u001a\u0004\u0018\u00010(2\b\u00100\u001a\u0004\u0018\u00010\n2\u0006\u00101\u001a\u00020\u001e2\u0006\u00102\u001a\u00020\u001eH\u0002J\u0012\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u000106H\u0002J\u001e\u00107\u001a\u0002042\u0006\u00108\u001a\u00020\u001b2\u0006\u00109\u001a\u00020\u001b2\u0006\u00102\u001a\u00020\u001eJ\u0006\u0010:\u001a\u00020\fJ \u0010;\u001a\u0002042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\u0010\u0010<\u001a\u0002042\u0006\u0010=\u001a\u00020(H\u0016J\u0010\u0010>\u001a\u0002042\u0006\u0010=\u001a\u00020(H\u0016J\u000e\u0010?\u001a\u0002042\u0006\u0010@\u001a\u00020AR(\u0010\u000f\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\f8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\"\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u00020 X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u000e\u0010%\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010&\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010)\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0011¨\u0006C"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_PhotoLayout;", "Landroid/widget/RelativeLayout;", "Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_ItemImageView$OnImageClickListener;", "context", "Landroid/content/Context;", "template", "Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_ImageTemplate;", "(Landroid/content/Context;Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_ImageTemplate;)V", "PGPhotoItems", "", "Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_PhotoItem;", "templateImage", "Landroid/graphics/Bitmap;", "(Landroid/content/Context;Ljava/util/List;Landroid/graphics/Bitmap;)V", "image", "backgroundImage", "getBackgroundImage", "()Landroid/graphics/Bitmap;", "setBackgroundImage", "(Landroid/graphics/Bitmap;)V", "<set-?>", "Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_TransitionImageView;", "backgroundImageView", "getBackgroundImageView", "()Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_TransitionImageView;", "mBackgroundImage", "mImageHeight", "", "mImageWidth", "mInternalScaleRatio", "", "mOnDragListener", "Landroid/view/View$OnDragListener;", "getMOnDragListener$app_release", "()Landroid/view/View$OnDragListener;", "setMOnDragListener$app_release", "(Landroid/view/View$OnDragListener;)V", "mOutputScaleRatio", "mPGItemImageViews", "", "Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_ItemImageView;", "mPGPhotoItems", "mProgressBar", "Landroid/widget/ProgressBar;", "mViewHeight", "mViewWidth", "getTemplateImage", "addPhotoItemView", "item", "internalScale", "outputScaleRatio", "asyncCreateBackgroundImage", "", "path", "", "build", "viewWidth", "viewHeight", "createImage", "init", "onDoubleClickImage", "v", "onLongClickImage", "recycleImages", "recycleBackground", "", "Companion", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_PhotoLayout extends RelativeLayout implements PG_ItemImageView.OnImageClickListener {
    private PG_TransitionImageView backgroundImageView;
    private Bitmap mBackgroundImage;
    private int mImageHeight;
    private int mImageWidth;
    private float mInternalScaleRatio;
    private OnDragListener mOnDragListener;
    float mOutputScaleRatio;
    private List<PG_ItemImageView> mPGItemImageViews;
    private List<PG_PhotoItem> mPGPhotoItems;
    public ProgressBar mProgressBar;
    int mViewHeight;
    int mViewWidth;
    private Bitmap templateImage;
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "PG_PhotoLayout";
    private static final int ID_EDIT = 1;
    private static final int ID_CHANGE = 2;
    private static final int ID_DELETE = 3;
    private static final int ID_CANCEL = 4;

    @Override
    public void onDoubleClickImage(PG_ItemImageView v) {
        Intrinsics.checkNotNullParameter(v, "v");
    }

    public final OnDragListener getMOnDragListener$app_release() {
        return this.mOnDragListener;
    }

    public final void setMOnDragListener$app_release(OnDragListener onDragListener) {
        Intrinsics.checkNotNullParameter(onDragListener, "<set-?>");
        this.mOnDragListener = onDragListener;
    }

    public static final boolean mOnDragListener$lambda$0(View view, DragEvent dragEvent) {
        if (dragEvent.getAction() != 3) {
            return true;
        }
        Intrinsics.checkNotNull(view, "null cannot be cast to non-null type chin.pswm.gps.photo.location.map.photoGrid.template.PG_ItemImageView");
        PG_ItemImageView pG_ItemImageView = (PG_ItemImageView) view;
        Object localState = dragEvent.getLocalState();
        Intrinsics.checkNotNull(localState, "null cannot be cast to non-null type chin.pswm.gps.photo.location.map.photoGrid.template.PG_ItemImageView");
        PG_ItemImageView pG_ItemImageView2 = (PG_ItemImageView) localState;
        String imagePath = pG_ItemImageView.getPGPhotoItem() != null ? pG_ItemImageView.getPGPhotoItem().getImagePath() : "";
        String imagePath2 = pG_ItemImageView2.getPGPhotoItem() != null ? pG_ItemImageView2.getPGPhotoItem().getImagePath() : "";
        if (imagePath == null) {
            imagePath = "";
        }
        if (Intrinsics.areEqual(imagePath, imagePath2 != null ? imagePath2 : "")) {
            return true;
        }
        pG_ItemImageView.swapImage(pG_ItemImageView2);
        return true;
    }

    public final PG_TransitionImageView getBackgroundImageView() {
        return this.backgroundImageView;
    }

    public final Bitmap getTemplateImage() {
        return this.templateImage;
    }

    public final Bitmap getBackgroundImage() {
        PG_TransitionImageView pG_TransitionImageView = this.backgroundImageView;
        Intrinsics.checkNotNull(pG_TransitionImageView);
        return pG_TransitionImageView.getImage();
    }

    public final void setBackgroundImage(Bitmap bitmap) {
        this.mBackgroundImage = bitmap;
    }

    
    public PG_PhotoLayout(Context context, PG_ImageTemplate template) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(template, "template");
        this.mOnDragListener = new OnDragListener() { 
            @Override 
            public final boolean onDrag(View view, DragEvent dragEvent) {
                boolean mOnDragListener$lambda$0;
                mOnDragListener$lambda$0 = PG_PhotoLayout.mOnDragListener$lambda$0(view, dragEvent);
                return mOnDragListener$lambda$0;
            }
        };
        this.mInternalScaleRatio = 1.0f;
        this.mOutputScaleRatio = 1.0f;
        PG_PhotoUtils pG_PhotoUtils = PG_PhotoUtils.INSTANCE;
        String mtemplate = template.getMtemplate();
        Intrinsics.checkNotNull(mtemplate);
        init(Companion.parseImageTemplate(template), pG_PhotoUtils.decodePNGImage(context, mtemplate));
    }

    
    public PG_PhotoLayout(Context context, List<PG_PhotoItem> PGPhotoItems, Bitmap templateImage) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(PGPhotoItems, "PGPhotoItems");
        Intrinsics.checkNotNullParameter(templateImage, "templateImage");
        this.mOnDragListener = new OnDragListener() { 
            @Override 
            public final boolean onDrag(View view, DragEvent dragEvent) {
                boolean mOnDragListener$lambda$0;
                mOnDragListener$lambda$0 = PG_PhotoLayout.mOnDragListener$lambda$0(view, dragEvent);
                return mOnDragListener$lambda$0;
            }
        };
        this.mInternalScaleRatio = 1.0f;
        this.mOutputScaleRatio = 1.0f;
        init(PGPhotoItems, templateImage);
    }

    private final void init(List<PG_PhotoItem> list, Bitmap bitmap) {
        this.mPGPhotoItems = list;
        this.templateImage = bitmap;
        Intrinsics.checkNotNull(bitmap);
        this.mImageWidth = bitmap.getWidth();
        Bitmap bitmap2 = this.templateImage;
        Intrinsics.checkNotNull(bitmap2);
        this.mImageHeight = bitmap2.getHeight();
        this.mPGItemImageViews = new ArrayList();
        setLayerType(2, null);
    }

    
    private final void asyncCreateBackgroundImage(final String str) {
        new AsyncTask<Void, Void, Bitmap>() { 
            @Override 
            protected void onPreExecute() {
                ProgressBar progressBar;
                super.onPreExecute();
                progressBar = PG_PhotoLayout.this.mProgressBar;
                Intrinsics.checkNotNull(progressBar);
                progressBar.setVisibility(0);
            }

            @Override 
            public Bitmap doInBackground(Void... params) {
                Intrinsics.checkNotNullParameter(params, "params");
                try {
                    PG_ImageDecoder pG_ImageDecoder = PG_ImageDecoder.INSTANCE;
                    String str2 = str;
                    Intrinsics.checkNotNull(str2);
                    Bitmap decodeFileToBitmap = pG_ImageDecoder.decodeFileToBitmap(str2);
                    if (decodeFileToBitmap != null) {
                        Bitmap blurImage = PG_PhotoUtils.INSTANCE.blurImage(decodeFileToBitmap, 10.0f);
                        if (!Intrinsics.areEqual(decodeFileToBitmap, blurImage)) {
                            decodeFileToBitmap.recycle();
                            System.gc();
                        }
                        return blurImage;
                    }
                    return null;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                } catch (OutOfMemoryError e2) {
                    e2.printStackTrace();
                    return null;
                }
            }

            @Override 
            public void onPostExecute(Bitmap bitmap) {
                ProgressBar progressBar;
                int i;
                int i2;
                float f;
                super.onPostExecute(bitmap);
                progressBar = PG_PhotoLayout.this.mProgressBar;
                Intrinsics.checkNotNull(progressBar);
                progressBar.setVisibility(8);
                if (bitmap != null) {
                    PG_TransitionImageView backgroundImageView = PG_PhotoLayout.this.getBackgroundImageView();
                    Intrinsics.checkNotNull(backgroundImageView);
                    i = PG_PhotoLayout.this.mViewWidth;
                    i2 = PG_PhotoLayout.this.mViewHeight;
                    f = PG_PhotoLayout.this.mOutputScaleRatio;
                    backgroundImageView.init(bitmap, i, i2, f);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public final void build(int i, int i2, float f) {
        if (i < 1 || i2 < 1) {
            return;
        }
        this.mViewWidth = i;
        this.mViewHeight = i2;
        this.mOutputScaleRatio = f;
        List<PG_ItemImageView> list = this.mPGItemImageViews;
        Intrinsics.checkNotNull(list);
        list.clear();
        this.mInternalScaleRatio = 1.0f / PG_PhotoUtils.INSTANCE.calculateScaleRatio(this.mImageWidth, this.mImageHeight, i, i2);
        List<PG_PhotoItem> list2 = this.mPGPhotoItems;
        Intrinsics.checkNotNull(list2);
        for (PG_PhotoItem pG_PhotoItem : list2) {
            List<PG_ItemImageView> list3 = this.mPGItemImageViews;
            Intrinsics.checkNotNull(list3);
            PG_ItemImageView addPhotoItemView = addPhotoItemView(pG_PhotoItem, this.mInternalScaleRatio, this.mOutputScaleRatio);
            Intrinsics.checkNotNull(addPhotoItemView);
            list3.add(addPhotoItemView);
        }
        ImageView imageView = new ImageView(getContext());
        imageView.setBackground(new BitmapDrawable(getResources(), this.templateImage));
        addView(imageView, new LayoutParams(-1, -1));
        this.mProgressBar = new ProgressBar(getContext());
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(13);
        ProgressBar progressBar = this.mProgressBar;
        Intrinsics.checkNotNull(progressBar);
        progressBar.setVisibility(8);
        addView(this.mProgressBar, layoutParams);
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        this.backgroundImageView = new PG_TransitionImageView(context);
        addView(this.backgroundImageView, 0, new LayoutParams(-1, -1));
        PG_TransitionImageView pG_TransitionImageView = this.backgroundImageView;
        Intrinsics.checkNotNull(pG_TransitionImageView);
        pG_TransitionImageView.setOnImageClickListener(new PG_TransitionImageView.OnImageClickListener() { 
            @Override 
            public void onDoubleClickImage(PG_TransitionImageView view) {
                Intrinsics.checkNotNullParameter(view, "view");
            }

            @Override 
            public void onLongClickImage(PG_TransitionImageView view) {
                Intrinsics.checkNotNullParameter(view, "view");
            }
        });
        Bitmap bitmap = this.mBackgroundImage;
        if (bitmap != null) {
            Intrinsics.checkNotNull(bitmap);
            if (!bitmap.isRecycled()) {
                PG_TransitionImageView pG_TransitionImageView2 = this.backgroundImageView;
                Intrinsics.checkNotNull(pG_TransitionImageView2);
                Bitmap bitmap2 = this.mBackgroundImage;
                Intrinsics.checkNotNull(bitmap2);
                pG_TransitionImageView2.init(bitmap2, this.mViewWidth, this.mViewHeight, this.mOutputScaleRatio);
                return;
            }
        }
        List<PG_PhotoItem> list4 = this.mPGPhotoItems;
        Intrinsics.checkNotNull(list4);
        if (list4.size() > 0) {
            List<PG_PhotoItem> list5 = this.mPGPhotoItems;
            Intrinsics.checkNotNull(list5);
            if (list5.get(0).getImagePath() != null) {
                List<PG_PhotoItem> list6 = this.mPGPhotoItems;
                Intrinsics.checkNotNull(list6);
                String imagePath = list6.get(0).getImagePath();
                Intrinsics.checkNotNull(imagePath);
                if (imagePath.length() > 0) {
                    List<PG_PhotoItem> list7 = this.mPGPhotoItems;
                    Intrinsics.checkNotNull(list7);
                    asyncCreateBackgroundImage(list7.get(0).getImagePath());
                }
            }
        }
    }

    private final PG_ItemImageView addPhotoItemView(PG_PhotoItem pG_PhotoItem, float f, float f2) {
        if (pG_PhotoItem == null || pG_PhotoItem.getMaskPath() == null) {
            return null;
        }
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        PG_ItemImageView pG_ItemImageView = new PG_ItemImageView(context, pG_PhotoItem);
        Bitmap maskImage = pG_ItemImageView.getMaskImage();
        Intrinsics.checkNotNull(maskImage);
        float width = maskImage.getWidth() * f;
        Bitmap maskImage2 = pG_ItemImageView.getMaskImage();
        Intrinsics.checkNotNull(maskImage2);
        float height = maskImage2.getHeight() * f;
        pG_ItemImageView.init(width, height, f2);
        pG_ItemImageView.setOnImageClickListener(this);
        List<PG_PhotoItem> list = this.mPGPhotoItems;
        Intrinsics.checkNotNull(list);
        if (list.size() > 1) {
            pG_ItemImageView.setOnDragListener(this.mOnDragListener);
        }
        LayoutParams layoutParams = new LayoutParams((int) width, (int) height);
        layoutParams.leftMargin = (int) (pG_PhotoItem.getX() * f);
        layoutParams.topMargin = (int) (f * pG_PhotoItem.getY());
        pG_ItemImageView.setOriginalLayoutParams(layoutParams);
        addView(pG_ItemImageView, layoutParams);
        return pG_ItemImageView;
    }

    public final Bitmap createImage() {
        Bitmap bitmap = null;
        Bitmap bitmap2 = null;
        float f = this.mOutputScaleRatio;
        Bitmap template = Bitmap.createBitmap((int) (this.mViewWidth * f), (int) (f * this.mViewHeight), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(template);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        PG_TransitionImageView pG_TransitionImageView = this.backgroundImageView;
        Intrinsics.checkNotNull(pG_TransitionImageView);
        if (pG_TransitionImageView.getImage() != null) {
            PG_TransitionImageView pG_TransitionImageView2 = this.backgroundImageView;
            Intrinsics.checkNotNull(pG_TransitionImageView2);
            Bitmap image = pG_TransitionImageView2.getImage();
            Intrinsics.checkNotNull(image);
            if (!image.isRecycled()) {
                PG_TransitionImageView pG_TransitionImageView3 = this.backgroundImageView;
                Intrinsics.checkNotNull(pG_TransitionImageView3);
                Bitmap image2 = pG_TransitionImageView3.getImage();
                Intrinsics.checkNotNull(image2);
                PG_TransitionImageView pG_TransitionImageView4 = this.backgroundImageView;
                Intrinsics.checkNotNull(pG_TransitionImageView4);
                canvas.drawBitmap(image2, pG_TransitionImageView4.getScaleMatrix(), paint);
            }
        }
        canvas.saveLayer(0.0f, 0.0f, template.getWidth(), template.getHeight(), paint, 31);
        List<PG_ItemImageView> list = this.mPGItemImageViews;
        Intrinsics.checkNotNull(list);
        Iterator<PG_ItemImageView> it = list.iterator();
        while (it.hasNext()) {
            PG_ItemImageView next = it.next();
            if (next.getImage() != null) {
                Bitmap image3 = next.getImage();
                Intrinsics.checkNotNull(image3);
                if (!image3.isRecycled()) {
                    int left = (int) (next.getLeft() * this.mOutputScaleRatio);
                    int top = (int) (next.getTop() * this.mOutputScaleRatio);
                    int width = (int) (next.getWidth() * this.mOutputScaleRatio);
                    int height = (int) (next.getHeight() * this.mOutputScaleRatio);
                    float f2 = left;
                    float f3 = top;
                    canvas.saveLayer(f2, f3, left + width, top + height, paint, 31);
                    canvas.save();
                    canvas.translate(f2, f3);
                    canvas.clipRect(0, 0, width, height);
                    Bitmap image4 = next.getImage();
                    Intrinsics.checkNotNull(image4);
                    canvas.drawBitmap(image4, next.getScaleMatrix(), paint);
                    canvas.restore();
                    canvas.save();
                    canvas.translate(f2, f3);
                    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                    Bitmap maskImage = next.getMaskImage();
                    Intrinsics.checkNotNull(maskImage);
                    canvas.drawBitmap(maskImage, next.getScaleMaskMatrix(), paint);
                    paint.setXfermode(null);
                    canvas.restore();
                    canvas.restore();
                    it = it;
                }
            }
        }
        Bitmap bitmap3 = this.templateImage;
        if (bitmap3 != null) {
            Intrinsics.checkNotNull(bitmap3);
            PG_ImageUtils pG_ImageUtils = PG_ImageUtils.INSTANCE;
            float f4 = this.mOutputScaleRatio;
            Intrinsics.checkNotNull(this.templateImage);
            Intrinsics.checkNotNull(this.templateImage);
            canvas.drawBitmap(bitmap3, pG_ImageUtils.createMatrixToDrawImageInCenterView(this.mViewWidth * f4, f4 * this.mViewHeight, bitmap.getWidth(), bitmap2.getHeight()), paint);
        }
        canvas.restore();
        Intrinsics.checkNotNullExpressionValue(template, "template");
        return template;
    }

    public final void recycleImages(boolean z) {
        if (z) {
            PG_TransitionImageView pG_TransitionImageView = this.backgroundImageView;
            Intrinsics.checkNotNull(pG_TransitionImageView);
            pG_TransitionImageView.recycleImages();
        }
        List<PG_ItemImageView> list = this.mPGItemImageViews;
        Intrinsics.checkNotNull(list);
        for (PG_ItemImageView pG_ItemImageView : list) {
            pG_ItemImageView.recycleImages(z);
        }
        Bitmap bitmap = this.templateImage;
        if (bitmap != null) {
            Intrinsics.checkNotNull(bitmap);
            if (!bitmap.isRecycled()) {
                Bitmap bitmap2 = this.templateImage;
                Intrinsics.checkNotNull(bitmap2);
                bitmap2.recycle();
                this.templateImage = null;
            }
        }
        System.gc();
    }

    @Override 
    public void onLongClickImage(PG_ItemImageView v) {
        Intrinsics.checkNotNullParameter(v, "v");
        List<PG_PhotoItem> list = this.mPGPhotoItems;
        Intrinsics.checkNotNull(list);
        if (list.size() > 1) {
            v.setTag("x=" + v.getPGPhotoItem().getX() + ",y=" + v.getPGPhotoItem().getY() + ",path=" + v.getPGPhotoItem().getImagePath());
            Object tag = v.getTag();
            Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type kotlin.CharSequence");
            ClipData.Item item = new ClipData.Item((CharSequence) tag);
            v.startDrag(new ClipData(v.getTag().toString(), new String[]{"text/plain"}, item), new DragShadowBuilder(v), v, 0);
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_PhotoLayout$Companion;", "", "()V", "ID_CANCEL", "", "ID_CHANGE", "ID_DELETE", "ID_EDIT", "TAG", "", "kotlin.jvm.PlatformType", "parseImageTemplate", "Ljava/util/ArrayList;", "Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_PhotoItem;", "template", "Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_ImageTemplate;", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ArrayList<PG_PhotoItem> parseImageTemplate(PG_ImageTemplate template) {
            List emptyList;
            List emptyList2;
            boolean z;
            boolean z2;
            Intrinsics.checkNotNullParameter(template, "template");
            ArrayList<PG_PhotoItem> arrayList = new ArrayList<>();
            try {
                String child = template.getChild();
                Intrinsics.checkNotNull(child);
                List<String> split = new Regex(";").split(child, 0);

                if (!split.isEmpty()) {
                    ListIterator<String> listIterator = split.listIterator(split.size());
                    while (listIterator.hasPrevious()) {
                        z2 = listIterator.previous().length() == 0;
                        if (!z2) {
                            emptyList = CollectionsKt.take(split, listIterator.nextIndex() + 1);
                            break;
                        }
                    }
                }
                emptyList = CollectionsKt.emptyList();
                String[] strArr = (String[]) emptyList.toArray(new String[0]);
                if (strArr != null) {
                    for (String str : strArr) {
                        List<String> split2 = new Regex(",").split(str, 0);
                        if (!split2.isEmpty()) {
                            ListIterator<String> listIterator2 = split2.listIterator(split2.size());
                            while (listIterator2.hasPrevious()) {
                                z2 = listIterator2.previous().length() == 0;
                                if (!z2) {
                                    emptyList2 = CollectionsKt.take(split, listIterator2.nextIndex() + 1);
                                    break;
                                }
                            }
                        }
                        emptyList2 = CollectionsKt.emptyList();
                        String[] strArr2 = (String[]) emptyList2.toArray(new String[0]);
                        if (strArr2 != null) {
                            PG_PhotoItem pG_PhotoItem = new PG_PhotoItem();
                            pG_PhotoItem.setIndex(Integer.parseInt(strArr2[0]));
                            pG_PhotoItem.setX(Integer.parseInt(strArr2[1]));
                            pG_PhotoItem.setY(Integer.parseInt(strArr2[2]));
                            pG_PhotoItem.setMaskPath(strArr2[3]);
                            arrayList.add(pG_PhotoItem);
                        }
                    }
                    final PG_PhotoLayout$Companion$parseImageTemplate$1 pG_PhotoLayout$Companion$parseImageTemplate$1 = new PG_PhotoLayout$Companion$parseImageTemplate$1() { 
                        @Override 
                        public final Integer invoke(PG_PhotoItem pG_PhotoItem2, PG_PhotoItem pG_PhotoItem3) {
                            return Integer.valueOf(pG_PhotoItem3.getIndex() - pG_PhotoItem2.getIndex());
                        }
                    };
                    Collections.sort(arrayList, new Comparator() { 
                        @Override 
                        public final int compare(Object obj, Object obj2) {
                            int parseImageTemplate$lambda$2;
                            parseImageTemplate$lambda$2 = PG_PhotoLayout.Companion.parseImageTemplate$lambda$2(pG_PhotoLayout$Companion$parseImageTemplate$1, obj, obj2);
                            return parseImageTemplate$lambda$2;
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return arrayList;
        }

        public static final int parseImageTemplate$lambda$2(Function2 tmp0, Object obj, Object obj2) {
            Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
            return ((Number) tmp0.invoke(obj, obj2)).intValue();
        }
    }
}
