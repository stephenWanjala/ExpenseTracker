package com.github.stephenwanjala.expensetracker.feature_expense.presentation.add_editExpense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase.SaveExpense
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditExpenseViewModel @Inject constructor(
    private val saveExpense: SaveExpense,
) : ViewModel() {
    private val _state = MutableStateFlow(AddEditEditExpenseState())
    val state = _state.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(500),
        initialValue = AddEditEditExpenseState()
    )

    fun onEvent(event: AddEditExpenseEvent) {
        when (event) {
            is AddEditExpenseEvent.EnteredAmount -> {
                event.value.takeIf { it > 0 }?.let {
                    _state.update { it.copy(amount = event.value.toString()) }
                }
            }

            is AddEditExpenseEvent.EnteredCategory -> _state.update { it.copy(category = event.value) }
            is AddEditExpenseEvent.EnteredTittle -> _state.update { it.copy(tittle = event.value) }
            is AddEditExpenseEvent.EnteredDescription -> _state.update { it.copy(description = event.value) }
            AddEditExpenseEvent.SaveExpense -> {
                saveExpense()
            }
        }
        _state.update {
            it.copy(
                saveButtonEnabled = (state.value.amount.isNotBlank() && state.value.amount.isNotEmpty() && state.value.tittle.isNotEmpty() && state.value.tittle.isNotEmpty())
            )
        }
    }


    private fun saveExpense() {
        viewModelScope.launch {
            saveExpense(
                title = state.value.tittle,
                category = state.value.category.name,
                amount = state.value.amount.toDouble(),
                description = state.value.description,
                date = System.currentTimeMillis()

            )
        }
    }
}
