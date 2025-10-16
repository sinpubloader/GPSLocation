package chin.pswm.gps.photo.location.map.utils

import androidx.compose.runtime.staticCompositionLocalOf
import kotlin.jvm.java

val LocalScreenTAG = staticCompositionLocalOf {
    ""
}

interface ITag {
    val TAG: String
        get() = this::class.java.simpleName
}