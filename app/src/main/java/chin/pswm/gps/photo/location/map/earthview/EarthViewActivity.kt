package chin.pswm.gps.photo.location.map.earthview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel

class EarthViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: EarthViewViewModel = viewModel()
            EarthViewScreen(viewModel)
        }
    }
}
