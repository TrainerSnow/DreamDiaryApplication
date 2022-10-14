package com.snow.dreamdiary.feature_dreams.presentation.bottomnav.startscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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