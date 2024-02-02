package com.tobiasschuerg.datetime

import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit

object DateUtils {
    /**
     * Converts a LocalDate to java.util.Date.
     * @param localDate The LocalDate to convert.
     * @return A Date representing the start of the LocalDate in the system's default timezone.
     */
    @Deprecated(
        "Use Java Time API's ZonedDateTime or Instant for modern time handling.",
        level = DeprecationLevel.WARNING,
    )
    @JvmStatic
    fun localDateToDate(localDate: LocalDate): Date {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
    }

    /**
     * Converts a java.util.Date to the number of epoch days.
     * @param date The Date to convert.
     * @return The number of days since the epoch (1970-01-01) for the Date's midnight in UTC.
     */
    @Deprecated(
        "Consider using LocalDate.toEpochDay() for modern date handling.",
        level = DeprecationLevel.WARNING,
    )
    @JvmStatic
    fun dateToEpochDays(date: Date): Long {
        val calendar = Calendar.getInstance()
        calendar.time = date
        val offset = calendar[Calendar.ZONE_OFFSET] + calendar[Calendar.DST_OFFSET]
        val utcMillis = calendar.timeInMillis + offset
        return TimeUnit.MILLISECONDS.toDays(utcMillis)
    }
}

/**
 * Converts java.util.Date to LocalDate.
 * @param date The Date to convert.
 * @return A LocalDate representing the date in the system's default timezone.
 */
@Deprecated(
    "Use java.time.Instant and ZonedDateTime for modern time handling.",
    level = DeprecationLevel.WARNING,
)
fun Date.dateToLocalDate(): LocalDate = toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
