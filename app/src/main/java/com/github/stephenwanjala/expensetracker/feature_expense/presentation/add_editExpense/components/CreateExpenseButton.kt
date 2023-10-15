package com.github.stephenwanjala.expensetracker.feature_expense.presentation.add_editExpense.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CreateExpenseButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    enabled: Boolean = false,
    onclick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth(0.8f)
            .padding(16.dp),
        onClick = onclick,
        shape = RoundedCornerShape(8.dp),
        enabled = enabled
    ) {
        Text(
            text = buttonText, modifier = Modifier,
            textAlign = TextAlign.Center
        )
    }
}