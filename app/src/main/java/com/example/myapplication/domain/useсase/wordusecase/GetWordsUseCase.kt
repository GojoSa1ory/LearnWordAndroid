package com.example.myapplication.domain.useсase.wordusecase

import com.example.myapplication.domain.models.WordModel
import com.example.myapplication.domain.repositories.WordRepository
import kotlinx.coroutines.flow.Flow

class GetWordsUseCase(private val wordRep: WordRepository) {
    suspend fun execute (): Result<Flow<List<WordModel>>> {
        return wordRep.read()
    }
}