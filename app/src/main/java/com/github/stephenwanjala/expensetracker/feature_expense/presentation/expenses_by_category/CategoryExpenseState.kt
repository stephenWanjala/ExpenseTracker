package com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category

import com.github.stephenwanjala.expensetracker.feature_expense.data.model.ExpenseSummary
import com.github.stephenwanjala.expensetracker.feature_expense.domain.ExpenseOrder
import com.github.stephenwanjala.expensetracker.feature_expense.domain.OrderType
import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Category
import com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase.CategorizedDailyExpense
import java.time.LocalDate

data class CategoryExpenseState(
    val order: ExpenseOrder = ExpenseOrder.Date(OrderType.Descending),
    val expenses: List<ExpenseSummary> = emptyList(),
    val expensesCat: List<CategorizedDailyExpense> = emptyList(),
    val orderSectionVisible: Boolean = false,
    val categories: List<Category> =emptyList(),
    val selectedCategory: String = "",
    val selectedDate: LocalDate = LocalDate.now(),
)
