package com.example.language.di

import com.example.language.ui.component.languagebottomsheet.LanguageBottomSheetViewModel
import com.example.language.ui.screen.details.LanguageDetailsScreen
import com.example.language.ui.screen.details.LanguageDetailsScreenViewModel
import com.example.language.ui.screen.main.LanguageScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val languageModule = module {
    viewModel { LanguageScreenViewModel(get(), get()) }
    viewModel { LanguageBottomSheetViewModel(get()) }
    viewModel { LanguageDetailsScreenViewModel(get(), get(), get()) }
}
