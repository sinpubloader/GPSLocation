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
                id = "123123",
                shortLabel = app.getString(R.string.earth_view),
                longLabel = app.getString(R.string.earth_view),
                intent = Intent(app, FirstOpenActivity::class.java).apply {
                    action = Intent.ACTION_VIEW
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtras(
                        bundleOf(
                            Constants.KEY_OPEN_FROM to Constants.OPEN_FROM_EARTH_SHORTCUT
                        )
                    )
                },
                shortcutIcon = R.drawable.icn_earth
            ),
            buildShortcut(
                id = "123124",
                shortLabel = app.getString(R.string.gps_camera),
                longLabel = app.getString(R.string.gps_camera),
                intent = Intent(app, FirstOpenActivity::class.java).apply {
                    action = Intent.ACTION_VIEW
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtras(
                        bundleOf(
                            Constants.KEY_OPEN_FROM to Constants.OPEN_FROM_GPS_SHORTCUT
                        )
                    )
                },
                shortcutIcon = R.drawable.img_cameraa
            ),
            buildShortcut(
                id = "sdfsa",
                shortLabel = app.getString(R.string.photo_grid),
                longLabel = app.getString(R.string.photo_grid),
                intent = Intent(app, FirstOpenActivity::class.java).apply {
                    action = Intent.ACTION_VIEW
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtras(
                        bundleOf(
                            Constants.KEY_OPEN_FROM to Constants.OPEN_FROM_GRID_SHORTCUT
                        )
                    )
                },
                shortcutIcon = R.drawable.photo_grid
            ),
            buildShortcut(
                id = "sdfsa",
                shortLabel = app.getString(R.string.route_planner),
                longLabel = app.getString(R.string.route_planner),
                intent = Intent(app, FirstOpenActivity::class.java).apply {
                    action = Intent.ACTION_VIEW
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtras(
                        bundleOf(
                            Constants.KEY_OPEN_FROM to Constants.OPEN_FROM_ROUTE_SHORTCUT
                        )
                    )
                },
                shortcutIcon = R.drawable.icn_route_planner
            )
        )
    }
}