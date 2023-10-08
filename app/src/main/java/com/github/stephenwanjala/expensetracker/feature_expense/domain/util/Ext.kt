package com.github.stephenwanjala.expensetracker.feature_expense.domain.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun LocalDateTime.toLong(): Long {
    val instant = this.atZone(ZoneId.systemDefault()).toInstant()
    return instant.toEpochMilli()
}

fun Long.toLocalDateTime(zoneId: ZoneId = ZoneId.systemDefault()): LocalDateTime {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(this), zoneId)
}