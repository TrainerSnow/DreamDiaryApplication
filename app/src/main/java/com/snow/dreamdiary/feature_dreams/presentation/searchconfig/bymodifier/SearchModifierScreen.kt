package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bymodifier

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.R
import com.snow.dreamdiary.common.presentation.components.BigIconButton
import com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bymodifier.components.DialogSelectLogicGate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchModifierScreen(
    viewModel: SearchModifierViewModel = hiltViewModel(),
    navController: NavHostController
) {

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.write_modifiers_search),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
            modifier = Modifier
                .padding(
                    bottom = 8.dp
                )
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(
                    bottom = 16.dp
                ),
            value = viewModel.state.value.textFieldInput,
            onValueChange = {
                viewModel.onEvent(SearchModifierEvent.TextFieldValueChanged(it))
            }
        )

        Text(
            text = stringResource(id = R.string.select_logic_gate),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
            modifier = Modifier
                .padding(
                    bottom = 8.dp
                )
        )
        OutlinedButton(
            onClick = { viewModel.onEvent(SearchModifierEvent.ToggleDialogShow(show = true)) },
        ) {
            Text(
                text = stringResource(id = viewModel.state.value.mode.gate.nameRes)
            )
        }

        BigIconButton(
            text = stringResource(id = R.string.search),
            onclick = { viewModel.onEvent(SearchModifierEvent.StartSearch) },
            icon = Icons.Rounded.Search)


        if (
            viewModel.state.value.showDialog
        ) {
            DialogSelectLogicGate(
                onDismissRequest = { viewModel.onEvent(SearchModifierEvent.ToggleDialogShow(show = false)) },
                onValueChange = {
                    viewModel.onEvent(SearchModifierEvent.SelectGate(it))
                },
                currentSelectedGate = viewModel.state.value.mode.gate
            )
        }

    }

}