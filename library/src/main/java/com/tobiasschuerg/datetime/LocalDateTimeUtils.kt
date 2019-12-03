package com.tobiasschuerg.datetime

import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

fun LocalDateTime.toUtcMillis(): Long = toInstant(ZoneOffset.UTC).toEpochMilli()
