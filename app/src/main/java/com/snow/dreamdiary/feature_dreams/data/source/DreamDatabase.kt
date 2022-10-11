package com.snow.dreamdiary.feature_dreams.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.snow.dreamdiary.feature_dreams.domain.model.Dream

@Database(
    entities = [
        Dream::class
    ],
    version = 1
)
@TypeConverters(com.snow.dreamdiary.common.data.TypeConverters::class)
abstract class DreamDatabase : RoomDatabase() {
    abstract val dreamDao: DreamDao

    companion object {
        const val DATABASE_NAME = "dreams_db"
    }
}