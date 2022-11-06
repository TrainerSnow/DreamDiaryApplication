package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.feature_dreams.domain.model.DailySurveyData

public class GetTimeStampsDailySurveyPartOverAverage(
    private val getDailySurveyPartAverageUseCase: GetDailySurveyPartAverageUseCase,
    private val getSurveysUseCase: GetSurveysUseCase
) {
    suspend operator fun invoke(selector: (DailySurveyData) -> Int): List<Long>{
        val avg = getDailySurveyPartAverageUseCase(selector)
        return getSurveysUseCase().filter { selector(it) > avg }.map { it.createdAt }
    }

    suspend operator fun invoke(selector: (DailySurveyData) -> Int, dateRange: LongRange): List<Long>{
        val avg = getDailySurveyPartAverageUseCase(dateRange, selector)
        return getSurveysUseCase().filter { selector(it) > avg }.map { it.createdAt }
    }
}