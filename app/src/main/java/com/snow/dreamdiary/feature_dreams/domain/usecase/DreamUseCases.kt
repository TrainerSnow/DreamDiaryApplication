package com.snow.dreamdiary.feature_dreams.domain.usecase;

data class DreamUseCases(
    val getDreams: GetDreamsUseCase,
    val deleteDreams: DeleteDreamUseCase
) {}