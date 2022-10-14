package com.snow.dreamdiary.feature_dreams.presentation.navigation;

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.snow.dreamdiary.feature_dreams.presentation.optionsoverview.OptionsOverviewScreen
import com.snow.dreamdiary.feature_dreams.presentation.searchmode.SearchModeScreen

@Composable
fun StartNavGraphDreams(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = DreamScreens.OptionsOverviewScreen.route
    ) {
        composable(
            route = DreamScreens.SearchModeScreen.route
        ) {
            SearchModeScreen(navController = navController)
        }
        composable(
            route = DreamScreens.OptionsOverviewScreen.route
        ) {
            OptionsOverviewScreen(navController = navController)
        }
    }
}