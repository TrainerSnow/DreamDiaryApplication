package com.snow.dreamdiary.feature_dreams.domain.util

import com.snow.dreamdiary.common.util.LogicGate

sealed class DreamSearchModes{
    data class ByModifier(
        val modifier: DreamModifier = DreamModifier.Person,
        val values: List<String>,
        val gate: LogicGate = LogicGate.And
    ): DreamSearchModes()
    data class ByComfortness(
        val fromVal: Byte,
        val toVal: Byte
    ): DreamSearchModes()
    data class ByDreamt(
        val fromTime: Long,
        val toTime: Long
    ): DreamSearchModes()
}
