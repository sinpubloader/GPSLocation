package chin.pswm.gps.photo.location.map.ads.adunit

import androidx.compose.runtime.Stable
import chin.pswm.gps.photo.location.map.ads.adunit.common.AdsStatus
import chin.pswm.gps.photo.location.map.ads.AdsManager
import chin.pswm.gps.photo.location.map.ads.adjust.AdjustManager
import chin.pswm.gps.photo.location.map.ads.prefs.Prefs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Base64

val String.decodeAdUnit: String
    get() = try {
        String(Base64.getDecoder().decode(this))
    } catch (ex: Exception) {
        this
    }

data class AdDataConfig(
    val defaultId: String,
    val name: String,
    val defaultEnable: Boolean,
) {
    val enable: Boolean
        get() {
            val prefs: Prefs = Prefs.INSTANCE
            return prefs.getBoolean(this.name, defaultEnable)
        }

    val id: String
        get() = try {
            val prefs: Prefs = Prefs.INSTANCE
            val ad = prefs.getString(this.name + "_adunit", "")
            ad.ifEmpty { defaultId }
        } catch (_: Exception) {
            defaultId
        }
}

abstract class AdUnit<T>(
    val listAdData: List<AdDataConfig>,
) {
    protected val TAG: String = this::class.java.simpleName

    val adsManager: AdsManager = AdsManager.INSTANCE
    val adjustManager: AdjustManager = AdjustManager.INSTANCE
    val prefs: Prefs = Prefs.INSTANCE

    var adData: T? = null

    var adLoadedTime: Long = 0L
    var adImpressionTime: Long = 0L

    protected var _statusFlow = MutableStateFlow(AdsStatus.NONE)
    val statusFlow = _statusFlow.asStateFlow()

    val status: AdsStatus
        get() = statusFlow.value

    @Stable
    val enabled: Boolean
        get() = listAdData.any { it.enable }

    fun impressionAtLeast(time: Long = 1000) = System.currentTimeMillis() - adImpressionTime >= time

    val isNoneOrFail: Boolean
        get() = status == AdsStatus.NONE || status == AdsStatus.FAIL

    fun shouldLoadAd() = when (status) {
        AdsStatus.NONE, AdsStatus.FAIL, AdsStatus.IMPRESSED -> true
        AdsStatus.LOADING, AdsStatus.SUCCESS -> false
    }

    fun makeFail() {
        _statusFlow.value = AdsStatus.FAIL
    }

    open fun releaseAd() {}

    open fun reset() {
        releaseAd()
        adData = null
        _statusFlow.value = AdsStatus.NONE
    }

    fun resetIfFailOrImpressed() {
        if (status == AdsStatus.FAIL || status == AdsStatus.IMPRESSED) {
            reset()
        }
    }

    fun displayText(ad: AdDataConfig) = "${ad.name}(${lastID(ad.id)})"

    private fun lastID(unit: String): String {
        val items = unit.split("/")
        return items.lastOrNull() ?: ""
    }
}