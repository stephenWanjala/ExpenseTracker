package com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses

import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase.CategorizedDailyExpense
import com.github.stephenwanjala.expensetracker.feature_expense.domain.util.relativeTime
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses.components.ExpenseItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun Expenses(
    expenseCat: CategorizedDailyExpense,
    navigator: DestinationsNavigator,
    viewModel: ExpensesViewModel = hiltViewModel()
) {
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val state = viewModel.state.collectAsState().value
    LaunchedEffect(key1 = state.expenses.isEmpty()) {
        viewModel.onEvent(ExpensesEvent.UpdateExpenseCat(expenseCat))
        viewModel.onEvent(ExpensesEvent.GetExpenses(expenseCat = expenseCat))
    }
    if (state.expenses.isEmpty()) {
        viewModel.onEvent(ExpensesEvent.GetExpenses(expenseCat = expenseCat))
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                MediumTopAppBar(
                    title = {
                        Text(
                            text = expenseCat.category.name,
                            textAlign = TextAlign.Center
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = navigator::popBackStack) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBackIosNew,
                                contentDescription = "Back"
                            )
                        }
                    },
                    scrollBehavior = scrollBehaviour,
                )
            }) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .nestedScroll(scrollBehaviour.nestedScrollConnection)
            ) {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Total  Spent  ${expenseCat.date.relativeTime(System.currentTimeMillis())}")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Ksh ${expenseCat.amount}")
                    }

                }
                Spacer(modifier = Modifier.height(6.dp))
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(state.expenses) {
                        ExpenseItem(onExpenseItemClick = {}, expense = it)
                    }
                }
            }

        }
    }
}