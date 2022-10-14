package com.snow.dreamdiary.feature_dreams.presentation.optionsoverview

import com.snow.dreamdiary.feature_dreams.presentation.navigation.DreamScreens

sealed class OptionsOverviewEvent{
    data class GoToScreen(val screen: DreamScreens): OptionsOverviewEvent()
}
