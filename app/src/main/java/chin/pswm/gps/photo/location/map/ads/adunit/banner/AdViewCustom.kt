package chin.pswm.gps.photo.location.map.ads.adunit.banner

import androidx.compose.runtime.Immutable
import com.google.android.gms.ads.AdView

@Immutable
data class AdViewCustom(
    val ad: AdView,
    val name: String,
    val isMeta: Boolean
)