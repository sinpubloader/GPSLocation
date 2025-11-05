package chin.pswm.gps.photo.location.map.activity.earthview.custom

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.ShimmerTheme
import com.valentinilk.shimmer.defaultShimmerTheme
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

@Composable
inline fun SimpleShimmer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    Box(
        modifier = modifier
            .shimmer()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

fun Modifier.shimmerApp(
    enable: Boolean = true,
    duration: Int = 800,
    delay: Int = 1500
): Modifier = composed {
    val shimmer = rememberShimmer(
        shimmerBounds = ShimmerBounds.View,
        theme = ShimmerTheme(
            animationSpec = infiniteRepeatable(
                animation = tween(
                    duration,
                    easing = LinearEasing,
                    delayMillis = delay,
                ),
                repeatMode = RepeatMode.Restart,
            ),
            blendMode = BlendMode.DstIn,
            rotation = 15.0f,
            shaderColors = listOf(
                Color.Unspecified.copy(alpha = 1.00f),
                Color.Unspecified.copy(alpha = 0.25f),
                Color.Unspecified.copy(alpha = 1.00f),
            ),
            shaderColorStops = listOf(
                0.0f,
                0.5f,
                1.0f,
            ),
            shimmerWidth = 400.dp,
        ),
    )
    if (enable) shimmer(customShimmer = shimmer)
    else this@shimmerApp
}

val HighLightShimmerTheme = defaultShimmerTheme.copy(
    animationSpec = infiniteRepeatable(
        animation = tween(
            durationMillis = 1_000,
            delayMillis = 1_500,
            easing = LinearEasing,
        ),
        repeatMode = RepeatMode.Restart,
    ),
    blendMode = BlendMode.Hardlight,
    rotation = 15f,
    shaderColors = listOf(
        Color.White.copy(alpha = 0.0f),
        Color.White.copy(alpha = 0.6f),
        Color.White.copy(alpha = 0.0f),
    ),
    shaderColorStops = null,
    shimmerWidth = 300.dp,
)

fun highLightShimmerTheme(duration: Int, delay: Int) = defaultShimmerTheme.copy(
    animationSpec = infiniteRepeatable(
        animation = tween(
            durationMillis = duration,
            delayMillis = delay,
            easing = LinearEasing,
        ),
        repeatMode = RepeatMode.Restart,
    ),
    blendMode = BlendMode.Hardlight,
    rotation = 15f,
    shaderColors = listOf(
        Color.White.copy(alpha = 0.0f),
        Color.White.copy(alpha = 0.6f),
        Color.White.copy(alpha = 0.0f),
    ),
    shaderColorStops = null,
    shimmerWidth = 300.dp,
)

fun Modifier.highlightShimmer(theme: ShimmerTheme = HighLightShimmerTheme) =
    composed { shimmer(rememberShimmer(shimmerBounds = ShimmerBounds.View, theme = theme)) }