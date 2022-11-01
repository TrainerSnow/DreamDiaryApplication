package com.snow.dreamdiary.feature_dreams.presentation.bottomnav.viewdreams

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.R
import com.snow.dreamdiary.common.util.TimeFormatUtil
import com.snow.dreamdiary.feature_dreams.presentation.bottomnav.viewdreams.components.CompleteOrderSection
import com.snow.dreamdiary.feature_dreams.presentation.bottomnav.viewdreams.components.DreamItem
import com.snow.dreamdiary.feature_dreams.presentation.bottomnav.viewdreams.components.DreamSelectorSection
import com.snow.dreamdiary.feature_dreams.presentation.navigation.BottomNavScreens
import com.snow.dreamdiary.ui.theme.DreamDiaryApplicationTheme
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewDreamsScreen(
    navController: NavHostController,
    viewModel: ViewDreamsViewModel = hiltViewModel()
) {

    val state = viewModel.state

    val actionFlow = viewModel.actionFlow

    val currentDream = state.value.dreams.getOrNull(state.value.currentDreamIndex)

    val context = LocalContext.current

    LaunchedEffect(key1 = 1) {
        actionFlow.collectLatest { event ->
            when (event) {
                is ViewDreamsViewModel.UIEvent.Message -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is ViewDreamsViewModel.UIEvent.Edit -> {
                    val id = event.dream.id
                    if (id == null)
                        navController.navigate(BottomNavScreens.AddEditDreamScreen.route)
                    else
                        navController.navigate(BottomNavScreens.AddEditDreamScreen.passDreamId(id))
                }
            }
        }
    }

    DreamDiaryApplicationTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = if (currentDream == null)
                                stringResource(id = R.string.view_dreams)
                            else
                                String.format(
                                    stringResource(id = R.string.view_dream_x),
                                    TimeFormatUtil.getMillisFormatted(currentDream.dreamtAt)
                                ),
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(
                        top = paddingValues.calculateTopPadding() + 16.dp,
                        bottom = paddingValues.calculateBottomPadding() + 16.dp,
                        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr) + 16.dp,
                        end = paddingValues.calculateEndPadding(LayoutDirection.Ltr) + 16.dp,
                    )
            ) {
                CompleteOrderSection(
                    order = state.value.sortingOrder,
                    onOrderChange = {
                        Log.d("ViewDreamsScreen", "ViewDreamsScreen: new Order: $it")
                        viewModel.onEvent(ViewDreamsEvent.RenewOrder(it))
                    },
                    onExpandClick = { viewModel.onEvent(ViewDreamsEvent.ToggleOrderMenu) },
                    isExpanded = state.value.isOrderMenuExpanded
                )
                if (state.value.dreams.isNotEmpty())
                    Spacer(modifier = Modifier.weight(1F))
                DreamSelectorSection(
                    onNextClick = { viewModel.onEvent(ViewDreamsEvent.NextDream) },
                    onRecentClick = { viewModel.onEvent(ViewDreamsEvent.RecentDream) },
                    currentNum = state.value.currentDreamIndex + 1,
                    maxNum = state.value.dreams.size
                )

                if (currentDream != null) {
                    DreamItem(
                        modifier = Modifier,
                        dream = currentDream,
                        onEditClick = { viewModel.onEvent(ViewDreamsEvent.EditDream(currentDream)) },
                        onDeleteClick = { viewModel.onEvent(ViewDreamsEvent.DeleteDream(currentDream)) }
                    )
                } else {
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