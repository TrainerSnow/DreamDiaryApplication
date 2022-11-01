package com.snow.dreamdiary.feature_dreams.domain.usecase

import com.snow.dreamdiary.feature_dreams.domain.model.DailySurveyData
import com.snow.dreamdiary.feature_dreams.domain.repository.DailySurveyRepository
import kotlinx.coroutines.flow.Flow

class GetSurveysUseCase(
    private val repository: DailySurveyRepository
) {
    operator fun invoke(): Flow<List<DailySurveyData>> {
        return repository.getSurveyDatas()
    }
}