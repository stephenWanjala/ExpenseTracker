package com.github.stephenwanjala.expensetracker.feature_expense.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.github.stephenwanjala.expensetracker.R
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.destinations.AddEditExpenseScreenDestination
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.destinations.ChartScreenDestination
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.destinations.DayWeekSummaryExpenseScreenDestination
import com.github.stephenwanjala.expensetracker.feature_expense.presentation.destinations.Destination
import com.github.stephenwanjala.expensetracker.ui.theme.ExpenseTrackerTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.ramcosta.composedestinations.utils.toDestinationsNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.Transparent.toArgb(),
                Color.Transparent.toArgb(),

                )
        )
        setContent {
            ExpenseTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navHostEngine = rememberAnimatedNavHostEngine(
                        navHostContentAlignment = Alignment.TopCenter,
                        rootDefaultAnimations = RootNavGraphDefaultAnimations(
                            enterTransition = {
                                scaleIn(transformOrigin = TransformOrigin.Center)
                            },
                            exitTransition = {
                                scaleOut(transformOrigin = TransformOrigin.Center)
                            }
                        )
                    )
                    val navController = navHostEngine.rememberNavController()
                    val currentDestination =
                        navController.currentBackStackEntryAsState().value?.destination
                    navController.currentBackStackEntryAsState().value?.destination
                    val bottomBarItems: List<BottomBarDestination> = listOf(
                        BottomBarDestination.Home,
                        BottomBarDestination.GRAPH,
                    )
                    val showBottomBar = currentDestination?.route in listOf(
                        BottomBarDestination.Home.direction.route,
                        BottomBarDestination.GRAPH.direction.route,
                    )
                    Scaffold(
                        bottomBar = {
                            AnimatedVisibility(visible = showBottomBar) {
                                BottomBar(navController = navController, items = bottomBarItems)
                            }
                        },
                        floatingActionButton = {
                            if (currentDestination?.route == DayWeekSummaryExpenseScreenDestination.route) {
                                FloatingActionButton(
                                    onClick = {
                                        navController.toDestinationsNavigator()
                                            .navigate(AddEditExpenseScreenDestination)
                                    },
                                    modifier = Modifier.navigationBarsPadding()
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Add Expense"
                                    )
                                }
                            }
                        }
                    ) { paddingValues ->
                        val unUsedPaddingValue = paddingValues.calculateBottomPadding()
                        DestinationsNavHost(
                            navGraph = NavGraphs.root,
                            navController = navController,
                            engine = navHostEngine,
//                            modifier = Modifier.padding(paddingValues),
                        )


                    }


                }
            }
        }
    }
}


enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val label: Int
) {
    Home(
        DayWeekSummaryExpenseScreenDestination,
        Icons.Filled.Home,
        Icons.Outlined.Home,
        R.string.home
    ),

    //
    GRAPH(
        ChartScreenDestination,
        Icons.Filled.BarChart,
        Icons.Outlined.BarChart,
        R.string.graph
    )
}

@Composable
fun BottomBar(
    navController: NavController,
    items: List<BottomBarDestination>
) {
    val currentDestination: Destination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination
    val destinationsNavigator: DestinationsNavigator = navController.toDestinationsNavigator()
    NavigationBar {
        items.forEach { destination ->
            NavigationBarItem(
                selected = currentDestination == destination.direction,
                onClick = {
                    if (currentDestination != destination.direction) {
                        destinationsNavigator.navigate(destination.direction) {
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (currentDestination == destination.direction) destination.selectedIcon else destination.unselectedIcon,
                        contentDescription = stringResource(destination.label)
                    )
                },
                label = { Text(stringResource(destination.label)) },
            )
        }
    }
}







