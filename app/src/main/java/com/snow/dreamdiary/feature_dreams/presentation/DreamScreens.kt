package com.snow.dreamdiary.feature_dreams.presentation

const val KEY_DREAM_ID = "dream_id"

sealed class DreamScreens(val route: String, val args: String = "") {
    object AddEditDreamScreen : DreamScreens(
        route = "route_addeditdreamscreen",
        args = formattedArgName(KEY_DREAM_ID)
    )
    object MainStartScreen : DreamScreens(
        route = "route_mainstartscreen"
    )
    object OverviewDreamsScreen : DreamScreens(
        route = "route_overviewdreamsscreen"
    )

    companion object{

        private fun formattedArgName(arg: String): String{
            return "{$arg}"
        }

    }
}


