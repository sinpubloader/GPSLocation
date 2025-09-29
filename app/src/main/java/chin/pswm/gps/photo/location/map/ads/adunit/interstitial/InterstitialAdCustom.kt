package chin.pswm.gps.photo.location.map.ads.adunit.interstitial

import com.google.android.gms.ads.interstitial.InterstitialAd

data class InterstitialAdCustom(
    val ad: InterstitialAd,
    val name: String,
    val isMeta: Boolean
)