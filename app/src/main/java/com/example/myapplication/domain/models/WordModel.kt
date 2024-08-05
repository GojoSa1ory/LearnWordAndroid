package com.example.myapplication.domain.models

data class WordModel (
    val _id: Int,
    val mainWord: String,
    val translatedWord: String,
    val wordDescription: String?
)