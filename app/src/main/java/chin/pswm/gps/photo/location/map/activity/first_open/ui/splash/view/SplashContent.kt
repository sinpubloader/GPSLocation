package chin.pswm.gps.photo.location.map.activity.first_open.ui.splash.view

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SplashContent() {
    val context = LocalContext.current

//    CenterBox(Modifier.fillMaxSize()) {
//        AppImage(
//            res = R.drawable.bg_splash,
//            contentScale = ContentScale.FillBounds,
//            modifier = Modifier.fillMaxSize()
//        )
//
//        ConstraintLayout(
//            modifier = Modifier
//                .fillMaxSize()
//        ) {
//
//            val (image, text, gif) = createRefs()
//
//            AppImage(
//                res = R.drawable.img_logo,
//                contentScale = ContentScale.FillWidth,
//                modifier = Modifier
//                    .constrainAs(image) {
//                        bottom.linkTo(text.top)
//                        centerHorizontallyTo(parent)
//                    }
//                    .fillMaxWidth(280f / 720)
//            )
//
//            Text(
//                stringResource(R.string.app_name),
//                style = appFont(800, 28),
//                color = Color(0xFF0E66FF),
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .constrainAs(text) {
//                        bottom.linkTo(gif.top)
//                        centerHorizontallyTo(parent)
//                    }
//                    .padding(top = 16.dp)
//            )
//
//            val gifEnabledLoader = remember {
//                ImageLoader.Builder(context)
//                    .components {
//                        if (SDK_INT >= 28) {
//                            add(ImageDecoderDecoder.Factory())
//                        } else {
//                            add(GifDecoder.Factory())
//                        }
//                    }.build()
//            }
//
//            AsyncImage(
//                model = R.drawable.gif_splash,
//                imageLoader = gifEnabledLoader,
//                contentDescription = null,
//                modifier = Modifier
//                    .constrainAs(gif) {
//                        centerTo(parent)
//                    }
//                    .fillMaxWidth(0.1625f)
//                    .aspectRatio(1f)
//            )
//        }
//    }
}

@Preview
@Composable
private fun PreviewSplashContent() {
    SplashContent()
}