package com.footballproject.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.footballproject.ui.screen.empty.EmptyScreen
import com.footballproject.ui.screen.list.ListScreen
import com.footballproject.ui.screen.settings.SettingsScreen
import com.footballproject.ui.screen.team.TeamScreen
import com.footballproject.ui.theme.custom.CustomTheme


@Composable
fun CustomNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: Screen = Screen.List,
) {
    val bottomScreens = listOf(
        Screen.Settings,
        Screen.List,
        Screen.Empty,
    )
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = CustomTheme.colors.tintColor
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomScreens.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon!!, contentDescription = null) },
                        label = {
                            Text(
                                stringResource(screen.name!!),
                                style = CustomTheme.typography.caption
                            )
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = startDestination.route,
            Modifier.padding(innerPadding),
        ) {
            composable(Screen.Settings.route) { SettingsScreen() }
            composable(Screen.List.route) { ListScreen(navController) }
            composable(Screen.Empty.route) { EmptyScreen() }

            composable(
                Screen.Team.route,
                arguments = listOf(navArgument("teamId") { type = NavType.IntType })
            ) {
                TeamScreen(it.arguments?.getInt("teamId"))
            }


        }
    }
}
