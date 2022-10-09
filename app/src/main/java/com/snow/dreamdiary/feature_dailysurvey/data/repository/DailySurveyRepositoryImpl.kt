package com.snow.dreamdiary.feature_dailysurvey.data.repository;

import com.snow.dreamdiary.feature_dailysurvey.data.source.DailySurveyDataDao
import com.snow.dreamdiary.feature_dailysurvey.domain.model.DailySurveyData
import com.snow.dreamdiary.feature_dailysurvey.domain.repository.DailySurveyRepository
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