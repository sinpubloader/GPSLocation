package chin.pswm.gps.photo.location.map.earthview

import android.app.Activity
import android.app.Application
import androidx.lifecycle.viewModelScope
import chin.pswm.gps.photo.location.map.earthview.custom.MarkLocation

import chin.pswm.gps.photo.location.map_debug.R;
//import com.ai.panda.ads.AdsManager
//import com.ai.panda.common.allowLocation
//import com.ai.panda.domain.models.enumClass.PermissionType
//import com.ai.panda.domain.models.location.MapType
//import com.ai.panda.domain.models.location.MarkLocation
//import com.ai.panda.domain.models.location.PlacesResponseGson
//import com.ai.panda.domain.repository.AppRepository
//import com.ai.panda.ui.base.BaseViewModel
//import com.ai.panda.ui.base.bottom_sheet.MapTypeBottomSheetState
//import com.ai.panda.ui.base.bottom_sheet.PermissionBottomSheetState
//import com.ai.panda.ui.nav.AppDestination
//import com.ai.panda.ui.nav.NavigationManager
import chin.pswm.gps.photo.location.map.earthview.state.EarthViewScreenState
import chin.pswm.gps.photo.location.map.earthview.state.SearchState
import chin.pswm.gps.photo.location.map.ui.theme.MapTypeBottomSheetState
import chin.pswm.gps.photo.location.map.ui.theme.PermissionBottomSheetState
import chin.pswm.gps.photo.location.map.ui.theme.PermissionType
import chin.pswm.gps.photo.location.map.ui.theme.PlacesResponseGson
import chin.pswm.gps.photo.location.map.utils.StorageUtils.TAG
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.maps.android.compose.MapType
import earth.worldwind.geom.AltitudeMode
import earth.worldwind.geom.Angle.Companion.degrees
import earth.worldwind.geom.Location
import earth.worldwind.geom.Position
import earth.worldwind.globe.Globe
import earth.worldwind.render.image.ImageSource
import earth.worldwind.shape.Placemark
import earth.worldwind.shape.PlacemarkAttributes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import timber.log.Timber
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume

