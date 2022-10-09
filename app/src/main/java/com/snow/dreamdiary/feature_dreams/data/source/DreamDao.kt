package com.snow.dreamdiary.feature_dreams.data.source

import androidx.room.*
import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import kotlinx.coroutines.flow.Flow

@Dao
interface DreamDao {

    @Query("SELECT * FROM dream")
    fun getDreams(): Flow<List<Dream>>

    @Query("SELECT * FROM dream WHERE id = :id")
    suspend fun getDreamById(id: Int): Dream?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDream(dream: Dream)

    @Delete
    suspend fun deleteDream(dream: Dream)
}