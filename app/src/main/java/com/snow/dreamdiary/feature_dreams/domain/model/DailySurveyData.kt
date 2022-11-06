package com.snow.dreamdiary.feature_dreams.domain.model

import androidx.annotation.IntRange
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DailySurveyData(
    var didDream: Boolean = false,
    var dreamsNum: Int = 0,
    var timeSlept: Long = 8,
    @IntRange(from = 0, to = 10) var health: Int = 5,
    @IntRange(from = 0, to = 10) var physicalActivity: Int = 5,
    @IntRange(from = 0, to = 10) var comfortness: Int = 5,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    var createdAt: Long
)
