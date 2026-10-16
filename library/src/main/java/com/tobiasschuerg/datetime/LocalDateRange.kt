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
) : ClosedRange<LocalDate>,
    Iterable<LocalDate> {
    init {
        require(!endInclusive.isBefore(start)) { "Start date must not be after end date." }
    }

    override fun iterator(): Iterator<LocalDate> =
        object : Iterator<LocalDate> {
            // null once endInclusive has been returned; avoids overflowing past LocalDate.MAX
            private var next: LocalDate? = start

            override fun hasNext(): Boolean = next != null

            override fun next(): LocalDate {
                val current = next ?: throw NoSuchElementException("Date range has no more elements.")
                next = if (current < endInclusive) current.plusDays(1) else null
                return current
            }
        }

    /**
     * Calculates the total number of days within the range, including both the start and end dates.
     *
     * @return The total number of days as a Long.
     */
    fun getLength(): Long = ChronoUnit.DAYS.between(start, endInclusive) + 1

    override fun equals(other: Any?): Boolean =
        other is LocalDateRange && start == other.start && endInclusive == other.endInclusive

    override fun hashCode(): Int = 31 * start.hashCode() + endInclusive.hashCode()

    override fun toString(): String = "$start..$endInclusive"
}
