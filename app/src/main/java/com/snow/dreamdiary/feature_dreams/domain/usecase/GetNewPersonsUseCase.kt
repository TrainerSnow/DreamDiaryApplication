package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository

class GetNewPersonsUseCase(
    private val repository: DreamRepository
) {
    operator fun invoke(
        newPersons: List<String>
    ): List<String> {
        val existingPersons = repository.getPersons()

        return newPersons.subtract(existingPersons.toSet()).toList()
    }
}