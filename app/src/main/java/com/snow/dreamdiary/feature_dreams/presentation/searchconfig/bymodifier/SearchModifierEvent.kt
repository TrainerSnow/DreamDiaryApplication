package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bymodifier

import com.snow.dreamdiary.common.util.LogicGate

sealed class SearchModifierEvent {
    data class TextFieldValueChanged(val value: String) : SearchModifierEvent()
    data class ToggleDialogShow(val show: Boolean) : SearchModifierEvent()
    data class SelectGate(val gate: LogicGate) : SearchModifierEvent()
    object StartSearch : SearchModifierEvent()
}