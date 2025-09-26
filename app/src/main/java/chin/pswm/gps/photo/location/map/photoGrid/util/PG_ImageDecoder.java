package chin.pswm.gps.photo.location.map.photoGrid.util;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.Toast;
import chin.pswm.gps.photo.location.map_debug.R;
import com.fom.rapid.app.Media;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004J\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J(\u0010\u0014\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J(\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018J\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u001d\u001a\u00020\u0013J\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0004J(\u0010 \u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004J(\u0010!\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004J\u0010\u0010\"\u001a\u0004\u0018\u00010\u000f2\u0006\u0010#\u001a\u00020$J \u0010\"\u001a\u0004\u0018\u00010\u000f2\u0006\u0010#\u001a\u00020$2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004J(\u0010%\u001a\u0004\u0018\u00010\u001a2\u0006\u0010#\u001a\u00020$2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018J\u0018\u0010&\u001a\u0004\u0018\u00010\u001a2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u0017\u001a\u00020\u0018J\u001a\u0010'\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010(\u001a\u0004\u0018\u00010)J\u0018\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010(\u001a\u00020)J\u0018\u0010,\u001a\u0004\u0018\u00010\u00132\u0006\u0010-\u001a\u00020\u00112\u0006\u0010.\u001a\u00020)R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006/"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/util/PG_ImageDecoder;", "", "()V", "SAMPLER_SIZE", "", "getSAMPLER_SIZE", "()I", "setSAMPLER_SIZE", "(I)V", "calculateInSampleSize", "options", "Landroid/graphics/BitmapFactory$Options;", "reqWidth", "reqHeight", "decodeAsset", "Landroid/graphics/Bitmap;", "context", "Landroid/content/Context;", "filePath", "", "decodeBlobToBitmap",  "", "res", "Landroid/content/res/Resources;", "decodeBlobToDrawable", "Landroid/graphics/drawable/Drawable;", "decodeBlobToDrawble", "decodeFileToBitmap", "pathName", "decodeResource", "resId", "decodeSampledBitmapFromResource", "decodeSampledDrawableFromResource", "decodeStreamToBitmap", "is", "Ljava/io/InputStream;", "decodeStreamToDrawable", "decodeStreamToDrawble", "decodeUriToBitmap", "uri", "Landroid/net/Uri;", "decodeUriToDrawable", "Landroid/graphics/drawable/BitmapDrawable;", "getRealPathFromURI", "activity", "contentURI", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_ImageDecoder {
    public static final PG_ImageDecoder INSTANCE = new PG_ImageDecoder();
    private static int SAMPLER_SIZE = 256;

    private PG_ImageDecoder() {
    }

    public final int getSAMPLER_SIZE() {
        return SAMPLER_SIZE;
    }

    public final void setSAMPLER_SIZE(int i) {
        SAMPLER_SIZE = i;
    }

    public final Bitmap decodeAsset(Context context, String filePath) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        try {
            InputStream open = context.getAssets().open(filePath);
            Intrinsics.checkNotNullExpressionValue(open, "am.open(filePath)");
            return decodeStreamToBitmap(open);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public final Bitmap decodeResource(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Resources resources = context.getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "context.resources");
        int i2 = SAMPLER_SIZE;
        return decodeSampledBitmapFromResource(resources, i, i2, i2);
    }

    public final Bitmap decodeUriToBitmap(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (uri == null) {
            return null;
        }
        try {
            String path = PG_FileUtils.INSTANCE.getPath(context, uri);
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            int i2 = options.outWidth;
            int i3 = options.outHeight;
            int i4 = SAMPLER_SIZE;
            while (i2 / 2 > i4 && i3 / 2 > i4) {
                i2 /= 2;
                i3 /= 2;
                i *= 2;
            }
            options.inSampleSize = i;
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(path, options);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e2) {
            throw e2;
        }
    }

    public final BitmapDrawable decodeUriToDrawable(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        try {
            String path = PG_FileUtils.INSTANCE.getPath(context, uri);
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            int i2 = options.outWidth;
            int i3 = options.outHeight;
            int i4 = SAMPLER_SIZE;
            while (i2 / 2 > i4 && i3 / 2 > i4) {
                i2 /= 2;
                i3 /= 2;
                i *= 2;
            }
            options.inSampleSize = i;
            options.inJustDecodeBounds = false;
            return new BitmapDrawable(context.getResources(), BitmapFactory.decodeFile(path, options));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            Toast.makeText(context, context.getString(R.string.waring_out_of_memory), 0).show();
            return null;
        }
    }

    public final Bitmap decodeBlobToBitmap(byte[] bArr) throws OutOfMemoryError {
        if (bArr != null) {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                int i = 1;
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
                int i2 = options.outWidth;
                int i3 = options.outHeight;
                int i4 = SAMPLER_SIZE;
                while (i2 / 2 > i4 && i3 / 2 > i4) {
                    i2 /= 2;
                    i3 /= 2;
                    i *= 2;
                }
                options.inSampleSize = i;
                options.inJustDecodeBounds = false;
                return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } catch (OutOfMemoryError e2) {
                e2.printStackTrace();
                throw e2;
            }
        }
        return null;
    }

    public final Drawable decodeBlobToDrawable(byte[] data, int i, int i2, Resources res) throws OutOfMemoryError {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(res, "res");
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(data, 0, data.length, options);
            options.inSampleSize = calculateInSampleSize(options, i, i2);
            options.inJustDecodeBounds = false;
            return new BitmapDrawable(res, BitmapFactory.decodeByteArray(data, 0, data.length, options));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            throw e2;
        }
    }

    public final Bitmap decodeBlobToBitmap(byte[] data, int i, int i2, Resources res) throws OutOfMemoryError {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(res, "res");
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(data, 0, data.length, options);
            options.inSampleSize = calculateInSampleSize(options, i, i2);
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeByteArray(data, 0, data.length, options);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            throw e2;
        }
    }

    public final Drawable decodeBlobToDrawble(byte[] data, Resources res) throws OutOfMemoryError {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(res, "res");
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(data, 0, data.length, options);
            int i2 = options.outWidth;
            int i3 = options.outHeight;
            int i4 = SAMPLER_SIZE;
            while (i2 / 2 > i4 && i3 / 2 > i4) {
                i2 /= 2;
                i3 /= 2;
                i *= 2;
            }
            options.inJustDecodeBounds = false;
            options.inSampleSize = i;
            return new BitmapDrawable(res, BitmapFactory.decodeByteArray(data, 0, data.length, options));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            throw e2;
        }
    }

    public final Drawable decodeStreamToDrawble(InputStream is, Resources res) throws OutOfMemoryError {
        Intrinsics.checkNotNullParameter(is, "is");
        Intrinsics.checkNotNullParameter(res, "res");
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i = 1;
            options.inJustDecodeBounds = true;
            Rect rect = new Rect();
            BitmapFactory.decodeStream(is, rect, options);
            int i2 = options.outWidth;
            int i3 = options.outHeight;
            int i4 = SAMPLER_SIZE;
            while (i2 / 2 > i4 && i3 / 2 > i4) {
                i2 /= 2;
                i3 /= 2;
                i *= 2;
            }
            options.inJustDecodeBounds = false;
            options.inSampleSize = i;
            return new BitmapDrawable(res, BitmapFactory.decodeStream(is, rect, options));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            throw e2;
        }
    }

    public final Bitmap decodeFileToBitmap(String pathName) throws OutOfMemoryError {
        Intrinsics.checkNotNullParameter(pathName, "pathName");
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(pathName, options);
            int i2 = options.outWidth;
            int i3 = options.outHeight;
            int i4 = SAMPLER_SIZE;
            while (i2 / 2 > i4 && i3 / 2 > i4) {
                i2 /= 2;
                i3 /= 2;
                i *= 2;
            }
            options.inJustDecodeBounds = false;
            options.inSampleSize = i;
            return BitmapFactory.decodeFile(pathName, options);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            throw e2;
        }
    }

    public final Bitmap decodeStreamToBitmap(InputStream is) throws OutOfMemoryError {
        Intrinsics.checkNotNullParameter(is, "is");
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i = 1;
            options.inJustDecodeBounds = true;
            Rect rect = new Rect();
            BitmapFactory.decodeStream(is, rect, options);
            int i2 = options.outWidth;
            int i3 = options.outHeight;
            int i4 = SAMPLER_SIZE;
            while (i2 / 2 > i4 && i3 / 2 > i4) {
                i2 /= 2;
                i3 /= 2;
                i *= 2;
            }
            options.inJustDecodeBounds = false;
            options.inSampleSize = i;
            return BitmapFactory.decodeStream(is, rect, options);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            throw e2;
        }
    }

    public final Bitmap decodeStreamToBitmap(InputStream is, int i, int i2) throws OutOfMemoryError {
        Intrinsics.checkNotNullParameter(is, "is");
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            Rect rect = new Rect();
            BitmapFactory.decodeStream(is, rect, options);
            options.inSampleSize = calculateInSampleSize(options, i, i2);
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeStream(is, rect, options);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            throw e2;
        }
    }

    public final Drawable decodeStreamToDrawable(InputStream is, int i, int i2, Resources res) throws OutOfMemoryError {
        Intrinsics.checkNotNullParameter(is, "is");
        Intrinsics.checkNotNullParameter(res, "res");
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            Rect rect = new Rect();
            BitmapFactory.decodeStream(is, rect, options);
            options.inSampleSize = calculateInSampleSize(options, i, i2);
            options.inJustDecodeBounds = false;
            return new BitmapDrawable(res, BitmapFactory.decodeStream(is, rect, options));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            throw e2;
        }
    }

    public final Bitmap decodeSampledBitmapFromResource(Resources res, int i, int i2, int i3) throws OutOfMemoryError {
        Intrinsics.checkNotNullParameter(res, "res");
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(res, i, options);
            options.inSampleSize = calculateInSampleSize(options, i2, i3);
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeResource(res, i, options);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            throw e2;
        }
    }

    public final Drawable decodeSampledDrawableFromResource(Resources res, int i, int i2, int i3) throws OutOfMemoryError {
        Intrinsics.checkNotNullParameter(res, "res");
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(res, i, options);
            options.inSampleSize = calculateInSampleSize(options, i2, i3);
            options.inJustDecodeBounds = false;
            return new BitmapDrawable(res, BitmapFactory.decodeResource(res, i, options));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            throw e2;
        }
    }

    public final int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        Intrinsics.checkNotNullParameter(options, "options");
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        if (i3 > i2 || i4 > i) {
            int round = Math.round(i3 / i2);
            int round2 = Math.round(i4 / i);
            return round < round2 ? round : round2;
        }
        return 1;
    }

    public final String getRealPathFromURI(Context activity, Uri contentURI) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(contentURI, "contentURI");
        Cursor query = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (query == null) {
            return contentURI.getPath();
        }
        query.moveToFirst();
        return query.getString(query.getColumnIndex("_data"));
    }
}
