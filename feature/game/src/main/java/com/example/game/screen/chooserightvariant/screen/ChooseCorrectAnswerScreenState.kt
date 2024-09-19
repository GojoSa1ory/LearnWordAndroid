package com.example.game.screen.chooserightvariant.screen

import com.example.domain.model.WordModel

data class ChooseCorrectAnswerScreenState (
    val isCorrect: Boolean = false,
    val isNextEnable: Boolean = false,
    val isShowStatsScreen: Boolean = false,
    val isError: Boolean = false,
    val errMessage: String = "",
    val words: List<WordModel> = emptyList(),
    val answers: List<String> = emptyList(),
    val currentWord: WordModel? = null,
    val currentWordPosition: Int = 1,
    val wordsCount: Int = 0,
)