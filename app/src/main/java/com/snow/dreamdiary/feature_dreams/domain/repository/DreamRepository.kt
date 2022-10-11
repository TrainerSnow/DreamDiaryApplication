package com.snow.dreamdiary.feature_dreams.domain.repository

import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import kotlinx.coroutines.flow.Flow

interface DreamRepository {

    fun getDreams(): Flow<List<Dream>>

    fun getPersons(): HashMap<String, Int>

    fun getFeelings(): HashMap<String, Int>

    fun getLocations(): HashMap<String, Int>

    suspend fun getDreamById(id: Int?): Dream?

    suspend fun insertDream(dream: Dream)

    suspend fun deleteDream(dream: Dream)

}