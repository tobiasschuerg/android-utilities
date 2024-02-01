package com.tobiasschuerg.datetime

import java.time.LocalDate
import java.time.temporal.ChronoUnit

/**
 * Range of two [LocalDate]s.
 */
class LocalDateRange(
    override val start: LocalDate,
    override val endInclusive: LocalDate
) : ClosedRange<LocalDate>, Iterable<LocalDate> {

    init {
        require(!endInclusive.isBefore(start)) { "start date must not be after end date" }
    }

    override fun iterator(): Iterator<LocalDate> {
        return object : Iterator<LocalDate> {

            var current: LocalDate = start

            override fun hasNext(): Boolean {
                return current <= endInclusive
            }

            override fun next(): LocalDate {
                if (hasNext()) {
                    val next = current
                    current = current.plusDays(1)
                    return next
                } else {
                    throw NoSuchElementException("date range has no more next element")
                }
            }
        }
    }

    /**
     * Number of day in this range.
     */
    fun getLength(): Long = ChronoUnit.DAYS.between(start, endInclusive) + 1
}
