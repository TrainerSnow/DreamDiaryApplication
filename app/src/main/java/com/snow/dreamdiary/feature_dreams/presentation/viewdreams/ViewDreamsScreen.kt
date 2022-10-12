package com.snow.dreamdiary.feature_dreams.presentation.viewdreams

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.R
import com.snow.dreamdiary.common.util.TimeFormatUtil
import com.snow.dreamdiary.feature_dreams.presentation.viewdreams.components.DreamItem
import com.snow.dreamdiary.ui.theme.DreamDiaryApplicationTheme
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewDreamsScreen(
    navController: NavHostController,
    viewModel: ViewDreamsViewModel = hiltViewModel()
) {

    val state = viewModel.state

    val actionFlow = viewModel.actionFlow

    val currentDream = state.value.dreams.getOrNull(state.value.currentDreamIndex)

    val context = LocalContext.current

    LaunchedEffect(key1 = 1) {
        actionFlow.collectLatest { event ->
            when (event) {
                is ViewDreamsViewModel.UIEvent.Message -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    DreamDiaryApplicationTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Sharp.ArrowBack,
                            contentDescription = stringResource(id = R.string.cd_back)
                        )
                    },
                    title = {
                        Text(
                            if (currentDream == null)
                                stringResource(id = R.string.view_dreams)
                            else
                                String.format(
                                    stringResource(id = R.string.view_dream_x),
                                    TimeFormatUtil.getMillisFormatted(currentDream.dreamtAt)
                                ),
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
            ) {

                Row {
                    Icon(
                        modifier = Modifier
                            .clickable { viewModel.onEvent(ViewDreamsEvent.RecentDream) },
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = stringResource(id = R.string.cd_back)
                    )
                    Icon(
                        modifier = Modifier
                            .clickable { viewModel.onEvent(ViewDreamsEvent.NextDream) },
                        imageVector = Icons.Rounded.ArrowForward,
                        contentDescription = stringResource(id = R.string.cd_next)
                    )

                }
                if (currentDream != null) {
                    DreamItem(
                        dream = currentDream,
                        onDeleteClick = { /*TODO*/ },
                        onEditClick = { /*TODO*/ }
                    )
                }
            }
        }
    }
}