package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.feature_dreams.domain.model.DailySurveyData

public class GetDailySurveyPartAverageUseCase(
    private val getSurveysUseCase: GetSurveysUseCase
) {
    suspend operator fun invoke(selector: (DailySurveyData) -> Int): Float{
        val surveys = getSurveysUseCase()
        val values = mutableListOf<Int>()

        surveys.forEach {
            values.add(selector(it))
        }

        return values.average().toFloat()
    }

    suspend operator fun invoke(dateRange: LongRange, selector: (DailySurveyData) -> Int): Float{
        val surveys = getSurveysUseCase(dateRange)
        val values = mutableListOf<Int>()

        surveys.forEach {
            values.add(selector(it))
        }

        return values.average().toFloat()
    }
}