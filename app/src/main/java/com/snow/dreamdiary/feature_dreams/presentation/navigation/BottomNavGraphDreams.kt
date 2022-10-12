package com.snow.dreamdiary.feature_dreams.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.snow.dreamdiary.feature_dreams.presentation.addeditdream.AddEditDreamScreen
import com.snow.dreamdiary.feature_dreams.presentation.startscreen.DreamStartScreen
import com.snow.dreamdiary.feature_dreams.presentation.viewdreams.ViewDreamsScreen

@Composable
fun BottomNavGraphDreams(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavDreamScreens.DreamStartScreen.route
    ) {
        composable(
            route = BottomNavDreamScreens.DreamStartScreen.route
        ) {
            DreamStartScreen(navController = navController)
        }
        composable(
            route = BottomNavDreamScreens.AddEditDreamScreen.route
        ) {
            AddEditDreamScreen(navController = navController)
        }
        composable(
            route = BottomNavDreamScreens.OverviewDreamsScreen.route
        ) {
            ViewDreamsScreen(navController = navController)
        }
    }
}