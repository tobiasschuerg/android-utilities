package com.tobiasschuerg.utils

import android.app.Activity
import android.app.Activity.INPUT_METHOD_SERVICE
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Hides the soft keyboard if a view is currently focused.
 * If no view is focused, nothing will happen.
 */
fun Activity.hideKeyboard() {
    currentFocus?.let { hideKeyboard(it) }
}

/**
 * Hide the soft keyboard.
 */
fun Context.hideKeyboard(focusView: View) {
    val inputMethodManager: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(focusView.windowToken, 0)
}
