package chin.pswm.gps.photo.location.map.interfaces;

import chin.pswm.gps.photo.location.map.model.GalleryModel;
import java.util.ArrayList;


public interface OnProgressUpdate {
    void onComplete(ArrayList<GalleryModel> arrayList);

    void onTaskStart();
}
