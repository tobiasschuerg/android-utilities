package com.tobiasschuerg.datetime

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDateTime

class LocalDateTimeUtilsKtTest {
    @Test
    fun `Given LocalDateTime, when converting to UTC millis, then result is correct`() {
        val dateTime = LocalDateTime.of(2020, 1, 1, 0, 0)
        val expectedMillis = 1577836800000 // This is the epoch millisecond representation of 2020-01-01T00:00 in UTC.

        assertEquals(expectedMillis, dateTime.toUtcMillis())
    }
}
