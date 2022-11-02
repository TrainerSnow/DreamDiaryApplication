package com.snow.dreamdiary.feature_dreams.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.ui.graphics.vector.ImageVector
import com.snow.dreamdiary.R


sealed class BottomNavScreens(
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector
) {
    object AddEditDreamScreen : BottomNavScreens(
        route = "route_addeditdreamscreen?$KEY_DREAM_ID={$KEY_DREAM_ID}",
        title = R.string.addeditdream,
        icon = Icons.Rounded.Add
    ) {
        fun passDreamId(id: Int): String {
            return "route_addeditdreamscreen?$KEY_DREAM_ID=$id"
        }
    }

    object DreamStartScreen : BottomNavScreens(
        route = "route_mainstartscreen",
        title = R.string.home,
        icon = Icons.Rounded.Home
    )

    object OverviewDreamsScreen : BottomNavScreens(
        route = "route_overviewdreamsscreen",
        title = R.string.view_dreams,
        icon = Icons.Rounded.Movie
    )

    //Not available in bottom nav but needs to be accessible from here
    object ViewDreamScreen:
        BottomNavScreens(
            route = "route_viewdreamscreen?$KEY_DREAM_ID={$KEY_DREAM_ID}",
            title  = androidx.viewpager.R.string.status_bar_notification_info_overflow,
            icon = Icons.Rounded.Error
        ) {
        fun withDreamId(id: Int): String {
            return "route_viewdreamscreen?$KEY_DREAM_ID=$id"
        }
    }
}

