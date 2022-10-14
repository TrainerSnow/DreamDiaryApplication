package com.snow.dreamdiary.feature_dreams.presentation.searchmode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SearchModeScreen(
    navController: NavHostController,
    viewModel: SearchModeViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true){
        viewModel.actionFlow.collectLatest {
            when (it) {
                is SearchModeViewModel.UIEvent.GoToScreen -> navController.navigate(it.screen.route)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ){

        // TODO: Change these buttons to custom ones with color and icon

        Button(
            onClick = { viewModel.onEvent(SearchModeEvent.OpenSearchModifiers) }
        ){
            Text(
                text = stringResource(id = R.string.search_by_modifiers)
            )
        }

        Button(
            onClick = { viewModel.onEvent(SearchModeEvent.OpenSearchComfortness) }
        ){
            Text(
                text = stringResource(id = R.string.search_by_comfortness)
            )
        }

        Button(
            onClick = { viewModel.onEvent(SearchModeEvent.OpenSearchDreamt) }
        ){
            Text(
                text = stringResource(id = R.string.search_by_time)
            )
        }
    }
}