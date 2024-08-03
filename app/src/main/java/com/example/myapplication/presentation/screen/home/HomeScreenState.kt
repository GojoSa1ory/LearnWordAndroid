package com.example.myapplication.presentation.screen.home

import com.example.myapplication.domain.entities.WordModel

data class HomeScreenState (
    val isLoading: Boolean = false,
    val words: List<WordModel> = emptyList(),
    val error: String? = null
){}