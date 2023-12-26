package com.github.stephenwanjala.expensetracker.feature_expense.data.repositoryImpl

import com.github.stephenwanjala.expensetracker.feature_expense.data.datasource.ExpenseDao
import com.github.stephenwanjala.expensetracker.feature_expense.data.model.ExpenseEntity
import com.github.stephenwanjala.expensetracker.feature_expense.data.model.ExpenseSummary
import com.github.stephenwanjala.expensetracker.feature_expense.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import java.time.YearMonth
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

    override fun getExpensesForWeek(startDate: Long, endDate: Long): Flow<List<ExpenseSummary>> =
        dao.getExpensesForWeek(startDate = startDate, endDate = endDate)

    override fun getExpensesForCategoryAndDate(
        category: String,
        date: Long
    ): Flow<List<ExpenseEntity>> =
        dao.getExpensesForCategoryAndDate(category = category, date = date)

    override fun getExpensesGroupedByCategoryAndDate(): Flow<List<ExpenseSummary>> =
        dao.getExpensesGroupedByCategoryAndDate()

    override suspend fun getTotalAmountSpentToday(): Double {
        val startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0)
        val endOfDay = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59)
        return dao.getTotalAmountSpentToday(startOfDay, endOfDay)
    }

    override suspend fun getTotalAmountSpentThisWeek(): Double {
        val startOfWeek = LocalDateTime.now().with(java.time.DayOfWeek.MONDAY).withHour(0).withMinute(0).withSecond(0)
        val endOfWeek = LocalDateTime.now().with(java.time.DayOfWeek.SUNDAY).withHour(23).withMinute(59).withSecond(59)
        return dao.getTotalAmountSpentThisWeek(startOfWeek, endOfWeek)
    }

    override suspend fun getTotalAmountSpentThisMonth(): Double {
        val startOfMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0)
        val endOfMonth = LocalDateTime.now().withDayOfMonth(YearMonth.from(startOfMonth).lengthOfMonth()).withHour(23).withMinute(59).withSecond(59)
        return dao.getTotalAmountSpentThisMonth(startOfMonth, endOfMonth)
    }


}