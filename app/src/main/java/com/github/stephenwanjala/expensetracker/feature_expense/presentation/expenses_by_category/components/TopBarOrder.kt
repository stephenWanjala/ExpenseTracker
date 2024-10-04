package com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.stephenwanjala.expensetracker.feature_expense.domain.ExpenseOrder
import com.github.stephenwanjala.expensetracker.feature_expense.domain.OrderType
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.ExpenseEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarOrder(
    modifier: Modifier = Modifier,
    expenseOrder: ExpenseOrder = ExpenseOrder.Date(OrderType.Descending),
    orderSectionVisible: Boolean = false,
    onToggleOrderEvent: (ExpenseEvent) -> Unit,
    onExpenseOrderChangeEvent: (ExpenseEvent) -> Unit,
    scrollBehaviour: TopAppBarScrollBehavior
) {
    Column(
        modifier = modifier
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Daily Expenses",
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            navigationIcon = {
               IconButton(onClick = {}) {
                   Icon(
                       imageVector = Icons.Default.Menu,
                       contentDescription = "Menu"
                   )
               }
            },
            actions = {
                IconButton(onClick = {
                    onToggleOrderEvent(ExpenseEvent.ToggleOrderSection)
                }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.Sort, contentDescription = "Icon Sort")
                }
            },
            scrollBehavior = scrollBehaviour
        )
        AnimatedVisibility(
            visible = orderSectionVisible,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = modifier.statusBarsPadding()
        ) {
            OrderSection(
                expenseOrder = expenseOrder,
                onExpenseOrderChange = {
                    onExpenseOrderChangeEvent(ExpenseEvent.Order(it))
                },
            )
        }
    }
}