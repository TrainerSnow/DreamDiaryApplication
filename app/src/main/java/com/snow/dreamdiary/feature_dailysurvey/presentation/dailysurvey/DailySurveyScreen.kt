package com.snow.dreamdiary.feature_dailysurvey.presentation.dailysurvey

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.R
import com.snow.dreamdiary.common.presentation.components.LabeledSwitch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailySurveyScreen(
    viewModel: DailySurveyViewModel = hiltViewModel(),
    navController: NavHostController
) {

    Column() {
        LabeledSwitch(
            text = stringResource(id = R.string.did_dream_today),
            onCheckedChange = { viewModel.onEvent(DailySurveyEvent.ChangeDidDream(it)) },
            checked = viewModel.state.value.surveyData.didDream
        )


        //Did sleep?
        Text(
            text = stringResource(id = R.string.how_many_dreams),
            style = MaterialTheme.typography.titleMedium
        )
        TextField(
            value = viewModel.state.value.dreamsNum,
            onValueChange = { text ->
                viewModel.onEvent(DailySurveyEvent.ChangeDreamsNum(text))
            }
        )
        Spacer(modifier = Modifier.height(12.dp))


        //Time slept
        Text(
            text = stringResource(id = R.string.how_much_slept),
            style = MaterialTheme.typography.titleMedium
        )
        TextField(
            value = viewModel.state.value.timeSlept,
            onValueChange = { text ->
                viewModel.onEvent(DailySurveyEvent.ChangeTimeSlept(text))
            }
        )
        Spacer(modifier = Modifier.height(12.dp))


        //Health
        Text(
            text = stringResource(id = R.string.how_health),
            style = MaterialTheme.typography.titleMedium
        )
        Slider(
            value = viewModel.state.value.surveyData.health.toFloat(),
            onValueChange = {
                viewModel.onEvent(DailySurveyEvent.ChangeHealth(it.toInt()))
            },
            steps = 10
        )
        Spacer(modifier = Modifier.height(12.dp))


        //Comfortness
        Text(
            text = stringResource(id = R.string.how_comfortness),
            style = MaterialTheme.typography.titleMedium
        )
        Slider(
            value = viewModel.state.value.surveyData.comfortness.toFloat(),
            onValueChange = {
                viewModel.onEvent(DailySurveyEvent.ChangeComfortness(it.toInt()))
            },
            steps = 10
        )
        Spacer(modifier = Modifier.height(12.dp))

        //Physical Activity
        Text(
            text = stringResource(id = R.string.how_activity),
            style = MaterialTheme.typography.titleMedium
        )
        Slider(
            value = viewModel.state.value.surveyData.physicalActivity.toFloat(),
            onValueChange = {
                viewModel.onEvent(DailySurveyEvent.ChangeActivity(it.toInt()))
            },
            steps = 10
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}
