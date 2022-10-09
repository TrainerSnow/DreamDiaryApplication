package com.snow.dreamdiary.feature_dailysurvey.domain.usecase;

import com.snow.dreamdiary.feature_dailysurvey.domain.model.DailySurveyData
import com.snow.dreamdiary.feature_dailysurvey.domain.repository.DailySurveyRepository

public class AddSurveyUseCase(
    private val repository: DailySurveyRepository
) {
    suspend operator fun invoke(data: DailySurveyData){
        if(repository.canInsertSurvey())
            repository.insertDailySurveyData(data)
    }
}