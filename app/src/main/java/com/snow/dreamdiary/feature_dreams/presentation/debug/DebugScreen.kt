package com.snow.dreamdiary.feature_dreams.presentation.debug

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.snow.dreamdiary.common.presentation.components.DreamPreviewCard
import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.presentation.behaviouranalysis.DreamBehaviourAnalysisScreen

@Composable
fun DebugScreen(navController: NavHostController) {
    DreamBehaviourAnalysisScreen(navController = navController)
}