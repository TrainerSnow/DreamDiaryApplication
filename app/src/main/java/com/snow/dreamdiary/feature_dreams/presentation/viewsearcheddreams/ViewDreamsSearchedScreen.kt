package com.snow.dreamdiary.feature_dreams.presentation.viewsearcheddreams

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.snow.dreamdiary.common.presentation.components.DreamPreviewCard
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
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                SearchMetaDataSection(
                    dreamsFound = dreams.size,
                    timeNeeded = timeNeeded
                )
            }

            items(count = viewModel.state.value.dreams.size) { i ->
                DreamPreviewCard(
                    dream = viewModel.state.value.dreams[i],
                )
            }
        }
    }
}