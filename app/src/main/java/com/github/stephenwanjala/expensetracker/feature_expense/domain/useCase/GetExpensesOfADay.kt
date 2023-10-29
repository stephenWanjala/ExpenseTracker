package com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase

import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Expense
import com.github.stephenwanjala.expensetracker.feature_expense.domain.repository.ExpenseRepository
import com.github.stephenwanjala.expensetracker.feature_expense.domain.util.toExpense
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetExpensesOfADay(private val repository: ExpenseRepository) {
    operator fun invoke(expenseCat: CategorizedDailyExpense): Flow<List<Expense>> =
        repository.getAllExpenses()
            .map {
                val neList = it.map { expenseEntity ->
                    expenseEntity.toExpense()
                }
                neList.filter { expense: Expense -> expense.category == expenseCat.category && expense.date.dayOfWeek.value == expenseCat.dayOfWeek }
            }


}
