package com.fredosuala.momentum.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.fredosuala.momentum.presentation.addhabit.AddHabitScreen
import com.fredosuala.momentum.presentation.blog.BlogScreen
import com.fredosuala.momentum.presentation.home.HomeScreen
import com.fredosuala.momentum.presentation.profile.ProfileScreen
import com.fredosuala.momentum.presentation.settings.SettingsScreen
import com.fredosuala.momentum.presentation.ui.theme.MomentumAppTheme
import com.fredosuala.momentum.presentation.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MomentumAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ScreenContent()
                }
            }
        }
    }
}

@Composable
private fun ScreenContent() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    Scaffold(

        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarState.value,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
                content = {
                    BottomNavigation(
                        backgroundColor = MaterialTheme.colors.primaryVariant,
                    ) {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination
                        bottomNavDestination.forEach { screen ->
                            BottomNavigationItem(
                                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                onClick = {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                unselectedContentColor = MaterialTheme.colors.surface,
                                selectedContentColor = MaterialTheme.colors.secondary,
                                icon = {
                                    Icon(
                                        imageVector = screen.icons,
                                        contentDescription = null
                                    )
                                }
                            )
                        }
                    }
                }
            )
        },
        scaffoldState = scaffoldState
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            Modifier.padding(innerPadding)
        ) {
            navigation(startDestination = Constants.HOMESCREEN, route = Constants.HABITS) {
                composable(Constants.HOMESCREEN) { HomeScreen(navController, bottomBarState) }
                composable(Constants.ADDHABITSCREEN) { AddHabitScreen(navController, bottomBarState) }
            }
            composable(Constants.BLOGSCREEN) { BlogScreen() }
            composable(Constants.PROFILESCREEN) { ProfileScreen() }
            composable(Constants.SETTINGSSCREEN) { SettingsScreen() }

        }
    }
}

private val bottomNavDestination = listOf<Screen>(Screen.Home, Screen.Blog, Screen.Profile, Screen.Settings)

sealed class Screen(val route: String, val icons: ImageVector) {
    object Home : Screen(Constants.HABITS, Icons.Outlined.Home)
    object Blog : Screen(Constants.BLOGSCREEN, Icons.Outlined.AccountBox)
    object Profile : Screen(Constants.PROFILESCREEN, Icons.Outlined.Person)
    object Settings : Screen(Constants.SETTINGSSCREEN, Icons.Outlined.Settings)
}