package chin.pswm.gps.photo.location.map.photoGrid.model;

import java.util.ArrayList;

import chin.pswm.gps.photo.location.map.photoGrid.template.PG_PhotoItem;
import chin.pswm.gps.photo.location.map.photoGrid.template.PG_PhotoLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0002\u0010\u0004R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0010\u0010\u0016R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001d¨\u0006!"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_TemplatePGItem;", "Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_ImageTemplate;", "()V", "template", "(Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_ImageTemplate;)V", "PGPhotoItemList", "Ljava/util/ArrayList;", "Lchin/pswm/gps/photo/location/map/photoGrid/template/PG_PhotoItem;", "getPGPhotoItemList", "()Ljava/util/ArrayList;", "setPGPhotoItemList", "(Ljava/util/ArrayList;)V", "header", "", "getHeader", "()Ljava/lang/String;", "setHeader", "(Ljava/lang/String;)V", "isAds", "", "()Z", "setAds", "(Z)V", "isHeader", "sectionFirstPosition", "", "getSectionFirstPosition", "()I", "setSectionFirstPosition", "(I)V", "sectionManager", "getSectionManager", "setSectionManager", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_TemplatePGItem extends PG_ImageTemplate {
    private ArrayList<PG_PhotoItem> PGPhotoItemList;
    private String header;
    private boolean isAds;
    private boolean isHeader;
    private int sectionFirstPosition;
    private int sectionManager;

    public final int getSectionManager() {
        return this.sectionManager;
    }

    public final void setSectionManager(int i) {
        this.sectionManager = i;
    }

    public final int getSectionFirstPosition() {
        return this.sectionFirstPosition;
    }

    public final void setSectionFirstPosition(int i) {
        this.sectionFirstPosition = i;
    }

    public final boolean isHeader() {
        return this.isHeader;
    }

    public final void setHeader(boolean z) {
        this.isHeader = z;
    }

    public final String getHeader() {
        return this.header;
    }

    public final void setHeader(String str) {
        this.header = str;
    }

    public final boolean isAds() {
        return this.isAds;
    }

    public final void setAds(boolean z) {
        this.isAds = z;
    }

    public final ArrayList<PG_PhotoItem> getPGPhotoItemList() {
        return this.PGPhotoItemList;
    }

    public final void setPGPhotoItemList(ArrayList<PG_PhotoItem> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.PGPhotoItemList = arrayList;
    }

    public PG_TemplatePGItem() {
        this.PGPhotoItemList = new ArrayList<>();
    }

    public PG_TemplatePGItem(PG_ImageTemplate template) {
        Intrinsics.checkNotNullParameter(template, "template");
        this.PGPhotoItemList = new ArrayList<>();
        setPGLanguages(template.getPGLanguages());
        setPackageId(template.getPackageId());
        setPreview(template.getPreview());
        setMtemplate(template.getMtemplate());
        setChild(template.getChild());
        setTitle(template.getTitle());
        setThumbnail(template.getThumbnail());
        setSelectedThumbnail(template.getSelectedThumbnail());
        setSelected(template.isSelected());
        setShowingType(template.getShowingType());
        setLastModified(template.getLastModified());
        setStatus(template.getStatus());
        setId(template.getId());
        this.PGPhotoItemList = PG_PhotoLayout.Companion.parseImageTemplate(template);
    }
}
