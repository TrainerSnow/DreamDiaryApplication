package com.snow.dreamdiary.feature_dreams.presentation.navigation

import com.snow.dreamdiary.feature_dreams.domain.util.DreamSearchModes

sealed class DreamScreens(
    open var route: String
) {

    object SearchModeScreen : DreamScreens(
        route = "route_searchmodescreen"
    )

    object OptionsOverviewScreen : DreamScreens(
        route = "route_optionsoverviewscreen"
    )

    data class SearchDreamtScreen(override var route: String = "route_searchdreamtscreen/{$KEY_MODE}") :
        DreamScreens(route) {
        fun withMode(mode: DreamSearchModes.ByDreamt): SearchDreamtScreen {
            this.route = this.route.replace("{$KEY_MODE}", mode.toJson().toString())
            return this
        }
    }

    data class SearchModifierScreen(override var route: String = "route_searchmodifierscreen/{$KEY_MODE}") :
        DreamScreens(route) {
        fun withMode(mode: DreamSearchModes.ByModifier): SearchModifierScreen {
            this.route = this.route.replace("{$KEY_MODE}", mode.toJson().toString())
            return this
        }
    }

    data class SearchComfortnessScreen(override var route: String = "route_searchcomfortnessscreen/{$KEY_MODE}") :
        DreamScreens(route) {
        fun withMode(mode: DreamSearchModes.ByComfortness): SearchComfortnessScreen {
            this.route = this.route.replace("{$KEY_MODE}", mode.toJson().toString())
            return this
        }
    }

    data class ViewSearchedDreamsScreen(override var route: String = "route_viewsearcheddreamsscreen/{$KEY_MODE}") :
        DreamScreens(route) {
        fun withMode(mode: DreamSearchModes): ViewSearchedDreamsScreen {
            this.route = this.route.replace("{$KEY_MODE}", mode.toJson().toString())
            return this
        }
    }

    @Deprecated("Don't use, won't work")
    object SearchConfigScreen : DreamScreens(
        route = "route_searchconfigscreen/{mode}"
    )
}
