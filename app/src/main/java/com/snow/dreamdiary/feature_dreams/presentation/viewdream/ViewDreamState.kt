package com.snow.dreamdiary.feature_dreams.presentation.viewdream

import com.snow.dreamdiary.feature_dreams.domain.model.Dream

data class ViewDreamState(
    val dream: Dream? = null,

    val descExpanded: Boolean = true,
    val annotationExpanded: Boolean = true,
    val personsExpanded: Boolean = true,
    val feelingsExpanded: Boolean = true,
    val locationsExpanded: Boolean = true,
    val comfortnessExpanded: Boolean = true
)
