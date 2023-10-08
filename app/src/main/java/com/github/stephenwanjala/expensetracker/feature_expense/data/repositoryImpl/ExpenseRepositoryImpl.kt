package com.github.stephenwanjala.expensetracker.feature_expense.data.repositoryImpl

import com.github.stephenwanjala.expensetracker.feature_expense.data.datasource.ExpenseDao
import com.github.stephenwanjala.expensetracker.feature_expense.data.model.ExpenseEntity
import com.github.stephenwanjala.expensetracker.feature_expense.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(
    private val dao: ExpenseDao
) : ExpenseRepository {
    override fun getAllExpenses(): Flow<List<ExpenseEntity>> = dao.getAllExpenses()

    override suspend fun insertExpense(expenseEntity: ExpenseEntity) =
        dao.insertExpense(expenseEntity)

    override suspend fun deleteExpense(expenseEntity: ExpenseEntity) =
        dao.deleteExpense(expenseEntity)

    override suspend fun getExpenseById(id: Int): ExpenseEntity? =
        dao.getExpenseById(expenseId = id)
}