package com.tobiasschuerg.datetime

import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDateTime

/**
 * Created by Tobias Schrg on 02.02.2024.
 */
class LocalDateTimeUtilsKtTest {

    @Test
    fun `Given LocalDateTime, when converting to UTC millis, then result is correct`() {
        val dateTime = LocalDateTime.of(2020, 1, 1, 0, 0)
        val expectedMillis = 1577836800000 // This is the epoch millisecond representation of 2020-01-01T00:00 in UTC.

        val resultMillis = dateTime.toUtcMillis()

        assertTrue(resultMillis == expectedMillis)
    }

}