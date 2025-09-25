package chin.pswm.gps.photo.location.map.ads.ext

interface ITag {
    val TAG: String
        get() = this::class.java.simpleName
}