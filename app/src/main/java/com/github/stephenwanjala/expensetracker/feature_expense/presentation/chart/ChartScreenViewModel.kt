package com.github.stephenwanjala.expensetracker.feature_expense.presentation.chart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Expense
import com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase.ExpenseScreenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ChartScreenViewModel@Inject constructor(
    private val expenseScreenUseCase: ExpenseScreenUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(ChartScreenState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(500), ChartScreenState())
    init {
        viewModelScope.launch{
            expenseScreenUseCase.expenses().collectLatest { expenses ->
                _state.update { it.copy(expenses = expenses) }
            }
        }
    }
}


data class ChartScreenState(
    val expenses: List<Expense> = emptyList(),
    val selectedDate: LocalDate = LocalDate.now(),
)