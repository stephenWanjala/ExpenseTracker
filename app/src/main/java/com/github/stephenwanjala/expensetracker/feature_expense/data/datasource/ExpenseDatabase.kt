package com.github.stephenwanjala.expensetracker.feature_expense.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.stephenwanjala.expensetracker.feature_expense.data.model.ExpenseEntity

@Database(
    entities = [ExpenseEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract val dao: ExpenseDao
    companion object{
        const val DATABASE_NAME ="expense_db"
    }
}