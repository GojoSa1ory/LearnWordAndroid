package com.example.language.ui.screen.details

import com.example.domain.model.LanguageModel

sealed class LanguageDetailsScreenIntent {
    data class GetLanguageAndWordsById(val id: Int): LanguageDetailsScreenIntent()
    data class DeleteLanguageAndWords(val lang: LanguageModel) : LanguageDetailsScreenIntent()
}