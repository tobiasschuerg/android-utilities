package com.tobiasschuerg.datetime
import java.time.LocalTime

/**
 * Calculates the total number of minutes elapsed since midnight for this LocalTime instance.
 *
 * @return Total minutes since midnight.
 */
fun LocalTime.totalMinutesSinceMidnight(): Int = hour * TimeConstants.MINUTES_PER_HOUR + minute
