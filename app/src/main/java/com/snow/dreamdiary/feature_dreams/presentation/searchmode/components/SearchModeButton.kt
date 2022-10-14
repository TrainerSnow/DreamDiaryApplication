package com.snow.dreamdiary.feature_dreams.presentation.searchmode.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SearchModeButton(
    text: String,
    onclick: () -> Unit,
    icon: ImageVector
) {
    OutlinedButton(
        modifier = Modifier
            .defaultMinSize(minHeight = 52.dp)
            .fillMaxWidth(fraction = 0.85F),
        onClick = onclick
    ){
        Icon(
            imageVector = icon,
            contentDescription = text
        )
        Spacer(modifier = Modifier
            .width(12.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium
        )
    }
}