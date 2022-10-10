package com.snow.dreamdiary.feature_dreams.presentation.addeditdream;

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.snow.dreamdiary.R

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


    DefaultContent()

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultContent() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.add_dream)
            )
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreviewAddEditDreamScreen() {
    DefaultContent()
}