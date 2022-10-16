package com.snow.dreamdiary.feature_dreams.presentation.viewsearcheddreams

import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.util.DreamSearchModes

data class ViewDreamsSearchedState(
    val dreams: MutableList<Dream> = mutableListOf(),
    val searchMode: DreamSearchModes,
    val timeNeeded: Long = 0L
)
