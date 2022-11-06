package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.feature_dreams.domain.util.DreamAffectables

public class GetDreamAffectablesAverage(
    private val getDreamsUseCase: GetDreamsUseCase
) {
    suspend operator fun invoke(affectables: DreamAffectables): Float{
        val dreams = getDreamsUseCase()

        val avg = when (affectables) {
            DreamAffectables.Comfortness -> {

            }
            DreamAffectables.Occurence -> TODO()
        }
    }
}