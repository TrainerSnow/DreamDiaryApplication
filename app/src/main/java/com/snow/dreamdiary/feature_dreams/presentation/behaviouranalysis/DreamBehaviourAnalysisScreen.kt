package com.snow.dreamdiary.feature_dreams.presentation.behaviouranalysis

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.R

@Composable
fun DreamBehaviourAnalysisScreen(
    navController: NavHostController,
    viewModel: DreamBehaviourAnalysisViewModel = hiltViewModel()
) {
    // TODO: Think of better way
    val labels = listOf(
        R.string.time_slept,
        R.string.physical_activity,
        R.string.health,
        R.string.daily_comfortness
    )

    Column(
        modifier = Modifier
            .padding(all = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.observing_affectable_x),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                modifier = Modifier
                    .clickable { viewModel.onEvent(DreamBehaviourAnalysisEvent.ToggleAffectable) },
                text = stringResource(id = viewModel.state.value.affectable.displayNameId),
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = stringResource(id = R.string.tap_to_swap),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Text(
            text = "${stringResource(id = viewModel.state.value.affectable.displayNameId)} ${
                stringResource(
                    id = R.string.x_affected_by
                )
            }",
            style = MaterialTheme.typography.titleLarge
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            labels.forEachIndexed { index, it ->
                Row() {
                    Text(
                        text = "${stringResource(id = it)}: " + if(viewModel.state.value.percentValues[index] == null) stringResource(
                            id = R.string.cant_be_calculated
                        ) else viewModel.state.value.percentValues[index],
                        color = if(viewModel.state.value.percentValues[index] == null)
                            MaterialTheme.colorScheme.onBackground
                        else if (viewModel.state.value.percentValues[index]!! > 0)
                            Color.Green
                        else
                            Color.Red,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}