package chin.pswm.gps.photo.location.map.ui.theme

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class SearchLocation(
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
)

data class PlacesResponseGson(
    @SerializedName("places")
    val places: List<PlaceGson> = listOf()
)

data class PlaceGson(
    @SerializedName("formattedAddress")
    val address: String,

    @SerializedName("location")
    val location: LocationGson,

    @SerializedName("displayName")
    val displayName: DisplayNameGson
)

data class LocationGson(
    @SerializedName("latitude")
    val latitude: Double,

    @SerializedName("longitude")
    val longitude: Double
)

data class DisplayNameGson(
    @SerializedName("text")
    val text: String,

    @SerializedName("languageCode")
    val languageCode: String
)