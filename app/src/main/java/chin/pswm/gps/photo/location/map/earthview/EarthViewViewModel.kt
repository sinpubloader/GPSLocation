package chin.pswm.gps.photo.location.map.earthview

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import chin.pswm.gps.photo.location.map.earthview.custom.MarkLocation
import chin.pswm.gps.photo.location.map.earthview.state.EarthViewScreenState
import chin.pswm.gps.photo.location.map.earthview.state.SearchState
import chin.pswm.gps.photo.location.map.ui.theme.PlacesResponseGson
import chin.pswm.gps.photo.location.map_debug.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import earth.worldwind.geom.AltitudeMode
import earth.worldwind.geom.Angle.Companion.degrees
import earth.worldwind.geom.Location
import earth.worldwind.geom.Position
import earth.worldwind.render.image.ImageSource
import earth.worldwind.shape.Placemark
import earth.worldwind.shape.PlacemarkAttributes
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import timber.log.Timber
import java.util.Locale
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume

class EarthViewViewModel(
    private val application: Application,
) : AndroidViewModel(application) {
    private val TAG: String
        get() = this::class.java.simpleName

    private val app = getApplication<Application>()

    var movingCamera = false
    val screenState =
        object : EarthViewScreenState(context = application) {

            override fun onBack(activity: Activity) {
                activity.finish()
            }
        }

    val searchState = object : SearchState() {

        override fun onPlaceSelected(item: MarkLocation) {
            Timber.tag(TAG).d("onPlaceSelected: $item")
            viewModelScope.launch(errorHandle {

            }) {
                clearSearchState()
                text = item.name
                val placemark = Placemark(
                    Position(
                        item.latitude.degrees,
                        item.longitude.degrees,
                        1000.0
                    ),
                    PlacemarkAttributes(),
                    item.name
                )

                placemark.attributes.imageSource = ImageSource.fromResource(R.drawable.ic_location_red)
                placemark.isEyeDistanceScaling = true
                placemark.eyeDistanceScalingThreshold = 15000000.0
                placemark.altitudeMode = AltitudeMode.CLAMP_TO_GROUND
                screenState.placeLayer.addRenderable(placemark)

                screenState.run {
                    wwd.engine.goToAnimator.travelTime = 2000
                    wwd.engine.camera.position.altitude = 2000.0
                    wwd.engine.goToAnimator.goTo(
                        position = Location(
                            latitude = item.latitude.degrees,
                            longitude = item.longitude.degrees
                        ),
                        completionCallback = {

                        }
                    )
                }
                movingCamera = false
            }
        }

        override fun onSearchSubmit() {
            Timber.tag(TAG).d("onSearchSubmit: ")
            Log.d(TAG, "onSearchSubmit: ")
            if (text.isEmpty() || text.isBlank()) return
            searching = true
            viewModelScope.launch {
                val results = withContext(Dispatchers.IO) {
                    searchPlace(text)
                }
                withContext(Dispatchers.Main) {
                    items.clear()
                    items.addAll(results)
                }
                Timber.tag(TAG).d("onSearchSubmit: ${results.size} items")
                searching = false
            }
        }
    }

    private suspend fun searchPlace(query: String): List<MarkLocation> {
        return suspendCancellableCoroutine { continuation ->
            val client = OkHttpClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .callTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()
            val request = Request.Builder()
                .url("https://nominatim.openstreetmap.org/search?q=${query}&format=json&accept-language=${Locale.getDefault().language}")
                .header("User-Agent", "GPS Earthview/1.0")
                .build()
            try {
                val response: Response = client.newCall(request).execute()

                fun parsePlacesWithGson(jsonString: String): List<MarkLocation> {
                    Timber.tag(TAG).d("parsePlacesWithGson: $jsonString")

                    return try {
                        val gson = Gson()
                        val itemType = object : TypeToken<List<MarkLocation>>() {}.type
                        gson.fromJson(jsonString, itemType)
                    } catch (_: Exception) {
                        listOf()
                    }
                }

                val json = response.body?.string()
                if (json.isNullOrEmpty()) continuation.resume(listOf())
                else {
                    val items = parsePlacesWithGson(json)
                    continuation.resume(items)
                }
            } catch (e: Exception) {
                Timber.tag(TAG).e(e, "searchPlace: $query failed")
                continuation.resume(listOf())
            }
            continuation.invokeOnCancellation {
                Timber.tag(TAG).w("searchPlace: $query canceled: ${it?.message}")
            }
        }
    }

    fun errorHandle(exception: (Throwable) -> Unit) = CoroutineExceptionHandler { _, throwable ->
        exception.invoke(throwable)
    }
}