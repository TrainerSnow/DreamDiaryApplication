package com.snow.dreamdiary.feature_dreams.presentation.stats.simple

import com.snow.dreamdiary.common.util.TimeFormatUtil
import com.snow.dreamdiary.feature_dreams.domain.util.DreamModifier

data class SimpleStatsState(

    var modifier: DreamModifier? = DreamModifier.Person,
    var showComfortness: Boolean = false,

    var fromDateValue: Long = System.currentTimeMillis(),
    var fromDateVis: String = TimeFormatUtil.getMillisDayFormatted(fromDateValue),

    var toDateValue: Long = System.currentTimeMillis(),
    var toDateVis: String = TimeFormatUtil.getMillisDayFormatted(toDateValue),

    var showDialog: Boolean = false,

    var modifierLabels: List<String> = emptyList(),
    var modifierData: List<Int> = emptyList()
)
