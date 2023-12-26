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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.stephenwanjala.expensetracker.R
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.DailyExpenseViewModel
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.destinations.AddEditExpenseScreenDestination
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.destinations.ExpensesDestination
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
                    contentDescription = stringResource(R.string.add_expense)
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
                                text = stringResource(R.string.no_expenses_recorded_yet),
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
                items(state.value.expensesCat) { categorizedDailyExpense ->
                    SummaryItem(expenseCat = categorizedDailyExpense, onSummaryClick = {
                        navigator.navigate(ExpensesDestination(expenseCat = it))
                    })
                }
            }
        }

    }

}

