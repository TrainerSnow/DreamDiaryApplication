package com.snow.dreamdiary.feature_dreams.presentation.searchmode

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Scale
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.R
import com.snow.dreamdiary.common.presentation.components.BigIconButton
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SearchModeScreen(
    navController: NavHostController,
    viewModel: SearchModeViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
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
        verticalArrangement = Arrangement.Center,

        ) {

        BigIconButton(
            onclick = { viewModel.onEvent(SearchModeEvent.OpenSearchModifiers) },
            text = stringResource(id = R.string.search_by_modifiers),
            icon = Icons.Rounded.Person
        )

        Spacer(modifier = Modifier.height(24.dp))

        BigIconButton(
            onclick = { viewModel.onEvent(SearchModeEvent.OpenSearchComfortness) },
            text = stringResource(id = R.string.search_by_comfortness),
            icon = Icons.Rounded.Scale
        )

        Spacer(modifier = Modifier.height(24.dp))

        BigIconButton(
            onclick = { viewModel.onEvent(SearchModeEvent.OpenSearchDreamt) },
            text = stringResource(id = R.string.search_by_time),
            icon = Icons.Rounded.Timer
        )
    }
}