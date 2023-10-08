package com.github.stephenwanjala.expensetracker.feature_expense.presentation

import androidx.lifecycle.ViewModel
import com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase.ExpenseScreenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DailyExpenseViewModel @Inject constructor(
    private val expenseScreenUseCase: ExpenseScreenUseCase
) : ViewModel(){

}