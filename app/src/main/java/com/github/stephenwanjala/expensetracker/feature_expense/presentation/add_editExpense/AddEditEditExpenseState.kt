package com.github.stephenwanjala.expensetracker.feature_expense.presentation.add_editExpense

import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Category

data class AddEditEditExpenseState(
    val tittle: String = "",
    val amount: String = "",
    val category: Category = Category.FOOD, //default category,
    val description: String = "",
    val saveButtonEnabled: Boolean = false,
    val expenseSaveError: String = "",

    )
