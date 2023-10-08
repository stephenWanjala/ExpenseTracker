package com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true,)
@Composable
fun DayWeekSummaryExpenseScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold {paddingValues ->
            Text(text = "$paddingValues")

        }
    }
}