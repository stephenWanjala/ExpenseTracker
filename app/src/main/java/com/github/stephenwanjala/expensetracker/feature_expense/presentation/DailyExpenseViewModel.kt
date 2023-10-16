package com.github.stephenwanjala.expensetracker.feature_expense.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.stephenwanjala.expensetracker.feature_expense.domain.ExpenseOrder
import com.github.stephenwanjala.expensetracker.feature_expense.domain.OrderType
import com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase.ExpenseScreenUseCase
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.CategoryExpenseState
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.ExpenseEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyExpenseViewModel @Inject constructor(
    private val expenseScreenUseCase: ExpenseScreenUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(CategoryExpenseState())
    val state =
        _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(500), CategoryExpenseState())
    private var job: Job? = null

    init {
        getExpenses(ExpenseOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: ExpenseEvent) {
        when (event) {
            is ExpenseEvent.Order -> {
                if (event.expenseOrder::class == state.value.order::class &&
                    state.value.order.orderType == event.expenseOrder.orderType
                ) return
                _state.update { it.copy(order = event.expenseOrder) }
                getExpenses(event.expenseOrder)
            }

            is ExpenseEvent.ToggleOrderSection -> _state.update { it.copy(orderSectionVisible = !it.orderSectionVisible) }
        }
    }

    private fun getExpenses(expenseOrder: ExpenseOrder) {
        viewModelScope.launch {

//            expenseScreenUseCase.expenseSummary(expenseOrder).collectLatest { expenses ->
//                _state.update { it.copy(expenses = expenses, order = expenseOrder) }
//            }
            expenseScreenUseCase.categoryDailyExpense(expenseOrder).collectLatest { expenses ->
                _state.update { it.copy(expensesCat = expenses, order = expenseOrder) }
            }
        }

    }

}