package com.example.language.ui.screen.details

import com.example.domain.model.LanguageModel
import com.example.domain.model.WordModel

sealed class LanguageDetailsScreenIntent {
    data class GetLanguageAndWordsById(val id: Int): LanguageDetailsScreenIntent()
    data class DeleteLanguageAndWords(val lang: LanguageModel) : LanguageDetailsScreenIntent()
    data class DeleteWord(val word: WordModel): LanguageDetailsScreenIntent()
}