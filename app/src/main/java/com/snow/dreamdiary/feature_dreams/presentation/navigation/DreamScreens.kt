package com.snow.dreamdiary.feature_dreams.presentation.navigation

sealed class DreamScreens(
    val route: String
) {

    object SearchModeScreen : DreamScreens(
        route = "route_searchmodescreen"
    )

    object OptionsOverviewScreen : DreamScreens(
        route = "route_optionsoverviewscreen"
    )
}
