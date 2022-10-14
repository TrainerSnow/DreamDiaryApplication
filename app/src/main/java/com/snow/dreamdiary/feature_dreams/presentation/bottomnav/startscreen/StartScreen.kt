package com.snow.dreamdiary.feature_dreams.presentation.bottomnav.startscreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.snow.dreamdiary.feature_dreams.presentation.navigation.StartNavGraphDreams

@Composable
fun DreamStartScreen(
    navControllerBottomNav: NavHostController
) {
    val navController = rememberNavController()

    StartNavGraphDreams(navController = navController)
}