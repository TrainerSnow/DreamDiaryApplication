package com.snow.dreamdiary.feature_dreams.data.repository

import com.snow.dreamdiary.feature_dreams.data.source.DreamDao
import com.snow.dreamdiary.feature_dreams.domain.model.Dream
import com.snow.dreamdiary.feature_dreams.domain.repository.DreamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.json.JSONObject

class DreamRepositoryImpl(
    private val dreamDao: DreamDao
) : DreamRepository {

    override fun getDreams(): Flow<List<Dream>> {
        return dreamDao.getDreams()
    }

    override fun getPersons(): HashMap<String, Int> {
        val flow = dreamDao.getPersons()
        var list: List<String>

        runBlocking {
            list = flow.first()
        }

        val personsMap = hashMapOf<String, Int>()

        list.forEach { jsonString ->
            val obj = JSONObject(jsonString)
            val jsonArr = obj.getJSONArray("values")

            for (i in 0 until jsonArr.length()) {
                val person = jsonArr.getString(i)

                if (personsMap.containsKey(person)) {
                    val currentValue = personsMap[person] ?: continue
                    personsMap[person] = currentValue + 1
                } else {
                    personsMap[person] = 1
                }
            }
        }

        return personsMap
    }

    override fun getFeelings(): HashMap<String, Int> {
        val flow = dreamDao.getFeelings()
        var list: List<String>

        runBlocking {
            list = flow.first()
        }

        val feelingsMap = hashMapOf<String, Int>()

        list.forEach { jsonString ->
            val obj = JSONObject(jsonString)
            val jsonArr = obj.getJSONArray("values")

            for (i in 0 until jsonArr.length()) {
                val feeling = jsonArr.getString(i)

                if (feelingsMap.containsKey(feeling)) {
                    val currentValue = feelingsMap[feeling] ?: continue
                    feelingsMap[feeling] = currentValue + 1
                } else {
                    feelingsMap[feeling] = 1
                }
            }
        }

        return feelingsMap
    }

    override fun getLocations(): HashMap<String, Int> {
        val flow = dreamDao.getLocations()
        var list: List<String>

        runBlocking {
            list = flow.first()
        }

        val locationsMap = hashMapOf<String, Int>()

        list.forEach { jsonString ->
            val obj = JSONObject(jsonString)
            val jsonArr = obj.getJSONArray("values")

            for (i in 0 until jsonArr.length()) {
                val location = jsonArr.getString(i)

                if (locationsMap.containsKey(location)) {
                    val currentValue = locationsMap[location] ?: continue
                    locationsMap[location] = currentValue + 1
                } else {
                    locationsMap[location] = 1
                }
            }
        }

        return locationsMap
    }

    override suspend fun getDreamById(id: Int?): Dream? {
        if (id == null)
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