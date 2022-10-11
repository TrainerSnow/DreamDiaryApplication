package com.snow.dreamdiary.feature_dreams.presentation.navigation


sealed class BottomNavDreamScreens(
    val route: String
) {
    object AddEditDreamScreen : BottomNavDreamScreens(
        route = "route_addeditdreamscreen"
    )

    object DreamStartScreen : BottomNavDreamScreens(
        route = "route_mainstartscreen"
    )

    object OverviewDreamsScreen : BottomNavDreamScreens(
        route = "route_overviewdreamsscreen"
    )
}

