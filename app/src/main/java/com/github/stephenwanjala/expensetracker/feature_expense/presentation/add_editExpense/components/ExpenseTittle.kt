package com.github.stephenwanjala.expensetracker.feature_expense.presentation.add_editExpense.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.add_editExpense.AddEditExpenseEvent

@Composable
fun ExpenseTittle(
    modifier: Modifier = Modifier,
    tittle: String,
    onTextChangeEvent: (AddEditExpenseEvent) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            text = "Expense Tittle",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium
        )
        ExpenseEditText(
            modifier=Modifier.fillMaxWidth(),
            placeHolder = "Enter Expense Tittle",
            onTextChange = {
                onTextChangeEvent(AddEditExpenseEvent.EnteredTittle(it))
            },
            text = tittle
        )
    }
}
