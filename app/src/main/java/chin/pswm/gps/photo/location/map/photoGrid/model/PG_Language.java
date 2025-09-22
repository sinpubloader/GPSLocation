package chin.pswm.gps.photo.location.map.photoGrid.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0012\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0010H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000b¨\u0006\u0016"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_Language;", "Landroid/os/Parcelable;", "()V", "in", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "value", "getValue", "setValue", "describeContents", "", "writeToParcel", "", "dest", "flags", "CREATOR", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_Language implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private String name;
    private String value;

    public  PG_Language(Parcel parcel, DefaultConstructorMarker defaultConstructorMarker) {
        this(parcel);
    }

    @Override 
    public int describeContents() {
        return 0;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final String getValue() {
        return this.value;
    }

    public final void setValue(String str) {
        this.value = str;
    }

    public PG_Language() {
    }

    @Override 
    public void writeToParcel(Parcel dest, int i) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(this.name);
        dest.writeString(this.value);
    }

    private PG_Language(Parcel parcel) {
        this.name = parcel.readString();
        this.value = parcel.readString();
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_Language$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_Language;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_Language;", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public static final class CREATOR implements Parcelable.Creator<PG_Language> {
        public  CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }

        @Override 
        public PG_Language createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new PG_Language(parcel, null);
        }

        @Override 
        public PG_Language[] newArray(int i) {
            return new PG_Language[i];
        }
    }
}
