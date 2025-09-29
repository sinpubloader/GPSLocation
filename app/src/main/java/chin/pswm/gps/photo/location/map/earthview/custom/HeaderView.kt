package chin.pswm.gps.photo.location.map.earthview.custom

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import chin.pswm.gps.photo.location.map.ui.theme.appFont
import chin.pswm.gps.photo.location.map.ui.theme.colorBlack
import chin.pswm.gps.photo.location.map_debug.R

//import com.example.gps.ui.theme.colorBlack

@Preview
@Composable
fun HeaderView(
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    @StringRes title: Int = R.string.app_name,
    @DrawableRes iconLeft: Int = R.drawable.ic_launcher_foreground,
    @DrawableRes iconRight: Int = 0,
    onLeftIconClick: () -> Unit = {},
    onRightIconClick: () -> Unit = {},
) {
    CenterRow(
        modifier = modifier
            .padding(16.dp),
        itemSpacing = 12.dp
    ) {

        Box(Modifier.size(24.dp)) {
            if (iconLeft != 0)
                AppIcon(
                    res = iconLeft,
                    color = colorBlack,
                    modifier = Modifier
                        .size(24.dp)
                        .onClick("left") {
                            onLeftIconClick()
                        }
                )
        }

        Text(
            stringResource(title),
            style = appFont(600, 20),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )

        Box(Modifier.size(24.dp)) {
            if (iconRight != 0)
                AppIcon(
                    res = iconRight,
                    color = colorBlack,
                    modifier = Modifier
                        .size(24.dp)
                        .onClick("right") {
                            onRightIconClick()
                        }
                )
        }
    }
}