package chin.pswm.gps.photo.location.map.utils;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.MediaStore;
import chin.pswm.gps.photo.location.map.interfaces.OnProgressUpdate;
import chin.pswm.gps.photo.location.map.model.GalleryModel;
import com.fom.rapid.app.Media;
import java.util.ArrayList;


public class GalleryAsyncTask extends AsyncTask<Void, Void, Void> {
    public ArrayList<GalleryModel> arrayList = new ArrayList<>();
    Context context;
    OnProgressUpdate listener;

    public GalleryAsyncTask(Context context, OnProgressUpdate onProgressUpdate) {
        this.context = context;
        this.listener = onProgressUpdate;
    }

    @Override 
    protected void onPreExecute() {
        super.onPreExecute();
        this.listener.onTaskStart();
    }

    @Override 
    public Void doInBackground(Void... voidArr) {
        this.arrayList = getFolderList();
        return null;
    }

    @Override 
    public void onPostExecute(Void r2) {
        super.onPostExecute( r2);
        this.listener.onComplete(this.arrayList);
    }

    public Cursor folderCursor() {
        return this.context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "bucket_id", "_size", "bucket_display_name", "date_added"}, "_size>? ", new String[]{"0"}, "date_added DESC");
    }

    public ArrayList<GalleryModel> getFolderList() {
        ArrayList<GalleryModel> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        Cursor folderCursor = folderCursor();
        while (folderCursor.moveToNext()) {
            String string = folderCursor.getString(folderCursor.getColumnIndexOrThrow("bucket_id"));
            String string2 = folderCursor.getString(folderCursor.getColumnIndexOrThrow("bucket_display_name"));
            if (!arrayList2.contains(string)) {
                arrayList.add(new GalleryModel(string2, getItemList(string)));
                arrayList2.add(string);
            }
        }
        folderCursor.close();
        return arrayList;
    }

    public Cursor itemCursor(String str) {
        return this.context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "bucket_id", "_size", "bucket_display_name", "date_added"}, "bucket_id=? AND _size>? ", new String[]{str, "0"}, "date_added DESC");
    }

    public ArrayList<String> getItemList(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor itemCursor = itemCursor(str);
        while (itemCursor.moveToNext()) {
            String string = itemCursor.getString(itemCursor.getColumnIndexOrThrow("_data"));
            itemCursor.getString(itemCursor.getColumnIndexOrThrow("bucket_id"));
            arrayList.add(string);
        }
        itemCursor.close();
        return arrayList;
    }
}
