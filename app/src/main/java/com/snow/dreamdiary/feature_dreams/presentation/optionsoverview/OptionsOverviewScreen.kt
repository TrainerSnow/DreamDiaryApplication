package com.snow.dreamdiary.feature_dreams.presentation.optionsoverview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.snow.dreamdiary.R

@Composable
fun OptionsOverviewScreen(
    navController: NavHostController
) {
    Column(
        modifier =
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TODO: Change this from button to good looking squares

        Button(
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = stringResource(id = R.string.search_dreams)
            )
        }

        for( i in 0 until 4){
            Button(
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = stringResource(id = R.string.search_dreams)
                )
            }
        }
    }
}