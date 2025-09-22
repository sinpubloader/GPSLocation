package chin.pswm.gps.photo.location.map.earthview.custom

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp

@Composable
fun Modifier.rounded(radius: Dp) = this.clip(
    RoundedCornerShape(
        radius
    )
)

@Composable
fun Modifier.circle() = this.clip(CircleShape)

fun round(value: Dp) = androidx.compose.foundation.shape.RoundedCornerShape(value)