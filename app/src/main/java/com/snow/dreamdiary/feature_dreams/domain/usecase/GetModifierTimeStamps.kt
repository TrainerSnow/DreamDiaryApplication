package com.snow.dreamdiary.feature_dreams.domain.usecase;

import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository
import kotlinx.coroutines.flow.first

public class GetModifierTimeStamps(
    private val repository: DreamRepository
) {

    suspend operator fun invoke(modifier: String): List<Long>{
        val dreams = repository.getDreams().first()
        val timeStamps: MutableList<Long> = mutableListOf()

        dreams.forEach{
            if(modifier in it.getAllModifiers()){
                timeStamps.add(it.dreamtAt)
            }
        }

        return timeStamps
    }

}