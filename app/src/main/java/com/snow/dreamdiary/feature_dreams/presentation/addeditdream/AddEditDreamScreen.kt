package com.snow.dreamdiary.feature_dreams.presentation.addeditdream;

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.snow.dreamdiary.R
import com.snow.dreamdiary.ui.theme.DreamDiaryApplicationTheme

@Composable
fun AddEditDreamScreen(
    navController: NavController,
    viewModel: AddEditDreamViewModel = hiltViewModel()
) {
    val descState = viewModel.dreamDesc

    val annotationState = viewModel.dreamAnnotation

    val personState = viewModel.persons
    val feelingState = viewModel.feelings
    val locationState = viewModel.locations

    val comfortState = viewModel.comfortness
    val dreamtAt = viewModel.dreamtAt

    // TODO: Collect Data and respond!!!
    val actionFlow = viewModel.actionFlow

    val scope = rememberCoroutineScope()

    DreamDiaryApplicationTheme {

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreviewAddEditDreamScreen() {
    DreamDiaryApplicationTheme(
        darkTheme = true
    ) {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Sharp.ArrowBack,
                            contentDescription = stringResource(id = R.string.cd_back)
                        )
                    },
                    title = {
                        Text(
                            text = stringResource(id = R.string.add_dream),
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = { /*TODO*/ },
                    text = {
                        Text(
                            text = stringResource(id = R.string.add)
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Sharp.Add,
                            contentDescription = stringResource(id = R.string.cd_add)
                        )
                    }
                )
            },
            floatingActionButtonPosition = FabPosition.End,

            ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Column(

                ) {
                }
            }
        }
    }

}