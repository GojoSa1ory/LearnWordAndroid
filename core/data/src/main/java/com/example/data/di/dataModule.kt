package com.example.data.di

import com.example.data.repository.WordRepositoryImpl
import com.example.domain.WordRepository
import org.koin.dsl.module

val dataModule = module {
    single<WordRepository> {
        WordRepositoryImpl(get())
    }
}