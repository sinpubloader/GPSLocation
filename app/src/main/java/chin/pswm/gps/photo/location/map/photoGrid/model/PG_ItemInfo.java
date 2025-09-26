package chin.pswm.gps.photo.location.map.photoGrid.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
@SuppressWarnings("all")
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 .2\u00020\u0001:\u0001.B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0014\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010)\u001a\u00020\u001bH\u0016J\u0018\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u001bH\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0014\"\u0004\b\"\u0010\u0016R\u001c\u0010#\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0014\"\u0004\b%\u0010\u0016R\u001c\u0010&\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0014\"\u0004\b(\u0010\u0016¨\u0006/"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_ItemInfo;", "Landroid/os/Parcelable;", "()V", "in", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "", "getId", "()J", "setId", "(J)V", "isSelected", "", "()Z", "setSelected", "(Z)V", "lastModified", "", "getLastModified", "()Ljava/lang/String;", "setLastModified", "(Ljava/lang/String;)V", "selectedThumbnail", "getSelectedThumbnail", "setSelectedThumbnail", "showingType", "", "getShowingType", "()I", "setShowingType", "(I)V", NotificationCompat.CATEGORY_STATUS, "getStatus", "setStatus", "thumbnail", "getThumbnail", "setThumbnail", "title", "getTitle", "setTitle", "describeContents", "writeToParcel", "", "dest", "flags", "CREATOR", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public class PG_ItemInfo implements Parcelable {
    private static final int NORMAL_ITEM_TYPE = 0;
    private long id;
    private boolean isSelected;
    private String lastModified;
    private String selectedThumbnail;
    private int showingType;
    private String status;
    private String thumbnail;
    private String title;
    public static final CREATOR CREATOR = new CREATOR(null);
    private static final String STATUS_ACTIVE = "active";
    private static final String STATUS_DELETED = "deleted";
    private static final int PACKAGE_ITEM_TYPE = 1;
    private static final int ADD_ITEM_TYPE = 2;
    private static final int SQUARE_CROP_TYPE = 3;
    private static final int CUSTOM_CROP_TYPE = 4;
    private static final int DRAW_CROP_TYPE = 5;

    @Override 
    public int describeContents() {
        return 0;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final String getThumbnail() {
        return this.thumbnail;
    }

    public final void setThumbnail(String str) {
        this.thumbnail = str;
    }

    public final String getSelectedThumbnail() {
        return this.selectedThumbnail;
    }

    public final void setSelectedThumbnail(String str) {
        this.selectedThumbnail = str;
    }

    public final boolean isSelected() {
        return this.isSelected;
    }

    public final void setSelected(boolean z) {
        this.isSelected = z;
    }

    public final int getShowingType() {
        return this.showingType;
    }

    public final void setShowingType(int i) {
        this.showingType = i;
    }

    public final String getLastModified() {
        return this.lastModified;
    }

    public final void setLastModified(String str) {
        this.lastModified = str;
    }

    public final String getStatus() {
        return this.status;
    }

    public final void setStatus(String str) {
        this.status = str;
    }

    public final long getId() {
        return this.id;
    }

    public final void setId(long j) {
        this.id = j;
    }

    public PG_ItemInfo() {
        this.showingType = NORMAL_ITEM_TYPE;
    }

    @Override 
    public void writeToParcel(Parcel dest, int i) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(this.title);
        dest.writeString(this.thumbnail);
        dest.writeString(this.selectedThumbnail);
        dest.writeBooleanArray(new boolean[]{this.isSelected});
        dest.writeInt(this.showingType);
        dest.writeString(this.lastModified);
        dest.writeString(this.status);
        dest.writeLong(this.id);
    }

    public PG_ItemInfo(Parcel in) {
        Intrinsics.checkNotNullParameter(in, "in");
        this.showingType = NORMAL_ITEM_TYPE;
        this.title = in.readString();
        this.thumbnail = in.readString();
        this.selectedThumbnail = in.readString();
        boolean[] zArr = new boolean[1];
        in.readBooleanArray(zArr);
        this.isSelected = zArr[0];
        this.showingType = in.readInt();
        this.lastModified = in.readString();
        this.status = in.readString();
        this.id = in.readLong();
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u001d\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010\u001eR\u0014\u0010\u0004\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0014\u0010\f\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0014\u0010\u000e\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0014\u0010\u0010\u001a\u00020\u0005X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0014\u0010\u0012\u001a\u00020\u0013X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u0013X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015¨\u0006\u001f"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_ItemInfo$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_ItemInfo;", "()V", "ADD_ITEM_TYPE", "", "getADD_ITEM_TYPE", "()I", "CUSTOM_CROP_TYPE", "getCUSTOM_CROP_TYPE", "DRAW_CROP_TYPE", "getDRAW_CROP_TYPE", "NORMAL_ITEM_TYPE", "getNORMAL_ITEM_TYPE", "PACKAGE_ITEM_TYPE", "getPACKAGE_ITEM_TYPE", "SQUARE_CROP_TYPE", "getSQUARE_CROP_TYPE", "STATUS_ACTIVE", "", "getSTATUS_ACTIVE", "()Ljava/lang/String;", "STATUS_DELETED", "getSTATUS_DELETED", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "(I)[Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_ItemInfo;", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public static final class CREATOR implements Parcelable.Creator<PG_ItemInfo> {
        public  CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }

        public final String getSTATUS_ACTIVE() {
            return PG_ItemInfo.STATUS_ACTIVE;
        }

        public final String getSTATUS_DELETED() {
            return PG_ItemInfo.STATUS_DELETED;
        }

        public final int getNORMAL_ITEM_TYPE() {
            return PG_ItemInfo.NORMAL_ITEM_TYPE;
        }

        public final int getPACKAGE_ITEM_TYPE() {
            return PG_ItemInfo.PACKAGE_ITEM_TYPE;
        }

        public final int getADD_ITEM_TYPE() {
            return PG_ItemInfo.ADD_ITEM_TYPE;
        }

        public final int getSQUARE_CROP_TYPE() {
            return PG_ItemInfo.SQUARE_CROP_TYPE;
        }

        public final int getCUSTOM_CROP_TYPE() {
            return PG_ItemInfo.CUSTOM_CROP_TYPE;
        }

        public final int getDRAW_CROP_TYPE() {
            return PG_ItemInfo.DRAW_CROP_TYPE;
        }

        @Override 
        public PG_ItemInfo createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new PG_ItemInfo(parcel);
        }

        @Override 
        public PG_ItemInfo[] newArray(int i) {
            return new PG_ItemInfo[i];
        }
    }
}
