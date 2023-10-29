package com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses

import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Expense
import com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase.CategorizedDailyExpense

sealed interface ExpensesEvent {
    data class GetExpenses(val expenseCat:CategorizedDailyExpense):ExpensesEvent
    data class DeleteExpense(val expense: Expense):ExpensesEvent
    data class EditExpense(val expense: Expense):ExpensesEvent
    data class UpdateExpenseCat(val expenseCat: CategorizedDailyExpense):ExpensesEvent
}