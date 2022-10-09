package com.snow.dreamdiary.feature_dreams.data.repository;

import com.snow.dreamdiary.feature_dreams.data.source.DailySurveyDataDao
import com.snow.dreamdiary.feature_dreams.domain.model.DailySurveyData
import com.snow.dreamdiary.feature_dreams.domain.repository.DailySurveyRepository
import kotlinx.coroutines.flow.Flow

public class DailySurveyRepositoryImpl(
    private val surveyDao: DailySurveyDataDao
): DailySurveyRepository {

    override fun getSurveyDatas(): Flow<List<DailySurveyData>> {
        return surveyDao.getSurveys()
    }

    override suspend fun insertDailySurveyData(data: DailySurveyData) {
        surveyDao.insertDailySurveyData(data)
    }

}