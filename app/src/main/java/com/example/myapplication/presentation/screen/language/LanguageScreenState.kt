package com.example.myapplication.presentation.screen.language

import com.example.myapplication.domain.models.LanguageAndWordsModel
import com.example.myapplication.domain.models.LanguageModel
import kotlinx.coroutines.flow.Flow

data class LanguageScreenState(
    val isLoading: Boolean = true,
    val languages: List<LanguageModel> = emptyList(),
    val error: String? = null
)