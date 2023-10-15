package com.github.stephenwanjala.expensetracker.feature_expense.domain.repository

import com.github.stephenwanjala.expensetracker.feature_expense.data.model.ExpenseEntity
import com.github.stephenwanjala.expensetracker.feature_expense.data.model.ExpenseSummary
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    fun getAllExpenses(): Flow<List<ExpenseEntity>>

    suspend fun insertExpense(expenseEntity: ExpenseEntity)
    suspend fun deleteExpense(expenseEntity: ExpenseEntity)
    suspend fun getExpenseById(id: Int): ExpenseEntity?

    fun getExpensesForWeek(startDate: Long, endDate: Long): Flow<List<ExpenseSummary>>

    fun getExpensesForCategoryAndDate(category: String, date: Long): Flow<List<ExpenseEntity>>

    fun getExpensesGroupedByCategoryAndDate(): Flow<List<ExpenseSummary>>
}