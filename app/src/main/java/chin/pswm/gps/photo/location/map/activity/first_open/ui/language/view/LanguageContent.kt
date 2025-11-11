package chin.pswm.gps.photo.location.map.activity.first_open.ui.language.view

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import chin.pswm.gps.photo.location.map.activity.earthview.custom.AppCard
import chin.pswm.gps.photo.location.map.activity.earthview.custom.AppIcon
import chin.pswm.gps.photo.location.map.activity.earthview.custom.AppImage
import chin.pswm.gps.photo.location.map.activity.earthview.custom.BaseScreen
import chin.pswm.gps.photo.location.map.activity.earthview.custom.CenterRow
import chin.pswm.gps.photo.location.map.activity.earthview.custom.HeaderView
import chin.pswm.gps.photo.location.map.activity.earthview.custom.onClick
import chin.pswm.gps.photo.location.map.activity.earthview.custom.onClickNotRipple
import chin.pswm.gps.photo.location.map.activity.earthview.custom.round
import chin.pswm.gps.photo.location.map.activity.earthview.custom.rounded
import chin.pswm.gps.photo.location.map.activity.first_open.data.LanguageType
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.adunit.natiive.view.NativeView
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs
import chin.pswm.gps.photo.location.map.languegess.LanguageState
import chin.pswm.gps.photo.location.map.ui.theme.appFont
import chin.pswm.gps.photo.location.map.ui.theme.colorBlack
import chin.pswm.gps.photo.location.map.ui.theme.colorWhite
import chin.pswm.gps.photo.location.map.ui.theme.primary
import chin.pswm.gps.photo.location.map_debug.R
import java.util.Locale

