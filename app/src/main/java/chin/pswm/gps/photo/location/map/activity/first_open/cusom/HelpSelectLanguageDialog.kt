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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import chin.pswm.gps.photo.location.map.earthview.custom.AppImage
import chin.pswm.gps.photo.location.map.earthview.custom.CenterColumn
import chin.pswm.gps.photo.location.map.earthview.custom.circle
import chin.pswm.gps.photo.location.map.earthview.custom.onClick
import chin.pswm.gps.photo.location.map.earthview.custom.round
import chin.pswm.gps.photo.location.map.ui.theme.appFont
import chin.pswm.gps.photo.location.map.ui.theme.primary
import chin.pswm.gps.photo.location.map_debug.R

@Composable
fun HelpSelectLanguageDialog(
    isShow: Boolean = true,
    onDismiss: () -> Unit = {},
    onContinue: () -> Unit = {},
) {
    if (isShow) {
        Dialog(
            onDismissRequest = {
                onDismiss()
            }
        ) {
            CenterColumn(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White, round(16.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                AppImage(
                    res = R.drawable.ic_question,
                    modifier = Modifier
                        .padding(top = 24.dp, bottom = 16.dp)
                        .fillMaxWidth(0.25f)
                        .aspectRatio(1f)
                )

                Text(
                    stringResource(R.string.help_you_choose_language),
                    style = appFont(600, 20),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Text(
                    buildAnnotatedString {
                        append(stringResource(R.string.help_you_choose_language_1))
                        append("\n")
                        append("\n")
                        append(stringResource(R.string.help_you_choose_language_2))
                        append("\n")
                        append("\n")
                        append(stringResource(R.string.help_you_choose_language_3))
                    },
                    style = appFont(400, 16),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                )

                Text(
                    stringResource(R.string.got_it),
                    style = appFont(600, 14),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 24.dp, bottom = 6.dp)
                        .fillMaxWidth()
                        .circle()
                        .background(primary)
                        .onClick("keep_language") {
                            onContinue()
                        }
                        .padding(vertical = 12.dp))
            }
        }
    }
}

@Preview
@Composable
private fun PreviewHelpSelectLanguageDialog() {
    HelpSelectLanguageDialog()
}