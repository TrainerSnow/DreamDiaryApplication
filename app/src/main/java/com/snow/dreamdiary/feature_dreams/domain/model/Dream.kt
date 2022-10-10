package com.snow.dreamdiary.feature_dreams.domain.model

import androidx.annotation.IntRange
import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dream(
    val description: String = "",
    val annotation: String = "",
    val persons: List<String> = emptyList(),
    val feelings: List<String> = emptyList(),
    val locations: List<String> = emptyList(),
    @IntRange(from = 0, to = 10) val comfortness: Int = 5,

    val createdAt: Long,
    val dreamtAt: Long,
    @Deprecated("")
    @PrimaryKey val id: Int? = null
) {
}
