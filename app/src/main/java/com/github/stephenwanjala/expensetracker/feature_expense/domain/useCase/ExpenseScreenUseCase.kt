package com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase

data class ExpenseScreenUseCase(
    val expenses: GetAllExpenses,
    val categoryDailyExpense: GetCategorizedDailyExpense,
    val saveExpense: SaveExpense,
    val getCategories: GetAllCategoriesUseCase,
)