package com.snow.dreamdiary.feature_dreams.domain.usecase

import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository

class FindDreamByIdUseCase(
    private val repository: DreamRepository
) {
    suspend operator fun invoke(id: Int?): Dream? {
        return repository.getDreamById(id)
    }
}