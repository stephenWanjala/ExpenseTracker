package com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase

data class ExpenseScreenUseCase(
    val expenseSummary: GetExpenseSummary,
    val categoryDailyExpense: GetCategorizedDailyExpense,
    val saveExpense: SaveExpense,
    val getCategories: GetAllCategoriesUseCase,
)