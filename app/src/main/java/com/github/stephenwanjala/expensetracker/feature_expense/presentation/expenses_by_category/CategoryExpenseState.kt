package com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category

import com.github.stephenwanjala.expensetracker.feature_expense.data.model.ExpenseSummary
import com.github.stephenwanjala.expensetracker.feature_expense.domain.ExpenseOrder
import com.github.stephenwanjala.expensetracker.feature_expense.domain.OrderType
import com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase.CategorizedDailyExpense

data class CategoryExpenseState(
    val order: ExpenseOrder = ExpenseOrder.Date(OrderType.Descending),
    val expenses: List<ExpenseSummary> = emptyList(),
    val orderSectionVisible: Boolean = false,
)
