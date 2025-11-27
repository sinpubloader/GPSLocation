package chin.pswm.gps.photo.location.map.activity.first_open.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.StringRes
import chin.pswm.gps.photo.location.map.activity.AdvanceCameraActivity
import chin.pswm.gps.photo.location.map.activity.GridCameraActivity
import chin.pswm.gps.photo.location.map.activity.MapViewActivity
import chin.pswm.gps.photo.location.map.activity.StartActivity
import chin.pswm.gps.photo.location.map_debug.R
import timber.log.Timber
import kotlin.jvm.java

object CommonUtils {

    fun Context.openWifiSetting() {
        try {
            val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
            this.startActivity(intent)
        } catch (_: Exception) {
            this.toast(R.string.something_went_wrong)
        }
    }

    fun Context?.toast(@StringRes res: Int) {
        Timber.tag("InterstitialAdUnit").e("toast: ${this != null} - $this")
        if (this == null) return
        try {
            Toast.makeText(this, this.getText(res), Toast.LENGTH_LONG).show()
            Timber.tag("InterstitialAdUnit").d("toast: shown")
        } catch (ex: Exception) {
            Timber.tag("InterstitialAdUnit").e("toast: error ${ex.message}")
        }
    }

    fun openToMainScreen(context: Context) {
        context.startActivity(Intent(context, StartActivity::class.java))
        (context as? Activity)?.finish()
    }

    fun openToGpsCamera(context: Context) {
        context.startActivity(Intent(context, AdvanceCameraActivity::class.java))
        (context as? Activity)?.finish()
    }

    fun openToMapView(context: Context) {
        context.startActivity(Intent(context, MapViewActivity::class.java))
        (context as? Activity)?.finish()
    }

    fun openToGridCamera(context: Context) {
        context.startActivity(Intent(context, GridCameraActivity::class.java))
        (context as? Activity)?.finish()
    }

}