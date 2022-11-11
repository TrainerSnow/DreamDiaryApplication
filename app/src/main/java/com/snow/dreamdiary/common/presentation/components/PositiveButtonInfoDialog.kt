package com.snow.dreamdiary.common.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.snow.dreamdiary.R

@Composable
fun PositiveButtonInfoDialog(
    text: String,
    onDismissRequest: () -> Unit,
    icon: @Composable () -> Unit,
    buttonText: String = stringResource(id = R.string.ok)
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

                Spacer(modifier = Modifier.height(12.dp))
                
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))
                
                Button(
                    onClick = { onDismissRequest() }
                ) {
                    Text(
                        text = buttonText
                    )
                }
            }
        }
    }
}