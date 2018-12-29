package com.tobiasschuerg.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.annotation.StringRes
import kotlin.reflect.KClass


fun Context.longToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Context.longToast(@StringRes text: Int) {
    longToast(getString(text))
}

/**
 * Checks if an internet connection is available.
 */
fun Context.isOnline(): Boolean {
    val connectivityManager: ConnectivityManager? = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    return connectivityManager?.activeNetworkInfo?.isConnected ?: false
}

/**
 * Directly start an [Activity] without any parameters.
 */
fun <T : Activity> Context.startActivity(activity: KClass<T>) {
    this.startActivity(Intent(this, activity.java))
}

/**
 * Directly start an [Activity] without any parameters.
 */
fun <T : Activity> Activity.startActivity(activity: KClass<T>) {
    this.startActivity(Intent(this, activity.java))
}

/**
 * Directly start an [Activity] for result.
 */
fun <T : Activity> Activity.startActivityForResult(activity: KClass<T>, resultCode: Int) {
    this.startActivityForResult(Intent(this, activity.java), resultCode)
}