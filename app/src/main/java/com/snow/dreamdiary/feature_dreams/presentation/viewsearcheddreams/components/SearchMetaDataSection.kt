package com.snow.dreamdiary.feature_dreams.presentation.viewsearcheddreams.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.snow.dreamdiary.R

@Composable
fun SearchMetaDataSection(
    dreamsFound: Int,
    timeNeeded: Long
) {
    OutlinedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = """
                ${stringResource(id = R.string.dreams_found)}$dreamsFound
            """.trimIndent(),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = """
                ${stringResource(id = R.string.time_needed)}$timeNeeded
            """.trimIndent(),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}