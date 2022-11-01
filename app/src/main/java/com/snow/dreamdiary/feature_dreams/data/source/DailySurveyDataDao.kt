package com.snow.dreamdiary.feature_dreams.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.snow.dreamdiary.feature_dreams.domain.model.DailySurveyData
import kotlinx.coroutines.flow.Flow

@Dao
interface DailySurveyDataDao {

    @Query("SELECT * FROM dailysurveydata")
    fun getSurveys(): Flow<List<DailySurveyData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailySurveyData(data: DailySurveyData)

}