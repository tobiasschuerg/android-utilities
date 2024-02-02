package com.tobiasschuerg.datetime

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar

class DateUtilsKtTest {
    @Test
    fun `localDateToDate converts correctly`() {
        val localDate = LocalDate.of(2020, 1, 1)
        val date = DateUtils.localDateToDate(localDate)
        val convertedBackLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

        assertEquals(localDate, convertedBackLocalDate)
    }

    @Test
    fun `dateToEpochDays calculates correctly`() {
        val calendar =
            Calendar.getInstance().apply {
                set(2020, Calendar.JANUARY, 1, 0, 0, 0)
                set(Calendar.MILLISECOND, 0)
            }
        val date = calendar.time
        val expectedEpochDays = LocalDate.of(2020, 1, 1).toEpochDay()

        assertEquals(expectedEpochDays, DateUtils.dateToEpochDays(date))
    }

    @Test
    fun `dateToLocalDate converts correctly`() {
        val calendar =
            Calendar.getInstance().apply {
                set(2020, Calendar.JANUARY, 1, 0, 0, 0)
                set(Calendar.MILLISECOND, 0)
            }
        val date = calendar.time
        val expectedLocalDate = LocalDate.of(2020, 1, 1)

        assertEquals(expectedLocalDate, date.dateToLocalDate())
    }
}
