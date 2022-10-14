package com.snow.dreamdiary.feature_dreams.presentation.bottomnav.addeditdream

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material.icons.sharp.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.snow.dreamdiary.R
import com.snow.dreamdiary.common.util.TimeFormatUtil
import com.snow.dreamdiary.common.util.TimeUtil
import com.snow.dreamdiary.feature_dreams.presentation.bottomnav.addeditdream.components.DialogValidateDreamModifiers
import com.snow.dreamdiary.ui.theme.DreamDiaryApplicationTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext

private const val TAG = "AddEditDreamScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditDreamScreen(
    navController: NavController,
    viewModel: AddEditDreamViewModel = hiltViewModel()
) {
    val descState = viewModel.dreamDesc.value

    val annotationState = viewModel.dreamAnnotation.value

    val personState = viewModel.persons.value
    val feelingState = viewModel.feelings.value
    val locationState = viewModel.locations.value

    val comfortState = viewModel.comfortness.value
    val dreamtAt = viewModel.dreamtAt.value

    val shouldShowDialog = viewModel.shouldShowDialog.value

    val dreamId = viewModel.dreamId

    Log.d(TAG, "AddEditDreamScreen: Dream id: ${dreamId.value}")

    val context = LocalContext.current

    val datePickerDialog = DatePickerDialog(
        context,
        { _:
          DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            viewModel.onEvent(
                AddEditDreamEvent.ChangeDreamtAt(
                    TimeUtil.millisFromDateDescription(
                        year, month, dayOfMonth
                    )
                )
            )
        }, TimeUtil.currentYear(), TimeUtil.currentMonthOfYear(), TimeUtil.currentDayOfMonth()
    )


    LaunchedEffect(key1 = true) {
        viewModel.actionFlow.collectLatest { event ->
            when (event) {
                is AddEditDreamViewModel.UIEvent.Message -> {
                    val res = event.res
                    withContext(Dispatchers.IO) {
                        val text = context.getString(res)
                        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                    }
                }
                AddEditDreamViewModel.UIEvent.GoBack -> {
                    navController.navigateUp()
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
                            AddEditDreamEvent.RequestAdd
                        )
                    },
                    text = {
                        Text(
                            text = if(dreamId.value == -1)
                                stringResource(id = R.string.add)
                            else
                                stringResource(id = R.string.edit)
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = if(dreamId.value == -1)
                                Icons.Rounded.Add
                            else
                                Icons.Rounded.Edit,
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
                            text = "${stringResource(id = R.string.selected_date_x)} ${
                                TimeFormatUtil.getMillisFormatted(
                                    if (dreamtAt.value == 0L) System.currentTimeMillis() else dreamtAt.value
                                )
                            }",
                            style = MaterialTheme.typography.titleMedium
                        )
                        IconButton(
                            onClick = { datePickerDialog.show() }
                        ) {
                            Icon(
                                imageVector = Icons.Sharp.CalendarMonth,
                                contentDescription = stringResource(id = R.string.cd_pick_date)
                            )
                        }
                    }
                    if (shouldShowDialog.value) {
                        DialogValidateDreamModifiers(
                            onPositiveClick = { viewModel.onEvent(AddEditDreamEvent.Add) },
                            onNegativeClick = { viewModel.onEvent(AddEditDreamEvent.DismissAddRequest) },
                            onDismissRequest = { viewModel.onEvent(AddEditDreamEvent.DismissAddRequest) },
                            newPersons = viewModel.newPersons.value,
                            newLocations = viewModel.newLocations.value,
                            newFeelings = viewModel.newFeelings.value
                        )
                    }
                }
            }
        }
    }


}