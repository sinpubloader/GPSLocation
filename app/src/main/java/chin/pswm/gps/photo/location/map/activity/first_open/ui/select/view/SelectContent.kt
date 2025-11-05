package chin.pswm.gps.photo.location.map.activity.first_open.ui.select.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import chin.pswm.gps.photo.location.map.activity.first_open.data.SelectType
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.adunit.natiive.view.NativeView
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs
import chin.pswm.gps.photo.location.map.activity.earthview.custom.AppCard
import chin.pswm.gps.photo.location.map.activity.earthview.custom.AppImage
import chin.pswm.gps.photo.location.map.activity.earthview.custom.BaseScreen
import chin.pswm.gps.photo.location.map.activity.earthview.custom.CenterBox
import chin.pswm.gps.photo.location.map.activity.earthview.custom.CenterRow
import chin.pswm.gps.photo.location.map.activity.earthview.custom.onClick
import chin.pswm.gps.photo.location.map.activity.earthview.custom.onClickNotRipple
import chin.pswm.gps.photo.location.map.activity.earthview.custom.round
import chin.pswm.gps.photo.location.map.activity.earthview.custom.rounded
import chin.pswm.gps.photo.location.map.ui.theme.appFont
import chin.pswm.gps.photo.location.map.ui.theme.colorBlack
import chin.pswm.gps.photo.location.map.ui.theme.colorWhite
import chin.pswm.gps.photo.location.map.ui.theme.primary
import chin.pswm.gps.photo.location.map_debug.R

@Composable
fun SelectContent(
    isAlt: Boolean,
    onContinue: () -> Unit = {},
    onSelectAnyItem: () -> Unit = {},
) {
    val preview = LocalInspectionMode.current

    var useText by remember {
        mutableStateOf(false)
    }

    if (!preview) {
        val prefs: Prefs = remember {
            Prefs.INSTANCE
        }
        LaunchedEffect(Unit) {
            useText = prefs.getBoolean("select_screen_use_text_ctr", false)
        }
    }

    BaseScreen(
        topBar = {
            CenterRow(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 32.dp, bottom = 16.dp)
            ) {
                Text(
                    stringResource(R.string.which_features_do_you_like_the_most),
                    style = appFont(600, 24),
                    color = Color.Black,
                    modifier = Modifier
                        .weight(1f)
                )

            }
        },
        bottomBar = {
            Column {
                if (!useText) {
                    Text(
                        stringResource(R.string._continue),
                        style = appFont(600, 18),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .rounded(12.dp)
                            .background(primary)
                            .onClick("continue") {
                                onContinue()
                            }
                            .padding(vertical = 16.dp)
                    )
                } else {
                    CenterBox(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    ) {
                        Text(
                            stringResource(R.string._continue),
                            style = appFont(700, 16),
                            color = primary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .onClickNotRipple("Continue") {
                                    onContinue()
                                }
                        )
                    }
                }

                if (!preview) {
                    val adsManager: AdsManager = remember {
                        AdsManager.INSTANCE
                    }
                    if (!isAlt) {
                        NativeView(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp)
                                .padding(horizontal = 12.dp),
                            nativeAdUnit = adsManager.nativeSelect,
                            layoutConfig = "layout_native_select" to R.layout.native_none_media_action_small_bottom_stroke,
                            layoutFaceBookConfig = "layout_native_select_meta" to R.layout.native_none_media_action_small_bottom_stroke,
                            reloadAd = { adsManager.reloadAdsSelect }
                        )
                    } else {
                        NativeView(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp)
                                .padding(horizontal = 12.dp),
                            nativeAdUnit = adsManager.nativeSelectAlt,
                            layoutConfig = "layout_native_select_alt" to R.layout.native_none_media_action_small_bottom_stroke,
                            layoutFaceBookConfig = "layout_native_select_alt_meta" to R.layout.native_none_media_action_small_bottom_stroke,
                            reloadAd = { adsManager.reloadAdsSelect }
                        )
                    }
                }
            }
        },
        backgroundColor = colorWhite
    ) {
        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
        ) {
            items(items = SelectType.entries) { type ->
                val isSelected by remember {
                    derivedStateOf {
                        SelectType.selectedItems.contains(type)
                    }
                }

                AppCard(
                    modifier = Modifier.fillMaxWidth(),
                    cardColor = colorWhite,
                    shape = round(16.dp),
                    elevation = 1.dp
                ) {
                    CenterRow(
                        Modifier
                            .fillMaxWidth()
                            .rounded(16.dp)
                            .background(colorWhite)
                            .onClick("choose_${type.ordinal}") {
                                if (SelectType.selectedItems.contains(type)) {
                                    SelectType.selectedItems.remove(type)
                                } else SelectType.selectedItems.add(type)
                                onSelectAnyItem()
                            }
                            .padding(horizontal = 16.dp, vertical = 18.dp)
                    ) {
                        Text(
                            stringResource(type.title),
                            style = appFont(500, 16),
                            color = colorBlack,
                            modifier = Modifier
                                .weight(1f)
                        )

                        if (!isSelected) {
                            AppImage(
                                res = R.drawable.ic_uncheck,
                                modifier = Modifier.size(24.dp),
                            )
                        } else {
                            AppImage(
                                res = R.drawable.ic_checked,
                                modifier = Modifier.size(24.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSelectContent() {
    SelectContent(
        isAlt = false
    )
}