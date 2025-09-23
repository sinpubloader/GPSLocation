package chin.pswm.gps.photo.location.map.ui.theme

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CAMERA
import android.Manifest.permission.POST_NOTIFICATIONS
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_PHONE_STATE
import android.Manifest.permission.RECORD_AUDIO
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import chin.pswm.gps.photo.location.map.utils.StorageUtils.TAG
//import com.example.gps.utils.ITag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class PermissionManager(
    private val app: Application
) /*: ITag*/ {


    private var _permissionRequest = MutableSharedFlow<PermissionType>()
    val permissionRequest = _permissionRequest.asSharedFlow()

    val state = object : PermissionBottomSheetState() {

        override fun onAllow(permissionType: PermissionType) {
            Timber.tag(TAG).d("onAllow: ")
            emitPermission(permissionType)
        }
    }

    fun show(permissions: List<PermissionType>) {
        state.show(permissions)
    }

    fun remove(permissionType: PermissionType) {
        state.remove(permissionType)
    }

    fun emitPermission(permissionType: PermissionType) {
        CoroutineScope(Dispatchers.IO).launch {
            _permissionRequest.emit(permissionType)
        }
    }


    companion object {
        val buildAbove30: Boolean
            get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

        fun Context?.checkPermission(permission: String) =
            this != null && ContextCompat.checkSelfPermission(
                this, permission
            ) == PackageManager.PERMISSION_GRANTED

        fun Context?.shouldShowRationale(permission: String) =
            this is Activity && ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                permission
            )

        fun Activity?.checkPermissionRational(permission: String) =
            this != null && ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

        val Context?.allowStorage12: Boolean
            get() = this != null && checkPermission(READ_EXTERNAL_STORAGE)

        val Context?.allowWriteStorage12: Boolean
            get() = this != null && checkPermission(WRITE_EXTERNAL_STORAGE)

        val Context?.allowStorageAllFile: Boolean
            get() = this != null && buildAbove30 && Environment.isExternalStorageManager()

        val Context?.allowLocation: Boolean
            get() = this != null && (checkPermission(ACCESS_FINE_LOCATION) || checkPermission(
                ACCESS_COARSE_LOCATION
            ))

        val Context?.allowCamera: Boolean
            get() = this != null && checkPermission(CAMERA)

        val Context?.allowRecordAudio: Boolean
            get() = this != null && checkPermission(RECORD_AUDIO)

        val Context?.rationalLocation: Boolean
            get() = this != null && (shouldShowRationale(ACCESS_FINE_LOCATION) && shouldShowRationale(
                ACCESS_COARSE_LOCATION
            ))

        val Context?.rationalNotification: Boolean
            get() = this != null && shouldShowRationale(POST_NOTIFICATIONS)

        val Context?.rationalCamera: Boolean
            get() = this != null && shouldShowRationale(CAMERA)

        val Context?.rationalRecordAudio: Boolean
            get() = this != null && shouldShowRationale(RECORD_AUDIO)

        val Context?.allowStorage: Boolean
            get() = this != null && if (buildAbove30) allowStorageAllFile else this.allowStorage12

        val Context?.allowWriteStorage: Boolean
            get() = this != null && if (buildAbove30) allowStorageAllFile else this.allowWriteStorage12

        val Context?.allowPhoneState: Boolean
            get() = this != null && checkPermission(READ_PHONE_STATE)

        val needPermissionNotification: Boolean
            get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

        val Context?.allowNotification: Boolean
            get() = this != null && (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU || checkPermission(
                POST_NOTIFICATIONS
            ))
    }
}