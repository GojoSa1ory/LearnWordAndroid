package com.example.database.di

import androidx.room.Room
import com.example.database.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "learnWord-database")
            .build()
    }

    single {
        val db = get<AppDatabase>()
        db.wordDao()
    }

    single {
        get<AppDatabase>().languageDao()
    }
}