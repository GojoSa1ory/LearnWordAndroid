package com.example.word.screen.create

import com.example.domain.model.LanguageModel

data class CreateScreenState (
    val isError: Boolean = false,
    val errorMessage: String = "",
    val mainWordValue: String = "",
    val translatedWordValue: String = "",
    val descriptionWord: String = "",
    val languages: List<LanguageModel> = emptyList(),
    val languageId: Int = 0
)