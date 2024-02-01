package com.tobiasschuerg.utils.views

import android.widget.Button

/**
 * Safely triggers the click event on this button only if it is both enabled and clickable.
 *
 * This function checks the button's [isEnabled] and [isClickable] properties before invoking
 * [performClick], ensuring that the click action is only performed when the button is interactive.
 */
fun Button.performCheckedClick() {
    if (isEnabled && isClickable) {
        performClick()
    }
}
