package com.snow.dreamdiary.feature_dreams.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.snow.dreamdiary.feature_dreams.domain.model.DailySurveyData

@Database(
    entities = [
        DailySurveyData::class
    ],
    version = 2
)
abstract class DailySurveyDataDatabase : RoomDatabase() {
    abstract val dailySurveyDataDao: DailySurveyDataDao

    companion object {
        const val DATABASE_NAME = "surveys.db"
    }
}