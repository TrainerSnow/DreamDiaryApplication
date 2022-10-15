package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bycomfortness

import com.snow.dreamdiary.feature_dreams.domain.util.DreamSearchModes

data class SearchComfortnessState(
    val from: String = "2",
    val to: String = "8",
    val mode: DreamSearchModes.ByComfortness = DreamSearchModes.ByComfortness()
)
