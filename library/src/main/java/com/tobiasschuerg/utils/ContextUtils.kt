package com.tobiasschuerg.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.distinctUntilChanged
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
 * On API 23+ the active network must provide internet access and must have been validated by the system,
 * so captive portals or networks without actual internet access are reported as offline.
 *
 * @return True if the internet connection is available, false otherwise.
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isOnline(): Boolean {
    val connectivityManager = ContextCompat.getSystemService(this, ConnectivityManager::class.java) ?: return false
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork).hasValidatedInternet()
    } else {
        @Suppress("DEPRECATION")
        connectivityManager.activeNetworkInfo?.isConnected == true
    }
}

/**
 * Creates a flow that emits internet connectivity status at specified intervals.
 *
 * Prefer [onlineStatusFlow], which is event driven and does not poll.
 *
 * @param delayMillis Delay in milliseconds between each status check.
 * @return Flow emitting boolean values representing connectivity status.
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isOnlineFlow(delayMillis: Long): Flow<Boolean> =
    flow {
        while (true) {
            emit(isOnline())
            delay(delayMillis)
        }
    }

/**
 * Creates a cold flow that emits the current internet connectivity status (see [isOnline]) and every
 * subsequent change of it. Backed by a [ConnectivityManager.NetworkCallback] instead of polling.
 *
 * The callback is registered when the flow is collected and unregistered when the collection stops.
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.onlineStatusFlow(): Flow<Boolean> =
    callbackFlow {
        val connectivityManager =
            ContextCompat.getSystemService(this@onlineStatusFlow, ConnectivityManager::class.java)
        if (connectivityManager == null) {
            send(false)
            close()
            return@callbackFlow
        }

        val callback =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                // Track the system default network, which is the one apps actually use for their traffic.
                object : ConnectivityManager.NetworkCallback() {
                    override fun onCapabilitiesChanged(
                        network: Network,
                        networkCapabilities: NetworkCapabilities,
                    ) {
                        trySend(networkCapabilities.hasValidatedInternet())
                    }

                    override fun onLost(network: Network) {
                        trySend(false)
                    }
                }.also { connectivityManager.registerDefaultNetworkCallback(it) }
            } else {
                // The permission is guaranteed by @RequiresPermission on onlineStatusFlow.
                @SuppressLint("MissingPermission")
                object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        trySend(isOnline())
                    }

                    override fun onLost(network: Network) {
                        trySend(isOnline())
                    }
                }.also {
                    val request =
                        NetworkRequest
                            .Builder()
                            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                            .build()
                    connectivityManager.registerNetworkCallback(request, it)
                }
            }

        trySend(isOnline())
        awaitClose { connectivityManager.unregisterNetworkCallback(callback) }
    }.distinctUntilChanged()
        .conflate()

private fun NetworkCapabilities?.hasValidatedInternet(): Boolean {
    if (this == null) return false
    val hasInternet = hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        hasInternet && hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    } else {
        hasInternet
    }
}

/**
 * Starts an Activity specified by the [activity] class parameter.
 *
 * If this is not an Activity context, [Intent.FLAG_ACTIVITY_NEW_TASK] is added as required by the framework.
 *
 * @param T The Activity class to start.
 */
fun <T : Activity> Context.startActivity(activity: KClass<T>) {
    val intent = Intent(this, activity.java)
    if (this !is Activity) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(intent)
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
 * Consider using the Activity Result APIs (`registerForActivityResult`) from androidx.activity instead.
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
