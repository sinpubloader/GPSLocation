package chin.pswm.gps.photo.location.map.model;


public class IntroModel {
    private final int infoHeader;
    private final int infoSrc;
    private final int infoTxt;

    public IntroModel(int i, int i2, int i3) {
        this.infoSrc = i;
        this.infoHeader = i2;
        this.infoTxt = i3;
    }

    public int getInfoSrc() {
        return this.infoSrc;
    }

    public int getInfoHeader() {
        return this.infoHeader;
    }

    public int getInfoTxt() {
        return this.infoTxt;
    }
}
