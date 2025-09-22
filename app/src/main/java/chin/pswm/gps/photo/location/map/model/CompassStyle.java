package chin.pswm.gps.photo.location.map.model;

public class CompassStyle {
    private final int styleImage;
    private final int baseImage;
    private final int fanImage;


    public CompassStyle(int styleImage, int baseImage, int fanImage, int styleId) {
        this.styleImage = styleImage;
        this.baseImage = baseImage;
        this.fanImage = fanImage;

    }

    public int getStyleImage() {
        return styleImage;
    }

    public int getBaseImage() {
        return baseImage;
    }

    public int getFanImage() {
        return fanImage;
    }

}
