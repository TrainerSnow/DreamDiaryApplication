package com.snow.dreamdiary.feature_dreams.presentation.searchconfig.bydreamt

data class SearchDreamtState(
    val dreamtFrom: Long = System.currentTimeMillis(),
    val dreamtTo: Long = System.currentTimeMillis()
)
