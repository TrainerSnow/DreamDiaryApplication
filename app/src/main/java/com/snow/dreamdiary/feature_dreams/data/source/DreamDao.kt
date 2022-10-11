package com.snow.dreamdiary.feature_dreams.data.source

import androidx.room.*
import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import kotlinx.coroutines.flow.Flow

@Dao
interface DreamDao {

    @Query("SELECT * FROM dream")
    fun getDreams(): Flow<List<Dream>>

    @Query("SELECT persons FROM dream")
    fun getPersons(): Flow<List<String>>

    @Query("SELECT feelings FROM dream")
    fun getFeelings(): Flow<List<String>>

    @Query("SELECT locations FROM dream")
    fun getLocations(): Flow<List<String>>

    @Query("SELECT * FROM dream WHERE id = :id")
    suspend fun getDreamById(id: Int): Dream?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDream(dream: Dream)

    @Delete
    suspend fun deleteDream(dream: Dream)
}