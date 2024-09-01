package com.example.domain.model

data class LanguageAndWordsModel(
    val language: LanguageModel,
    val words: List<WordModel>
) {
    companion object
}