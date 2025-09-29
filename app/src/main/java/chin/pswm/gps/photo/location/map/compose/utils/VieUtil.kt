package chin.pswm.gps.photo.location.map.compose.utils

import android.graphics.Color
import android.view.View
import androidx.compose.ui.platform.ComposeView
import chin.pswm.gps.photo.location.map.ads.ext.tryWithoutCatch

fun View?.gone() {
    tryWithoutCatch {
        this?.visibility = View.GONE
    }
}

fun View?.visible() {
    tryWithoutCatch {
        this?.visibility = View.VISIBLE
    }
}

fun View?.invisible() {
    tryWithoutCatch {
        this?.visibility = View.INVISIBLE
    }
}

fun ComposeView?.black() {
    tryWithoutCatch {
        this?.setBackgroundColor(Color.BLACK)
    }
}

fun ComposeView?.transparent() {
    tryWithoutCatch {
        this?.setBackgroundColor(Color.TRANSPARENT)
    }
}