package chin.pswm.gps.photo.location.map.photoGrid.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.bumptech.glide.Glide;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;


@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001FB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fJ&\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004J\u0016\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004J\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u000fJ&\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#J&\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u000fJ\u000e\u0010(\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u001aJ\u0016\u0010)\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010*\u001a\u00020\u0006J\u000e\u0010+\u001a\u00020,2\u0006\u0010\u0016\u001a\u00020\u0017J\u0010\u0010-\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020\u0006H\u0002J\u001a\u0010.\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010/\u001a\u0004\u0018\u000100H\u0002J\u000e\u00101\u001a\u00020\n2\u0006\u0010%\u001a\u00020\u001aJ\u0010\u00102\u001a\u00020\u001a2\u0006\u00103\u001a\u000204H\u0007J \u00105\u001a\u0002062\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\u0006J\u0016\u0010:\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010;\u001a\u00020\u0004J\u0010\u0010<\u001a\u0002062\b\u0010=\u001a\u0004\u0018\u000108J\u0010\u0010>\u001a\u0002062\b\u0010=\u001a\u0004\u0018\u000104J\u0016\u0010?\u001a\u0002062\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010@\u001a\u00020\u001aJ\u0016\u0010A\u001a\u0002062\u0006\u0010%\u001a\u00020\u001a2\u0006\u0010B\u001a\u00020\u0006J\u0018\u0010C\u001a\u0002062\u0006\u0010D\u001a\u0002042\u0006\u0010E\u001a\u00020\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006G"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/util/PG_ImageUtils;", "", "()V", "MIN_OUTPUT_IMAGE_SIZE", "", "OUTPUT_COLLAGE_FOLDER", "", "getOUTPUT_COLLAGE_FOLDER", "()Ljava/lang/String;", "usedMemorySize", "", "getUsedMemorySize", "()J", "calculateOutputScaleFactor", "viewWidth", "", "viewHeight", "createMatrixToDrawImageInCenterView", "Landroid/graphics/Matrix;", "imageWidth", "imageHeight", "dpFromPx", "context", "Landroid/content/Context;", "px", "fastblur", "Landroid/graphics/Bitmap;", "sentBitmap", "radius", "focus", "src", "filtratedBitmap", "rect", "Landroid/graphics/Rect;", "isCircle", "", "getCircularArea", "bitmap", "circleX", "circleY", "getCircularBitmap", "getImageOrientation", "imagePath", "getMemoryInfo", "Lchin/pswm/gps/photo/location/map/photoGrid/util/PG_ImageUtils$MemoryInfo;", "getOrientationFromExif", "getOrientationFromMediaStore", "imageUri", "Landroid/net/Uri;", "getSizeInBytes", "loadBitmapFromView", "v", "Landroid/view/View;", "loadImageWithPicasso", "", "imageView", "Landroid/widget/ImageView;", "uri", "pxFromDp", "dp", "recycleImageView", "iv", "recycleView", "saveAndShare", "image", "saveBitmap", "path", "takeScreen", "view", "outputImagePath", "MemoryInfo", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_ImageUtils {
    public static final PG_ImageUtils INSTANCE = new PG_ImageUtils();
    private static final String OUTPUT_COLLAGE_FOLDER = Environment.getExternalStorageDirectory().toString() + "/PhotoCollage";
    private static final float MIN_OUTPUT_IMAGE_SIZE = 640.0f;

    private PG_ImageUtils() {
    }

    public final String getOUTPUT_COLLAGE_FOLDER() {
        return OUTPUT_COLLAGE_FOLDER;
    }

    public final long getUsedMemorySize() {
        try {
            Runtime runtime = Runtime.getRuntime();
            return runtime.totalMemory() - runtime.freeMemory();
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }


    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\f"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/util/PG_ImageUtils$MemoryInfo;", "", "()V", "availMem", "", "getAvailMem", "()J", "setAvailMem", "(J)V", "totalMem", "getTotalMem", "setTotalMem", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public static final class MemoryInfo {
        private long availMem;
        private long totalMem;

        public final long getAvailMem() {
            return this.availMem;
        }

        public final void setAvailMem(long j) {
            this.availMem = j;
        }

        public final long getTotalMem() {
            return this.totalMem;
        }

        public final void setTotalMem(long j) {
            this.totalMem = j;
        }
    }

    public final void loadImageWithPicasso(Context context, ImageView imageView, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        if (str == null || str.length() <= 1) {
            return;
        }
        if (StringsKt.startsWith(str, "http://", false) || StringsKt.startsWith(str, "https://", false)) {
            Glide.with(context).load(str).into(imageView);
        } else if (StringsKt.startsWith(str, "drawable://", false)) {
            try {
                String substring = str.substring(11);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                Glide.with(context).load(Integer.valueOf(Integer.parseInt(substring))).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (StringsKt.startsWith(str, "assets://", false)) {
            String substring2 = str.substring(9);
            Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
            Glide.with(context).load(Uri.parse("file:///android_asset/" + substring2)).into(imageView);
        } else {
            Glide.with(context).load(new File(str)).into(imageView);
        }
    }

    public final MemoryInfo getMemoryInfo(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        MemoryInfo memoryInfo = new MemoryInfo();
        Object systemService = context.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        ActivityManager.MemoryInfo memoryInfo2 = new ActivityManager.MemoryInfo();
        ((ActivityManager) systemService).getMemoryInfo(memoryInfo2);
        memoryInfo.setAvailMem(memoryInfo2.availMem);
        memoryInfo.setTotalMem(memoryInfo2.totalMem);
        return memoryInfo;
    }

    public final float calculateOutputScaleFactor(int i, int i2) {
        float min = Math.min(i, i2) / MIN_OUTPUT_IMAGE_SIZE;
        if (min >= 1.0f || min <= 0.0f) {
            return 1.0f;
        }
        return 1.0f / min;
    }

    public final void saveAndShare(Context context, Bitmap image) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(image, "image");
        try {
            StringBuilder sb = new StringBuilder();
            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            Intrinsics.checkNotNullExpressionValue(format, "SimpleDateFormat(\"yyyy-M…HH:mm:ss\").format(Date())");
            String sb2 = sb.append(new Regex(":").replace(format, "-")).append(".png").toString();
            File file = new File(OUTPUT_COLLAGE_FOLDER);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, sb2);
            image.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(file2));
            PG_PhotoUtils pG_PhotoUtils = PG_PhotoUtils.INSTANCE;
            String absolutePath = file2.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "photoFile.absolutePath");
            pG_PhotoUtils.addImageToGallery(absolutePath, context);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("image/png");
            intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(file2.getAbsolutePath())));
            context.startActivity(Intent.createChooser(intent, "Share Image"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final float dpFromPx(Context context, float f) {
        Intrinsics.checkNotNullParameter(context, "context");
        return f / context.getResources().getDisplayMetrics().density;
    }

    public final float pxFromDp(Context context, float f) {
        Intrinsics.checkNotNullParameter(context, "context");
        return f * context.getResources().getDisplayMetrics().density;
    }

    public final Matrix createMatrixToDrawImageInCenterView(float f, float f2, float f3, float f4) {
        float max = Math.max(f / f3, f2 / f4);
        Matrix matrix = new Matrix();
        matrix.postTranslate((f - f3) / 2.0f, (f2 - f4) / 2.0f);
        float f5 = 2;
        matrix.postScale(max, max, f / f5, f2 / f5);
        return matrix;
    }

    public final void recycleView(View view) {
        Bitmap bitmap;
        if (view == null) {
            return;
        }
        Drawable background = view.getBackground();
        view.setBackgroundColor(0);
        if (background == null || !(background instanceof BitmapDrawable) || (bitmap = ((BitmapDrawable) background).getBitmap()) == null || bitmap.isRecycled()) {
            return;
        }
        bitmap.recycle();
    }

    public final void recycleImageView(ImageView imageView) {
        Bitmap bitmap;
        Bitmap bitmap2;
        if (imageView == null) {
            return;
        }
        Drawable background = imageView.getBackground();
        Drawable drawable = imageView.getDrawable();
        imageView.setBackgroundColor(0);
        imageView.setImageBitmap(null);
        if (background != null && (background instanceof BitmapDrawable) && (bitmap2 = ((BitmapDrawable) background).getBitmap()) != null && !bitmap2.isRecycled()) {
            bitmap2.recycle();
        }
        if (drawable == null || !(drawable instanceof BitmapDrawable) || (bitmap = ((BitmapDrawable) drawable).getBitmap()) == null || bitmap.isRecycled()) {
            return;
        }
        bitmap.recycle();
    }

    public final long getSizeInBytes(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    @Deprecated(message = "")
    public final Bitmap loadBitmapFromView(View v) throws OutOfMemoryError {
        Intrinsics.checkNotNullParameter(v, "v");
        try {
            int measuredWidth = v.getMeasuredWidth();
            int measuredHeight = v.getMeasuredHeight();
            Drawable background = v.getBackground();
            v.setBackgroundColor(0);
            v.layout(0, 0, measuredWidth, measuredHeight);
            Bitmap returnedBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(returnedBitmap);
            if (background != null) {
                background.draw(canvas);
            }
            v.draw(canvas);
            v.setBackground(background);
            Intrinsics.checkNotNullExpressionValue(returnedBitmap, "returnedBitmap");
            return returnedBitmap;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Deprecated(message = "")
    public final void takeScreen(View view, String outputImagePath) throws OutOfMemoryError {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(outputImagePath, "outputImagePath");
        try {
            Bitmap loadBitmapFromView = loadBitmapFromView(view);
            File file = new File(outputImagePath);
            File parentFile = file.getParentFile();
            Intrinsics.checkNotNull(parentFile);
            parentFile.mkdirs();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            loadBitmapFromView.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (OutOfMemoryError e3) {
            e3.printStackTrace();
            throw e3;
        }
    }

    public final int getImageOrientation(Context context, String imagePath) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imagePath, "imagePath");
        int orientationFromExif = getOrientationFromExif(imagePath);
        return orientationFromExif < 0 ? getOrientationFromMediaStore(context, Uri.fromFile(new File(imagePath))) : orientationFromExif;
    }

    private final int getOrientationFromExif(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
            if (attributeInt != 1) {
                if (attributeInt != 3) {
                    if (attributeInt != 6) {
                        return attributeInt != 8 ? -1 : 270;
                    }
                    return 90;
                }
                return 180;
            }
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private final int getOrientationFromMediaStore(Context context, Uri uri) {
        if (uri == null) {
            return -1;
        }
        Cursor query = context.getContentResolver().query(uri, new String[]{"orientation"}, null, null, null);
        if (query == null || !query.moveToFirst()) {
            return -1;
        }
        int i = query.getInt(0);
        query.close();
        return i;
    }

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

    public final Bitmap getCircularArea(Bitmap bitmap, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        int i4 = i3 * 2;
        Bitmap output = Bitmap.createBitmap(i4, i4, Bitmap.Config.ARGB_8888);
        Rect rect = new Rect();
        rect.top = i2 - i3;
        rect.bottom = rect.top + i4;
        rect.left = i - i3;
        rect.right = rect.left + i4;
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawCircle(output.getWidth() / 2, output.getHeight() / 2, i3, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, new Rect(0, 0, output.getWidth(), output.getHeight()), paint);
        Intrinsics.checkNotNullExpressionValue(output, "output");
        return output;
    }

    public final Bitmap focus(Bitmap src, Bitmap filtratedBitmap, Rect rect, boolean z) {
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.checkNotNullParameter(filtratedBitmap, "filtratedBitmap");
        Intrinsics.checkNotNullParameter(rect, "rect");
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        Bitmap createBitmap = Bitmap.createBitmap(rect.right - rect.left, rect.bottom - rect.top, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNull(createBitmap);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(src, rect, new Rect(0, 0, createBitmap.getWidth(), createBitmap.getHeight()), paint);
        if (z) {
            Bitmap circularBitmap = getCircularBitmap(createBitmap);
            createBitmap.recycle();
            createBitmap = circularBitmap;
        }
        Bitmap result = Bitmap.createBitmap(filtratedBitmap.getWidth(), filtratedBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(result);
        canvas.drawBitmap(createBitmap, new Rect(0, 0, createBitmap.getWidth(), createBitmap.getHeight()), rect, paint);
        createBitmap.recycle();
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    public final Bitmap getCircularBitmap(Bitmap bitmap) {
        int width;
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        if (bitmap.getWidth() > bitmap.getHeight()) {
            width = bitmap.getHeight() / 2;
        } else {
            width = bitmap.getWidth() / 2;
        }
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, width, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        Intrinsics.checkNotNullExpressionValue(output, "output");
        return output;
    }
}
