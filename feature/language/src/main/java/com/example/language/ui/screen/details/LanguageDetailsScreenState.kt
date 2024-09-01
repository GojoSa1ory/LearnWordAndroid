package com.example.language.ui.screen.details

import com.example.domain.model.LanguageAndWordsModel


data class LanguageDetailsScreenState(
    val isError: Boolean = false,
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val languageAndWords: LanguageAndWordsModel? = null
)