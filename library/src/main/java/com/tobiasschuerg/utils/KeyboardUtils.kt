package com.tobiasschuerg.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Hides the soft keyboard if there is a currently focused view. Does nothing if no view has focus.
 */
fun Activity.hideKeyboard() {
    currentFocus?.let {
        hideKeyboard(it)
    }
}

/**
 * Hides the soft keyboard associated with the provided view.
 *
 * @param focusView The view from which to hide the keyboard.
 */
fun Context.hideKeyboard(focusView: View) {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(focusView.windowToken, 0)
}
