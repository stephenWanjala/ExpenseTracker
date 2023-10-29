package com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase

import com.github.stephenwanjala.expensetracker.feature_expense.data.model.ExpenseEntity
import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Category
import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Expense
import com.github.stephenwanjala.expensetracker.feature_expense.domain.repository.ExpenseRepository
import com.github.stephenwanjala.expensetracker.feature_expense.domain.util.toExpense
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform


class GetExpensesGivenDateAndCategory(private val repository: ExpenseRepository) {
    operator fun invoke(category: Category, date: Long): Flow<List<Expense>> =
        repository.getExpensesForCategoryAndDate(category = category.name, date = date).transform {
            it.map(ExpenseEntity::toExpense)
        }
}