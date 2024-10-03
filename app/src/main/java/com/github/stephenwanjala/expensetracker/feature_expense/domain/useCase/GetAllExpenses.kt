package com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase

import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Expense
import com.github.stephenwanjala.expensetracker.feature_expense.domain.repository.ExpenseRepository
import com.github.stephenwanjala.expensetracker.feature_expense.domain.util.toExpense
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllExpenses(private val repository: ExpenseRepository) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<List<Expense>> =
        repository.getAllExpenses()
            .map { it.map { expenseEntity -> expenseEntity.toExpense() } }
}