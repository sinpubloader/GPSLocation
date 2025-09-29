package chin.pswm.gps.photo.location.map.ads.adjust

import android.content.Context
import android.os.Bundle
import chin.pswm.gps.photo.location.map.MyApplication
import chin.pswm.gps.photo.location.map.ads.ext.ITag
import chin.pswm.gps.photo.location.map_debug.BuildConfig
import com.adjust.sdk.Adjust
import com.adjust.sdk.AdjustAdRevenue
import com.adjust.sdk.AdjustConfig
import com.adjust.sdk.LogLevel
import com.facebook.FacebookSdk
import com.facebook.LoggingBehavior
import com.facebook.appevents.AppEventsConstants
import com.facebook.appevents.AppEventsLogger
import com.google.android.gms.ads.AdValue
import timber.log.Timber


class AdjustManager() : ITag {

    companion object {
        lateinit var INSTANCE: AdjustManager
    }

    init {
        INSTANCE = this
        val environment = if (BuildConfig.DEBUG) AdjustConfig.ENVIRONMENT_SANDBOX
        else AdjustConfig.ENVIRONMENT_PRODUCTION

        val config = AdjustConfig(MyApplication.instance, "5e73pcy05ce8", environment)

        // Change the log level.
        config.setLogLevel(if (BuildConfig.DEBUG) LogLevel.DEBUG else LogLevel.VERBOSE)
        config.setOnAttributionChangedListener { attribution ->
            Timber.Forest.tag(TAG).d("Attribution callback called!")
        }

        // Set event success tracking delegate.
        config.setOnEventTrackingSucceededListener { eventSuccessResponseData ->
            Timber.Forest.tag(TAG).d("Event success data: $eventSuccessResponseData")
        }
        // Set event failure tracking delegate.
        config.setOnEventTrackingFailedListener { eventFailureResponseData ->
            Timber.Forest.tag(TAG).e("Event failure data: $eventFailureResponseData")
        }

        // Set session success tracking delegate.
        config.setOnSessionTrackingSucceededListener { sessionSuccessResponseData ->
            Timber.Forest.tag(TAG).d("Session success data: $sessionSuccessResponseData")
        }

        // Set session failure tracking delegate.
        config.setOnSessionTrackingFailedListener { sessionFailureResponseData ->
            Timber.Forest.tag(TAG).e("Session failure data: $sessionFailureResponseData")
        }
        config.enableSendingInBackground()
        Adjust.initSdk(config)
        if (config.isValid) {
            Timber.Forest.tag(TAG).d(": init success ")
        } else {
            Timber.Forest.tag(TAG).e("init fail: ")
        }
    }

    fun logAdRevenue(
        context: Context,
        adValue: AdValue,
        adUnit: String,
        type: String,
        mediation: String?
    ) {
        Timber.tag(TAG).i("logAdRevenue: $adUnit - $mediation")

        val adRevenue = AdjustAdRevenue("admob_sdk")
        adRevenue.setRevenue(adValue.valueMicros / 1000000.0, adValue.currencyCode)
        adRevenue.setAdRevenueNetwork(mediation)
        Adjust.trackAdRevenue(adRevenue)

        // Tracking for facebook
        FacebookSdk.setIsDebugEnabled(BuildConfig.DEBUG)
        if (BuildConfig.DEBUG) FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS)
        val logger = AppEventsLogger.newLogger(context)
        val facebookParams = Bundle()
        facebookParams.putString(AppEventsConstants.EVENT_PARAM_CURRENCY, adValue.currencyCode)
        logger.logEvent(
            eventName = AppEventsConstants.EVENT_NAME_AD_IMPRESSION,
            valueToSum = adValue.valueMicros / 1000000.0,
            parameters = facebookParams
        )

    }
}