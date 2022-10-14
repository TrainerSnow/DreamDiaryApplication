package com.snow.dreamdiary.feature_dreams.domain.util

import com.snow.dreamdiary.common.util.LogicGate

sealed class DreamSearchModes{
    data class ByModifier(
        val modifier: DreamModifier = DreamModifier.Person,
        val values: List<String> = listOf(""),
        val gate: LogicGate = LogicGate.And
    ): DreamSearchModes()
    data class ByComfortness(
        val fromVal: Byte = 0,
        val toVal: Byte = 0
    ): DreamSearchModes()
    data class ByDreamt(
        val fromTime: Long = 0,
        val toTime: Long = 0
    ): DreamSearchModes()
}
