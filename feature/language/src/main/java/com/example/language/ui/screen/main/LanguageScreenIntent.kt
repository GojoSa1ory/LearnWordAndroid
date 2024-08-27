package com.example.language.ui.screen.main

sealed class LanguageScreenIntent {
    object GetLanguages: LanguageScreenIntent()
    data class SearchLanguage(val req: String): LanguageScreenIntent()
}