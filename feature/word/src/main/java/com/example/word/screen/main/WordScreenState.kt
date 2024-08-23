package com.example.word.screen.main

import com.example.domain.model.WordModel

data class WordScreenState (
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val words: List<com.example.domain.model.WordModel> = emptyList(),
    val searchReq: String = ""
)