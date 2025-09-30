package chin.pswm.gps.photo.location.map.earthview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel

class EarthViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: EarthViewViewModel = viewModel()
            EarthViewScreen(viewModel)
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
