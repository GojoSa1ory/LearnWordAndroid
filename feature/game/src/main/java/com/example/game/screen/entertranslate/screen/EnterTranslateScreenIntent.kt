package com.example.game.screen.entertranslate.screen

sealed class EnterTranslateScreenIntent {
    data class GetWords(val id: Int) : EnterTranslateScreenIntent()
    data object CheckAnswer : EnterTranslateScreenIntent()
    data object SwipeLanguage: EnterTranslateScreenIntent()
}