package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bycomfortness

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.R
import com.snow.dreamdiary.common.presentation.components.BigIconButton
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchComfortnessScreen(
    viewModel: SearchComfortnessViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = true){
        viewModel.actionFlow.collectLatest {
            when (it) {
                is SearchComfortnessViewModel.UIEvent.Message -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is SearchComfortnessViewModel.UIEvent.GoToScreen -> {
                    navController.navigate(it.screen.route)
                }
            }
        }
    }


    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {

        Text(
            text = stringResource(id = R.string.select_comfortness_range),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
            modifier = Modifier
                .padding(
                    bottom = 8.dp
                )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.from_comfortness),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.width(12.dp))
            OutlinedTextField(
                value = viewModel.state.value.from.toString(),
                onValueChange = { text: String ->
                    viewModel.onEvent(
                        SearchComfortnessEvent.ChangeFromValue(
                            text
                        )
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.to_comfortness),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.width(12.dp))
            OutlinedTextField(
                value = viewModel.state.value.to,
                onValueChange = { text: String ->
                    viewModel.onEvent(SearchComfortnessEvent.ChangeToValue(text))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            BigIconButton(
                text = stringResource(id = R.string.search),
                onclick = { viewModel.onEvent(SearchComfortnessEvent.StartSearch) },
                icon = Icons.Rounded.Search
            )
        }
    }
}