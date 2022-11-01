package com.snow.dreamdiary.feature_dreams.domain.model

import androidx.annotation.IntRange
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DailySurveyData(
    val didDream: Boolean = false,
    val dreamsNum: Int = 0,
    val timeSlept: Long = 8,
    @IntRange(from = 0, to = 10) val health: Int = 5,
    @IntRange(from = 0, to = 10) val physicalActivity: Int = 5,
    @IntRange(from = 0, to = 10) val comfortness: Int = 5,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val createdAt: Long
)
