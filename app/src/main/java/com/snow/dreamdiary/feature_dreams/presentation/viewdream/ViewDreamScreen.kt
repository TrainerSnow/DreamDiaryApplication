package com.snow.dreamdiary.feature_dreams.presentation.viewdream

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.R
import com.snow.dreamdiary.common.presentation.components.ConditionalCircularProgressIndicator
import com.snow.dreamdiary.common.util.TimeFormatUtil
import com.snow.dreamdiary.feature_dreams.presentation.viewdream.components.ModifierDescriptionItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun ViewDreamScreen(
    bottomNavController: NavHostController,
    viewModel: ViewDreamViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val undoMsg = stringResource(id = R.string.undo)
    val deletedMsg = stringResource(id = R.string.deleted_dream)

    LaunchedEffect(key1 = 1) {
        viewModel.actionFlow.collectLatest {
            when (it) {
                is ViewDreamViewModel.UIEvent.GoToScreen -> {
                    bottomNavController.navigate(it.route)
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        val ignored = it
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ConditionalCircularProgressIndicator(
                flag = viewModel.state.value.dream != null
            ) {
                val dream = viewModel.state.value.dream!!

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "${stringResource(id = R.string.dream_from)} ${
                        TimeFormatUtil.getMillisDayFormatted(
                            dream.dreamtAt
                        )
                    }",
                    style = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    OutlinedButton(onClick = {
                        viewModel.onEvent(ViewDreamEvent.Delete)

                        scope.launch {
                            val result = scaffoldState.snackbarHostState.showSnackbar(
                                message = deletedMsg,
                                actionLabel = undoMsg
                            )
                            if(result == SnackbarResult.ActionPerformed){
                                viewModel.onEvent(ViewDreamEvent.Restore)
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = stringResource(id = R.string.delete)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = stringResource(id = R.string.delete))
                    }
                    Button(onClick = { viewModel.onEvent(ViewDreamEvent.Edit) }) {
                        Icon(
                            imageVector = Icons.Rounded.Edit,
                            contentDescription = stringResource(id = R.string.edit)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = stringResource(id = R.string.edit))
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp)
                    ) {
                        ModifierDescriptionItem(
                            title = stringResource(id = R.string.description),
                            values = listOf(dream.description),
                            expanded = viewModel.state.value.descExpanded,
                            onRowClick = { viewModel.onEvent(ViewDreamEvent.ToggleDesc) }
                        )
                        ModifierDescriptionItem(
                            title = stringResource(id = R.string.annotation),
                            values = listOf(dream.annotation),
                            expanded = viewModel.state.value.annotationExpanded,
                            onRowClick = { viewModel.onEvent(ViewDreamEvent.ToggleAnn) }
                        )
                        ModifierDescriptionItem(
                            title = stringResource(id = R.string.persons),
                            values = dream.persons,
                            expanded = viewModel.state.value.personsExpanded,
                            onRowClick = { viewModel.onEvent(ViewDreamEvent.TogglePerson) }
                        )
                        ModifierDescriptionItem(
                            title = stringResource(id = R.string.feelings),
                            values = dream.feelings,
                            expanded = viewModel.state.value.feelingsExpanded,
                            onRowClick = { viewModel.onEvent(ViewDreamEvent.ToggleFeeling) }
                        )
                        ModifierDescriptionItem(
                            title = stringResource(id = R.string.locations),
                            values = dream.locations,
                            expanded = viewModel.state.value.locationsExpanded,
                            onRowClick = { viewModel.onEvent(ViewDreamEvent.ToggleLocation) }
                        )

                        ModifierDescriptionItem(
                            title = stringResource(id = R.string.comfortness),
                            values = listOf(dream.comfortness.toString()),
                            expanded = viewModel.state.value.comfortnessExpanded,
                            onRowClick = { viewModel.onEvent(ViewDreamEvent.ToggleComf) }
                        )
                    }
                }
            }
        }
    }


}