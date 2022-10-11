package com.snow.dreamdiary.feature_dreams.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Movie
import androidx.compose.ui.graphics.vector.ImageVector
import com.snow.dreamdiary.R


sealed class BottomNavDreamScreens(
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector
) {
    object AddEditDreamScreen : BottomNavDreamScreens(
        route = "route_addeditdreamscreen",
        title = R.string.addeditdream,
        icon = Icons.Rounded.Add
    )

    object DreamStartScreen : BottomNavDreamScreens(
        route = "route_mainstartscreen",
        title = R.string.home,
        icon = Icons.Rounded.Home
    )

    object OverviewDreamsScreen : BottomNavDreamScreens(
        route = "route_overviewdreamsscreen",
        title = R.string.view_dreams,
        icon = Icons.Rounded.Movie
    )
}

