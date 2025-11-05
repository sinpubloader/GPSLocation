package chin.pswm.gps.photo.location.map.activity.first_open.cusom.shotcuts

import android.content.Context
import android.content.Intent
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.core.os.bundleOf
import chin.pswm.gps.photo.location.map.activity.first_open.FirstOpenActivity
import chin.pswm.gps.photo.location.map.activity.first_open.common.Constants
import chin.pswm.gps.photo.location.map_debug.R

class ShortcutManager(
    private val app: Context
) {

    fun updateShortcuts() {
        val listOfShortcuts = getShortcutsList()
        ShortcutManagerCompat.setDynamicShortcuts(app, listOfShortcuts)
    }

    private fun getShortcutsList(): List<ShortcutInfoCompat> {
        fun buildShortcut(
            id: String,
            shortLabel: String,
            longLabel: String,
            intent: Intent,
            shortcutIcon: Int
        ): ShortcutInfoCompat {
            return ShortcutInfoCompat.Builder(app, id)
                .setShortLabel(shortLabel)
                .setLongLabel(longLabel)
                .setIntent(intent)
                .setIcon(IconCompat.createWithResource(app, shortcutIcon))
                .build()
        }

        return arrayListOf(
            buildShortcut(
                id = "4234",
                shortLabel = app.getString(R.string.app_name),
                longLabel = app.getString(R.string.app_name),
                intent = Intent(app, FirstOpenActivity::class.java).apply {
                    action = Intent.ACTION_VIEW
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtras(
                        bundleOf(
                            Constants.KEY_OPEN_FROM to Constants.OPEN_FROM_SHORTCUT
                        )
                    )
                },
                shortcutIcon = R.drawable.ic_checked
            ),
            buildShortcut(
                id = "464562",
                shortLabel = app.getString(R.string.app_name),
                longLabel = app.getString(R.string.app_name),
                intent = Intent(app, FirstOpenActivity::class.java).apply {
                    action = Intent.ACTION_VIEW
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtras(
                        bundleOf(
                            Constants.KEY_OPEN_FROM to Constants.OPEN_FROM_SHORTCUT
                        )
                    )
                },
                shortcutIcon = R.drawable.ic_checked
            ),
            buildShortcut(
                id = "12123",
                shortLabel = app.getString(R.string.app_name),
                longLabel = app.getString(R.string.app_name),
                intent = Intent(app, FirstOpenActivity::class.java).apply {
                    action = Intent.ACTION_VIEW
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtras(
                        bundleOf(
                            Constants.KEY_OPEN_FROM to Constants.OPEN_FROM_SHORTCUT
                        )
                    )
                },
                shortcutIcon = R.drawable.ic_checked
            )
        )
    }
}