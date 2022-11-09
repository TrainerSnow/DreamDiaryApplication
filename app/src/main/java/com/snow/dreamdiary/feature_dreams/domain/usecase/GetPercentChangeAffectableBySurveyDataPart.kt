package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.feature_dreams.domain.model.DailySurveyData
import com.snow.dreamdiary.feature_dreams.domain.util.DreamAffectables

public class GetPercentChangeAffectableBySurveyDataPart(
    private val getDailySurveyPartAverageUseCase: GetDailySurveyPartAverageUseCase,
    private val getAffectableAvgSpecificDays: GetAffectableAvgSpecificDays
) {
    suspend operator fun invoke(
        selector: (DailySurveyData) -> Int, affectable: DreamAffectables
    ): Double {
        val allTimeAvg = getDailySurveyPartAverageUseCase(selector)
        val specifiedAvg = getAffectableAvgSpecificDays(selector, affectable)

        val percent = specifiedAvg/allTimeAvg

        val percentChange = 1 - percent

        return percentChange
    }
}