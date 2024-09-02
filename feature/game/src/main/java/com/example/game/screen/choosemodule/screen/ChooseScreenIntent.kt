package com.example.game.screen.choosemodule.screen

import com.example.domain.model.LanguageModel

sealed class ChooseScreenIntent {
    object GetLanguages: ChooseScreenIntent()
}