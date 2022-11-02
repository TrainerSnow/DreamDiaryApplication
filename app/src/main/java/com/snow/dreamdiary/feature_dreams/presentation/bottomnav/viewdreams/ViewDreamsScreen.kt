package com.snow.dreamdiary.feature_dreams.presentation.bottomnav.viewdreams

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.R
import com.snow.dreamdiary.common.presentation.components.DreamPreviewCard
import com.snow.dreamdiary.feature_dreams.presentation.bottomnav.viewdreams.components.CompleteOrderSection
import com.snow.dreamdiary.ui.theme.DreamDiaryApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewDreamsScreen(
    navController: NavHostController,
    viewModel: ViewDreamsViewModel = hiltViewModel()
) {


    LaunchedEffect(key1 = 1) {

    }

    DreamDiaryApplicationTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                                stringResource(id = R.string.view_dreams)
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(
                        top = paddingValues.calculateTopPadding() + 16.dp,
                        bottom = paddingValues.calculateBottomPadding() + 16.dp,
                        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr) + 16.dp,
                        end = paddingValues.calculateEndPadding(LayoutDirection.Ltr) + 16.dp,
                    )
            ) {
                item {
                    CompleteOrderSection(
                        order = viewModel.state.value.sortingOrder,
                        onOrderChange = {
                            Log.d("ViewDreamsScreen", "ViewDreamsScreen: new Order: $it")
                            viewModel.onEvent(ViewDreamsEvent.RenewOrder(it))
                        },
                        onExpandClick = { viewModel.onEvent(ViewDreamsEvent.ToggleOrderMenu) },
                        isExpanded = viewModel.state.value.isOrderMenuExpanded
                    )
                }

                if (viewModel.state.value.dreams.isNotEmpty()) {
                    items(
                        count = viewModel.state.value.dreams.size - 1
                    ) { i ->
                        DreamPreviewCard(
                            dream = viewModel.state.value.dreams[i]
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                } else {
                    item {
                        Box(
                            modifier = Modifier
                                .background(Color.Transparent)
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.no_dream_found)
                            )
                        }
                    }
                }
            }
        }
    }
}