package com.snow.dreamdiary.feature_dreams.presentation.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBarDreams(
    navController: NavHostController
) {
    val screens = listOf(
        BottomNavDreamScreens.DreamStartScreen,
        BottomNavDreamScreens.OverviewDreamsScreen,
        BottomNavDreamScreens.AddEditDreamScreen,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDest = navBackStackEntry?.destination

    BottomAppBar() {
        screens.forEach {

        }
    }
}

@Composable
fun RowScope.BottomNavEntry(
    screens: BottomNavDreamScreens,
    currentDest: NavDestination?,
    navController: NavController
) {

}