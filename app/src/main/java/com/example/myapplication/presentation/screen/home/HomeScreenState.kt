package com.example.myapplication.presentation.screen.home

import com.example.myapplication.domain.models.WordAndLanguageModel
import com.example.myapplication.domain.models.WordModel
import kotlinx.coroutines.flow.Flow

data class HomeScreenState (
    val isLoading: Boolean = false,
    val words: List<WordAndLanguageModel> = emptyList(),
    val error: String? = null
){}