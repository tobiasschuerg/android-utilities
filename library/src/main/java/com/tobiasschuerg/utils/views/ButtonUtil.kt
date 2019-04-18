package com.tobiasschuerg.utils.views

import android.widget.Button

/**
 * Calls the click listener only iff the button is enabled and clickable.
 *
 * @see [Button.performClick]
 */
fun Button.performCheckedClick() {
    if (isEnabled && isClickable) {
        performClick()
    }
}