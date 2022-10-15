package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bymodifier

import com.snow.dreamdiary.feature_dreams.domain.util.DreamSearchModes

data class SearchModifierState(
    val mode: DreamSearchModes.ByModifier = DreamSearchModes.ByModifier(),
    val textFieldInput: String = "",
    val showDialog: Boolean = false
)
