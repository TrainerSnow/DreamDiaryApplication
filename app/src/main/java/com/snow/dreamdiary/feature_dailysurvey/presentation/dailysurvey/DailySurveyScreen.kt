package com.snow.dreamdiary.feature_dailysurvey.presentation.dailysurvey

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.R
import com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bymodifier.SearchModifierViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailySurveyScreen(
    viewModel: DailySurveyViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = true){
        viewModel.actionFlow.collectLatest {
            when (it) {
                is DailySurveyViewModel.UIEvent.Message -> {
                    val res = it.res
                    withContext(Dispatchers.IO){
                        val text = context.getString(res)
                        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onEvent(DailySurveyEvent.Send) }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = stringResource(id = R.string.add)
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = it.calculateStartPadding(LayoutDirection.Ltr) + 16.dp,
                    top = it.calculateTopPadding() + 16.dp,
                    end = it.calculateEndPadding(LayoutDirection.Ltr) + 16.dp,
                    bottom = it.calculateBottomPadding() + 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {
            Text(
                text = stringResource(id = R.string.did_dream_today),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
            )
            Switch(
                onCheckedChange = { viewModel.onEvent(DailySurveyEvent.ChangeDidDream(it)) },
                checked = viewModel.state.value.surveyData.didDream
            )

            //Did dream?
            Text(
                text = stringResource(id = R.string.how_many_dreams),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
            )
            TextField(
                value = viewModel.state.value.dreamsNum,
                onValueChange = { text ->
                    viewModel.onEvent(DailySurveyEvent.ChangeDreamsNum(text))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )


            //Time slept
            Text(
                text = stringResource(id = R.string.how_much_slept),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
            )
            TextField(
                value = viewModel.state.value.timeSlept,
                onValueChange = { text ->
                    viewModel.onEvent(DailySurveyEvent.ChangeTimeSlept(text))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )


            //Health
            Text(
                text = stringResource(id = R.string.how_health),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
            )
            Slider(
                value = viewModel.state.value.surveyData.health.toFloat(),
                onValueChange = {
                    viewModel.onEvent(DailySurveyEvent.ChangeHealth(it.toInt()))
                },
                valueRange = 0F..10F,
            )


            //Comfortness
            Text(
                text = stringResource(id = R.string.how_comfortness),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
            )
            Slider(
                value = viewModel.state.value.surveyData.comfortness.toFloat(),
                onValueChange = {
                    viewModel.onEvent(DailySurveyEvent.ChangeComfortness(it.toInt()))
                },
                valueRange = 0F..10F,
            )

            //Physical Activity
            Text(
                text = stringResource(id = R.string.how_activity),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
            )
            Slider(
                value = viewModel.state.value.surveyData.physicalActivity.toFloat(),
                onValueChange = {
                    viewModel.onEvent(DailySurveyEvent.ChangeActivity(it.toInt()))
                },
                valueRange = 0F..10F,
            )
        }
    }
}
