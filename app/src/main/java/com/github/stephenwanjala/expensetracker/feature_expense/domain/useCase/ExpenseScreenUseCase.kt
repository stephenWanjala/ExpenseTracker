package com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase

data class ExpenseScreenUseCase(
    private val categorizedDailyExpense: GetCategorizedDailyExpense,
    private val saveExpense: SaveExpense,
)
