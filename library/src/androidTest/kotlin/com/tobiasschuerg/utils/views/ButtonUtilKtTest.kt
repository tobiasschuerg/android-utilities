package com.tobiasschuerg.utils.views

import android.content.Context
import android.widget.Button
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ButtonUtilKtTest {
    private fun getContext(): Context = ApplicationProvider.getApplicationContext()

    @Test
    fun testPerformCheckedClick_whenButtonIsEnabledAndClickable() {
        val button = Button(getContext())

        var clicked = false
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            button.isEnabled = true
            button.isClickable = true
            button.setOnClickListener { clicked = true }

            // Perform the action to be tested
            button.performCheckedClick()
        }

        // Assert that the click listener was triggered
        assertTrue("Button should have been clicked", clicked)
    }

    @Test
    fun testPerformCheckedClick_whenButtonIsDisabled() {
        val button = Button(getContext())

        var clicked = false
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            button.isEnabled = false // Button is disabled
            button.isClickable = true // Button is still set as clickable
            button.setOnClickListener { clicked = true }

            // Perform the action to be tested
            button.performCheckedClick()
        }

        // Assert that the click listener was not triggered
        assertFalse("Button should not have been clicked", clicked)
    }
}
