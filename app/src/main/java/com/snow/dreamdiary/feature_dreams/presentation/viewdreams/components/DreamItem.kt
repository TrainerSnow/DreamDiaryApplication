package com.snow.dreamdiary.feature_dreams.presentation.viewdreams.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snow.dreamdiary.R
import com.snow.dreamdiary.feature_dreams.domain.model.Dream

@Composable
fun DreamItem(
    modifier: Modifier,
    dream: Dream,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
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
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    OutlinedButton(
                        onClick = onDeleteClick,
                        
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Delete,
                            contentDescription = stringResource(id = R.string.cd_delete)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(id = R.string.delete)
                        )
                    }

                    Button(
                        onClick = onEditClick,

                        ) {
                        Icon(
                            imageVector = Icons.Rounded.Edit,
                            contentDescription = stringResource(id = R.string.cd_edit)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(id = R.string.edit)
                        )
                    }
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
