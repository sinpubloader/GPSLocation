package chin.pswm.gps.photo.location.map.ads.adunit.banner

import android.app.Activity
import android.content.Context
import android.graphics.Insets
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import com.google.android.gms.ads.AdSize
import timber.log.Timber

object BannerSize {

    private const val TAG = "BannerSize"

    private fun getScreenWidth(activity: Activity): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = activity.windowManager.currentWindowMetrics
            val insets: Insets = windowMetrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.width() - insets.left - insets.right
        } else {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }

    fun adSize(context: Context, adWidth: Int, type: BannerType): AdSize {
        Timber.tag(TAG).d("adSize: $adWidth")
        return when (type) {

            BannerType.BANNER_INLINE_ADAPTIVE -> {
                AdSize.getCurrentOrientationInlineAdaptiveBannerAdSize(context, adWidth)
            }

            BannerType.BANNER_INLINE_ADAPTIVE_50 -> {
                AdSize.getInlineAdaptiveBannerAdSize(adWidth, 50)
            }

            BannerType.BANNER_INLINE_ADAPTIVE_90 -> {
                AdSize.getInlineAdaptiveBannerAdSize(adWidth, 90)
            }

            BannerType.BANNER_INLINE_ADAPTIVE_120 -> {
                AdSize.getInlineAdaptiveBannerAdSize(adWidth, 120)
            }

            BannerType.BANNER_INLINE_LANDSCAPE_ADAPTIVE -> {
                AdSize.getLandscapeInlineAdaptiveBannerAdSize(context, adWidth)
            }

            else -> {
                AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth)
            }
        }
    }

    fun adSize(activity: Activity, type: BannerType): AdSize {
        val adWidthPixels = getScreenWidth(activity).toFloat()

        val density = activity.resources.displayMetrics.density
        val adWidth = (adWidthPixels / density).toInt()

        Timber.tag(TAG).d("activity adSize: $adWidth")

        return when (type) {

            BannerType.BANNER_INLINE_ADAPTIVE -> {
                AdSize.getCurrentOrientationInlineAdaptiveBannerAdSize(activity, adWidth)
            }

            BannerType.BANNER_INLINE_ADAPTIVE_50 -> {
                AdSize.getInlineAdaptiveBannerAdSize(adWidth, 50)
            }

            BannerType.BANNER_INLINE_ADAPTIVE_90 -> {
                AdSize.getInlineAdaptiveBannerAdSize(adWidth, 90)
            }

            BannerType.BANNER_INLINE_ADAPTIVE_120 -> {
                AdSize.getInlineAdaptiveBannerAdSize(adWidth, 120)
            }

            BannerType.BANNER_INLINE_LANDSCAPE_ADAPTIVE -> {
                AdSize.getLandscapeInlineAdaptiveBannerAdSize(activity, adWidth)
            }

            else -> {
                AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth)
            }
        }
    }
}