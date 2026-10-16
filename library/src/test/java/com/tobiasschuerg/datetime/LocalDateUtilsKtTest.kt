package com.tobiasschuerg.datetime

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import java.util.Locale

class LocalDateUtilsKtTest {
    private lateinit var originalLocale: Locale

    @Before
    fun setup() {
        // Set the default locale to a specific one expected by your tests
        originalLocale = Locale.getDefault()
        Locale.setDefault(Locale.GERMANY)
    }

    @After
    fun tearDown() {
        Locale.setDefault(originalLocale)
    }

    @Test
    fun `test week bounds for locale starting on sunday`() {
        Locale.setDefault(Locale.US)
        val date = LocalDate.of(2021, 8, 4) // Wednesday
        assertEquals(LocalDate.of(2021, 8, 1), date.firstDayOfWeek())
        assertEquals(LocalDate.of(2021, 8, 7), date.lastDayOfWeek())
    }

    @Test
    fun `test quarter bounds for every month`() {
        for (month in 1..12) {
            val date = LocalDate.of(2021, month, 15)
            val quarterStartMonth = (month - 1) / 3 * 3 + 1
            assertEquals(LocalDate.of(2021, quarterStartMonth, 1), date.atStartOfQuarter())
            assertEquals(
                LocalDate.of(2021, quarterStartMonth + 2, 1).atEndOfMonth(),
                date.atEndOfQuarter(),
            )
        }
    }

    @Test
    fun `test toUtcMillis conversion`() {
        val date = LocalDate.of(2020, 1, 1) // Example date.
        val expectedMillis = 1577836800000L // Expected UTC milliseconds for 2020-01-01.
        assertEquals(expectedMillis, date.toUtcMillis())
    }

    @Test
    fun `test first day of week based on locale`() {
        val date = LocalDate.of(2021, 8, 4) // Wednesday
        val expectedFirstDayOfWeek = LocalDate.of(2021, 8, 2) // Assuming Locale starts week on Monday
        assertEquals(expectedFirstDayOfWeek, date.firstDayOfWeek())
    }

    @Test
    fun `test last day of week based on locale`() {
        val date = LocalDate.of(2021, 8, 4) // Wednesday
        val expectedLastDayOfWeek = LocalDate.of(2021, 8, 8) // Assuming Locale ends week on Sunday
        assertEquals(expectedLastDayOfWeek, date.lastDayOfWeek())
    }

    @Test
    fun `test start of month`() {
        val date = LocalDate.of(2021, 8, 15) // Middle of August
        val expectedStartOfMonth = LocalDate.of(2021, 8, 1)
        assertEquals(expectedStartOfMonth, date.atStartOfMonth())
    }

    @Test
    fun `test end of month`() {
        val date = LocalDate.of(2021, 2, 10) // February (non-leap year)
        val expectedEndOfMonth = LocalDate.of(2021, 2, 28)
        assertEquals(expectedEndOfMonth, date.atEndOfMonth())
    }

    @Test
    fun `test start of quarter`() {
        val date = LocalDate.of(2021, 4, 10) // Q2
        val expectedStartOfQuarter = LocalDate.of(2021, 4, 1)
        assertEquals(expectedStartOfQuarter, date.atStartOfQuarter())
    }

    @Test
    fun `test end of quarter`() {
        val date = LocalDate.of(2021, 4, 10) // Q2
        val expectedEndOfQuarter = LocalDate.of(2021, 6, 30)
        assertEquals(expectedEndOfQuarter, date.atEndOfQuarter())
    }

    @Test
    fun `test start of year`() {
        val date = LocalDate.of(2021, 5, 10) // Any day in 2021
        val expectedStartOfYear = LocalDate.of(2021, 1, 1)
        assertEquals(expectedStartOfYear, date.atStartOfYear())
    }

    @Test
    fun `test end of year`() {
        val date = LocalDate.of(2021, 5, 10) // Any day in 2021
        val expectedEndOfYear = LocalDate.of(2021, 12, 31)
        assertEquals(expectedEndOfYear, date.atEndOfYear())
    }
}
