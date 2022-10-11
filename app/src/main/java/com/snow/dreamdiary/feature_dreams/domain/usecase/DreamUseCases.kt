package com.snow.dreamdiary.feature_dreams.domain.usecase;

data class DreamUseCases(
    val getDreams: GetDreamsUseCase,
    val deleteDreams: DeleteDreamUseCase,
    val addDream: AddDreamUseCase,
    val editDream: EditDreamUseCase,
    val getNewFeelings: GetNewFeelingsUseCase,
    val getNewLocations: GetNewLocationsUseCase,
    val getNewPersons: GetNewPersonsUseCase,
) {}