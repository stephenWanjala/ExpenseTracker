package com.github.stephenwanjala.expensetracker.feature_expense.presentation.add_editExpense.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Category
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.add_editExpense.AddEditExpenseEvent
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ExpenseCategories(
    modifier: Modifier = Modifier,
    selectedCategory: Category,
    onCategorySelect: (AddEditExpenseEvent) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = "Category",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),

            ) {
            Category.entries.forEach { category ->
                CategoryChip(
                    category = category,
                    isSelected = selectedCategory == category,
                    onCategorySelect = {
                        onCategorySelect(AddEditExpenseEvent.EnteredCategory(it))
                    }
                )
            }

        }
    }

}

@Composable
fun CategoryChip(category: Category, isSelected: Boolean, onCategorySelect: (Category) -> Unit) {
    ElevatedAssistChip(onClick = {
        onCategorySelect(category)
    }, label = {
        Text(text = category.name.lowercase(Locale.ROOT)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() })
    },
        colors = AssistChipDefaults.elevatedAssistChipColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        ),
        leadingIcon = {
            Icon(
                imageVector = category.imageVector(),
                contentDescription = category.name,
                tint = if (isSelected) MaterialTheme.colorScheme.onPrimary else LocalContentColor.current
            )
        }
    )

}
