package com.snow.dreamdiary.feature_dreams.presentation.history

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.R
import com.snow.dreamdiary.common.presentation.components.LabeledRadioButton
import com.snow.dreamdiary.common.util.TimeUtil
import com.snow.dreamdiary.feature_dreams.presentation.history.components.TimeStampLineGraphDerivative
import com.snow.dreamdiary.feature_dreams.presentation.history.components.TimeStampLineGraphActual
import com.snow.dreamdiary.ui.theme.DreamDiaryApplicationTheme

private const val TAG = "HistoryScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    navController: NavHostController,
    viewModel: HistoryViewModel = hiltViewModel()
) {

    DreamDiaryApplicationTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                LabeledRadioButton(
                    onClick = { viewModel.onEvent(HistoryEvent.EnableDreamView) },
                    text = stringResource(id = R.string.dream),
                    selected = viewModel.state.value.dreamView
                )
                Spacer(modifier = Modifier.width(24.dp))
                LabeledRadioButton(
                    onClick = { viewModel.onEvent(HistoryEvent.EnableModifierView) },
                    text = stringResource(id = R.string.user_defined),
                    selected = viewModel.state.value.modifierView
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                value = viewModel.state.value.searchedValue,
                onValueChange = { viewModel.onEvent(HistoryEvent.SearchedValueChanged(it)) },
                enabled = viewModel.state.value.modifierView,
                trailingIcon = {
                    IconButton(onClick = { viewModel.onEvent(HistoryEvent.SendSearch) }) {
                        Icon(
                            imageVector = Icons.Rounded.Send,
                            contentDescription = stringResource(id = R.string.send)
                        )
                    }
                }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(id = R.string.actual))
                Spacer(modifier = Modifier.width(8.dp))
                Switch(
                    checked = viewModel.state.value.changedView,
                    onCheckedChange = { viewModel.onEvent(HistoryEvent.ToggleView) }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(id = R.string.change))
            }
            Log.e(TAG, "HistoryScreen: timestamps: ${viewModel.state.value.timeStamps}", )
            Log.e(TAG, "HistoryScreen: earliest dream: : ${viewModel.state.value.earliestTimeStamp}", )
            if(viewModel.state.value.actualView){
                Log.e(TAG, "HistoryScreen: Drawing actual graph", )
                TimeStampLineGraphActual(
                    yLabel = stringResource(id = R.string.amount),
                    xLabel = stringResource(id = R.string.time),
                    timeStamps = viewModel.state.value.timeStamps,
                    timeStart = viewModel.state.value.earliestTimeStamp
                )
            }else{
                Log.e(TAG, "HistoryScreen: drawing derivative graph", )
                TimeStampLineGraphDerivative(
                    yLabel = stringResource(id = R.string.amount),
                    xLabel = stringResource(id = R.string.time),
                    timeStamps = viewModel.state.value.timeStamps,
                    timeStart = viewModel.state.value.earliestTimeStamp,
                    interval = 45646546,
                    // TODO: Change this to not be magic num!!!!!
                    yMaxValue = 40
                )
            }
        }
    }
}