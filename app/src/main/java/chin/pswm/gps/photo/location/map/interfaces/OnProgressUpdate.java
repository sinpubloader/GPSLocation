package chin.pswm.gps.photo.location.map.interfaces;

import java.util.ArrayList;

import chin.pswm.gps.photo.location.map.model.GalleryModel;


public interface OnProgressUpdate {
    void onComplete(ArrayList<GalleryModel> arrayList);

    void onTaskStart();
}
