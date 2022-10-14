package com.snow.dreamdiary.feature_dreams.presentation.navigation;

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.snow.dreamdiary.feature_dreams.presentation.bottomnav.addeditdream.AddEditDreamScreen
import com.snow.dreamdiary.feature_dreams.presentation.optionsoverview.OptionsOverviewScreen
import com.snow.dreamdiary.feature_dreams.presentation.searchconfig.SearchConfigScreen
import com.snow.dreamdiary.feature_dreams.presentation.searchmode.SearchModeScreen

const val KEY_MODE = "mode"

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
        composable(
            route = DreamScreens.SearchConfigScreen.route,
            arguments = listOf(
                navArgument(
                    name = KEY_MODE
                ) {
                    type = NavType.StringType
                })
        ) {
            SearchConfigScreen(navController = navController)
        }
    }
}