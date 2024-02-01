package com.tobiasschuerg.utils

import com.tobiasschuerg.datetime.LocalDateRange
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class LocalDateRangeTest {
    @Test
    fun `Given single day range, when counted, then size is 1`() {
        val today = LocalDate.now()
        val range = LocalDateRange(today, today)
        assertEquals(1, range.count())
        assertEquals(today, range.first())
        assertEquals(today, range.last())
    }

    @Test
    fun `Given two day range, when counted, then size is 2`() {
        val today = LocalDate.now()
        val tomorrow = today.plusDays(1)
        val range = LocalDateRange(today, tomorrow)
        assertEquals(2, range.count())
        assertEquals(today, range.first())
        assertEquals(tomorrow, range.last())
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Given start date after end date, when range is created, then exception is thrown`() {
        LocalDateRange(LocalDate.now().plusDays(1), LocalDate.now())
    }

    @Test
    fun `Given range of 8 days, when length is calculated, then result is 8`() {
        val start = LocalDate.now()
        val end = start.plusDays(7)
        val range = LocalDateRange(start, end)

        assertEquals(8, range.getLength())
        assertEquals(range.getLength(), range.count().toLong())
    }

    @Test
    fun `Given maximum range, when counted, then size matches days between`() {
        val start = LocalDate.MIN
        val end = LocalDate.MAX
        val range = LocalDateRange(start, end)
        // This assertion assumes the implementation can handle the entire LocalDate range.
        // This is a theoretical test and might not be practical
        // due to the vast range of LocalDate.MIN to LocalDate.MAX.
        assertEquals(ChronoUnit.DAYS.between(start, end) + 1, range.getLength())
    }

    @Test
    fun `Given leap year range, when counted, then includes February 29`() {
        val start = LocalDate.of(2020, 2, 28)
        val end = LocalDate.of(2020, 3, 1)
        val range = LocalDateRange(start, end)
        assertTrue(LocalDate.of(2020, 2, 29) in range)
        assertEquals(3, range.count())
    }

    @Test
    fun `Given range across years, when counted, then size is correct`() {
        val start = LocalDate.of(2019, 12, 31)
        val end = LocalDate.of(2020, 1, 2)
        val range = LocalDateRange(start, end)
        assertEquals(3, range.count())
    }

    @Test
    fun `Given range at LocalDate boundaries, when length is calculated, then avoids overflow`() {
        val start = LocalDate.of(9999, 12, 30)
        val end = LocalDate.of(9999, 12, 31)
        val range = LocalDateRange(start, end)
        assertEquals(2, range.count())
    }

    @Test
    fun `Given inclusive range, when first and last accessed, then matches start and end`() {
        val start = LocalDate.now()
        val end = start.plusDays(10)
        val range = LocalDateRange(start, end)
        assertEquals(start, range.first())
        assertEquals(end, range.last())
    }

    @Test
    fun `Given range, when iterated, then covers all dates without duplication`() {
        val start = LocalDate.now()
        val end = start.plusDays(5)
        val range = LocalDateRange(start, end)
        val dates = mutableListOf<LocalDate>()
        for (date in range) {
            assertTrue("Date $date is duplicated in iteration.", date !in dates)
            dates.add(date)
        }
        assertEquals(6, dates.size)
    }
}
