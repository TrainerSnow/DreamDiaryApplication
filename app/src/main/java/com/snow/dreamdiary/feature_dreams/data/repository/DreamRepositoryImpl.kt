package com.snow.dreamdiary.feature_dreams.data.repository;

import androidx.lifecycle.LiveData
import com.snow.dreamdiary.feature_dreams.data.source.DreamDao
import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class DreamRepositoryImpl(
    private val dreamDao: DreamDao
): DreamRepository {

    override fun getDreams(): Flow<List<Dream>> {
        return dreamDao.getDreams()
    }

    override fun getPersons(): List<String> {
        val flow = dreamDao.getPersons()
        var list: List<String>

        runBlocking {
            list = flow.first()
        }

        return list
    }

    override fun getFeelings(): List<String> {
        val flow = dreamDao.getFeelings()
        var list: List<String>

        runBlocking {
            list = flow.first()
        }

        return list
    }

    override fun getLocations(): List<String> {
        val flow = dreamDao.getLocations()
        var list: List<String>

        runBlocking {
            list = flow.first()
        }

        return list
    }

    override suspend fun getDreamById(id: Int?): Dream? {
        if(id == null)
            return null
        return dreamDao.getDreamById(id)
    }

    override suspend fun insertDream(dream: Dream) {
        dreamDao.insertDream(dream)
    }

    override suspend fun deleteDream(dream: Dream) {
        dreamDao.deleteDream(dream)
    }

}