class EarthViewViewModel(
    private val application: Application,
    val appRepository: AppRepository,
    private val navigationManager: NavigationManager,
    private val adsManager: AdsManager,
) : BaseViewModel() {

    var movingCamera = false
    val screenState =
        object : EarthViewScreenState(context = application, defaultLatLng = appRepository.customState.latLng) {

            override fun onBack(activity: Activity) {
                adsManager.showInterInApp(
                    activity = activity,
                    onNextAction = {
                        navigationManager.pop(AppDestination.EarthView::class.java)
                    }
                )
            }

            override fun onMyLocation() {
                Timber.tag(TAG)
                    .d("onMyLocation: ${appRepository.customState.latitude}/${appRepository.customState.longitude}")
                if (application.allowLocation) {
                    if (!appRepository.hasLocationData || movingCamera) return
                    movingCamera = true
                    if (is2DMode) {
                        viewModelScope.launch(errorHandle {
                            movingCamera = false
                        }) {
                            cameraPositionState.animate(
                                update = CameraUpdateFactory.newLatLngZoom(
                                    LatLng(
                                        appRepository.customState.latitude,
                                        appRepository.customState.longitude
                                    ),
                                    15f
                                ),
                                durationMs = 2000
                            )
                            movingCamera = false
                        }
                    } else {
                        wwd.engine.goToAnimator.travelTime = 2000
                        wwd.engine.camera.position.altitude = 2000.0
                        wwd.engine.goToAnimator.goTo(
                            position = Location(
                                appRepository.customState.latitude.degrees,
                                appRepository.customState.longitude.degrees
                            ),
                            completionCallback = {

                            }
                        )
                        movingCamera = false
                    }
                } else {
                    permissionBottomSheetState.show(listOf(PermissionType.Location))
                }
            }

            override fun onMapType() {
                mapTypeBottomSheetState.show(mapType)
            }
        }

    val permissionBottomSheetState = object : PermissionBottomSheetState() {
        override fun allowPermissionClick(permissionType: PermissionType) {
            viewModelScope.launch {
                _permissionAction.emit(permissionType)
            }
        }
    }

    val mapTypeBottomSheetState = object : MapTypeBottomSheetState() {
        override fun onMapTypeChange(mapType: MapType) {
            setMapType(mapType)
        }
    }

    val searchState = object : SearchState() {

        override fun onPlaceSelected(item: MarkLocation) {
            Timber.tag(TAG).d("onPlaceSelected: $item")
            viewModelScope.launch(errorHandle {

            }) {
                clearSearchState()
                text = item.name
                if (screenState.is2DMode) {
                    viewModelScope.launch(errorHandle {
                        movingCamera = false
                    }) {
                        screenState.cameraPositionState.animate(
                            update = CameraUpdateFactory.newLatLngZoom(
                                LatLng(
                                    item.latitude,
                                    item.longitude
                                ),
                                15f
                            ),
                            durationMs = 2000
                        )
                        movingCamera = false
                    }
                } else {
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
        }

        override fun onSearchSubmit() {
            Timber.tag(TAG).d("onSearchSubmit: ")
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

    private val _permissionAction = MutableSharedFlow<PermissionType>()
    val permissionAction = _permissionAction.asSharedFlow()

    init {
        if (appRepository.hasLocationData) {
            updateAddMark()
        } else {
            appRepository.onUpdateLocation = { location ->
                updateAddMark()
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
            val mediaType = "application/json".toMediaType()
            val body = "{\n  \"textQuery\" : \"$query\"\n}".toRequestBody(mediaType)
            val request = Request.Builder()
                .url("https://places.googleapis.com/v1/places:searchText")
                .post(body)
                .addHeader("X-Goog-Api-Key", "AIzaSyALdyXjj-btK0KmRFEArNuVxIJmmx_6vrc")
                .addHeader(
                    "X-Goog-FieldMask",
                    "places.displayName,places.formattedAddress,places.priceLevel,places.location"
                )
                .addHeader("Content-Type", "application/json")
                .build()
            try {
                val response: Response = client.newCall(request).execute()

                fun parsePlacesWithGson(jsonString: String): List<MarkLocation> {
                    Timber.tag(TAG).d("parsePlacesWithGson: $jsonString")
                    val gson = Gson()
                    val placesResponse = gson.fromJson(jsonString, PlacesResponseGson::class.java)

                    return placesResponse.places.map { place ->
                        MarkLocation(
                            name = place.displayName.text,
                            latitude = place.location.latitude,
                            longitude = place.location.longitude,
                            address = place.address
                        )
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

    private fun updateAddMark() {
        if (!appRepository.hasLocationData) return
        screenState.wwd.mainScope.launch {
            if (screenState.placeLayer.isEmpty()) {
                val placemark = Placemark(
                    Position(
                        appRepository.customState.latitude.degrees,
                        appRepository.customState.longitude.degrees,
                        1000.0
                    ),
                    PlacemarkAttributes(),
                    application.getString(R.string.your_location)
                )

                placemark.attributes.imageSource = ImageSource.fromResource(R.drawable.ic_location_red)
                placemark.isEyeDistanceScaling = true
                placemark.eyeDistanceScalingThreshold = 15000000.0
                placemark.altitudeMode = AltitudeMode.CLAMP_TO_GROUND

                screenState.placeLayer.addRenderable(placemark)
            } else {
                val placemark = screenState.placeLayer.getRenderable(0) as Placemark
                placemark.moveTo(Globe(), placemark.position)
            }

            screenState.markLocation = MarkLocation(
                name = application.getString(R.string.your_location),
                latitude = appRepository.customState.latitude,
                longitude = appRepository.customState.longitude,
                address = ""
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        appRepository.onUpdateLocation = null
    }

    private fun setMapType(mapType: MapType) {
        this@EarthViewViewModel.screenState.mapType = mapType
    }
}