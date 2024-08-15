package com.example.myapplication.presentation.screen.language

sealed class LanguageScreenIntent {
    object GetLanguages: LanguageScreenIntent()
    data class SearchLanguage(val req: String): LanguageScreenIntent()
}