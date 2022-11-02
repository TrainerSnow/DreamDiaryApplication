package com.snow.dreamdiary.feature_dreams.presentation.optionsoverview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.R
import com.snow.dreamdiary.common.util.TimeFormatUtil
import com.snow.dreamdiary.feature_dreams.presentation.navigation.DreamScreens
import com.snow.dreamdiary.feature_dreams.presentation.optionsoverview.components.OptionsItem
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionsOverviewScreen(
    navController: NavHostController,
    viewModel: OptionsOverviewViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.actionFlow.collectLatest {
            when (it) {
                is OptionsOverviewViewModel.UIEvent.GoToScreen -> navController.navigate(it.screen.route)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.home)
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding(),
                    start = it.calculateStartPadding(LayoutDirection.Ltr),
                    end = it.calculateEndPadding(LayoutDirection.Ltr)
                )
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 24.dp)
            ) {
                item {
                    OptionsItem(
                        text = stringResource(id = R.string.search_dreams),
                        icon = Icons.Rounded.Search,
                        onClick = { viewModel.onEvent(OptionsOverviewEvent.GoToScreen(DreamScreens.SearchModeScreen)) }
                    )
                }

                item {
                    OptionsItem(
                        text = stringResource(id = R.string.daily_survey),
                        icon = Icons.Rounded.EditNote,
                        onClick = { viewModel.onEvent(OptionsOverviewEvent.GoToScreen(DreamScreens.DailySurveyScreen)) }
                    )
                }

                item {
                    OptionsItem(
                        text = stringResource(id = R.string.simple_stats),
                        icon = Icons.Rounded.List,
                        onClick = { viewModel.onEvent(OptionsOverviewEvent.GoToScreen(DreamScreens.SimpleStatsScreen)) }
                    )
                }

                item {
                    OptionsItem(
                        text = stringResource(id = R.string.advanced_stats),
                        icon = Icons.Rounded.List,
                        onClick = { viewModel.onEvent(OptionsOverviewEvent.GoToScreen(DreamScreens.AdvancedStatsScreen)) }
                    )
                }

                item {
                    OptionsItem(
                        text = stringResource(id = R.string.history),
                        icon = Icons.Rounded.Timer,
                        onClick = { viewModel.onEvent(OptionsOverviewEvent.GoToScreen(DreamScreens.HistoryScreen)) }
                    )
                }

                item {
                    OptionsItem(
                        text = stringResource(id = R.string.debug),
                        icon = Icons.Rounded.Numbers,
                        onClick = { viewModel.onEvent(OptionsOverviewEvent.GoToScreen(DreamScreens.DebugScreen)) }
                    )
                }
            }
        }
    }
}