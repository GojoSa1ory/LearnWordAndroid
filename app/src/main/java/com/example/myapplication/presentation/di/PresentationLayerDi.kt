package com.example.myapplication.presentation.di

import com.example.myapplication.presentation.screen.home.HomeScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationDi = module {
    viewModel { HomeScreenViewModel(get(), get(), get()) }
}