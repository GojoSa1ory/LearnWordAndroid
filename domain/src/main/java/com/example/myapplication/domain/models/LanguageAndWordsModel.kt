package com.example.myapplication.domain.models

data class LanguageAndWordsModel (
    val language: LanguageModel,
    val words: List<WordModel>
) {
    companion object
}