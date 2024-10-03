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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Category
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.DailyExpenseViewModel
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.destinations.AddEditExpenseScreenDestination
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.destinations.ExpensesDestination
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.components.MonthlyCategoryExpenditure
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.components.SummaryItem
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.components.TopBarOrder
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun DayWeekSummaryExpenseScreen(
    viewModel: DailyExpenseViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {
    val state = viewModel.state.collectAsState()
    val preselectedCategory: Category? = state.value.categories.firstOrNull { it.name == state.value.selectedCategory }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navigator.navigate(AddEditExpenseScreenDestination)
                },
                modifier = Modifier.padding(16.dp),

                ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Expense"
                )
            }
        },
        topBar = {
//            CenterAlignedTopAppBar(
//                title = {
//                    Text(
//                        text = "Expenses",
//                        style = MaterialTheme.typography.headlineSmall
//                    )
//                },
//            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
//                .padding(paddingValues)
        ) {

            LazyColumn(contentPadding = paddingValues) {
                item{
                    Text(
                        text = "Monthly Expenses",
                        modifier = Modifier.padding(6.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    MonthlyCategoryExpenditure(
                        modifier = Modifier.padding(8.dp),
                        categories = state.value.categories,
                        expenses = state.value.monthlyExpenses,
                        preselectedCategory= preselectedCategory,
                        onCategorySelected = { category ->
                            viewModel.onEvent(ExpenseEvent.SelectCategory(category.name))
                        },
                        onDateSelected = { year, month, dayOfMonth ->
                            viewModel.onEvent(ExpenseEvent.SelectDate(year, month, dayOfMonth))
                        },
                        defaultSelectedDate = state.value.selectedDate
                    )
                }
                item{
                    TopBarOrder(
                        onToggleOrderEvent = viewModel::onEvent,
                        onExpenseOrderChangeEvent = viewModel::onEvent,
                        orderSectionVisible = state.value.orderSectionVisible,
                        expenseOrder = state.value.order,
                    )
                }
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
                items(state.value.expensesCat) { categorizedDailyExpense ->
                    SummaryItem(expenseCat = categorizedDailyExpense, onSummaryClick = {
                        navigator.navigate(ExpensesDestination(expenseCat = it))
                    })
                }
            }
        }

    }

}

