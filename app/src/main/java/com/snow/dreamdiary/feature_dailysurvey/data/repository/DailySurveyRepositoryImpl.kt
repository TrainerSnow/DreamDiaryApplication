package com.snow.dreamdiary.feature_dailysurvey.data.repository;

import com.snow.dreamdiary.feature_dailysurvey.data.source.DailySurveyDataDao
import com.snow.dreamdiary.feature_dailysurvey.domain.model.DailySurveyData
import com.snow.dreamdiary.feature_dailysurvey.domain.repository.DailySurveyRepository
import com.snow.dreamdiary.feature_dailysurvey.domain.util.TimeUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.take

public class DailySurveyRepositoryImpl(
    private val surveyDao: DailySurveyDataDao
): DailySurveyRepository {

    override fun getSurveyDatas(): Flow<List<DailySurveyData>> {
        return surveyDao.getSurveys()
    }

    override suspend fun insertDailySurveyData(data: DailySurveyData) {
        surveyDao.insertDailySurveyData(data)
    }

    override suspend fun canInsertSurvey(): Boolean {
        val surveys = surveyDao.getSurveys()
        var flag = true

        surveys.collectIndexed { index, value ->
            val createdAt = value[index].createdAt
            if(createdAt == TimeUtil.thisDayStartInMillis())
                flag = false
        }
        return flag
    }

}