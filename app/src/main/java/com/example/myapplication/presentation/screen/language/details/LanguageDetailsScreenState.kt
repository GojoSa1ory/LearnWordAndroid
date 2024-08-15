package com.example.myapplication.presentation.screen.language.details


import com.example.myapplication.domain.models.LanguageAndWordsModel

data class LanguageDetailsScreenState(
    val isError: Boolean = false,
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val languageAndWords: LanguageAndWordsModel? = null
)