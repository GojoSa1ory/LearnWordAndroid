package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.daos.LanguageDao
import com.example.database.daos.WordDao
import com.example.database.entity.LanguageEntity
import com.example.database.entity.WordEntity

@Database(
    version = 2,
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
    abstract fun languageDao(): LanguageDao
}
