package com.snow.dreamdiary.feature_dreams.presentation.addeditdream;

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.snow.dreamdiary.R
import com.snow.dreamdiary.common.util.TimeFormatUtil
import com.snow.dreamdiary.common.util.TimeUtil
import com.snow.dreamdiary.feature_dreams.presentation.addeditdream.components.DatePicker
import com.snow.dreamdiary.ui.theme.DreamDiaryApplicationTheme
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

private const val TAG = "AddEditDreamScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditDreamScreen(
    navController: NavController,
    dreamId: Int = -1,
    viewModel: AddEditDreamViewModel = hiltViewModel()
) {
    val descState = viewModel.dreamDesc.value

    val annotationState = viewModel.dreamAnnotation.value

    val personState = viewModel.persons.value
    val feelingState = viewModel.feelings.value
    val locationState = viewModel.locations.value

    val comfortState = viewModel.comfortness.value
    val dreamtAt = viewModel.dreamtAt.value

    // TODO: Collect Data and respond!!!
    val actionFlow = viewModel.actionFlow

    val dialogState = rememberMaterialDialogState()

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
                            text = stringResource(id = R.string.add_dream),
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        viewModel.onEvent(
                            AddEditDreamEvent.Add
                        )
                    },
                    text = {
                        Text(
                            text = stringResource(id = R.string.add)
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Sharp.Add,
                            contentDescription = stringResource(id = R.string.cd_add)
                        )
                    }
                )
            },
            floatingActionButtonPosition = FabPosition.End,

            ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = stringResource(id = R.string.write_dream),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                        modifier = Modifier
                            .padding(
                                bottom = 8.dp
                            )
                    )
                    OutlinedTextField(
                        value = descState.text,
                        onValueChange = { text ->
                            viewModel.onEvent(AddEditDreamEvent.ChangeDescription(text))
                        },
                    )

                    Divider(
                        modifier = Modifier
                            .padding(vertical = 12.dp),
                        thickness = 0.3.dp
                    )


                    Text(
                        text = stringResource(id = R.string.write_annotation),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                        modifier = Modifier
                            .padding(
                                bottom = 8.dp
                            )
                    )
                    OutlinedTextField(
                        value = annotationState.text,
                        onValueChange = { text ->
                            viewModel.onEvent(AddEditDreamEvent.ChangeAnnotation(text))
                        },
                    )

                    Divider(
                        modifier = Modifier
                            .padding(vertical = 12.dp),
                        thickness = 0.3.dp
                    )


                    Text(
                        text = stringResource(id = R.string.write_persons),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                        modifier = Modifier
                            .padding(
                                bottom = 8.dp
                            )
                    )
                    OutlinedTextField(
                        value = personState.text,
                        onValueChange = { text ->
                            viewModel.onEvent(AddEditDreamEvent.ChangePersons(text))
                        },
                    )

                    Divider(
                        modifier = Modifier
                            .padding(vertical = 12.dp),
                        thickness = 0.3.dp
                    )


                    Text(
                        text = stringResource(id = R.string.write_feelings),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                        modifier = Modifier
                            .padding(
                                bottom = 8.dp
                            )
                    )
                    OutlinedTextField(
                        value = feelingState.text,
                        onValueChange = { text ->
                            viewModel.onEvent(AddEditDreamEvent.ChangeFeelings(text))
                        },
                    )

                    Divider(
                        modifier = Modifier
                            .padding(vertical = 12.dp),
                        thickness = 0.3.dp
                    )


                    Text(
                        text = stringResource(id = R.string.write_locations),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                        modifier = Modifier
                            .padding(
                                bottom = 8.dp
                            )
                    )
                    OutlinedTextField(
                        value = locationState.text,
                        onValueChange = { text ->
                            viewModel.onEvent(AddEditDreamEvent.ChangeLocations(text))
                        },
                    )

                    Divider(
                        modifier = Modifier
                            .padding(vertical = 12.dp),
                        thickness = 0.3.dp
                    )


                    Text(
                        text = stringResource(id = R.string.select_comfortness),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                        modifier = Modifier
                            .padding(
                                bottom = 8.dp
                            )
                    )
                    Slider(
                        value = comfortState.value.toFloat(),
                        onValueChange = { value ->
                            Log.d(TAG, "AddEditDreamScreen: changed slider value to $value")
                            viewModel.onEvent(AddEditDreamEvent.ChangeComfortness(value.toInt()))
                        },
                        valueRange = 0F..10F,
                        steps = 10
                    )

                    Divider(
                        modifier = Modifier
                            .padding(vertical = 12.dp),
                        thickness = 0.3.dp
                    )

                    Text(
                        text = stringResource(id = R.string.select_date_dreamt),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                        modifier = Modifier
                            .padding(
                                bottom = 8.dp
                            )
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${stringResource(id = R.string.selected_date_x)} ${TimeFormatUtil.getMillisFormatted(dreamtAt.value)}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        IconButton(
                            onClick = { dialogState.show() }
                        ) {
                            Icon(
                                imageVector = Icons.Sharp.CalendarMonth,
                                contentDescription = stringResource(id = R.string.cd_pick_date)
                            )
                        }
                    }
                    DatePicker(
                        dialogState = dialogState,
                        onDateSelect = { newDate ->
                            viewModel.onEvent(
                                AddEditDreamEvent.ChangeDreamtAt(
                                    TimeUtil.millisFromLocalDate(
                                        newDate
                                    )
                                )
                            )
                        }
                    )

                }
            }
        }
    }


}