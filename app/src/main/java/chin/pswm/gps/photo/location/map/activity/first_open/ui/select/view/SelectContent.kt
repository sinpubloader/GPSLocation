package chin.pswm.gps.photo.location.map.activity.first_open.ui.select.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs

@Composable
fun SelectContent(
    isAlt: Boolean,
    onContinue: () -> Unit = {},
    onSelectAnyItem: () -> Unit = {},
) {
    val preview = LocalInspectionMode.current

    var useText by remember {
        mutableStateOf(true)
    }

    if (!preview) {
        val prefs: Prefs = remember {
            Prefs.INSTANCE
        }
        LaunchedEffect(Unit) {
            useText = prefs.getBoolean("select_screen_use_text_ctr", true)
        }
    }

//    BaseScreen(
//        topBar = {
//            CenterRow(
//                Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp, vertical = 16.dp)
//            ) {
//                Text(
//                    stringResource(R.string.which_features_do_you_like_the_most),
//                    style = appFont(700, 15),
//                    color = Color.Black,
//                    textAlign = if (useText) null else TextAlign.Center,
//                    modifier = Modifier
//                        .weight(1f)
//                )
//
//                if (useText) {
//                    Text(
//                        stringResource(R.string.next),
//                        style = appFont(700, 15),
//                        color = primary,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier
//                            .onClickNotRipple("Continue") {
//                                onContinue()
//                            }
//                    )
//                }
//            }
//        },
//        bottomBar = {
//            Column {
//                if (!useText) {
//                    ActionButton(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 16.dp, vertical = 12.dp),
//                        title = R.string._continue,
//                        background = primary,
//                        event = "Continue",
//                        onClick = {
//                            onContinue()
//                        }
//                    )
//                }
//
//                if (!preview) {
//                    val adsManager: AdsManager = koinInject()
//                    if (!isAlt) {
//                        NativeView(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(bottom = 10.dp)
//                                .padding(horizontal = 12.dp),
//                            nativeAdUnit = adsManager.nativeSelect,
//                            layoutConfig = "layout_native_select" to R.layout.native_none_media_action_small_bottom_stroke,
//                            layoutFaceBookConfig = "layout_native_select_meta" to R.layout.native_none_media_action_small_bottom_stroke,
//                            reloadAd = { adsManager.reloadAdsSelect }
//                        )
//                    } else {
//                        NativeView(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(bottom = 10.dp)
//                                .padding(horizontal = 12.dp),
//                            nativeAdUnit = adsManager.nativeSelectAlt,
//                            layoutConfig = "layout_native_select_alt" to R.layout.native_none_media_action_small_bottom_stroke,
//                            layoutFaceBookConfig = "layout_native_select_alt_meta" to R.layout.native_none_media_action_small_bottom_stroke,
//                            reloadAd = { adsManager.reloadAdsSelect }
//                        )
//                    }
//                }
//            }
//        },
//        backgroundColor = colorWhite
//    ) {
//        LazyVerticalGrid(
//            state = SelectType.state,
//            columns = GridCells.Fixed(2),
//            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp),
//            modifier = Modifier
//                .fillMaxSize(),
//            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
//            horizontalArrangement = Arrangement.spacedBy(16.dp),
//        ) {
//            items(
//                items = SelectType.entries.toPersistentList(),
//            ) { item ->
//                val isSelected by remember {
//                    derivedStateOf {
//                        item in SelectType.selectedItems
//                    }
//                }
//
//                Box(
//                    Modifier
//                        .fillMaxWidth()
//                        .then(
//                            if (isSelected) border(1.2.dp, primary, round(8.dp))
//                            else Modifier
//                        )
//                ) {
//                    AppImage(
//                        res = item.icon,
//                        contentScale = ContentScale.FillWidth,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .rounded(8.dp)
//                            .onClick("click_${item.ordinal}") {
//                                if (SelectType.selectedItems.contains(item)) SelectType.selectedItems.remove(item)
//                                else SelectType.selectedItems.add(item)
//                                onSelectAnyItem()
//                            }
//                    )
//
//                    if (isSelected) {
//                        AppImage(
//                            res = R.drawable.ic_checked,
//                            modifier = Modifier
//                                .padding(10.dp)
//                                .align(Alignment.TopEnd)
//                                .size(24.dp)
//                        )
//                    }
//                }
//            }
//        }
//    }
}

@Preview
@Composable
private fun PreviewSelectContent() {
    SelectContent(
        isAlt = false
    )
}