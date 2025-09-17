package com.tobiasschuerg.datetime

import android.annotation.SuppressLint
import java.time.LocalDate
import java.time.temporal.ChronoUnit

/**
 * Represents a range of dates between two [LocalDate] instances, inclusive of both start and end dates.
 * Ensures that the start date is not after the end date and provides an iterator over the range.
 *
 * @property start The start date of the range.
 * @property endInclusive The end date of the range, inclusive.
 */
@SuppressLint("NewApi")
class LocalDateRange(
    override val start: LocalDate,
    override val endInclusive: LocalDate,
) : ClosedRange<LocalDate>, Iterable<LocalDate> {
    init {
        require(!endInclusive.isBefore(start)) { "Start date must not be after end date." }
    }

    override fun iterator(): Iterator<LocalDate> {
        return object : Iterator<LocalDate> {
            var current: LocalDate = start

            override fun hasNext(): Boolean = current <= endInclusive

            override fun next(): LocalDate {
                if (!hasNext()) throw NoSuchElementException("Date range has no more elements.")
                return current.apply {
                    current = this.plusDays(1)
                }
            }
        }
    }

    /**
     * Calculates the total number of days within the range, including both the start and end dates.
     *
     * @return The total number of days as a Long.
     */
    fun getLength(): Long = ChronoUnit.DAYS.between(start, endInclusive) + 1
}
