package com.example.myapplication.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.daos.WordDao
import com.example.myapplication.data.entities.LanguageEntity
import com.example.myapplication.data.entities.WordEntity

@Database(
    version = 1,
    entities = [
        WordEntity::class,
        LanguageEntity::class,
    ],
//    autoMigrations = [
//        AutoMigration (from = 1, to = 2)
//    ],
    exportSchema = true
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDao
}
