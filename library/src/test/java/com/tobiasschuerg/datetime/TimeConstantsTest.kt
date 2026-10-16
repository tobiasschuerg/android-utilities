package com.tobiasschuerg.datetime

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.concurrent.TimeUnit

class TimeConstantsTest {
    @Test
    fun `constants match TimeUnit conversions`() {
        assertEquals(TimeUnit.HOURS.toMinutes(1), TimeConstants.MINUTES_PER_HOUR.toLong())
        assertEquals(TimeUnit.MINUTES.toSeconds(1), TimeConstants.SECONDS_PER_MINUTE.toLong())
        assertEquals(TimeUnit.HOURS.toSeconds(1), TimeConstants.SECONDS_PER_HOUR.toLong())
        assertEquals(TimeUnit.SECONDS.toMillis(1), TimeConstants.ONE_SECOND_IN_MILLIS)
    }

    @Suppress("DEPRECATION")
    @Test
    fun `one hour in seconds is 3600`() {
        assertEquals(3600, TimeConstants.ONE_HOUR_IN_SECONDS)
    }
}
