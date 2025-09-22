package chin.pswm.gps.photo.location.map.earthview.custom

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.compose.foundation.Image
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun AppIcon(
    modifier: Modifier,
    @DrawableRes res: Int,
    color: Color = Color.White
) {
    val iconNew by remember(res) {
        mutableIntStateOf(res)
    }
    Icon(
        modifier = modifier,
        painter = painterResource(iconNew),
        contentDescription = "icon",
        tint = color
    )
}

@Composable
fun AppImageBitmap(
    modifier: Modifier = Modifier,
    image: Bitmap,
    contentScale: ContentScale = ContentScale.Fit
) {
    val bitmap by remember(image) {
        mutableStateOf(image)
    }
    Image(
        painter = BitmapPainter(bitmap.asImageBitmap()),
        contentDescription = null,
        modifier = modifier,
        contentScale = contentScale
    )
}

@Composable
fun AppImage(
    modifier: Modifier = Modifier,
    @DrawableRes res: Int,
    contentScale: ContentScale = ContentScale.Fit,
    alignment: Alignment = Alignment.Center,
) {
    val imageNew by remember(res) {
        mutableIntStateOf(res)
    }

    Image(
        modifier = modifier,
        painter = painterResource(imageNew),
        alignment = alignment,
        contentDescription = "icon",
        contentScale = contentScale
    )
}

@Composable
fun AppLottie(
    modifier: Modifier = Modifier,
    @RawRes res: Int,
    restartOnPlay: Boolean = true,
    reverseOnRepeat: Boolean = true,
    speed: Float = 1f,
    iterations: Int = LottieConstants.IterateForever,
    contentScale: ContentScale = ContentScale.Fit,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(res))
    val progress by animateLottieCompositionAsState(
        composition,
        speed = speed,
        restartOnPlay = restartOnPlay,
        reverseOnRepeat = reverseOnRepeat,
        iterations = iterations,
    )
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        contentScale = contentScale,
        progress = { progress },
    )
}

enum class PlayState {
    Play,
    Pause
}

@Composable
fun AppLottie(
    modifier: Modifier = Modifier,
    @RawRes res: Int,
    playState: PlayState,
    speed: Float = 1f,
    contentScale: ContentScale = ContentScale.Fit,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(res))
    val progress = rememberLottieAnimatable()

    LaunchedEffect(playState) {
        composition?.let { comp ->
            when (playState) {
                PlayState.Play -> {
                    progress.animate(
                        composition = comp,
                        speed = speed,
                        clipSpec = LottieClipSpec.Frame(0, 30)
                    )
                }

                PlayState.Pause -> {
                    progress.animate(
                        composition = comp,
                        speed = speed,
                        clipSpec = LottieClipSpec.Frame(31, 60)
                    )
                }
            }
        }
    }

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        contentScale = contentScale,
        progress = { progress.progress },
    )
}