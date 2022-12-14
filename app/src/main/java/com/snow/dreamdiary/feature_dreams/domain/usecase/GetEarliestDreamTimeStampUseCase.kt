package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository
import kotlinx.coroutines.flow.first

public class GetEarliestDreamTimeStampUseCase(
    private val repository: DreamRepository
) {
    suspend operator fun invoke(): Long{
        return repository.getDreams().first().minBy { it.dreamtAt }.dreamtAt
    }
}