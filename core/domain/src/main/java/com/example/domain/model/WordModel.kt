package com.example.domain.model

data class WordModel (
    val mainWord: String,
    val translatedWord: String,
    val wordDescription: String,
    val languageId: Int,
    val languageName: String
) {
    companion object
}