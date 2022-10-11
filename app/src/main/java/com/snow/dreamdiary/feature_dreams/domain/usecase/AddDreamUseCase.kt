package com.snow.dreamdiary.feature_dreams.domain.usecase

import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository

class AddDreamUseCase(
    private val repository: DreamRepository
) {
    suspend operator fun invoke(dream: Dream) {
        //No need to check if anything is empty
        repository.insertDream(dream)
    }
}