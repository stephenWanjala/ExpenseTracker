package com.github.stephenwanjala.expensetracker.feature_expense.domain.repository

import com.github.stephenwanjala.expensetracker.feature_expense.data.model.ExpenseEntity
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    fun getAllExpenses(): Flow<List<ExpenseEntity>>

    suspend fun insertExpense(expenseEntity: ExpenseEntity)
    suspend fun deleteExpense(expenseEntity: ExpenseEntity)
    suspend fun getExpenseById(id: Int): ExpenseEntity?
}