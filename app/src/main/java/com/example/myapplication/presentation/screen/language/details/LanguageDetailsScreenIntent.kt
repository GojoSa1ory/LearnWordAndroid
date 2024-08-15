package com.example.myapplication.presentation.screen.language.details

import com.example.myapplication.domain.models.LanguageModel

sealed class LanguageDetailsScreenIntent {
    data class GetLanguageAndWordsById(val id: Int): LanguageDetailsScreenIntent()
    data class DeleteLanguageAndWords(val lang: LanguageModel) : LanguageDetailsScreenIntent()
}