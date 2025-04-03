package com.github.stephenwanjala.expensetracker.feature_expense.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.stephenwanjala.expensetracker.feature_expense.navigation.ExpenseNavHost
import com.github.stephenwanjala.expensetracker.feature_expense.navigation.TopLevelDestinations
import com.github.stephenwanjala.expensetracker.ui.theme.ExpenseTrackerTheme
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
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination: NavDestination? = navBackStackEntry?.destination

                    val showBottomNav =
                        TopLevelDestinations.entries.map { it.direction::class }.any { route ->

                            currentDestination?.hierarchy?.any {
                                it.hasRoute(route)
                            } == true
                        }
                    val destinationItems = listOf(
                        TopLevelDestinations.Home,
                        TopLevelDestinations.Chart
                    )
                    val selectedItemIndex = rememberSaveable { mutableIntStateOf(0) }


                    Scaffold(
                        bottomBar = {
                            AnimatedVisibility(visible = showBottomNav) {
                                BottomBar(
                                    navController = navController,
                                    items = destinationItems,
                                    selectedItemIndex = selectedItemIndex.intValue,
                                    onClick = { index ->
                                        selectedItemIndex.intValue = index
                                    })
                            }
                        },
                    ) { paddingValues ->
                        paddingValues.calculateBottomPadding()
                        ExpenseNavHost(
                            navHostController = navController,
                            modifier = Modifier
                                .padding(paddingValues)
                                .consumeWindowInsets(paddingValues)
                        )


                    }


                }
            }
        }
    }
}


@Composable
fun BottomBar(
    navController: NavController,
    items: List<TopLevelDestinations>,
    selectedItemIndex: Int = 0,
    onClick: (Int) -> Unit
) {

    NavigationBar {
        items.forEachIndexed { index, destination ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    if (selectedItemIndex != index) {
                        onClick(index)
                        navController.navigate(destination.direction) {
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selectedItemIndex == index) destination.selectedIcon else destination.unselectedIcon,
                        contentDescription = stringResource(destination.label)
                    )
                },
                label = { Text(stringResource(destination.label)) },
            )
        }
    }
}







