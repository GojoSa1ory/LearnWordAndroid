package com.example.myapplication.domain.models

data class WordModel (
    val mainWord: String,
    val translatedWord: String,
    val wordDescription: String?,
    val languageId: Int
) {
    companion object
}