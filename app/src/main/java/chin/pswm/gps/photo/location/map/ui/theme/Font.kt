package chin.pswm.gps.photo.location.map.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun appFont(
    fontWeight: Int,
    fontSize: Int,
    style: FontStyle = FontStyle.Normal,
    color: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Start,
): TextStyle {
    return TextStyle(
        fontWeight = FontWeight(fontWeight),
        fontSize = fontSize.sp.nonScaledSp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        ),
        textAlign = textAlign,
        fontStyle = style,
        color = color
    )
}

private val TextUnit.nonScaledSp
    @Composable
    get() = (this.value / LocalDensity.current.fontScale).sp