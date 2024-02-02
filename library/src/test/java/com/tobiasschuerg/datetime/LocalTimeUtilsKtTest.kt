package com.tobiasschuerg.datetime

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalTime

class LocalTimeUtilsKtTest {
    @Test
    fun `totalMinutesSinceMidnight at midnight`() {
        val time = LocalTime.MIDNIGHT
        val expected = 0
        assertEquals(expected, time.totalMinutesSinceMidnight())
    }

    @Test
    fun `totalMinutesSinceMidnight in the afternoon`() {
        val time = LocalTime.of(15, 30)
        val expected = 15 * 60 + 30 // 15 hours and 30 minutes since midnight
        assertEquals(expected, time.totalMinutesSinceMidnight())
    }

    @Test
    fun `totalMinutesSinceMidnight just before midnight`() {
        val time = LocalTime.of(23, 59)
        val expected = 23 * 60 + 59 // 23 hours and 59 minutes since midnight
        assertEquals(expected, time.totalMinutesSinceMidnight())
    }
}
