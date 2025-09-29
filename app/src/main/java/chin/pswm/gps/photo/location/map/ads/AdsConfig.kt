package chin.pswm.gps.photo.location.map.ads

import chin.pswm.gps.photo.location.map_debug.BuildConfig


object AdsConfig {
    val debug: Boolean
        get() = BuildConfig.DEBUG

    val listDeviceTest by lazy {
        listOf<String>(
            "705AF3F0DD86D021A9745CEA0413EC51",
            "E4A7D6E8BA7EEDED45712ED7130B3223"
        )
    }

    val canInitGa4: Boolean
        get() = !BuildConfig.DEBUG && false // todo: Loc
}