package com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase

import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Category
import com.github.stephenwanjala.expensetracker.feature_expense.domain.repository.ExpenseRepository
import com.github.stephenwanjala.expensetracker.feature_expense.domain.util.toLocalDateTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime

class GetCategorizedDailyExpense(private val repository: ExpenseRepository) {
    operator fun invoke(): Flow<List<CategorizedDailyExpense>> {
        return repository.getAllExpenses()
            .map { expenses ->
                // Group expenses by category and date
                val categorizedExpenses = expenses
                    .groupBy { it.category }
                    .flatMap { (category, categoryExpenses) ->
                        categoryExpenses
                            .groupBy { it.date }
                            .map { (date, dateExpenses) ->
                                CategorizedDailyExpense(
                                    category = Category.valueOf(category),
                                    date = date.toLocalDateTime(),
                                    amount = dateExpenses.sumOf { it.amount }
                                )
                            }
                    }
                // Sort the result by date
                categorizedExpenses.sortedByDescending { it.date }
            }
            .flowOn(Dispatchers.IO)
    }
}


data class CategorizedDailyExpense(
    val category: Category,
    val date: LocalDateTime,
    val amount: Double,
)