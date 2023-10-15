package com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.stephenwanjala.expensetracker.feature_expense.domain.ExpenseOrder
import com.github.stephenwanjala.expensetracker.feature_expense.domain.OrderType
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.ExpenseEvent

@Composable
fun TopBarOrder(
    modifier: Modifier = Modifier,
    expenseOrder: ExpenseOrder = ExpenseOrder.Date(OrderType.Descending),
    orderSectionVisible: Boolean= false,
    onToggleOrderEvent: (ExpenseEvent)-> Unit,
    onExpenseOrderChangeEvent: (ExpenseEvent) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(
            text = "Daily Expenses",
            style = MaterialTheme.typography.headlineSmall
        )
        IconButton(onClick = {
            onToggleOrderEvent(ExpenseEvent.ToggleOrderSection)
        }) {
            Icon(imageVector = Icons.Default.Sort, contentDescription = "Icon Sort")
        }
    }
    AnimatedVisibility(
        visible = orderSectionVisible,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically()
    ) {
        OrderSection(
            expenseOrder =expenseOrder,
            onExpenseOrderChange = {
                onExpenseOrderChangeEvent(ExpenseEvent.Order(it))
            },
        )
    }
}