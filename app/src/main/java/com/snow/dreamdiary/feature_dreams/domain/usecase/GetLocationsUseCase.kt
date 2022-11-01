package com.snow.dreamdiary.feature_dreams.domain.usecase

import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository
import kotlinx.coroutines.flow.first

class GetLocationsUseCase(
    private val repository: DreamRepository
) {
    operator fun invoke(): HashMap<String, Int> {
        return repository.getLocations()
    }

    suspend operator fun invoke(other: String): HashMap<String, Int> {
        val dreams = repository.getDreams().first()
        val locationsMap: HashMap<String, Int> = HashMap()

        dreams.forEach {
            if (it.getAllModifiers().contains(other)) {
                it.locations.forEach { location ->
                    if (locationsMap.containsKey(location)) {
                        val existingNum = locationsMap[location]
                            ?: throw IllegalStateException("Should not be null")
                        locationsMap[location] = existingNum + 1
                    } else {
                        locationsMap[location] = 1
                    }
                }
            }
        }
        locationsMap.remove(other)
        return locationsMap
    }
}