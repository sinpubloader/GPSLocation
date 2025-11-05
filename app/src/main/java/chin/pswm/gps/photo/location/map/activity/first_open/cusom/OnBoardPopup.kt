package chin.pswm.gps.photo.location.map.activity.first_open.cusom

import androidx.compose.foundation.background
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
import chin.pswm.gps.photo.location.map.earthview.custom.CenterColumn
import chin.pswm.gps.photo.location.map.earthview.custom.circle
import chin.pswm.gps.photo.location.map.earthview.custom.onClick
import chin.pswm.gps.photo.location.map.earthview.custom.round
import chin.pswm.gps.photo.location.map.ui.theme.appFont
import chin.pswm.gps.photo.location.map.ui.theme.primary
import chin.pswm.gps.photo.location.map_debug.R

@Composable
fun OnboardPopup(
    isShow: Boolean = true,
    onGoIt: () -> Unit = {},
) {
    if (isShow) {
        Dialog(
            onDismissRequest = {
                onGoIt()
            }
        ) {
            CenterColumn(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White, round(16.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    stringResource(R.string.onboard_popup_title),
                    style = appFont(600, 18),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                )

                Text(
                    stringResource(R.string.onboard_popup_content),
                    style = appFont(400, 13),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                )

                Text(
                    stringResource(R.string.continue_casting),
                    style = appFont(600, 14),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 24.dp, bottom = 6.dp)
                        .fillMaxWidth()
                        .circle()
                        .background(primary)
                        .onClick("got_it") {
                            onGoIt()
                        }
                        .padding(vertical = 12.dp)
                )

                Text(
                    text = stringResource(R.string.continue_viewing_how_the_app_works),
                    style = appFont(400, 12),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewOnboardPopup() {
    OnboardPopup()
}