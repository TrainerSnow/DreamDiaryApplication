package com.snow.dreamdiary.feature_dreams.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.snow.dreamdiary.R


sealed class DreamScreens(
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector
) {
    object AddEditDreamScreen : DreamScreens(
        route = "route_addeditdreamscreen?$KEY_DREAM_ID={$KEY_DREAM_ID}",
        title = R.string.addeditdream,
        icon = Icons.Rounded.Add
    ){
        fun passDreamId(id: Int): String{
            return "route_addeditdreamscreen?$KEY_DREAM_ID=$id"
        }
    }

    object DreamStartScreen : DreamScreens(
        route = "route_mainstartscreen",
        title = R.string.home,
        icon = Icons.Rounded.Home
    )

    object OverviewDreamsScreen : DreamScreens(
        route = "route_overviewdreamsscreen",
        title = R.string.view_dreams,
        icon = Icons.Rounded.Movie
    )

    object SearchModeScreen : DreamScreens(
        route = "route_searchmodescreen",
        title = R.string.searhc_mode,
        icon = Icons.Rounded.Search
    )
}

