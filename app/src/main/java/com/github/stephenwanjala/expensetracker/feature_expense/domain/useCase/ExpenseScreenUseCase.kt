package com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase

data class ExpenseScreenUseCase(
    val categorizedDailyExpense: GetCategorizedDailyExpense,
    val saveExpense: SaveExpense,
)
