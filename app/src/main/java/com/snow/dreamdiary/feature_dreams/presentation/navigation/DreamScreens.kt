package com.snow.dreamdiary.feature_dreams.presentation.navigation

import com.snow.dreamdiary.feature_dreams.domain.util.DreamSearchModes

sealed class DreamScreens(
    var route: String
) {

    object SearchModeScreen : DreamScreens(
        route = "route_searchmodescreen"
    )

    object OptionsOverviewScreen : DreamScreens(
        route = "route_optionsoverviewscreen"
    )

    object SearchConfigScreen : DreamScreens(
        route = "route_searchconfigscreen/{mode}"
    ){
        fun withDreamSearchMode(mode: DreamSearchModes): SearchConfigScreen{
            val modeJson = when (mode) {
                is DreamSearchModes.ByComfortness -> mode.toJson()
                is DreamSearchModes.ByDreamt -> mode.toJson()
                is DreamSearchModes.ByModifier -> mode.toJson()
            }
            SearchConfigScreen.route.replace("mode", modeJson.toString())
            return SearchConfigScreen
        }
    }
}
