package chin.pswm.gps.photo.location.map.compose.uninstall.view

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import chin.pswm.gps.photo.location.map.activity.earthview.custom.AppImage
import chin.pswm.gps.photo.location.map.activity.earthview.custom.BaseScreen
import chin.pswm.gps.photo.location.map.activity.earthview.custom.CenterColumn
import chin.pswm.gps.photo.location.map.activity.earthview.custom.CenterRow
import chin.pswm.gps.photo.location.map.activity.earthview.custom.onClickNotRipple
import chin.pswm.gps.photo.location.map.activity.earthview.custom.rounded
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.adunit.natiive.view.NativeView
import chin.pswm.gps.photo.location.map.ui.theme.appFont
import chin.pswm.gps.photo.location.map_debug.R

@Composable
fun UninstallExploreFeatureContent(
    onHome: () -> Unit = {},
    onVote: () -> Unit = {},
    onGpsCamera: () -> Unit = {},
    onMapView: () -> Unit = {},
    onPhotoGrid: () -> Unit = {}
) {
    val context = LocalContext.current
    val featureHighlights = listOf(
        UninstallFeatureHighlight(
            icon = R.drawable.img_cameraa,
            title = R.string.uninstall_feature_gps_camera,
            description = R.string.uninstall_feature_gps_camera_desc,
            analyticsKey = "feature_gps_camera"
        ),
        UninstallFeatureHighlight(
            icon = R.drawable.icn_earth,
            title = R.string.uninstall_feature_map_view,
            description = R.string.uninstall_feature_map_view_desc,
            analyticsKey = "feature_map_view"
        ),
        UninstallFeatureHighlight(
            icon = R.drawable.photo_grid,
            title = R.string.uninstall_feature_photo_grid,
            description = R.string.uninstall_feature_photo_grid_desc,
            analyticsKey = "feature_photo_grid"
        ),
    )

    BaseScreen(
        topBar = {
            CenterRow(
                Modifier
                    .fillMaxWidth()
            ) {
                val adsManager: AdsManager = remember {
                    AdsManager.INSTANCE
                }
                NativeView(
                    modifier = Modifier
                        .fillMaxWidth(),
                    nativeAdUnit = adsManager.nativeUninstall,
                    layoutConfig = "native_uninstall" to R.layout.native_none_media_action_small_bottom_stroke,
                    layoutFaceBookConfig = "native_uninstall_meta" to R.layout.native_none_media_action_small_bottom_stroke,
                )

            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AppImage(
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxSize(),
                res = R.drawable.intro_4
            )

            CenterColumn(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter))
            {
                Text(
                    text = buildAnnotatedString {
                        append(context.getString(R.string.uninstall_keep_title_prefix) + " ")
                        withStyle(
                            appFont(500, 23).copy(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFFB786C3),
                                        Color(0xFFDCB1AA),
                                        Color(0xFFF3CC9A),
                                        Color(0xFFF9C185),
                                        Color(0xFFEB8969),
                                    )
                                )
                            ).toSpanStyle()
                        ) {
                            append(stringResource(R.string.uninstall_keep_title_highlight))
                        }
                    },
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = appFont(600, 24, color = Color.Black, textAlign = TextAlign.Center),
                )
                Text(
                    text = stringResource(R.string.uninstall_keep_subtitle),
                    modifier = Modifier.padding(top = 12.dp, start = 24.dp, end = 24.dp),
                    style = appFont(
                        400,
                        16,
                        color = Color.Black.copy(alpha = 0.75f),
                        textAlign = TextAlign.Center
                    )
                )

                CenterRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    itemSpacing = 8.dp
                ) {
                    featureHighlights.forEachIndexed { index, feature ->

                        FeatureHighlightItem(
                            feature = feature,
                            modifier = Modifier
                                .weight(1f)                                 // 1) apply weight first (RowScope)
                                .padding(horizontal = 8.dp, vertical = 12.dp)
                                .clickable(                                 // 2) clickable last so it covers padding area
                                    role = Role.Button,
                                    onClick = {
                                        // debug log to verify which box was clicked
                                        when (index) {
                                            0 -> {
                                                // first box clicked
                                                onGpsCamera()
                                            }

                                            1 -> {
                                                // second box clicked
                                                onMapView()
                                            }

                                            2 -> {
                                                onPhotoGrid()
                                            }
                                        }
                                    }
                                )
                        )
                    }
                }

                Text(
                    text = stringResource(R.string.uninstall_go_home),
                    style = appFont(500, 18, color = Color.White, textAlign = TextAlign.Center),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .rounded(20.dp)
                        .background(Color.Black)
                        .padding(vertical = 12.dp)
                        .onClickNotRipple("go_home") {
                            onHome()
                        }
                )

                Text(
                    text = stringResource(R.string.uninstall_still_leave),
                    style = appFont(
                        400,
                        14,
                        color = Color.Black.copy(0.5f),
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(vertical = 18.dp)
                        .onClickNotRipple("still_uninstall") {
                            onVote()
                        }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewUninstallExploreFeatureContent() {
    UninstallExploreFeatureContent()
}

@Composable
private fun FeatureHighlightItem(
    feature: UninstallFeatureHighlight,
    modifier: Modifier = Modifier
) {
    CenterColumn(
        modifier = modifier,
        itemSpacing = 6.dp
    ) {
        AppImage(
            modifier = Modifier.size(36.dp),
            res = feature.icon,
        )
        Text(
            text = stringResource(feature.title),
            style = appFont(600, 14, color = Color.Black, textAlign = TextAlign.Center),
            modifier = Modifier.padding(top = 6.dp)
        )
        Text(
            text = stringResource(feature.description),
            style = appFont(
                400,
                12,
                color = Color.Black.copy(alpha = 0.65f),
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}

private data class UninstallFeatureHighlight(
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
    @StringRes val description: Int,
    val analyticsKey: String
)