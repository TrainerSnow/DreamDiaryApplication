package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository

public class AddDreamUseCase(
    private val repository: DreamRepository
) {
    suspend operator fun invoke(dream: Dream){
        // TODO: Check if dream has valid inputs!!!! Else throw MessageException
        repository.insertDream(dream)
    }
}