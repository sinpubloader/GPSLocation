package chin.pswm.gps.photo.location.map.activity.first_open.data

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import chin.pswm.gps.photo.location.map_debug.R

@Immutable
enum class SelectType(
    @param:StringRes val title: Int
) {
    Item1(R.string.select_title_1),
    Item2(R.string.select_title_2),
    Item3(R.string.select_title_3),
    Item4(R.string.select_title_4),
    Item5(R.string.select_title_5),
    Item6(R.string.select_title_6);

    companion object {
        val selectedItems by lazy {
            mutableStateListOf<SelectType>()
        }
    }
}