package com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.stephenwanjala.expensetracker.feature_expense.data.model.ExpenseSummary
import com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase.CategorizedDailyExpense
import com.github.stephenwanjala.expensetracker.feature_expense.domain.util.relativeTime
import com.github.stephenwanjala.expensetracker.feature_expense.domain.util.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryItem(
    modifier: Modifier = Modifier,
    expenseSummary: ExpenseSummary,
    onSummaryClick: (ExpenseSummary) -> Unit,
) {
    val expenseSummaryDate by remember {
        mutableStateOf(expenseSummary.date.toLocalDateTime())
    }

    Card(
        onClick = { onSummaryClick(expenseSummary) },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = expenseSummary.category, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${expenseSummaryDate.dayOfWeek.name}, ${expenseSummaryDate.month.name} ${expenseSummaryDate.dayOfMonth}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "KSH. ${expenseSummary.totalAmount}",
                    textAlign = TextAlign.End,
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryItem(
    modifier: Modifier = Modifier,
    expenseCat: CategorizedDailyExpense,
    onSummaryClick: (CategorizedDailyExpense) -> Unit,
) {

    Card(
        onClick = { onSummaryClick(expenseCat) },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = expenseCat.category.name, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = expenseCat.date.relativeTime(System.currentTimeMillis()),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "KSH. ${expenseCat.amount}",
                    textAlign = TextAlign.End,
                )
            }
        }
    }

}