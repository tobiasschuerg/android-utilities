package com.tobiasschuerg.utils.views

import android.view.inputmethod.EditorInfo
import android.widget.EditText

/**
 * Sets up an action to be performed when the IME_ACTION_DONE is triggered for this EditText.
 * This is typically used for handling the "Done" button press on the soft keyboard.
 *
 * @param action The lambda function to execute when IME_ACTION_DONE is triggered.
 */
fun EditText.doOnDone(action: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            action.invoke() // Invoke the passed action
            true // Return true to indicate that the action has been handled
        } else {
            false // Return false to let other listeners continue processing the event
        }
    }
}
