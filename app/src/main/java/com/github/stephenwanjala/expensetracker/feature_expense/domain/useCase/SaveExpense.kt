package com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase

import com.github.stephenwanjala.expensetracker.feature_expense.data.model.ExpenseEntity
import com.github.stephenwanjala.expensetracker.feature_expense.domain.repository.ExpenseRepository

class SaveExpense(private val repository: ExpenseRepository) {
    suspend operator fun invoke(
        title: String,
        amount: Double,
        category: String,
        date: Long,
        description: String,
    ) = repository.insertExpense(
        ExpenseEntity(
            title = title,
            amount = amount,
            category = category,
            date = date,
            description = description,
        )
    )
}