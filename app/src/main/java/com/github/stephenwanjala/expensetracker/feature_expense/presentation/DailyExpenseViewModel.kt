package com.github.stephenwanjala.expensetracker.feature_expense.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.stephenwanjala.expensetracker.feature_expense.domain.ExpenseOrder
import com.github.stephenwanjala.expensetracker.feature_expense.domain.OrderType
import com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase.ExpenseScreenUseCase
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.CategoryExpenseState
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.ExpenseEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DailyExpenseViewModel @Inject constructor(
    private val expenseScreenUseCase: ExpenseScreenUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(CategoryExpenseState())
    val state =
        _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(500), CategoryExpenseState())
    init {
        getExpenses(ExpenseOrder.Date(OrderType.Descending))
        viewModelScope.launch {
            combine(
                expenseScreenUseCase.getCategories(),
                expenseScreenUseCase.expenses()
            ) { categories, expenses ->
                _state.update {
                    val selectedCategory = categories.getOrNull(0)?.name ?: ""
                    it.copy(
                        expenses = expenses,
                        categories = categories,
                        selectedCategory = selectedCategory
                    ).also {
                        filterMonthlyExpenses(
                            it.selectedDate,
                            selectedCategory
                        )
                    }
                }

            }.collect()
        }
    }

    fun onEvent(event: ExpenseEvent) {
        when (event) {
            is ExpenseEvent.Order -> {
                if (event.expenseOrder::class == state.value.order::class &&
                    state.value.order.orderType == event.expenseOrder.orderType
                ) return
                _state.update {
                    it.copy(order = event.expenseOrder).also { getExpenses(event.expenseOrder) }
                }

            }

            is ExpenseEvent.ToggleOrderSection -> _state.update { it.copy(orderSectionVisible = !it.orderSectionVisible) }

            is ExpenseEvent.SelectCategory -> {
                _state.update {
                    it.copy(selectedCategory = event.category).also {
                        filterMonthlyExpenses(
                            it.selectedDate,
                            event.category
                        )
                    }
                }
            }

            is ExpenseEvent.SelectDate -> {
                _state.update {
                    it.copy(
                        selectedDate = LocalDate.of(
                            event.year,
                            event.month + 1,
                            event.dayOfMonth
                        )
                    )
                }
                filterMonthlyExpenses(
                    LocalDate.of(
                        event.year,
                        event.month + 1,
                        event.dayOfMonth
                    ),
                    state.value.selectedCategory
                )
            }
        }
    }

    private fun getExpenses(expenseOrder: ExpenseOrder) {
        viewModelScope.launch {
            expenseScreenUseCase.categoryDailyExpense(expenseOrder).collectLatest { expenses ->
                _state.update { it.copy(expensesCat = expenses, order = expenseOrder) }
            }
        }
    }

    private fun filterMonthlyExpenses(date: LocalDate, category: String) {
        val monthlyExpenses = _state.value.expenses.filter {
            date.year == it.date.toLocalDate().year &&
            date.month == it.date.toLocalDate().month &&
                    it.category.name == category
        }
        _state.update { it.copy(monthlyExpenses = monthlyExpenses) }
    }
}
