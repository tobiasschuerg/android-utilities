package com.tobiasschuerg.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlin.reflect.KClass


fun Context.longToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Context.longToast(@StringRes text: Int) {
    longToast(getString(text))
}

fun Context.isOnline(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.activeNetworkInfo.isConnected
}

/**
 * Directly start an [Activity] without any parameters.
 */
fun <T : Activity> Context.startActivity(activity: KClass<T>) {
    this.startActivity(Intent(this, activity.java))
}

/**
 * Directly start an [Activity] for result.
 */
fun <T : Activity> AppCompatActivity.startActivityForResult(activity: KClass<T>, resultCode: Int) {
    this.startActivityForResult(Intent(this, activity.java), resultCode)
}