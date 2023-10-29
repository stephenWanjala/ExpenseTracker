package com.github.stephenwanjala.expensetracker.feature_expense.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val amount: Double,
    val category: String,
    val date: Long,
    val description: String,
)
