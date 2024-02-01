package com.tobiasschuerg.utils

import com.tobiasschuerg.datetime.LocalDateRange
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

class LocalDateRangeTest {

    @Test
    fun testSingleDayRange() {
        val range = LocalDateRange(LocalDate.now(), LocalDate.now())
        Assert.assertEquals(1, range.count())
        Assert.assertEquals(LocalDate.now(), range.first())
        Assert.assertEquals(LocalDate.now(), range.last())
    }

    @Test
    fun testTwoDayRange() {
        val range = LocalDateRange(LocalDate.now(), LocalDate.now().plusDays(1))
        Assert.assertEquals(2, range.count())
        Assert.assertEquals(LocalDate.now(), range.first())
        Assert.assertEquals(LocalDate.now().plusDays(1), range.last())
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test that start must be before end date`() {
        LocalDateRange(LocalDate.now().plusDays(1), LocalDate.now())
    }

    @Test
    fun `test length calculation`() {
        val range = LocalDateRange(LocalDate.now(), LocalDate.now().plusDays(7))

        Assert.assertEquals(8, range.getLength())
        Assert.assertEquals(range.getLength(), range.count().toLong())
    }
}
