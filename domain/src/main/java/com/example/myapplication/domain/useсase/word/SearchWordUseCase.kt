package com.example.myapplication.domain.useсase.word

import com.example.myapplication.domain.models.WordAndLanguageModel
import com.example.myapplication.domain.repositories.WordRepository
import kotlinx.coroutines.flow.Flow

class SearchWordUseCase(private val rep: WordRepository) {
    suspend fun execute (req: String): Result<Flow<List<WordAndLanguageModel>>> {
        return rep.search(req)
    }
}