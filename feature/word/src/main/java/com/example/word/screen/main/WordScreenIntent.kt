package com.example.word.screen.main

import com.example.domain.model.WordModel

sealed class WordScreenIntent {
    object GetWords: WordScreenIntent()
    data class SearchWord(val req: String): WordScreenIntent()
    data class DeleteWord(val word: com.example.domain.model.WordModel): WordScreenIntent()
}