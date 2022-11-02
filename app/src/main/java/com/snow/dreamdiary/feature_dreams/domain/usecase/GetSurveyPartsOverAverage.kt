package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.feature_dreams.domain.model.DailySurveyData

public class GetSurveyPartsOverAverage(
    private val getSurveysUseCase: GetSurveysUseCase,
    private val getDailySurveyPartAverageUseCase: GetDailySurveyPartAverageUseCase
) {
    suspend operator fun invoke(
        selector : (DailySurveyData) -> Int,
        overDefinition: Float = 1.4F
    ): List<Long> {
        val avg = getDailySurveyPartAverageUseCase(selector)
        val surveys = getSurveysUseCase()

        val rList: MutableList<Long> = mutableListOf()

        surveys.forEach {
            val value = selector(it)
            if ((avg * overDefinition) < value) {
                rList.add(it.createdAt)
            }
        }

        return rList
    }

    suspend operator fun invoke(
        selector: (DailySurveyData) -> Int,
        overDefinition: Float = 1.4F,
        dateRange: LongRange
    ): List<Long> {
        val avg = getDailySurveyPartAverageUseCase(dateRange, selector)
        val surveys = getSurveysUseCase(dateRange)

        val rList: MutableList<Long> = mutableListOf()

        surveys.forEach {
            val value = selector(it)
            if ((avg * overDefinition) < value) {
                rList.add(it.createdAt)
            }
        }

        return rList
    }
}