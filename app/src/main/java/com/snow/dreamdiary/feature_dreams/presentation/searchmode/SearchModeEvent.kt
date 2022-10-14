package com.snow.dreamdiary.feature_dreams.presentation.searchmode

import com.snow.dreamdiary.feature_dreams.presentation.navigation.BottomNavScreens

sealed class SearchModeEvent{
    data class OpenScreen(val screen: BottomNavScreens): SearchModeEvent()
}
