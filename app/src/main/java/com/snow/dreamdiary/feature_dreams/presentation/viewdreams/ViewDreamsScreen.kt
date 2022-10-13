package com.snow.dreamdiary.feature_dreams.presentation.viewdreams

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
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
import com.snow.dreamdiary.feature_dreams.presentation.viewdreams.components.CompleteOrderSection
import com.snow.dreamdiary.feature_dreams.presentation.viewdreams.components.DreamItem
import com.snow.dreamdiary.feature_dreams.presentation.viewdreams.components.DreamSelectorSection
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
            }
        }
    }

    DreamDiaryApplicationTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Sharp.ArrowBack,
                            contentDescription = stringResource(id = R.string.cd_back)
                        )
                    },
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
                    .padding(paddingValues)
            ) {
                CompleteOrderSection(
                    order = state.value.sortingOrder,
                    onOrderChange = { viewModel.onEvent(ViewDreamsEvent.RenewOrder(it)) },
                    onExpandClick = { viewModel.onEvent(ViewDreamsEvent.ToggleOrderMenu) },
                    isExpanded = state.value.isOrderMenuExpanded
                )
                Spacer(modifier = Modifier.weight(1F))
                DreamSelectorSection(
                    onNextClick = { viewModel.onEvent(ViewDreamsEvent.NextDream) },
                    onRecentClick = { viewModel.onEvent(ViewDreamsEvent.RecentDream) },
                    currentNum = state.value.currentDreamIndex + 1,
                    maxNum = state.value.dreams.size
                )
                
                if(currentDream != null){
                    DreamItem(
                        modifier = Modifier,
                        dream = currentDream,
                        onEditClick = { viewModel.onEvent(ViewDreamsEvent.EditDream(currentDream)) },
                        onDeleteClick = { viewModel.onEvent(ViewDreamsEvent.DeleteDream(currentDream)) }
                    )
                }else{
                    Box(
                        modifier = Modifier
                            .background(Color.Transparent),
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