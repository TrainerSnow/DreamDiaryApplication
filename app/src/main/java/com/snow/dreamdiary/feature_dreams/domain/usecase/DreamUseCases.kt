package com.snow.dreamdiary.feature_dreams.domain.usecase

data class DreamUseCases(
    val getDreams: GetDreamsUseCase,
    val deleteDreams: DeleteDreamUseCase,
    val addDream: AddDreamUseCase,
    val editDream: EditDreamUseCase,
    val getNewFeelings: GetNewFeelingsUseCase,
    val getNewLocations: GetNewLocationsUseCase,
    val getNewPersons: GetNewPersonsUseCase,
    val getFeelings: GetFeelingsUseCase,
    val getLocations: GetLocationsUseCase,
    val getPersons: GetPersonsUseCase,
    val getDreamById: FindDreamByIdUseCase,
    val getDreamsForSearchConfig: GetDreamsForSearchConfig,
    val getModifiersInTime: GetModifiersInTime,
    val getComfortnesses: GetComfortnessesUseCase,
    val getModifierTimeStamps: GetModifierTimeStamps,
    val getDreamTimeStamps: GetDreamTimeStamps,
    val getComfortnessTimeStamps: GetComfortnessTimeStamps,
    val getEarliestDreamTimeStamp: GetEarliestDreamTimeStamp
)