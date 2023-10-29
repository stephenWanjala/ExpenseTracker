package com.github.stephenwanjala.expensetracker.feature_expense.domain.model

import java.time.LocalDateTime


data class Expense(
    val title: String,
    val amount: Double,
    val category: Category,
    val date: LocalDateTime,
    val description: String,
)