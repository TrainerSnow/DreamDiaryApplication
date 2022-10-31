package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bydreamt

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.sharp.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.R
import com.snow.dreamdiary.common.presentation.components.BigIconButton
import com.snow.dreamdiary.common.util.TimeFormatUtil
import com.snow.dreamdiary.common.util.TimeUtil
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SearchDreamtScreen(
    viewModel: SearchDreamtViewModel = hiltViewModel(),
    navController: NavHostController
) {


    LaunchedEffect(key1 = true) {
        viewModel.actionFlow.collectLatest {
            when (it) {
                is SearchDreamtViewModel.UIEvent.GoToScreen -> {
                    navController.navigate(it.screen.route)
                }
            }
        }
    }

    val datePickerDialogFrom = DatePickerDialog(
        LocalContext.current,
        { _:
          DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            viewModel.onEvent(
                SearchDreamtEvent.ChangeDreamtFrom(
                    TimeUtil.millisFromDateDescription(
                        year, month, dayOfMonth
                    )
                )
            )
        }, TimeUtil.currentYear(), TimeUtil.currentMonthOfYear(), TimeUtil.currentDayOfMonth()
    )

    val datePickerDialogTo = DatePickerDialog(
        LocalContext.current,
        { _:
          DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            viewModel.onEvent(
                SearchDreamtEvent.ChangeDreamtTo(
                    TimeUtil.millisFromDateDescription(
                        year, month, dayOfMonth
                    )
                )
            )
        }, TimeUtil.currentYear(), TimeUtil.currentMonthOfYear(), TimeUtil.currentDayOfMonth()
    )

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {

        Text(
            text = stringResource(id = R.string.select_date_range),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
            modifier = Modifier
                .padding(
                    bottom = 8.dp
                )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${stringResource(id = R.string.from_date)} ${
                    TimeFormatUtil.getMillisFormatted(
                        viewModel.state.value.mode.fromTime
                    )
                }",
                style = MaterialTheme.typography.titleMedium
            )
            IconButton(
                onClick = { datePickerDialogFrom.show() }
            ) {
                Icon(
                    imageVector = Icons.Sharp.CalendarMonth,
                    contentDescription = stringResource(id = R.string.cd_pick_date)
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${stringResource(id = R.string.to_date)} ${
                    TimeFormatUtil.getMillisFormatted(
                        viewModel.state.value.mode.toTime
                    )
                }",
                style = MaterialTheme.typography.titleMedium
            )
            IconButton(
                onClick = { datePickerDialogTo.show() }
            ) {
                Icon(
                    imageVector = Icons.Sharp.CalendarMonth,
                    contentDescription = stringResource(id = R.string.cd_pick_date)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            BigIconButton(
                text = stringResource(id = R.string.search),
                onclick = { viewModel.onEvent(SearchDreamtEvent.StartSearch) },
                icon = Icons.Rounded.Search
            )
        }
    }
}