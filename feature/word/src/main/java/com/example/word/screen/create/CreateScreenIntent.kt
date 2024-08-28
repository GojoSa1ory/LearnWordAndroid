package com.example.word.screen.create

sealed class CreateScreenIntent {
    data object CreateWord: CreateScreenIntent()
    data object GetLanguages: CreateScreenIntent()
}