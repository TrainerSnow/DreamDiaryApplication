package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository

class GetLocationsUseCase(
    private val repository: DreamRepository
) {
    operator fun invoke(): HashMap<String, Int> {
        return repository.getLocations()
    }
}