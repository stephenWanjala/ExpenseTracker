package com.github.stephenwanjala.expensetracker.feature_expense.domain.util

import android.text.format.DateUtils
import com.github.stephenwanjala.expensetracker.feature_expense.data.model.ExpenseEntity
import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Category
import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Expense
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun LocalDateTime.toLong(): Long {
    val instant = this.atZone(ZoneId.systemDefault()).toInstant()
    return instant.toEpochMilli()
}

fun Long.toLocalDateTime(zoneId: ZoneId = ZoneId.systemDefault()): LocalDateTime {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(this), zoneId)
}

fun ExpenseEntity.toExpense(): Expense =
    Expense(
        title = title,
        amount = amount,
        description = description,
        date = date.toLocalDateTime(),
        category = Category.valueOf(category)
    )


fun Expense.toExpenseEntity(): ExpenseEntity {
    return ExpenseEntity(
        title = title,
        amount = amount,
        category = category.name,
        date = date.toLong(),
        description = description,
    )
}


fun Long.relativeTime(): String = DateUtils.getRelativeTimeSpanString(
    this,
    System.currentTimeMillis(),
    DateUtils.SECOND_IN_MILLIS
).toString()


fun LocalDateTime.relativeTime(timeNow: Long): String = DateUtils.getRelativeTimeSpanString(
    this.toLong(),
    timeNow,
    DateUtils.SECOND_IN_MILLIS,
).toString()

fun LocalDateTime.toHrFormat(pattern: String = "hh:mm a"): String {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return this.format(formatter)
}