package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository

public class DeleteAllDreamsUseCase(
    private val repository: DreamRepository
) {
    suspend operator fun invoke(){
        repository.deleteAllDreams()
    }
}