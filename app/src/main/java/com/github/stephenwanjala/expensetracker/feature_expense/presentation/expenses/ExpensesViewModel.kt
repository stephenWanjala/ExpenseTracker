package com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase.CategorizedDailyExpense
import com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase.GetExpensesOfADay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val categoryDayUseCase: GetExpensesOfADay
) : ViewModel() {

    private val _state = MutableStateFlow(ExpensesState())
    val state =
        _state.asStateFlow()

    init {
        state.value.expenseCat?.let { getExpenses(it) }
    }


    fun onEvent(event: ExpensesEvent) {
        when (event) {
            is ExpensesEvent.DeleteExpense -> TODO()
            is ExpensesEvent.EditExpense -> TODO()
            is ExpensesEvent.GetExpenses -> {
                getExpenses(
                    expenseCat = event.expenseCat
                )
            }

            is ExpensesEvent.UpdateExpenseCat -> {
                _state.update { it.copy(expenseCat = event.expenseCat) }
                getExpenses(event.expenseCat)
            }
        }
    }

    private fun getExpenses(expenseCat: CategorizedDailyExpense) {
        viewModelScope.launch {
            categoryDayUseCase(expenseCat).collect { expenses ->
                _state.update { it.copy(expenses = expenses) }
            }
        }
    }

}