package com.snow.dreamdiary.feature_dreams.presentation.searchmode

import com.snow.dreamdiary.feature_dreams.presentation.navigation.DreamScreens

sealed class SearchModeEvent{
    data class OpenScreen(val screen: DreamScreens) : SearchModeEvent()

    object OpenSearchModifiers: SearchModeEvent()
    object OpenSearchComfortness: SearchModeEvent()
    object OpenSearchDreamt: SearchModeEvent()
}
