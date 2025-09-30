package chin.pswm.gps.photo.location.map.ads

import android.app.Dialog
import android.content.Context
import chin.pswm.gps.photo.location.map.ads.ext.tryWithoutCatch
import chin.pswm.gps.photo.location.map_debug.R

object LoadingAdsDialog {

    var dialog: Dialog? = null

    fun showLoading(context: Context) = try {
        tryWithoutCatch { if (dialog?.isShowing == true) dialog?.cancel() }
        dialog = createLoadingDialog(context).also { it.show() }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    fun hideLoading() = try {
        tryWithoutCatch { if (dialog?.isShowing == true) dialog?.dismiss() }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    fun createLoadingDialog(context: Context): Dialog {
        val dialog = Dialog(context, R.style.ThemePanda_Ads)
        dialog.setContentView(R.layout.dialog_before_show_ads)
        return dialog
    }
}

