package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.feature_dreams.domain.model.DailySurveyData

public class GetSurveyPartsBelowAverageNum(
    private val getSurveysUseCase: GetSurveysUseCase,
    private val getDailySurveyPartAverageUseCase: GetDailySurveyPartAverageUseCase
) {
    suspend operator fun invoke(
        selector : (DailySurveyData) -> Int,
        belowDefinition: Float = 0.75F
    ): Int {
        val avg = getDailySurveyPartAverageUseCase(selector)
        val surveys = getSurveysUseCase()

        var num = 0

        surveys.forEach {
            val value = selector(it)
            if ((avg * belowDefinition)> value) {
                num += 1
            }
        }

        return num
    }

    suspend operator fun invoke(
        selector: (DailySurveyData) -> Int,
        belowDefinition: Float = 0.75F,
        dateRange: LongRange
    ): Int {
        val avg = getDailySurveyPartAverageUseCase(dateRange, selector)
        val surveys = getSurveysUseCase(dateRange)

        var num = 0

        surveys.forEach {
            val value = selector(it)
            if ((avg * belowDefinition) > value) {
                num += 1
            }
        }

        return num
    }
}