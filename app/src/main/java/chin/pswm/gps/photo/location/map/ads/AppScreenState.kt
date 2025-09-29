package chin.pswm.gps.photo.location.map.ads

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object AppScreenState {
    var screenCreated = 0
    var lastScreen by mutableStateOf("")
}