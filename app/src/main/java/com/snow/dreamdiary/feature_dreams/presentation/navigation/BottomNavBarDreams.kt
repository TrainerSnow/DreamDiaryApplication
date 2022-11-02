package com.snow.dreamdiary.feature_dreams.presentation.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBarDreams(
    navController: NavHostController
) {
    val screens = listOf(
        BottomNavScreens.AddEditDreamScreen,
        BottomNavScreens.DreamStartScreen,
        BottomNavScreens.OverviewDreamsScreen,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDest = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.surface
    ) {
        screens.forEach {
            BottomNavEntry(
                screen = it,
                currentDest = currentDest,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.BottomNavEntry(
    screen: BottomNavScreens,
    currentDest: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        selected = currentDest?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        },
        label = {
            Text(text = stringResource(id = screen.title), color = MaterialTheme.colorScheme.onSecondaryContainer)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = stringResource(id = screen.title),
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    )
}