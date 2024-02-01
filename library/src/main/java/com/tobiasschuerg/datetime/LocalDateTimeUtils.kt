package com.tobiasschuerg.datetime

import java.time.LocalDateTime
import java.time.ZoneOffset

fun LocalDateTime.toUtcMillis(): Long = toInstant(ZoneOffset.UTC).toEpochMilli()
