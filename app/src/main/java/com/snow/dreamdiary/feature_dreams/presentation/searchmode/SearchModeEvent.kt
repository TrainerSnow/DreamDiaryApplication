package com.snow.dreamdiary.feature_dreams.presentation.searchmode

import com.snow.dreamdiary.feature_dreams.presentation.navigation.DreamScreens
import com.snow.dreamdiary.feature_dreams.presentation.startscreen.StartScreenEvent

sealed class SearchModeEvent{
    data class OpenScreen(val screen: DreamScreens): SearchModeEvent()
}
