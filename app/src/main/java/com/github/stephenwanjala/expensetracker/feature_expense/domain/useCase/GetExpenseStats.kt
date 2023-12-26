package com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase

import com.github.stephenwanjala.expensetracker.feature_expense.domain.repository.ExpenseRepository

class GetExpenseStats(private val repository: ExpenseRepository){
    suspend operator fun invoke(): ExpenseStats {
        return ExpenseStats(
            totalAmountSpentToday = repository.getTotalAmountSpentToday(),
            totalAmountSpentThisWeek = repository.getTotalAmountSpentThisWeek(),
            totalAmountSpentThisMonth = repository.getTotalAmountSpentThisMonth()
        )
    }
}

data class ExpenseStats(
    val totalAmountSpentToday: Double,
    val totalAmountSpentThisWeek: Double,
    val totalAmountSpentThisMonth: Double
)
