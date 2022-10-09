package com.snow.dreamdiary.feature_dreams.domain.model

import androidx.annotation.IntRange
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.IDN

@Entity
data class DailySurveyData(
    val didDream: Boolean,
    val timeSlept: Long,
    @IntRange(from = 0, to = 10) val health: Int,
    @IntRange(from = 0, to = 10) val physicalActivity: Int,
    @IntRange(from = 0, to = 10) val comfortness: Int,
    @PrimaryKey val idn: Int
)
