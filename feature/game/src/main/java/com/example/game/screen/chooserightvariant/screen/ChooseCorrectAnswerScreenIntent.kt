package com.example.game.screen.chooserightvariant.screen

import com.example.domain.model.WordModel

sealed class ChooseCorrectAnswerScreenIntent {
    data class FetchWords(val id: Int): ChooseCorrectAnswerScreenIntent()
    data class CheckAnswer(val word: String): ChooseCorrectAnswerScreenIntent()
    data object SwitchAnswer: ChooseCorrectAnswerScreenIntent()
}