package com.snow.dreamdiary.feature_dreams.domain.usecase

import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository
import kotlinx.coroutines.flow.first


class GetPersonsUseCase(
    private val repository: DreamRepository
) {
    operator fun invoke(): HashMap<String, Int> {
        return repository.getPersons()
    }

    suspend operator fun invoke(other: String): HashMap<String, Int> {


        val dreams = repository.getDreams().first()
        val personsMap: HashMap<String, Int> = HashMap()

        dreams.forEach {
            if (it.getAllModifiers().contains(other)) {
                it.persons.forEach { person ->
                    if (personsMap.containsKey(person)) {
                        val existingNum =
                            personsMap[person] ?: throw IllegalStateException("Should not be null")
                        personsMap[person] = existingNum + 1
                    } else {
                        personsMap[person] = 1
                    }
                }
            }
        }

        return personsMap
    }
}