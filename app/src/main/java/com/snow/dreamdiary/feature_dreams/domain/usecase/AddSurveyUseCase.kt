package com.snow.dreamdiary.feature_dreams.domain.usecase

import com.snow.dreamdiary.feature_dreams.domain.model.DailySurveyData
import com.snow.dreamdiary.feature_dreams.domain.repository.DailySurveyRepository

class AddSurveyUseCase(
    private val repository: DailySurveyRepository
) {
    suspend operator fun invoke(data: DailySurveyData) {
        if (repository.canInsertSurvey())
            repository.insertDailySurveyData(data)
    }
}