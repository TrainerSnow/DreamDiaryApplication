package com.snow.dreamdiary.feature_dreams.domain.usecase

import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository
import kotlinx.coroutines.flow.first

class GetComfortnessesUseCase(
    private val repository: DreamRepository
) {
    operator fun invoke(): HashMap<String, Int> {
        return repository.getComfortnesses()
    }

    suspend operator fun invoke(other: String): HashMap<String, Int> {
        val dreams = repository.getDreams().first()
        val comfortnessMap: HashMap<String, Int> = HashMap()

        dreams.forEach {
            if (it.getAllModifiers().contains(other)) {
                if (comfortnessMap.containsKey(it.comfortness.toString())) {
                    val existingNum = comfortnessMap[it.comfortness.toString()]
                        ?: throw IllegalStateException("Should not be null")
                    comfortnessMap[it.comfortness.toString()] = existingNum + 1
                } else {
                    comfortnessMap[it.comfortness.toString()] = 1
                }
            }
        }

        return comfortnessMap
    }
}