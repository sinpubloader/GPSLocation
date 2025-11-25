package chin.pswm.gps.photo.location.map.ads.ext

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.view.ViewParent
import android.widget.Toast
import androidx.annotation.StringRes
import timber.log.Timber

object AppUtils {

    fun isNetworkConnected(context: Context?): Boolean {
        if (context != null) {
            var result = false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }

            return result
        }
        return false
    }
}

fun tryWithoutCatch(block: () -> Unit) {
    try {
        block.invoke()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Context?.toast(@StringRes res: Int) {
    if (this == null) return
    try {
        Toast.makeText(this, this.getText(res), Toast.LENGTH_SHORT).show()
    } catch (ex: Exception) {
        Timber.tag("AppUtils").e("toast: error ${ex.message}")
    }
}

fun View.requestLayoutWithDelay(delayMillis: Long) {
    postDelayed({
        val t = parent?.parent?.parent
        if (t == null) {
            postDelayed({
                val k = parent?.parent?.parent
                if (k != null) {
                    k.requestLayout()
                } else {
                    Timber.tag("requestLayoutWithDelay").d("parent is null again")
                }
            }, delayMillis)
        } else {
            t.requestLayout()
        }
    }, delayMillis)
}

private fun ViewParent?.findAndroidComposeViewParent(): ViewParent? = when {
    this != null && this::class.java.simpleName == "AndroidComposeView" -> this
    this != null -> this.parent.findAndroidComposeViewParent()
    else -> null
}