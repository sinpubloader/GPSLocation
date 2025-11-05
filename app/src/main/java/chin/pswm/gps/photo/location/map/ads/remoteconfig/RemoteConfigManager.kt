package chin.pswm.gps.photo.location.map.ads.remoteconfig

import chin.pswm.gps.photo.location.map.ads.AdsConfig
import chin.pswm.gps.photo.location.map.ads.ext.ITag
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class RemoteConfigManager(
    private val cache: Prefs
) : ITag {

    companion object {
        lateinit var INSTANCE: RemoteConfigManager
    }

    private val scopeIO = CoroutineScope(Dispatchers.IO)

    private val _fetchedFlow = MutableStateFlow(false)
    val fetchedStateFlow = _fetchedFlow.asStateFlow()

    init {
        INSTANCE = this
        fetchRemoteConfig()
    }

    private fun fetchRemoteConfig() {
        Timber.tag(TAG).d("fetchRemoteConfig: start fetching")
        if (AdsConfig.canInitGa4) {
//            val remoteConfig = Firebase.remoteConfig
//            val configSettings = remoteConfigSettings {
//                minimumFetchIntervalInSeconds = 3600
//            }
//            remoteConfig.setConfigSettingsAsync(configSettings)
//
//            remoteConfig.fetchAndActivate()
//                .addOnCompleteListener { task ->
//                    Timber.tag(TAG).d("fetchRemoteConfig: addOnCompleteListener")
//                    scopeIO.launch {
//                        if (task.isSuccessful) {
//                            val keys = remoteConfig.getKeysByPrefix("")
//                            for (key in keys) {
//                                val rawValue = remoteConfig.getValue(key)
//                                if (rawValue.source == FirebaseRemoteConfig.VALUE_SOURCE_REMOTE) {
//                                    cache.put(key, rawValue)
//                                    if (rawValue.isLong()) {
//                                        cache.put(key, rawValue.asLong())
//                                        Timber.tag(TAG).d("fetchRemoteConfig long: $key: ${rawValue.asLong()}")
//                                    } else if (rawValue.isDouble()) {
//                                        cache.put(key, rawValue.asDouble().toFloat())
//                                        Timber.tag(TAG).d("fetchRemoteConfig double: $key: ${rawValue.asDouble()}")
//                                    } else if (rawValue.isBoolean()) {
//                                        cache.put(key, rawValue.asBoolean())
//                                        Timber.tag(TAG).d("fetchRemoteConfig boolean: $key: ${rawValue.asBoolean()}")
//                                    } else if (rawValue.isString()) {
//                                        cache.put(key, rawValue.asString())
//                                        Timber.tag(TAG).d("fetchRemoteConfig string: $key: ${rawValue.asString()}")
//                                    } else if (rawValue.isByteArray()) {
//                                        cache.put(key, rawValue.asByteArray())
//                                        Timber.tag(TAG).d("fetchRemoteConfig bytearray: $key: ${rawValue.asByteArray()}")
//                                    }
//                                }
//                            }
//                        }
//                        _fetchedFlow.value = true
//                    }
//                }.addOnFailureListener {
//                    Timber.tag(TAG).d("fetchRemoteConfig: addOnFailureListener")
//                    _fetchedFlow.value = true
//                }.addOnSuccessListener {
//                    Timber.tag(TAG).d("fetchRemoteConfig: addOnSuccessListener")
//                }.addOnCanceledListener {
//                    Timber.tag(TAG).d("fetchRemoteConfig: addOnCanceledListener")
//                }
        } else {
            _fetchedFlow.value = true
        }
    }

//    internal fun FirebaseRemoteConfigValue.isBoolean(): Boolean = try {
//        this.asBoolean()
//        true
//    } catch (_: Exception) {
//        false
//    }
//
//    internal fun FirebaseRemoteConfigValue.isString(): Boolean = try {
//        this.asString()
//        true
//    } catch (_: Exception) {
//        false
//    }
//
//    internal fun FirebaseRemoteConfigValue.isDouble(): Boolean = try {
//        this.asDouble()
//        true
//    } catch (_: Exception) {
//        false
//    }
//
//    internal fun FirebaseRemoteConfigValue.isLong(): Boolean = try {
//        this.asLong()
//        true
//    } catch (_: Exception) {
//        false
//    }
//
//    internal fun FirebaseRemoteConfigValue.isByteArray(): Boolean = try {
//        this.asByteArray()
//        true
//    } catch (_: Exception) {
//        false
//    }

    fun tryWithoutCatch(block: () -> Unit) {
        try {
            block.invoke()
        } catch (ex: Exception) {
            Timber.tag("tryWithoutCautch").e("tryWithoutCautch: ${ex.message}")
            ex.printStackTrace()
        }
    }
}