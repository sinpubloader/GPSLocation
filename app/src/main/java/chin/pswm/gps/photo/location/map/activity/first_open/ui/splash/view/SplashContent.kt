package chin.pswm.gps.photo.location.map.activity.first_open.ui.splash.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import chin.pswm.gps.photo.location.map.earthview.custom.AppImage
import chin.pswm.gps.photo.location.map.earthview.custom.CenterBox
import chin.pswm.gps.photo.location.map.earthview.custom.CenterColumn
import chin.pswm.gps.photo.location.map.ui.theme.appFont
import chin.pswm.gps.photo.location.map.ui.theme.colorWhite
import chin.pswm.gps.photo.location.map_debug.R

@Composable
fun SplashContent() {

    CenterBox(Modifier.fillMaxSize()) {
        AppImage(
            res = R.drawable.splash,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        CenterColumn(Modifier.fillMaxSize()) {

            AppImage(
                res = R.drawable.splash_logo,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth(0.38f)
            )

            Text(
                stringResource(R.string.app_name),
                style = appFont(600, 22),
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = 24.dp)
            )
        }

        CircularProgressIndicator(
            color = colorWhite,
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview
@Composable
private fun PreviewSplashContent() {
    SplashContent()
}