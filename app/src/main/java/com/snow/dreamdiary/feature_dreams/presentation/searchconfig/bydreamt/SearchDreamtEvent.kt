package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bydreamt

sealed class SearchDreamtEvent {
    data class ChangeDreamtFrom(val value: Long) : SearchDreamtEvent()
    data class ChangeDreamtTo(val value: Long) : SearchDreamtEvent()
    object StartSearch : SearchDreamtEvent()
}