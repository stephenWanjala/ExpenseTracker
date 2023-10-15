package com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.DailyExpenseViewModel
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.components.TopBarOrder
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun DayWeekSummaryExpenseScreen(
    viewModel: DailyExpenseViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val state = viewModel.state.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
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
        }

    }

}

