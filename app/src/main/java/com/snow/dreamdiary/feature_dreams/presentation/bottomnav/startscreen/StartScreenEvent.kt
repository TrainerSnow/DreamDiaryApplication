package com.snow.dreamdiary.feature_dreams.presentation.bottomnav.startscreen

import com.snow.dreamdiary.feature_dreams.presentation.navigation.BottomNavScreens

sealed class StartScreenEvent{
    data class OpenScreen(val screen: BottomNavScreens): StartScreenEvent()
}
