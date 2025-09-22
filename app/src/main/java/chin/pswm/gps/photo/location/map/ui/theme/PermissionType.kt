package chin.pswm.gps.photo.location.map.ui.theme

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import chin.pswm.gps.photo.location.map_debug.R;

enum class PermissionType(
    @param:DrawableRes val icon: Int,
    @param:StringRes val title: Int,
    @param:StringRes val body: Int,
) {
    Camera(
        icon = R.drawable.img_camera,
        title = R.string.permission_camera_title,
        body = R.string.permission_camera_content,
    ),
    Location(
        icon = R.drawable.img_location,
        title = R.string.permission_location_title,
        body = R.string.permission_location_content,
    ),
    Voice(
        icon = R.drawable.img_voice,
        title = R.string.permission_voice_title,
        body = R.string.permission_voice_content,
    ),
}