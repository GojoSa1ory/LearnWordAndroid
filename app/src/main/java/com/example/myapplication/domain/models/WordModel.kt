package com.example.myapplication.domain.models

import com.example.myapplication.data.model.LanguageModel

data class WordModel (
    val _id: Int,
    val mainWord: String,
    val translatedWord: String,
    val wordDescription: String?,

    val language: LanguageModel?
)