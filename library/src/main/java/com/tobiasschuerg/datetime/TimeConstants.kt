package com.tobiasschuerg.datetime

object TimeConstants {
    const val MINUTES_PER_HOUR = 60
    const val SECONDS_PER_MINUTE = 60
    const val SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR
    const val ONE_SECOND_IN_MILLIS: Long = 1000

    @Deprecated("Use SECONDS_PER_HOUR instead.", ReplaceWith("TimeConstants.SECONDS_PER_HOUR"))
    const val ONE_HOUR_IN_SECONDS = SECONDS_PER_HOUR
}
