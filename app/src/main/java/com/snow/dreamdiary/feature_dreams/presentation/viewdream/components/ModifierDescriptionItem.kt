package com.snow.dreamdiary.feature_dreams.presentation.viewdream.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.snow.dreamdiary.R

@Composable
fun ModifierDescriptionItem(
    title: String,
    values: Collection<String>,
    expanded: Boolean
) {
    Column() {
        Row(
            modifier = Modifier
                .padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if(expanded) Icons.Rounded.ArrowUpward else Icons.Rounded.ArrowDownward,
                contentDescription = stringResource(id = R.string.expand)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = title)
        }
        Spacer(modifier = Modifier.height(12.dp))
        AnimatedVisibility(visible = expanded) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            ) {
                values.forEach{
                    Text(text = "-$it")
                }
            }
        }
    }
}