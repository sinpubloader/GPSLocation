package chin.pswm.gps.photo.location.map.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes

object BitmapUtil {

    fun bitmapFromDrawable(context: Context, @DrawableRes drawableResId: Int): Bitmap? {
        return try {
            BitmapFactory.decodeResource(context.resources, drawableResId)
        } catch (e: Exception) {
            null
        }
    }
}