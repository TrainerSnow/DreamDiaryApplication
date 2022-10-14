package com.snow.dreamdiary.feature_dreams.presentation.startscreen

import com.snow.dreamdiary.feature_dreams.presentation.navigation.DreamScreens

sealed class StartScreenEvent{
    data class OpenScreen(val screen: DreamScreens): StartScreenEvent()
}
