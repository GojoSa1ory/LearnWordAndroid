package com.example.myapplication.domain.useсase.word

import com.example.myapplication.domain.models.WordAndLanguageModel
import com.example.myapplication.domain.repositories.WordRepository
import kotlinx.coroutines.flow.Flow

class GetWordsUseCase(private val wordRep: WordRepository) {
    suspend fun execute (): Result<Flow<List<WordAndLanguageModel>>> {
        return wordRep.read()
    }
}