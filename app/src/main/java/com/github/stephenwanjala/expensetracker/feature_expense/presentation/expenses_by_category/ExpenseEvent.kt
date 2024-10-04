package com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category

import com.github.stephenwanjala.expensetracker.feature_expense.domain.ExpenseOrder

sealed class ExpenseEvent {
    data class Order(val expenseOrder: ExpenseOrder): ExpenseEvent()
    data object ToggleOrderSection: ExpenseEvent()


}