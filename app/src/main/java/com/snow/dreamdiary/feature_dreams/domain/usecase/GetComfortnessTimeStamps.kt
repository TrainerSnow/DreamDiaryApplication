package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository
import kotlinx.coroutines.flow.first

public class GetComfortnessTimeStamps(
    private val repository: DreamRepository
) {
    suspend operator fun invoke(comfortness: Int): List<Long>{
        val dreams = repository.getDreams().first()
        val timeStamps: MutableList<Long> = mutableListOf()

        dreams.forEach {
            if(it.comfortness == comfortness){
                timeStamps.add(it.dreamtAt)
            }
        }

        return timeStamps
    }
}