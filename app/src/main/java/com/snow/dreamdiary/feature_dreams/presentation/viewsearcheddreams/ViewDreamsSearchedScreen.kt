package com.snow.dreamdiary.feature_dreams.presentation.viewsearcheddreams

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.feature_dreams.presentation.bottomnav.viewdreams.components.DreamItem
import com.snow.dreamdiary.feature_dreams.presentation.viewsearcheddreams.components.SearchMetaDataSection

@Composable
fun ViewDreamsSearchedScreen(
    navController: NavHostController,
    viewModel: ViewDreamsSearchedViewModel = hiltViewModel()
) {
    val dreams = viewModel.state.value.dreams
    val timeNeeded = viewModel.state.value.timeNeeded


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                end = 16.dp,
                start = 16.dp,
                top = 16.dp
            )
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SearchMetaDataSection(
                dreamsFound = dreams.size,
                timeNeeded = timeNeeded
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    count = dreams.size
                ) {
                    DreamItem(

                        dream = dreams[it],
                        onEditClick = {},
                        onDeleteClick = {},
                        takeMaxSize = false,
                        showButtons = false
                    )
                }
            }
        }
    }
}