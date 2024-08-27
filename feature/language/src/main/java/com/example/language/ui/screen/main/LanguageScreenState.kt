package com.example.language.ui.screen.main

import com.example.domain.model.LanguageModel


data class LanguageScreenState(
    val isLoading: Boolean = true,
    val languages: List<LanguageModel> = emptyList(),
    val error: String? = null
)