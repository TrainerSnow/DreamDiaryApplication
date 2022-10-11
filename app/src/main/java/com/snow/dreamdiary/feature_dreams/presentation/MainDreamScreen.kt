package com.snow.dreamdiary.feature_dreams.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.snow.dreamdiary.feature_dreams.presentation.navigation.BottomNavBarDreams
import com.snow.dreamdiary.feature_dreams.presentation.navigation.BottomNavGraphDreams
import com.snow.dreamdiary.ui.theme.DreamDiaryApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDreamScreen() {
    DreamDiaryApplicationTheme {
        val navController: NavHostController = rememberNavController()

        Scaffold(
            bottomBar = {
                BottomNavBarDreams(
                    navController = navController
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .padding(it)
            ) {
                BottomNavGraphDreams(navController = navController)
            }
        }
    }
}