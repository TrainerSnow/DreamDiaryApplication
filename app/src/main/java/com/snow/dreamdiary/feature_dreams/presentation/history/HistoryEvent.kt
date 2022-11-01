package com.snow.dreamdiary.feature_dreams.presentation.history

sealed class HistoryEvent{
    object EnableModifierView: HistoryEvent()
    object EnableDreamView: HistoryEvent()
    object SendSearch: HistoryEvent()
    object ToggleView: HistoryEvent()

    data class SearchedValueChanged(val value: String): HistoryEvent()
}
