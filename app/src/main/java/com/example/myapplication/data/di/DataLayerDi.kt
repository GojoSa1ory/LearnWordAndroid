package com.example.myapplication.data.di

import androidx.room.Room
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.repositories.WordRepositoryImpl
import com.example.myapplication.domain.repositories.WordRepository
import org.koin.dsl.module


val dataModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "learnWord_database")
            .build()
    }

    single {
        get<AppDatabase>().wordDao()
    }

    single<WordRepository> {
        WordRepositoryImpl(get())
    }

}