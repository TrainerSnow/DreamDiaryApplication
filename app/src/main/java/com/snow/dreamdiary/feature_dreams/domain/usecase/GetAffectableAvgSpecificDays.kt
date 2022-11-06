package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.common.util.TimeUtil
import com.snow.dreamdiary.feature_dreams.domain.model.DailySurveyData
import com.snow.dreamdiary.feature_dreams.domain.util.DreamAffectables

public class GetAffectableAvgSpecificDays(
    private val getTimeStampsDailySurveyPartOverAverage: GetTimeStampsDailySurveyPartOverAverage,
    private val getDreamAffectablesAverageUseCase: GetDreamAffectablesAverageUseCase
) {
    suspend operator fun invoke(selector: (DailySurveyData) -> Int, affectable: DreamAffectables): Double{
        val stamps = getTimeStampsDailySurveyPartOverAverage(selector)
        val avg = getDreamAffectablesAverageUseCase(affectable, stamps)
        return avg
    }
}