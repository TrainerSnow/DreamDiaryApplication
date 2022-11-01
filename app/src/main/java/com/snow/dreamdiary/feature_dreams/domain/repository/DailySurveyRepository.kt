package com.snow.dreamdiary.feature_dreams.domain.repository

import com.snow.dreamdiary.feature_dreams.domain.model.DailySurveyData
import kotlinx.coroutines.flow.Flow

interface DailySurveyRepository {

    fun getSurveyDatas(): Flow<List<DailySurveyData>>

    suspend fun insertDailySurveyData(data: DailySurveyData)

    suspend fun canInsertSurvey(): Boolean

}