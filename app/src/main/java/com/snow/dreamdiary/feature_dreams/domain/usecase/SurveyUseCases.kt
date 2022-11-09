package com.snow.dreamdiary.feature_dreams.domain.usecase

class SurveyUseCases(
    val addSurvey: AddSurveyUseCase,
    val getSurveys: GetSurveysUseCase,
    val getDailySurveyPartAverage: GetDailySurveyPartAverageUseCase,
    val getSurveyPartsOverAverage: GetSurveyPartsOverAverage,
    val getSurveyPartsBelowAverage: GetSurveyPartsBelowAverage,
    val getEffectiveSurveyData: GetEffectiveSurveyDataUseCase,
    val getTimeStampsDailySurveyPartOverAverage: GetTimeStampsDailySurveyPartOverAverage
)