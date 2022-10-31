package com.snow.dreamdiary.feature_dreams.domain.usecase

import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository
import com.snow.dreamdiary.feature_dreams.domain.util.DreamModifier
import kotlinx.coroutines.flow.first

class GetModifiersInTime(
    private val repository: DreamRepository
) {
    suspend operator fun invoke(
        fromTime: Long,
        toTime: Long,
        modifierType: DreamModifier?,
        useComfortness: Boolean
    ): Pair<List<String>, List<Int>> {

        if (modifierType == null && !useComfortness) {
            throw IllegalArgumentException("modifierType == null && !useComfortness results to true, should not occur")
        }

        var dreams: List<Dream> = repository.getDreams().first()
        dreams = dreams.filter {
            it.dreamtAt in (fromTime + 1) until toTime
        }

        val nameList = mutableListOf<String>()
        val dataList = mutableListOf<Int>()

        if (useComfortness) {

            dreams.forEach {
                if (nameList.contains(it.comfortness.toString())) {
                    dataList[nameList.indexOf(it.comfortness.toString())] += 1
                } else {
                    nameList.add(it.comfortness.toString())
                    dataList.add(1)
                }
            }

        } else {
            when (modifierType) {
                DreamModifier.Person -> {
                    dreams.forEach {
                        it.persons.forEach { modifier ->
                            if (nameList.contains(modifier)) {
                                dataList[nameList.indexOf(modifier)] += 1
                            } else {
                                nameList.add(modifier)
                                dataList.add(1)
                            }
                        }
                    }
                }
                DreamModifier.Feeling -> {
                    dreams.forEach {
                        it.feelings.forEach { modifier ->
                            if (nameList.contains(modifier)) {
                                dataList[nameList.indexOf(modifier)] += 1
                            } else {
                                nameList.add(modifier)
                                dataList.add(1)
                            }
                        }
                    }
                }
                DreamModifier.Location -> {
                    dreams.forEach {
                        it.locations.forEach { modifier ->
                            if (nameList.contains(modifier)) {
                                dataList[nameList.indexOf(modifier)] += 1
                            } else {
                                nameList.add(modifier)
                                dataList.add(1)
                            }
                        }
                    }
                }
                else -> {
                    throw IllegalStateException("IDK")
                }
            }
        }
        return Pair(nameList, dataList)
    }
}