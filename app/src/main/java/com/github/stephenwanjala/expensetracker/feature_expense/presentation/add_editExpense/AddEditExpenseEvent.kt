package com.github.stephenwanjala.expensetracker.feature_expense.presentation.add_editExpense

import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Category

sealed interface AddEditExpenseEvent {
    data class EnteredTittle(val value: String) : AddEditExpenseEvent
    data class EnteredAmount(val value: Double) : AddEditExpenseEvent

    data class EnteredCategory(val value: Category) : AddEditExpenseEvent

    data class EnteredDescription(val value: String) : AddEditExpenseEvent

    data object SaveExpense : AddEditExpenseEvent
}