package com.snow.dreamdiary.feature_dreams.domain.usecase

import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository

class GetNewLocationsUseCase(
    private val repository: DreamRepository
) {
    operator fun invoke(
        newLocations: List<String>
    ): List<String> {
        val existingLocations = repository.getLocations()

        return newLocations.subtract(existingLocations.keys).toList()
    }
}