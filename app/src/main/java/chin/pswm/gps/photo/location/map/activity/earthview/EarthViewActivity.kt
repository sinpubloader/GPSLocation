package chin.pswm.gps.photo.location.map.activity.earthview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import chin.pswm.gps.photo.location.map.ads.AppScreenState
import chin.pswm.gps.photo.location.map.ads.ext.ITag
import chin.pswm.gps.photo.location.map.ads.ext.Tracking
import timber.log.Timber

class EarthViewActivity : ComponentActivity(), ITag {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: EarthViewViewModel = viewModel()
            EarthViewScreen(viewModel)
        }

        if (AppScreenState.lastScreen != TAG ) {
            Tracking.logEvent(TAG)
            AppScreenState.lastScreen = TAG
            AppScreenState.screenCreated += 1
            Timber.tag(TAG).d("TrackingScreen: $TAG - screenCreated ${AppScreenState.screenCreated}")
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemNavigationBar()
    }

    private fun hideSystemNavigationBar() {
        try {
            val window = this.window
            WindowCompat.setDecorFitsSystemWindows(window, true)
            val windowCompat = WindowCompat.getInsetsController(window, window.decorView)
            windowCompat.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            windowCompat.isAppearanceLightNavigationBars = false
            windowCompat.hide(WindowInsetsCompat.Type.navigationBars())
        } catch (e: Exception) {
        }
    }
}
