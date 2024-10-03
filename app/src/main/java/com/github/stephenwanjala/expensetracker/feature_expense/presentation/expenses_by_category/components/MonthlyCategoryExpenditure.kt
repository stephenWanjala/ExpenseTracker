package com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Category
import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Expense
import java.time.LocalDate

@Composable
fun MonthlyCategoryExpenditure(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    expenses: List<Expense>,
    onCategorySelected: (Category) -> Unit,
    onDateSelected: (Int, Int, Int) -> Unit,
    preselectedCategory: Category? = null,
    defaultSelectedDate: LocalDate

) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AnimatedVisibility(categories.isNotEmpty()) {
            println("categories.isNotEmpty()")
            println(expenses)
            Row(
                modifier = modifier
                    .fillMaxWidth(),
//                .padding(8.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spinner(
                    list = categories,
                    preselected = preselectedCategory ?: categories.first(),
                    onSelectionChanged = { category ->
                        onCategorySelected(category)
                    },
                    modifier = Modifier.weight(1f),
                    isEnabled = { true },
                )
//            Spacer(modifier = Modifier.weight(0.1f))
                ExpenseDatePicker(
                    modifier = Modifier.weight(1f),
                    onSelectDate = { year, month, dayOfMonth ->
                        onDateSelected(year, month, dayOfMonth)

                    },
                    defaultSelectedDate = defaultSelectedDate
                )

            }
        }
        expenses.forEach { expenseSummary ->
            Text(text = expenseSummary.category.name)
            Text(text = expenseSummary.date.toString())
            Text(text = expenseSummary.title)
            Text(text = expenseSummary.description)
            Text(text = expenseSummary.amount.toString())
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

}