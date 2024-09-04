package com.example.game.screen.entertranslate.screen

import com.example.domain.model.WordModel

data class EnterTranslateScreenState (
    val isError: Boolean = false,
    val isCorrect: Boolean = false,
    val isNextEnable: Boolean = false,
    val isShowAnswer: Boolean = false,
    val isShowStatsScreen: Boolean = false,
    val errorMessage: String = "",
    val translatedWord: String = "",
    val userTranslate: String = "",
    val words: List<WordModel> = emptyList(),
    val currentWord: WordModel? = null,
)