package com.snow.dreamdiary.feature_dreams.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.snow.dreamdiary.feature_dreams.presentation.addeditdream.AddEditDreamScreen
import com.snow.dreamdiary.feature_dreams.presentation.startscreen.DreamStartScreen
import com.snow.dreamdiary.feature_dreams.presentation.viewdreams.ViewDreamsScreen

const val KEY_DREAM_ID = "dream_id"

@Composable
fun BottomNavGraphDreams(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = DreamScreens.DreamStartScreen.route
    ) {
        composable(
            route = DreamScreens.DreamStartScreen.route
        ) {
            DreamStartScreen(navController = navController)
        }
        composable(
            route = DreamScreens.AddEditDreamScreen.route,
            arguments = listOf(
                navArgument(
                    name = KEY_DREAM_ID
                ) {
                    type = NavType.IntType
                    defaultValue = 22
                })
        ) {
            AddEditDreamScreen(navController = navController)
        }
        composable(
            route = DreamScreens.OverviewDreamsScreen.route
        ) {
            ViewDreamsScreen(navController = navController)
        }
    }
}