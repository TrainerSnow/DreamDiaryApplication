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
    val color: Color = dreamColors[0],
    @PrimaryKey val id: Int
) {
    companion object {
        val dreamColors = listOf(
            Color(255, 250, 129),
            Color(179, 226, 221),
            Color(251, 182, 209),
            Color(252, 169, 133)
        )
    }
}
