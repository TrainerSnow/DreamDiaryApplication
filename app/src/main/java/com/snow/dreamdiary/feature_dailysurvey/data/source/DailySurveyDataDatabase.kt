package com.snow.dreamdiary.feature_dailysurvey.data.source;

import androidx.room.Database
import androidx.room.RoomDatabase
import com.snow.dreamdiary.feature_dailysurvey.domain.model.DailySurveyData

@Database(
    entities = [
        DailySurveyData::class
    ],
    version = 1
)
abstract class DailySurveyDataDatabase: RoomDatabase() {
    abstract val dailySurveyDataDao: DailySurveyDataDao
}