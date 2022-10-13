package com.snow.dreamdiary.feature_dreams.presentation.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.snow.dreamdiary.feature_dreams.presentation.BottomNavDreamScreens

@Composable
fun BottomNavBarDreams(
    navController: NavHostController
) {
    val screens = listOf(
        BottomNavDreamScreens.AddEditDreamScreen,
        BottomNavDreamScreens.DreamStartScreen,
        BottomNavDreamScreens.OverviewDreamsScreen,
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
    screen: BottomNavDreamScreens,
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
            Text(text = stringResource(id = screen.title))
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