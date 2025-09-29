package chin.pswm.gps.photo.location.map.ads.adunit.reward

import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd

enum class RewardType {
    Video, Interstitial
}

data class RewardAd(
    val name: String,
    val rewardedAd: RewardedAd? = null,
    val rewardedInterstitialAd: RewardedInterstitialAd? = null
)