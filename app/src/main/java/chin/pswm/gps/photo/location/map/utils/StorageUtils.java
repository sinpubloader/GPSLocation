package chin.pswm.gps.photo.location.map.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class StorageUtils {
    public static String TAG = "STORAGE_TAG";

    public static String create_folder(String str) {
        if (Build.VERSION.SDK_INT <= 29) {
            File file = new File(Environment.getExternalStorageDirectory() + "/" + str);
            Log.d(TAG, "createDir:" + file);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        }
        File file2 = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + File.separator + "/" + str)));
        Log.d(TAG, "createDir:" + file2);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        return file2.getAbsolutePath();
    }

    public static String create_folder_with_sub_folder(String str, String str2) {
        if (Build.VERSION.SDK_INT <= 29) {
            File file = new File(Environment.getExternalStorageDirectory() + "/" + str + "/" + str2);
            Log.d(TAG, "createDir:" + file);
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getAbsolutePath();
        }
        File file2 = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + File.separator + str + File.separator + str2)));
        Log.d(TAG, "createDir:" + file2);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        return file2.getAbsolutePath();
    }

    public static void scanFile(Context context, String str) {
        try {
            MediaScannerConnection.scanFile(context, new String[]{str}, null, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public void onScanCompleted(String str2, Uri uri) {
                    Log.i("TAG", "Finished scanning " + str2);
                }
            });
        } catch (Exception unused) {
        }
    }

    public static String saveImage(Context context, Bitmap bitmap, String str, String str2) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, str2);
            if (file2.exists()) {
                file2.delete();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            String absolutePath = file2.getAbsolutePath();
            MediaScannerConnection.scanFile(context, new String[]{file2.getPath()}, null, null);
            return absolutePath;
        } catch (Exception e) {
            Log.d(TAG, "saveImage: " + e);
            return null;
        }
    }

    public static String saveImageWithLocation(Context context, Bitmap bitmap, String str, String str2, double d, double d2) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, str2);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            String absolutePath = file2.getAbsolutePath();
            MediaScannerConnection.scanFile(context, new String[]{file2.getPath()}, null, null);
            H(file2, d, d2);
            return absolutePath;
        } catch (Exception e) {
            Log.d(TAG, "saveImage: " + e);
            return null;
        }
    }

    public static void H(File file, double d, double d2) {
        String str;
        String str2;
        try {
            ExifInterface exifInterface = new ExifInterface(file.getCanonicalPath());
            String[] split = Location.convert(Math.abs(d), 2).split(":");
            String[] split2 = split[2].split("\\.");
            if (split2.length == 0) {
                str = split[2];
            } else {
                str = split2[0];
            }
            exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_LATITUDE, split[0] + "/1," + split[1] + "/1," + str + "/1");
            exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_LATITUDE_REF, d > 0.0d ? "N" : androidx.exifinterface.media.ExifInterface.LATITUDE_SOUTH);
            String[] split3 = Location.convert(Math.abs(d2), 2).split(":");
            String[] split4 = split3[2].split("\\.");
            if (split4.length == 0) {
                str2 = split3[2];
            } else {
                str2 = split4[0];
            }
            exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_LONGITUDE, split3[0] + "/1," + split3[1] + "/1," + str2 + "/1");
            exifInterface.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_GPS_LONGITUDE_REF, d2 > 0.0d ? androidx.exifinterface.media.ExifInterface.LONGITUDE_EAST : androidx.exifinterface.media.ExifInterface.LONGITUDE_WEST);
            exifInterface.saveAttributes();
        } catch (Exception e) {
            Log.d(TAG, "H:Exception  " + e.getMessage());
        }
    }

    public static List<Uri> getFolderData(String str) {
        File[] listFiles;
        ArrayList arrayList = new ArrayList();
        if (new File(str).exists() && (listFiles = new File(str).listFiles()) != null && listFiles.length != 0) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].getAbsolutePath().contains(".jpg")) {
                    arrayList.add(Uri.parse(listFiles[i].getAbsolutePath()));
                }
            }
        }
        return arrayList;
    }

    public static List<Uri> getFolderDataVideo(String str) {
        File[] listFiles;
        ArrayList arrayList = new ArrayList();
        if (new File(str).exists() && (listFiles = new File(str).listFiles()) != null && listFiles.length != 0) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].getAbsolutePath().contains(".mp4")) {
                    arrayList.add(Uri.parse(listFiles[i].getAbsolutePath()));
                }
            }
        }
        return arrayList;
    }

    public static Bitmap getBitmapFromFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            return BitmapFactory.decodeFile(file.getAbsolutePath());
        }
        return null;
    }

    public static void share_app(Context context) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", "https://play.google.com/store/apps/details?id=" + context.getPackageName());
        context.startActivity(Intent.createChooser(intent, "Share via"));
    }

    public static void rate_app(Context context) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + context.getPackageName())));
        } catch (ActivityNotFoundException unused) {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    public static String fileDate(long j, String str) {
        return new SimpleDateFormat(str).format(Long.valueOf(j));
    }

    public static String getCurrentDate(String str) {
        return new SimpleDateFormat(str, Locale.ENGLISH).format(Calendar.getInstance().getTime()).replace(" ", "");
    }
}
