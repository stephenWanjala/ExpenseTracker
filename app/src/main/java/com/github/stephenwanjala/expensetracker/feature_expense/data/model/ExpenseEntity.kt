package com.github.stephenwanjala.expensetracker.feature_expense.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Category
import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Expense
import com.github.stephenwanjala.expensetracker.feature_expense.domain.util.toLocalDateTime

@Entity
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val amount: Double,
    val category: String,
    val date: Long,
    val description: String,
) {
    fun ExpenseEntity.toExpense(): Expense =
        Expense(
            title = title,
            amount = amount,
            description = description,
            date = date.toLocalDateTime(),
            category = Category.valueOf(category)
        )
}