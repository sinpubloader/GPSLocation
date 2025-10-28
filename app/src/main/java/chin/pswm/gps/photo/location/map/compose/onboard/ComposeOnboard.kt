package chin.pswm.gps.photo.location.map.compose.onboard

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import chin.pswm.gps.photo.location.map.MyApplication
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.adunit.common.AdsStatus
import chin.pswm.gps.photo.location.map.ads.adunit.natiive.view.NativeView
import chin.pswm.gps.photo.location.map.earthview.custom.AppIcon
import chin.pswm.gps.photo.location.map.earthview.custom.AppImage
import chin.pswm.gps.photo.location.map.earthview.custom.BaseScreen
import chin.pswm.gps.photo.location.map.earthview.custom.CenterColumn
import chin.pswm.gps.photo.location.map.earthview.custom.CenterRow
import chin.pswm.gps.photo.location.map.earthview.custom.circle
import chin.pswm.gps.photo.location.map.earthview.custom.onClickNotRipple
import chin.pswm.gps.photo.location.map.ui.theme.appFont
import chin.pswm.gps.photo.location.map.ui.theme.mainBg
import chin.pswm.gps.photo.location.map.ui.theme.primary
import chin.pswm.gps.photo.location.map_debug.R
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.min

fun setMyContent(composeView: ComposeView, onFinish: () -> Unit) {
    composeView.setContent {
        ComposeOnboard(onFinish = onFinish)
    }
}

@Immutable
enum class OnboardType(
    @param:StringRes val title: Int,
    @param:DrawableRes val image: Int,
) {
    Onboard1(R.string.intro_1, R.drawable.intro_1),
    Onboard2(R.string.intro_2, R.drawable.intro_2),
    Onboard3(R.string.intro_3, R.drawable.intro_3),
    Onboard4(R.string.intro_4, R.drawable.intro_4);


    companion object {

        @Stable
        fun getEntries(): List<OnboardType> {
            return listOf(Onboard1, Onboard2, Onboard3, Onboard4)
        }
    }


}

