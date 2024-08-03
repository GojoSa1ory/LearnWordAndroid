package com.example.myapplication.presentation.screen.home

import com.example.myapplication.domain.entities.WordEntity

data class HomeScreenState (
    val isLoading: Boolean = false,
    val words: List<WordEntity> = emptyList(),
    val error: String? = null
){}