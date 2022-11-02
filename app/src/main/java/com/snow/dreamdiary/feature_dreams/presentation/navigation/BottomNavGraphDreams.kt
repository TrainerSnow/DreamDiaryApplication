package com.snow.dreamdiary.feature_dreams.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.snow.dreamdiary.feature_dreams.presentation.bottomnav.addeditdream.AddEditDreamScreen
import com.snow.dreamdiary.feature_dreams.presentation.bottomnav.startscreen.DreamStartScreen
import com.snow.dreamdiary.feature_dreams.presentation.bottomnav.viewdreams.ViewDreamsScreen
import com.snow.dreamdiary.feature_dreams.presentation.viewdream.ViewDreamScreen

const val KEY_DREAM_ID = "dream_id"

@Composable
fun BottomNavGraphDreams(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavScreens.DreamStartScreen.route
    ) {
        composable(
            route = BottomNavScreens.DreamStartScreen.route
        ) {
            DreamStartScreen(navControllerBottomNav = navController)
        }
        composable(
            route = BottomNavScreens.AddEditDreamScreen.route,
            arguments = listOf(
                navArgument(
                    name = KEY_DREAM_ID
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                })
        ) {
            AddEditDreamScreen(navController = navController)
        }
        composable(
            route = BottomNavScreens.ViewDreamScreen.route,
            arguments = listOf(
                navArgument(
                    name = KEY_DREAM_ID
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                })
        ) {
            ViewDreamScreen(navController = navController)
        }
        composable(
            route = BottomNavScreens.OverviewDreamsScreen.route
        ) {
            ViewDreamsScreen(navController = navController)
        }
    }
}