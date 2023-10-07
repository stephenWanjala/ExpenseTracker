package com.github.stephenwanjala.expensetracker.domain.model

data class Expense(
    val id: Int,
    val title: String,
    val amount: Float,
    val category: Category,
    val date: Long,
    val isIncome: Boolean,
    val description: String,
)