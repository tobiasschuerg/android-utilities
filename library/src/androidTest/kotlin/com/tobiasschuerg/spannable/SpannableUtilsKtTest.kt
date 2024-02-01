package com.tobiasschuerg.spannable

import android.graphics.Typeface
import android.text.style.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SpannableExtensionsTest {

    private val testString = "Test String"

    @Test
    fun testBoldSpan() {
        val spanned = bold(testString)
        val spans = spanned.getSpans(0, spanned.length, StyleSpan::class.java)
        assertTrue(spans.isNotEmpty())
        assertEquals(Typeface.BOLD, spans[0].style)
    }

    @Test
    fun testItalicSpan() {
        val spanned = italic(testString)
        val spans = spanned.getSpans(0, spanned.length, StyleSpan::class.java)
        assertTrue(spans.isNotEmpty())
        assertEquals(Typeface.ITALIC, spans[0].style)
    }

    @Test
    fun testUnderlineSpan() {
        val spanned = underline(testString)
        val spans = spanned.getSpans(0, spanned.length, UnderlineSpan::class.java)
        assertTrue(spans.isNotEmpty())
    }

    @Test
    fun testStrikeSpan() {
        val spanned = strike(testString)
        val spans = spanned.getSpans(0, spanned.length, StrikethroughSpan::class.java)
        assertTrue(spans.isNotEmpty())
    }

    @Test
    fun testSuperscriptSpan() {
        val spanned = sup(testString)
        val spans = spanned.getSpans(0, spanned.length, SuperscriptSpan::class.java)
        assertTrue(spans.isNotEmpty())
    }

    @Test
    fun testSubscriptSpan() {
        val spanned = sub(testString)
        val spans = spanned.getSpans(0, spanned.length, SubscriptSpan::class.java)
        assertTrue(spans.isNotEmpty())
    }

    @Test
    fun testSizeSpan() {
        val relativeSize = 1.5f // Example size multiplier
        val spanned = size(relativeSize, testString)
        val spans = spanned.getSpans(0, spanned.length, RelativeSizeSpan::class.java)
        assertTrue(spans.isNotEmpty())
        assertEquals(relativeSize, spans[0].sizeChange, 0.0f)
    }

    @Test
    fun testColorSpan() {
        val color = 0xFFFF00FF.toInt() // Example color
        val spanned = color(color, testString)
        val spans = spanned.getSpans(0, spanned.length, ForegroundColorSpan::class.java)
        assertTrue(spans.isNotEmpty())
        assertEquals(color, spans[0].foregroundColor)
    }

    @Test
    fun testBackgroundSpan() {
        val color = 0xFF00FF00.toInt() // Example background color
        val spanned = background(color, testString)
        val spans = spanned.getSpans(0, spanned.length, BackgroundColorSpan::class.java)
        assertTrue(spans.isNotEmpty())
        assertEquals(color, spans[0].backgroundColor)
    }

    @Test
    fun testUrlSpan() {
        val url = "http://example.com"
        val spanned = url(url, testString)
        val spans = spanned.getSpans(0, spanned.length, URLSpan::class.java)
        assertTrue(spans.isNotEmpty())
        assertEquals(url, spans[0].url)
    }
}
