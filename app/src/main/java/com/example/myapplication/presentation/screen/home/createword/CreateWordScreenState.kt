package com.example.myapplication.presentation.screen.home.createword

import com.example.myapplication.domain.models.LanguageModel

data class CreateWordScreenState (
    val errorMessage: String = "",
    val isError: Boolean = false,
    val languages: List<LanguageModel> = emptyList(),
    val mainWord: String = "",
    val translatedWord: String = "",
    val descriptionWord: String = "",
    val languageId: Int = 0
)