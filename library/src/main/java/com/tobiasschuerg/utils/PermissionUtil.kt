package com.tobiasschuerg.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * Utility object for checking and requesting permissions in an Android application.
 *
 * Usage:
 * - Use [hasPermissions] in any Context to check if all required permissions have been granted.
 * - Use [requestPermissions] in an Activity to request the necessary permissions.
 */
object PermissionUtil {
    /**
     * Checks if all given permissions have been granted.
     *
     * @param permissions Vararg of permission strings to check.
     * @return True if all permissions are granted, false if at least one is denied.
     */
    fun Context.hasPermissions(vararg permissions: String): Boolean {
        // Early return for versions below Marshmallow as permission is granted at install time
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true

        return permissions.all { permission ->
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
        }
    }

    /**
     * Requests the specified permissions to be granted to this application.
     * This variant uses a fixed request code.
     *
     * @param permissions An array of permission strings to request.
     */
    fun Activity.requestPermissions(permissions: Array<String>) {
        ActivityCompat.requestPermissions(this, permissions, 0) // Using 0 as the fixed request code
    }
}
