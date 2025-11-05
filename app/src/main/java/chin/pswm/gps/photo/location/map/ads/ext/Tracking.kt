package chin.pswm.gps.photo.location.map.ads.ext

import android.os.Bundle
import chin.pswm.gps.photo.location.map.ads.AdsConfig
import com.google.firebase.Firebase
import com.google.firebase.analytics.analytics
import timber.log.Timber


class Tracking {

    companion object {
        private val TAG: String
            get() = this::class.java.simpleName

        fun logEvent(name: String, bundle: Bundle? = null) {
            Timber.tag(TAG).d("logEvent: $name - $bundle")
            if (AdsConfig.canInitGa4) {
                try {
                    Firebase.analytics.logEvent(name, bundle)
                } catch (e: Exception) {
                    Timber.tag(TAG).e("logEvent: ${e.message}")
                }
            }
        }

        fun logEventAdsImpression(bundle: Bundle?) {
            tryWithoutCatch {
                // todo: update Firebase
                Firebase.analytics.logEvent("custom_ad_impression", bundle)
                Timber.tag(TAG).d("custom_ad_impression: bundle $bundle")
            }
        }
    }
}