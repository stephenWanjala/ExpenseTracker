package com.github.stephenwanjala.expensetracker.feature_expense.domain

sealed interface OrderType {
    data object Ascending: OrderType
    data object Descending: OrderType
}