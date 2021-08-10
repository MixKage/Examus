import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionManager private constructor() {

    fun checkPermission(
        activity: Activity,
        permissionRequests: Array<String>?,
        requestCode: Int
    ): Boolean {

        permissionRequests?.let {
            it.firstOrNull {
                ContextCompat.checkSelfPermission(
                    activity,
                    it
                ) != PackageManager.PERMISSION_GRANTED
            }?.apply {
                ActivityCompat.requestPermissions(
                    activity,
                    permissionRequests,
                    requestCode
                )
                return false
            }
        }
        return true
    }

    companion object {
        val instance by lazy { PermissionManager() }
        const val CAMERA_PERMISSION = 1
    }
}