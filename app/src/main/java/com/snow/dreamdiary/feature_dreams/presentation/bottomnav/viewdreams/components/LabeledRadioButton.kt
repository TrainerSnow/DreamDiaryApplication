package com.snow.dreamdiary.feature_dreams.presentation.bottomnav.viewdreams.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun LabeledRadioButton(
    onClick: () -> Unit,
    text: String,
    selected: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall
        )
    }
}