package com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase

import com.github.stephenwanjala.expensetracker.feature_expense.data.model.ExpenseSummary
import com.github.stephenwanjala.expensetracker.feature_expense.domain.ExpenseOrder
import com.github.stephenwanjala.expensetracker.feature_expense.domain.OrderType
import com.github.stephenwanjala.expensetracker.feature_expense.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetExpenseSummary(
    private val repository: ExpenseRepository,
) {
    operator fun invoke(expenseOrder: ExpenseOrder = ExpenseOrder.Date(OrderType.Descending)): Flow<List<ExpenseSummary>> {
        return repository.getExpensesGroupedByCategoryAndDate().map { summaryList ->
            when (expenseOrder) {
                is ExpenseOrder.Amount -> {
                    when (expenseOrder.orderType) {
                        OrderType.Ascending -> {
                            summaryList.sortedBy { it.totalAmount }
                        }

                        OrderType.Descending -> summaryList.sortedByDescending { it.totalAmount }
                    }
                }

                is ExpenseOrder.Category -> {
                    when (expenseOrder.orderType) {
                        OrderType.Ascending -> summaryList.sortedBy { it.category }
                        OrderType.Descending -> summaryList.sortedByDescending { it.category }
                    }
                }

                is ExpenseOrder.Date -> {
                    when (expenseOrder.orderType) {
                        OrderType.Ascending -> summaryList.sortedBy { it.date }
                        OrderType.Descending -> summaryList.sortedByDescending { it.date }
                    }
                }
            }

        }
    }
}