package com.github.stephenwanjala.expensetracker.feature_expense.presentation.add_editExpense.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseAppBar(modifier: Modifier = Modifier, closePage: () -> Unit) {
    CenterAlignedTopAppBar(modifier = modifier, title = {
        Text(
            text = "Create Expense",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    },
        actions = {
            IconButton(
                onClick = closePage,
            ) {
                Icon(imageVector = Icons.Outlined.Close, contentDescription = "close")
            }
        }
    )
}