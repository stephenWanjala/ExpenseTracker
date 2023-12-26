package com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StatsCard(modifier: Modifier = Modifier, stats: Stats) {
    OutlinedCard(modifier = modifier) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stats.type.toString())
            Text(text = stats.amount.toString())

        }
    }
}


@Preview(
    showBackground = true,
)
@Composable
private fun StatsCardPrev() {
    StatsCard(stats = Stats(type = StatsType.DAY, amount = 100.90))
}

data class Stats(val type: StatsType, val amount: Double)
sealed interface StatsType {
    data object DAY : StatsType
    data object WEEK : StatsType
    data object MONTH : StatsType
}
