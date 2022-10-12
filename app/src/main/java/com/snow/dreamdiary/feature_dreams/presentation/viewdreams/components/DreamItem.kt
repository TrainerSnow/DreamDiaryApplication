package com.snow.dreamdiary.feature_dreams.presentation.viewdreams.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snow.dreamdiary.R
import com.snow.dreamdiary.feature_dreams.domain.model.Dream

@Composable
fun DreamItem(
    dream: Dream
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = stringResource(id = R.string.description),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = dream.description,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(id = R.string.annotation),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = dream.annotation,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))

                if(dream.persons.isNotEmpty()){
                    DreamModifierSection(
                        name = stringResource(id = R.string.persons),
                        items = dream.persons
                    )
                }

                if(dream.feelings.isNotEmpty()){
                    DreamModifierSection(
                        name = stringResource(id = R.string.feelings),
                        items = dream.feelings
                    )
                }

                if(dream.locations.isNotEmpty()){
                    DreamModifierSection(
                        name = stringResource(id = R.string.locations),
                        items = dream.locations
                    )
                }
            }
        }
    }
}

@Composable
private fun DreamModifierSection(
    name: String,
    items: List<String>
) {
    Text(
        text = name,
        style = MaterialTheme.typography.titleLarge
    )
    items.forEach {
        Text(
            text = "-$it",
            style = MaterialTheme.typography.bodyLarge
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun DefaultDreamItemPreview() {
    val now = System.currentTimeMillis()
    DreamItem(
        dream = Dream(
            description = "I ran down hill",
            annotation = "I woke up",
            persons = listOf(
                "Meike",
                "Niklas",
                "Papa",
                "Jona"
            ),
            feelings = listOf(
                "Happy",
                "Sad"
            ),
            locations = listOf(
                "Emmendingen",
                "Zuhause"
            ),
            comfortness = 8,
            createdAt = now,
            dreamtAt = now
        )
    )
}