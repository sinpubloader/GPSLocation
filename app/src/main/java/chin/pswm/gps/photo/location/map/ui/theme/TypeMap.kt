package chin.pswm.gps.photo.location.map.ui.theme

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import chin.pswm.gps.photo.location.map_debug.R;
import com.google.maps.android.compose.MapType

@Immutable
enum class TypeMap(
    val type: MapType,
    @param:StringRes val title: Int,
    @param:DrawableRes val icon: Int,
) {
    Normal(
        type = MapType.NORMAL,
        title = R.string.map_type_normal_content,
        icon = R.drawable.img_street
    ),
    SatelLite(
        type = MapType.SATELLITE,
        title = R.string.map_type_satellite_content,
        icon = R.drawable.img_satelline
    ),
}