package chin.pswm.gps.photo.location.map.earthview.state

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Immutable
import earth.worldwind.WorldWindow
import earth.worldwind.layer.RenderableLayer
import earth.worldwind.ogc.GpkgContentManager
import java.io.File

@Immutable
open class EarthViewScreenState(context: Context) {

    val contentManager =
        GpkgContentManager(File(context.cacheDir, "cache_content.gpkg").absolutePath)
    val wwd = WorldWindow(context)
    val placeLayer = RenderableLayer()

    open fun onBack(activity: Activity) {}
}