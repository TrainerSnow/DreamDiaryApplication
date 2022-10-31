package com.snow.dreamdiary.feature_dreams.presentation.stats.advanced

import com.snow.dreamdiary.feature_dreams.domain.util.DreamModifier

data class AdvancedStatsState(
    var modifier: DreamModifier? = DreamModifier.Person,
    var showComfortness: Boolean = false,

    var modifierLabels: List<String> = emptyList(),
    var modifierData: List<Int> = emptyList(),

    val searchedValue: String = ""
)
