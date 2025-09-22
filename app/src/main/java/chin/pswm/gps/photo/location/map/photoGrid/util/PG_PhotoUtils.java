package chin.pswm.gps.photo.location.map.photoGrid.util;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.ObjectKey;
import com.fom.rapid.app.Media;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;


@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aJ&\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\fJ&\u0010 \u001a\u00020!2\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\fJ\u000e\u0010\"\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017J$\u0010#\u001a\u00020\u00172\f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\fJ\u0016\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\u00172\u0006\u0010)\u001a\u00020\u0017J\u001e\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\u00172\u0006\u0010)\u001a\u00020\u00172\u0006\u0010*\u001a\u00020+J\u0018\u0010,\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u0004J\u001a\u0010.\u001a\u0004\u0018\u00010\u00172\u0006\u0010/\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\fH\u0007J\u0016\u00100\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u00101\u001a\u00020\fJ\u0018\u00102\u001a\u0004\u0018\u00010\u00172\u0006\u00103\u001a\u00020\u00172\u0006\u00104\u001a\u00020\fJ\u0016\u00105\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u00106\u001a\u00020\u0004J\u000e\u00107\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017J\u0018\u00108\u001a\u0002092\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010:\u001a\u00020\fH\u0002J\u0018\u0010;\u001a\u0002092\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010<\u001a\u00020\fH\u0002J \u0010=\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010>\u001a\u00020?2\b\u0010-\u001a\u0004\u0018\u00010\u0004J&\u0010@\u001a\u0004\u0018\u00010\u00172\b\u00103\u001a\u0004\u0018\u00010\u00172\u0006\u0010A\u001a\u00020\u001a2\b\b\u0002\u00102\u001a\u000209H\u0007J\u0016\u0010B\u001a\u00020\u00172\u0006\u0010C\u001a\u00020\u00172\u0006\u0010D\u001a\u00020\u001aR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\fX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\fX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e¨\u0006E"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/util/PG_PhotoUtils;", "", "()V", "ASSET_PREFIX", "", "getASSET_PREFIX", "()Ljava/lang/String;", "DRAWABLE_PREFIX", "getDRAWABLE_PREFIX", "EDITED_WHITE_IMAGE_SUFFIX", "getEDITED_WHITE_IMAGE_SUFFIX", "FLIP_HORIZONTAL", "", "getFLIP_HORIZONTAL", "()I", "FLIP_VERTICAL", "getFLIP_VERTICAL", "addImageToGallery", "", "filePath", "context", "Landroid/content/Context;", "blurImage", "Landroid/graphics/Bitmap;", "bitmap", "radius", "", "calculateScaleRatio", "imageWidth", "imageHeight", "viewWidth", "viewHeight", "calculateThumbnailSize", "", "cleanImage", "createBitmapMask", "pointList", "Ljava/util/ArrayList;", "Landroid/graphics/PointF;", "cropImage", "mainImage", "mask", "m", "Landroid/graphics/Matrix;", "decodePNGImage", "uri", "fastblur", "sentBitmap", "fillBackgroundColorToImage", "color", "flip", "src", "type", "getCameraPhotoOrientation", "imagePath", "getCircularBitmap", "isTransparentColumn", "", "column", "isTransparentRow", "row", "loadImageWithGlide", "imageView", "Landroid/widget/ImageView;", "rotateImage", "degs", "transparentPadding", "image", "ratioWidthPerHeight", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_PhotoUtils {
    public static final PG_PhotoUtils INSTANCE = new PG_PhotoUtils();
    private static final String EDITED_WHITE_IMAGE_SUFFIX = "_white.jpg";
    private static final int FLIP_VERTICAL = 1;
    private static final int FLIP_HORIZONTAL = 2;
    private static final String DRAWABLE_PREFIX = "drawable://";
    private static final String ASSET_PREFIX = "assets://";

    public final Bitmap blurImage(Bitmap bitmap, float f) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        return null;
    }

    public final Bitmap rotateImage(Bitmap bitmap, float f) {
        return rotateImage$default(this, bitmap, f, false, 4, null);
    }

    private PG_PhotoUtils() {
    }

    public final String getEDITED_WHITE_IMAGE_SUFFIX() {
        return EDITED_WHITE_IMAGE_SUFFIX;
    }

    public final int getFLIP_VERTICAL() {
        return FLIP_VERTICAL;
    }

    public final int getFLIP_HORIZONTAL() {
        return FLIP_HORIZONTAL;
    }

    public final String getDRAWABLE_PREFIX() {
        return DRAWABLE_PREFIX;
    }

    public final String getASSET_PREFIX() {
        return ASSET_PREFIX;
    }

    public final void addImageToGallery(String filePath, Context context) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        Intrinsics.checkNotNullParameter(context, "context");
        ContentValues contentValues = new ContentValues();
        contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("mime_type", "image/png");
        contentValues.put("_data", filePath);
        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
    }

    public final Bitmap fillBackgroundColorToImage(Bitmap bitmap, int i) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Bitmap result = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawColor(i);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint());
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    public static  Bitmap rotateImage$default(PG_PhotoUtils pG_PhotoUtils, Bitmap bitmap, float f, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return pG_PhotoUtils.rotateImage(bitmap, f, z);
    }

    public final Bitmap rotateImage(Bitmap bitmap, float f, boolean z) {
        if (f == 0.0f) {
            return bitmap;
        }
        if (bitmap != null) {
            Matrix matrix = new Matrix();
            matrix.postRotate(f);
            if (z) {
                matrix.postScale(1.0f, -1.0f);
            }
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }
        return null;
    }

    public final int getCameraPhotoOrientation(Context context, String imagePath) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imagePath, "imagePath");
        try {
            int attributeInt = new ExifInterface(new File(imagePath).getAbsolutePath()).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
            if (attributeInt != 3) {
                return attributeInt != 8 ? 90 : 270;
            }
            return 180;
        } catch (Exception e) {
            e.printStackTrace();
            return 90;
        }
    }

    public final void loadImageWithGlide(Context context, ImageView imageView, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        if (str == null || str.length() <= 1) {
            return;
        }
        if (StringsKt.startsWith(str, "http://", false) || StringsKt.startsWith(str, "https://", false)) {
            Glide.with(context).load(str).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView);
            return;
        }
        String str2 = DRAWABLE_PREFIX;
        if (StringsKt.startsWith(str, str2, false)) {
            try {
                String substring = str.substring(str2.length());
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                Glide.with(context).load(Integer.valueOf(Integer.parseInt(substring))).into(imageView);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        String str3 = ASSET_PREFIX;
        if (StringsKt.startsWith(str, str3, false)) {
            String substring2 = str.substring(str3.length());
            Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
            Glide.with(context).load(Uri.parse("file:///android_asset/" + substring2)).signature(new ObjectKey(String.valueOf(System.currentTimeMillis()))).into(imageView);
            return;
        }
        Glide.with(context).load(new File(str)).into(imageView);
    }

    public final Bitmap decodePNGImage(Context context, String uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        String str = DRAWABLE_PREFIX;
        if (StringsKt.startsWith(uri, str, false)) {
            try {
                String substring = uri.substring(str.length());
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                return BitmapFactory.decodeResource(context.getResources(), Integer.parseInt(substring));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String str2 = ASSET_PREFIX;
            if (StringsKt.startsWith(uri, str2, false)) {
                String substring2 = uri.substring(str2.length());
                Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
                try {
                    InputStream open = context.getAssets().open(substring2);
                    Intrinsics.checkNotNullExpressionValue(open, "context.assets.open(path)");
                    return BitmapFactory.decodeStream(open);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } else {
                return BitmapFactory.decodeFile(uri);
            }
        }
        return null;
    }

    public final Bitmap flip(Bitmap src, int i) {
        Intrinsics.checkNotNullParameter(src, "src");
        Matrix matrix = new Matrix();
        if (i == FLIP_VERTICAL) {
            matrix.preScale(1.0f, -1.0f);
        } else if (i != FLIP_HORIZONTAL) {
            return null;
        } else {
            matrix.preScale(-1.0f, 1.0f);
        }
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }

    @Deprecated(message = " ")
    public final Bitmap fastblur(Bitmap sentBitmap, int i) {
        int[] iArr;
        int i2 = i;
        Intrinsics.checkNotNullParameter(sentBitmap, "sentBitmap");
        Bitmap copy = sentBitmap.copy(sentBitmap.getConfig(), true);
        if (i2 < 1) {
            return null;
        }
        int width = copy.getWidth();
        int height = copy.getHeight();
        int i3 = width * height;
        int[] iArr2 = new int[i3];
        if (copy.isRecycled()) {
            return null;
        }
        copy.getPixels(iArr2, 0, width, 0, 0, width, height);
        int i4 = width - 1;
        int i5 = height - 1;
        int i6 = i2 + i2 + 1;
        int[] iArr3 = new int[i3];
        int[] iArr4 = new int[i3];
        int[] iArr5 = new int[i3];
        int[] iArr6 = new int[Math.max(width, height)];
        int i7 = (i6 + 1) >> 1;
        int i8 = i7 * i7;
        int i9 = i8 * 256;
        int[] iArr7 = new int[i9];
        for (int i10 = 0; i10 < i9; i10++) {
            iArr7[i10] = i10 / i8;
        }
        int[][] iArr8 = new int[][]{new int[i6]};
        for (int i11 = 0; i11 < i6; i11++) {
            iArr8[i11] = new int[3];
        }
        int i12 = i2 + 1;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        while (i13 < height) {
            Bitmap bitmap = copy;
            int i16 = height;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            int i25 = -i2;
            int i26 = 0;
            while (i25 <= i2) {
                int i27 = i5;
                int[] iArr9 = iArr6;
                int i28 = iArr2[i14 + Math.min(i4, Math.max(i25, 0))];
                int[] iArr10 = iArr8[i25 + i2];
                iArr10[0] = (i28 & 16711680) >> 16;
                iArr10[1] = (i28 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr10[2] = i28 & 255;
                int abs = i12 - Math.abs(i25);
                int i29 = iArr10[0];
                i26 += i29 * abs;
                int i30 = iArr10[1];
                i17 += i30 * abs;
                int i31 = iArr10[2];
                i18 += abs * i31;
                if (i25 > 0) {
                    i22 += i29;
                    i23 += i30;
                    i24 += i31;
                } else {
                    i19 += i29;
                    i20 += i30;
                    i21 += i31;
                }
                i25++;
                i5 = i27;
                iArr6 = iArr9;
            }
            int i32 = i5;
            int[] iArr11 = iArr6;
            int i33 = i26;
            int i34 = i2;
            int i35 = 0;
            while (i35 < width) {
                iArr3[i14] = iArr7[i33];
                iArr4[i14] = iArr7[i17];
                iArr5[i14] = iArr7[i18];
                int i36 = i33 - i19;
                int i37 = i17 - i20;
                int i38 = i18 - i21;
                int[] iArr12 = iArr8[((i34 - i2) + i6) % i6];
                int i39 = i19 - iArr12[0];
                int i40 = i20 - iArr12[1];
                int i41 = i21 - iArr12[2];
                if (i13 == 0) {
                    iArr = iArr7;
                    iArr11[i35] = Math.min(i35 + i2 + 1, i4);
                } else {
                    iArr = iArr7;
                }
                int i42 = iArr2[i15 + iArr11[i35]];
                int i43 = (i42 & 16711680) >> 16;
                iArr12[0] = i43;
                int i44 = (i42 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr12[1] = i44;
                int i45 = i42 & 255;
                iArr12[2] = i45;
                int i46 = i22 + i43;
                int i47 = i23 + i44;
                int i48 = i24 + i45;
                i33 = i36 + i46;
                i17 = i37 + i47;
                i18 = i38 + i48;
                i34 = (i34 + 1) % i6;
                int[] iArr13 = iArr8[i34 % i6];
                int i49 = iArr13[0];
                i19 = i39 + i49;
                int i50 = iArr13[1];
                i20 = i40 + i50;
                int i51 = iArr13[2];
                i21 = i41 + i51;
                i22 = i46 - i49;
                i23 = i47 - i50;
                i24 = i48 - i51;
                i14++;
                i35++;
                iArr7 = iArr;
            }
            i15 += width;
            i13++;
            copy = bitmap;
            height = i16;
            i5 = i32;
            iArr6 = iArr11;
        }
        Bitmap bitmap2 = copy;
        int i52 = i5;
        int[] iArr14 = iArr6;
        int i53 = height;
        int[] iArr15 = iArr7;
        int i54 = 0;
        while (i54 < width) {
            int i55 = -i2;
            int i56 = i6;
            int[] iArr16 = iArr2;
            int i57 = 0;
            int i58 = 0;
            int i59 = 0;
            int i60 = 0;
            int i61 = 0;
            int i62 = 0;
            int i63 = 0;
            int i64 = i55;
            int i65 = i55 * width;
            int i66 = 0;
            int i67 = 0;
            while (i64 <= i2) {
                int i68 = width;
                int max = Math.max(0, i65) + i54;
                int[] iArr17 = iArr8[i64 + i2];
                iArr17[0] = iArr3[max];
                iArr17[1] = iArr4[max];
                iArr17[2] = iArr5[max];
                int abs2 = i12 - Math.abs(i64);
                i66 += iArr3[max] * abs2;
                i67 += iArr4[max] * abs2;
                i57 += iArr5[max] * abs2;
                if (i64 > 0) {
                    i61 += iArr17[0];
                    i62 += iArr17[1];
                    i63 += iArr17[2];
                } else {
                    i58 += iArr17[0];
                    i59 += iArr17[1];
                    i60 += iArr17[2];
                }
                int i69 = i52;
                if (i64 < i69) {
                    i65 += i68;
                }
                i64++;
                i52 = i69;
                width = i68;
            }
            int i70 = width;
            int i71 = i52;
            int i72 = i2;
            int i73 = i54;
            int i74 = i53;
            int i75 = 0;
            while (i75 < i74) {
                iArr16[i73] = (iArr16[i73] & ViewCompat.MEASURED_STATE_MASK) | (iArr15[i66] << 16) | (iArr15[i67] << 8) | iArr15[i57];
                int i76 = i66 - i58;
                int i77 = i67 - i59;
                int i78 = i57 - i60;
                int[] iArr18 = iArr8[((i72 - i2) + i56) % i56];
                int i79 = i58 - iArr18[0];
                int i80 = i59 - iArr18[1];
                int i81 = i60 - iArr18[2];
                if (i54 == 0) {
                    iArr14[i75] = Math.min(i75 + i12, i71) * i70;
                }
                int i82 = iArr14[i75] + i54;
                int i83 = iArr3[i82];
                iArr18[0] = i83;
                int i84 = iArr4[i82];
                iArr18[1] = i84;
                int i85 = iArr5[i82];
                iArr18[2] = i85;
                int i86 = i61 + i83;
                int i87 = i62 + i84;
                int i88 = i63 + i85;
                i66 = i76 + i86;
                i67 = i77 + i87;
                i57 = i78 + i88;
                i72 = (i72 + 1) % i56;
                int[] iArr19 = iArr8[i72];
                int i89 = iArr19[0];
                i58 = i79 + i89;
                int i90 = iArr19[1];
                i59 = i80 + i90;
                int i91 = iArr19[2];
                i60 = i81 + i91;
                i61 = i86 - i89;
                i62 = i87 - i90;
                i63 = i88 - i91;
                i73 += i70;
                i75++;
                i2 = i;
            }
            i54++;
            i2 = i;
            i52 = i71;
            i53 = i74;
            i6 = i56;
            iArr2 = iArr16;
            width = i70;
        }
        int i92 = width;
        bitmap2.setPixels(iArr2, 0, i92, 0, 0, i92, i53);
        return bitmap2;
    }

    public final float calculateScaleRatio(int i, int i2, int i3, int i4) {
        return Math.max(i / i3, i2 / i4);
    }

    public final int[] calculateThumbnailSize(int i, int i2, int i3, int i4) {
        int[] iArr = new int[2];
        float f = i;
        float f2 = f / i3;
        float f3 = i2;
        float max = Math.max(f2, f3 / i4);
        if (max == f2) {
            iArr[0] = i3;
            iArr[1] = (int) (f3 / max);
        } else {
            iArr[0] = (int) (f / max);
            iArr[1] = i4;
        }
        return iArr;
    }

    public final Bitmap cleanImage(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= width) {
                i2 = 0;
                break;
            } else if (!isTransparentColumn(bitmap, i2)) {
                break;
            } else {
                i2++;
            }
        }
        int i3 = width - 1;
        int i4 = i2 + 1;
        if (i4 <= i3) {
            while (true) {
                if (!isTransparentColumn(bitmap, i3)) {
                    width = i3;
                    break;
                } else if (i3 == i4) {
                    break;
                } else {
                    i3--;
                }
            }
        }
        int i5 = 0;
        while (true) {
            if (i5 >= height) {
                break;
            } else if (!isTransparentRow(bitmap, i5)) {
                i = i5;
                break;
            } else {
                i5++;
            }
        }
        int i6 = height - 1;
        int i7 = i + 1;
        if (i7 <= i6) {
            while (true) {
                if (!isTransparentRow(bitmap, i6)) {
                    height = i6;
                    break;
                } else if (i6 == i7) {
                    break;
                } else {
                    i6--;
                }
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, i2, i, (width - i2) + 1, (height - i) + 1);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(bitmap, lef…ft + 1, bottom - top + 1)");
        return createBitmap;
    }

    private final boolean isTransparentRow(Bitmap bitmap, int i) {
        int width = bitmap.getWidth();
        for (int i2 = 0; i2 < width; i2++) {
            if (bitmap.getPixel(i2, i) != 0) {
                return false;
            }
        }
        return true;
    }

    private final boolean isTransparentColumn(Bitmap bitmap, int i) {
        int height = bitmap.getHeight();
        for (int i2 = 0; i2 < height; i2++) {
            if (bitmap.getPixel(i, i2) != 0) {
                return false;
            }
        }
        return true;
    }

    public final Bitmap transparentPadding(Bitmap image, float f) throws OutOfMemoryError {
        int i;
        Intrinsics.checkNotNullParameter(image, "image");
        try {
            int width = image.getWidth();
            int height = image.getHeight();
            int i2 = (int) (width / f);
            int i3 = (i2 - height) / 2;
            if (i3 < 0) {
                int i4 = (int) (height * f);
                int max = Math.max((i4 - width) / 2, 0);
                i3 = 0;
                width = i4;
                i = max;
            } else {
                height = i2;
                i = 0;
            }
            Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);
            canvas.drawARGB(0, 0, 0, 0);
            canvas.drawBitmap(image, i, i3, new Paint());
            Intrinsics.checkNotNullExpressionValue(result, "result");
            return result;
        } catch (OutOfMemoryError e) {
            throw e;
        }
    }

    public final Bitmap createBitmapMask(ArrayList<PointF> pointList, int i, int i2) {
        Intrinsics.checkNotNullParameter(pointList, "pointList");
        Bitmap bitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawARGB(0, 0, 0, 0);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        Path path = new Path();
        int size = pointList.size();
        for (int i3 = 0; i3 < size; i3++) {
            if (i3 == 0) {
                path.moveTo(pointList.get(i3).x, pointList.get(i3).y);
            } else {
                path.lineTo(pointList.get(i3).x, pointList.get(i3).y);
            }
        }
        canvas.drawPath(path, paint);
        canvas.clipPath(path);
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
        return bitmap;
    }

    public final Bitmap cropImage(Bitmap mainImage, Bitmap mask, Matrix m) {
        Intrinsics.checkNotNullParameter(mainImage, "mainImage");
        Intrinsics.checkNotNullParameter(mask, "mask");
        Intrinsics.checkNotNullParameter(m, "m");
        Canvas canvas = new Canvas();
        Bitmap result = Bitmap.createBitmap(mainImage.getWidth(), mainImage.getHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(result);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        paint.setAntiAlias(true);
        canvas.drawBitmap(mainImage, m, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mask, 0.0f, 0.0f, paint);
        paint.setXfermode(null);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    public final Bitmap cropImage(Bitmap mainImage, Bitmap mask) {
        Intrinsics.checkNotNullParameter(mainImage, "mainImage");
        Intrinsics.checkNotNullParameter(mask, "mask");
        Canvas canvas = new Canvas();
        Bitmap result = Bitmap.createBitmap(mainImage.getWidth(), mainImage.getHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(result);
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        paint.setAntiAlias(true);
        canvas.drawBitmap(mainImage, 0.0f, 0.0f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mask, 0.0f, 0.0f, paint);
        paint.setXfermode(null);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    public final Bitmap getCircularBitmap(Bitmap bitmap) {
        Bitmap createBitmap;
        int width;
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        if (bitmap.getWidth() > bitmap.getHeight()) {
            createBitmap = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(bitmap.heig…height, Config.ARGB_8888)");
        } else {
            createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(bitmap.widt….width, Config.ARGB_8888)");
        }
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        if (bitmap.getWidth() > bitmap.getHeight()) {
            width = bitmap.getHeight() / 2;
        } else {
            width = bitmap.getWidth() / 2;
        }
        float f = width;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        canvas.drawCircle(f, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }
}
