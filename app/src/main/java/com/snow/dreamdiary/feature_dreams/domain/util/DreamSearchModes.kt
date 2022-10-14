package com.snow.dreamdiary.feature_dreams.domain.util

sealed class DreamSearchModes{
    data class ByModifier(
        val modifiers: DreamModifier,
        val values: List<String>
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
