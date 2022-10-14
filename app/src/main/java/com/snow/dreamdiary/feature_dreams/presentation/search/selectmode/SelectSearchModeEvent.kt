package com.snow.dreamdiary.feature_dreams.presentation.search.selectmode

import com.snow.dreamdiary.feature_dreams.domain.util.DreamSearchModes

sealed class SelectSearchModeEvent{
    data class ModeSelected(val mode: DreamSearchModes): SelectSearchModeEvent()
}
