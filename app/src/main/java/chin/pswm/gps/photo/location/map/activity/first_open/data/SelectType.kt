package chin.pswm.gps.photo.location.map.activity.first_open.data

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import chin.pswm.gps.photo.location.map_debug.R

@Immutable
enum class SelectType(
    @param:StringRes val title: Int
) {
    Item1(R.string.earth_view),
    Item2(R.string.timestamp_camera),
    Item3(R.string.photo_grid),
    Item4(R.string.route_planner),
    Item5(R.string.compass),
    Item6(R.string.gpl7);

    companion object {
        val selectedItems by lazy {
            mutableStateListOf<SelectType>()
        }
    }
}