package chin.pswm.gps.photo.location.map.photoGrid.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
@SuppressWarnings("all")
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 &2\u00020\u0001:\u0001&B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0014\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010 \u001a\u00020!H\u0016J\u0018\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020!H\u0016R$\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0086.¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0011\"\u0004\b\u001f\u0010\u0013¨\u0006'"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_ImageTemplate;", "Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_ItemInfo;", "()V", "in", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "PGLanguages", "", "Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_Language;", "getPGLanguages", "()[Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_Language;", "setPGLanguages", "([Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_Language;)V", "[Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_Language;", "child", "", "getChild", "()Ljava/lang/String;", "setChild", "(Ljava/lang/String;)V", "mtemplate", "getMtemplate", "setMtemplate", "packageId", "", "getPackageId", "()J", "setPackageId", "(J)V", "preview", "getPreview", "setPreview", "describeContents", "", "writeToParcel", "", "dest", "flags", "CREATOR", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public class PG_ImageTemplate extends PG_ItemInfo {
    public static final CREATOR CREATOR = new CREATOR(null);
    public PG_Language[] PGLanguages;
    private String child;
    private String mtemplate;
    private long packageId;
    private String preview;

    @Override
    public int describeContents() {
        return 0;
    }

    public final PG_Language[] getPGLanguages() {
        PG_Language[] pG_LanguageArr = this.PGLanguages;
        if (pG_LanguageArr != null) {
            return pG_LanguageArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("PGLanguages");
        return null;
    }

    public final void setPGLanguages(PG_Language[] pG_LanguageArr) {
        Intrinsics.checkNotNullParameter(pG_LanguageArr, "<set-?>");
        this.PGLanguages = pG_LanguageArr;
    }

    public final long getPackageId() {
        return this.packageId;
    }

    public final void setPackageId(long j) {
        this.packageId = j;
    }

    public final String getPreview() {
        return this.preview;
    }

    public final void setPreview(String str) {
        this.preview = str;
    }

    public final String getMtemplate() {
        return this.mtemplate;
    }

    public final void setMtemplate(String str) {
        this.mtemplate = str;
    }

    public final String getChild() {
        return this.child;
    }

    public final void setChild(String str) {
        this.child = str;
    }

    public PG_ImageTemplate() {
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(dest, "dest");
        super.writeToParcel(dest, i);
        if (getPGLanguages() != null) {
            PG_Language[] pGLanguages = getPGLanguages();
            Intrinsics.checkNotNull(pGLanguages);
            if (pGLanguages.length > 0) {
                PG_Language[] pGLanguages2 = getPGLanguages();
                Intrinsics.checkNotNull(pGLanguages2);
                i2 = pGLanguages2.length;
                dest.writeInt(i2);
                if (getPGLanguages() != null && i2 > 0) {
                    dest.writeTypedArray(getPGLanguages(), i);
                }
                dest.writeLong(this.packageId);
                dest.writeString(this.preview);
                dest.writeString(this.mtemplate);
                dest.writeString(this.child);
            }
        }
        i2 = 0;
        dest.writeInt(i2);
        if (getPGLanguages() != null) {
            dest.writeTypedArray(getPGLanguages(), i);
        }
        dest.writeLong(this.packageId);
        dest.writeString(this.preview);
        dest.writeString(this.mtemplate);
        dest.writeString(this.child);
    }

    
    protected PG_ImageTemplate(Parcel in) {
        super(in);
        Intrinsics.checkNotNullParameter(in, "in");
        int readInt = in.readInt();
        if (readInt > 0) {
            setPGLanguages(new PG_Language[readInt]);
            in.readTypedArray(getPGLanguages(), PG_Language.CREATOR);
        }
        this.packageId = in.readLong();
        this.preview = in.readString();
        this.mtemplate = in.readString();
        this.child = in.readString();
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_ImageTemplate$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_ImageTemplate;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_ImageTemplate;", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    
    public static final class CREATOR implements Parcelable.Creator<PG_ImageTemplate> {
        public  CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }

        @Override 
        public PG_ImageTemplate createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new PG_ImageTemplate(parcel);
        }

        @Override 
        public PG_ImageTemplate[] newArray(int i) {
            return new PG_ImageTemplate[i];
        }
    }
}
