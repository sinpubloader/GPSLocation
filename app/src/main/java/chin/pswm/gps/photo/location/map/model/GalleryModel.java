package chin.pswm.gps.photo.location.map.model;

import java.util.ArrayList;


public class GalleryModel {
    String folderName;
    ArrayList<String> itemList;

    public GalleryModel(String str, ArrayList<String> arrayList) {
        new ArrayList();
        this.folderName = str;
        this.itemList = arrayList;
    }

    public String getFolderName() {
        return this.folderName;
    }

    public void setFolderName(String str) {
        this.folderName = str;
    }

    public ArrayList<String> getItemList() {
        return this.itemList;
    }

    public void setItemList(ArrayList<String> arrayList) {
        this.itemList = arrayList;
    }
}
