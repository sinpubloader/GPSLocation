package chin.pswm.gps.photo.location.map.ads.prefs

import android.app.Application

class Prefs(private val app: Application) : PreferenceImpl(app) {

    companion object {
        lateinit var INSTANCE: Prefs
    }

    init {
        INSTANCE = this
    }

    var screenSkipInter: Long by SharedPreferenceProperty("screen_skip_inter", 2)

    var language: String by SharedPreferenceProperty("language", "")
    var firstOpen: Boolean by SharedPreferenceProperty("firstOpen", true)
    var intervalInterInapp: Long by SharedPreferenceProperty("interval_inter_inapp", 15000)
    var openTo: Int by SharedPreferenceProperty("openTo", 2)

}