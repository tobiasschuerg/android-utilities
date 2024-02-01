package com.tobiasschuerg.datetime

import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * Converts this [LocalDateTime] to the number of milliseconds from the epoch of 1970-01-01T00:00:00Z.
 *
 * @return The number of milliseconds from the epoch of 1970-01-01T00:00:00Z in the UTC timezone.
 */
fun LocalDateTime.toUtcMillis(): Long = toInstant(ZoneOffset.UTC).toEpochMilli()
