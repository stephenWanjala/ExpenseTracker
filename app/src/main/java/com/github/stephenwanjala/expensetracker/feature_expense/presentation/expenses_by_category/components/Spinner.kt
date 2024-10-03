package com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Spinner(
    list: List<Category>,
    preselected: Category,
    onSelectionChanged: (category: Category) -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: () -> Boolean
) {

    var selected by remember { mutableStateOf(preselected) }
    var expanded by remember { mutableStateOf(false) } // initial value

    LaunchedEffect(Unit) {
        onSelectionChanged(selected)
    }


    Card(

        modifier = modifier
            .clickable {
                expanded = !expanded
            }
            .padding(16.dp),
        shape = CardDefaults.outlinedShape,
        colors = CardDefaults.outlinedCardColors(),
        elevation = CardDefaults.outlinedCardElevation(),
        border = CardDefaults.outlinedCardBorder(),
        onClick = { expanded = !expanded },
        enabled = isEnabled()

    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {

            Text(
                text = selected.name,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Icon(Icons.Outlined.ArrowDropDown, null, modifier = Modifier.padding(8.dp))

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()   // delete this modifier and use .wrapContentWidth() if you would like to wrap the dropdown menu around the content
            ) {
                list.forEach { listEntry ->


                    DropdownMenuItem(
                        onClick = {
                            selected = listEntry
                            expanded = false
                            onSelectionChanged(selected)
                        },
                        text = {
                            Text(
                                text = listEntry.name,
                                modifier = Modifier
//                                    .wrapContentWidth()  //optional instead of fillMaxWidth
                                    .fillMaxWidth()
                                    .align(Alignment.Start)
                            )
                        },
                    )
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun SpinnerSample_Preview() {
    MaterialTheme {
        val spinnerData =
            listOf(Category.INVESTMENT, Category.FOOD, Category.TRAVEL, Category.UTILITIES)

        Spinner(
            spinnerData,
            preselected = spinnerData.first(),
            onSelectionChanged = { },
            modifier = Modifier.fillMaxWidth(),
            isEnabled = {
                true
            }
        )
    }
}

data class SpinnerData(
    val id: Int,
    val name: String
)