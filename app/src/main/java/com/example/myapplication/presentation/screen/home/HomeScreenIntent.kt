package com.example.myapplication.presentation.screen.home

import com.example.myapplication.domain.entities.WordEntity

sealed class HomeScreenIntent {
    object LoadWords : HomeScreenIntent()
    data class DeleteWords(val word: WordEntity): HomeScreenIntent()
    data class Search(val request: String): HomeScreenIntent()
}