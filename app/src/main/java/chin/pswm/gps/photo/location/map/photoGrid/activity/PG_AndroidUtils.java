package chin.pswm.gps.photo.location.map.photoGrid.activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.TypedValue;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.ObjectKey;

import java.io.File;

import chin.pswm.gps.photo.location.map.photoGrid.model.PG_FilterData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
@SuppressWarnings("all")
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b3\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020AJ\u0016\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020GJ\u000e\u0010H\u001a\u00020A2\u0006\u0010I\u001a\u00020JJ\u000e\u0010K\u001a\u00020?2\u0006\u0010I\u001a\u00020JJ\u0016\u0010L\u001a\u00020C2\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020CJ\u0016\u0010P\u001a\u00020C2\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020CJ \u0010Q\u001a\u00020R2\u0006\u0010F\u001a\u00020G2\u0006\u0010S\u001a\u00020T2\b\u0010U\u001a\u0004\u0018\u00010\u0004J\u0016\u0010V\u001a\u00020E2\u0006\u0010W\u001a\u00020C2\u0006\u0010F\u001a\u00020GJ\u001e\u0010X\u001a\u00020A2\u0006\u0010@\u001a\u00020A2\u0006\u0010Y\u001a\u00020C2\u0006\u0010Z\u001a\u00020CR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR\"\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u0015\u0010\r\"\u0004\b\u0016\u0010\u000fR\"\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u000fR\"\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u001b\u0010\r\"\u0004\b\u001c\u0010\u000fR\"\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u001e\u0010\r\"\u0004\b\u001f\u0010\u000fR\"\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b!\u0010\r\"\u0004\b\"\u0010\u000fR\"\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b$\u0010\r\"\u0004\b%\u0010\u000fR\"\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b'\u0010\r\"\u0004\b(\u0010\u000fR\"\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b*\u0010\r\"\u0004\b+\u0010\u000fR\"\u0010,\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b-\u0010\r\"\u0004\b.\u0010\u000fR\"\u0010/\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b0\u0010\r\"\u0004\b1\u0010\u000fR\"\u00102\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b3\u0010\r\"\u0004\b4\u0010\u000fR\"\u00105\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b6\u0010\r\"\u0004\b7\u0010\u000fR\"\u00108\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b9\u0010\r\"\u0004\b:\u0010\u000fR\"\u0010;\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b<\u0010\r\"\u0004\b=\u0010\u000f¨\u0006["}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/activity/PG_AndroidUtils;", "", "()V", "ASSET_PREFIX", "", "getASSET_PREFIX", "()Ljava/lang/String;", "DRAWABLE_PREFIX", "getDRAWABLE_PREFIX", "filter_bw", "", "Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_FilterData;", "getFilter_bw", "()[Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_FilterData;", "setFilter_bw", "([Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_FilterData;)V", "[Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_FilterData;", "filter_clr1", "getFilter_clr1", "setFilter_clr1", "filter_clr2", "getFilter_clr2", "setFilter_clr2", "filter_dark", "getFilter_dark", "setFilter_dark", "filter_duo", "getFilter_duo", "setFilter_duo", "filter_elegant", "getFilter_elegant", "setFilter_elegant", "filter_euro", "getFilter_euro", "setFilter_euro", "filter_film", "getFilter_film", "setFilter_film", "filter_fresh", "getFilter_fresh", "setFilter_fresh", "filter_golden", "getFilter_golden", "setFilter_golden", "filter_ins", "getFilter_ins", "setFilter_ins", "filter_lomo", "getFilter_lomo", "setFilter_lomo", "filter_movie", "getFilter_movie", "setFilter_movie", "filter_pink", "getFilter_pink", "setFilter_pink", "filter_retro", "getFilter_retro", "setFilter_retro", "filter_tint", "getFilter_tint", "setFilter_tint", "bitmapToIntArray", "", "bitmap", "Landroid/graphics/Bitmap;", "dipTopx", "", "dip", "", "context", "Landroid/content/Context;", "drawableToBitmap", "drawable", "Landroid/graphics/drawable/Drawable;", "drawableToIntArray", "getBitmapOfHeight", "res", "Landroid/content/res/Resources;", "getBitmapOfWidth", "loadImageWithGlide", "", "imageView", "Landroid/widget/ImageView;", "str", "pxTodip", "px", "resizeImageToNewSize", "i", "i2", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_AndroidUtils {
    public static final PG_AndroidUtils INSTANCE = new PG_AndroidUtils();
    private static PG_FilterData[] filter_fresh = {new PG_FilterData("Fresh1", 1.45f, 1.29f, 1.55f, 1.0f), new PG_FilterData("Fresh2", 1.55f, 1.35f, 1.29f, 1.0f), new PG_FilterData("Fresh3", 1.55f, 1.29f, 1.85f, 1.0f), new PG_FilterData("Fresh4", 1.55f, 1.29f, 1.5f, 1.0f), new PG_FilterData("Fresh5", 1.65f, 1.2f, 1.25f, 1.0f)};
    private static PG_FilterData[] filter_euro = {new PG_FilterData("Euro1", 1.45f, 1.35f, 1.1f, 1.0f), new PG_FilterData("Euro2", 1.3f, 1.3f, 1.55f, 1.0f), new PG_FilterData("Euro3", 1.45f, 1.55f, 1.63f, 1.0f), new PG_FilterData("Euro4", 1.1f, 1.35f, 1.35f, 1.0f)};
    private static PG_FilterData[] filter_dark = {new PG_FilterData("Dark1", 1.5f, 1.5f, 1.5f, 0.0f), new PG_FilterData("Dark2", 0.5f, 0.5f, 0.5f, 0.0f), new PG_FilterData("Dark3", 1.0f, 1.0f, 1.0f, 0.0f)};
    private static PG_FilterData[] filter_ins = {new PG_FilterData("Ins1", 1.15f, 1.02f, 1.15f, 1.0f), new PG_FilterData("Ins2", 1.2f, 1.2f, 1.02f, 1.0f), new PG_FilterData("Ins3", 1.0f, 1.15f, 1.15f, 0.8f), new PG_FilterData("Ins4", 1.58f, 1.58f, 1.58f, 0.5f)};
    private static PG_FilterData[] filter_elegant = {new PG_FilterData("Elegant1", 1.35f, 1.38f, 1.33f, 1.1f), new PG_FilterData("Elegant2", 1.45f, 1.25f, 1.15f, 0.5f), new PG_FilterData("Elegant3", 1.35f, 1.33f, 1.33f, 0.6f), new PG_FilterData("Elegant4", 1.55f, 1.53f, 1.53f, 1.0f), new PG_FilterData("Elegant5", 1.55f, 1.53f, 1.53f, 0.7f)};
    private static PG_FilterData[] filter_golden = {new PG_FilterData("Golden1", 1.15f, 1.1f, 1.0f, 0.8f), new PG_FilterData("Golden2", 0.87f, 0.73f, 0.87f, 1.0f), new PG_FilterData("Golden3", 1.0f, 0.9f, 0.7f, 1.0f), new PG_FilterData("Golden4", 1.0f, 0.8f, 0.5f, 0.8f)};
    private static PG_FilterData[] filter_tint = {new PG_FilterData("Tint1", 1.0f, 0.85f, 0.65f, 1.0f), new PG_FilterData("Tint2", 0.67f, 1.0f, 0.93f, 1.0f), new PG_FilterData("Tint3", 0.61f, 0.77f, 1.3f, 0.9f), new PG_FilterData("Tint4", 1.47f, 1.23f, 1.42f, 0.4f)};
    private static PG_FilterData[] filter_film = {new PG_FilterData("Film1", 1.25f, 1.02f, 1.02f, 1.0f), new PG_FilterData("Film2", 1.0f, 0.8f, 0.74f, 1.0f), new PG_FilterData("Film3", 0.84f, 0.8f, 0.78f, 1.0f), new PG_FilterData("Film4", 1.4f, 1.34f, 1.3f, 0.8f), new PG_FilterData("Film5", 1.0f, 0.75f, 0.72f, 0.7f)};
    private static PG_FilterData[] filter_lomo = {new PG_FilterData("Lomo1", 0.74f, 0.67f, 0.64f, 0.65f), new PG_FilterData("Lomo2", 0.95f, 1.14f, 0.95f, 0.85f), new PG_FilterData("Lomo3", 0.75f, 0.4f, 0.3f, 0.3f), new PG_FilterData("Lomo4", 1.47f, 1.23f, 1.42f, 0.5f)};
    private static PG_FilterData[] filter_movie = {new PG_FilterData("Movie1", 1.02f, 1.02f, 1.02f, 0.5f), new PG_FilterData("Movie2", 1.35f, 1.43f, 1.33f, 0.7f), new PG_FilterData("Movie3", 1.1f, 1.04f, 1.14f, 1.0f), new PG_FilterData("Movie4", 1.7f, 1.3f, 1.8f, 0.6f), new PG_FilterData("Movie5", 1.2f, 1.1f, 0.95f, 1.2f)};
    private static PG_FilterData[] filter_retro = {new PG_FilterData("Retro1", 1.25f, 1.02f, 1.02f, 1.0f), new PG_FilterData("Retro2", 1.55f, 1.53f, 1.53f, 1.0f), new PG_FilterData("Retro3", 1.55f, 2.04f, 2.04f, 1.0f), new PG_FilterData("Retro4", 1.25f, 1.55f, 1.55f, 1.0f)};
    private static PG_FilterData[] filter_bw = {new PG_FilterData("BW1", 1.2f, 1.02f, 1.02f, 0.0f), new PG_FilterData("BW2", 1.55f, 1.53f, 1.53f, 0.0f), new PG_FilterData("BW3", 1.85f, 1.8f, 1.85f, 0.0f)};
    private static PG_FilterData[] filter_clr1 = {new PG_FilterData("Color1", 0.9f, 0.0f, 0.3f, 0.0f), new PG_FilterData("Color2", 0.3f, 0.8f, 1.2f, 0.0f), new PG_FilterData("Color3", 1.2f, 0.8f, 0.3f, 0.0f), new PG_FilterData("Color4", 0.5f, 1.5f, 1.0f, 0.1f)};
    private static PG_FilterData[] filter_clr2 = {new PG_FilterData("Color1", 0.7f, 1.2f, 1.6f, 0.0f), new PG_FilterData("Color2", 1.4f, 1.1f, 0.7f, 0.0f), new PG_FilterData("Color3", 1.2f, 0.8f, 0.3f, 0.0f), new PG_FilterData("Color4", 1.2f, 0.84f, 0.8f, 0.1f)};
    private static PG_FilterData[] filter_duo = {new PG_FilterData("Duo1", 0.9f, 0.5f, 1.3f, 0.0f), new PG_FilterData("Duo2", 0.33f, 0.51f, 1.0f, 0.0f), new PG_FilterData("Duo3", 1.0f, 0.5f, 0.99f, 0.0f), new PG_FilterData("Duo4", 0.8f, 0.44f, 0.4f, 0.1f)};
    private static PG_FilterData[] filter_pink = {new PG_FilterData("Pink1", 1.28f, 0.84f, 1.28f, 0.0f), new PG_FilterData("Pink2", 1.55f, 1.04f, 1.04f, 0.0f), new PG_FilterData("Pink3", 1.1f, 0.5f, 0.5f, 0.0f), new PG_FilterData("Pink4", 1.5f, 1.1f, 1.5f, 0.0f)};
    private static final String ASSET_PREFIX = "assets://";
    private static final String DRAWABLE_PREFIX = "drawable://";

    private PG_AndroidUtils() {
    }

    public final PG_FilterData[] getFilter_fresh() {
        return filter_fresh;
    }

    public final void setFilter_fresh(PG_FilterData[] pG_FilterDataArr) {
        Intrinsics.checkNotNullParameter(pG_FilterDataArr, "<set-?>");
        filter_fresh = pG_FilterDataArr;
    }

    public final PG_FilterData[] getFilter_euro() {
        return filter_euro;
    }

    public final void setFilter_euro(PG_FilterData[] pG_FilterDataArr) {
        Intrinsics.checkNotNullParameter(pG_FilterDataArr, "<set-?>");
        filter_euro = pG_FilterDataArr;
    }

    public final PG_FilterData[] getFilter_dark() {
        return filter_dark;
    }

    public final void setFilter_dark(PG_FilterData[] pG_FilterDataArr) {
        Intrinsics.checkNotNullParameter(pG_FilterDataArr, "<set-?>");
        filter_dark = pG_FilterDataArr;
    }

    public final PG_FilterData[] getFilter_ins() {
        return filter_ins;
    }

    public final void setFilter_ins(PG_FilterData[] pG_FilterDataArr) {
        Intrinsics.checkNotNullParameter(pG_FilterDataArr, "<set-?>");
        filter_ins = pG_FilterDataArr;
    }

    public final PG_FilterData[] getFilter_elegant() {
        return filter_elegant;
    }

    public final void setFilter_elegant(PG_FilterData[] pG_FilterDataArr) {
        Intrinsics.checkNotNullParameter(pG_FilterDataArr, "<set-?>");
        filter_elegant = pG_FilterDataArr;
    }

    public final PG_FilterData[] getFilter_golden() {
        return filter_golden;
    }

    public final void setFilter_golden(PG_FilterData[] pG_FilterDataArr) {
        Intrinsics.checkNotNullParameter(pG_FilterDataArr, "<set-?>");
        filter_golden = pG_FilterDataArr;
    }

    public final PG_FilterData[] getFilter_tint() {
        return filter_tint;
    }

    public final void setFilter_tint(PG_FilterData[] pG_FilterDataArr) {
        Intrinsics.checkNotNullParameter(pG_FilterDataArr, "<set-?>");
        filter_tint = pG_FilterDataArr;
    }

    public final PG_FilterData[] getFilter_film() {
        return filter_film;
    }

    public final void setFilter_film(PG_FilterData[] pG_FilterDataArr) {
        Intrinsics.checkNotNullParameter(pG_FilterDataArr, "<set-?>");
        filter_film = pG_FilterDataArr;
    }

    public final PG_FilterData[] getFilter_lomo() {
        return filter_lomo;
    }

    public final void setFilter_lomo(PG_FilterData[] pG_FilterDataArr) {
        Intrinsics.checkNotNullParameter(pG_FilterDataArr, "<set-?>");
        filter_lomo = pG_FilterDataArr;
    }

    public final PG_FilterData[] getFilter_movie() {
        return filter_movie;
    }

    public final void setFilter_movie(PG_FilterData[] pG_FilterDataArr) {
        Intrinsics.checkNotNullParameter(pG_FilterDataArr, "<set-?>");
        filter_movie = pG_FilterDataArr;
    }

    public final PG_FilterData[] getFilter_retro() {
        return filter_retro;
    }

    public final void setFilter_retro(PG_FilterData[] pG_FilterDataArr) {
        Intrinsics.checkNotNullParameter(pG_FilterDataArr, "<set-?>");
        filter_retro = pG_FilterDataArr;
    }

    public final PG_FilterData[] getFilter_bw() {
        return filter_bw;
    }

    public final void setFilter_bw(PG_FilterData[] pG_FilterDataArr) {
        Intrinsics.checkNotNullParameter(pG_FilterDataArr, "<set-?>");
        filter_bw = pG_FilterDataArr;
    }

    public final PG_FilterData[] getFilter_clr1() {
        return filter_clr1;
    }

    public final void setFilter_clr1(PG_FilterData[] pG_FilterDataArr) {
        Intrinsics.checkNotNullParameter(pG_FilterDataArr, "<set-?>");
        filter_clr1 = pG_FilterDataArr;
    }

    public final PG_FilterData[] getFilter_clr2() {
        return filter_clr2;
    }

    public final void setFilter_clr2(PG_FilterData[] pG_FilterDataArr) {
        Intrinsics.checkNotNullParameter(pG_FilterDataArr, "<set-?>");
        filter_clr2 = pG_FilterDataArr;
    }

    public final PG_FilterData[] getFilter_duo() {
        return filter_duo;
    }

    public final void setFilter_duo(PG_FilterData[] pG_FilterDataArr) {
        Intrinsics.checkNotNullParameter(pG_FilterDataArr, "<set-?>");
        filter_duo = pG_FilterDataArr;
    }

    public final PG_FilterData[] getFilter_pink() {
        return filter_pink;
    }

    public final void setFilter_pink(PG_FilterData[] pG_FilterDataArr) {
        Intrinsics.checkNotNullParameter(pG_FilterDataArr, "<set-?>");
        filter_pink = pG_FilterDataArr;
    }

    public final int dipTopx(float f, Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public final float pxTodip(int i, Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return i / context.getResources().getDisplayMetrics().density;
    }

    public final Bitmap drawableToBitmap(Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
        return bitmap;
    }

    public final Bitmap resizeImageToNewSize(Bitmap bitmap, int i, int i2) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = i;
        float f2 = i2;
        if (height != i2 || width != i) {
            float f3 = width;
            float f4 = f / f3;
            float f5 = height;
            float f6 = f2 / f5;
            if (f4 >= f6) {
                f4 = f6;
            }
            f2 = f5 * f4;
            f = f3 * f4;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) f, (int) f2, true);
        Intrinsics.checkNotNullExpressionValue(createScaledBitmap, "createScaledBitmap(bitma…oInt(), f2.toInt(), true)");
        return createScaledBitmap;
    }

    public final int[] drawableToIntArray(Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        Bitmap drawableToBitmap = INSTANCE.drawableToBitmap(drawable);
        int width = drawableToBitmap.getWidth();
        int height = drawableToBitmap.getHeight();
        int[] iArr = new int[width * height];
        drawableToBitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        return iArr;
    }

    public final int[] bitmapToIntArray(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        return iArr;
    }

    public final int getBitmapOfWidth(Resources res, int i) {
        Intrinsics.checkNotNullParameter(res, "res");
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(res, i, options);
            return options.outWidth;
        } catch (Exception unused) {
            return 0;
        }
    }

    public final int getBitmapOfHeight(Resources res, int i) {
        Intrinsics.checkNotNullParameter(res, "res");
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(res, i, options);
            return options.outHeight;
        } catch (Exception unused) {
            return 0;
        }
    }

    public final String getASSET_PREFIX() {
        return ASSET_PREFIX;
    }

    public final String getDRAWABLE_PREFIX() {
        return DRAWABLE_PREFIX;
    }

    public final void loadImageWithGlide(Context context, ImageView imageView, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        if (str != null && str.length() > 1) {
            if (StringsKt.startsWith(str, "http://", false) || StringsKt.startsWith(str, "https://", false)) {
                ((RequestBuilder) Glide.with(context).load(str).diskCacheStrategy(DiskCacheStrategy.RESOURCE)).into(imageView);
                return;
            }
            String str2 = DRAWABLE_PREFIX;
            if (StringsKt.startsWith(str, str2, false)) {
                try {
                    RequestManager with = Glide.with(context);
                    String substring = str.substring(str2.length());
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                    with.load(Integer.valueOf(Integer.parseInt(substring))).into(imageView);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                String str3 = ASSET_PREFIX;
                if (StringsKt.startsWith(str, str3, false)) {
                    RequestManager with2 = Glide.with(context);
                    StringBuilder sb = new StringBuilder("file:///android_asset/");
                    String substring2 = str.substring(str3.length());
                    Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
                    RequestBuilder<Drawable> load = with2.load(Uri.parse(sb.append(substring2).toString()));
                    String valueOf = String.valueOf(System.currentTimeMillis());
                    Intrinsics.checkNotNull(valueOf, "null cannot be cast to non-null type com.bumptech.glide.load.Key");
                    ((RequestBuilder) load.signature(new ObjectKey(System.currentTimeMillis()))).into(imageView);
                    return;
                }
                Glide.with(context).load(new File(str)).into(imageView);
            }
        }
    }
}
