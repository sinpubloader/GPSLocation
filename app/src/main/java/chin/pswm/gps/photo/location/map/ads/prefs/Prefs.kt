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

}