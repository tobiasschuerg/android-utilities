package com.tobiasschuerg.utils.views

import android.view.inputmethod.EditorInfo
import android.widget.EditText

/**
 * Executes a function once [EditorInfo.IME_ACTION_DONE] is triggered.
 *
 * @see [EditorInfo.IME_ACTION_DONE]
 */
fun EditText.doOnDone(action: () -> Unit) {
    this.setOnEditorActionListener { _, actionId, _ ->
        when (actionId) {
            EditorInfo.IME_ACTION_DONE -> {
                action()
                true
            }
            else -> false
        }
    }
}
