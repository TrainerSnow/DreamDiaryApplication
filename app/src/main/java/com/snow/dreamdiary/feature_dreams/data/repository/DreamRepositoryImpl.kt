package com.snow.dreamdiary.feature_dreams.data.repository;

import com.snow.dreamdiary.feature_dreams.data.source.DreamDao
import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository
import kotlinx.coroutines.flow.Flow

class DreamRepositoryImpl(
    private val dreamDao: DreamDao
): DreamRepository {

    override fun getDreams(): Flow<List<Dream>> {
        return dreamDao.getDreams()
    }

    override suspend fun getDreamById(id: Int): Dream? {
        return dreamDao.getDreamById(id)
    }

    override suspend fun insertDream(dream: Dream) {
        dreamDao.insertDream(dream)
    }

    override suspend fun deleteDream(dream: Dream) {
        dreamDao.deleteDream(dream)
    }

}