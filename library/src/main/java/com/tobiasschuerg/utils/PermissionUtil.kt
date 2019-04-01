package com.tobiasschuerg.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

/**
 * Helps with checking and requesting permission.
 *
 * Created by Tobias Schürg on 03.03.2016.
 */
object PermissionUtil {

    fun Context.hasPermissions(vararg permissions: String): Boolean {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }

    fun Activity.requestPermissions(permissions: Array<String>) {
        ActivityCompat.requestPermissions(this, permissions, 0)
    }
}
