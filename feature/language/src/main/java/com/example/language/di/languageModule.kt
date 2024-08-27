package com.example.language.di

import com.example.language.ui.screen.main.LanguageScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val languageModule = module {
    viewModel { LanguageScreenViewModel(get()) }
}