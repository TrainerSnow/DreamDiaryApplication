package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bymodifier

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bydreamt.SearchDreamtViewModel

@Composable
fun SearchModifierScreen(
    viewModel: SearchModifierViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.tertiary)
            .fillMaxSize(),
        contentAlignment = Alignment.Center

    ){
        Text(
            text = "SearchModifierScreen",
            style = MaterialTheme.typography.displayMedium
        )
    }
}