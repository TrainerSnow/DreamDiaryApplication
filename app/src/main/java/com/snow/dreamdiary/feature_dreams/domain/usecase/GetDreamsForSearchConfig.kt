package com.snow.dreamdiary.feature_dreams.domain.usecase

import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository
import com.snow.dreamdiary.feature_dreams.domain.util.DreamSearchModes
import kotlinx.coroutines.flow.last

public class GetDreamsForSearchConfig(
    private val repository: DreamRepository
) {
    suspend operator fun invoke(searchMode: DreamSearchModes): List<Dream> {
        return when (searchMode) {
            is DreamSearchModes.ByComfortness -> getDreamsSearchedComfortness(searchMode)
            is DreamSearchModes.ByDreamt -> getDreamsSearchedDate(searchMode)
            is DreamSearchModes.ByModifier -> getDreamsSearchedModifiers(searchMode)
        }
    }

    private suspend fun getDreamsSearchedModifiers(searchMode: DreamSearchModes.ByModifier): List<Dream> {
        val dreams = repository.getDreams().last()
        val filteredDreams: MutableList<Dream> = mutableListOf()

        dreams.forEach { dream ->
            if (searchMode.gate.eval(
                    searchMode.values
                ) {
                    dream.getAllModifiers().contains(it)
                }
            ) {
                filteredDreams.add(dream)
            }
        }

        return filteredDreams
    }

    private suspend fun getDreamsSearchedDate(searchMode: DreamSearchModes.ByDreamt): List<Dream> {
        val dreams = repository.getDreams().last()
        val filteredDreams: MutableList<Dream> = mutableListOf()

        dreams.forEach { dream ->
            if ((searchMode.fromTime..searchMode.toTime).contains(dream.dreamtAt)){
                filteredDreams.add(dream)
            }
        }

        return filteredDreams
    }

    private suspend fun getDreamsSearchedComfortness(searchMode: DreamSearchModes.ByComfortness): List<Dream> {
        val dreams = repository.getDreams().last()
        val filteredDreams: MutableList<Dream> = mutableListOf()

        dreams.forEach { dream ->
            if ((searchMode.fromVal..searchMode.toVal).contains(dream.comfortness)){
                filteredDreams.add(dream)
            }
        }

        return filteredDreams
    }
}