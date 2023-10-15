package com.github.stephenwanjala.expensetracker.feature_expense.presentation.add_editExpense

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.add_editExpense.components.AddExpenseAppBar
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.add_editExpense.components.CreateExpenseButton
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.add_editExpense.components.ExpenseAmount
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.add_editExpense.components.ExpenseCategories
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.add_editExpense.components.ExpenseDescription
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.add_editExpense.components.ExpenseTittle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AddEditExpenseScreen(
    navigator: DestinationsNavigator,
    viewModel: AddEditExpenseViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState()
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        AddExpenseAppBar(closePage = { navigator.popBackStack() })
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = paddingValues)
                .verticalScroll(state = rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            ExpenseTittle(tittle = state.value.tittle, onTextChangeEvent = viewModel::onEvent)

            ExpenseAmount(amount = state.value.amount, onTextChangeEvent = viewModel::onEvent)
            ExpenseCategories(
                selectedCategory = state.value.category,
                onCategorySelect = viewModel::onEvent
            )
            ExpenseDescription(
                body = state.value.description,
                onTextChangeEvent = viewModel::onEvent
            )
            CreateExpenseButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                buttonText = "Create Expense",
                enabled = state.value.saveButtonEnabled,
                onclick = {
                    viewModel.onEvent(AddEditExpenseEvent.SaveExpense)
                    navigator.popBackStack()
                })
        }
    }
}