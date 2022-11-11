package com.snow.dreamdiary.common.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.snow.dreamdiary.R

@Composable
fun YesNoButtonDialog(
    text: String,
    icon: @Composable () -> Unit,
    buttonText: String = stringResource(id = R.string.ok),
    onDismissRequest: () -> Unit,
    onYesClick: () -> Unit,
    onNoClick: () -> Unit,
    yesText: String = stringResource(id = R.string.ok),
    notext: String = stringResource(id = R.string.cancel)
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                icon()

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick = onNoClick
                    ) {
                        Text(
                            text = notext
                        )
                    }

                    Button(
                        onClick = onYesClick
                    ) {
                        Text(
                            text = yesText
                        )
                    }
                }
            }
        }
    }
}