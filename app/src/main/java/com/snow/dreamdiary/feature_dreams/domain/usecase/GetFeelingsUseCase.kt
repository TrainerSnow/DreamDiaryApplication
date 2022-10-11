package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository

class GetFeelingsUseCase(
    private val repository: DreamRepository
) {
    operator fun invoke(): HashMap<String, Int> {
        return repository.getFeelings()
    }
}