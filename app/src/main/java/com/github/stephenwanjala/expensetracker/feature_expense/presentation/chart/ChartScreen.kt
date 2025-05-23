package com.github.stephenwanjala.expensetracker.feature_expense.presentation.chart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aay.compose.barChart.BarChart
import com.aay.compose.barChart.model.BarParameters
import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Category
import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Expense
import com.github.stephenwanjala.expensetracker.ui.theme.ExpenseTrackerTheme
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ChartScreen(
    viewModel: ChartScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    val expenses = state.value.expenses
    val scope = rememberCoroutineScope()
    val tabs = listOf(ChartType.Daily, ChartType.Monthly)
    val pagerState = rememberPagerState(pageCount = { tabs.size })

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(bottom = 80.dp) //Work Around Add padding to avoid navigation bar
            ) {
                TabRow(
                    selectedTabIndex = pagerState.currentPage,
                    modifier = Modifier.fillMaxWidth()
//                        .padding(16.dp),
                ) {
                    tabs.forEachIndexed { index, chartType ->
                        Tab(
                            text = { Text(chartType.name) },
                            selected = pagerState.currentPage == index,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            unselectedContentColor = MaterialTheme.colorScheme.onSurface,
                            selectedContentColor = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                HorizontalPager(
                    state = pagerState,
                    contentPadding = paddingValues
                ) { page ->
                    when (page) {
                        0 -> {
                            DailyExpenseChart(
                                expenses
                            )
                        }

                        1 -> {
                            MonthlyExpenseBarChart(expenses)
                        }
                    }
                }
            }
        }
    }
}


enum class ChartType {
    Daily,
    Monthly
}


@Composable
fun DailyExpenseChart(
    expenses: List<Expense>,
    modifier: Modifier = Modifier,
) {
    val groupedExpenses =
        expenses.groupBy { it.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) }
            .mapValues { entry ->
                entry.value.groupBy { it.category }
                    .mapValues { categoryEntry -> categoryEntry.value.sumOf { it.amount } }
            }

    val categories = expenses.map { it.category }.distinct()
    val categoryColors = mapOf(
        Category.FOOD to Color(0xFF6C3428),
        Category.TRAVEL to Color(0xFFBA704F),
        Category.ENTERTAINMENT to Color(0xFFDFA878),
        Category.PERSONAL to Color(0xFF81BE88)
    )
    val days = groupedExpenses.keys.sorted()
    val barParameters = categories.map { category ->
        BarParameters(
            dataName = category.name,
            data = days.map { day -> groupedExpenses[day]?.get(category) ?: 0.0 },
            barColor = categoryColors[category] ?: MaterialTheme.colorScheme.primary
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        BarChart(
            chartParameters = barParameters,
            gridColor = MaterialTheme.colorScheme.onSurfaceVariant,
            xAxisData = days,
            isShowGrid = true,
            animateChart = true,
            showGridWithSpacer = true,
            yAxisStyle = TextStyle(
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
            ),
            xAxisStyle = TextStyle(
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.W400
            ),
            yAxisRange = 15,
            barWidth = 20.dp
        )
    }
}


@Composable
fun MonthlyExpenseBarChart(expenses: List<Expense>) {
    // Group expenses by month and category
    val groupedExpenses =
        expenses.groupBy { it.date.format(DateTimeFormatter.ofPattern("yyyy-MM")) }
            .mapValues { entry ->
                entry.value.groupBy { it.category }
                    .mapValues { categoryEntry -> categoryEntry.value.sumOf { it.amount } }
            }

    // Prepare data for the chart
    val categories = expenses.map { it.category }.distinct()
    val months = groupedExpenses.keys.sorted()
    val categoryColors = mapOf(
        Category.FOOD to Color(0xFF6C3428),
        Category.TRAVEL to Color(0xFFBA704F),
        Category.ENTERTAINMENT to Color(0xFFDFA878),
        Category.PERSONAL to Color(0xFF81BE88)
    )
    val barParameters = categories.map { category ->
        BarParameters(
            dataName = category.name,
            data = months.map { month -> groupedExpenses[month]?.get(category) ?: 0.0 },
            barColor = categoryColors[category] ?: MaterialTheme.colorScheme.primary
        )
    }

    Box(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        BarChart(
            chartParameters = barParameters,
            gridColor = MaterialTheme.colorScheme.onSurfaceVariant,
            xAxisData = months,
            isShowGrid = true,
            animateChart = true,
            showGridWithSpacer = true,
            yAxisStyle = TextStyle(
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
            ),
            xAxisStyle = TextStyle(
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.W400
            ),
            yAxisRange = 15,
            barWidth = 20.dp
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BarChartPreview() {
    val expenses = listOf(
        Expense("Groceries", 500.0, Category.FOOD, LocalDateTime.now(), "Weekly groceries"),
        Expense("Gas", 300.0, Category.TRAVEL, LocalDateTime.now().plusDays(2), "Fill up the tank"),
        Expense(
            "Dinner",
            400.0,
            Category.FOOD,
            LocalDateTime.now().plusDays(5),
            "Dinner with friends"
        ),
        Expense("Lunch", 200.0, Category.FOOD, LocalDateTime.now().plusDays(1), "Lunch at work"),
        Expense("Coffee", 50.0, Category.FOOD, LocalDateTime.now().minusDays(30), "Morning coffee"),
        Expense(
            "Subscription",
            150.0,
            Category.ENTERTAINMENT,
            LocalDateTime.now().plusDays(3),
            "Monthly subscription"
        ),
        Expense(
            "Maintenance",
            1000.0,
            Category.TRAVEL,
            LocalDateTime.now().plusDays(4),
            "Car maintenance"
        ),
        Expense("Pet Food", 250.0, Category.FOOD, LocalDateTime.now().plusDays(30), "Pet food"),
        Expense(
            "Beauty Products",
            600.0,
            Category.PERSONAL,
            LocalDateTime.now().plusDays(7),
            "Beauty products"
        ),
        Expense(
            "Groceries",
            100.0,
            Category.FOOD,
            LocalDateTime.now().plusDays(8),
            "Monthly groceries"
        )
    )
    ExpenseTrackerTheme {
        MonthlyExpenseBarChart(expenses)
    }
}