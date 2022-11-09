package com.snow.dreamdiary.feature_dreams.domain.usecase

data class DreamSurveyUseCases(
    val getAffectableAvgSpecificDays: GetAffectableAvgSpecificDays,
    val getPercentChangeAffectableBySurveyDataPart: GetPercentChangeAffectableBySurveyDataPart
)
