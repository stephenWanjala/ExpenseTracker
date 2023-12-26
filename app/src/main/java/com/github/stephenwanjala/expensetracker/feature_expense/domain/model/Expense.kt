package com.github.stephenwanjala.expensetracker.feature_expense.domain.model

import java.time.LocalDateTime
import java.util.UUID


data class Expense(
    val title: String,
    val amount: Double,
    val category: Category,
    val date: LocalDateTime,
    val description: String,
    val id: UUID =UUID.randomUUID()
)