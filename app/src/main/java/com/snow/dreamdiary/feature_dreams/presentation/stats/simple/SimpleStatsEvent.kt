package com.snow.dreamdiary.feature_dreams.presentation.stats.simple

import com.snow.dreamdiary.feature_dreams.domain.util.DreamModifier

sealed class SimpleStatsEvent {
    data class ChangeModifier(val modifier: DreamModifier) : SimpleStatsEvent()
    data class ChangeTimeFrom(val newTime: Long) : SimpleStatsEvent()
    data class ChangeTimeTo(val newTime: Long) : SimpleStatsEvent()
    object ToggleDialog : SimpleStatsEvent()
    object ChangeToComfortness : SimpleStatsEvent()
}
