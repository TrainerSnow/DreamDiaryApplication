package com.snow.dreamdiary.feature_dreams.domain.usecase

import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository
import kotlinx.coroutines.flow.first

class GetFeelingsUseCase(
    private val repository: DreamRepository
) {
    operator fun invoke(): HashMap<String, Int> {
        return repository.getFeelings()
    }

    suspend operator fun invoke(other: String): HashMap<String, Int> {
        val dreams = repository.getDreams().first()
        val feelingsMap: HashMap<String, Int> = HashMap()

        dreams.forEach {
            if (it.getAllModifiers().contains(other)) {
                it.feelings.forEach { feeling ->
                    if (feelingsMap.containsKey(feeling)) {
                        val existingNum = feelingsMap[feeling]
                            ?: throw IllegalStateException("Should not be null")
                        feelingsMap[feeling] = existingNum
                    } else {
                        feelingsMap[feeling] = 1
                    }
                }
            }
        }

        return feelingsMap
    }
}