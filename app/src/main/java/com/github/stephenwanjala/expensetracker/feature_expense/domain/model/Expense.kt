package com.github.stephenwanjala.expensetracker.feature_expense.domain.model

import com.github.stephenwanjala.expensetracker.feature_expense.data.model.ExpenseEntity
import com.github.stephenwanjala.expensetracker.feature_expense.domain.util.toLong
import java.time.LocalDateTime


data class Expense(
    val title: String,
    val amount: Double,
    val category: Category,
    val date: LocalDateTime,
    val description: String,
){
    fun Expense.toExpenseEntity(): ExpenseEntity {
        return ExpenseEntity(
            title = title,
            amount = amount,
            category = category.name,
            date = date.toLong(),
            description = description,
        )
    }
}