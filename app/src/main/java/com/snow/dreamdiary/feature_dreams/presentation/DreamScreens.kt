package com.snow.dreamdiary.feature_dreams.presentation

sealed class DreamScreens(val route: String){
    object AddEditDreamScreen: DreamScreens("route_addeditdreamscreen")
    object MainStartScreen: DreamScreens("route_mainstartscreen")
    object OverviewDreamsScreen: DreamScreens("route_overviewdreamsscreen")
}
