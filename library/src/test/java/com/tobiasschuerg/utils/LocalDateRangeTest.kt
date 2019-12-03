package com.tobiasschuerg.utils

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.jakewharton.threetenabp.AndroidThreeTen
import com.tobiasschuerg.datetime.LocalDateRange
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.threeten.bp.LocalDate


@RunWith(RobolectricTestRunner::class)
class LocalDateRangeTest {

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        AndroidThreeTen.init(context)
    }

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

}