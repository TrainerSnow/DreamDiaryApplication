package com.snow.dreamdiary.feature_dreams.presentation.optionsoverview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.R
import com.snow.dreamdiary.feature_dreams.presentation.navigation.DreamScreens
import com.snow.dreamdiary.feature_dreams.presentation.optionsoverview.components.OptionsItem
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
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 24.dp)
        ){
            item {
                OptionsItem(
                    text = stringResource(id = R.string.search_dreams),
                    icon = Icons.Rounded.Search,
                    onClick = { viewModel.onEvent(OptionsOverviewEvent.GoToScreen(DreamScreens.SearchModeScreen)) },
                    num = 0
                )
            }

            items(count = 4, key = null){ i ->
                OptionsItem(
                    text = "Item nr $i",
                    icon = Icons.Rounded.Search,
                    onClick = { viewModel.onEvent(OptionsOverviewEvent.GoToScreen(DreamScreens.SearchModeScreen)) },
                    num = i + 1
                )
            }
        }
    }
}