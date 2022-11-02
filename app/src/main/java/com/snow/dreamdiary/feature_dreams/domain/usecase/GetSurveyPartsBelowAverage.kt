package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.feature_dreams.domain.model.DailySurveyData

public class GetSurveyPartsBelowAverage(
    private val getSurveysUseCase: GetSurveysUseCase,
    private val getDailySurveyPartAverageUseCase: GetDailySurveyPartAverageUseCase
) {
    suspend operator fun invoke(
        selector : (DailySurveyData) -> Int,
        belowDefinition: Float = 0.75F
    ): List<Long> {
        val avg = getDailySurveyPartAverageUseCase(selector)
        val surveys = getSurveysUseCase()

        val rList: MutableList<Long> = mutableListOf()

        surveys.forEach {
            val value = selector(it)
            if ((avg * belowDefinition)> value) {
                rList.add(it.createdAt)
            }
        }

        return rList
    }

    suspend operator fun invoke(
        selector: (DailySurveyData) -> Int,
        belowDefinition: Float = 0.75F,
        dateRange: LongRange
    ): List<Long> {
        val avg = getDailySurveyPartAverageUseCase(dateRange, selector)
        val surveys = getSurveysUseCase(dateRange)

        val rList: MutableList<Long> = mutableListOf()

        surveys.forEach {
            val value = selector(it)
            if ((avg * belowDefinition) > value) {
                rList.add(it.createdAt)
            }
        }

        return rList
    }
}