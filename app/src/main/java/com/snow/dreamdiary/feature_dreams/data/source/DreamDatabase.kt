package com.snow.dreamdiary.feature_dreams.data.source;

import androidx.room.Database
import androidx.room.RoomDatabase
import com.snow.dreamdiary.feature_dreams.domain.model.Dream

@Database(
    entities = [
        Dream::class
    ],
    version = 1
)
abstract class DreamDatabase: RoomDatabase() {
    abstract val dreamDao: DreamDao
}