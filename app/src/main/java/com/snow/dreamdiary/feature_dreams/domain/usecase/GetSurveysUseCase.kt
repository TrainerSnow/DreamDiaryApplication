package com.snow.dreamdiary.feature_dreams.domain.usecase

import com.snow.dreamdiary.feature_dreams.domain.model.DailySurveyData
import com.snow.dreamdiary.feature_dreams.domain.repository.DailySurveyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class GetSurveysUseCase(
    private val repository: DailySurveyRepository
) {
    suspend operator fun invoke(): List<DailySurveyData> {
        return repository.getSurveyDatas().first()
    }
}