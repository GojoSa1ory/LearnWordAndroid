package com.example.game.di

import com.example.game.screen.choosemodule.screen.ChooseScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val gameModule = module {

    viewModel<ChooseScreenViewModel>{ ChooseScreenViewModel(get()) }

}