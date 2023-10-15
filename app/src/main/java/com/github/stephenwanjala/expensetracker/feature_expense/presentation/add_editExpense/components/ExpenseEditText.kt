package com.github.stephenwanjala.expensetracker.feature_expense.presentation.add_editExpense.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ExpenseEditText(
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    placeHolder: String,
    text: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
        capitalization = KeyboardCapitalization.Words,
        keyboardType = KeyboardType.Text
    ),
    singleLine: Boolean = true,
    onTextChange: (String) -> Unit
) {

    OutlinedTextField(
        value = text,
        onValueChange = { value ->
            onTextChange(value)
        },
        placeholder = {
            Text(text = placeHolder, textAlign = TextAlign.Center)
        },
        shape = RoundedCornerShape(20.dp),
        maxLines = maxLines,
        modifier = modifier.padding(16.dp),
        keyboardOptions = keyboardOptions,
        singleLine = singleLine

    )

}