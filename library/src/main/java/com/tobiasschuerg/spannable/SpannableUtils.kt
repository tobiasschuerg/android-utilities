package com.tobiasschuerg.spannable

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.SubscriptSpan
import android.text.style.SuperscriptSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan

/**
 * Creates a new [SpannableString] based on the DSL provided in [func].
 */
fun spannable(func: SpannableStringBuilder.() -> Unit): SpannableString {
    val spannable = SpannableStringBuilder()
    spannable.func()
    return SpannableString(spannable)
}

/**
 * Applies a span to the [SpannableString].
 */
private fun span(
    source: CharSequence,
    what: Any,
): SpannableString {
    val spannableString = SpannableString(source)
    spannableString.setSpan(what, 0, source.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannableString
}

/**
 * Concatenates this [SpannableString] with another [SpannableString].
 */
operator fun SpannableString.plus(s: SpannableString): SpannableString = SpannableString(TextUtils.concat(this, s))

/**
 * Concatenates this [SpannableString] with a [String].
 */
operator fun SpannableString.plus(s: String): SpannableString = SpannableString(TextUtils.concat(this, s))

// Style functions
fun bold(s: CharSequence): SpannableString = span(s, StyleSpan(Typeface.BOLD))

fun italic(s: CharSequence): SpannableString = span(s, StyleSpan(Typeface.ITALIC))

fun underline(s: CharSequence): SpannableString = span(s, UnderlineSpan())

fun strike(s: CharSequence): SpannableString = span(s, StrikethroughSpan())

fun sup(s: CharSequence): SpannableString = span(s, SuperscriptSpan())

fun sub(s: CharSequence): SpannableString = span(s, SubscriptSpan())

fun size(
    size: Float,
    s: CharSequence,
): SpannableString = span(s, RelativeSizeSpan(size))

fun color(
    color: Int,
    s: CharSequence,
): SpannableString = span(s, ForegroundColorSpan(color))

fun background(
    color: Int,
    s: CharSequence,
): SpannableString = span(s, BackgroundColorSpan(color))

fun url(
    url: String,
    s: CharSequence,
): SpannableString = span(s, URLSpan(url))
