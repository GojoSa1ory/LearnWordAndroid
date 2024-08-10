package com.example.myapplication.domain.useсase.wordusecase

import com.example.myapplication.domain.models.LanguageAndWordsModel
import com.example.myapplication.domain.models.WordAndLanguageModel
import com.example.myapplication.domain.models.WordModel
import com.example.myapplication.domain.repositories.WordRepository
import kotlinx.coroutines.flow.Flow

class SearchWordUseCase(private val rep: WordRepository) {
    suspend fun execute (req: String): Result<Flow<List<WordAndLanguageModel>>> {
        return rep.search(req)
    }
}