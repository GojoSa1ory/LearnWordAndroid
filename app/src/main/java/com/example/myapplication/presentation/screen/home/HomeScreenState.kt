package com.example.myapplication.presentation.screen.home

import com.example.myapplication.domain.models.WordModel
import kotlinx.coroutines.flow.Flow

data class HomeScreenState (
    val isLoading: Boolean = false,
    val words: List<com.example.myapplication.domain.models.WordModel> = emptyList(),
    val error: String? = null
){}