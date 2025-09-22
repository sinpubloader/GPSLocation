package chin.pswm.gps.photo.location.map.earthview.state

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import chin.pswm.gps.photo.location.map.earthview.custom.MarkLocation
import chin.pswm.gps.photo.location.map.ui.theme.TypeMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import earth.worldwind.WorldWindow
import earth.worldwind.layer.RenderableLayer
import earth.worldwind.ogc.GpkgContentManager
import java.io.File

@Immutable
open class EarthViewScreenState(context: Context, val defaultLatLng: LatLng) {

    var is2DMode by mutableStateOf(false)
    val contentManager =
        GpkgContentManager(File(context.cacheDir, "cache_content.gpkg").absolutePath)
    val wwd = WorldWindow(context)
    val placeLayer = RenderableLayer()

    // map 2d
    val cameraPositionState: CameraPositionState = CameraPositionState().apply {
        position = CameraPosition.fromLatLngZoom(defaultLatLng, 15f)
    }
    var markLocation by mutableStateOf<MarkLocation?>(null)
    var mapType by mutableStateOf<TypeMap>(TypeMap.Normal)


    open fun onBack(activity: Activity) {}
    open fun onMyLocation() {}
    open fun onMapType() {}
}