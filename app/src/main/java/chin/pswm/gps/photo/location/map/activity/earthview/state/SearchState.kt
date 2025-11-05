package chin.pswm.gps.photo.location.map.activity.earthview.state

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import chin.pswm.gps.photo.location.map.activity.earthview.custom.MarkLocation

@Immutable
open class SearchState {

    var searching by mutableStateOf(false)
    var text by mutableStateOf("")

    val items = mutableStateListOf<MarkLocation>()

    val hasItems by derivedStateOf { items.isNotEmpty() }

    open fun onSearchSubmit() {}
    fun clearSearchState() {
        searching = false
        text = ""
        items.clear()
    }

    open fun onPlaceSelected(item: MarkLocation) {}
}