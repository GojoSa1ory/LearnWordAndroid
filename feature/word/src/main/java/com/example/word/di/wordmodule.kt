package com.example.word.di

import com.example.word.screen.create.CreateScreenViewModel
import com.example.word.screen.main.WordScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val wordModule = module {
    viewModel { WordScreenViewModel(get()) }
    viewModel { CreateScreenViewModel(get(), get()) }
}