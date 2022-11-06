package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.common.extension.average
import com.snow.dreamdiary.common.util.TimeUtil
import com.snow.dreamdiary.feature_dreams.domain.util.DreamAffectables
import kotlinx.coroutines.flow.first

public class GetDreamAffectablesAverageUseCase(
    private val getDreamsUseCase: GetDreamsUseCase,
    private val getEarliestDreamTimeStampUseCase: GetEarliestDreamTimeStampUseCase
) {
    suspend operator fun invoke(affectables: DreamAffectables): Double{
        val dreams = getDreamsUseCase().first()

        return when (affectables) {
            DreamAffectables.Comfortness -> {
                dreams.average { it.comfortness }
            }
            DreamAffectables.Occurence -> {
                val daysSinceFirstDream = TimeUtil.millisToDayNum(getEarliestDreamTimeStampUseCase())
                dreams.size.toDouble()/daysSinceFirstDream
            }
        }
    }
}