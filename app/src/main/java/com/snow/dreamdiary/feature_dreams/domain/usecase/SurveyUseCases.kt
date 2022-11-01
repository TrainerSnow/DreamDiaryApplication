package com.snow.dreamdiary.feature_dreams.domain.usecase

class SurveyUseCases(
    val addSurvey: AddSurveyUseCase,
    val getSurveys: GetSurveysUseCase,
    getDailySurveyPartAverage: GetDailySurveyPartAverageUseCase
)