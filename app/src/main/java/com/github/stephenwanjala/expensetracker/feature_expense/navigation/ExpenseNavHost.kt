package com.github.stephenwanjala.expensetracker.feature_expense.navigation


import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.github.stephenwanjala.expensetracker.R
import com.github.stephenwanjala.expensetracker.feature_expense.domain.model.Category
import com.github.stephenwanjala.expensetracker.feature_expense.domain.useCase.CategorizedDailyExpense
import com.github.stephenwanjala.expensetracker.feature_expense.navigation.Destination.CategoryExpenses
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.add_editExpense.AddEditExpenseScreen
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.chart.ChartScreen
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses.Expenses
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.expenses_by_category.DayWeekSummaryExpenseScreen
import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


@Composable
fun ExpenseNavHost(modifier: Modifier = Modifier, navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Destination.ExpenseSummary,
        modifier = modifier
    ) {
        composable<Destination.ExpenseSummary> {
            DayWeekSummaryExpenseScreen(onSummaryClick = { expenseCat ->
                navHostController.navigate(
                    CategoryExpenses(
                        category = expenseCat.category,
                        date = expenseCat.date.toEpochMilliseconds(),
                        amount = expenseCat.amount,
                        dayOfWeek = expenseCat.dayOfWeek
                    )
                )
            }, addNewExpense = { navHostController.navigate(Destination.AddEditExpense) })
        }
        composable<CategoryExpenses> { backStackEntry ->
            val expenseCatRoute = backStackEntry.toRoute<CategoryExpenses>()
            val expenseCat = CategorizedDailyExpense(
                category = expenseCatRoute.category,
                date = expenseCatRoute.date.toLocalDateTime(),
                amount = expenseCatRoute.amount,
                dayOfWeek = expenseCatRoute.dayOfWeek
            )
            Expenses(expenseCat = expenseCat, onNavigateUp = navHostController::navigateUp)
        }


        composable<Destination.AddEditExpense> {
            AddEditExpenseScreen(onNavigateUp = navHostController::navigateUp)
        }


        composable<Destination.ChartDestination> { ChartScreen() }
    }
}


sealed interface Destination {
    @Serializable
    data object ExpenseSummary : Destination


    @Serializable
    data class CategoryExpenses(
        val category: Category,
        val date: Long,
        val amount: Double,
        val dayOfWeek: Int,
    ) : Destination


    @Serializable
    data object AddEditExpense : Destination


    @Serializable
    data object ChartDestination : Destination
}




enum class TopLevelDestinations(
    val direction: Destination,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    @StringRes val label: Int
) {
    Home(
        Destination.ExpenseSummary,
        Icons.Outlined.Home,
        Icons.Filled.Home,
        R.string.expense
    ),


    Chart(
        Destination.ChartDestination,
        Icons.Outlined.BarChart,
        Icons.Filled.BarChart,
        R.string.chart
    ),
}




/**
 * Extension function to convert LocalDateTime to Millis.
 *
 * @return epochMilli.
 */
fun LocalDateTime.toEpochMilliseconds(): Long {
    return this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}


/**
 * Extension function to convert a Long to a LocalDateTime.
 *
 * @return The LocalDateTime object.
 */
fun Long.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault())
}


