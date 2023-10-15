package com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.stephenwanjala.expensetracker.feature_expense.domain.ExpenseOrder
import com.github.stephenwanjala.expensetracker.feature_expense.domain.OrderType


@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    expenseOrder: ExpenseOrder = ExpenseOrder.Date(OrderType.Descending),
    onExpenseOrderChange: (ExpenseOrder) -> Unit
) {


    Column(
        modifier = modifier,
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Category",
                selected = expenseOrder is ExpenseOrder.Category,
                onSelected = { onExpenseOrderChange(ExpenseOrder.Category(expenseOrder.orderType)) })
            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Date",
                selected = expenseOrder is ExpenseOrder.Date,
                onSelected = { onExpenseOrderChange(ExpenseOrder.Date(expenseOrder.orderType)) })
            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Amount",
                selected = expenseOrder is ExpenseOrder.Amount,
                onSelected = { onExpenseOrderChange(ExpenseOrder.Amount(expenseOrder.orderType)) })

        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {

            DefaultRadioButton(
                text = OrderType.Ascending.toString(),
                selected = expenseOrder.orderType is OrderType.Ascending,
                onSelected = { onExpenseOrderChange(expenseOrder.copy(OrderType.Ascending)) })
            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = OrderType.Descending.toString(),
                selected = expenseOrder.orderType is OrderType.Descending,
                onSelected = { onExpenseOrderChange(expenseOrder.copy(OrderType.Descending)) })
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}