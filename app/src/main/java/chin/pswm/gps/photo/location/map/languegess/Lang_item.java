package chin.pswm.gps.photo.location.map.languegess;

public class Lang_item {
    String Lag_name;
    String Lag_code;
    String Lag_de_name;
    int Flag_image;

    public Lang_item(String lag_name, String lag_code, String lag_de_name, int flag_image) {
        Lag_name = lag_name;
        Lag_code = lag_code;
        Lag_de_name = lag_de_name;
        Flag_image = flag_image;
    }

    public int getFlag_image() {
        return Flag_image;
    }

    public void setFlag_image(int flag_image) {
        this.Flag_image = flag_image;
    }

    public String getLag_name() {
        return Lag_name;
    }

    public void setLag_name(String lag_name) {
        Lag_name = lag_name;
    }

    public String getLag_code() {
        return Lag_code;
    }

    public void setLag_code(String lag_code) {
        Lag_code = lag_code;
    }


    public String getLag_de_name() {
        return Lag_de_name;
    }

    public void setLag_de_name(String lag_de_name) {
        Lag_de_name = lag_de_name;
    }


}
