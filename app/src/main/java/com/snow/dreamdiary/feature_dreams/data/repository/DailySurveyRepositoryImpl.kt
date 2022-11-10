package com.snow.dreamdiary.feature_dreams.data.repository

import com.snow.dreamdiary.common.util.TimeUtil
import com.snow.dreamdiary.feature_dreams.data.source.DailySurveyDataDao
import com.snow.dreamdiary.feature_dreams.domain.model.DailySurveyData
import com.snow.dreamdiary.feature_dreams.domain.repository.DailySurveyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class DailySurveyRepositoryImpl(
    private val surveyDao: DailySurveyDataDao
) : DailySurveyRepository {

    override fun getSurveyDatas(): Flow<List<DailySurveyData>> {
        return surveyDao.getSurveys()
    }

    override suspend fun insertDailySurveyData(data: DailySurveyData) {
        surveyDao.insertDailySurveyData(data)
    }

    override suspend fun canInsertSurvey(): Boolean {
        val surveysFlow = surveyDao.getSurveys()
        val surveys: List<DailySurveyData> = surveysFlow.first()
        var flag = true

        surveys.forEach {
            if (it.createdAt == TimeUtil.thisDayStartInMillis()) {
                flag = false
            }
        }

        return flag
    }

    override suspend fun deleteAllSurveys() {
        surveyDao.deleteAllSurveys()
    }

}