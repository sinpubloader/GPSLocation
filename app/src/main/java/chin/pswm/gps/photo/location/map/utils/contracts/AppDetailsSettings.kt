package chin.pswm.gps.photo.location.map.utils.contracts

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContract
import androidx.core.net.toUri

class AppDetailsSettings : ActivityResultContract<Void?, Void?>() {
    override fun createIntent(context: Context, input: Void?): Intent {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.data = ("package:" + context.applicationContext.packageName).toUri()
        if (context !is Activity) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Void? {
        return null
    }
}