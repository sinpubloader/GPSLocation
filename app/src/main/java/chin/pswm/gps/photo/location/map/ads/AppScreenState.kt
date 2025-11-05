package chin.pswm.gps.photo.location.map.ads

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.lifecycle.compose.LifecycleResumeEffect
import chin.pswm.gps.photo.location.map.ads.ext.Tracking
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber

object AppScreenState {
    var screenCreated = 0
    var lastScreen by mutableStateOf("")

    var requestingNotificationPermission = MutableStateFlow(false)
}

@Composable
fun TrackingScreen(screen: String) {
    val localInspectionMode = LocalInspectionMode.current

    LifecycleResumeEffect(Unit) {
        if (!localInspectionMode) {
            Firebase.analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
                param(FirebaseAnalytics.Param.SCREEN_NAME, screen)
            }
        }
        if (AppScreenState.lastScreen != screen && screen !in listOf("Home", "Library")) {
            Tracking.logEvent(screen)
            AppScreenState.lastScreen = screen
            AppScreenState.screenCreated += 1
            Timber.tag(screen).d("TrackingScreen: $screen - screenCreated ${AppScreenState.screenCreated}")
        }
        onPauseOrDispose {

        }
    }
}