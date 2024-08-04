package com.example.myapplication.domain.di

import com.example.myapplication.domain.useсase.wordusecase.CreateWordUseCase
import com.example.myapplication.domain.useсase.wordusecase.DeleteWordUseCase
import com.example.myapplication.domain.useсase.wordusecase.GetWordsUseCase
import com.example.myapplication.domain.useсase.wordusecase.SearchWordUseCase
import com.example.myapplication.domain.useсase.wordusecase.UpdateWordUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetWordsUseCase(get()) }
    factory { CreateWordUseCase(get()) }
    factory { UpdateWordUseCase(get()) }
    factory { DeleteWordUseCase(get()) }
    factory { SearchWordUseCase(get()) }
}