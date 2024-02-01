package com.tobiasschuerg.datetime

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields
import java.util.Locale

/**
 * Converts this [LocalDate] to the number of milliseconds from the epoch of 1970-01-01T00:00:00Z,
 * assuming the start of this date in UTC.
 *
 * @return The number of milliseconds from the epoch of 1970-01-01T00:00:00Z.
 */
fun LocalDate.toUtcMillis(): Long = atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()

/**
 * Returns the first day of the week based on the system's default locale.
 *
 * @return The [LocalDate] representing the first day of the current week.
 */
fun LocalDate.firstDayOfWeek(): LocalDate {
    val weekFields = WeekFields.of(Locale.getDefault())
    return with(TemporalAdjusters.previousOrSame(weekFields.firstDayOfWeek))
}

/**
 * Returns the last day of the week based on the system's default locale.
 *
 * @return The [LocalDate] representing the last day of the current week.
 */
fun LocalDate.lastDayOfWeek(): LocalDate {
    val weekFields = WeekFields.of(Locale.getDefault())
    return with(TemporalAdjusters.nextOrSame(weekFields.firstDayOfWeek.minus(1)))
}

/**
 * Calculates the start of the month for this date.
 *
 * @return The [LocalDate] representing the first day of the current month.
 */
fun LocalDate.atStartOfMonth(): LocalDate = withDayOfMonth(1)

/**
 * Calculates the end of the month for this date.
 *
 * @return The [LocalDate] representing the last day of the current month.
 */
fun LocalDate.atEndOfMonth(): LocalDate = withDayOfMonth(lengthOfMonth())

/**
 * Calculates the start of the quarter for this date.
 *
 * @return The [LocalDate] representing the first day of the current quarter.
 */
fun LocalDate.atStartOfQuarter(): LocalDate = withMonth((monthValue - 1) / 3 * 3 + 1).atStartOfMonth()

/**
 * Calculates the end of the quarter for this date.
 *
 * @return The [LocalDate] representing the last day of the current quarter.
 */
fun LocalDate.atEndOfQuarter(): LocalDate = withMonth((monthValue - 1) / 3 * 3 + 3).atEndOfMonth()

/**
 * Calculates the start of the year for this date.
 *
 * @return The [LocalDate] representing the first day of the year.
 */
fun LocalDate.atStartOfYear(): LocalDate = withDayOfYear(1)

/**
 * Calculates the end of the year for this date.
 *
 * @return The [LocalDate] representing the last day of the year.
 */
fun LocalDate.atEndOfYear(): LocalDate = withDayOfYear(lengthOfYear())
