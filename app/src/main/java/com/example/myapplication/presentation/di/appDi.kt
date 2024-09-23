package com.example.myapplication.presentation.di

import com.example.domain.usecase.language.CreateLanguageUseCase
import com.example.domain.usecase.language.DeleteLanguageUseCase
import com.example.domain.usecase.language.GetLanguageAndWordsByIdUseCase
import com.example.domain.usecase.language.GetLanguageUseCase
import com.example.domain.usecase.language.SearchLangUseCase
import com.example.domain.usecase.language.UpdateLanguageUseCase
import com.example.domain.usecase.word.CreateWordUseCase
import com.example.domain.usecase.word.DeleteWordUseCase
import com.example.domain.usecase.word.GetWordWithLanguageUseCase
import com.example.domain.usecase.word.GetWordsByLangIdUseCase
import com.example.domain.usecase.word.SearchWordUseCase
import org.koin.dsl.module

val appModule = module {

    factory { GetWordWithLanguageUseCase(get()) }
    factory { DeleteWordUseCase(get()) }
    factory { SearchWordUseCase(get()) }
    factory { CreateWordUseCase(get()) }
    factory { GetWordsByLangIdUseCase(get()) }

    factory { GetLanguageUseCase(get()) }
    factory { CreateLanguageUseCase(get()) }
    factory { GetLanguageAndWordsByIdUseCase(get()) }
    factory { DeleteLanguageUseCase(get()) }
    factory { SearchLangUseCase(get()) }
    factory { UpdateLanguageUseCase(get()) }

}