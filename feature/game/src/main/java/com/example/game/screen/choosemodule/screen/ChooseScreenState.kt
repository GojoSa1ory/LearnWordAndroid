package com.example.game.screen.choosemodule.screen

import com.example.domain.model.LanguageModel

data class ChooseScreenState(
    val loading: Boolean = true,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val languages: List<LanguageModel> = emptyList()
)