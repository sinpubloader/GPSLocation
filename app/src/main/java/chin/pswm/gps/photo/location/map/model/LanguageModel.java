package chin.pswm.gps.photo.location.map.model;


public class LanguageModel {
    private String id;
    private int image;
    private String language_name;

    public int getImage() {
        return this.image;
    }

    public void setImage(int i) {
        this.image = i;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getLanguage_name() {
        return this.language_name;
    }

    public void setLanguage_name(String str) {
        this.language_name = str;
    }

    public LanguageModel(int i, String str, String str2) {
        this.id = str2;
        this.language_name = str;
        this.image = i;
    }
}
