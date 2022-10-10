package com.snow.dreamdiary.feature_dreams.presentation.addeditdream

import com.snow.dreamdiary.feature_dailysurvey.domain.util.TimeUtil

data class AddEditDreamState(
    val description: String = "",
    val annotation: String = "",
    val persons: List<String> = emptyList(),
    val feelings: List<String> = emptyList(),
    val locations: List<String> = emptyList(),
    val comfortness: Int = 0,
    val createdAt: Long = System.currentTimeMillis(),
    val dreamtAt: Long = createdAt
)
