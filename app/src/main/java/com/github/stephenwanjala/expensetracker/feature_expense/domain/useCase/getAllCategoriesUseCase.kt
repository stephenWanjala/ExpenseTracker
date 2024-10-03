package com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase

import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Category
import com.github.stephenwanjala.expensetracker.feature_expense.domain.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow

class GetAllCategoriesUseCase(
    private val repository: ExpenseRepository
) {
    operator fun invoke(): Flow<List<Category>> = repository.getAllCategories()
}