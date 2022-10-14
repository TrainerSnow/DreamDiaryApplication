package com.snow.dreamdiary.feature_dreams.presentation.bottomnav.viewdreams.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.snow.dreamdiary.R

@Composable
fun DreamSelectorSection(
    onNextClick: () -> Unit,
    onRecentClick: () -> Unit,
    currentNum: Int,
    maxNum: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onRecentClick,

            ) {
            Icon(
                modifier = Modifier
                    .size(82.dp),
                imageVector = Icons.Rounded.KeyboardArrowLeft,
                contentDescription = stringResource(id = R.string.cd_back)
            )
        }
        Spacer(modifier = Modifier.weight(1F))

        Text(
            text = if (maxNum == 0) {
                "-/-"
            } else {
                "$currentNum/$maxNum"
            },
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.weight(1F))
        IconButton(onClick = onNextClick) {
            Icon(
                modifier = Modifier
                    .size(82.dp),
                imageVector = Icons.Rounded.KeyboardArrowRight,
                contentDescription = stringResource(id = R.string.cd_back)
            )
        }
    }
}