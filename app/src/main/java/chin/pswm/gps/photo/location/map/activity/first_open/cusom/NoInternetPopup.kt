package chin.pswm.gps.photo.location.map.activity.first_open.cusom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import chin.pswm.gps.photo.location.map.earthview.custom.AppLottie
import chin.pswm.gps.photo.location.map.earthview.custom.CenterColumn
import chin.pswm.gps.photo.location.map.earthview.custom.circle
import chin.pswm.gps.photo.location.map.earthview.custom.onClick
import chin.pswm.gps.photo.location.map.earthview.custom.round
import chin.pswm.gps.photo.location.map.ui.theme.appFont
import chin.pswm.gps.photo.location.map.ui.theme.primary
import chin.pswm.gps.photo.location.map_debug.R

@Composable
fun NoInternetPopup(
    isShow: Boolean = true,
    onGoSetting: () -> Unit = {}
) {
    if (isShow) {
        Dialog(
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
            onDismissRequest = {

            }
        ) {
            CenterColumn(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White, round(16.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                AppLottie(
                    res = R.raw.ani_no_internet,
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .aspectRatio(1f)
                )

                Text(
                    stringResource(R.string.no_internet_access),
                    style = appFont(600, 18),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Text(
                    stringResource(R.string.no_internet_access_content),
                    style = appFont(400, 14),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                )

                Text(
                    stringResource(R.string.enable_internet),
                    style = appFont(600, 14),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 24.dp, bottom = 6.dp)
                        .fillMaxWidth()
                        .circle()
                        .background(primary)
                        .onClick("enable_internet") {
                            onGoSetting()
                        }
                        .padding(vertical = 12.dp))
            }
        }
    }
}

@Preview
@Composable
private fun PreviewNoInternetPopup() {
    NoInternetPopup()
}