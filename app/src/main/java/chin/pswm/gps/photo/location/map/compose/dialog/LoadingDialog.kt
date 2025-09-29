package chin.pswm.gps.photo.location.map.compose.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
import chin.pswm.gps.photo.location.map.earthview.custom.CenterColumn
import chin.pswm.gps.photo.location.map.earthview.custom.rounded
import chin.pswm.gps.photo.location.map.ui.theme.appFont
import chin.pswm.gps.photo.location.map.ui.theme.colorWhite
import chin.pswm.gps.photo.location.map_debug.R

@Composable
fun LoadingDialog(
    isLoading: Boolean
) {
    if (isLoading) {
        Dialog(
            onDismissRequest = {

            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            CenterColumn(
                Modifier
                    .fillMaxWidth()
                    .rounded(16.dp)
                    .background(colorWhite)
                    .padding(16.dp),
                itemSpacing = 16.dp
            ) {
                CircularProgressIndicator()

                Text(
                    stringResource(R.string.loading),
                    style = appFont(600, 22),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    LoadingDialog(true)
}