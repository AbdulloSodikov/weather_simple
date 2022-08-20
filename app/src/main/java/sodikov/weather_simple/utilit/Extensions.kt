package sodikov.weather_simple.utilit

import android.content.pm.PackageManager
import android.content.pm.PermissionGroupInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.isPermissionGranted(permission:String):Boolean {
    return ContextCompat.checkSelfPermission(
        activity as AppCompatActivity,permission) == PackageManager.PERMISSION_GRANTED
}