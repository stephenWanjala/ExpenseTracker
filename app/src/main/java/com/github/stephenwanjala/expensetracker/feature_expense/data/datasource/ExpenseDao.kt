package com.github.stephenwanjala.expensetracker.feature_expense.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.github.stephenwanjala.expensetracker.feature_expense.data.model.ExpenseEntity
import com.github.stephenwanjala.expensetracker.feature_expense.data.model.ExpenseSummary
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM ExpenseEntity")
    fun getAllExpenses(): Flow<List<ExpenseEntity>>

    @Upsert
    suspend fun insertExpense(expenseEntity: ExpenseEntity)

    @Delete
    suspend fun deleteExpense(expenseEntity: ExpenseEntity)

    @Query("SELECT * FROM expenseentity WHERE id = :expenseId")
    suspend fun getExpenseById(expenseId: Int): ExpenseEntity?


    @Query("SELECT category, date, SUM(amount) as totalAmount FROM ExpenseEntity WHERE date >= :startDate AND date <= :endDate GROUP BY category, date")
    fun getExpensesForWeek(startDate: Long, endDate: Long): Flow<List<ExpenseSummary>>


    @Query("SELECT * FROM ExpenseEntity WHERE category = :category AND date  = :date")
    fun getExpensesForCategoryAndDate(category: String, date: Long): Flow<List<ExpenseEntity>>

    @Query("SELECT category, date, SUM(amount) AS totalAmount FROM ExpenseEntity GROUP BY category")
    fun getExpensesGroupedByCategoryAndDate(): Flow<List<ExpenseSummary>>

}