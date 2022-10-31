package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bymodifier.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.snow.dreamdiary.common.presentation.components.LabeledRadioButton
import com.snow.dreamdiary.common.util.LogicGate

@Composable
fun DialogSelectLogicGate(
    onDismissRequest: () -> Unit,
    onValueChange: (LogicGate) -> Unit,
    currentSelectedGate: LogicGate
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                LogicGate.logicGates.forEach {
                    LabeledRadioButton(
                        onClick = {
                            onValueChange(it)
                            onDismissRequest()
                        },
                        text = stringResource(id = it.nameRes),
                        selected = currentSelectedGate == it
                    )
                }
            }
        }
    }
}