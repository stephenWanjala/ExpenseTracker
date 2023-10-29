package com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses

import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Expense
import com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase.CategorizedDailyExpense

data class ExpensesState(
    val expenses: List<Expense> = emptyList(),
    val expenseCat:CategorizedDailyExpense? =null
)