package com.tobiasschuerg.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.reflect.KClass

/**
 * Displays a long duration toast message.
 *
 * @param text The message to display.
 */
fun Context.longToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

/**
 * Displays a long duration toast message using a string resource.
 *
 * @param text The resource id of the message to display.
 */
fun Context.longToast(
    @StringRes text: Int,
) {
    longToast(getString(text))
}

/**
 * Checks for internet connectivity.
 *
 * @return True if the internet connection is available, false otherwise.
 */
@RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isOnline(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager?.activeNetwork
        val capabilities = connectivityManager?.getNetworkCapabilities(network)
        capabilities?.hasCapability(android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    } else {
        @Suppress("DEPRECATION")
        connectivityManager?.activeNetworkInfo?.isConnected == true
    }
}

/**
 * Creates a flow that emits internet connectivity status at specified intervals.
 *
 * @param delayMillis Delay in milliseconds between each status check.
 * @return Flow emitting boolean values representing connectivity status.
 */
@RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isOnlineFlow(delayMillis: Long): Flow<Boolean> =
    flow {
        while (true) {
            emit(isOnline())
            delay(delayMillis)
        }
    }

/**
 * Starts an Activity specified by the [activity] class parameter.
 *
 * @param T The Activity class to start.
 */
fun <T : Activity> Context.startActivity(activity: KClass<T>) {
    val intent = Intent(this, activity.java)
    ContextCompat.startActivity(this, intent, null)
}

/**
 * Starts an Activity specified by the [activity] class parameter, intended for use within an Activity context.
 *
 * @param T The Activity class to start.
 */
fun <T : Activity> Activity.startActivity(activity: KClass<T>) {
    this.startActivity(Intent(this, activity.java))
}

/**
 * Starts an Activity for which you would like a result when it finishes.
 *
 * @param T The Activity class to start.
 * @param requestCode If >= 0, this code will be returned in onActivityResult() when the activity exits.
 */
fun <T : Activity> Activity.startActivityForResult(
    activity: KClass<T>,
    requestCode: Int,
) {
    val intent = Intent(this, activity.java)
    startActivityForResult(intent, requestCode)
}
