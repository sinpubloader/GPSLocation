package chin.pswm.gps.photo.location.map.earthview.custom

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class MarkLocation(
    @SerializedName("name")
    val name: String,

    @SerializedName("display_name")
    val address: String,

    @SerializedName("lat")
    val latitude: Double,

    @SerializedName("lon")
    val longitude: Double,

    @SerializedName("place_id")
    val placeId: String? = null
)