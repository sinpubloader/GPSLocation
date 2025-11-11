package chin.pswm.gps.photo.location.map.activity.earthview.view


import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LifecycleStartEffect
import chin.pswm.gps.photo.location.map.ads.adunit.banner.view.BannerView
import chin.pswm.gps.photo.location.map.activity.earthview.custom.AppIcon
import chin.pswm.gps.photo.location.map.activity.earthview.custom.AppImage
import chin.pswm.gps.photo.location.map.activity.earthview.custom.BaseScreen
import chin.pswm.gps.photo.location.map.activity.earthview.custom.CenterBox
import chin.pswm.gps.photo.location.map.activity.earthview.custom.CenterColumn
import chin.pswm.gps.photo.location.map.activity.earthview.custom.CenterRow
import chin.pswm.gps.photo.location.map.activity.earthview.custom.onClick
import chin.pswm.gps.photo.location.map.activity.earthview.custom.onClickNotRipple
import chin.pswm.gps.photo.location.map.activity.earthview.custom.round
import chin.pswm.gps.photo.location.map.activity.earthview.custom.rounded
import chin.pswm.gps.photo.location.map.activity.earthview.state.EarthViewScreenState
import chin.pswm.gps.photo.location.map.activity.earthview.state.SearchState
import chin.pswm.gps.photo.location.map.ui.theme.appFont
import chin.pswm.gps.photo.location.map.ui.theme.brushMain
import chin.pswm.gps.photo.location.map.ui.theme.colorWhite
import chin.pswm.gps.photo.location.map.ui.theme.mainBg
import chin.pswm.gps.photo.location.map.ui.theme.neutral50
import chin.pswm.gps.photo.location.map.ui.theme.neutral700
import chin.pswm.gps.photo.location.map.ui.theme.primaryColor
import chin.pswm.gps.photo.location.map_debug.BuildConfig
import chin.pswm.gps.photo.location.map_debug.R
import earth.worldwind.globe.projection.Wgs84Projection
import earth.worldwind.layer.BackgroundLayer
import earth.worldwind.layer.atmosphere.AtmosphereLayer
import earth.worldwind.layer.mercator.WebMercatorLayerFactory
import earth.worldwind.layer.starfield.StarFieldLayer
import kotlinx.coroutines.launch