@Preview
@Composable
fun ComposeOnboard(onFinish: () -> Unit = {}) {
    val context = LocalContext.current
    val preview = LocalInspectionMode.current
    LaunchedEffect(Unit) {
//        AdsManager.INSTANCE.run {
////            nativeSelect.loadAd(context)
////            nativeSelectAlt.loadAd(context)
//        }
    }

    var finallPage = 0
    val scope = rememberCoroutineScope()
    var onboardType by remember { mutableStateOf(OnboardType.Onboard1) }
    var currentPage by remember { mutableIntStateOf(0) }
    val adsManager = remember {
        AdsManager.INSTANCE
    }
    val adsFSStatus = adsManager.nativeFSN.statusFlow.collectAsState().value

    val hasAds by remember(adsFSStatus) {
        derivedStateOf {
            adsFSStatus != AdsStatus.NONE && adsFSStatus != AdsStatus.FAIL
        }
    }
    val pageState =
        rememberPagerState(pageCount = { OnboardType.getEntries().size + if (hasAds) 1 else 0 })

    LaunchedEffect(Unit) {
        snapshotFlow { pageState.currentPage }.collectLatest { page ->
            currentPage = page
            onboardType = if (hasAds) {
                if (page >= 2) {
                    finallPage = min(page - 1, 3)
                    OnboardType.getEntries()[min(page - 1, 3)]
                } else {
                    finallPage = page
                    OnboardType.getEntries()[page]
                }
            } else {
                finallPage = min(page, 3)
                OnboardType.getEntries()[min(page, 3)]
            }
            MyApplication.sendEvent("Onboarde_Screen", "onboard_$finallPage")
        }
    }

    BaseScreen(
        backgroundColor = mainBg,
        bottomBar = {
            if (!(currentPage == 2 && hasAds)) {
                BottomBar(
                    onboardType = onboardType,
                    onNext = {
                        if (pageState.currentPage == pageState.pageCount - 1) {
                            onFinish()
                        } else {
                            scope.launch {
                                pageState.animateScrollToPage(pageState.currentPage + 1)
                            }
                        }
                    }
                )
            }
        }
    ) {
        HorizontalPager(
            state = pageState,
            modifier = Modifier.fillMaxSize(),
            beyondViewportPageCount = pageState.pageCount,
            contentPadding = PaddingValues(with(LocalDensity.current) { 1f.toDp() }),
        ) { page ->
            when (page) {
                0 -> {
                    Box(Modifier.fillMaxSize()) {
                        AppImage(
                            res = OnboardType.Onboard1.image,
                            contentScale = ContentScale.FillWidth,
                            alignment = Alignment.TopCenter,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 24.dp)
                        )

                        CenterColumn(
                            Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                        ) {
                            Text(
                                stringResource(OnboardType.Onboard1.title),
                                style = appFont(700, 24),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )

                            NativeView(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                nativeAdUnit = AdsManager.INSTANCE.nativeOnboard1,
                                layoutConfig = "layout_onboard_1" to R.layout.native_media_ctr_bot_big_filled,
                                layoutFaceBookConfig = "layout_onboard_1_meta" to R.layout.native_media_ctr_bot_big_filled,
                            )
                        }
//                        MyApplication.sendEvent("Onboarde_Screen", "onboard_0")
                    }
                }

                1 -> {
                    Box(Modifier.fillMaxSize()) {
                        AppImage(
                            res = OnboardType.Onboard2.image,
                            contentScale = ContentScale.FillWidth,
                            alignment = Alignment.TopCenter,
                            modifier = Modifier.fillMaxSize()
                        )

                        Text(
                            stringResource(OnboardType.Onboard2.title),
                            style = appFont(700, 24),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
//                        MyApplication.sendEvent("Onboarde_Screen", "onboard_1")
                    }
                }

                2 -> {
                    if (hasAds && !preview) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {

                            NativeView(
                                modifier = Modifier.fillMaxSize(),
                                nativeAdUnit = AdsManager.INSTANCE.nativeFSN,
                                layoutConfig = "layoutFSOnboard" to R.layout.native_full_screen,
                                layoutFaceBookConfig = "layoutFSOnboardMeta" to R.layout.native_full_screen,
                            )

                            AppIcon(
                                res = R.drawable.ic_close,
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(top = 24.dp, end = 24.dp)
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .onClickNotRipple("close_ads") {
                                        scope.launch {
                                            pageState.animateScrollToPage(pageState.currentPage + 1)
                                        }
                                    }
                                    .background(Color.Gray)
                                    .padding(2.dp),
                                color = Color.White
                            )
                        }
                    } else {
                        Box(Modifier.fillMaxSize()) {
                            AppImage(
                                res = OnboardType.Onboard3.image,
                                contentScale = ContentScale.FillWidth,
                                alignment = Alignment.TopCenter,
                                modifier = Modifier.fillMaxSize()
                            )

                            Text(
                                stringResource(OnboardType.Onboard3.title),
                                style = appFont(700, 24),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                    }
//                    MyApplication.sendEvent("Onboarde_Screen", "onboard_2")
                }

                3 -> {
                    if (hasAds && !preview) {
                        Box(Modifier.fillMaxSize()) {
                            AppImage(
                                res = OnboardType.Onboard3.image,
                                contentScale = ContentScale.FillWidth,
                                alignment = Alignment.TopCenter,
                                modifier = Modifier.fillMaxSize()
                            )

                            Text(
                                stringResource(OnboardType.Onboard3.title),
                                style = appFont(700, 24),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                    } else {
                        Box(Modifier.fillMaxSize()) {
                            AppImage(
                                res = OnboardType.Onboard4.image,
                                contentScale = ContentScale.FillWidth,
                                alignment = Alignment.TopCenter,
                                modifier = Modifier.fillMaxSize()
                            )

                            CenterColumn(
                                Modifier
                                    .align(Alignment.BottomCenter)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    stringResource(OnboardType.Onboard4.title),
                                    style = appFont(700, 24),
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                )

                                NativeView(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                    nativeAdUnit = AdsManager.INSTANCE.nativeOnboard3,
                                    layoutConfig = "layout_onboard_4" to R.layout.native_media_ctr_bot_big_filled,
                                    layoutFaceBookConfig = "layout_onboard_4_meta" to R.layout.native_media_ctr_bot_big_filled,
                                )
                            }
                        }
                    }
//                    MyApplication.sendEvent("Onboarde_Screen", "onboard_3")
                }

                else -> Box(Modifier.fillMaxSize()) {
                    AppImage(
                        res = OnboardType.Onboard4.image,
                        contentScale = ContentScale.FillWidth,
                        alignment = Alignment.TopCenter,
                        modifier = Modifier.fillMaxSize()
                    )

                    CenterColumn(
                        Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                    ) {
                        Text(
                            stringResource(OnboardType.Onboard4.title),
                            style = appFont(700, 24),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )

                        NativeView(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            nativeAdUnit = AdsManager.INSTANCE.nativeOnboard3,
                            layoutConfig = "layout_onboard_4" to R.layout.native_media_ctr_bot_big_filled,
                            layoutFaceBookConfig = "layout_onboard_4_meta" to R.layout.native_media_ctr_bot_big_filled,
                        )
                    }
//                    MyApplication.sendEvent("Onboarde_Screen", "onboard_4")
                }
            }
        }
    }

//    val nativeAdUnit = remember {
//        if (index == 0) {
//            AdsManager.INSTANCE.nativeOnboard1
//        } else {
//            AdsManager.INSTANCE.nativeOnboard3
//        }
//    }
//
//    NativeView(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 14.dp)
//            .padding(bottom = 12.dp),
//        nativeAdUnit = nativeAdUnit,
//        layoutConfig = "layout_onboard" to R.layout.native_media_ctr_bot_big_filled,
//        layoutFaceBookConfig = "layout_onboard_meta" to R.layout.native_media_ctr_bot_big_filled
//    )
}

@Composable
private fun BottomBar(
    onboardType: OnboardType,
    onNext: () -> Unit
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        CenterRow(
            itemSpacing = 4.dp,
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            OnboardType.getEntries().forEach { type ->
                val isSelected by remember(onboardType) {
                    derivedStateOf {
                        onboardType == type
                    }
                }
                Box(
                    Modifier
                        .height(8.dp)
                        .width(if (isSelected) 32.dp else 8.dp)
                        .circle()
                        .background(primary.copy(if (isSelected) 1f else 0.4f))
                )
            }
        }

        Text(
            stringResource(if (onboardType == OnboardType.Onboard4) R.string.get_start else R.string.next),
            style = appFont(600, 18),
            color = primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .onClickNotRipple("next_${onboardType.ordinal}") {
                    onNext()
                }
        )
    }
}