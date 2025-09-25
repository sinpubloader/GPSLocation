package chin.pswm.gps.photo.location.map.ads.adunit

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.getSystemService
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ProcessLifecycleOwner
import chin.pswm.gps.photo.location.map.ads.ext.AppUtils
import chin.pswm.gps.photo.location.map.ads.ext.ITag
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs
import chin.pswm.gps.photo.location.map.ads.AdsConfig
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.ump.ConsentDebugSettings
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber

open class BaseAds(
    private val application: Application,
    private val prefs: Prefs,
) : ActivityLifecycleCallbacks, DefaultLifecycleObserver, ITag {

    init {
        Timber.tag(TAG).d("init: ")
        this.application.registerActivityLifecycleCallbacks(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

        initMobileAds()
    }

    private val scope = CoroutineScope(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
        Timber.tag(TAG).e("error: ${throwable.message}")
    })

    var isShowingAds: Boolean = false
    var clickedAnyAds: Boolean = false

    // for ads resume
    var enableAdResume: Boolean = false
    var disableAdResumeOneTime: Boolean = false

    private val consentInformation by lazy {
        UserMessagingPlatform.getConsentInformation(application)
    }

    private var _consentFinished = MutableStateFlow(false)
    val initFinished = _consentFinished.asStateFlow()


    private val connectivityManager = application.getSystemService<ConnectivityManager>()
    val isConnectedFlow: Flow<Boolean>
        get() = callbackFlow {
            val callback = object : NetworkCallback() {
                override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: NetworkCapabilities
                ) {
                    super.onCapabilitiesChanged(network, networkCapabilities)
                    val connected = networkCapabilities.hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_VALIDATED
                    )
                    trySend(connected)
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    trySend(false)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    trySend(false)
                }

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    trySend(true)
                }
            }

            try {
                connectivityManager?.registerDefaultNetworkCallback(callback)
            } catch (_: Exception) {
                trySend(true)
            }

            awaitClose {
                connectivityManager?.unregisterNetworkCallback(callback)
            }
        }

    val isConnected = isConnectedFlow
        .stateIn(
            scope = scope,
            started = SharingStarted.Eagerly,
            initialValue = AppUtils.isNetworkConnected(application)
        )

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {}
    override fun onActivityDestroyed(p0: Activity) {}
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityResumed(activity: Activity) {}
    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}
    override fun onActivityStarted(p0: Activity) {}
    override fun onActivityStopped(p0: Activity) {}

    private var isMobileAdsInitializeCalled = false
    private fun initMobileAds() {
        Timber.tag(TAG).d("initMobileAds: ")
        if (isMobileAdsInitializeCalled) {
            return
        }
        isMobileAdsInitializeCalled = true
        CoroutineScope(Dispatchers.IO + CoroutineExceptionHandler { _, e ->
            Timber.tag(TAG).e("initMobileAds: error ${e.message}")
        }).launch {
            MobileAds.setRequestConfiguration(
                RequestConfiguration.Builder().setTestDeviceIds(AdsConfig.listDeviceTest).build()
            )
            MobileAds.initialize(application) { initializationStatus: InitializationStatus ->
                val statusMap = initializationStatus.adapterStatusMap
                for (adapterClass in statusMap.keys) {
                    val status = statusMap[adapterClass]
                    Timber.tag(TAG).d(
                        "Adapter name: %s, Description: %s, Latency: %d",
                        adapterClass,
                        status!!.description,
                        status.latency
                    )
                }
            }
        }
    }

    private val consentSuccess: Boolean
        get() = consentInformation.canRequestAds() || !isPrivacyOptionsRequired

    private val isPrivacyOptionsRequired: Boolean
        get() = consentInformation.privacyOptionsRequirementStatus == ConsentInformation.PrivacyOptionsRequirementStatus.REQUIRED

    open val canShowAds: Boolean
        get() = consentSuccess

    fun requestUMP(activity: Activity, enableDebug: Boolean, resetData: Boolean) {
        Timber.tag(TAG).d("requestUMP: ")
        val builder = ConsentRequestParameters.Builder()
        if (enableDebug) {
            val debugSettings = ConsentDebugSettings.Builder(application).apply {
                setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
                AdsConfig.listDeviceTest.forEach {
                    addTestDeviceHashedId(it)
                }
            }.build()
            builder.setConsentDebugSettings(debugSettings)
        }
        val params = builder.setTagForUnderAgeOfConsent(false).build()

        if (resetData) {
            Timber.tag(TAG).d("requestUMP: reset")
            consentInformation.reset()
        }

        consentInformation.requestConsentInfoUpdate(activity, params, {
            if (isPrivacyOptionsRequired) {
                UserMessagingPlatform.loadAndShowConsentFormIfRequired(
                    activity
                ) { loadAndShowError ->
                    if (loadAndShowError != null) {
                        // Consent gathering failed.
                        Timber.tag(TAG).w("%s: %s", loadAndShowError.errorCode, loadAndShowError.message)
                    }
                    _consentFinished.value = true
                }
            } else {
                Timber.tag(TAG).d("requestUMP: not required")
                _consentFinished.value = true
            }
        }, { requestConsentError ->
            Timber.tag(TAG).w("%s: %s", requestConsentError.errorCode, requestConsentError.message)
            _consentFinished.value = true
        })

        if (consentInformation.canRequestAds()) {
            Timber.tag(TAG).d("requestUMP: be could show ads")
            _consentFinished.value = true
        }
    }

    // dialog open ads
    var showLoadingDialog by mutableStateOf(false)
    fun showLoading() {
        showLoadingDialog = true
    }

    fun hideLoading() {
        showLoadingDialog = false
    }

    private fun isAtLeastStarted(): Boolean {
        return ProcessLifecycleOwner.get().lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)
    }
}