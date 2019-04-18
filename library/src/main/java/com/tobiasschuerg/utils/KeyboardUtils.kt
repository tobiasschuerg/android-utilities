package com.tobiasschuerg.utils

import android.app.Activity.INPUT_METHOD_SERVICE
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_IMPLICIT_ONLY

/**
 * Hide the soft keyboard.
 */
fun Context.hideKeyboard() {
    val inputMethodManager: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(HIDE_IMPLICIT_ONLY, 0)
}
