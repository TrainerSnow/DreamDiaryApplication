package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bydreamt

import com.snow.dreamdiary.feature_dreams.domain.util.DreamSearchModes

data class SearchDreamtState(
    val mode: DreamSearchModes.ByDreamt = DreamSearchModes.ByDreamt()
)
