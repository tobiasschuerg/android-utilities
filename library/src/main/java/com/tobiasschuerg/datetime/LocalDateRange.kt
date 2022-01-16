package com.tobiasschuerg.datetime

import org.threeten.bp.LocalDate

class LocalDateRange(override val start: LocalDate, override val endInclusive: LocalDate) :
    ClosedRange<LocalDate>, Iterable<LocalDate> {

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
}
