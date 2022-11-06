package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.common.util.TimeUtil
import com.snow.dreamdiary.feature_dreams.domain.model.DailySurveyData
import com.snow.dreamdiary.feature_dreams.domain.util.DreamAffectables

/**
 * @return Average value of the affectable (Dream comf. or occurence) on days where selector (e.g. physical activity) was over-averaged
 */
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