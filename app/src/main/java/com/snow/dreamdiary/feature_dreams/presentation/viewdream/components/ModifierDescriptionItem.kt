package com.snow.dreamdiary.feature_dreams.presentation.viewdream.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
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
    expanded: Boolean,
    dashes: Boolean = true,
    onRowClick: () -> Unit = {  }
) {
    Column() {
        Row(
            modifier = Modifier
                .clickable { onRowClick() }
                .padding(all = 8.dp)
                ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if(expanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                contentDescription = stringResource(id = R.string.expand)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
        }
        AnimatedVisibility(visible = expanded) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 8.dp)
            ) {
                values.forEach{
                    Text(text = "${if(dashes) "-" else "" }$it")
                }
            }
        }
    }
}