package chin.pswm.gps.photo.location.map.activity.first_open.ui.onboard.view

import android.os.Build
import androidx.activity.BackEventCompat
import androidx.activity.compose.BackHandler
import androidx.activity.compose.PredictiveBackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LifecycleResumeEffect
import chin.pswm.gps.photo.location.map.activity.first_open.cusom.OnboardPopup
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.adunit.common.AdsStatus
import chin.pswm.gps.photo.location.map.ads.adunit.natiive.view.NativeView
import chin.pswm.gps.photo.location.map.ads.ext.Tracking
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs
import chin.pswm.gps.photo.location.map.compose.onboard.OnboardType
import chin.pswm.gps.photo.location.map.earthview.custom.AppIcon
import chin.pswm.gps.photo.location.map.earthview.custom.AppImage
import chin.pswm.gps.photo.location.map.earthview.custom.BaseScreen
import chin.pswm.gps.photo.location.map.earthview.custom.CenterColumn
import chin.pswm.gps.photo.location.map.earthview.custom.CenterRow
import chin.pswm.gps.photo.location.map.earthview.custom.circle
import chin.pswm.gps.photo.location.map.earthview.custom.onClickNotRipple
import chin.pswm.gps.photo.location.map.ui.theme.appFont
import chin.pswm.gps.photo.location.map.ui.theme.colorWhite
import chin.pswm.gps.photo.location.map.ui.theme.primary
import chin.pswm.gps.photo.location.map.utils.LocalScreenTAG
import chin.pswm.gps.photo.location.map_debug.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

@Composable
fun OnboardContent(onFinish: () -> Unit) {

    val TAG = LocalScreenTAG.current
    val context = LocalContext.current
    val adsManager: AdsManager = remember {
        AdsManager.INSTANCE
    }
    val prefs: Prefs = remember {
        Prefs.INSTANCE
    }

    val scope = rememberCoroutineScope()
    val adsStatus = adsManager.nativeFSN.statusFlow.collectAsState().value
    val hasFsn by remember {
        derivedStateOf {
            adsStatus in listOf(AdsStatus.SUCCESS, AdsStatus.LOADING, AdsStatus.IMPRESSED) && adsManager.nativeFSN.enabled
        }
    }
    val pageState = rememberPagerState {
        OnboardType.entries.count() + if (hasFsn) 1 else 0
    }
    var currentPage by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        snapshotFlow { pageState.currentPage }.collectLatest { page ->
            when (page) {
                0 -> {
                    if (adsManager.reloadAdsFSN) adsManager.nativeFSN.loadAd(context)
                }

                1 -> {
                    if (adsManager.reloadAdsOnboard) adsManager.nativeOnboard1.loadAd(context)
                    if (adsManager.reloadAdsFSN) adsManager.nativeFSN.loadAd(context)
                    adsManager.nativeOnboard4.loadAd(context)
                }

                2 -> {
                    if (adsManager.reloadAdsFSN) adsManager.nativeFSN.loadAd(context)
                    adsManager.nativeOnboard4.loadAd(context)
                }

                3 -> {
                    if (hasFsn) {
                        if (adsManager.reloadAdsFSN) adsManager.nativeFSN.loadAd(context)
                        if (adsManager.reloadAdsOnboard) adsManager.nativeOnboard4.loadAd(context)
                    } else {
                        // todo: has native home?
                    }
                }

                else -> {
                    if (adsManager.reloadAdsOnboard) adsManager.nativeOnboard4.loadAd(context)
                }

            }
        }
    }

    LifecycleResumeEffect(Unit) {
        if (adsManager.clickedNativeOb1) {
            val action = prefs.getString("action_clicked_native_ob1", "next_screen")
            when (action) {
                "finish" -> onFinish()
                "clear_ads" -> adsManager.clearAdsOb1()
                "next_screen" -> scope.launch {
                    pageState.animateScrollToPage(pageState.currentPage + 1)
                }

            }
            adsManager.clickedNativeOb1 = false
        }

        if (adsManager.clickedNativeOb4) {
            val action = prefs.getString("action_clicked_native_ob4", "finish")
            when (action) {
                "finish" -> onFinish()
                "clear_ads" -> adsManager.clearAdsOb4()
                "none" -> Unit

            }
            adsManager.clickedNativeOb4 = false
        }

        if (adsManager.clickedNativeFsn) {
            val action = prefs.getString("action_clicked_native_fsn", "next_screen")
            when (action) {
                "finish" -> onFinish()
                "clear_ads" -> adsManager.clearAdsFsn()
                "next_screen" -> scope.launch {
                    pageState.animateScrollToPage(pageState.currentPage + 1)
                }

            }
            adsManager.clickedNativeFsn = false
        }

        onPauseOrDispose {

        }
    }


    BaseScreen(
        backgroundColor = colorWhite,
        bottomBar = {
            BottomBar(
                onboardType = OnboardType.Onboard1,
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
                                color = Color.Black,
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
                                layoutConfig = "layout_onboard_1" to R.layout.native_media_ctr_bot_small_filled,
                                layoutFaceBookConfig = "layout_onboard_1_meta" to R.layout.native_media_ctr_bot_small_filled,
                            )
                        }
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
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }

                2 -> {
                    if (hasFsn) {
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
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                    }
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
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )

                        NativeView(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            nativeAdUnit = AdsManager.INSTANCE.nativeOnboard4,
                            layoutConfig = "layout_onboard_4" to R.layout.native_media_ctr_bot_small_filled,
                            layoutFaceBookConfig = "layout_onboard_4_meta" to R.layout.native_media_ctr_bot_small_filled,
                        )
                    }
                }
            }
        }
    }

    var showOnBoardPopup by remember {
        mutableStateOf(false)
    }

    BackHandler(Build.VERSION.SDK_INT <= Build.VERSION_CODES.TIRAMISU) {
        Tracking.logEvent("${TAG}_back")

        if (!showOnBoardPopup) {
            Tracking.logEvent(TAG + "_back_pressed")
        }
        showOnBoardPopup = true
    }

    var swipeEdge by remember {
        mutableIntStateOf(0)
    }

    PredictiveBackHandler(Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) { progress: Flow<BackEventCompat> ->
        try {
            progress.collectLatest { backEvent ->
                swipeEdge = backEvent.swipeEdge
            }
            if (swipeEdge == 0) {
                Tracking.logEvent("${TAG}_back")
                if (!showOnBoardPopup) {
                    Tracking.logEvent(TAG + "_back_pressed")
                }
                showOnBoardPopup = true
            } else {
                if (pageState.currentPage == pageState.pageCount - 1) {
                    onFinish()
                } else {
                    scope.launch {
                        pageState.animateScrollToPage(pageState.currentPage + 1)
                    }
                }
            }
        } catch (_: CancellationException) {

        }
    }

    OnboardPopup(
        isShow = showOnBoardPopup,
        onGoIt = {
            showOnBoardPopup = false
        }
    )
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
        // Center indicators (dots)
        CenterRow(
            itemSpacing = 4.dp,
            modifier = Modifier.align(Alignment.Center)
        ) {
            OnboardType.getEntries().forEach { type ->
                val isSelected by remember(onboardType) {
                    derivedStateOf { onboardType == type }
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

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clip(RoundedCornerShape(50))
                .background(primary)
                .clickable { onNext() }
                .padding(horizontal = 24.dp, vertical = 10.dp)
        ) {
            Text(
                text = stringResource(
                    if (onboardType == OnboardType.Onboard4)
                        R.string.get_start
                    else
                        R.string.next
                ),
                style = appFont(600, 18),
                color = Color.White,              // white text like your image
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}