@Composable
fun LanguageContent(
    languageType: LanguageType,
    language: String,
    state: LazyListState,
    onBack: () -> Unit,
    onConfirm: () -> Unit,
    onLanguageChange: (String) -> Unit,
    showPoint: Boolean = false,
) {
    val preview = LocalInspectionMode.current
    var selectedLanguage by remember { mutableStateOf(language) }
    var showTouchPoint by remember { mutableStateOf(showPoint) }

    BaseScreen(
        backgroundColor = colorWhite,
        topBar = {
            when (languageType) {
                LanguageType.Normal, LanguageType.Alt -> {
                    LanguageTopBar(onConfirm = onConfirm)
                }

                LanguageType.Setting -> {
                    HeaderView(
                        title = R.string.choose_language,
                        onLeftIconClick = onBack,
                        onRightIconClick = onConfirm,
                        iconLeft = R.drawable.ic_back,
                        iconRight = R.drawable.ic_check
                    )
                }
            }
        },
        bottomBar = {
            if (!preview) {
                val adsManager: AdsManager = remember {
                    AdsManager.INSTANCE
                }
                /*when (languageType) {
                    LanguageType.Normal -> {
                        NativeView(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            nativeAdUnit = adsManager.nativeLanguage,
                            layoutConfig = "layout_native_language" to R.layout.native_media_left_filled,
                            layoutFaceBookConfig = "layout_native_language_meta" to R.layout.native_media_left_filled,
                            reloadAd = { adsManager.reloadAdsLanguage }
                        )
                    }

                    LanguageType.Alt -> {
//                        NativeView(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(10.dp),
//                            nativeAdUnit = adsManager.nativeLanguageAlt,
//                            layoutConfig = "layout_native_language_alt" to R.layout.native_media_ctr_bot_big_filled,
//                            layoutFaceBookConfig = "layout_native_language_alt_meta" to R.layout.native_media_ctr_bot_big_filled,
//                            reloadAd = { adsManager.reloadAdsLanguageAlt }
//                        )
                    }

                    LanguageType.Setting -> {
                        BannerView(
                            adUnit = BuildConfig.banner_inapp,
                            adUnitName = "banner_inapp",
                            position = "banner_position_language"
                        )
                    }
                }*/
                NativeView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    nativeAdUnit = adsManager.nativeLanguage,
                    layoutConfig = "layout_native_language" to R.layout.native_media_left_filled,
                    layoutFaceBookConfig = "layout_native_language_meta" to R.layout.native_media_left_filled,
                    reloadAd = { adsManager.reloadAdsLanguage }
                )
            }
        },
    ) {
        LazyColumn(
            state = state,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 2.dp)
        ) {
            itemsIndexed(items = LanguageState.codes) { index, code ->

                AppCard(
                    modifier = Modifier.fillMaxWidth(),
                    cardColor = colorWhite,
                    shape = round(16.dp),
                    elevation = 1.dp
                ) {

                    Box(
                        Modifier
                            .fillMaxWidth()
                            .rounded(16.dp)
                            .background(colorWhite)
                            .onClick("choose_$code") {
                                selectedLanguage = code
                                onLanguageChange(code)
                                showTouchPoint = false
                            }
                            .padding(horizontal = 16.dp)
                    ) {
                        val isSelected = code == selectedLanguage

                        val name = Locale.forLanguageTag(code).displayLanguage
                        val nameBase = Locale.forLanguageTag(code)
                            .getDisplayLanguage(Locale.forLanguageTag(code))

                        CenterRow(Modifier.fillMaxWidth()) {

                            Text(
                                buildAnnotatedString {
                                    append(name)
                                    withStyle(
                                        appFont(
                                            fontWeight = 400,
                                            fontSize = 14,
                                            color = Color.DarkGray.copy(0.7f)
                                        ).toSpanStyle()
                                    ) {
                                        append(" ($nameBase)")
                                    }
                                },
                                style = appFont(500, 16),
                                color = colorBlack,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(vertical = 18.dp)
                            )

                            AppImage(
                                res = if (isSelected) R.drawable.ic_checked else R.drawable.ic_uncheck,
                                modifier = Modifier.size(24.dp),
                            )
                        }

                        if (index == 0 && showTouchPoint) {

                            val infiniteTransition =
                                rememberInfiniteTransition(label = "infinite transition")
                            val scale by infiniteTransition.animateFloat(
                                initialValue = 0.9f,
                                targetValue = 1.1f,
                                animationSpec = infiniteRepeatable(
                                    animation = tween(durationMillis = 1500),
                                    repeatMode = RepeatMode.Reverse
                                ),
                                label = "scale"
                            )

                            AppImage(
                                res = R.drawable.ic_touch,
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(end = 40.dp)
                                    .size(32.dp)
                                    .graphicsLayer {
                                        scaleX = scale
                                        scaleY = scale
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LanguageTopBar(onConfirm: () -> Unit) {
    val preview = LocalInspectionMode.current
    CenterRow(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            stringResource(R.string.language),
            style = appFont(600, 20),
            modifier = Modifier.weight(1f)
        )

        var useIcon by remember {
            mutableStateOf(true)
        }

        if (!preview) {
            val prefs: Prefs = remember {
                Prefs.INSTANCE
            }
            LaunchedEffect(Unit) {
                useIcon = prefs.getBoolean("language_use_icon", true)
            }
        }

        if (useIcon) {
            AppIcon(
                res = R.drawable.ic_check,
                color = colorBlack,
                modifier = Modifier
                    .size(32.dp)
                    .rounded(8.dp)
                    .onClickNotRipple("confirm") {
                        onConfirm()
                    }
                    .padding(2.dp)
            )
        } else {
            Text(
                stringResource(R.string.next),
                style = appFont(600, 16),
                color = colorWhite,
                modifier = Modifier
                    .rounded(8.dp)
                    .background(primary)
                    .onClick("confirm") {
                        onConfirm()
                    }
                    .padding(vertical = 6.dp, horizontal = 10.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLanguageContent() {
    LanguageContent(
        state = rememberLazyListState(),
        languageType = LanguageType.Normal,
        language = "en",
        onBack = {

        },
        onConfirm = {

        },
        onLanguageChange = {

        }
    )
}