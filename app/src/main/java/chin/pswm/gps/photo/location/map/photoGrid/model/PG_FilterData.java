package chin.pswm.gps.photo.location.map.photoGrid.model;

import com.google.gson.annotations.SerializedName;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0013\u0018\u00002\u00020\u0001B/\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tR\u001e\u0010\u0007\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001e\u0010\b\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lchin/pswm/gps/photo/location/map/photoGrid/model/PG_FilterData;", "", "text", "", "red", "", "green", "blue", "saturation", "(Ljava/lang/String;FFFF)V", "getBlue", "()F", "setBlue", "(F)V", "getGreen", "setGreen", "getRed", "setRed", "getSaturation", "setSaturation", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "app_release"}, k = 1, mv = {1, 8, 0}, xi = 48)

public final class PG_FilterData {
    @SerializedName("blue")
    private float blue;
    @SerializedName("green")
    private float green;
    @SerializedName("red")
    private float red;
    @SerializedName("saturation")
    private float saturation;
    @SerializedName("text")
    private String text;

    public final String getText() {
        return this.text;
    }

    public final void setText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.text = str;
    }

    public final float getRed() {
        return this.red;
    }

    public final void setRed(float f) {
        this.red = f;
    }

    public final float getGreen() {
        return this.green;
    }

    public final void setGreen(float f) {
        this.green = f;
    }

    public final float getBlue() {
        return this.blue;
    }

    public final void setBlue(float f) {
        this.blue = f;
    }

    public final float getSaturation() {
        return this.saturation;
    }

    public final void setSaturation(float f) {
        this.saturation = f;
    }

    public PG_FilterData(String text, float f, float f2, float f3, float f4) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.text = text;
        this.red = f;
        this.green = f2;
        this.blue = f3;
        this.saturation = f4;
    }
}
