package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository

class GetNewFeelingsUseCase(
    private val repository: DreamRepository
) {
    operator fun invoke(
        newFeelings: List<String>
    ): List<String> {
        val existingFeelings = repository.getFeelings()

        return newFeelings.subtract(existingFeelings.toSet()).toList()
    }
}