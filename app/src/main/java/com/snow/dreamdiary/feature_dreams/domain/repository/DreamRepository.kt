package com.snow.dreamdiary.feature_dreams.domain.repository

import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import kotlinx.coroutines.flow.Flow

interface DreamRepository {

    fun getDreams(): Flow<List<Dream>>

    fun getPersons(): List<String>

    fun getFeelings(): List<String>

    fun getLocations(): List<String>

    suspend fun getDreamById(id: Int?): Dream?

    suspend fun insertDream(dream: Dream)

    suspend fun deleteDream(dream: Dream)

}