package com.example.game.di

import com.example.game.screen.choosemodule.screen.ChooseScreenViewModel
import com.example.game.screen.chooserightvariant.screen.ChooseCorrectAnswerScreenViewModel
import com.example.game.screen.entertranslate.screen.EnterTranslateScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val gameModule = module {

    viewModel<ChooseScreenViewModel>{ ChooseScreenViewModel(get()) }
    viewModel { EnterTranslateScreenViewModel(get()) }
    viewModel { ChooseCorrectAnswerScreenViewModel(get()) }
}