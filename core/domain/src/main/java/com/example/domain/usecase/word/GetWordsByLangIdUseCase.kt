package com.example.domain.usecase.word

import com.example.domain.model.WordModel
import com.example.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow

class GetWordsByLangIdUseCase(
    private val rep: WordRepository
) {
    suspend fun invoke(id: Int): Result<Flow<List<WordModel>>> {
        return rep.readWordsByLangId(id)
    }
}