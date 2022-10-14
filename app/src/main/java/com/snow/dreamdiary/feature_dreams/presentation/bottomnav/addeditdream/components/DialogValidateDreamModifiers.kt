package com.snow.dreamdiary.feature_dreams.presentation.bottomnav.addeditdream.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.snow.dreamdiary.R

@Composable
fun DialogValidateDreamModifiers(
    onPositiveClick: () -> Unit,
    onNegativeClick: () -> Unit,
    onDismissRequest: () -> Unit,
    newPersons: List<String>,
    newLocations: List<String>,
    newFeelings: List<String>
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = stringResource(id = R.string.detected_new_values),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = stringResource(id = R.string.persons),
                    style = MaterialTheme.typography.titleSmall
                )
                newPersons.forEach {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(id = R.string.feelings),
                    style = MaterialTheme.typography.titleSmall
                )
                newFeelings.forEach {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(id = R.string.locations),
                    style = MaterialTheme.typography.titleSmall
                )
                newLocations.forEach {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(id = R.string.sure_add_new_modifiers),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        modifier = Modifier
                            .weight(1F),
                        onClick = onNegativeClick
                    ) {
                        Text(
                            text = stringResource(id = R.string.no)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        modifier = Modifier
                            .weight(1F),
                        onClick = onPositiveClick
                    ) {
                        Text(
                            text = stringResource(id = R.string.yes)
                        )
                    }
                }

            }
        }
    }
}