package com.snow.dreamdiary.feature_dreams.domain.usecase

class SurveyUseCases(
    val addSurvey: AddSurveyUseCase,
    val getSurveys: GetSurveysUseCase,
    val getDailySurveyPartAverage: GetDailySurveyPartAverageUseCase,
    val getSurveyPartsOverAverage: GetSurveyPartsOverAverage,
    getSurveyPartsBelowAverage: GetSurveyPartsBelowAverage
)