package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bycomfortness

sealed class SearchComfortnessEvent{
    data class ChangeToValue(val to: String): SearchComfortnessEvent()
    data class ChangeFromValue(val from: String): SearchComfortnessEvent()
    object StartSearch: SearchComfortnessEvent()
}