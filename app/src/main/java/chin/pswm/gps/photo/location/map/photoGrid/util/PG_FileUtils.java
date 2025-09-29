package chin.pswm.gps.photo.location.map.photoGrid.util;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.google.android.gms.common.internal.ImagesContract;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.ListIterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

@SuppressWarnings("all")
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020)H\u0007J\u0006\u0010*\u001a\u00020+J\u0016\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u0019J\u0006\u0010/\u001a\u000200J\u000e\u00101\u001a\u00020+2\u0006\u00102\u001a\u00020\u0019J\u0016\u00103\u001a\u00020+2\u0006\u00104\u001a\u00020\u00062\u0006\u00105\u001a\u00020\u0006J\u0018\u00106\u001a\u00020+2\u0006\u00107\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019H\u0002J\u0012\u00109\u001a\u0004\u0018\u00010\u00062\b\u0010:\u001a\u0004\u0018\u00010;J \u00109\u001a\u0004\u0018\u00010\u00062\u0006\u0010<\u001a\u00020\u00062\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020>J9\u0010@\u001a\u0004\u0018\u00010\u00062\u0006\u0010A\u001a\u00020B2\b\u0010C\u001a\u0004\u0018\u00010D2\b\u0010E\u001a\u0004\u0018\u00010\u00062\u000e\u0010F\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010G¢\u0006\u0002\u0010HJ\u0012\u0010I\u001a\u0004\u0018\u00010\u00062\b\u0010C\u001a\u0004\u0018\u00010\u0006J\u001a\u0010J\u001a\u0004\u0018\u00010\u00192\u0006\u0010A\u001a\u00020B2\b\u0010C\u001a\u0004\u0018\u00010DJ\u0018\u0010K\u001a\u0004\u0018\u00010\u00062\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020DJ\u0010\u0010K\u001a\u0004\u0018\u00010\u00062\u0006\u00102\u001a\u00020\u0019J\u001a\u0010L\u001a\u0004\u0018\u00010\u00062\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020DH\u0007J\u0012\u0010M\u001a\u0004\u0018\u00010\u00192\b\u00102\u001a\u0004\u0018\u00010\u0019J\u000e\u0010N\u001a\u00020\u00062\u0006\u0010O\u001a\u00020PJ(\u0010Q\u001a\u0004\u0018\u00010R2\u0006\u0010A\u001a\u00020B2\b\u0010C\u001a\u0004\u0018\u00010D2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010\u0006H\u0007J\u0018\u0010Q\u001a\u0004\u0018\u00010R2\u0006\u0010A\u001a\u00020B2\u0006\u00102\u001a\u00020\u0019J\u0012\u0010T\u001a\u0004\u0018\u00010D2\b\u00102\u001a\u0004\u0018\u00010\u0019J\u000e\u0010U\u001a\u00020\u00042\u0006\u0010C\u001a\u00020DJ\u000e\u0010V\u001a\u00020\u00042\u0006\u0010C\u001a\u00020DJ\u000e\u0010W\u001a\u00020\u00042\u0006\u0010C\u001a\u00020DJ\u0010\u0010X\u001a\u00020\u00042\b\u0010Y\u001a\u0004\u0018\u00010\u0006J\u000e\u0010Z\u001a\u00020\u00042\u0006\u0010C\u001a\u00020DJ\u000e\u0010[\u001a\u00020\u00042\u0006\u0010C\u001a\u00020DJ\u0018\u0010\\\u001a\u0004\u0018\u00010\u00062\u0006\u0010]\u001a\u00020R2\u0006\u00105\u001a\u00020\u0006J\u0016\u0010^\u001a\u00020\u00042\u0006\u0010_\u001a\u00020;2\u0006\u0010.\u001a\u00020\u0019J\u000e\u0010`\u001a\u00020\u00062\u0006\u0010a\u001a\u00020\u0006J\u0016\u0010b\u001a\u00020+2\u0006\u0010c\u001a\u00020\u00062\u0006\u0010d\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0014\u0010\u000b\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0014\u0010\r\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0014\u0010\u000f\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u0014\u0010\u0011\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\bR\u0014\u0010\u0013\u001a\u00020\u0006X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0011\u0010\u0015\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\bR \u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010!\"\u0004\b&\u0010#¨\u0006e"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/util/PG_FileUtils;", "", "()V", "DEBUG", "", "HIDDEN_PREFIX", "", "getHIDDEN_PREFIX", "()Ljava/lang/String;", "MIME_TYPE_APP", "getMIME_TYPE_APP", "MIME_TYPE_AUDIO", "getMIME_TYPE_AUDIO", "MIME_TYPE_IMAGE", "getMIME_TYPE_IMAGE", "MIME_TYPE_TEXT", "getMIME_TYPE_TEXT", "MIME_TYPE_VIDEO", "getMIME_TYPE_VIDEO", "TAG", "getTAG$app_release", "TEMP_FOLDER", "getTEMP_FOLDER", "sComparator", "Ljava/util/Comparator;", "Ljava/io/File;", "getSComparator", "()Ljava/util/Comparator;", "setSComparator", "(Ljava/util/Comparator;)V", "sDirFilter", "Ljava/io/FileFilter;", "getSDirFilter", "()Ljava/io/FileFilter;", "setSDirFilter", "(Ljava/io/FileFilter;)V", "sFileFilter", "getSFileFilter", "setSFileFilter", "byteArrayToHexString", "b", "", "cleanTempFiles", "", "copyFile", "source", "dest", "createGetContentIntent", "Landroid/content/Intent;", "deleteFile", "file", "downloadFile", "urlText", "outPath", "extractFolder", "zipFile", "destination", "generateMD5", "inputStream", "Ljava/io/InputStream;", "sourcePath", "offset", "", "length", "getDataColumn", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "selection", "selectionArgs", "", "(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)Ljava/lang/String;", "getExtension", "getFile", "getMimeType", "getPath", "getPathWithoutFilename", "getReadableFileSize", "size", "", "getThumbnail", "Landroid/graphics/Bitmap;", "mimeType", "getUri", "isDownloadsDocument", "isExternalStorageDocument", "isGooglePhotosUri", "isLocal", ImagesContract.URL, "isMediaDocument", "isMediaUri", "saveBitmapToFile", "bitmap", "saveToFile", "is", "sha128s", "input", "unzip", "zipFilePath", "outFolder", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_FileUtils {
    private static final boolean DEBUG = false;
    public static final PG_FileUtils INSTANCE = new PG_FileUtils();
    private static final String TEMP_FOLDER = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/dauroi.photoeditor/Temp";
    private static final String TAG = "PG_FileUtils";
    private static final String MIME_TYPE_AUDIO = "audio/*";
    private static final String MIME_TYPE_TEXT = "text/*";
    private static final String MIME_TYPE_IMAGE = "image/*";
    private static final String MIME_TYPE_VIDEO = "video/*";
    private static final String MIME_TYPE_APP = "application/*";
    private static final String HIDDEN_PREFIX = ".";
    private static Comparator<File> sComparator = new Comparator() {
        @Override 
        public final int compare(Object obj, Object obj2) {
            int sComparator$lambda$0;
            sComparator$lambda$0 = PG_FileUtils.sComparator$lambda$0((File) obj, (File) obj2);
            return sComparator$lambda$0;
        }
    };
    private static FileFilter sFileFilter = new FileFilter() {
        @Override
        public final boolean accept(File file) {
            boolean sFileFilter$lambda$1;
            sFileFilter$lambda$1 = PG_FileUtils.sFileFilter$lambda$1(file);
            return sFileFilter$lambda$1;
        }
    };
    private static FileFilter sDirFilter = new FileFilter() {
        @Override 
        public final boolean accept(File file) {
            boolean sDirFilter$lambda$2;
            sDirFilter$lambda$2 = PG_FileUtils.sDirFilter$lambda$2(file);
            return sDirFilter$lambda$2;
        }
    };

    public final Bitmap getThumbnail(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getThumbnail$default(this, context, uri, null, 4, null);
    }

    private PG_FileUtils() {
    }

    public final String getTEMP_FOLDER() {
        return TEMP_FOLDER;
    }

    public final String getTAG$app_release() {
        return TAG;
    }

    public final String getMIME_TYPE_AUDIO() {
        return MIME_TYPE_AUDIO;
    }

    public final String getMIME_TYPE_TEXT() {
        return MIME_TYPE_TEXT;
    }

    public final String getMIME_TYPE_IMAGE() {
        return MIME_TYPE_IMAGE;
    }

    public final String getMIME_TYPE_VIDEO() {
        return MIME_TYPE_VIDEO;
    }

    public final String getMIME_TYPE_APP() {
        return MIME_TYPE_APP;
    }

    public final String getHIDDEN_PREFIX() {
        return HIDDEN_PREFIX;
    }

    public final Comparator<File> getSComparator() {
        return sComparator;
    }

    public final void setSComparator(Comparator<File> comparator) {
        Intrinsics.checkNotNullParameter(comparator, "<set-?>");
        sComparator = comparator;
    }

    public static final int sComparator$lambda$0(File file, File file2) {
        String name = file.getName();
        Intrinsics.checkNotNullExpressionValue(name, "f1.name");
        String lowerCase = name.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        String name2 = file2.getName();
        Intrinsics.checkNotNullExpressionValue(name2, "f2.name");
        String lowerCase2 = name2.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
        return lowerCase.compareTo(lowerCase2);
    }

    public final FileFilter getSFileFilter() {
        return sFileFilter;
    }

    public final void setSFileFilter(FileFilter fileFilter) {
        Intrinsics.checkNotNullParameter(fileFilter, "<set-?>");
        sFileFilter = fileFilter;
    }

    public static final boolean sFileFilter$lambda$1(File file) {
        String fileName = file.getName();
        if (file.isFile()) {
            Intrinsics.checkNotNullExpressionValue(fileName, "fileName");
            return !StringsKt.startsWith(fileName, HIDDEN_PREFIX, false);
        }
        return false;
    }

    public final FileFilter getSDirFilter() {
        return sDirFilter;
    }

    public final void setSDirFilter(FileFilter fileFilter) {
        Intrinsics.checkNotNullParameter(fileFilter, "<set-?>");
        sDirFilter = fileFilter;
    }

    public static final boolean sDirFilter$lambda$2(File file) {
        String fileName = file.getName();
        if (file.isDirectory()) {
            Intrinsics.checkNotNullExpressionValue(fileName, "fileName");
            return !StringsKt.startsWith(fileName, HIDDEN_PREFIX, false);
        }
        return false;
    }

    public final String getExtension(String arg7) {
        if(arg7 == null) {
            return null;
        }

        int v0 = StringsKt.lastIndexOf(((CharSequence)arg7), ".", 0, false);
        if(v0 >= 0) {
            String v7 = arg7.substring(v0);
            Intrinsics.checkNotNullExpressionValue(v7, "this as java.lang.String).substring(startIndex)");
            return v7;
        }

        return "";
    }
    public final boolean isLocal(String str) {
        return (str == null || StringsKt.startsWith(str, "http://", false) || StringsKt.startsWith(str, "https://", false)) ? false : true;
    }

    public final boolean isMediaUri(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        String authority = uri.getAuthority();
        Intrinsics.checkNotNull(authority);
        return StringsKt.equals("media", authority, true);
    }

    public final Uri getUri(File file) {
        if (file != null) {
            return Uri.fromFile(file);
        }
        return null;
    }

    public final File getPathWithoutFilename(File file) {
        if (file != null) {
            if (file.isDirectory()) {
                return file;
            }
            String name = file.getName();
            String filepath = file.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(filepath, "filepath");
            String substring = filepath.substring(0, filepath.length() - name.length());
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            if (StringsKt.endsWith(substring, "/", false)) {
                substring = substring.substring(0, substring.length() - 1);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            return new File(substring);
        }
        return null;
    }

    public final String getMimeType(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        String extension = getExtension(file.getName());
        Intrinsics.checkNotNull(extension);
        if (extension.length() > 0) {
            MimeTypeMap singleton = MimeTypeMap.getSingleton();
            String substring = extension.substring(1);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
            return singleton.getMimeTypeFromExtension(substring);
        }
        return "application/octet-stream";
    }

    public final String getMimeType(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        String path = getPath(context, uri);
        Intrinsics.checkNotNull(path);
        return getMimeType(new File(path));
    }

    public final boolean isExternalStorageDocument(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return Intrinsics.areEqual("com.android.externalstorage.documents", uri.getAuthority());
    }

    public final boolean isDownloadsDocument(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return Intrinsics.areEqual("com.android.providers.downloads.documents", uri.getAuthority());
    }

    public final boolean isMediaDocument(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return Intrinsics.areEqual("com.android.providers.media.documents", uri.getAuthority());
    }

    public final boolean isGooglePhotosUri(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return Intrinsics.areEqual("com.google.android.apps.photos.content", uri.getAuthority());
    }

    public final String getDataColumn(Context arg9, Uri arg10, String arg11, String[] arg12) {
        Intrinsics.checkNotNullParameter(arg9, "context");
        ContentResolver v1 = arg9.getContentResolver();
        Intrinsics.checkNotNull(arg10);
        Cursor v9 = v1.query(arg10, new String[]{"_data"}, arg11, arg12, null);
        if(v9 != null && (v9.moveToFirst())) {
            if(PG_FileUtils.DEBUG) {
                DatabaseUtils.dumpCursor(v9);
            }

            String v10 = v9.getString(v9.getColumnIndexOrThrow("_data"));
            v9.close();
            return v10;
        }

        if(v9 != null) {
            v9.close();
        }

        return null;
    }

    public final String getPath(Context arg7, Uri arg8) {
        List v8_6;
        List v7_2;
        Intrinsics.checkNotNullParameter(arg7, "context");
        Intrinsics.checkNotNullParameter(arg8, "uri");
        if(PG_FileUtils.DEBUG) {
            StringBuilder v1 = new StringBuilder("Authority: ").append(arg8.getAuthority()).append(", Fragment: ").append(arg8.getFragment()).append(", Port: ").append(arg8.getPort()).append(", Query: ").append(arg8.getQuery()).append(", Scheme: ").append(arg8.getScheme()).append(", Host: ").append(arg8.getHost()).append(", Segments: ");
            List v2 = arg8.getPathSegments();
            Intrinsics.checkNotNullExpressionValue(v2, "uri.pathSegments");
            Log.d(PG_FileUtils.TAG + " File -", v1.append(v2).toString());
        }

        Uri v1_1 = null;
        if(DocumentsContract.isDocumentUri(arg7, arg8)) {
            if(this.isExternalStorageDocument(arg8)) {
                String v7 = DocumentsContract.getDocumentId(arg8);
                Intrinsics.checkNotNullExpressionValue(v7, "docId");
                List v7_1 = new Regex(":").split(((CharSequence)v7), 0);
                if(!v7_1.isEmpty()) {
                    ListIterator v8 = v7_1.listIterator(v7_1.size());
                    while(v8.hasPrevious()) {
                        if(((CharSequence)(((String)v8.previous()))).length() == 0) {
                            continue;
                        }

                        v7_2 = CollectionsKt.take(((Iterable)v7_1), v8.nextIndex() + 1);
                        String[] v7_3 = (String[])((Collection)v7_2).toArray(new String[0]);
                        return StringsKt.equals("primary", v7_3[0], true) ? Environment.getExternalStorageDirectory().toString() + '/' + v7_3[1] : null;
                    }
                }

                v7_2 = CollectionsKt.emptyList();
            }

            if(this.isDownloadsDocument(arg8)) {
                String v8_1 = DocumentsContract.getDocumentId(arg8);
                Uri v0 = Uri.parse("content://downloads/public_downloads");
                Long v8_2 = Long.valueOf(v8_1);
                Intrinsics.checkNotNullExpressionValue(v8_2, "valueOf(id)");
                Uri v8_3 = ContentUris.withAppendedId(v0, ((Number)v8_2).longValue());
                Intrinsics.checkNotNullExpressionValue(v8_3, "withAppendedId(\n        …eOf(id)\n                )");
                return this.getDataColumn(arg7, v8_3, null, null);
            }

            if(this.isMediaDocument(arg8)) {
                String v8_4 = DocumentsContract.getDocumentId(arg8);
                Intrinsics.checkNotNullExpressionValue(v8_4, "docId");
                List v8_5 = new Regex(":").split(((CharSequence)v8_4), 0);
                if(!v8_5.isEmpty()) {
                    ListIterator v0_1 = v8_5.listIterator(v8_5.size());
                    while(v0_1.hasPrevious()) {
                        if(((CharSequence)(((String)v0_1.previous()))).length() == 0) {
                            continue;
                        }

                        v8_6 = CollectionsKt.take(((Iterable)v8_5), v0_1.nextIndex() + 1);
                        String[] v8_7 = (String[])((Collection)v8_6).toArray(new String[0]);
                        String v0_2 = v8_7[0];
                        if(Intrinsics.areEqual("image", v0_2)) {
                            return this.getDataColumn(arg7, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_id=?", new String[]{v8_7[1]});
                        }

                        if(Intrinsics.areEqual("video", v0_2)) {
                            return this.getDataColumn(arg7, MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "_id=?", new String[]{v8_7[1]});
                        }

                        if(Intrinsics.areEqual("audio", v0_2)) {
                            v1_1 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                        }

                        return this.getDataColumn(arg7, v1_1, "_id=?", new String[]{v8_7[1]});
                    }
                }

                v8_6 = CollectionsKt.emptyList();
            }

            return null;
        }

        String v0_3 = arg8.getScheme();
        Intrinsics.checkNotNull(v0_3);
        if(StringsKt.equals("content", v0_3, true)) {
            return this.isGooglePhotosUri(arg8) ? arg8.getLastPathSegment() : this.getDataColumn(arg7, arg8, null, null);
        }

        String v7_4 = arg8.getScheme();
        Intrinsics.checkNotNull(v7_4);
        return StringsKt.equals("file", v7_4, true) ? arg8.getPath() : null;
    }

    public final File getFile(Context arg2, Uri arg3) {
        Intrinsics.checkNotNullParameter(arg2, "context");
        if(arg3 != null) {
            String v2 = this.getPath(arg2, arg3);
            return v2 == null || !this.isLocal(v2) ? null : new File(v2);
        }

        return null;
    }

    public final String getReadableFileSize(int i) {
        float f;
        DecimalFormat decimalFormat = new DecimalFormat("###.#");
        String str = " KB";
        if (i > 1024) {
            f = i / 1024;
            float f2 = 1024;
            if (f > f2) {
                f /= f2;
                if (f > f2) {
                    f /= f2;
                    str = " GB";
                } else {
                    str = " MB";
                }
            }
        } else {
            f = 0.0f;
        }
        return decimalFormat.format(f) + str;
    }

    public final Bitmap getThumbnail(Context context, File file) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(file, "file");
        return getThumbnail(context, getUri(file), getMimeType(file));
    }

    public static  Bitmap getThumbnail$default(PG_FileUtils pG_FileUtils, Context context, Uri uri, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            Intrinsics.checkNotNull(uri);
            str = pG_FileUtils.getMimeType(context, uri);
        }
        return pG_FileUtils.getThumbnail(context, uri, str);
    }
    public final Bitmap getThumbnail(Context arg11, Uri arg12, String arg13) {
        Bitmap v11_2;
        Cursor v12 = null;
        Intrinsics.checkNotNullParameter(arg11, "context");
        boolean v1 = PG_FileUtils.DEBUG;
        if(v1) {
            Log.d(PG_FileUtils.TAG, "Attempting to get thumbnail");
        }

        Intrinsics.checkNotNull(arg12);
        Bitmap v3 = null;
        if(!this.isMediaUri(arg12)) {
            Log.e(PG_FileUtils.TAG, "You can only retrieve thumbnails for images and videos.");
            return null;
        }

        ContentResolver v11 = arg11.getContentResolver();
        try {
            v12 = v11.query(arg12, null, null, null, null);
            Intrinsics.checkNotNull(v12);
            if(v12.moveToFirst()) {
                int v4 = v12.getInt(0);
                if(v1) {
                    Log.d(PG_FileUtils.TAG, "Got thumb ID: " + v4);
                }

                Intrinsics.checkNotNull(arg13);
                if(StringsKt.contains(((CharSequence)arg13), "video", false)) {
                    v11_2 = MediaStore.Video.Thumbnails.getThumbnail(v11, ((long)v4), 1, null);
                    v12.close();
                    return v11_2;
                }
                else {
                    if(!StringsKt.contains(((CharSequence)arg13), ((CharSequence)PG_FileUtils.MIME_TYPE_IMAGE), false)) {
                        v12.close();
                        return v3;
                    }

                    v11_2 = MediaStore.Images.Thumbnails.getThumbnail(v11, ((long)v4), 1, null);
                    v3 = v11_2;
                }
            }
        }
        catch(Exception v11_1) {
            if(PG_FileUtils.DEBUG) {
                Log.e(PG_FileUtils.TAG, "getThumbnail", ((Throwable)v11_1));
            }

            if(v12 == null) {
                return null;
            }
        }
        return v3;
    }

    public final Intent createGetContentIntent() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        intent.addCategory("android.intent.category.OPENABLE");
        return intent;
    }

    public final void cleanTempFiles() {
        File[] v0 = new File(PG_FileUtils.TEMP_FOLDER).listFiles();
        if (v0 != null) {
            int v2;
            for (v2 = 0; v2 < v0.length; ++v2) {
                v0[v2].delete();
            }
        }
    }

    public final boolean saveToFile(InputStream arg5, File arg6) {
        Intrinsics.checkNotNullParameter(arg5, "is");
        Intrinsics.checkNotNullParameter(arg6, "dest");
        try {
            if(arg6.getParentFile() != null) {
                File v1 = arg6.getParentFile();
                Intrinsics.checkNotNull(v1);
                if(!v1.exists()) {
                    File v1_1 = arg6.getParentFile();
                    Intrinsics.checkNotNull(v1_1);
                    v1_1.mkdirs();
                }
            }

            FileOutputStream v1_2 = new FileOutputStream(arg6);
            byte[] v6 = new byte[0x800];
            while(arg5.read(v6) != -1) {
                v1_2.write(v6, 0, arg5.read(v6));
            }

            v1_2.flush();
            arg5.close();
            v1_2.close();
            return true;
        }
        catch(FileNotFoundException v5_1) {
            v5_1.printStackTrace();
            return false;
        }
        catch(IOException v5) {
            v5.printStackTrace();
            return false;
        }
    }

    public final void downloadFile(String arg4, String arg5) throws IOException {
        Intrinsics.checkNotNullParameter(arg4, "urlText");
        Intrinsics.checkNotNullParameter(arg5, "outPath");
        URLConnection v4 = new URL(arg4).openConnection();
        v4.connect();
        InputStream v4_1 = v4.getInputStream();
        File v0 = new File(arg5).getParentFile();
        Intrinsics.checkNotNull(v0);
        v0.mkdirs();
        FileOutputStream v0_1 = new FileOutputStream(new File(arg5));
        byte[] v5 = new byte[0x800];
        while (v4_1.read(v5) != -1) {
            v0_1.write(v5, 0, v4_1.read(v5));
        }

        v0_1.flush();
        v0_1.close();
        v4_1.close();
    }

    public final String sha128s(String arg6) throws NoSuchAlgorithmException {
        Intrinsics.checkNotNullParameter(arg6, "input");
        MessageDigest v0 = MessageDigest.getInstance("SHA-1");
        byte[] v6 = arg6.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(v6, "this as java.lang.String).getBytes(charset)");
        byte[] v6_1 = v0.digest(v6);
        StringBuffer v0_1 = new StringBuffer();
        int v2;
        for(v2 = 0; v2 < v6_1.length; ++v2) {
            String v3 = Integer.toString(((byte)(v6_1[v2] & -1)) + 0x100, 16);
            Intrinsics.checkNotNullExpressionValue(v3, "toString((result[i] and …ff.toByte()) + 0x100, 16)");
            String v3_1 = v3.substring(1);
            Intrinsics.checkNotNullExpressionValue(v3_1, "this as java.lang.String).substring(startIndex)");
            v0_1.append(v3_1);
        }

        String v6_2 = v0_1.toString();
        Intrinsics.checkNotNullExpressionValue(v6_2, "sb.toString()");
        return v6_2;
    }
    public final void deleteFile(File arg6) {
        Intrinsics.checkNotNullParameter(arg6, "file");
        if (arg6.isDirectory()) {
            File[] v0 = arg6.listFiles();
            if (v0 != null) {
                int v2;
                for (v2 = 0; v2 < v0.length; ++v2) {
                    File v3 = v0[v2];
                    Intrinsics.checkNotNullExpressionValue(v3, "f");
                    this.deleteFile(v3);
                }

                arg6.delete();
                return;
            }

            arg6.delete();
            return;
        }

        arg6.delete();
    }


    public final void unzip(String zipFilePath, String outFolder) {
        Intrinsics.checkNotNullParameter(zipFilePath, "zipFilePath");
        Intrinsics.checkNotNullParameter(outFolder, "outFolder");
        File file = new File(outFolder);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            extractFolder(new File(zipFilePath), file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void extractFolder(File arg9, File arg10) throws ZipException, IOException {
        ZipFile v0 = new ZipFile(arg9);
        Enumeration v9 = v0.entries();
        while (v9.hasMoreElements()) {
            Object v1 = v9.nextElement();
            Intrinsics.checkNotNull(v1, "null cannot be cast to non-null type java.util.zip.ZipEntry");
            ZipEntry v1_1 = (ZipEntry) v1;
            String v2 = v1_1.getName();
            File v3 = new File(arg10.getAbsolutePath(), v2);
            File v2_1 = v3.getParentFile();
            Intrinsics.checkNotNull(v2_1);
            v2_1.mkdirs();
            if (v1_1.isDirectory()) {
                continue;
            }

            BufferedInputStream v2_2 = new BufferedInputStream(v0.getInputStream(v1_1));
            byte[] v4 = new byte[0x800];
            if (v3.exists()) {
                continue;
            }

            BufferedOutputStream v3_1 = new BufferedOutputStream(((OutputStream) new FileOutputStream(v3)), 0x800);
            while (v2_2.read(v4, 0, 0x800) != -1) {
                v3_1.write(v4, 0, v2_2.read(v4, 0, 0x800));
            }

            v3_1.flush();
            v3_1.close();
            v2_2.close();
        }

        v0.close();
    }

    public final String saveBitmapToFile(Bitmap bitmap, String outPath) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(outPath, "outPath");
        File file = new File(outPath);
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final String generateMD5(String arg7, long arg8, long arg10) throws IOException {
        String v7_2;
        RandomAccessFile v1;
        Intrinsics.checkNotNullParameter(arg7, "sourcePath");
        try {
            v1 = new RandomAccessFile(new File(arg7), "r");
        } catch (IOException unused_ex) {
            v1 = null;
            if (v1 != null) {
                try {
                    v1.close();
                } catch (IOException v7_4) {
                    v7_4.printStackTrace();
                }

                return null;
            }
        }

        try {
            v1.seek(arg8);
            byte[] v7 = new byte[0x800];
            int v9 = v1.read(v7);
            MessageDigest v8 = MessageDigest.getInstance("MD5");
            Intrinsics.checkNotNullExpressionValue(v8, "getInstance(\"MD5\")");
            long v2;
            for (v2 = 0L; v1.read(v7) > 0 && v2 < arg10; v2 += (long) v9) {
                v9 = v1.read(v7);
                v8.update(v7, 0, v9);
            }

            byte[] v7_1 = v8.digest();
            Intrinsics.checkNotNullExpressionValue(v7_1, "hashValue");
            v7_2 = this.byteArrayToHexString(v7_1);
        } catch (NoSuchAlgorithmException unused_ex) {


            return null;
        } catch (IOException unused_ex) {
            return null;
        }

        try {
            v1.close();
        } catch (IOException v8_1) {
            v8_1.printStackTrace();
        }

        return v7_2;
    }

    public final String generateMD5(InputStream arg6) {
        String v0_1;
        if (arg6 == null) {
            return null;
        }

        try {
            byte[] v1 = new byte[0x800];
            MessageDigest v2 = MessageDigest.getInstance("MD5");
            Intrinsics.checkNotNullExpressionValue(v2, "getInstance(\"MD5\")");
            while (arg6.read(v1) > 0) {
                v2.update(v1, 0, arg6.read(v1));
            }

            byte[] v1_1 = v2.digest();
            Intrinsics.checkNotNullExpressionValue(v1_1, "hashValue");
            v0_1 = this.byteArrayToHexString(v1_1);
        } catch (NoSuchAlgorithmException unused_ex) {
            try {
                arg6.close();
            } catch (IOException v6) {
                v6.printStackTrace();
            }

            return null;
        } catch (IOException unused_ex) {
            try {
                arg6.close();
            } catch (IOException v6_1) {
                v6_1.printStackTrace();
            }

            return null;
        } catch (Throwable v0) {
            try {
                arg6.close();
            } catch (IOException v6_2) {
                v6_2.printStackTrace();
            }

            throw v0;
        }

        try {
            arg6.close();
        } catch (IOException v6_3) {
            v6_3.printStackTrace();
        }

        return v0_1;
    }

    public final String byteArrayToHexString(byte[] arg6) {
        Intrinsics.checkNotNullParameter(arg6, "b");
        String v1 = "";
        int v2;
        for (v2 = 0; v2 < arg6.length; ++v2) {
            StringBuilder v1_1 = new StringBuilder().append(v1);
            String v3 = Integer.toString(((byte) (arg6[v2] & -1)) + 0x100, 16);
            Intrinsics.checkNotNullExpressionValue(v3, "toString((b[i] and 0xff.toByte()) + 0x100, 16)");
            String v3_1 = v3.substring(1);
            Intrinsics.checkNotNullExpressionValue(v3_1, "this as java.lang.String).substring(startIndex)");
            v1 = v1_1.append(v3_1).toString();
        }

        String v6 = v1.toLowerCase();
        Intrinsics.checkNotNullExpressionValue(v6, "this as java.lang.String).toLowerCase()");
        return v6;
    }

    public final boolean copyFile(File arg5, File arg6) {
        Intrinsics.checkNotNullParameter(arg5, "source");
        Intrinsics.checkNotNullParameter(arg6, "dest");
        try {
            File v1 = arg6.getParentFile();
            Intrinsics.checkNotNull(v1);
            v1.mkdirs();
            FileInputStream v1_1 = new FileInputStream(arg5);
            FileOutputStream v5_2 = new FileOutputStream(arg6);
            byte[] v6 = new byte[0x800];
            while (v1_1.read(v6) != -1) {
                v5_2.write(v6, 0, v1_1.read(v6));
            }

            v5_2.flush();
            v1_1.close();
            v5_2.close();
            return true;
        } catch (FileNotFoundException v5_1) {
            v5_1.printStackTrace();
            return false;
        } catch (IOException v5) {
            v5.printStackTrace();
            return false;
        }
    }
}
