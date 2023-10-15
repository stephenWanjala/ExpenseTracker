package com.github.stephenwanjala.expensetracker.feature_expense.data.model

data class ExpenseSummary(
    val category: String,
    val date: Long,
    val totalAmount: Double,
)
