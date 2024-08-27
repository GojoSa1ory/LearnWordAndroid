package com.example.language.ui.screen.details



data class LanguageDetailsScreenState(
    val isError: Boolean = false,
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
//    val languageAndWords: LanguageAndWordsModel? = null
)