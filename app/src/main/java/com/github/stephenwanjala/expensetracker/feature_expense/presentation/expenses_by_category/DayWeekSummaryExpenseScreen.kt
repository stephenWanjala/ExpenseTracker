package com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.DailyExpenseViewModel
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.destinations.AddEditExpenseScreenDestination
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.components.SummaryItem
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.components.TopBarOrder
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun DayWeekSummaryExpenseScreen(
    viewModel: DailyExpenseViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {
    val state = viewModel.state.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navigator.navigate(AddEditExpenseScreenDestination.route)
                },
                modifier = Modifier.padding(16.dp),

                ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Expense"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TopBarOrder(
                onToggleOrderEvent = viewModel::onEvent,
                onExpenseOrderChangeEvent = viewModel::onEvent,
                orderSectionVisible = state.value.orderSectionVisible,
                expenseOrder = state.value.order
            )
            LazyColumn {
                if (state.value.expensesCat.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No Expenses Recorded Yet",
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }

//                items(state.value.expenses) {item: ExpenseSummary ->
//                    SummaryItem(expenseSummary = item, onSummaryClick = {})
//
//                }
                items(state.value.expensesCat) {
                    SummaryItem(expenseCat = it, onSummaryClick = {})
                }
            }
        }

    }

}

