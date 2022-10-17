package com.snow.dreamdiary.feature_dailysurvey.domain.usecase;

import com.snow.dreamdiary.feature_dailysurvey.domain.model.DailySurveyData
import com.snow.dreamdiary.feature_dailysurvey.domain.repository.DailySurveyRepository
import kotlinx.coroutines.flow.Flow

public class GetSurveysUseCase(
    private val repository: DailySurveyRepository
) {
    operator fun invoke(): Flow<List<DailySurveyData>> {
        return repository.getSurveyDatas()
    }
}