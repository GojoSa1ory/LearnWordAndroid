package com.example.data.di

import com.example.data.repository.LanguageRepositoryImpl
import com.example.data.repository.WordRepositoryImpl
import com.example.domain.repository.LanguageRepository
import com.example.domain.repository.WordRepository
import org.koin.dsl.module

val dataModule = module {
    single<WordRepository> {
        WordRepositoryImpl(get())
    }

    single<LanguageRepository> {
        LanguageRepositoryImpl(get())
    }
}