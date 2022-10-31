package com.snow.dreamdiary.feature_dreams.presentation.stats.advanced

import com.snow.dreamdiary.feature_dreams.domain.util.DreamModifier

sealed class AdvancedStatsEvent {
    data class ChangeModifier(val modifier: DreamModifier) : AdvancedStatsEvent()
    data class UpdateSearchedValue(val value: String) : AdvancedStatsEvent()

    object ChangeToComfortness : AdvancedStatsEvent()
    object Search : AdvancedStatsEvent()
}
