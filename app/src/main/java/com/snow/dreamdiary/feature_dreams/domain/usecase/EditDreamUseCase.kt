package com.snow.dreamdiary.feature_dreams.domain.usecase

import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository

class EditDreamUseCase(
    private val repository: DreamRepository
) {
    suspend operator fun invoke(dream: Dream) {
        val oldDream = repository.getDreamById(dream.id)
        //Swap out old dream to new one
        if (oldDream != null)
            repository.deleteDream(oldDream)
        repository.insertDream(dream)
    }
}