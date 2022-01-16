package com.tobiasschuerg.datetime

import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneOffset
import org.threeten.bp.temporal.WeekFields
import java.util.Locale

fun LocalDate.toUtcMillis(): Long = atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()

fun LocalDate.firstDayOfWeek(): LocalDate {
    val dayOfWeek = WeekFields.of(Locale.getDefault()).dayOfWeek()
    return with(dayOfWeek, 1)
}

fun LocalDate.lastDayOfWeek(): LocalDate {
    val dayOfWeek = WeekFields.of(Locale.getDefault()).dayOfWeek()
    return with(dayOfWeek, DayOfWeek.values().size.toLong())
}

fun LocalDate.atStartOfWeek(): LocalDate {
    val dayOfWeek = WeekFields.of(Locale.getDefault()).dayOfWeek()
    return with(dayOfWeek, 1)
}

fun LocalDate.atEndOfWeek(): LocalDate {
    val dayOfWeek = WeekFields.of(Locale.getDefault()).dayOfWeek()
    return with(dayOfWeek, DAYS_IN_A_WEEK)
}

fun LocalDate.atStartOfMonth(): LocalDate {
    return withDayOfMonth(1)
}

fun LocalDate.atEndOfMonth(): LocalDate {
    return withDayOfMonth(lengthOfMonth())
}

fun LocalDate.atStartOfQuarter(): LocalDate {
    return withMonth((month.value - 1) / 3 * 3 + 1).atStartOfMonth()
}

fun LocalDate.atEndOfQuarter(): LocalDate {
    return withMonth((month.value - 1) / 3 * 3 + 3).atEndOfMonth()
}

fun LocalDate.atStartOfYear(): LocalDate {
    return withDayOfYear(1)
}

fun LocalDate.atEndOfYear(): LocalDate {
    return withDayOfYear(lengthOfYear())
}

private val DAYS_IN_A_WEEK = 7L
