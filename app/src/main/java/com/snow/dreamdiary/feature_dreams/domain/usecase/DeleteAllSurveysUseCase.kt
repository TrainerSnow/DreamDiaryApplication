package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.feature_dreams.domain.repository.DailySurveyRepository

public class DeleteAllSurveysUseCase(
    private val repository: DailySurveyRepository
) {
    suspend operator fun invoke(){
        repository.deleteAllSurveys()
    }
}