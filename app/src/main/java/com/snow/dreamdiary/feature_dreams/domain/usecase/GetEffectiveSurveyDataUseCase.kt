package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.feature_dreams.domain.model.DailySurveyData

public class GetEffectiveSurveyDataUseCase(
    private val getSurveysUseCase: GetSurveysUseCase
) {
    suspend operator fun invoke(): List<DailySurveyData> {
        val surveys = getSurveysUseCase().sortedByDescending { it.createdAt }.toMutableList()

        for (i in surveys.indices) {
            if (
                i == surveys.size - 1
            ) {
                continue
            }

            val nextSurvey = surveys[i + 1]

            surveys[i] = surveys[i].copy(
                timeSlept = nextSurvey.timeSlept
            )
        }

        return surveys
    }
}