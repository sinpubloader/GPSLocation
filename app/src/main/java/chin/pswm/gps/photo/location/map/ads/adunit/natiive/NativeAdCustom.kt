package chin.pswm.gps.photo.location.map.ads.adunit.natiive

import androidx.compose.runtime.Immutable
import com.google.android.gms.ads.nativead.NativeAd

@Immutable
data class NativeAdCustom(
    val ad: NativeAd,
    val name: String,
    val isMeta: Boolean
)