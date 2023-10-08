package com.github.stephenwanjala.expensetracker.feature_expense.domain

sealed class ExpenseOrder (val orderType: OrderType){
    class Date(orderType: OrderType): ExpenseOrder(orderType)
    class Amount(orderType: OrderType): ExpenseOrder(orderType)

    class Category(orderType: OrderType): ExpenseOrder(orderType)
    fun copy(orderType: OrderType): ExpenseOrder{
        return when(this){
            is Date -> Date(orderType)
            is Amount -> Amount(orderType)
            is Category -> Category(orderType)
        }
    }


}