@Composable
fun EarthViewContent(
    state: EarthViewScreenState,
    searchState: SearchState,
    onBack: ()-> Unit
) {
    val preview = LocalInspectionMode.current
    val context = LocalContext.current
    BaseScreen(
        topBar = {
            EarthTopBarView(onBack = onBack)
        },
        bottomBar = {
            BannerView(
                adUnit = BuildConfig.banner_inapp,
                adUnitName = "banner_inapp"
            )
        }
    ) {
        Box(Modifier.fillMaxSize()) {
            if (!preview) {
                AndroidView(
                    factory = {
                        state.wwd
                    }, modifier = Modifier.fillMaxSize()
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {

                Column(Modifier.weight(1f)) {
                    var showInfo by remember {
                        mutableStateOf(false)
                    }

                    if (showInfo) {
                        Text(
                            buildAnnotatedString {
                                append(stringResource(R.string.worldwindkotlin))
                                append("\n")
                                append(stringResource(R.string.copyright_2025_worldwindearth))
                                append("\n")
                                append(stringResource(R.string.worldwindkotlin_apache))
                            },
                            style = appFont(400, 12),
                            color = Color(0xFF414651),
                            lineHeight = 16.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 6.dp)
                                .background(Color.White, round(8.dp))
                                .padding(vertical = 8.dp, horizontal = 12.dp)
                        )

                        Box(
                            Modifier
                                .padding(start = 26.dp)
                                .size(12.dp)
                                .clip(TriangleShape())
                                .background(Color.White)
                        )
                    }

                    AppImage(
                        res = R.drawable.ic_info_circle,
                        modifier = Modifier
                            .padding(bottom = 24.dp, start = 16.dp)
                            .size(32.dp)
                            .onClick("info") {
                                showInfo = !showInfo
                            }
                    )
                }
            }

            SearchView(
                searchState = searchState
            )
        }
    }

    LaunchedEffect(Unit) {
        state.wwd.engine.globe.projection = Wgs84Projection()
        state.wwd.requestRedraw()

        state.wwd.engine.layers.apply {
            addLayer(BackgroundLayer())
            addLayer(
                WebMercatorLayerFactory.createLayer(
                    urlTemplate = "https://mt.google.com/vt/lyrs=s&x={x}&y={y}&z={z}&hl={lang}",
                    imageFormat = "image/jpeg",
                    name = "Google Satellite"
                ).apply {
                    state.wwd.mainScope.launch { configureCache(state.contentManager, "GSat") }
                })
            addLayer(StarFieldLayer())
            addLayer(AtmosphereLayer())
            addLayer(state.placeLayer)
        }
    }

    LifecycleStartEffect(Unit) {
        state.wwd.onResume()
        onStopOrDispose {
            state.wwd.onPause()
        }
    }
}

@Composable
private fun SearchView(
    searchState: SearchState = SearchState()
) {
    val focusManager = LocalFocusManager.current
    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp, horizontal = 16.dp),
    ) {
        CenterRow(
            Modifier
                .fillMaxWidth()
                .then(
                    if (searchState.hasItems) Modifier.clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                    else Modifier.rounded(28.dp)
                )
                .background(neutral50)
                .padding(12.dp)
        ) {

            BasicTextField(
                searchState.text,
                onValueChange = { text ->
                    searchState.text = text
                },
                decorationBox = {
                    if (searchState.text.isEmpty()) {
                        Text(
                            "Search", style = appFont(400, 16), color = neutral700, modifier = Modifier.fillMaxWidth()
                        )
                    }
                    it()
                },
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                    searchState.onSearchSubmit()
                }),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                maxLines = 1,
                cursorBrush = brushMain,
                textStyle = appFont(400, 16),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            )

            if (searchState.text.isNotEmpty()) {
                AppIcon(
                    res = R.drawable.ic_close, modifier = Modifier
                        .size(24.dp)
                        .onClickNotRipple("clear") {
                            searchState.searching = false
                            searchState.text = ""
                            searchState.items.clear()
                            focusManager.clearFocus()
                        })
            } else {
                AppIcon(
                    res = R.drawable.ic_search, modifier = Modifier.size(24.dp)
                )
            }
        }

        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            if (searchState.searching) {
                CircularProgressIndicator(
                    color = Color.White, modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 24.dp)
                )
            } else if (searchState.items.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomEnd = 28.dp, bottomStart = 28.dp))
                        .background(neutral50)
                        .verticalScroll(rememberScrollState()),
                ) {
                    searchState.items.forEach { item ->
                        CenterRow(
                            Modifier
                                .fillMaxWidth()
                                .onClickNotRipple("select_place") {
                                    focusManager.clearFocus()
                                    searchState.onPlaceSelected(item)
                                }
                                .padding(horizontal = 16.dp, vertical = 12.dp)) {
                            CenterColumn(Modifier.weight(1f)) {
                                Text(
                                    item.name,
                                    style = appFont(600, 16),
                                    color = Color(0xFF444444),
                                    modifier = Modifier.fillMaxWidth()
                                )
                                Text(
                                    item.address,
                                    style = appFont(400, 14),
                                    color = Color(0xFF444444),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }

                            AppIcon(
                                res = R.drawable.ic_location_red,
                                color = Color(0xff444444),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun EarthTopBarView(
    onBack: () -> Unit = {},
) {
    CenterBox(
        modifier = Modifier
            .fillMaxWidth()
            .background(mainBg)
            .padding(14.dp),
    ) {
        AppIcon(
            res = R.drawable.ic_back,
            color = colorWhite,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(24.dp)
                .onClick("left") {
                    onBack()
                })

        Text(
            stringResource(R.string.earth_view),
            style = appFont(700, 18),
            textAlign = TextAlign.Center,
            color = colorWhite,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

class TriangleShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val trianglePath = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width, 0f)
            lineTo(size.width / 2, size.height * 0.8f)
            lineTo(0f, 0f)
            close()
        }
        return Outline.Generic(trianglePath)
    }
}