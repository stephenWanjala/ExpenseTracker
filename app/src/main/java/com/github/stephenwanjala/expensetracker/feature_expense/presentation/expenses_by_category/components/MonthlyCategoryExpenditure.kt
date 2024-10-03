package com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.stephenwanjala.expensetracker.feature_expense.data.model.ExpenseSummary
import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Category
import java.time.LocalDate

@Composable
fun MonthlyCategoryExpenditure(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    expenseSummaries: List<ExpenseSummary>,
    onCategorySelected: (Category) -> Unit,
    onDateSelected: (Int, Int, Int) -> Unit,
    preselectedCategory: Category? = null,
    defaultSelectedDate: LocalDate

) {

    AnimatedVisibility(categories.isNotEmpty()) {
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
}