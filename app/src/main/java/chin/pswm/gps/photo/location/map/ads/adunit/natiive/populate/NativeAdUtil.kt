package chin.pswm.gps.photo.location.map.ads.adunit.natiive.populate

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import chin.pswm.gps.photo.location.map_debug.R
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import timber.log.Timber

object NativeAdUtil {

    private const val TAG = "NativeAd"

    fun populateUnifiedNativeAdView(context: Context, nativeAd: NativeAd?, adView: NativeAdView) {
        if (nativeAd == null) {
            adView.visibility = View.GONE
            return
        }
        adView.mediaView = adView.findViewById(R.id.ad_media)
        if (adView.mediaView != null) {
            adView.mediaView!!.postDelayed({
                val sizeMin = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    120f,
                    context.resources.displayMetrics
                )
                Timber.tag(TAG).e("Native sizeMin: $sizeMin")
                Timber.tag(TAG).e("Native w/h media %s/%s : ", adView.mediaView?.width, adView.mediaView?.height)
                if (adView.mediaView!!.width < sizeMin || adView.mediaView!!.height < sizeMin) {
                    Timber.tag(TAG).e("Size media native not valid")
                }
            }, 1000)
        }
        // Set other ad assets.
        adView.headlineView = adView.findViewById(R.id.ad_headline)
        adView.bodyView = adView.findViewById(R.id.ad_body)
        adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)
        adView.iconView = adView.findViewById(R.id.ad_app_icon)
//        adView.priceView = adView.findViewById<View>(R.id.ad_price)
//        adView.starRatingView = adView.findViewById<View>(R.id.ad_stars)
        adView.advertiserView = adView.findViewById(R.id.ad_advertiser)

        // The headline is guaranteed to be in every UnifiedNativeAd.
        try {
            (adView.headlineView as TextView?)!!.text = nativeAd.headline
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        try {
            if (nativeAd.body == null) {
                adView.bodyView?.visibility = View.INVISIBLE
            } else {
                adView.bodyView?.visibility = View.VISIBLE
                (adView.bodyView as? TextView?)?.text = nativeAd.body
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            if (nativeAd.callToAction == null) {
                adView.callToActionView?.visibility = View.INVISIBLE
            } else {
                adView.callToActionView?.visibility = View.VISIBLE
                (adView.callToActionView as? TextView)?.text = nativeAd.callToAction
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            if (nativeAd.icon == null) {
                adView.iconView?.visibility = View.GONE
            } else {
                (adView.iconView as? ImageView)?.setImageDrawable(nativeAd.icon?.drawable)
                adView.iconView?.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            if (nativeAd.price == null) {
                adView.priceView?.visibility = View.INVISIBLE
            } else {
                adView.priceView?.visibility = View.VISIBLE
                (adView.priceView as? TextView)?.text = nativeAd.price
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            if (nativeAd.starRating == null) {
                adView.starRatingView?.visibility = View.INVISIBLE
            } else {
                (adView.starRatingView as? RatingBar)?.rating = nativeAd.starRating!!.toFloat()
                adView.starRatingView?.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            if (nativeAd.advertiser == null) {
                adView.advertiserView?.visibility = View.INVISIBLE
            } else {
                (adView.advertiserView as? TextView)?.text = nativeAd.advertiser
                adView.advertiserView?.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad. The SDK will populate the adView's MediaView
        // with the media content from this native ad.
        adView.setNativeAd(nativeAd)
    }

}