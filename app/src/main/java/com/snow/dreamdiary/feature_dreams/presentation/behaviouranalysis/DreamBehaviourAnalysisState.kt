package com.snow.dreamdiary.feature_dreams.presentation.behaviouranalysis

import com.snow.dreamdiary.feature_dreams.domain.util.DreamAffectables

data class DreamBehaviourAnalysisState(
    val affectable: DreamAffectables = DreamAffectables.Occurence,
    val percentValues: List<Double> = listOf(Double.NaN, Double.NaN, Double.NaN, Double.NaN)
)
