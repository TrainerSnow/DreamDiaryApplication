package com.snow.dreamdiary.feature_dreams.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.snow.dreamdiary.feature_dreams.presentation.dailysurvey.DailySurveyScreen
import com.snow.dreamdiary.feature_dreams.presentation.debug.DebugScreen
import com.snow.dreamdiary.feature_dreams.presentation.history.HistoryScreen
import com.snow.dreamdiary.feature_dreams.presentation.optionsoverview.OptionsOverviewScreen
import com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bycomfortness.SearchComfortnessScreen
import com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bydreamt.SearchDreamtScreen
import com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bymodifier.SearchModifierScreen
import com.snow.dreamdiary.feature_dreams.presentation.searchmode.SearchModeScreen
import com.snow.dreamdiary.feature_dreams.presentation.stats.advanced.AdvancedStatsScreen
import com.snow.dreamdiary.feature_dreams.presentation.stats.simple.SimpleStatsScreen
import com.snow.dreamdiary.feature_dreams.presentation.viewdream.ViewDreamScreen
import com.snow.dreamdiary.feature_dreams.presentation.viewsearcheddreams.ViewDreamsSearchedScreen

const val KEY_MODE = "mode"

@Composable
fun StartNavGraphDreams(
    navController: NavHostController,
    bottomNavController: NavHostController
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
            route = DreamScreens.DebugScreen.route
        ) {
            DebugScreen(navController = navController)
        }
        composable(
            route = DreamScreens.HistoryScreen.route
        ) {
            HistoryScreen(navController = navController)
        }
        composable(
            route = DreamScreens.AdvancedStatsScreen.route
        ) {
            AdvancedStatsScreen(navController = navController)
        }
        composable(
            route = DreamScreens.DailySurveyScreen.route
        ) {
            DailySurveyScreen(navController = navController)
        }
        composable(
            route = DreamScreens.SimpleStatsScreen.route
        ) {
            SimpleStatsScreen(navController = navController)
        }
        composable(
            route = DreamScreens.SearchDreamtScreen().route,
            arguments = listOf(
                navArgument(
                    name = KEY_MODE
                ) {
                    type = NavType.StringType
                })
        ) {
            SearchDreamtScreen(navController = navController)
        }
        composable(
            route = DreamScreens.SearchComfortnessScreen().route,
            arguments = listOf(
                navArgument(
                    name = KEY_MODE
                ) {
                    type = NavType.StringType
                })
        ) {
            SearchComfortnessScreen(navController = navController)
        }
        composable(
            route = DreamScreens.SearchModifierScreen().route,
            arguments = listOf(
                navArgument(
                    name = KEY_MODE
                ) {
                    type = NavType.StringType
                })
        ) {
            SearchModifierScreen(navController = navController)
        }
        composable(
            route = DreamScreens.ViewSearchedDreamsScreen().route,
            arguments = listOf(
                navArgument(
                    name = KEY_MODE
                ) {
                    type = NavType.StringType
                })
        ) {
            ViewDreamsSearchedScreen(navController = navController)
        }
        composable(
            route = DreamScreens.ViewDreamScreen().route,
            arguments = listOf(
                navArgument(
                    name = KEY_DREAM_ID
                ) {
                    type = NavType.IntType
                })
        ) {
            ViewDreamScreen(bottomNavController = bottomNavController)
        }

    }
}