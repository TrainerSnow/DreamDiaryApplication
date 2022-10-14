package com.snow.dreamdiary.feature_dreams.presentation.optionsoverview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.R
import com.snow.dreamdiary.feature_dreams.presentation.navigation.DreamScreens
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OptionsOverviewScreen(
    navController: NavHostController,
    viewModel: OptionsOverviewViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true,){
        viewModel.actionFlow.collectLatest {
            when (it) {
                is OptionsOverviewViewModel.UIEvent.GoToScreen -> navController.navigate(it.screen.route)
            }
        }
    }

    Column(
        modifier =
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TODO: Change this from button to good looking squares

        Button(
            onClick = { viewModel.onEvent(OptionsOverviewEvent.GoToScreen(DreamScreens.SearchModeScreen)) }
        ) {
            Text(
                text = stringResource(id = R.string.search_dreams)
            )
        }

        for( i in 0 until 4){
            Button(
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = stringResource(id = R.string.search_dreams)
                )
            }
        }
    }
}