package com.snow.dreamdiary.feature_dreams.domain.model

import androidx.annotation.IntRange
import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dream(
    val description: String,
    val annotation: List<String>,
    val persons: List<String>,
    val feelings: List<String>,
    val locations: List<String>,
    @IntRange(from = 0, to = 10) val comfortness: Int,

    val createdAt: Long,
    val dreamtAt: Long,
    val color: Int,
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
