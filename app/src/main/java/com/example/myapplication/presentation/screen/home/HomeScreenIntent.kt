package com.example.myapplication.presentation.screen.home

import com.example.myapplication.domain.models.WordModel

sealed class HomeScreenIntent {
    object LoadWords : HomeScreenIntent()
    data class DeleteWords(val word: com.example.myapplication.domain.models.WordModel): HomeScreenIntent()
    data class Search(val request: String): HomeScreenIntent()
}