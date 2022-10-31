package com.snow.dreamdiary.feature_dreams.presentation.stats.advanced

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.R
import com.snow.dreamdiary.common.presentation.components.LabeledRadioButton
import com.snow.dreamdiary.feature_dreams.domain.util.DreamModifier
import com.snow.dreamdiary.feature_dreams.presentation.stats.simple.components.HorizontalBarChart
import com.snow.dreamdiary.ui.theme.DreamDiaryApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedStatsScreen(
    viewModel: AdvancedStatsViewModel = hiltViewModel(),
    navController: NavHostController
) {
    DreamDiaryApplicationTheme {
        Surface(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column {

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = viewModel.state.value.searchedValue,
                    onValueChange = {
                        viewModel.onEvent(AdvancedStatsEvent.UpdateSearchedValue(it))
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = { viewModel.onEvent(AdvancedStatsEvent.Search) }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Send,
                                contentDescription = stringResource(id = R.string.send)
                            )
                        }
                    }
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    LabeledRadioButton(
                        onClick = {
                            viewModel.onEvent(
                                AdvancedStatsEvent.ChangeModifier(
                                    DreamModifier.Person
                                )
                            )
                        },
                        text = stringResource(id = R.string.persons),
                        selected = viewModel.state.value.modifier == DreamModifier.Person
                    )
                    LabeledRadioButton(
                        onClick = {
                            viewModel.onEvent(
                                AdvancedStatsEvent.ChangeModifier(
                                    DreamModifier.Feeling
                                )
                            )
                        },
                        text = stringResource(id = R.string.feelings),
                        selected = viewModel.state.value.modifier == DreamModifier.Feeling
                    )
                    LabeledRadioButton(
                        onClick = {
                            viewModel.onEvent(
                                AdvancedStatsEvent.ChangeModifier(
                                    DreamModifier.Location
                                )
                            )
                        },
                        text = stringResource(id = R.string.locations),
                        selected = viewModel.state.value.modifier == DreamModifier.Location
                    )
                }
                LabeledRadioButton(
                    onClick = { viewModel.onEvent(AdvancedStatsEvent.ChangeToComfortness) },
                    text = stringResource(id = R.string.comfortness),
                    selected = viewModel.state.value.showComfortness
                )



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
