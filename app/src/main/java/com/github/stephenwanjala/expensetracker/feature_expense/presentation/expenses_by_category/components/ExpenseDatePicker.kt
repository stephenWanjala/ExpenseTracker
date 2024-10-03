package com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.util.*

@Composable
fun ExpenseDatePicker(
    modifier: Modifier = Modifier,
    defaultSelectedDate: LocalDate ,
    onSelectDate: (year: Int, month: Int, dayOfMonth: Int) -> Unit
) {
    var selectedDate by remember { mutableStateOf("${defaultSelectedDate.dayOfMonth}/${defaultSelectedDate.monthValue}/${defaultSelectedDate.year}") }
    val calendar = Calendar.getInstance().apply {
        set(defaultSelectedDate.year, defaultSelectedDate.monthValue - 1, defaultSelectedDate.dayOfMonth)
    }
    LaunchedEffect(Unit) {
        onSelectDate(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
            onSelectDate(year, month, dayOfMonth)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(onClick = { datePickerDialog.show() }) {
            Text(text = if (selectedDate.isEmpty()) "Select Date" else selectedDate)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DatePickerExamplePreview() {
    MaterialTheme {
        ExpenseDatePicker(defaultSelectedDate = LocalDate.now()) { year, month, dayOfMonth ->
        }
    }
}