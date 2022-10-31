package com.snow.dreamdiary.feature_dreams.presentation.stats.simple

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.R
import com.snow.dreamdiary.common.presentation.components.LabeledRadioButton
import com.snow.dreamdiary.common.util.TimeUtil
import com.snow.dreamdiary.feature_dreams.domain.util.DreamModifier
import com.snow.dreamdiary.feature_dreams.presentation.stats.simple.components.HorizontalBarChart
import com.snow.dreamdiary.ui.theme.DreamDiaryApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleStatsScreen(
    navController: NavHostController,
    viewModel: SimpleStatsViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val datePickerDialogFrom = DatePickerDialog(
        context,
        { _:
          DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            viewModel.onEvent(
                SimpleStatsEvent.ChangeTimeFrom(
                    TimeUtil.millisFromDateDescription(
                        year, month, dayOfMonth
                    )
                )
            )
        },
        TimeUtil.currentYear(),
        TimeUtil.currentMonthOfYear(),
        TimeUtil.currentDayOfMonth()
    )

    val datePickerDialogTo = DatePickerDialog(
        context,
        { _:
          DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            viewModel.onEvent(
                SimpleStatsEvent.ChangeTimeTo(
                    TimeUtil.millisFromDateDescription(
                        year, month, dayOfMonth
                    )
                )
            )
        },
        TimeUtil.currentYear(),
        TimeUtil.currentMonthOfYear(),
        TimeUtil.currentDayOfMonth()
    )

    DreamDiaryApplicationTheme {
        Surface(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    LabeledRadioButton(
                        onClick = { viewModel.onEvent(SimpleStatsEvent.ChangeModifier(DreamModifier.Person)) },
                        text = stringResource(id = R.string.persons),
                        selected = viewModel.state.value.modifier == DreamModifier.Person
                    )
                    LabeledRadioButton(
                        onClick = { viewModel.onEvent(SimpleStatsEvent.ChangeModifier(DreamModifier.Feeling)) },
                        text = stringResource(id = R.string.feelings),
                        selected = viewModel.state.value.modifier == DreamModifier.Feeling
                    )
                    LabeledRadioButton(
                        onClick = { viewModel.onEvent(SimpleStatsEvent.ChangeModifier(DreamModifier.Location)) },
                        text = stringResource(id = R.string.locations),
                        selected = viewModel.state.value.modifier == DreamModifier.Location
                    )
                }
                LabeledRadioButton(
                    onClick = { viewModel.onEvent(SimpleStatsEvent.ChangeToComfortness) },
                    text = stringResource(id = R.string.comfortness),
                    selected = viewModel.state.value.showComfortness
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .weight(1F),
                        value = viewModel.state.value.fromDateVis,
                        onValueChange = { },
                        readOnly = true,
                        trailingIcon = {
                            IconButton(
                                onClick = { datePickerDialogFrom.show() }
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.CalendarMonth,
                                    contentDescription = stringResource(id = R.string.from_date)
                                )
                            }
                        }

                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    OutlinedTextField(
                        modifier = Modifier
                            .weight(1F),
                        value = viewModel.state.value.toDateVis,
                        onValueChange = { },
                        readOnly = true,
                        trailingIcon = {
                            IconButton(
                                onClick = { datePickerDialogTo.show() }
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.CalendarMonth,
                                    contentDescription = stringResource(id = R.string.to_date)
                                )
                            }
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(12.dp)
                        .clip(RoundedCornerShape(4.dp))
                ) {
                    HorizontalBarChart(
                        labels = viewModel.state.value.modifierLabels,
                        data = viewModel.state.value.modifierData
                    )
                }
            }
        }
    }
}