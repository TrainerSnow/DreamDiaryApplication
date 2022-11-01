package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.feature_dreams.domain.model.DailySurveyData

public class GetSurveyPartsOverAverageNum(
    private val getSurveysUseCase: GetSurveysUseCase,
    private val getDailySurveyPartAverageUseCase: GetDailySurveyPartAverageUseCase
) {
    suspend operator fun invoke(
        selector : (DailySurveyData) -> Int,
        overDefinition: Float = 1.4F
    ): Int {
        val avg = getDailySurveyPartAverageUseCase(selector)
        val surveys = getSurveysUseCase()

        var num = 0

        surveys.forEach {
            val value = selector(it)
            if ((avg * overDefinition) < value) {
                num += 1
            }
        }

        return num
    }

    suspend operator fun invoke(
        selector: (DailySurveyData) -> Int,
        overDefinition: Float = 1.4F,
        dateRange: LongRange
    ): Int {
        val avg = getDailySurveyPartAverageUseCase(dateRange, selector)
        val surveys = getSurveysUseCase(dateRange)

        var num = 0

        surveys.forEach {
            val value = selector(it)
            if ((avg * overDefinition) < value) {
                num += 1
            }
        }

        return num
    }
}